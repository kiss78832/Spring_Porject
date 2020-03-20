package com.spring.group.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.spring.application.model.ApplicationVO;
import com.spring.group.model.GroupService;
import com.spring.group.model.GroupVO;
import com.spring.itinerary.model.ItineraryService;
import com.spring.itinerary.model.ItineraryVO;
import com.spring.member.model.MemberVO;
import com.spring.memberinfo.model.MemberinfoService;
import com.spring.memberinfo.model.MemberinfoVO;

public class GroupServlet extends HttpServlet{
	
	
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		System.out.println(action);
		
		if("insertGroup".equals(action)) {
//			錯誤訊息存到List<String>顯示在View上
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
		try {
			
			/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
			String gp_leader = req.getParameter("gp_leader");
			String gp_name = req.getParameter("gp_name").trim();
			MemberVO memberVO = (MemberVO) req.getSession().getAttribute("memberVO");
			
			String nameReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if(gp_name == null || gp_name.trim().length() == 0) {
				errorMsgs.add("隊伍名稱勿空白");
			}else if(!gp_name.trim().matches(nameReg)) {
				errorMsgs.add("隊伍名稱: 只能是中、英文字母、數字和_ , 且長度必需在4到10之間");
			}
			
			
			java.sql.Date first_day = null;
			try {
				first_day = java.sql.Date.valueOf(req.getParameter("first_day").trim());
			}catch(IllegalArgumentException e) {
				first_day = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入入園日期");
			}
			
			Integer gp_nbp = null;
			try {
				gp_nbp = new Integer(req.getParameter("gp_nbp").trim());
			}catch(NumberFormatException e) {
				gp_nbp = 0;
				errorMsgs.add("請選擇人數");
			}
			
			
			String satellite = null;
			try {
				satellite = new String(req.getParameter("satellite").trim());
			}catch(NumberFormatException e) {
				errorMsgs.add("衛星電話");
			}
			
			
			String radio = null;
			try {
				radio = new String(req.getParameter("radio").trim());
			}catch(NumberFormatException e) {
				errorMsgs.add("無線電頻");
			}
			
			String target_loca = req.getParameter("target_loca").trim();
			
			
			
			
			String route_id = null;
			try {
				route_id = new String(req.getParameter("route_id").trim());
			}catch(NumberFormatException e) {

			}
			
		
			GroupVO groupVO = new GroupVO();
			
			groupVO.setGp_name(gp_name);
			groupVO.setFirst_day(first_day);

			groupVO.setGp_nbp(gp_nbp);
			groupVO.setSatellite(satellite);
			groupVO.setRadio(radio);
			groupVO.setTarget_loca(target_loca);
			groupVO.setRoute_id(route_id);
			System.out.println(route_id);
			if (!errorMsgs.isEmpty()) {
				System.out.println("error");
				req.setAttribute("groupVO", groupVO); // 含有輸入格式錯誤的groupVO物件,也存入req
				 
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/group/groupMember/groupUp.jsp");
				failureView.forward(req, res);
				return;
			}
			
			/******************************行程***********************************/
			
			String group_id = req.getParameter("group_id");
			
			Integer total_day = null;
			try {
				total_day = new Integer(req.getParameter("total_day").trim());
			}catch(NumberFormatException e) {
				errorMsgs.add("請選擇天數");
			}
			String trip_break =req.getParameter("trip_break");
			String trip_schedule = req.getParameter("trip_schedule");
			
			ItineraryVO itineraryVO = new ItineraryVO();
			itineraryVO.setGroup_id(group_id);
			itineraryVO.setTotal_day(total_day);
			itineraryVO.setTrip_schedule(trip_schedule);
			itineraryVO.setTrip_break(trip_break);
			System.out.println(total_day);
			System.out.println("trip_schedule:"+trip_schedule);
			
			
			
			
			/***************************開始新增資料***************************************/
										 
							/**********揪團***********/
			GroupService groupSvc = new GroupService();
			groupVO = groupSvc.addgroupData(memberVO.getMember_id(), gp_name, 1, gp_nbp, satellite, radio, 0, 0, 0, first_day, target_loca, route_id);
			System.out.println("GroupVO_ID : "+groupVO.getGroup_id());
			
							/************行程***********/
			ItineraryService itinerarySvc = new ItineraryService();
			itineraryVO = itinerarySvc.addItinerary(groupVO.getGroup_id(), total_day, trip_break, trip_schedule);
			
			               /************行程***********/
			MemberinfoService meminfoSvc = new MemberinfoService();
			MemberinfoVO memberinfoVO = meminfoSvc.insertForLeader(groupVO.getGroup_id(), memberVO.getMember_id());

			
			/***************************新增完成,準備轉交(Send the Success view)***********/
			String url = "/front-end/group/groupLeader/groupList.jsp";
			res.sendRedirect(getServletContext().getContextPath()+url);
//			RequestDispatcher successView = req.getRequestDispatcher(url);	
//			successView.forward(req, res);

			/***************************其他可能的錯誤處理**********************************/			
			}catch(Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = 
						req.getRequestDispatcher("/front-end/group/groupMember/groupUp.jsp");
				failureView.forward(req, res);
				return;
			}
		}
		
		
		
