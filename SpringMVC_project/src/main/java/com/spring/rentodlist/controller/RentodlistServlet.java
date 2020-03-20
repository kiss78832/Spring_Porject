package com.spring.rentodlist.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.spring.dailytypetotal.model.DailyTotalService;
import com.spring.dailytypetotal.model.DailyTotalVO;
import com.spring.equipment.model.EquipmentVO;
import com.spring.rentoddetail.model.RentOdDetailVO;
import com.spring.rentodlist.model.RentOdListDAO;
import com.spring.rentodlist.model.RentOdListVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class RentodlistServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		HttpSession session = req.getSession();
		List<EquipmentVO> buylist = (Vector<EquipmentVO>) session.getAttribute("shoppingcart");
		RentOdListVO rentodlistvo = (RentOdListVO) session.getAttribute("rentodlistvo");

        if ("CHECKOUT".equals(action)) { 
        	List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
        	String member_id = req.getParameter("member_id");
        	
        	String rent_payment = req.getParameter("rent_payment");
        	
        	java.sql.Date rsved_rent_date =rentodlistvo.getRsved_rent_date();
        	
			java.sql.Date ex_return_date =rentodlistvo.getEx_return_date();
			
			
			List<DailyTotalVO> list = new ArrayList();
			try {
				DailyTotalService updateDate = new DailyTotalService();

				
				Boolean xx = true;
				for (int i = 0; i < buylist.size(); i++) {
					EquipmentVO order = buylist.get(i);
					Integer quantity = order.getQuantity();
					String type_eq_num = order.getType_eq_num();

					DailyTotalService sqlDate = new DailyTotalService();
					List<DailyTotalVO> listsqlDate = sqlDate.getsqlDate(type_eq_num, rsved_rent_date, ex_return_date);


						for (DailyTotalVO sqlDateVO : listsqlDate) {

							if (sqlDateVO.getStart_qty() + quantity > sqlDateVO.getDaily_eq_qty()) {
								xx = false;
								throw new Exception();
							}else {
								Integer start_qty = sqlDateVO.getStart_qty()+quantity;
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
					}	//if (xx) {
//						for (int i = 0; i < buylist.size(); i++) {
//							EquipmentVO order = buylist.get(i);
//							Integer quantity = order.getQuantity();
//							String type_eq_num = order.getType_eq_num();
//							for (DailyTotalVO list1 : list) {
//								if(list1.getType_eq_num()==type_eq_num) {
//									Integer start_qty = list1.getStart_qty();
//									String dailyeq_num = list1.getDailyeq_num();
//									java.sql.Date eq_date = list1.getEq_date();
//									Integer daily_eq_qty = list1.getDaily_eq_qty();
//
//									
//									updateDate.updateDailyTotal(dailyeq_num, type_eq_num, eq_date, daily_eq_qty, start_qty);
//								}
//									
//								
//							}
//						}
//					}
			
			
				}catch(Exception e) {errorMsgs.add("請對應正確的日期及數量");
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/equipment/shoppingCart.jsp");
					failureView.forward(req, res);
					return;
					}

			
			Integer od_status =  new Integer(req.getParameter("od_status").trim());	
			
			Integer od_total_price =  Integer.valueOf(req.getParameter("od_total_price").trim());	
			
			String rent_name = req.getParameter("rent_name").trim();
			String rent_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (rent_name == null || rent_name.trim().length() == 0) {
				errorMsgs.add("姓名: 請勿空白");
			} else if(!rent_name.trim().matches(rent_nameReg)) { 
				errorMsgs.add("姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
            }
			
			String rent_phone = req.getParameter("rent_phone").trim();
			String rent_phoneReg = "[0-9]{10}";
			if (rent_phone == null || rent_phone.trim().length() == 0) {
				errorMsgs.add("電話: 請勿空白");
			} else if(!rent_phone.trim().matches(rent_phoneReg)) { 
				errorMsgs.add("手機: 請輸入0-9及範圍在10以內");
            }
			
			String card_number = req.getParameter("cardNumber").trim();
			String card_numberReg = "^5[1-5]\\d{14}$";
			if (card_number == null || card_number.trim().length() == 0) {
				errorMsgs.add("卡號: 請勿空白");
			} else if(!card_number.trim().matches(card_numberReg)) { 
				errorMsgs.add("請輸入正確的信用卡格式:你的信用卡號碼不符合「xxxxxxxxxxxxxxxx」的格式");  //5111005111051128
            }
			
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/rentodlist/orderform.jsp");
				failureView.forward(req, res);
				return;
			}
				
				RentOdListVO rentodlistVO = new RentOdListVO();
				rentodlistVO.setMember_id(member_id);
				rentodlistVO.setRent_payment(rent_payment);
				rentodlistVO.setRsved_rent_date(rsved_rent_date);
				rentodlistVO.setEx_return_date(ex_return_date);
				rentodlistVO.setOd_status(od_status);
				rentodlistVO.setOd_total_price(od_total_price);
				rentodlistVO.setRent_name(rent_name);
				rentodlistVO.setRent_phone(rent_phone);

				RentOdListDAO rentodlistDAO = new RentOdListDAO();
				List<RentOdDetailVO> testList = new ArrayList<RentOdDetailVO>();
				
				for (int i = 0; i < buylist.size(); i++) {
					RentOdDetailVO detail = new RentOdDetailVO();
					EquipmentVO order = buylist.get(i);
					Integer quantity = order.getQuantity();
					String eq_num = order.getEq_num();

					detail.setEq_num(eq_num);
					detail.setQuantity(quantity);
					testList.add(detail);
				}	
					String rent_odnum = rentodlistDAO.insertWithOrdAndDaily(rentodlistVO , testList ,list);
					req.setAttribute("rent_odnum", rent_odnum);
					String url = "/front-end/rentodlistdetail/order.jsp";
					RequestDispatcher rd = req.getRequestDispatcher(url);
					rd.forward(req, res);
					session.removeAttribute("shoppingcart");
					session.removeAttribute("amount");
}	 catch (Exception e) {
	errorMsgs.add("修改資料失敗:"+e.getMessage());
	RequestDispatcher failureView = req
			.getRequestDispatcher("/front-end/rentodlist/orderform.jsp");
	failureView.forward(req, res);
}
        }
	}
}
	

