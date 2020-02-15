package com.rentodlist.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dailytypetotal.model.DailyTotalService;
import com.dailytypetotal.model.DailyTotalVO;
import com.equipment.model.EquipmentService;
import com.equipment.model.EquipmentVO;
import com.rentoddetail.model.RentOdDetailService;
import com.rentoddetail.model.RentOdDetailVO;
import com.rentodlist.model.RentOdListDAO;
import com.rentodlist.model.RentOdListService;
import com.rentodlist.model.RentOdListVO;




@WebServlet("/RentOdListBackEndcontroller")
public class RentOdListBackEndcontroller extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);
			
			List<RentOdDetailVO> rodlist = new ArrayList<RentOdDetailVO>();

			try {
				String rent_odnum = req.getParameter("rent_odnum");
				
				RentOdDetailService rentoddetailSvc = new RentOdDetailService();
				List<RentOdDetailVO> rentoddetaillist = rentoddetailSvc.getDetail(rent_odnum);
				
				for(RentOdDetailVO rentoddetailvo:rentoddetaillist) {
					rodlist.add(rentoddetailvo);
				}
				req.setAttribute("rodlist", rodlist); 
				String url = "/back-end/rentorderlistdetail/orderlistdetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/order/order.jsp");
				failureView.forward(req, res);
			}
		}

		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp 或  /dept/listEmps_ByDeptno.jsp 的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】		
			
			try {
				/***************************1.接收請求參數****************************************/
				String rent_odnum = req.getParameter("rent_odnum");
				
				/***************************2.開始查詢資料****************************************/
				RentOdListService rolSvc = new RentOdListService();
				RentOdListVO rolVO = rolSvc.getOneRentOdList(rent_odnum);
				RentOdDetailService rodSvc = new RentOdDetailService();
				List<RentOdDetailVO> rodlist = rodSvc.getDetail(rent_odnum)	;			
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("rolVO", rolVO); // 資料庫取出的empVO物件,存入req
				req.setAttribute("rodlist", rodlist);
				String url = "/back-end/rentodlist/updateorder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料取出時失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				
				String rent_odnum = req.getParameter("rent_odnum");

				String member_id = req.getParameter("member_id");		

				String rent_name = req.getParameter("rent_name").trim();

				if (rent_name == null || rent_name.trim().length() == 0) {
					errorMsgs.add("姓名請勿空白");
				}
				
				String rent_phone = req.getParameter("rent_phone").trim();
				if (rent_phone == null || rent_phone.trim().length() == 0) {
					errorMsgs.add("電話請勿空白");
				}
				
				java.sql.Date rsved_rent_date = java.sql.Date.valueOf(req.getParameter("rsved_rent_date").trim());
				
				java.sql.Date real_rent_date = null;

				java.sql.Date ex_return_date = java.sql.Date.valueOf(req.getParameter("ex_return_date").trim());
				
				java.sql.Date real_return_date = null;
				
				Integer od_status = new Integer(req.getParameter("od_status").trim());
				
				Integer od_total_price = new Integer(req.getParameter("od_total_price").trim()); 
				String rent_payment = req.getParameter("rent_payment");
				
				
				try {
					if(od_status == 0) {
					
						List<DailyTotalVO> list = new ArrayList();
						RentOdDetailService rodSvc = new RentOdDetailService();
						List<RentOdDetailVO> rodVOlist =  rodSvc.getDetail(rent_odnum);
						
						List <String> quantitystr =new ArrayList<String>();
						List <Integer> quannum =new ArrayList<Integer>();
						for (int i = 0; i < rodVOlist.size(); i++) {
							quantitystr.add(req.getParameter("quantity"+i));
						}
						for (int i = 0; i < rodVOlist.size(); i++) {
							String quanStr = quantitystr.get(i);
							int qnum =Integer.parseInt(quanStr);
							quannum.add(qnum);
						}
						for (int i = 0; i < rodVOlist.size(); i++) {
							rodVOlist.get(i).setQuantity(quannum.get(i));
						}
						
						List<RentOdDetailVO> testList = new ArrayList<RentOdDetailVO>();
						
						for (int i = 0; i < rodVOlist.size(); i++) {
							RentOdDetailVO detail = new RentOdDetailVO();
							RentOdDetailVO order = rodVOlist.get(i);
							Integer quantity = order.getQuantity();
							String eq_num = order.getEq_num();
							String rent_odnum1 = order.getRent_odnum();
							detail.setRent_odnum(rent_odnum1);
							detail.setEq_num(eq_num);
							detail.setQuantity(quantity);
							testList.add(detail);
						}
						
						Integer total = 0;
						Integer price = 0;
						EquipmentService equSvc = new  EquipmentService();
						List<EquipmentVO> equlist = equSvc.getAll();
						for (int i = 0; i < rodVOlist.size(); i++) {
							RentOdDetailVO order = rodVOlist.get(i);
							Integer quantity = order.getQuantity();
							String eq_num = order.getEq_num();
							for(int j= 0 ; j<equlist.size();j++) { 
							if(eq_num.equals(equlist.get(j).getEq_num())) {
							 price = equlist.get(j).getEq_price();
							 total += (price * quantity);
							}	
						}
						}
						
						for (int i = 0; i < rodVOlist.size(); i++) {
							RentOdDetailService oldorderSvc = new RentOdDetailService();
							List<RentOdDetailVO> oldorderlist = oldorderSvc.getDetail(rent_odnum);
							System.out.println(oldorderlist.get(i).getQuantity());
							RentOdDetailVO order = rodVOlist.get(i);
							Integer quantity = order.getQuantity();
							System.out.println(quantity);
							String eq_num = order.getEq_num();
							EquipmentVO equVO = equSvc.getOneEquipment(eq_num);
							String type_eq_num = equVO.getType_eq_num();

							DailyTotalService sqlDate = new DailyTotalService();
							List<DailyTotalVO> listsqlDate = sqlDate.getsqlDate(type_eq_num, rsved_rent_date, ex_return_date);

							
							for (DailyTotalVO sqlDateVO : listsqlDate) {

								if (sqlDateVO.getStart_qty() -oldorderlist.get(i).getQuantity() + quantity > sqlDateVO.getDaily_eq_qty()) {
									throw new Exception();
								}else {
									Integer start_qty = sqlDateVO.getStart_qty()-oldorderlist.get(i).getQuantity()+quantity;
									String dailyeq_num = sqlDateVO.getDailyeq_num();
									java.sql.Date eq_date = sqlDateVO.getEq_date();
									Integer daily_eq_qty = sqlDateVO.getDaily_eq_qty();
									sqlDateVO.setDailyeq_num(dailyeq_num);
									sqlDateVO.setType_eq_num(type_eq_num);
									sqlDateVO.setEq_date(eq_date);
									sqlDateVO.setDaily_eq_qty(daily_eq_qty);
									sqlDateVO.setStart_qty(start_qty);
									sqlDate.updateDailyTotal(dailyeq_num, type_eq_num, eq_date, daily_eq_qty, start_qty);
//									list.add(sqlDateVO);
								}
							}
							}

						RentOdListVO rentodlistVO = new RentOdListVO();
						rentodlistVO.setRent_odnum(rent_odnum);
						rentodlistVO.setMember_id(member_id);
						rentodlistVO.setRent_payment(rent_payment);
						rentodlistVO.setRsved_rent_date(rsved_rent_date);
						rentodlistVO.setEx_return_date(ex_return_date);
						rentodlistVO.setOd_status(od_status);
						rentodlistVO.setOd_total_price(total);
						rentodlistVO.setRent_name(rent_name);
						rentodlistVO.setRent_phone(rent_phone);

						
						RentOdListDAO rentodlistDAO = new RentOdListDAO();
						rentodlistDAO.updateWithOrdAndDaily(rentodlistVO , testList ,list);
						

							
							RequestDispatcher failureView = req
									.getRequestDispatcher("/back-end/rentodlist/order.jsp");
							failureView.forward(req, res);
						
					}
					if(od_status == 1) {
						RentOdDetailService rodSvc = new RentOdDetailService();
						List<RentOdDetailVO> rodVOlist =  rodSvc.getDetail(rent_odnum);
						List<DailyTotalVO> list = new ArrayList();
						for (int i = 0; i < rodVOlist.size(); i++) {
							RentOdDetailVO order = rodVOlist.get(i);
							Integer quantity = order.getQuantity();
							String eq_num = order.getEq_num();
							EquipmentService equSvc = new EquipmentService();
							EquipmentVO equVO = equSvc.getOneEquipment(eq_num);
							String type_eq_num = equVO.getType_eq_num();

							DailyTotalService sqlDate = new DailyTotalService();
							List<DailyTotalVO> listsqlDate = sqlDate.getsqlDate(type_eq_num, rsved_rent_date, ex_return_date);


								for (DailyTotalVO sqlDateVO : listsqlDate) {

										Integer start_qty = sqlDateVO.getStart_qty()-quantity;
										String dailyeq_num = sqlDateVO.getDailyeq_num();
										java.sql.Date eq_date = sqlDateVO.getEq_date();
										Integer daily_eq_qty = sqlDateVO.getDaily_eq_qty();
										sqlDateVO.setDailyeq_num(dailyeq_num);
										sqlDateVO.setType_eq_num(type_eq_num);
										sqlDateVO.setEq_date(eq_date);
										sqlDateVO.setDaily_eq_qty(daily_eq_qty);
										sqlDateVO.setStart_qty(start_qty);
										list.add(sqlDateVO);
									}
								}
							
						RentOdListVO rentodlistVO = new RentOdListVO();
						rentodlistVO.setRent_odnum(rent_odnum);
						rentodlistVO.setMember_id(member_id);
						rentodlistVO.setRent_payment(rent_payment);
						rentodlistVO.setRsved_rent_date(rsved_rent_date);
						rentodlistVO.setEx_return_date(ex_return_date);
						rentodlistVO.setOd_status(od_status);
						rentodlistVO.setOd_total_price(od_total_price);
						rentodlistVO.setRent_name(rent_name);
						rentodlistVO.setRent_phone(rent_phone);

						RentOdListService rolSvc = new RentOdListService();
						rolSvc.updateRentOdList(rent_odnum, member_id, rent_payment, rsved_rent_date, real_rent_date, ex_return_date, real_return_date, od_status, od_total_price, rent_name, rent_phone);
						DailyTotalService sqlDate = new DailyTotalService();
						
						

							
							RequestDispatcher failureView = req
									.getRequestDispatcher("/back-end/rentodlist/order.jsp");
							failureView.forward(req, res);
						

						
					}
					if(od_status == 2) {
						
						List<DailyTotalVO> list = new ArrayList();
						RentOdDetailService rodSvc = new RentOdDetailService();
						List<RentOdDetailVO> rodVOlist =  rodSvc.getDetail(rent_odnum);
						
						List <String> quantitystr =new ArrayList<String>();
						List <Integer> quannum =new ArrayList<Integer>();
						for (int i = 0; i < rodVOlist.size(); i++) {
							quantitystr.add(req.getParameter("quantity"+i));
						}
						for (int i = 0; i < rodVOlist.size(); i++) {
							String quanStr = quantitystr.get(i);
							int qnum =Integer.parseInt(quanStr);
							quannum.add(qnum);
						}
						for (int i = 0; i < rodVOlist.size(); i++) {
							rodVOlist.get(i).setQuantity(quannum.get(i));
						}
						
						List<RentOdDetailVO> testList = new ArrayList<RentOdDetailVO>();
						
						for (int i = 0; i < rodVOlist.size(); i++) {
							RentOdDetailVO detail = new RentOdDetailVO();
							RentOdDetailVO order = rodVOlist.get(i);
							Integer quantity = order.getQuantity();
							String eq_num = order.getEq_num();
							String rent_odnum1 = order.getRent_odnum();
							detail.setRent_odnum(rent_odnum1);
							detail.setEq_num(eq_num);
							detail.setQuantity(quantity);
							testList.add(detail);
						}
						
						Integer total = 0;
						Integer price = 0;
						EquipmentService equSvc = new  EquipmentService();
						List<EquipmentVO> equlist = equSvc.getAll();
						for (int i = 0; i < rodVOlist.size(); i++) {
							RentOdDetailVO order = rodVOlist.get(i);
							Integer quantity = order.getQuantity();
							String eq_num = order.getEq_num();
							for(int j= 0 ; j<equlist.size();j++) { 
							if(eq_num.equals(equlist.get(j).getEq_num())) {
							 price = equlist.get(j).getEq_price();
							 total += (price * quantity);
							}	
						}
						}

						for (int i = 0; i < rodVOlist.size(); i++) {
							RentOdDetailService oldorderSvc = new RentOdDetailService();
							List<RentOdDetailVO> oldorderlist = oldorderSvc.getDetail(rent_odnum);
							RentOdDetailVO order = rodVOlist.get(i);
							Integer quantity = order.getQuantity();
							String eq_num = order.getEq_num();
							EquipmentVO equVO = equSvc.getOneEquipment(eq_num);
							String type_eq_num = equVO.getType_eq_num();

							DailyTotalService sqlDate = new DailyTotalService();
							List<DailyTotalVO> listsqlDate = sqlDate.getsqlDate(type_eq_num, rsved_rent_date, ex_return_date);

							
								for (DailyTotalVO sqlDateVO : listsqlDate) {

									if (sqlDateVO.getStart_qty() -oldorderlist.get(i).getQuantity() + quantity > sqlDateVO.getDaily_eq_qty()) {
										throw new Exception();
									}else {
										Integer start_qty = sqlDateVO.getStart_qty()-oldorderlist.get(i).getQuantity()+quantity;
										String dailyeq_num = sqlDateVO.getDailyeq_num();
										java.sql.Date eq_date = sqlDateVO.getEq_date();
										Integer daily_eq_qty = sqlDateVO.getDaily_eq_qty();
										sqlDateVO.setDailyeq_num(dailyeq_num);
										sqlDateVO.setType_eq_num(type_eq_num);
										sqlDateVO.setEq_date(eq_date);
										sqlDateVO.setDaily_eq_qty(daily_eq_qty);
										sqlDateVO.setStart_qty(start_qty);
										list.add(sqlDateVO);
									}
								}
							}

						RentOdListVO rentodlistVO = new RentOdListVO();
						rentodlistVO.setRent_odnum(rent_odnum);
						rentodlistVO.setMember_id(member_id);
						rentodlistVO.setRent_payment(rent_payment);
						rentodlistVO.setRsved_rent_date(rsved_rent_date);
						rentodlistVO.setReal_rent_date(java.sql.Date.valueOf(getDateTime()));
						rentodlistVO.setEx_return_date(ex_return_date);
						rentodlistVO.setOd_status(od_status);
						rentodlistVO.setOd_total_price(total);
						rentodlistVO.setRent_name(rent_name);
						rentodlistVO.setRent_phone(rent_phone);
						
						
						RentOdListDAO rentodlistDAO = new RentOdListDAO();
						rentodlistDAO.updateWithOrdAndDaily(rentodlistVO , testList ,list);
						
							for(RentOdDetailVO abc:rodVOlist) {
								EquipmentVO equvoforrent = equSvc.getOneEquipment(abc.getEq_num());
								List<EquipmentVO> equequlistforrent =  equSvc.getAllTypeEqNum(equvoforrent.getType_eq_num());
								int count1 = abc.getQuantity();
								int count = 0;
								System.out.println(count1);
								for(int j=0; j<equequlistforrent.size();j++) {
									EquipmentVO equvo1forrent = equequlistforrent.get(j);

								if(equvo1forrent.getEq_status()==0) {
									String eq_num = equvo1forrent.getEq_num();
									String eq_name = equvo1forrent.getEq_name();
									String eq_type =equvo1forrent.getEq_type();
									String eq_size = equvo1forrent.getEq_size();
									String eq_brand = equvo1forrent.getEq_brand();
									Integer eq_price =equvo1forrent.getEq_price();
									Integer eq_status = 1;
									byte[] eq_pic = equvo1forrent.getEq_pic();
									String eq_detail = equvo1forrent.getEq_detail();
									String type_eq_num = equvo1forrent.getType_eq_num();
									equSvc.updateEquipment(eq_num, eq_name, eq_type, eq_size, eq_brand, eq_price, eq_status, eq_pic, eq_detail, type_eq_num);
									count++;
									if(count == count1) { 
										j=equequlistforrent.size();}
								
								}
								}
							}
							
							RequestDispatcher failureView = req
									.getRequestDispatcher("/back-end/rentodlist/order.jsp");
							failureView.forward(req, res);
						
						
						
					}
					if(od_status == 3) {
						List<DailyTotalVO> list = new ArrayList();
						RentOdDetailService rodSvc = new RentOdDetailService();
						List<RentOdDetailVO> rodVOlist =  rodSvc.getDetail(rent_odnum);					
						List<RentOdDetailVO> testList = new ArrayList<RentOdDetailVO>();
						EquipmentService equSvc = new  EquipmentService();
						List<EquipmentVO> equlist = equSvc.getAll();


						for (int i = 0; i < rodVOlist.size(); i++) {
							RentOdDetailVO order = rodVOlist.get(i);
							Integer quantity = order.getQuantity();
							String eq_num = order.getEq_num();
							EquipmentVO equVO = equSvc.getOneEquipment(eq_num);
							String type_eq_num = equVO.getType_eq_num();

							DailyTotalService sqlDate = new DailyTotalService();
							List<DailyTotalVO> listsqlDate = sqlDate.getBigDate(type_eq_num, ex_return_date);
 
							
								for (DailyTotalVO sqlDateVO : listsqlDate) {
		
										Integer start_qty = sqlDateVO.getStart_qty();
										String dailyeq_num = sqlDateVO.getDailyeq_num();
										java.sql.Date eq_date = sqlDateVO.getEq_date();
										Integer daily_eq_qty = sqlDateVO.getDaily_eq_qty()-quantity;
										sqlDateVO.setDailyeq_num(dailyeq_num);
										sqlDateVO.setType_eq_num(type_eq_num);
										sqlDateVO.setEq_date(eq_date);
										sqlDateVO.setDaily_eq_qty(daily_eq_qty);
										sqlDateVO.setStart_qty(start_qty);
										list.add(sqlDateVO);
										if(daily_eq_qty<start_qty) {
											//errormessage.add通知客戶裝備未歸還 通知管理員裝備不足
										}
									}
								}
							

						RentOdListVO rentodlistVO = new RentOdListVO();
						rentodlistVO.setRent_odnum(rent_odnum);
						rentodlistVO.setMember_id(member_id);
						rentodlistVO.setRent_payment(rent_payment);
						rentodlistVO.setRsved_rent_date(rsved_rent_date);
						rentodlistVO.setReal_rent_date(java.sql.Date.valueOf(req.getParameter("real_rent_date").trim()));
						rentodlistVO.setEx_return_date(ex_return_date);
						rentodlistVO.setOd_status(od_status);
						rentodlistVO.setOd_total_price(od_total_price);
						rentodlistVO.setRent_name(rent_name);
						rentodlistVO.setRent_phone(rent_phone);
						
						
						RentOdListDAO rentodlistDAO = new RentOdListDAO();
						rentodlistDAO.updateWithOrdAndDaily(rentodlistVO , testList ,list);
						
							for(RentOdDetailVO abc:rodVOlist) {
								EquipmentVO equvoforrent = equSvc.getOneEquipment(abc.getEq_num());
								List<EquipmentVO> equequlistforrent =  equSvc.getAllTypeEqNum(equvoforrent.getType_eq_num());
								int count1 = abc.getQuantity();
								int count = 0;
								for(int j=0; j<equequlistforrent.size();j++) {
									EquipmentVO equvo1forrent = equequlistforrent.get(j);

								if(equvo1forrent.getEq_status()==1) {
									String eq_num = equvo1forrent.getEq_num();
									String eq_name = equvo1forrent.getEq_name();
									String eq_type =equvo1forrent.getEq_type();
									String eq_size = equvo1forrent.getEq_size();
									String eq_brand = equvo1forrent.getEq_brand();
									Integer eq_price =equvo1forrent.getEq_price();
									Integer eq_status = 2;
									byte[] eq_pic = equvo1forrent.getEq_pic();
									String eq_detail = equvo1forrent.getEq_detail();
									String type_eq_num = equvo1forrent.getType_eq_num();
									equSvc.updateEquipment(eq_num, eq_name, eq_type, eq_size, eq_brand, eq_price, eq_status, eq_pic, eq_detail, type_eq_num);
									count++;
									if(count == count1) { 
										j=equequlistforrent.size();}
								
								}
								}
							}
							
							RequestDispatcher failureView = req
									.getRequestDispatcher("/back-end/rentodlist/order.jsp");
							failureView.forward(req, res);
					}
					
					if(od_status == 4) {
						List<DailyTotalVO> list = new ArrayList();
						RentOdDetailService rodSvc = new RentOdDetailService();
						List<RentOdDetailVO> rodVOlist =  rodSvc.getDetail(rent_odnum);					
						List<RentOdDetailVO> testList = new ArrayList<RentOdDetailVO>();
						EquipmentService equSvc = new  EquipmentService();
						List<EquipmentVO> equlist = equSvc.getAll();
						RentOdListService rolSvc = new RentOdListService();
						RentOdListVO rolVO = rolSvc.getOneRentOdList(rent_odnum);
						if(rolVO.getOd_status()==3) {
						for (int i = 0; i < rodVOlist.size(); i++) {
							RentOdDetailVO order = rodVOlist.get(i);
							Integer quantity = order.getQuantity();
							String eq_num = order.getEq_num();
							EquipmentVO equVO = equSvc.getOneEquipment(eq_num);
							String type_eq_num = equVO.getType_eq_num();

							DailyTotalService sqlDate = new DailyTotalService();
							List<DailyTotalVO> listsqlDate = sqlDate.getBigDate(type_eq_num, ex_return_date);

							
								for (DailyTotalVO sqlDateVO : listsqlDate) {

										Integer start_qty = sqlDateVO.getStart_qty();
										String dailyeq_num = sqlDateVO.getDailyeq_num();
										java.sql.Date eq_date = sqlDateVO.getEq_date();
										Integer daily_eq_qty = sqlDateVO.getDaily_eq_qty()+quantity;
										sqlDateVO.setDailyeq_num(dailyeq_num);
										sqlDateVO.setType_eq_num(type_eq_num);
										sqlDateVO.setEq_date(eq_date);
										sqlDateVO.setDaily_eq_qty(daily_eq_qty);
										sqlDateVO.setStart_qty(start_qty);
										list.add(sqlDateVO);
										if(daily_eq_qty<start_qty) {
											//errormessage.add通知客戶裝備未歸還 通知管理員裝備不足
										}
									}
								}
							

						RentOdListVO rentodlistVO = new RentOdListVO();
						rentodlistVO.setRent_odnum(rent_odnum);
						rentodlistVO.setMember_id(member_id);
						rentodlistVO.setRent_payment(rent_payment);
						rentodlistVO.setRsved_rent_date(rsved_rent_date);
						rentodlistVO.setReal_rent_date(java.sql.Date.valueOf(req.getParameter("real_rent_date").trim()));
						rentodlistVO.setEx_return_date(ex_return_date);
						rentodlistVO.setOd_status(4);
						rentodlistVO.setOd_total_price(od_total_price);
						rentodlistVO.setRent_name(rent_name);
						rentodlistVO.setRent_phone(rent_phone);
						
						
						RentOdListDAO rentodlistDAO = new RentOdListDAO();
						rentodlistDAO.updateWithOrdAndDaily(rentodlistVO , testList ,list);
						
							for(RentOdDetailVO abc:rodVOlist) {
								EquipmentVO equvoforrent = equSvc.getOneEquipment(abc.getEq_num());
								List<EquipmentVO> equequlistforrent =  equSvc.getAllTypeEqNum(equvoforrent.getType_eq_num());
								int count1 = abc.getQuantity();
								int count = 0;
								for(int j=0; j<equequlistforrent.size();j++) {
									EquipmentVO equvo1forrent = equequlistforrent.get(j);

								if(equvo1forrent.getEq_status()==3) {
									String eq_num = equvo1forrent.getEq_num();
									String eq_name = equvo1forrent.getEq_name();
									String eq_type =equvo1forrent.getEq_type();
									String eq_size = equvo1forrent.getEq_size();
									String eq_brand = equvo1forrent.getEq_brand();
									Integer eq_price =equvo1forrent.getEq_price();
									Integer eq_status = 4;
									byte[] eq_pic = equvo1forrent.getEq_pic();
									String eq_detail = equvo1forrent.getEq_detail();
									String type_eq_num = equvo1forrent.getType_eq_num();
									equSvc.updateEquipment(eq_num, eq_name, eq_type, eq_size, eq_brand, eq_price, eq_status, eq_pic, eq_detail, type_eq_num);
									count++;
									if(count == count1) { 
										j=equequlistforrent.size();}
								
								}
								}
							}
							
							RequestDispatcher failureView = req
									.getRequestDispatcher("/back-end/rentodlist/order.jsp");
							failureView.forward(req, res);
					}else{
						for (int i = 0; i < rodVOlist.size(); i++) {
							RentOdDetailVO order = rodVOlist.get(i);
							Integer quantity = order.getQuantity();
							String eq_num = order.getEq_num();
							EquipmentVO equVO = equSvc.getOneEquipment(eq_num);
							String type_eq_num = equVO.getType_eq_num();

							DailyTotalService sqlDate = new DailyTotalService();
							List<DailyTotalVO> listsqlDate = sqlDate.getBigDate(type_eq_num, ex_return_date);

							
								for (DailyTotalVO sqlDateVO : listsqlDate) {

										Integer start_qty = sqlDateVO.getStart_qty();
										String dailyeq_num = sqlDateVO.getDailyeq_num();
										java.sql.Date eq_date = sqlDateVO.getEq_date();
										Integer daily_eq_qty = sqlDateVO.getDaily_eq_qty();
										sqlDateVO.setDailyeq_num(dailyeq_num);
										sqlDateVO.setType_eq_num(type_eq_num);
										sqlDateVO.setEq_date(eq_date);
										sqlDateVO.setDaily_eq_qty(daily_eq_qty);
										sqlDateVO.setStart_qty(start_qty);
										list.add(sqlDateVO);
										if(daily_eq_qty<start_qty) {
											//errormessage.add通知客戶裝備未歸還 通知管理員裝備不足
										}
									}
								}
							

						RentOdListVO rentodlistVO = new RentOdListVO();
						rentodlistVO.setRent_odnum(rent_odnum);
						rentodlistVO.setMember_id(member_id);
						rentodlistVO.setRent_payment(rent_payment);
						rentodlistVO.setRsved_rent_date(rsved_rent_date);
						rentodlistVO.setReal_rent_date(java.sql.Date.valueOf(req.getParameter("real_rent_date").trim()));
						rentodlistVO.setEx_return_date(ex_return_date);
						rentodlistVO.setOd_status(4);
						rentodlistVO.setOd_total_price(od_total_price);
						rentodlistVO.setRent_name(rent_name);
						rentodlistVO.setRent_phone(rent_phone);
						
						
						RentOdListDAO rentodlistDAO = new RentOdListDAO();
						rentodlistDAO.updateWithOrdAndDaily(rentodlistVO , testList ,list);
						
							for(RentOdDetailVO abc:rodVOlist) {
								EquipmentVO equvoforrent = equSvc.getOneEquipment(abc.getEq_num());
								List<EquipmentVO> equequlistforrent =  equSvc.getAllTypeEqNum(equvoforrent.getType_eq_num());
								int count1 = abc.getQuantity();
								int count = 0;
								for(int j=0; j<equequlistforrent.size();j++) {
									EquipmentVO equvo1forrent = equequlistforrent.get(j);

								if(equvo1forrent.getEq_status()==1) {
									String eq_num = equvo1forrent.getEq_num();
									String eq_name = equvo1forrent.getEq_name();
									String eq_type =equvo1forrent.getEq_type();
									String eq_size = equvo1forrent.getEq_size();
									String eq_brand = equvo1forrent.getEq_brand();
									Integer eq_price =equvo1forrent.getEq_price();
									Integer eq_status = 4;
									byte[] eq_pic = equvo1forrent.getEq_pic();
									String eq_detail = equvo1forrent.getEq_detail();
									String type_eq_num = equvo1forrent.getType_eq_num();
									equSvc.updateEquipment(eq_num, eq_name, eq_type, eq_size, eq_brand, eq_price, eq_status, eq_pic, eq_detail, type_eq_num);
									count++;
									if(count == count1) { 
										j=equequlistforrent.size();}
								
								}
								}
							}
							
							RequestDispatcher failureView = req
									.getRequestDispatcher("/back-end/rentodlist/order.jsp");
							failureView.forward(req, res);
					}
				}
				} catch(Exception e) {
					errorMsgs.add("修改狀態失敗");
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/rentodlist/updateorder.jsp");
				failureView.forward(req, res);
				return;
				}

				
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/rentodlist/updateorder.jsp");
				failureView.forward(req, res);
			}
		}

		}
	
	
	
	
	
	
	
	
	public String getDateTime(){
		  SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
		  Date date = new Date();
		  String strDate = sdFormat.format(date);
		  System.out.println(strDate);
		  return strDate;
		  }

	
	
	
	
	
	
	
	
	}















