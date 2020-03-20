package com.spring.staff.controller;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

public class StaffPasswordMail {
	
	String sendMail(String sf_email,String sf_account) {
		String password;
		
		/*記給員工的真密碼*/
		password = createPassword();
		String end = password;
		/*給DB編碼過的密碼*//*未做*/
		
		String passwordForDB = null;
		String end2 = end.substring(2);
		
		int end3 = (Integer.parseInt(end2))*9+10;
		
		String end4 = String.valueOf(end3);
		String end5 = end4.substring(0,3);
		String end6 = end4.substring(3);
		String end7 = end5+str()+end6;

		
		String to = sf_email;
		String from = "ixlogic.wu@gmail.com";
		
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
	          message.setSubject("Island Peak 員工帳號開通通知");//"+sf_account+" "+password+"

	          // Now set the actual message
	          message.setContent("<div style='background-color: black; width:500px;border-radius: 20px;padding: 20px;'>"+
	        		  "<div style='height: 80px;display: inline-block;'>"
	          		+ "<img src='https://i.imgur.com/hsSc2kb.png' style='vertical-align:bottom;'>&nbsp;&nbsp;"
	          		+ "<span style='color:red;font-size:30px;vertical-align: bottom;'>Island Peak</span>" + 
	          		"</div><hr><div><h2 style='color: white;'>歡迎您的加入 !</h2>"+
	          		"<p style='color: white;font-size: 20px;'>你的帳號為 :"+sf_account+"  </p>"+
	          		"<p style='color: white;font-size: 20px;'>你的密碼為 :  "+password+"</p></div><hr></div>","text/html; charset=UTF-8");
	          
	          
	          // Send message
	          Transport.send(message);
	          System.out.println("Sent message successfully....");
	       }catch (MessagingException mex) {
	          mex.printStackTrace();
	       }
		
		
		return end7;
	}
	
	String str() {
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= 6; i++) {
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
	
	String createPassword() {
		StringBuilder password = new StringBuilder();
		password.append("SF");
		for(int i = 1 ; i <= 8 ;i++) {
			password.append(String.valueOf((int)(Math.random()*9+1)));
		}
		return password.toString();
	}

}