//		if ("CHECKOUT".equals(action)) {
//			String member_id = req.getParameter("member_id");
//			String rent_payment = req.getParameter("rent_payment");
//			java.sql.Date rsved_rent_date = java.sql.Date.valueOf(req.getParameter("rsved_rent_date").trim());
//			java.sql.Date ex_return_date = java.sql.Date.valueOf(req.getParameter("ex_return_date").trim());
//			Integer od_status = new Integer(req.getParameter("od_status").trim());
//			Integer od_total_price = new Integer(req.getParameter("od_total_price").trim());
//			String rent_name = req.getParameter("rent_name").trim();
//			String rent_phone = req.getParameter("rent_phone").trim();
//
//			
//			
//			
//			
//			
//			
//			
//			
//			DailyTotalService parameterDate = new DailyTotalService();
//			List<String> parameterDatelist = parameterDate.getDays(rsved_rent_date.toString(),
//					ex_return_date.toString());
//
//			for (int i = 0; i < buylist.size(); i++) {
//				EquipmentVO order = buylist.get(i);
//				Integer quantity = order.getQuantity();
//				String type_eq_num = order.getType_eq_num();
//
//				DailyTotalService sqlDate = new DailyTotalService();
//				List<DailyTotalVO> listsqlDate = sqlDate.getsqlDate(type_eq_num, rsved_rent_date, ex_return_date);
//				
//				for (String parameterDatelist1 : parameterDatelist) {
//
//					for (DailyTotalVO listsqlDate1 : listsqlDate) {
//
//						if (listsqlDate1.getEq_date().toString().equals(parameterDatelist1)
//								&& listsqlDate1.getStart_qty() + quantity <= listsqlDate1.getDaily_eq_qty()) {
//							Integer start_qty = listsqlDate1.getStart_qty()+ quantity ;
//							String dailyeq_num = listsqlDate1.getDailyeq_num();
//							java.sql.Date eq_date = listsqlDate1.getEq_date();
//							Integer daily_eq_qty = listsqlDate1.getDaily_eq_qty();
//							listsqlDate1.setDailyeq_num(dailyeq_num);
//							listsqlDate1.setType_eq_num(type_eq_num);
//							listsqlDate1.setEq_date(eq_date);
//							listsqlDate1.setDaily_eq_qty(daily_eq_qty);
//							listsqlDate1.setStart_qty(start_qty);
//
//							listsqlDate1 = sqlDate.updateDailyTotal(dailyeq_num, type_eq_num, eq_date, daily_eq_qty, start_qty);
//							
//						} else {
//							System.out.println("選購太多商品");
//							
//						}	
//					}
//				}
//			}
//
//		}

		
		
		
		
		
		
		
		
		
		
		
		
		
		//-------------------------------------------------------日期------------------------------------------------------------------------------------------	
		
