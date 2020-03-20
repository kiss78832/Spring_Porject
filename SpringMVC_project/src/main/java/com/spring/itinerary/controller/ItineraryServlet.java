package com.spring.itinerary.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ItineraryServlet extends HttpServlet{
	
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
			
			req.setCharacterEncoding("UTF-8");
			
			String action = req.getParameter("action");
		
			System.out.println(action);
			
			
			/*****************************抓取資料***************************************/

//			if("findByMemberId".equals(action)) {//listAll資料
//				List<String> erroMsgs = new LinkedList<String>();
//				req.setAttribute("erroMsgs", erroMsgs);
//					
//				try {
//				/***************************1.接收請求參數****************************************/
//					
//				/***************************2.開始查詢資料****************************************/
//					
//				/***************************3.查詢完成,準備轉交(Send the Success view)************/
//					
//			   /***************************其他可能的錯誤處理**********************************/
//			   }catch(Exception e) {
//				   erroMsgs.add("無法取得要修改的資料:" + e.getMessage());
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/front-end/group/joinGroupList");
//					failureView.forward(req, res);
//			   }
//			}
//			/*****************************抓取資料***************************************/
	}
}