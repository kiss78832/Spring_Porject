package com.spring.message.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.spring.article.model.ArticleService;
import com.spring.message.model.MessageService;
import com.spring.message.model.MessageVO;

public class MessageServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
//----------------------------------------------新增-------------------------------------------------------------		
		
				if ("insert_msg".equals(action)) { // 來自addEmp.jsp的請求
					System.out.println("進來了新增留言");
					List<String> errorMsgs = new LinkedList<String>();// 將此集存儲在請求範圍中，以備我們需要時使用
					req.setAttribute("errorMsgs", errorMsgs);// 發送ErrorPage視圖。
					
					try {
						/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
						// 無須驗證，因為抓別人資料，已驗證
						
						String member_id = req.getParameter("member_id").trim();
						String msg_content = req.getParameter("msg_content").trim();
						String article_id = req.getParameter("article_id").trim();	
						System.out.println("留言article_id"+article_id);
						System.out.println("留言member_id"+member_id);
						System.out.println("留言msg_content"+msg_content);

						
						System.out.println("資料驗證成功");
						MessageVO messageVO = new MessageVO();

						messageVO.setMember_id(member_id);
						messageVO.setArticle_id(article_id);
						messageVO.setMsg_content(msg_content);
				
//						ArticleVO articleVO1 = new ArticleVO();
//						articleVO1.setArticle_id(article_id);
						

						// 如果有錯誤，請將用途發回表單
						if (!errorMsgs.isEmpty()) {
							System.out.println("錯誤了拉");
							System.out.println(errorMsgs);
							req.setAttribute("messageVO", messageVO); // 含有輸入格式錯誤的empVO物件,也存入req
							RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article/article_content.jsp");
							failureView.forward(req, res);
							return;
						}

						/*************************** 2.開始新增資料 ***************************************/
						MessageService messageSvc = new MessageService();
						messageVO = messageSvc.addMsg(member_id, article_id, msg_content);
						req.setAttribute("messageVO", messageVO);
						
						ArticleService articleSvc = new ArticleService();
						req.setAttribute("article_id", article_id);

						/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
						System.out.println("留言新增成功");
						String url = "/front-end/article/article_content.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
						successView.forward(req, res);

						/*************************** 其他可能的錯誤處理 **********************************/
					} catch (Exception e) {
						System.out.println("錯誤");
						errorMsgs.add(e.getMessage());
						RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article/article_content.jsp");
						failureView.forward(req, res);
					}
				}	
				
					
		
	}
		
}