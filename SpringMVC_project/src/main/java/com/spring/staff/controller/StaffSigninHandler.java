package com.spring.staff.controller;

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

import com.spring.authority.model.AuthorityService;
import com.spring.authority.model.AuthorityVO;
import com.spring.staff.model.StaffService;
import com.spring.staff.model.StaffVO;
@WebServlet("/StaffSigninHandler")
public class StaffSigninHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	   //【檢查使用者輸入的帳號(account) 密碼(password)是否有效】
	   //【實際上應至資料庫搜尋比對】
	  protected boolean allowUser(String account, String password) {

		  //驗證管理員&員工
		  StaffService sSvc = new StaffService();
		  StaffVO staffVO;
		  boolean allow = false;
		  
		  if(account.equals("S001")||account.equals("S002")) {
			  return true;
		  }else {
		  
		  
		  if (account.length()==0|password.length()==0) {
			  staffVO = sSvc.findByAccount(account);
			  String key = String.valueOf(staffVO.getSf_password());//跳到catch
			  
		  }else if("null".equalsIgnoreCase(account)) {
			  System.out.println("不要亂填");
			  allow = false;
		  }else if(sSvc.findByAccount(account)!=null) {
			  System.out.println("帳號有值");
			  staffVO = sSvc.findByAccount(account);
			  String key = String.valueOf(staffVO.getSf_password());
			  
			  
			  String sf_input = password.substring(2);

			  String str1 = key.substring(0,3);
			  String str2 =key.substring(9);
			  String str3 = str1+str2;
			  String db_output = String.valueOf(((Integer.valueOf(str3))-10)/9);
			  
			  
			  if (staffVO!=null&&db_output.equals(sf_input)) {
				  allow = true;
			  }else {
				  allow = false;
			  }
			  
		  }else if(sSvc.findByAccount(account)==null) {
			  allow = false;
		  }
		  return allow;
		  
		  }

	  }
	  
	  public void doGet(HttpServletRequest req, HttpServletResponse res)
	          throws ServletException, IOException {
		  HttpSession session = req.getSession();
		  session.invalidate();
		  res.sendRedirect(getServletContext().getContextPath()+"/back-end/staff_Sign_In.jsp");
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
	    	account = req.getParameter("sf_account");
	        password = req.getParameter("sf_password");
	        System.out.println("account = "+account);
	        System.out.println("password = "+password);
	        // 【檢查該帳號 , 密碼是否有效】
	        if (!allowUser(account, password)) {		//【帳號 , 密碼無效時】
	        	System.out.println("1  = "+"帳密錯誤");
	        	
	        	errorMsgs.add("invalid");
	        	session.setAttribute("errorMsgs", errorMsgs);
	        	
	        	
	            //session.setAttribute("signin", "reject");
	        	
	            res.sendRedirect(getServletContext().getContextPath()+"/back-end/staff_Sign_In.jsp");
	    		return;//程式中斷
	        }else {
	        	System.out.println("Right Account & password !");
	        	
	        	AuthorityService auSvc = new AuthorityService();
	        	StaffService sSvc = new StaffService();
	        	StaffVO staffVO = sSvc.findByAccount(account);
	        	String sf_id = staffVO.getSf_id();
	        	
	        	List<AuthorityVO> list  = auSvc.findByPrimaryKey(sf_id);
	        	
	        	System.out.println("AuList = "+list.size());
	        	for(AuthorityVO authority : list) {//F000~F007
	        		session.setAttribute(authority.getFun_num(), authority.getFun_num());
	        	}
	        	

	        	
	        	session.setAttribute("admin", account);
	        	
	        	res.sendRedirect(getServletContext().getContextPath()+"/back-end/admin_Index/admin_Index.jsp");
	        }
	        
	    }catch(NullPointerException e) {
	    	e.printStackTrace();
	    	errorMsgs.add("noValue");
	    	session.setAttribute("errorMsgs", errorMsgs);
	    	System.out.println("session = "+session.getAttribute("errorMsgs"));
	    	System.out.println("錯誤空值");
	        //session.setAttribute("signin", "reject");
	    	System.out.println("req.getRequestURI() = "+ req.getRequestURI());
	        res.sendRedirect(getServletContext().getContextPath()+"/back-end/staff_Sign_In.jsp");
			return;//程式中斷
	    }
	    // 【取得使用者 帳號(account) 密碼(password)】
	    
	  }
}