		/*****************************抓取資料***************************************/

		if("getOne_For_Update".equals(action)) {//listAll資料
			List<String> erroMsgs = new LinkedList<String>();
			req.setAttribute("erroMsgs", erroMsgs);
				
			try {
			/***************************1.接收請求參數****************************************/
				String group_id = req.getParameter("group_id");
			/***************************2.開始查詢資料****************************************/
				
				GroupService groupSvc = new GroupService();
				GroupVO groupVO = groupSvc.getOneGroupById(group_id);
				ItineraryService itinerarySve = new ItineraryService();
				ItineraryVO itineraryVO = itinerarySve.findByGroup(group_id);
				System.out.println(groupVO);
			/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("groupVO", groupVO);
				req.setAttribute("itineraryVO", itineraryVO);
				String url = "/front-end/group/groupLeader/update_group.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 groupUp.jsp
				successView.forward(req, res);
		   /***************************其他可能的錯誤處理**********************************/
		   }catch(Exception e) {
			   erroMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/group/joinGroup.jsp");
				failureView.forward(req, res);
		   }
		}

		
		/*****************************團主查看詳細資訊***************************************/
		
		if("detailed".equals(action)) {
			List<String> erroMsgs = new LinkedList<String>();
			req.setAttribute("erroMsgs", erroMsgs);
				
			try {
			/***************************1.接收請求參數****************************************/
				
				String group_id = req.getParameter("group_id");
			/***************************2.開始查詢資料****************************************/
				ItineraryService itinerarySve = new ItineraryService();
				ItineraryVO itineraryVO = itinerarySve.findByGroup(group_id);
				
				GroupService groupSvc = new GroupService();
				GroupVO groupVO = groupSvc.getOneGroupById(group_id);
				
				
				System.out.println(itineraryVO.getTrip_break());
				System.out.println(itineraryVO);
				System.out.println(groupVO);
			/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("itineraryVO", itineraryVO);
				req.setAttribute("groupVO", groupVO);				
				String url = "/front-end/group/groupLeader/Detailed.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 groupUp.jsp
				successView.forward(req, res);
		   /***************************其他可能的錯誤處理**********************************/
		   }catch(Exception e) {
			   erroMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/group/groupLeader/groupList.jsp");
				failureView.forward(req, res);
		   }
		}
		
		
			/*****************************團主揪團紀錄*************************************/
		
		if("groupUphistory".equals(action)) {//listAll資料
			List<String> erroMsgs = new LinkedList<String>();
			req.setAttribute("erroMsgs", erroMsgs);
				
			try {
			/***************************1.接收請求參數****************************************/
				
				String group_id = req.getParameter("group_id");
			/***************************2.開始查詢資料****************************************/
				
				GroupService groupSvc = new GroupService();
				GroupVO groupVO = groupSvc.getOneGroupById(group_id);
				System.out.println(groupVO);
			/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("groupVO", groupVO);
				String url = "/front-end/group/groupLeader/groupUp_History.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 groupUp.jsp
				successView.forward(req, res);
		   /***************************其他可能的錯誤處理**********************************/
		   }catch(Exception e) {
			   erroMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/group/groupLeader/groupList.jsp");
				failureView.forward(req, res);
		   }
		}
		
		
			/*****************************會員查看詳細資訊***************************************/
		
