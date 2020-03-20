package com.spring.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import redis.clients.jedis.Jedis;

public class ConfirmMail extends HttpServlet {
	
	Jedis jedis = new Jedis("localhost", 6379);
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		
		jedis.auth("123456");
		
		String member_id = req.getParameter("member_id");
		String action = req.getParameter("action");
		String m_email = req.getParameter("m_email");
		System.out.println("member_id = "+member_id);
		System.out.println("action = "+action);
		
		if("signup".equals(action)) {
			String code = returnAuthCode();
			/*驗證碼存進redis*/
			jedis.set("Member_Id:"+member_id, code);
			jedis.expire("Member_Id:"+member_id, 60*2);
			/*發送mail*/
			JSONObject jobj = new JSONObject();
			try {
				jobj.put("pass", code);
				out.println(jobj);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			 // Recipient's email ID needs to be mentioned.
		      String to = m_email;

		      // Sender's email ID needs to be mentioned
		      String from = "ixlogic.wu@gmail.com";

		      // Assuming you are sending email from localhost
		      String host = "10.120.25.21";

		      // Get system properties
		      Properties properties = System.getProperties();

		      // Setup mail server
		      properties.put("mail.smtp.host", "smtp.gmail.com");
		      properties.put("mail.smtp.socketFactory.port", "465");
		      properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		      properties.put("mail.smtp.auth", "true");
		      properties.put("mail.smtp.port", "465");
		      
		      
			     final String myGmail = "ixlogic.wu@gmail.com";
			     final String myGmail_password = "BBB45678";
		      
		      // Get the default Session object.
		      Session session = Session.getInstance(properties, new Authenticator() {
				   protected PasswordAuthentication getPasswordAuthentication() {
					   return new PasswordAuthentication(myGmail, myGmail_password);
				   }
			   });

		      try{
		          // Create a default MimeMessage object.
		    	  Message message = new MimeMessage(session);

		          // Set From: header field of the header.
		          message.setFrom(new InternetAddress(from));

		          // Set To: header field of the header.
		          message.addRecipient(Message.RecipientType.TO,
		                                   new InternetAddress(to));

		          // Set Subject: header field
		          message.setSubject("Island Peak 驗證通知");

		          // Now set the actual message
		          
		          message.setContent("<div style='background-color: black; width:500px;border-radius: 20px;padding: 20px;'>"+
		        		  "<div style='height: 80px;display: inline-block;'>"
		          		+ "<img src='https://i.imgur.com/hsSc2kb.png' style='vertical-align:bottom;'>&nbsp;&nbsp;"
		          		+ "<span style='color:red;font-size:30px;vertical-align: bottom;'>Island Peak</span>" + 
		          		"</div><hr><div><h2 style='color: white;'>謝謝您的註冊  !</h2>"+
		          		"<p style='color: white;font-size: 20px;'>您的帳號驗證碼為  :  "+code+"</p>"+
		          		"</div><hr></div>","text/html; charset=UTF-8");

		          // Send message
		          Transport.send(message);
		          System.out.println("Sent message successfully....");
		       }catch (MessagingException mex) {
		          mex.printStackTrace();
		       }
		}else if("getconfirm".equals(action)) {//sweetalert 點擊時 同步查詢Redis資料比對
			
			String confirm =  jedis.get("Member_Id:"+member_id);
			
			JSONObject jobj = new JSONObject();
			
			try {
				jobj.put("confirm", confirm);
				out.println(jobj);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
		}
		
		jedis.close();
	}
	
	
	
	
	private static String returnAuthCode() {
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= 8; i++) {
			int condition = (int) (Math.random() * 3) + 1;
			switch (condition) {
			case 1:
				char c1 = (char)((int)(Math.random() * 26) + 65);
				sb.append(c1);
				break;
			case 2:
				char c2 = (char)((int)(Math.random() * 26) + 97);
				sb.append(c2);
				break;
			case 3:
				sb.append((int)(Math.random() * 10));
			}
		}
		return sb.toString();
	}
	

}