//		
//		
//		
//		if ("CHECKOUT".equals(action)) {
//			String member_id = req.getParameter("member_id");
//			String rent_payment = req.getParameter("rent_payment");
//			java.sql.Date rsved_rent_date = java.sql.Date.valueOf(req.getParameter("rsved_rent_date").trim());
//			java.sql.Date ex_return_date = java.sql.Date.valueOf(req.getParameter("ex_return_date").trim());
//			Integer od_status = new Integer(req.getParameter("od_status").trim());
//			Integer od_total_price = new Integer(req.getParameter("od_total_price").trim());
//			String rent_name = req.getParameter("rent_name").trim();
//			String rent_phone = req.getParameter("rent_phone").trim();
//
//			DailyTotalService parameterDate = new DailyTotalService();
//			List<String> parameterDatelist = parameterDate.getDays(rsved_rent_date.toString(),
//					ex_return_date.toString());
//			DailyTotalService updateDate = new DailyTotalService();
//			List<DailyTotalVO> list = new ArrayList();
//			
//			Boolean xx = true;
//			for (int i = 0; i < buylist.size(); i++) {
//				EquipmentVO order = buylist.get(i);
//				Integer quantity = order.getQuantity();
//				String type_eq_num = order.getType_eq_num();
//
//				DailyTotalService sqlDate = new DailyTotalService();
//				List<DailyTotalVO> listsqlDate = sqlDate.getsqlDate(type_eq_num, rsved_rent_date, ex_return_date);
//
//				for (String parameterDatelist1 : parameterDatelist) {
//
//					for (DailyTotalVO listsqlDate1 : listsqlDate) {
//
//						if (listsqlDate1.getEq_date().toString().equals(parameterDatelist1)) {
//							Integer start_qty = listsqlDate1.getStart_qty();
//							String dailyeq_num = listsqlDate1.getDailyeq_num();
//							java.sql.Date eq_date = listsqlDate1.getEq_date();
//							Integer daily_eq_qty = listsqlDate1.getDaily_eq_qty();
//							listsqlDate1.setDailyeq_num(dailyeq_num);
//							listsqlDate1.setType_eq_num(type_eq_num);
//							listsqlDate1.setEq_date(eq_date);
//							listsqlDate1.setDaily_eq_qty(daily_eq_qty);
//							listsqlDate1.setStart_qty(start_qty);
//							list.add(listsqlDate1);
//
//						}
//					}
//				}
//			}
//			for (int i = 0; i < buylist.size(); i++) {
//				EquipmentVO order = buylist.get(i);
//				Integer quantity = order.getQuantity();
//				String type_eq_num = order.getType_eq_num();
//				for (DailyTotalVO list1 : list) {
//					if (type_eq_num == list1.getType_eq_num()
//							&& list1.getStart_qty() + quantity > list1.getDaily_eq_qty()) {
//						xx = false;
//					}
//				}
//
//			}
//			if (xx) {
//				for (int i = 0; i < buylist.size(); i++) {
//					EquipmentVO order = buylist.get(i);
//					Integer quantity = order.getQuantity();
//					String type_eq_num = order.getType_eq_num();
//					for (DailyTotalVO list1 : list) {
//						if(list1.getType_eq_num()==type_eq_num) {
//							Integer start_qty = list1.getStart_qty()+ quantity;
//							String dailyeq_num = list1.getDailyeq_num();
//							java.sql.Date eq_date = list1.getEq_date();
//							Integer daily_eq_qty = list1.getDaily_eq_qty();
//							list1.setDailyeq_num(dailyeq_num);
//							list1.setType_eq_num(type_eq_num);
//							list1.setEq_date(eq_date);
//							list1.setDaily_eq_qty(daily_eq_qty);
//							list1.setStart_qty(start_qty);
//							
//							updateDate.updateDailyTotal(dailyeq_num, type_eq_num, eq_date, daily_eq_qty, start_qty);
//						}
//							
//						
//					}
//				}
//			}
//
//		}
//
//	}
//}
//	
	
	
	
	
	
	//-------------------------------------------------------------------------------------------------------------------------------------------------
	
	
	
	
	
	
	
	
	
	
	
	
