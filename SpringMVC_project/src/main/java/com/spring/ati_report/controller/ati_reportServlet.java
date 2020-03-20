package com.spring.ati_report.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.spring.ati_report.model.Ati_reportService;
import com.spring.ati_report.model.Ati_reportVO;

public class ati_reportServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);//轉交doPost
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");//進來伺服器的編碼
		String action = req.getParameter("action"); //接收請求name=action，為了進行比對
		
			/*************修改檢舉狀態*****************/		
	if("update_status".equals(action)) {
		System.out.println("進入修改檢舉狀態");
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs",errorMsgs);
		
		try {
			/*********請求參數************/
			String article_id = req.getParameter("article_id").trim();
			String sf_id = req.getParameter("sf_id").trim();
			//錯誤容器不為空就返回頁面，可回傳一個VO回去
			if(!errorMsgs.isEmpty()) {   
				RequestDispatcher failView = req.getRequestDispatcher("/back-end/ati_report/ati_report.jsp");
				failView.forward(req, res);
				return;
			}
			System.out.println("article_id測試"+article_id);
			
			/**********處理檢舉************/
			Ati_reportService atiSvc = new Ati_reportService();
			Ati_reportVO atiVO = atiSvc.getOneAti(article_id);
			System.out.println("atiVO測試"+atiVO);
			atiSvc.updateAti(atiVO.getReport_id(), article_id, sf_id, atiVO.getMember_id(), 2);
			System.out.println("修改狀態成功");
			
			/************成功準備轉交******/
			String url = "/back-end/ati_report/ati_report.jsp";
			res.sendRedirect(getServletContext().getContextPath()+url);
		}catch(Exception e) {
			System.out.println("處理失敗"+e.getMessage());
			errorMsgs.add("處理失敗:"+e.getMessage()); //在錯誤容器新增錯誤
			RequestDispatcher failView = req.getRequestDispatcher("/back-end/ati_report/ati_report.jsp");
			failView.forward(req, res);
		}
		
		
	}	
	/*************修改檢舉狀態(開放)*****************/	
	
	if("update_open".equals(action)) {
	System.out.println("進入修改檢舉狀態");
	List<String> errorMsgs = new LinkedList<String>();
	req.setAttribute("errorMsgs",errorMsgs);
	
	try {
		/*********請求參數************/
		String article_id = req.getParameter("article_id").trim();
		//錯誤容器不為空就返回頁面，可回傳一個VO回去
		if(!errorMsgs.isEmpty()) {   
			RequestDispatcher failView = req.getRequestDispatcher("/back-end/ati_report/ati_report.jsp");
			failView.forward(req, res);
			return;
		}
		System.out.println("article_id測試"+article_id);
	
	/**********處理檢舉************/
		Ati_reportService atiSvc = new Ati_reportService();
		Ati_reportVO atiVO = atiSvc.getOneAti(article_id);
		System.out.println("atiVO測試"+atiVO);
		atiSvc.deleteAti(article_id);
		System.out.println("修改狀態成功");
		
		/************成功準備轉交******/
		String url = "/back-end/ati_report/ati_report.jsp";
		res.sendRedirect(getServletContext().getContextPath()+url);
	}catch(Exception e) {
		System.out.println("處理失敗"+e.getMessage());
		errorMsgs.add("處理失敗:"+e.getMessage()); //在錯誤容器新增錯誤
		RequestDispatcher failView = req.getRequestDispatcher("/back-end/ati_report/ati_report.jsp");
		failView.forward(req, res);
	}
	
	
	}	
		
		
		
	}

	
}

