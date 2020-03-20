package com.spring.order_detail.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.spring.dailyBed.model.DailyBedService;
import com.spring.dailyBed.model.DailyBedVO;
import com.spring.location.model.LocationService;
import com.spring.location.model.LocationVO;
import com.spring.meal.model.MealService;
import com.spring.order_detail.model.Order_DetailService;
import com.spring.order_detail.model.Order_DetailVO;
import com.spring.room_order.model.Room_orderService;
import com.spring.room_order.model.Room_orderVO;

@WebServlet("/order_detailServlet")
public class order_detailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public order_detailServlet() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		res.setContentType("text/html; charset=UTF-8");

		// ajax date select
		if ("dateSelect".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				Integer year = new Integer(req.getParameter("year"));
				Integer month = new Integer(req.getParameter("month"));
				Integer date = new Integer(req.getParameter("date"));

				DailyBedService dailyBedService = new DailyBedService();
				List<DailyBedVO> list_dailyBedVO = dailyBedService.getAllByDate(year, month, date);
				List<String> list_locName = dailyBedService.getLocNameByDate(year, month, date);

				
				List<LocationVO> locations_status2 = new LocationService().getLocsByLoc_status(2);
					
					
				StringBuilder sb = new StringBuilder("[");

				for (int i = 0; i < list_dailyBedVO.size(); i++) {
					DailyBedVO dailyBedVO = list_dailyBedVO.get(i);
					
					//只顯示不用審核的據點
					
					for (int k = 0; k < locations_status2.size(); k++) {
						
						LocationVO locationVO = locations_status2.get(k);
						
						if(dailyBedVO.getLocation_id().equals(locationVO.getLocation_id())) {
							
							sb.append("{\"location_id\":\"" + dailyBedVO.getLocation_id() + "\",");

							for (int j = 0; j < list_locName.size(); j++) {
								if (i == j) {
									String loc_name = list_locName.get(j);
									sb.append("\"loc_name\":\"" + loc_name + "\"},");
								}
							}
							
							
							
						}
						
							
					}
					

				}
				sb.replace(sb.length() - 1, sb.length(), "]");

				String loc_idAndLoc_name = sb.toString();

				System.out.println(loc_idAndLoc_name);

				PrintWriter out = res.getWriter();

				out.print(loc_idAndLoc_name);
				out.flush();

			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("選擇日期資料失敗" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/room_order/Reserved_Bed.jsp");
				failureView.forward(req, res);
			}

		}

		// ajax location select
		if ("locSelect".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {

				String location_id = req.getParameter("location_id");

				Integer year = new Integer(req.getParameter("year"));
				Integer month = new Integer(req.getParameter("month"));
				Integer date = new Integer(req.getParameter("date"));

				DailyBedService dailyBedSvc = new DailyBedService();

				DailyBedVO dailyBedVO = dailyBedSvc.getLocVOByDate(year, month, date, location_id);

				Integer remaining_total = dailyBedVO.getRemaining_total();

				LocationService locSvc = new LocationService();
				Integer loc_type = locSvc.getOneLocation(location_id).getLoc_type();

				StringBuilder sb = new StringBuilder("[{");
				sb.append("\"loc_type\":\"" + loc_type + "\",\"remaining_total\":\"" + remaining_total + "\"}]");

				String locTypeRemainingId = sb.toString();

				System.out.println(locTypeRemainingId);

				PrintWriter out = res.getWriter();

				out.print(locTypeRemainingId);
				out.flush();

			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("選擇據點資料失敗" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/room_order/Reserved_Bed.jsp");
				failureView.forward(req, res);
			}

		}

		// press order commit from Reserved_bed.jsp
		if ("submitOrder".equals(action)) {

			Set<String> errorMsgs = new HashSet<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {

				String[] date = req.getParameterValues("date");
				String[] location_id = req.getParameterValues("location");
				String[] bed_num = req.getParameterValues("bed_num");
				String[] meal_id = req.getParameterValues("meal_id");
				String[] meal_num = req.getParameterValues("meal_num");

				// create order_detailVO list and set data (haven't checked yet)
				List<Order_DetailVO> list = new LinkedList<Order_DetailVO>();

				for (int i = 0; i < meal_num.length; i++) {
					Order_DetailVO order_detailVO = new Order_DetailVO();

					System.out.println(date);
					if (date[i].equals("")) {
						errorMsgs.add("請選擇日期");
					}else {
						System.out.println("1111");
						order_detailVO.setCheckin_date(java.sql.Date.valueOf(date[i]));
					}

					if (location_id == null) {
						errorMsgs.add("請選擇據點");
					} else {

						order_detailVO.setLocation_id(location_id[i]);
						System.out.println("location_id:" + location_id[i] + location_id[i].getClass());
					}

//					System.out.println("bed_num"+bed_num[i]+bed_num[i].getClass());
//					
//					System.out.println("bed_num[i] = "+bed_num[i]);
					if (bed_num == null || "".equals(bed_num[i]) || "0".equals(bed_num[i]) ) {
						if (location_id != null) {
							StringBuilder sb = new StringBuilder();
							sb.append("請選擇")
									.append(new LocationService().getOneLocation(location_id[i]).getLocation_name())
									.append("的床位數");
							errorMsgs.add(sb.toString());
							System.out.println("床位沒抓到");
						}
					} else {
						System.out.println("床位OK");
						
						order_detailVO.setBed_num(Integer.valueOf(bed_num[i]));
						
						Integer bed_price = new LocationService().getOneLocation(location_id[i]).getBed_price();

						order_detailVO.setBedTotal_price(Integer.parseInt(bed_num[i]) * bed_price);
					}

					if (meal_id != null) {

						if ("0".equals(meal_id[i])) {

							order_detailVO.setMeal_id("0");
							order_detailVO.setMeal_quantity(0);
							order_detailVO.setMealTotal_price(0);
							list.add(order_detailVO);

						} else {

							if (Integer.parseInt(meal_num[i]) == 0 || meal_num[i] == null) {

								order_detailVO.setMeal_id("0");
								order_detailVO.setMeal_quantity(0);
								order_detailVO.setMealTotal_price(0);
								list.add(order_detailVO);

							} else {

								order_detailVO.setMeal_id(meal_id[i]);

								order_detailVO.setMeal_quantity(Integer.parseInt(meal_num[i]));

								Integer meal_price = new MealService().getOneMeal(meal_id[i]).getMeal_price();

								order_detailVO.setMealTotal_price(Integer.parseInt(meal_num[i]) * meal_price);

								MealService mealSvc = new MealService();
								System.out.println(mealSvc.getOneMeal(meal_id[i]).getMeal_name());

								list.add(order_detailVO);
							}
						}

					}
				}

				

				// check error

				List<Order_DetailVO> temp = new LinkedList<Order_DetailVO>();

				for (int i = 0; i < list.size(); i++) {
					Order_DetailVO order_DetailVO = list.get(i);

					for (int j = 0; j < list.size(); j++) {
						Order_DetailVO compareVO = list.get(j);
						if (order_DetailVO.getCheckin_date().compareTo(compareVO.getCheckin_date()) == 0
								&& order_DetailVO.getLocation_id().equals(compareVO.getLocation_id()) && j != i) {
							temp.add(compareVO);
							StringBuilder sb = new StringBuilder();
							sb.append("您的食宿訂單在").append(order_DetailVO.getCheckin_date()).append("重複了相同的據點:")
									.append(new LocationService().getOneLocation(order_DetailVO.getLocation_id())
											.getLocation_name());

							String str = sb.toString();

							errorMsgs.add(str);
							System.out.println("add error");
						}
					}
				}

				for (Order_DetailVO order_DetailVO : temp) {
					list.remove(order_DetailVO);
				}
				
				//check bed number error
				
				for (Order_DetailVO order_DetailVO : list) {

					DailyBedService dailyBedSvc = new DailyBedService();
					
					String location_id_check = order_DetailVO.getLocation_id();
					java.sql.Date date_check = order_DetailVO.getCheckin_date();

					DailyBedVO dailyBedVO = dailyBedSvc.getDailyBedVOByFullDate(location_id_check, date_check);
					
						if (dailyBedVO.getRemaining_total() == 0) {
							errorMsgs.add(
									new LocationService().getOneLocation(dailyBedVO.getLocation_id()).getLocation_name()
											+ "於" + date + "的預訂床位數已滿!");
						}else if(order_DetailVO.getBed_num() != null) {
						
						if(dailyBedVO.getRemaining_total() < order_DetailVO.getBed_num()){
							errorMsgs.add(
									new LocationService().getOneLocation(dailyBedVO.getLocation_id()).getLocation_name()
											+ "於" + date + "的床位數剩餘"+dailyBedVO.getRemaining_total()+",不足以提供給您所預訂的床位數!");
						}
						
						}
					

				}
				
				
				
				
				

				if (errorMsgs.size() != 0) {
					System.out.println("errorMsgs com");
					req.setAttribute("errorCheckState", "fail");
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/room_order/Reserved_Bed.jsp");
					failureView.forward(req, res);
					return;
				}
				
				req.setAttribute("orderDetailCheck", list);
				

				Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

				String jsonStr = gson.toJson(list);

				System.out.println(jsonStr);

				System.out.println(date.length);
				System.out.println(location_id.length);

				String url = "/front-end/room_order/OrderDetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("預訂床位資料失敗" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/room_order/Reserved_Bed.jsp");
				failureView.forward(req, res);
			}

		}

		// user back to insert order
		if ("back".equals(action)) {
			System.out.println("come to back action...");
			try {

				List<Order_DetailVO> orderDetailCheck = (List<Order_DetailVO>) req.getSession()
						.getAttribute("orderDetailCheck");

				System.out.println("orderDetailCheck_sizes:" + orderDetailCheck.size());

				Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

				String jsonStr = gson.toJson(orderDetailCheck);

				System.out.println(jsonStr);

				String url = "/front-end/room_order/Reserved_Bed.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				e.printStackTrace();
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/room_order/Reserved_Bed.jsp");
				failureView.forward(req, res);

			}
		}

		if ("getDetails".equals(action)) {

			try {

				String order_id = req.getParameter("order_id");

				Order_DetailService odSvc = new Order_DetailService();

				List<Order_DetailVO> Order_DetailList = odSvc.getDetailsByOrder_id(order_id);

				req.setAttribute("Order_DetailList", Order_DetailList);

				boolean openModal = true;
				req.setAttribute("openModal", openModal);

				RequestDispatcher successView = req.getRequestDispatcher("/back-end/order_detail/OrderMge.jsp");
				successView.forward(req, res);
				return;

			} catch (Exception e) {
				throw new ServletException(e);
			}
		}

		if ("memberGetDetails".equals(action)) {

			try {

				String order_id = req.getParameter("order_id");

				Order_DetailService odSvc = new Order_DetailService();

				List<Order_DetailVO> Order_DetailList = odSvc.getDetailsByOrder_id(order_id);

				req.setAttribute("Order_DetailList", Order_DetailList);

				boolean openModal = true;
				req.setAttribute("openModal", openModal);

				RequestDispatcher successView = req.getRequestDispatcher("/front-end/order_detail/Order_Query.jsp");
				successView.forward(req, res);
				return;

			} catch (Exception e) {
				throw new ServletException(e);
			}
		}

		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				String order_id = req.getParameter("order_id");
				Integer order_status = new Integer(req.getParameter("order_status"));
				Integer payment_status;

				System.out.println(order_id + order_status);

				if (order_status == 2 || order_status == 3) {
					payment_status = 2;
				} else if (order_status == 4 || order_status == 5) {
					payment_status = 3;
				} else {
					payment_status = 1;
				}

				Room_orderService room_orderSvc = new Room_orderService();

				room_orderSvc.updateOrder_status(order_status, payment_status, order_id);

				
				
				//addBack dailyBed number
				if (order_status == 4 || order_status == 5) {
					DailyBedService dailyBedSvc = new DailyBedService();
					
					Order_DetailService odSvc = new Order_DetailService();
					List<Order_DetailVO> list = odSvc.getDetailsByOrder_id(order_id);
					for(Order_DetailVO order_DetailVO:list) {
						
						Integer reservedNumber = order_DetailVO.getBed_num();
						String  location_id = order_DetailVO.getLocation_id();
						java.sql.Date date = order_DetailVO.getCheckin_date();
						
						DailyBedVO dailyBedVO = dailyBedSvc.getDailyBedVOByFullDate(location_id, date);
						
						dailyBedSvc.cancelDailyBed(dailyBedVO.getDailyBed_id(), reservedNumber);
					}
				}
				

				StringBuilder sb = new StringBuilder();

				sb.append("{\"order_id\":\"" + order_id + "\",");

				sb.append("\"order_status\":\"" + order_status + "\"}");

				String order_idAndOrder_status = sb.toString();

				System.out.println(order_idAndOrder_status);

				PrintWriter out = res.getWriter();

				System.out.println("success");
				out.print(order_idAndOrder_status);
				out.flush();

				System.out.println("success");