//			RentOdListVO rentodlistVO = new RentOdListVO();
//			rentodlistVO.setMember_id(member_id);
//			rentodlistVO.setRent_payment(rent_payment);
//			rentodlistVO.setRsved_rent_date(rsved_rent_date);
//			rentodlistVO.setEx_return_date(ex_return_date);
//			rentodlistVO.setOd_status(od_status);
//			rentodlistVO.setOd_total_price(od_total_price);
//			rentodlistVO.setRent_name(rent_name);
//			rentodlistVO.setRent_phone(rent_phone);
//
//			RentOdListDAO rentodlistDAO = new RentOdListDAO();
//			List<RentOdDetailVO> testList = new ArrayList<RentOdDetailVO>();
//			
//			for (int i = 0; i < buylist.size(); i++) {
//				RentOdDetailVO detail = new RentOdDetailVO();
//				EquipmentVO order = buylist.get(i);
//				Integer quantity = order.getQuantity();
//				String eq_num = order.getEq_num();
//
//				detail.setEq_num(eq_num);
//				detail.setQuantity(quantity);
//				testList.add(detail);
//			}	
//				rentodlistDAO.insertWithOrd(rentodlistVO , testList);
//
//				String url = "/front-end/renthome/order.jsp";
//				RequestDispatcher rd = req.getRequestDispatcher(url);
//				rd.forward(req, res);

//}

//		 if ("CHECKOUT".equals(action)) { 
//			 java.sql.Date rsved_rent_date =java.sql.Date.valueOf(req.getParameter("rsved_rent_date").trim());
//				java.sql.Date ex_return_date = java.sql.Date.valueOf(req.getParameter("ex_return_date").trim());
//		for (int i = 0; i < buylist.size(); i++) {
//			RentOdListService rolSVC = new RentOdListService(); 
//			RentOdDetailVO detail = new RentOdDetailVO();
//			List<RentOdDetailVO> dailyList = new ArrayList<RentOdDetailVO>();
//			EquipmentVO order = buylist.get(i);
//			Integer quantity = order.getQuantity();
//			String type_eq_num = order.getType_eq_num();
//        	
//			DailyTotalJDBCDAO dao = new DailyTotalJDBCDAO();
//			List<DailyTotalVO> sqllist = dao.getDate(type_eq_num,rsved_rent_date,ex_return_date);
//			String rsved_rent_date1 =rsved_rent_date.toString();
//			String ex_return_date1 =ex_return_date.toString();
//			
//			List<String> parameterdate = rolSVC.getDays(rsved_rent_date1,ex_return_date1);
//			for(DailyTotalVO dailytotal:sqllist) {
//				for(String javadate:parameterdate) {
//				if(dailytotal.getEq_date().toString()==javadate&& quantity+dailytotal.getStart_qty()>dailytotal.getDaily_eq_qty())
//					System.out.println("wrong");
//				else
//					System.out.println("OKAY");
//				}
//			}
//		}	
//		
//		 }	

//			detail.setEq_num(type_eq_num);
//			detail.setQuantity(quantity);
//			testList.add(detail);
//		}	
//			rentodlistDAO.insertWithOrd(rentodlistVO , testList);

//		List<String> errorMsgs = new LinkedList<String>();
//		req.setAttribute("errorMsgs", errorMsgs);
//
//			String member_id = req.getParameter("member_id");
//
//
//			String rent_payment = req.getParameter("rent_payment");
//	
//			
//			java.sql.Date rsved_rent_date =null;
//			java.sql.Date ex_return_date = null;
//			try {
//				rsved_rent_date = java.sql.Date.valueOf(req.getParameter("rsved_rent_date").trim());
//				ex_return_date = java.sql.Date.valueOf(req.getParameter("ex_return_date").trim());	
//						
//					/***************************2.開始查詢資料*****************************************/
//					DailyTotalService dailytotalSvc = new DailyTotalService();
//
//					List<DailyTotalVO> list = null;
//					for (DailyTotalVO aEqu : list) {
//						for (int i = 0; i < buylist.size(); i++) {
//							EquipmentVO order = buylist.get(i);
//							Integer quantity = order.getQuantity();
//							String type_eq_num = order.getType_eq_num();
//							list =dailytotalSvc.getDate(type_eq_num, rsved_rent_date,ex_return_date);
//							
//							
//							if(aEqu.getType_eq_num()==order.getType_eq_num()&&aEqu.getStart_qty()+quantity > aEqu.getDaily_eq_qty())
//								errorMsgs.add("超出範圍");
//							else
//								aEqu = dailytotalserviceSvc.updateDailyTotal(aEqu.getDailyeq_num(),aEqu.getType_eq_num(),aEqu.getEq_date(),aEqu.getDaily_eq_qty(),aEqu.getStart_qty()+quantity);
//								
//						}
//			
//					}
//				} catch (Exception e) {
//					errorMsgs.add("無法取得資料:" + e.getMessage());
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/front-end/renthome/orderform.jsp");
//					failureView.forward(req, res);
//				}
//
//				
//			
//
//			Integer od_status =  new Integer(req.getParameter("od_status").trim());
//		
//			Integer od_total_price =  new Integer(req.getParameter("od_total_price").trim());
//			
//			String rent_name = req.getParameter("rent_name").trim();
//			
//			String rent_phone = req.getParameter("rent_phone").trim();
//			
//			RentOdListVO rentodlistVO = new RentOdListVO();
//			rentodlistVO.setMember_id(member_id);
//			rentodlistVO.setRent_payment(rent_payment);
//			rentodlistVO.setRsved_rent_date(rsved_rent_date);
//			rentodlistVO.setEx_return_date(ex_return_date);
//			rentodlistVO.setOd_status(od_status);
//			rentodlistVO.setOd_total_price(od_total_price);
//			rentodlistVO.setRent_name(rent_name);
//			rentodlistVO.setRent_phone(rent_phone);
//			
//			
//			if (!errorMsgs.isEmpty()) {
//				req.setAttribute("rentodlistVO", rentodlistVO); // 含有輸入格式錯誤的empVO物件,也存入req
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/front-end/renthome/orderform.jsp");
//				failureView.forward(req, res);
//				return; //程式中斷
//			}			
//

