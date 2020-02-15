package com.application.controller;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.application.model.ApplicationService;
import com.application.model.ApplicationVO;
import com.dailyBed.model.DailyBedService;
import com.dailyBed.model.DailyBedVO;
import com.group.model.GroupService;
import com.group.model.GroupVO;
import com.info.model.InfoService;
import com.location.model.LocationService;
import com.location.model.LocationVO;
import com.order_detail.model.Order_DetailService;
import com.room_order.model.Room_orderService;
import com.room_order.model.Room_orderVO;

public class ApplicationServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>(); // 將此集存儲在請求範圍中，以備我們需要時使用。
			req.setAttribute("errorMsgs", errorMsgs); // 發送錯誤視圖

			try {
				// 接收請求參數 - 輸入格式的錯誤處理
				String str = req.getParameter("app_num");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入入園申請單編號");
				}

				// 如果有錯誤，請將用途發回表單
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/application/application.jsp");
					failureView.forward(req, res);
					return; // 中斷下面的程式
				}
				String app_num = null;
				try {
					app_num = new String(str); // str = req.getParameter("app_num");
				} catch (Exception e) {
					errorMsgs.add("入園申請單格式不正確");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/application/application.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 2.開始查詢資料 *****************************************/
				ApplicationService appSvc = new ApplicationService();
				ApplicationVO appVO = appSvc.getOneApp(app_num);
				if (appVO == null) {
					errorMsgs.add("查無資料");
				}
				// 如果有錯誤，請將用途發回表單
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/application/application.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("empVO", appVO); // 資料庫取出的empVO物件,存入req
				String url = "/emp/listOneEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/application/application.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();// 將此集存儲在請求範圍中，以備我們需要時使用
			req.setAttribute("errorMsgs", errorMsgs);// 發送ErrorPage視圖。

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				System.out.println("進來了");
				
				boolean openModal=true;
				// 無須驗證，因為抓別人資料，已驗證
				String radio = req.getParameter("radio");
				String member_id = req.getParameter("member_id");
				System.out.println(member_id);
				String route_id = req.getParameter("route_id");

				String egc_contact = req.getParameter("egc_contact");
				String contactReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (egc_contact == null || egc_contact.trim().length() == 0) {
					errorMsgs.add("緊急連絡人: 請勿空白");
				} else if (!egc_contact.trim().matches(contactReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("緊急聯絡人: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}
				System.out.println("進來了3");
				String egc_phone = req.getParameter("egc_phone").trim();
				String phoneReg = "^[0-9]{10}$";
				if (egc_phone == null || egc_phone.trim().length() == 0) {
					errorMsgs.add("緊急聯絡人電話請勿空白");
				} else if (!egc_phone.matches(phoneReg)) {
					errorMsgs.add("緊急聯絡人電話:只能是數字，不要超過10個數字");
				}
				System.out.println("進來了4");
				String satellite = req.getParameter("satellite").trim();
				String satelliteReg = "^[0-9]{1,10}$";
				if (!satellite.trim().matches(satelliteReg)) {
					errorMsgs.add("衛星電話請輸入數字");
				}
				System.out.println("進來了5");
				Integer app_status = Integer.parseInt(req.getParameter("app_status").trim());
				if (app_status == null || app_status == 0) {
					System.out.println("這邊要想一下，要設定狀態碼，判斷不對要傳回給揪團");
				}
				System.out.println("進來了6");
				Integer app_days = null;
				String day = req.getParameter("app_days").trim();
				String dayReg = "^[0-9]{2}$";
				if (!(day.trim()).matches(dayReg)) {
					errorMsgs.add("請不要輸入超過兩位數!");
				} else if (day == null || day.trim().length() == 0) {
					errorMsgs.add("請輸入預定床位");
				} else
					app_days = Integer.parseInt(day);
				System.out.println("進來了7");
				java.sql.Date first_day = null;
				try {
					// valueOf轉成java物件
					first_day = java.sql.Date.valueOf(req.getParameter("first_day").trim());
				} catch (IllegalArgumentException e) {
					first_day = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				System.out.println("進來了8");
				System.out.println("進來了9");

				ApplicationVO appVO = new ApplicationVO();
				System.out.println("進來了11");
//				appVO.setGroup_id(group_id);
				appVO.setMember_id(member_id);
				System.out.println("進來了12");
				appVO.setRoute_id(route_id);
				appVO.setEgc_contact(egc_contact);
				appVO.setEgc_phone(egc_phone);
				appVO.setSatellite(satellite);
				appVO.setRadio(radio);
				appVO.setApp_status(app_status);
				appVO.setApp_days(app_days);
				appVO.setFirst_day(first_day);
				// 測試
				System.out.println(errorMsgs);

				// 如果有錯誤，請將用途發回表單
				if (!errorMsgs.isEmpty()) {
					System.out.println("錯誤了拉");
					req.setAttribute("appVO", appVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/application/Application.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				ApplicationService appSvc = new ApplicationService();
				appVO = appSvc.addApp("", member_id, route_id, egc_contact, egc_phone, satellite, radio,
						app_status, app_days, first_day,0,0,"");
				req.setAttribute("openModal",openModal );
				req.setAttribute("appVO", appVO);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				System.out.println("新增成功");
				String url = "/front-end/application/Application.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				System.out.println("錯誤了拉4");
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/application/Application.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				// 無須驗證
				String app_num = req.getParameter("app_num").trim();
				String group_id = req.getParameter("group_id").trim();
				String member_id = req.getParameter("member_id").trim();
				String route_id = req.getParameter("route_id").trim();
				String radio = req.getParameter("radio").trim();
				String locations = req.getParameter("locations").trim();

				String egc_contact = req.getParameter("egc_contact");
				String contactReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (egc_contact == null || egc_contact.trim().length() == 0) {
					errorMsgs.add("緊急連絡人: 請勿空白");
				} else if (!egc_contact.matches(contactReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("緊急聯絡人: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				String egc_phone = req.getParameter("egc_phone").trim();
				String phoneReg = "^[0-9]{10}$";
				if (egc_phone == null || egc_phone.trim().length() == 0) {
					errorMsgs.add("緊急聯絡人電話請勿空白");
				} else if (!egc_contact.trim().matches(phoneReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("緊急聯絡人電話:只能是數字，不要超過10個數字");
				}

				String satellite = req.getParameter("satellite").trim();
				String satelliteReg = "^[0-9]$";
				if (!satellite.trim().matches(satelliteReg)) {
					errorMsgs.add("衛星電話請輸入數字");
				}

				Integer app_status = Integer.parseInt(req.getParameter("app_status").trim());
				if (app_status == null || app_status == 0) {
					System.out.println("這邊要想一下，要設定狀態碼，判斷不對要傳回給揪團");
				}

				Integer app_days = null;
				String day = req.getParameter("app_days").trim();
				String dayReg = "^[0-9]{2}$";
				if (!(day.trim()).matches(dayReg)) {
					errorMsgs.add("請不要輸入超過兩位數!");
				} else if (day == null || day.trim().length() == 0) {
					errorMsgs.add("請輸入預定床位");
				} else
					app_days = Integer.parseInt(day);

				java.sql.Date first_day = null;
				try {
					// valueOf轉成java物件
					first_day = java.sql.Date.valueOf(req.getParameter("first_day").trim());
				} catch (IllegalArgumentException e) {
					first_day = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				Integer dailybed_provided = null;
				String dailybed = req.getParameter("dailybed_dailybed").trim();
				String dailybedReg = "^[0-9]{2}$";
				if (!(dailybed.trim()).matches(dailybedReg)) {
					errorMsgs.add("請不要輸入超過兩位數!");
				} else if (dailybed == null || dailybed.trim().length() == 0) {
					errorMsgs.add("請輸入預定床位");
				} else
					dailybed_provided = Integer.parseInt(dailybed);

				Integer meal_provided = null;
				String meal = req.getParameter("meal_provided").trim();
				String mealReg = "^[0-9]{2}$";
				if (!(meal.trim()).matches(mealReg)) {
					errorMsgs.add("請不要輸入超過兩位數!");
				} else if (meal == null || meal.trim().length() == 0) {
					errorMsgs.add("請輸入預定床位");
				} else
					meal_provided = Integer.parseInt(meal);

				ApplicationVO appVO = new ApplicationVO();
				appVO.setGroup_id(group_id);
				appVO.setMember_id(member_id);
				appVO.setRoute_id(route_id);
				appVO.setEgc_contact(egc_contact);
				appVO.setEgc_phone(egc_phone);
				appVO.setSatellite(satellite);
				appVO.setRadio(radio);
				appVO.setApp_status(app_status);
				appVO.setApp_days(app_days);
				appVO.setFirst_day(first_day);
				appVO.setDailybed_provided(dailybed_provided);
				appVO.setMeal_provided(meal_provided);
				appVO.setLocations(locations);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("appVO", appVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/application/Application.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				ApplicationService appSvc = new ApplicationService();
				appVO = appSvc.updateApp(app_num, group_id, member_id, route_id, egc_contact, egc_phone, satellite,
						radio, app_status, app_days, first_day, dailybed_provided, meal_provided, locations);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("empVO", appVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/application/application.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				System.out.println("錯誤了拉4");
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/application/application.jsp");
				failureView.forward(req, res);
			}
		}
		
//-----------------------------------------------通過單人-----------------------------------------------------------		
		
		
		if ("pass_alone".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*************************** 1.接收請求參數 ***************************************/
				ApplicationService appSvc = new ApplicationService();
				String app_num = req.getParameter("app_num");
				appSvc.updateApp_One(1,app_num);
				
			
				
				/*************************** 3.,準備轉交(Send the Success view) ***********/
				String url = "/back-end/application/Application_back.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("錯誤:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/application/Application_back.jsp");
				failureView.forward(req, res);
			}

		}
//-----------------------------------------------通過-----------------------------------------------------------
		
		if ("pass".equals(action)) {
			System.out.println("進入pass");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			System.out.println("檢測");
			
			//測試注意事項，申請天數必須跟食宿相同，目的必須是代號
			
			
			try {
				/*************************** 1.接收請求參數 1.**************************************/
				
				
				
				
				String group_id = req.getParameter("group_id").trim();
				Integer app_days = new Integer(req.getParameter("app_days"))-1;
				String member_id = req.getParameter("member_id").trim();
				Date first_day = java.sql.Date.valueOf(req.getParameter("first_day"));
				Integer meal_price = new Integer(req.getParameter("meal_price"));
				Integer bed_price = new Integer(req.getParameter("bed_price"));
				
				//當天總住宿
				Integer mdailybed_provided = new Integer(req.getParameter("dailybed_provided"))/app_days;
				//當天總訂餐
				Integer meal_provided = new Integer(req.getParameter("meal_provided"))/app_days;
				//取得地點字串分割後
				String locations_str = req.getParameter("locations").trim();
				String[] locations_array = locations_str.split(",");
				
				System.out.println("測試"+mdailybed_provided);
				System.out.println("測試111"+app_days);

				
//				System.out.println(locations_array[0]);
//				System.out.println(locations_array[1]);
//				System.out.println(locations_array[2]);
				
				/*************************** 2.開始e-maill傳資料 ***************************************/
				Order_DetailService detaillSvc = new Order_DetailService();
				Room_orderService roomSvc = new Room_orderService();
				
				
				roomSvc.addRoom_order(member_id, group_id,0, 1, 1 ,app_days);
				//依申請天數新增明細
				
				for(int i = 0 ; i < app_days ; i++) {
					DailyBedService dailyBedSvc = new DailyBedService();
					LocationService locationSvc = new LocationService();
					List<LocationVO> location_list = locationSvc.getAll();
					DailyBedVO dailyBedVO = null ;
					String location_id ="";
					
					for(LocationVO locationVO : location_list) {
						String location_name = locationVO.getLocation_name();
//						System.out.println("location_name"+location_name);
						if(location_name.equals(locations_array[i])) {
							location_id = locationSvc.getLocationName(location_name).getLocation_id();
							System.out.println("location_id測試"+location_id);
							
						}
					}
					
					dailyBedVO = dailyBedSvc.getDailyBedVOByFullDate(location_id, first_day);
					
					
					
//					System.out.println("DailyBed_id"+dailyBedVO.getDailyBed_id());
					//當數字不等於零才能進來扣床數
					if(dailyBedVO.getRemaining_total() != 0) {
						dailyBedSvc.updateDailyBed(dailyBedVO.getDailyBed_id(),mdailybed_provided);
						
					}else {
						errorMsgs.add("床位數不足");
					}
					
					System.out.println("測試數量:"+dailyBedSvc.updateDailyBed(dailyBedVO.getDailyBed_id(),mdailybed_provided).toString());
					
					
					
					System.out.println("member_id:"+member_id);
					//取得order_id
					Room_orderVO roomVO = roomSvc.getLastestOne(member_id);
					String order_id = roomVO.getOrder_id();
					System.out.println("order_id:"+order_id);
					
					//新增最近床位數量
					detaillSvc.addOrder_Detail(order_id,location_id,mdailybed_provided,bed_price,first_day,"M001",meal_provided,meal_price);
				} 
				
				
					//成立訂單
					int order_price = (mdailybed_provided*app_days*bed_price)+(meal_provided*app_days*meal_price);
					System.out.println("order_price"+order_price);
					roomSvc.updateRoom_order(member_id, group_id, order_price, 1, 1, app_days);
					System.out.println("新增成功"+order_price);
					
					//更改已審核
					ApplicationService appSvc = new ApplicationService();
					String app_num = req.getParameter("app_num");
					appSvc.updateApp_One(1,app_num);
					
					System.out.println("審核成功");
					
					
					//更改揪團狀態
					GroupService groupSvc = new GroupService();
					GroupVO groupVO = groupSvc.getOneGroup(group_id);
					System.out.println(groupVO.toString());
//					groupVO.setGp_status(0);
					groupSvc.updateStatus(group_id,0);  //update方法套柏楊的方法
					
					System.out.println("狀態更新成功");
				
					
					

				/*************************** 3.e-maill傳資料完成,準備轉交(Send the Success view) ***********/
				
					String url = "/back-end/application/Application_back.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
					

				/*************************** 其他可能的錯誤處理 **********************************/
					} catch (Exception e) {
					String m_email = req.getParameter("m_email").trim();
					System.out.println("m_email錯誤 ");
					e.printStackTrace();
					errorMsgs.add("通過失敗:" + e.getMessage());
					
					ApplicationService appSvc = new ApplicationService();
					appSvc.transfer_Mail(m_email,"入園申請不通過","您的入園申請單不通過");
					
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/application/Application_back.jsp");
					failureView.forward(req, res);
			}
		}

//----------------------------------------------不通過------------------------------------------------------------		
		if ("fail".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*************************** 1.接收請求參數 ***************************************/
				ApplicationService appSvc = new ApplicationService();
				String m_email = req.getParameter("m_email").trim();
				String app_num = req.getParameter("app_num");
				System.out.println(app_num);
				appSvc.updateApp_One(2,app_num);

				/*************************** 2.開始e-maill傳資料 ***************************************/
				
//				appSvc.transfer_Mail("kiss78832@gmail.com","入園申請不通過", "您的入園申請單不通過");
//				appSvc.updateApp_One(0,app_num);
				
				/*************************** 3.e-maill傳資料完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/application/Application_back.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("e-mail錯誤:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/application/Application_back.jsp");
				failureView.forward(req, res);
			}

		}

//-----------------------------------------------通過單人-----------------------------------------------------------		
		
		
				if ("check_pass".equals(action)) {
					
					List<String> errorMsgs = new LinkedList<String>();
					req.setAttribute("errorMsgs", errorMsgs);
					
					try {
						/*************************** 1.接收請求參數 ***************************************/
						ApplicationService appSvc = new ApplicationService();
						String app_num = req.getParameter("app_num");
						appSvc.updateApp_One(1,app_num);
						
					
						
						/*************************** 3.,準備轉交(Send the Success view) ***********/
						String url = "/back-end/application/Application_back.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url);
						successView.forward(req, res);

						/*************************** 其他可能的錯誤處理 **********************************/
					} catch (Exception e) {
						errorMsgs.add("錯誤:" + e.getMessage());
						RequestDispatcher failureView = req.getRequestDispatcher("/back-end/application/Application_back.jsp");
						failureView.forward(req, res);
					}

				}		
		
				
/*****************************************跳窗**********************************************************/
				if ("getOne_From06".equals(action)) {

					try {
				
						//Bootstrap_modal
						boolean openModal=true;
						req.setAttribute("openModal",openModal );
						
						// 取出的empVO送給listOneEmp.jsp
						RequestDispatcher successView = req
								.getRequestDispatcher("/front-end/application/Application.jsp");
						successView.forward(req, res);
						return;

						// Handle any unusual exceptions
					} catch (Exception e) {
						throw new ServletException(e);
					}
				}
		
/****************/		
	}

}