//			RequestDispatcher successView = req
//					.getRequestDispatcher("/back-end/orderMge/OrderMge.jsp");
//			successView.forward(req, res);
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("更新訂單狀態失敗" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/order_detail/OrderMge.jsp");
				failureView.forward(req, res);

			}

		}

		if ("getAllByOrderStatus".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				System.out.println("getAllByOrderStatus come in");
				Integer order_status = Integer.parseInt(req.getParameter("order_status"));

				Room_orderService room_orderSvc = new Room_orderService();
				List<Room_orderVO> orderStatusList = room_orderSvc.getAllByOrderStatus(order_status);

				if (!errorMsgs.isEmpty()) {

					return;
				}

				req.setAttribute("orderStatusList", orderStatusList);

				RequestDispatcher successView = req.getRequestDispatcher("/back-end/order_detail/OrderStatus.jsp");
				successView.forward(req, res);

			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("篩選訂單狀態失敗" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/order_detail/OrderMge.jsp");
				failureView.forward(req, res);

			}

		}

		if ("getAllByPaymentStatus".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				System.out.println("getAllByPaymentStatus come in");
				Integer payment_status = Integer.parseInt(req.getParameter("payment_status"));

				Room_orderService room_orderSvc = new Room_orderService();
				List<Room_orderVO> paymentStatusList = room_orderSvc.getAllByPaymentStatus(payment_status);

				if (!errorMsgs.isEmpty()) {

					return;
				}

				req.setAttribute("paymentStatusList", paymentStatusList);

				RequestDispatcher successView = req.getRequestDispatcher("/back-end/order_detail/PaymentStatus.jsp");
				successView.forward(req, res);

			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("篩選訂單狀態失敗" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/order_detail/OrderMge.jsp");
				failureView.forward(req, res);

			}

		}

		if ("getMemberId".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				System.out.println("getMemberId come in");
				String member_id = req.getParameter("member_id");

				Room_orderService room_orderSvc = new Room_orderService();
				List<Room_orderVO> memberIdList = room_orderSvc.getAllByMember_id(member_id);

				if (!errorMsgs.isEmpty()) {

					return;
				}

				req.setAttribute("memberIdList", memberIdList);

				RequestDispatcher successView = req.getRequestDispatcher("/back-end/order_detail/OneMember.jsp");
				successView.forward(req, res);

			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("篩選訂單資料失敗" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/order_detail/OrderMge.jsp");
				failureView.forward(req, res);

			}

		}

		if ("getGroupId".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				System.out.println("getGroupId come in");
				String group_id = req.getParameter("group_id");

				Room_orderService room_orderSvc = new Room_orderService();
				List<Room_orderVO> groupIdList = room_orderSvc.getAllByGroup_id(group_id);

				if (!errorMsgs.isEmpty()) {

					return;
				}

				req.setAttribute("groupIdList", groupIdList);

				RequestDispatcher successView = req.getRequestDispatcher("/back-end/order_detail/OneGroup.jsp");
				successView.forward(req, res);

			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("篩選訂單資料失敗" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/order_detail/OrderMge.jsp");
				failureView.forward(req, res);

			}

		}

		if ("getDateRange".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				System.out.println("getDateRange come in");
				Integer date_range = Integer.parseInt(req.getParameter("date_range"));

				Room_orderService room_orderSvc = new Room_orderService();
				List<Room_orderVO> dateRangeList = room_orderSvc.getAllByDateRange(date_range);

				if (!errorMsgs.isEmpty()) {

					return;
				}

				req.setAttribute("dateRangeList", dateRangeList);

				RequestDispatcher successView = req.getRequestDispatcher("/back-end/order_detail/DateRange.jsp");
				successView.forward(req, res);

			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("篩選訂單資料失敗" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/order_detail/OrderMge.jsp");
				failureView.forward(req, res);

			}

		}

		if ("getAll".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				System.out.println("getAll come in");

				if (!errorMsgs.isEmpty()) {

					return;
				}

				RequestDispatcher successView = req.getRequestDispatcher("/back-end/order_detail/OrderMge.jsp");
				successView.forward(req, res);

			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("篩選訂單資料失敗" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/order_detail/OrderMge.jsp");
				failureView.forward(req, res);

			}

		}

	}

}
