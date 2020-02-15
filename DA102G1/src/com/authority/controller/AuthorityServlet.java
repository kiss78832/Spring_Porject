package com.authority.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.authority.model.AuthorityService;

public class AuthorityServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = res.getWriter();
		
		String action = req.getParameter("action");
		

		System.out.println("I'm in the AuthorityServlet !");
		
		if("authChange".equals(action)) {
			String sf_id = req.getParameter("sf_id");
			HttpSession session = req.getSession();
			AuthorityService dao = new AuthorityService();
			dao.deleteAuthority(sf_id);
			
			
			Enumeration enu = req.getParameterNames();
			while(enu.hasMoreElements()) {
				String name = (String)enu.nextElement();
				String[] values = req.getParameterValues(name);
				
				if(values!=null) {
					for (int i = 0; i < values.length; i++) {
						
						if(values[i].contains("F")) {
							dao.addAuthority(sf_id, values[i]);
						}
					}
				}
			}
			session.setAttribute("update", "success");
			res.sendRedirect(getServletContext().getContextPath()+"/back-end/staff/listAllStaff.jsp");
		}
	}

	
	
}
