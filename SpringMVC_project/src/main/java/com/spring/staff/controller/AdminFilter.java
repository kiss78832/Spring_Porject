package com.spring.staff.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminFilter implements Filter {
	
	private FilterConfig config;

	@Override
	public void destroy() {
		config = null;
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		HttpSession session = req.getSession();
		
		String admin = (String)session.getAttribute("admin");
		if(admin == null) {
			
			session.setAttribute("illegal", "you shall not pass!!!");
			
			 res.sendRedirect(config.getServletContext().getContextPath()+"/back-end/staff_Sign_In.jsp");
	    		return;//程式中斷
			
			
		}else {
			chain.doFilter(req, res);
		}
		
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
		
	}

}
