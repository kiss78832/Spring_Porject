package com.member.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

import com.member.model.MemberService;
import com.member.model.MemberVO;

@MultipartConfig(fileSizeThreshold=1024*1024,maxFileSize=5*1024*1024,maxRequestSize=5*5*1024*1024)
public class MemberServlet extends HttpServlet {
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException {
		doPost(req,res);
	}

	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException {
		
		System.out.println("我進doPost了");
		
		req.setCharacterEncoding("UTF-8");
		
		res.setContentType("text/html;charset=UTF-8");
		
		String action = req.getParameter("action");
		
//		HttpSession session = req.getSession();
		
		PrintWriter out =  res.getWriter();
		
		/*會員資料更新*/
		if("UPDATE".equals(action)) {

			System.out.println("我進 if UPDATE了");
			MemberService memSvc = new MemberService();
			HttpSession session = req.getSession();
			MemberVO mvo = ((MemberVO)session.getAttribute("memberVO"));
				/*尚未驗證*//*尚未錯誤重導*/
				
			String m_name = req.getParameter("m_name");
			
			if(m_name==""||"null".equalsIgnoreCase(m_name)) {
				m_name = mvo.getM_name();
			}
			/*永遠有值*/
			String member_id = req.getParameter("member_id");
			
			String password = req.getParameter("password");
			
			if("".equals(password)||"null".equalsIgnoreCase(password)) {
				password = mvo.getPassword();
			}
			
			
			
			String sex  = req.getParameter("gender");
			Integer gender;
			if(sex==null) {
				sex = "0";
				gender =Integer.valueOf(sex);
			}else {
				gender =Integer.valueOf(sex);
			}
			
			
			
			String birthStr = req.getParameter("birthday").trim();
			java.sql.Date birthday;
			if("".equals(birthStr)) {
				birthday = mvo.getBirthday();
			}else {
				birthday = java.sql.Date.valueOf(birthStr);
			}
			/*前台已驗證*/
			String cellphone = req.getParameter("cellphone");
			/*前台已驗證*/
			String m_email = req.getParameter("m_email");

			
			memSvc.updateMemberData(m_name,member_id,password,gender,birthday,
					cellphone,m_email);
			
			System.out.println("MemberData Update Success!");
			
			session.setAttribute("update", "success");

			res.sendRedirect(getServletContext().getContextPath()+"/front-end/member/memberData.jsp");

			
			
		/*會員註冊*/
		}else if("SINGUP".equals(action)) {
			
			MemberService memSvc = new MemberService();
			HttpSession session = req.getSession();

			if(memSvc.compareWith(req.getParameter("member_id"))==true) {
				System.out.println("Account Exist!");
				res.sendRedirect(getServletContext().getContextPath()+"/front-end/welcome/index.jsp");
			}else {
				/*以下還沒驗證*//*尚未錯誤重導*/
				String m_name = req.getParameter("m_name");
				String member_id =req.getParameter("member_id");
				String password  = req.getParameter("password");
				String m_email = req.getParameter("m_email");
				
				
				
				String account = req.getParameter("member_id");
				
				session.setAttribute("account", account); 
				
				boolean AA = memSvc.signUp(m_name,member_id,password,m_email);
				String isAdd = String.valueOf(AA);
				
				MemberVO memberVO =memSvc.getOneMember(member_id);
				session.setAttribute("memberVO", memberVO);
				
				/*註冊成功*/
				session.setAttribute("signupSuccess", isAdd);
				System.out.println("isAdd = "+isAdd);
				System.out.println("signUp Success!");
				
				res.sendRedirect(getServletContext().getContextPath()+"/front-end/welcome/index.jsp");
				
			}
			
		/*編輯名片*/
		}else if("CARDUPDATE".equals(action)) {
			MemberService memSvc = new MemberService();
			HttpSession session = req.getSession();
			
			MemberVO memberVO = ((MemberVO)session.getAttribute("memberVO"));
			
			String member_id = memberVO.getMember_id();	
			MemberVO mvo = memSvc.getOneMember(member_id);
			
			
			String nick_name;
			String outdoor_exp;
			byte[] m_photo;
			byte[] back_img;
			

			//更新別名
			String niname = req.getParameter("nick_name");
			if("".equals(niname)) {
				nick_name = mvo.getNick_name();
			}else {
				nick_name = niname;
			}
			
			//更新戶外經歷
			String exp = req.getParameter("outdoor_exp");
			if("".equals(exp)) {
				System.out.println("我是空字串");
				outdoor_exp = mvo.getOutdoor_exp();
			}else{
				outdoor_exp = exp;
			}
				
			//更新頭像
			InputStream in = req.getPart("m_photo").getInputStream();
			System.out.println("長度 = "+in.available());
			System.out.println(mvo.getM_name());
			
			if(in.available()==0) {
				System.out.println("原圖");
				m_photo = mvo.getM_photo();
			}else {
				System.out.println("新圖");
				m_photo = getPic(in);
			}
			
			//更新名片背景
			InputStream in2 = req.getPart("back_img").getInputStream();
			if(in2.available()==0) {
				back_img = mvo.getBack_img();
			}else {
				back_img = getPic(in2);
			}
			

			
			memSvc.cardUpdate(nick_name, outdoor_exp, 
					 m_photo, back_img, member_id);

			session.setAttribute("memberVO", mvo);
			session.setAttribute("update", "success");
			System.out.println("MemberCard Update Success!");
			
			res.sendRedirect(getServletContext().getContextPath()+"/front-end/member/cardEdit.jsp");
//			RequestDispatcher successView = req
//					.getRequestDispatcher("/front-end/member/cardEdit.jsp");
//			successView.forward(req, res);
			
		}
		
		else if ("COMPARE".equals(action)) {
			MemberService memSvc = new MemberService();
			
			String member_id = req.getParameter("member_id");
			
			boolean exist = memSvc.compareWith(member_id);
			
			JSONObject jsonObject = new JSONObject();
			try {
				jsonObject.put("result", exist);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
					
			
			out.println(jsonObject.toString());
		}else if("FORGETPASSWORD".equals(action)) {
			
			MemberService memSvc = new MemberService();
			
			String member_id = req.getParameter("member_id");
			String m_email = req.getParameter("m_email");
			
			String[] result = memSvc.forgetPassword(member_id, m_email);
			
			JSONObject jobj = new JSONObject();
			
			if(result[0].equals("true")) {
				try {
					jobj.put("result", true);
					
					/*寄信*/
					
					ForgetPassword fp = new ForgetPassword();
					fp.sendMail(result[1], m_email);
					
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}else{
				try {
					jobj.put("result", false);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			
			out.println(jobj);
			
			
		}
		
		
		
	}
	
	public static byte[] getPic(InputStream in) throws IOException {

		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();// data is written into a byte array
		byte[] data = new byte[8192];
		int i;
		while ((i = in.read(data)) != -1) {
			baos.write(data, 0, i);
		}
		baos.close();
		in.close();
		return baos.toByteArray();//boas陣列
	}
}
