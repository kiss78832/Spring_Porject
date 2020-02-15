package com.info.controller;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.info.model.InfoService;
import com.info.model.InfoVO;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class InfoServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("進來了");
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

//-----------------------------------------刪除---------------------------------------------------------		

		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String route_ID = req.getParameter("route_ID").trim();

				/*************************** 2.開始刪除資料 ***************************************/
				InfoService infoSvc = new InfoService();
				infoSvc.deleteInfo(route_ID);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/route/Route_Info.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/route/Route_Info.jsp");
				failureView.forward(req, res);
			}
		}

//-----------------------------------------新增---------------------------------------------------------		

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();// 將此集存儲在請求範圍中，以備我們需要時使用
			req.setAttribute("errorMsgs", errorMsgs);// 發送ErrorPage視圖。

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				// 無須驗證，因為抓別人資料，已驗證
				String route_ID = req.getParameter("route_ID").trim();
				String route_idReg = "^R[0-9]{3,5}$";
				if (route_ID == null || route_ID.trim().length() == 0) {
					errorMsgs.add("路線編號: 請勿空白");
				} else if (!route_ID.trim().matches(route_idReg)) {
					errorMsgs.add("路線編號: 請用R開頭的編號+數字");
				}

				String route_Name = req.getParameter("route_Name");
				Timestamp open_Time = java.sql.Timestamp.valueOf(req.getParameter("open_Time"));

				Integer open_Status = new Integer(req.getParameter("open_Status"));
				String open_StatusReg = "^[0-1]{1}$";
				if (!open_Status.toString().trim().matches(open_StatusReg)) {
					errorMsgs.add("狀態碼請輸入0或1");
				}

				// 打包錯誤的資料回傳給客戶端
				InfoVO infoVO = new InfoVO();
				infoVO.setRoute_ID(route_ID);
				infoVO.setRoute_Name(route_Name);
				infoVO.setOpen_Time(open_Time);
				infoVO.setOpen_Status(open_Status);

				System.out.println(route_ID);
				System.out.println(route_Name);
				System.out.println(open_Time);
				System.out.println(open_Status);

				// 如果有錯誤，請將用途發回表單
				if (!errorMsgs.isEmpty()) {
					System.out.println("錯誤了拉");
					System.out.println(errorMsgs);
					req.setAttribute("infoVO", infoVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/route/Route_Info.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				InfoService infoSvc = new InfoService();
				infoVO = infoSvc.addInfo(route_ID, route_Name, open_Time, open_Status);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/route/Route_Info.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/route/Route_Info.jsp");
				failureView.forward(req, res);
			}
		}

//-----------------------------------------修改---------------------------------------------------------		

		if ("getOne_For_Update".equals(action)) { 
			System.out.println("嘿嘿");
			List<String> errorMsgs = new LinkedList<String>();// 將此集存儲在請求範圍中，以備我們需要時使用
			req.setAttribute("errorMsgs", errorMsgs);// 發送ErrorPage視圖。

			String route_ID = req.getParameter("route_ID").trim();
			System.out.println(route_ID);
			InfoVO infoVO = new InfoVO();
			infoVO.setRoute_ID(route_ID);



			try {
				/***************************2.開始查詢資料****************************************/
				InfoService infoSvc = new InfoService();
				InfoVO infoVO2 = infoSvc.getOneInfo(route_ID);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("infoVO", infoVO2);         // 資料庫取出的empVO物件,存入req
				String url = "/back-end/route/update_Info.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/route/Route_Info.jsp");
				failureView.forward(req, res);
			}
		}

		
//-----------------------------------------修改update---------------------------------------------------------		


		if ("update".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();// 將此集存儲在請求範圍中，以備我們需要時使用
			req.setAttribute("errorMsgs", errorMsgs);// 發送ErrorPage視圖。

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				System.out.println("進來了1");
				// 無須驗證，因為抓別人資料，已驗證
				String route_ID = req.getParameter("route_ID").trim();
				System.out.println(route_ID);
				String route_idReg = "^R[0-9]{3,5}$";
				if (route_ID == null || route_ID.trim().length() == 0) {
					System.out.println("進來了2-1");
					errorMsgs.add("路線編號: 請勿空白");
				} else if (!route_ID.trim().matches(route_idReg)) {
					System.out.println("進來了2-2");
					errorMsgs.add("路線編號: 請用R開頭的編號+數字");
				}
				System.out.println("進來了2");
				String route_Name = req.getParameter("route_Name");
				if (route_Name == null || route_Name.trim().length() == 0) {
					errorMsgs.add("路線編號: 請勿空白");
				}
				System.out.println("進來了3");
				Timestamp open_Time = null;
				  

					try {
						
						open_Time = java.sql.Timestamp.valueOf(req.getParameter("open_Time").trim());
					} catch (IllegalArgumentException e) {
						open_Time=new java.sql.Timestamp(System.currentTimeMillis());
						errorMsgs.add("請輸入正確日期格式!");
					}
					
					System.out.println("進來了4");
					
				Integer open_Status = new Integer(req.getParameter("open_Status"));
				String open_StatusReg = "^[0-1]{1}$";
				if (!open_Status.toString().trim().matches(open_StatusReg)) {
					errorMsgs.add("狀態碼請輸入0或1");
				}
				

				// 打包錯誤的資料回傳給客戶端
				InfoVO infoVO = new InfoVO();
				infoVO.setRoute_ID(route_ID);
				infoVO.setRoute_Name(route_Name);
				infoVO.setOpen_Time(open_Time);
				infoVO.setOpen_Status(open_Status);

				System.out.println(route_ID);
				System.out.println(route_Name);
				System.out.println(open_Time);
				System.out.println(open_Status);

				// 如果有錯誤，請將用途發回表單
				if (!errorMsgs.isEmpty()) {
					System.out.println("錯誤了拉");
					System.out.println(errorMsgs);
					req.setAttribute("infoVO", infoVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/route/update_Info.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				InfoService infoSvc = new InfoService();
				infoVO = infoSvc.updateInfo(route_ID, route_Name, open_Time, open_Status);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/route/Route_Info.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/route/update_Info.jsp");
				failureView.forward(req, res);
			}
		}
		
		
	}
}