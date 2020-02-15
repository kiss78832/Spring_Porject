package com.room_order.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONObject;

import com.dailyBed.model.DailyBedService;
import com.dailyBed.model.DailyBedVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.location.model.LocationService;
import com.meal.model.MealService;
import com.member.model.MemberVO;
import com.order_detail.model.Order_DetailService;
import com.order_detail.model.Order_DetailVO;
import com.room_order.model.MailService;
import com.room_order.model.Room_orderService;
import com.room_order.model.Room_orderVO;


@ServerEndpoint("/Room_Order/{userName}")
public class room_orderServlet extends HttpServlet {
	
	private static final Set<Session> connectedSessions = Collections.synchronizedSet(new HashSet<>());

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		res.setContentType("text/html; charset=UTF-8");

		if ("insertOrder".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				List<Order_DetailVO> orderDetailCheck = (List<Order_DetailVO>) req.getSession()
						.getAttribute("orderDetailCheck");
				System.out.println("orderDetailCheck = " + orderDetailCheck);
				// check errors
				DailyBedService dailyBedSvc = new DailyBedService();
				LocationService locSvc = new LocationService();
				
				//check loc dailyBedVO remaining number
				for (Order_DetailVO order_DetailVO : orderDetailCheck) {

					String location_id = order_DetailVO.getLocation_id();
					Date date = order_DetailVO.getCheckin_date();

					DailyBedVO dailyBedVO = dailyBedSvc.getDailyBedVOByFullDate(location_id, date);
					
						if (dailyBedVO.getRemaining_total() == 0) {
							errorMsgs.add(
									new LocationService().getOneLocation(dailyBedVO.getLocation_id()).getLocation_name()
											+ "於" + date + "的預訂床位數已滿!");
						}else if(dailyBedVO.getRemaining_total() < order_DetailVO.getBed_num()){
							errorMsgs.add(
									new LocationService().getOneLocation(dailyBedVO.getLocation_id()).getLocation_name()
											+ "於" + date + "的床位數剩餘"+dailyBedVO.getRemaining_total()+",不足以提供給您所預訂的床位數!");
						}
					

				}

				//error handle
				if (!errorMsgs.isEmpty()) {
					System.out.println("order have problem!!");
					
					
					List<String> error = (List<String>)req.getAttribute("errorMsgs");
					Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

					String jsonStr = gson.toJson(error);

					System.out.println(error);
					
					
					req.setAttribute("state", "fail");
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/room_order/Reserved_Bed.jsp");
					failureView.forward(req, res);

					return;
				}

				// 模擬會員帳號

				
//				req.getSession().setAttribute("member_id", "A001");
				MemberVO memberVO = (MemberVO)req.getSession().getAttribute("memberVO");
				String member_id = memberVO.getMember_id();

				// insert room_order
				Integer booking_day = orderDetailCheck.size();
				Integer order_price = 0;
				for (Order_DetailVO order_DetailVO : orderDetailCheck) {

					order_price += (order_DetailVO.getBedTotal_price() + order_DetailVO.getMealTotal_price());

				}

				Room_orderService room_orderSvc = new Room_orderService();
				//room_orderSvc.addWithoutGroup(member_id, order_price, 1, 1, booking_day);
				
				//prepare for insertWithOrders methods
				Room_orderVO room_orderVO2 = new Room_orderVO();
				room_orderVO2.setMember_id(member_id);
				room_orderVO2.setOrder_price(order_price);
				room_orderVO2.setOrder_status(1);
				room_orderVO2.setPayment_status(1);
				room_orderVO2.setBooking_day(booking_day);

				room_orderSvc.insertWithOrders(room_orderVO2, orderDetailCheck);
				
				System.out.println("新增訂單成功");

				Order_DetailService order_DetailSvc = new Order_DetailService();

				
				// append mail messages
				StringBuilder mailmessages = new StringBuilder();
				int orderCount = 0;
				
				
				for (Order_DetailVO order_DetailVO : orderDetailCheck) {

					// update dailyBedVO
					
					

					String location_id = order_DetailVO.getLocation_id();
					Date date = order_DetailVO.getCheckin_date();

					DailyBedVO dailyBedVO = dailyBedSvc.getDailyBedVOByFullDate(location_id, date);

					String dailyBed_id = dailyBedVO.getDailyBed_id();

					Integer reservedNumber = order_DetailVO.getBed_num();

					dailyBedSvc.updateDailyBed(dailyBed_id, reservedNumber);

					// insert order_detailVO
					
					//method which can get the most recent room_orderVO
					Room_orderVO room_orderVO = room_orderSvc.getLastestOne(member_id);

					String order_id = room_orderVO.getOrder_id();
					
					//used for e-mail payment
					req.getSession().setAttribute("order_id",order_id);

					Integer bed_num = order_DetailVO.getBed_num();

					Integer bedTotal_price = order_DetailVO.getBedTotal_price();

					Date checkin_date = order_DetailVO.getCheckin_date();

					String meal_id = order_DetailVO.getMeal_id();
					Integer meal_quantity = order_DetailVO.getMeal_quantity();
					Integer mealTotal_price = order_DetailVO.getMealTotal_price();

//					if ("0".equals(order_DetailVO.getMeal_id())) {
//						order_DetailSvc.addWithoutMeal(order_id, location_id, bed_num, bedTotal_price, checkin_date);
//					} else {
//
//						order_DetailSvc.addOrder_Detail(order_id, location_id, bed_num, bedTotal_price, checkin_date,
//								meal_id, meal_quantity, mealTotal_price);
//
//					}
					
					MealService mealSvc = new MealService();
					
					orderCount++;
					
					mailmessages.append("\r\n");
					mailmessages.append("第").append(orderCount).append("筆訂單明細:\r\n");
					mailmessages.append("訂單編號:").append(order_id).append("\r\n");
					mailmessages.append("預訂據點名稱:").append(locSvc.getOneLocation(location_id).getLocation_name()).append("\r\n");
					mailmessages.append("訂床數:").append(bed_num).append("\r\n");
					mailmessages.append("預訂床位總價錢:").append(bedTotal_price).append("\r\n");
					mailmessages.append("入住日期:").append(date).append("\r\n");
					
					if ( !("0".equals(order_DetailVO.getMeal_id())) ) {
						mailmessages.append("套餐編號:").append(meal_id).append("\r\n");
						mailmessages.append("套餐名稱:").append(mealSvc.getOneMeal(meal_id).getMeal_name()).append("\r\n");
						mailmessages.append("套餐份數:").append(meal_quantity).append("\r\n");
						mailmessages.append("套餐價錢:").append(mealTotal_price).append("\r\n");
					}
					
					mailmessages.append("\r\n");
					mailmessages.append("--------------------------\r\n");

					
					
				}

				mailmessages.append("預定天數").append(booking_day).append("\r\n");
				mailmessages.append("訂單價格:").append(order_price).append("\r\n");
				
				mailmessages.append("\r\n");
				
				
				mailmessages.append("\r\n");
				mailmessages.append("請在14天內至以下網址進行訂單繳費，謝謝您的預訂:")	.append("\r\n");
				mailmessages.append("\r\n");
				mailmessages.append(req.getScheme()+"://" +req.getServerName()+":"+req.getServerPort()+ req.getContextPath()+"/front-end/order_detail/payment.jsp");
//				mailmessages.append("http://localhost:8081" + req.getContextPath()+"/roomOrder.html?action=alreadyPay");
				mailmessages.append("\r\n");
				
				
				
				
				
				String state = "success";
				req.setAttribute("state", state);
				
				//send e-mail
//				String to = "cavalier121@gmail.com";
//				String to = "fx22451@gmail.com";
//				String to = "alex9708hero@gmail.com";
				
				String to = (String)memberVO.getM_email();
				
				String subject = "顧客新增訂單通知";
				
				String messageText = mailmessages.toString();
				
				System.out.println(messageText);
				 
				MailService mailService = new MailService();
			    mailService.sendMail(to, subject, messageText);

			    //web socket
			    JSONObject jobj = new JSONObject();
			   
			    jobj.put("action" , "insertOrder");
			    
				for (Session session : connectedSessions) {
					if (session.isOpen())
						session.getAsyncRemote().sendText(jobj.toString());
				}
			    
			    
			    //remove session List<Order_DetailVO> orderDetailCheck
			    req.getSession().removeAttribute("orderDetailCheck");
			    
				String url = "/front-end/room_order/Reserved_Bed.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/room_order/Reserved_Bed.jsp");
				failureView.forward(req, res);
			}

		}
		
		if("getRoomOrder".equals(action)) {
			try {
				
	
				
				String order_id = req.getParameter("order_id");
				req.getSession().setAttribute("order_id", order_id);
				
				System.out.println(order_id);
				
				System.out.println("getRoom_order!");

				
			}catch (Exception e) {
				e.printStackTrace();
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/room_order/Reserved_Bed.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("alreadyPay".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				
				String order_id = (String)req.getSession().getAttribute("order_id");
				
				Room_orderService room_orderSvc = new Room_orderService();
				room_orderSvc.updateOrder_status(2, 2, order_id);
				
				String paymentState = "success";
				req.setAttribute("paymentState", paymentState);
				
				req.getSession().removeAttribute("order_id");
				
			    //web socket
			    JSONObject jobj = new JSONObject();
			   
			    jobj.put("action" , "alreadyPay");
			    
				for (Session session : connectedSessions) {
					if (session.isOpen())
						session.getAsyncRemote().sendText(jobj.toString());
				}
				
				
				System.out.println("alreadyPay success^__^");
				
				String url = "/front-end/order_detail/Order_Query.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				
			}catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/room_order/Reserved_Bed.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if("MemberCancel".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				
				String order_id = req.getParameter("order_id");
				
				System.out.println(order_id);
				
				Room_orderService room_orderSvc = new Room_orderService();
				
				Room_orderVO room_orderVO = room_orderSvc.getOneRoom_Order(order_id);
				if(room_orderVO.getPayment_status() == 3){
					errorMsgs.add("此筆訂單已被取消");
					System.out.println("cancel come");
				}
				
				if (!errorMsgs.isEmpty()) {
					System.out.println("order cancel have problem!!");
					
					req.setAttribute("errorMsgs", errorMsgs);
					
					req.setAttribute("cancelState", "duplicate");
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/order_detail/Order_Query.jsp");
					failureView.forward(req, res);
					System.out.println("cancel come2");

					return;
				}
				
				
				
				
				
				if(room_orderSvc.getOneRoom_Order(order_id).getPayment_status()==1) {
					room_orderSvc.updateOrder_status(4, 3, order_id);
				}
				else{
					room_orderSvc.updateOrder_status(5, 3, order_id);
				}
				
				Order_DetailService order_detailSvc = new Order_DetailService();
				DailyBedService dailyBedSvc = new DailyBedService();
				
				List<Order_DetailVO> list = order_detailSvc.getDetailsByOrder_id(order_id);
				
				for(Order_DetailVO order_DetailVO : list) {
				
					String location_id = order_DetailVO.getLocation_id();
					Date date = order_DetailVO.getCheckin_date();

					DailyBedVO dailyBedVO = dailyBedSvc.getDailyBedVOByFullDate(location_id, date);

					String dailyBed_id = dailyBedVO.getDailyBed_id();

					Integer reservedNumber = order_DetailVO.getBed_num();

					dailyBedSvc.cancelDailyBed(dailyBed_id, reservedNumber);

				}
				
				String cancelState = "success";
				req.setAttribute("cancelState", cancelState);
				
			    //web socket
				
			    JSONObject jobj = new JSONObject();
			   
			    jobj.put("action" , "cancel");
			    
				for (Session session : connectedSessions) {
					if (session.isOpen())
						session.getAsyncRemote().sendText(jobj.toString());
				}
				
				
				System.out.println("cancel success");
				
				String url = "/front-end/order_detail/Order_Query.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				
			}catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/order_detail/Order_Query.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("locSelectForChart".equals(action)) {
			
			String location_id = req.getParameter("location_id");
			
			 DailyBedService dailyBedSvc = new  DailyBedService();
			
			
			List<DailyBedVO> list = dailyBedSvc.getAllByLoc_idAndDate(location_id,
					new Integer(req.getParameter("year")), new Integer(req.getParameter("nextMonth")) + 1);
			
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			
			String jsonStr = gson.toJson(list);

			System.out.println(jsonStr);

			PrintWriter out = res.getWriter();

			out.print(jsonStr);
			out.flush();
			
		}
		

	}
	
	//web socket methods
	
	@OnOpen
	public void onOpen(@PathParam("userName") String userName, Session userSession) throws IOException {
		connectedSessions.add(userSession);
		String text = String.format("Session ID = %s, connected; userName = %s", userSession.getId(), userName);
		System.out.println(text);
	}

	@OnMessage
	public void onMessage(Session userSession, String message) {
		for (Session session : connectedSessions) {
			if (session.isOpen())
				session.getAsyncRemote().sendText(message);
		}
		System.out.println("Message received: " + message);
	}

	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		connectedSessions.remove(userSession);
		String text = String.format("session ID = %s, disconnected; close code = %d; reason phrase = %s",
				userSession.getId(), reason.getCloseCode().getCode(), reason.getReasonPhrase());
		System.out.println(text);
	}

	@OnError
	public void onError(Session userSession, Throwable e) {
		System.out.println("Error: " + e.toString());
	}
	

}