		if("memberdetailed".equals(action)) {//listAll資料
			List<String> erroMsgs = new LinkedList<String>();
			req.setAttribute("erroMsgs", erroMsgs);
				
			try {
			/***************************1.接收請求參數****************************************/
				System.out.println("我也進來了");
				String group_id = req.getParameter("group_id");
			/***************************2.開始查詢資料****************************************/
				GroupService groupSvc = new GroupService();
				GroupVO groupVO = groupSvc.memberByGroupId(group_id);
				
				ItineraryService itinerarySve = new ItineraryService();
				ItineraryVO itineraryVO = itinerarySve.findByGroup(group_id);
				
				System.out.println(itineraryVO);
				System.out.println(groupVO);
			/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("groupVO", groupVO);
				req.setAttribute("itineraryVO", itineraryVO);
				String url = "/front-end/group/memberJoinGroupDetailed.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 memberJoin_info.jsp
				successView.forward(req, res);
		   /***************************其他可能的錯誤處理**********************************/
		   }catch(Exception e) {
			   erroMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/group/joinGroupList.jsp");
				failureView.forward(req, res);
		   }
		}
		
		
		
		
		
		
		/***********************************更新資料********************************************/
		if("updateGroup".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String group_id = req.getParameter("group_id").trim();
				String gp_name = req.getParameter("gp_name");
				String groupReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if(gp_name == null ||gp_name.trim().length() == 0) {
					errorMsgs.add("團名: 請勿空白");
				}else if(!gp_name.trim().matches(groupReg)) {
					errorMsgs.add("團名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}
				
				Integer gp_nbp = null;
				try {
					gp_nbp = new Integer(req.getParameter("gp_nbp").trim());
				}catch (NumberFormatException e) {
					gp_nbp = 0;
					errorMsgs.add("揪團人數請填數字.");
				}
				
				
				java.sql.Date first_day = null;
				try {
					first_day = java.sql.Date.valueOf(req.getParameter("first_day").trim());
				}catch(IllegalArgumentException e) {
					first_day = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入入園日期");
				}
				
//				java.sql.Date end_day = null;
//				try {
//					end_day = java.sql.Date.valueOf(req.getParameter("end_day").trim());
//				} catch (IllegalArgumentException e) {
//					end_day = new java.sql.Date(System.currentTimeMillis());
//					errorMsgs.add("請輸入離開日期");
//				}
				
				String satellite = null;
				try {
					satellite = new String(req.getParameter("satellite").trim());
				}catch(NumberFormatException e) {
					errorMsgs.add("衛星電話");
				}
				
				
				String radio = null;
				try {
					radio = new String(req.getParameter("radio").trim());
				}catch(NumberFormatException e) {
					errorMsgs.add("衛星電話");
				}
				
				String target_loca =req.getParameter("target_loca");
				
				
				GroupVO groupVO = new GroupVO();
				
				groupVO.setGp_name(gp_name);
				groupVO.setFirst_day(first_day);
				groupVO.setGp_nbp(gp_nbp);
				groupVO.setSatellite(satellite);
				groupVO.setRadio(radio);
				groupVO.setMeal_provided(gp_nbp);
				groupVO.setTarget_loca(target_loca);
					
				if (!errorMsgs.isEmpty()) {
					System.out.println("error");
					req.setAttribute("groupVO", groupVO); // 含有輸入格式錯誤的groupVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/group/groupLeader/update_group.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************開始新增資料***************************************/
				GroupService groupSvc = new GroupService();
				groupVO = groupSvc.updateGroup(group_id, gp_name, gp_nbp, satellite, radio, first_day, target_loca);
				
				/**************************修改完成,準備轉交(Send the Success view)***********/
				req.setAttribute("groupVO", groupVO);
				String url = "/front-end/group/groupLeader/groupList.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);	
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
//				
				}catch(Exception e) {
					e.printStackTrace();
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = 
							req.getRequestDispatcher("/front-end/group/groupLeader/update_group.jsp");
					failureView.forward(req, res);
					return;
				}
			}
		    /*****************************更新資料***************************************/
		
		
		/*****************************更改揪團狀態***************************************/
		
		if("updateStatus".equals(action)) {
			List<String> erroMsgs = new LinkedList<String>();
			req.setAttribute("erroMsgs", erroMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String group_id = req.getParameter("group_id").trim();
				GroupVO groupVO = new GroupVO();

			
				if (!erroMsgs.isEmpty()) {
					System.out.println("error");
					req.setAttribute("groupVO", groupVO); // 含有輸入格式錯誤的groupVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/group/groupMember/groupUp.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************開始新增資料***************************************/
				GroupService groupSvc = new GroupService();
				groupVO = groupSvc.updateStatus(group_id, 2);
				
				/**************************修改完成,準備轉交(Send the Success view)***********/
				req.setAttribute("groupVO", groupVO);
				String url = "/front-end/group/groupLeader/groupList.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);	
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
//				
				}catch(Exception e) {
					e.printStackTrace();
					erroMsgs.add(e.getMessage());
					RequestDispatcher failureView = 
							req.getRequestDispatcher("/front-end/group/groupLeader/groupList.jsp");
					failureView.forward(req, res);
					return;
				}
			}
		
		
		
		/***********************************送出揪團********************************************/
		if("insterApplication".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("erroMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String group_id = req.getParameter("group_id").trim();
				
				String gp_leader = req.getParameter("gp_leader").trim();

				String satellite = req.getParameter("satellite");
				
				String radio = req.getParameter("radio");
				
				Integer dailybed_provided = null;
				try {
					dailybed_provided = new Integer(req.getParameter("gp_nbp").trim());
				}catch(NumberFormatException e) {

				}
			
				Integer meal_provided = null;
				try {
					meal_provided = new Integer(req.getParameter("gp_nbp").trim());
				}catch(NumberFormatException e) {
				
				}
				
				Integer gp_nbp = null;
				try {
					gp_nbp = new Integer(req.getParameter("gp_nbp").trim());
				}catch(NumberFormatException e) {
				}
				
				java.sql.Date first_day = null;
				try {
					first_day = java.sql.Date.valueOf(req.getParameter("first_day").trim());
				}catch(IllegalArgumentException e) {
					first_day = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入入園日期");
				}
				
				String trip_break =req.getParameter("trip_break");
				String locations = trip_break.trim();
				String route_id = req.getParameter("route_id");
				
				System.out.println("==========================================");

				
				
				GroupVO groupVO = new GroupVO();
				ApplicationVO applicationVO = new ApplicationVO();
				applicationVO.setGroup_id(group_id);
				applicationVO.setMember_id(gp_leader);
				applicationVO.setSatellite(satellite);
				applicationVO.setRadio(radio);
				applicationVO.setFirst_day(first_day);
				applicationVO.setRoute_id(route_id);
				System.out.println(route_id);
				System.out.println("group_id"+group_id);
				System.out.println("gp_leader"+gp_leader);
				if (!errorMsgs.isEmpty()) {
					System.out.println("error");
					req.setAttribute("groupVO", groupVO); // 含有輸入格式錯誤的groupVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/group/groupLeader/update_group.jsp");
					failureView.forward(req, res);
					return;
				}
				 
				/***************************開始新增資料***************************************/
				GroupService groupSvc = new GroupService();
				applicationVO = groupSvc.insterApplication(group_id, applicationVO.getMember_id(), route_id, 0, satellite, radio, 2, dailybed_provided, meal_provided, first_day, locations);
				groupVO = groupSvc.changeGroup(group_id, 4);//送出時改審核狀態
				/**************************修改完成,準備轉交(Send the Success view)***********/
				
				req.setAttribute("groupVO", groupVO);
//				String url = "/front-end/group/groupLeader/groupList.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);	
//				successView.forward(req, res);
				String url = "/front-end/group/groupLeader/groupList.jsp";
				res.sendRedirect(getServletContext().getContextPath()+url);
				/***************************其他可能的錯誤處理**********************************/

				}catch(Exception e) {
					e.printStackTrace();
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = 
							req.getRequestDispatcher("/front-end/group/groupLeader/update_group.jsp");
					failureView.forward(req, res);
					return;
				}
			}
	}
}
