package com.spring.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.spring.member.model.MemberService;
import com.spring.member.model.MemberVO;

@WebServlet("/signinhandler")
public class SigninHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

   //【檢查使用者輸入的帳號(account) 密碼(password)是否有效】
   //【實際上應至資料庫搜尋比對】
  protected boolean allowUser(String account, String password) {

	  //驗證管理員&員工
	  //會員
	  MemberService memSvc = new MemberService();
	  MemberVO memberVO;
	  boolean allow = false;
	  
	  
	  if("null".equalsIgnoreCase(account)) {
		  System.out.println("帳號錯誤");
		  allow = false;
	  }else if (account.length()==0||password.length()==0) {
		  memberVO = memSvc.getOneMember(account);
		  String key = String.valueOf(memberVO.getPassword());//跳到catch
	  }else if(memSvc.getOneMember(account)!=null) {
		  memberVO = memSvc.getOneMember(account);
		  String key = String.valueOf(memberVO.getPassword());
		  if (memberVO!=null&&key.equals(password)) {
			  allow = true;
		  }else {
			  allow = false;
		  }
	  }else if(memSvc.getOneMember(account)==null) {
		  allow = false;
	  }
	  
	  

      return allow;
  }
  
  public void doGet(HttpServletRequest req, HttpServletResponse res)
          throws ServletException, IOException {
	  HttpSession session = req.getSession();
	  session.invalidate();
	  res.sendRedirect(getServletContext().getContextPath()+"/front-end/welcome/index.jsp");
	  System.out.println("登出");
  }
  
  public void doPost(HttpServletRequest req, HttpServletResponse res)
                                throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");
    res.setContentType("text/html; charset=UTF-8");
    HttpSession session = req.getSession();
    PrintWriter out = res.getWriter();
    List<String> errorMsgs = new LinkedList<String>();
    
    String account;
    String password;
    try {
    	account = req.getParameter("member_id");
        password = req.getParameter("password");

        // 【檢查該帳號 , 密碼是否有效】
        if (!allowUser(account, password)) {		//【帳號 , 密碼無效時】
        	System.out.println("帳密錯誤");
        	
        	errorMsgs.add("invalid");
        	session.setAttribute("errorMsgs", errorMsgs);
        	

        	String location = (String)session.getAttribute("location");
        	System.out.println("location = "+location);
        	if(location==null) {
        		res.sendRedirect(getServletContext().getContextPath()+"/front-end/welcome/index.jsp");
        	}else {
        		res.sendRedirect(location);
        	}
            
    		return;//程式中斷


        }else {                                       //【帳號 , 密碼有效時, 才做以下工作】
        	System.out.println("帳密正確");
			session.removeAttribute("errorMsgs");

          
			session.setAttribute("account", account);   //*工作1: 才在session內做已經登入過的標識
			
			MemberService memSvc = new MemberService();
			MemberVO memberVO = memSvc.getOneMember(account);
			session.setAttribute("memberVO", memberVO);
			session.setAttribute("signupSuccess", "true");
           try {
             String location = (String) session.getAttribute("location");
             if (location != null) {
            	 System.out.println("有來源網頁");
               session.removeAttribute("location");   //*工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
               res.sendRedirect(location);            
               return;
             }
           }catch (Exception ignored) { }
           System.out.println("無來源網頁");
           res.sendRedirect(getServletContext().getContextPath()+"/front-end/welcome/index.jsp");
        }
    }catch(NullPointerException e) {
    	e.printStackTrace();
    	session.setAttribute("errorMsgs", errorMsgs);
    	errorMsgs.add("noValue");
    	//
        //session.setAttribute("signin", "reject");
    	
    	res.sendRedirect(getServletContext().getContextPath()+"/front-end/welcome/index.jsp");
		return;//程式中斷
    }
    // 【取得使用者 帳號(account) 密碼(password)】
    
  }
}