//			
//
//
//    }

//}

//	// 使用byte[]方式
//	public static byte[] getPictureByteArray(InputStream is) throws IOException {
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		byte[] buffer = new byte[8192];
//		int i;
//		while ((i = is.read(buffer)) != -1) {
//			baos.write(buffer, 0, i);
//		}
//		baos.close();
//		is.close();
//
//		return baos.toByteArray();
//	}

//}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//		
//		if ("CHECKOUT".equals(action)) {
//			String member_id = req.getParameter("member_id");
//			String rent_payment = req.getParameter("rent_payment");
//			java.sql.Date rsved_rent_date = java.sql.Date.valueOf(req.getParameter("rsved_rent_date").trim());
//			java.sql.Date ex_return_date = java.sql.Date.valueOf(req.getParameter("ex_return_date").trim());
//			Integer od_status = new Integer(req.getParameter("od_status").trim());
//			Integer od_total_price = new Integer(req.getParameter("od_total_price").trim());
//			String rent_name = req.getParameter("rent_name").trim();
//			String rent_phone = req.getParameter("rent_phone").trim();
//
//
//			DailyTotalService updateDate = new DailyTotalService();
//			List<DailyTotalVO> list = new ArrayList();
//			
//			Boolean xx = true;
//			for (int i = 0; i < buylist.size(); i++) {
//				EquipmentVO order = buylist.get(i);
//				Integer quantity = order.getQuantity();
//				String type_eq_num = order.getType_eq_num();
//
//				DailyTotalService sqlDate = new DailyTotalService();
//				List<DailyTotalVO> listsqlDate = sqlDate.getsqlDate(type_eq_num, rsved_rent_date, ex_return_date);
//
//
//					for (DailyTotalVO sqlDateVO : listsqlDate) {
//
//						if (sqlDateVO.getStart_qty() + quantity > sqlDateVO.getDaily_eq_qty()) {
//							xx = false;
//						}else {
//							Integer start_qty = sqlDateVO.getStart_qty()+quantity;
//							String dailyeq_num = sqlDateVO.getDailyeq_num();
//							java.sql.Date eq_date = sqlDateVO.getEq_date();
//							Integer daily_eq_qty = sqlDateVO.getDaily_eq_qty();
//							sqlDateVO.setDailyeq_num(dailyeq_num);
//							sqlDateVO.setType_eq_num(type_eq_num);
//							sqlDateVO.setEq_date(eq_date);
//							sqlDateVO.setDaily_eq_qty(daily_eq_qty);
//							sqlDateVO.setStart_qty(start_qty);
//							list.add(sqlDateVO);
//						}
//					}
//				}	if (xx) {
//					for (int i = 0; i < buylist.size(); i++) {
//						EquipmentVO order = buylist.get(i);
//						Integer quantity = order.getQuantity();
//						String type_eq_num = order.getType_eq_num();
//						for (DailyTotalVO list1 : list) {
//							if(list1.getType_eq_num()==type_eq_num) {
//								Integer start_qty = list1.getStart_qty();
//								String dailyeq_num = list1.getDailyeq_num();
//								java.sql.Date eq_date = list1.getEq_date();
//								Integer daily_eq_qty = list1.getDaily_eq_qty();
//
//								
//								updateDate.updateDailyTotal(dailyeq_num, type_eq_num, eq_date, daily_eq_qty, start_qty);
//							}
//								
//							
//						}
//					}
//				}
//			}
//
//
//
//		}
//
//	}
//
//	
//		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
