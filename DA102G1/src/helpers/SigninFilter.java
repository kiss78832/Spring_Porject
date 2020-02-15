package helpers;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class SigninFilter implements Filter {

	private FilterConfig config;

	public void init(FilterConfig config) {
		this.config = config;
	}

	public void destroy() {
		config = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws ServletException, IOException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		// 【取得 session】
		HttpSession session = req.getSession();
		// 【從 session 判斷此user是否登入過】

		session.removeAttribute("errorMsgs");
		Object account = session.getAttribute("account");
		
		if (account == null){
			session.setAttribute("signin", "reject");
//			<% session.setAttribute("location", request.getRequestURI()); %>
			System.out.println("重導目標網頁 = "+req.getRequestURI());
			session.setAttribute("location", req.getRequestURI());
			//當前頁面停留太久，session失效，重導到首頁，未寫監聽器
			//新的session，直接重導首頁
			if(session.isNew()) {
				res.sendRedirect(config.getServletContext().getContextPath()+"/front-end/welcome/index.jsp");
				return;
			}
			
			
//				res.sendRedirect((String)session.getAttribute("location"));
				res.sendRedirect(config.getServletContext().getContextPath()+"/front-end/welcome/index.jsp");
				return;
		} else {
			chain.doFilter(request, response);
		}
	}
}