package com.spring.member.controller;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ForgetPassword {

	
	void sendMail(String pass,String m_email){
		
		
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
		          message.setSubject("Island Peak 忘記密碼通知");

		          // Now set the actual message

		          message.setContent("<div style='background-color: black; width:500px;border-radius: 20px;padding: 20px;'>"+
		        		  "<div style='height: 80px;display: inline-block;'>"
		          		+ "<img src='https://i.imgur.com/hsSc2kb.png' style='vertical-align:bottom;'>&nbsp;&nbsp;"
		          		+ "<span style='color:red;font-size:30px;vertical-align: bottom;'>Island Peak</span>" + 
		          		"</div><hr><div><h2 style='color: white;'>謝謝您繼續支持本網站 !</h2>"+
		          		"<p style='color: white;font-size: 20px;'>您的密碼為 :  "+pass+"</p>"+
		          		"</div><hr></div>","text/html; charset=UTF-8");

		          // Send message
		          Transport.send(message);
		          System.out.println("Sent message successfully....");
		       }catch (MessagingException mex) {
		          mex.printStackTrace();
		       }
	}
	
}
