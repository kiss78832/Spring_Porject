package com.memberinfo.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.member.model.*;
import com.memberinfo.model.*;

public class MemberinfoServlet extends HttpServlet{
		
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

			if("findByMemberId".equals(action)) {//listAll資料
				List<String> erroMsgs = new LinkedList<String>();
				req.setAttribute("erroMsgs", erroMsgs);
					
				try {
				/***************************1.接收請求參數****************************************/
					MemberVO memberVO = (MemberVO) req.getSession().getAttribute("memberVO");
					String member_id = req.getParameter("member_id");
					String group_id = req.getParameter("group_id");
				/***************************2.開始查詢資料****************************************/
					MemberService memSvc = new MemberService();
					MemberJDBCDAO dao = new MemberJDBCDAO();
					
//					MemberVO memberVO = memSvc.findByMemberId(member_id);
					System.out.println(member_id);
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
					req.setAttribute("memberVO", memberVO);
					req.setAttribute("group_id", group_id);
					String url = "/front-end/group/groupMember/memberJoin_Page.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 groupUp.jsp
					successView.forward(req, res);
			   /***************************其他可能的錯誤處理**********************************/
			   }catch(Exception e) {
				   erroMsgs.add("無法取得要修改的資料:" + e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/group/joinGroupList");
					failureView.forward(req, res);
			   }
			}
			/*****************************抓取資料***************************************/
			
			
			
			
			/*****************************新增資料***************************************/
			if ("insert".equals(action)) { 
				
				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);
				System.out.println("我進來了");
				try {
					/***********************1.接收請求參數 *************************/
					String group_id = req.getParameter("group_id").trim();
					MemberVO memberVO = (MemberVO) req.getSession().getAttribute("memberVO");
					
					String m_name = req.getParameter("m_name");
					
					java.sql.Date birthday = null;
					try {
						birthday = java.sql.Date.valueOf(req.getParameter("birthday").trim());
					}catch(IllegalArgumentException e) {
						birthday = new java.sql.Date(System.currentTimeMillis());
						errorMsgs.add("請輸入生日");
					}
					
					String cellphone = req.getParameter("cellphone");
					
					String m_email = req.getParameter("m_email");
					
					String address = req.getParameter("address");
					if (address == null || address.trim().length() == 0) {
						errorMsgs.add("地址請勿空白");
					}
					
					String identity = req.getParameter("identity");
					if(identity == null || identity.trim().length() == 0) {
						errorMsgs.add("身分證請勿空白");
					}
					
					String egc_contact = req.getParameter("egc_contact");
					if(egc_contact == null || egc_contact.trim().length() == 0) {
						errorMsgs.add("緊急聯絡人請勿空白");
					}
					
					String egc_phone = req.getParameter("egc_phone");
					if(egc_phone == null || egc_phone.trim().length() == 0) {
						errorMsgs.add("緊急聯絡人電話勿空白");
					}
					
					
					MemberinfoVO memberinfoVO = new MemberinfoVO();
					memberinfoVO.setGroup_id(group_id);
					memberinfoVO.setMember_id(memberVO.getMember_id());
					memberinfoVO.setAddress(address);
					memberinfoVO.setIdentity(identity);
					memberinfoVO.setEgc_contact(egc_contact);
					memberinfoVO.setEgc_phone(egc_phone);
					
					
					System.out.println(group_id);
					System.out.println(memberVO.getMember_id());
					System.out.println(address);
					System.out.println(identity);
					System.out.println(egc_contact);
					System.out.println(egc_phone);
					
				
					/***************************2.開始新增資料***************************************/
					MemberinfoService meminfoSvc = new MemberinfoService();
					memberinfoVO = meminfoSvc.addMemberinfo(group_id, memberVO.getMember_id(), address, identity, egc_contact, egc_phone, m_name, birthday, cellphone, m_email);
					System.out.println("進來了1");
					/***************************3.新增完成,準備轉交(Send the Success view)***********/
					String url = "/front-end/group/joinGroupList.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交
					successView.forward(req, res);				
					System.out.println("進來了2");
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					e.printStackTrace();
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/group/groupMember/memberJoin_Page.jsp");
					failureView.forward(req, res);
				}
			}
	}
}
