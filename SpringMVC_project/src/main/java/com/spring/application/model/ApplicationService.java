package com.spring.application.model;

import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.spring.article.model.ArticleService;

public class ApplicationService {

	private ApplicationDAO_interface dao;

	public ApplicationService() {
		dao = new ApplicationJNDI();
	}

	public void transfer_Mail(String to, String subject, String messageText){
				System.out.println("進來了");
		   try {
			   // 設定使用SSL連線至 Gmail smtp Server
			   Properties props = new Properties();
			   props.put("mail.smtp.host", "smtp.gmail.com");
			   props.put("mail.smtp.socketFactory.port", "465");
			   props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
			   props.put("mail.smtp.auth", "true");
			   props.put("mail.smtp.port", "465");

	       // ●設定 gmail 的帳號 & 密碼 (將藉由你的Gmail來傳送Email)
	       // ●須將myGmail的【安全性較低的應用程式存取權】打開
		     final String myGmail = "ixlogic.wu@gmail.com";
		     final String myGmail_password = "BBB45678";
			   Session session = Session.getInstance(props, new Authenticator() {
				   protected PasswordAuthentication getPasswordAuthentication() {
					   return new PasswordAuthentication(myGmail, myGmail_password);
				   }
			   });

			   Message message = new MimeMessage(session);
			   message.setHeader("ContentType", "text/html;charset=UTF-8");
			   message.setFrom(new InternetAddress(myGmail));
			   message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
			  
			   //設定信中的主旨  
			   message.setSubject(subject);
			   //設定信中的內容 
			   message.setText(messageText);

			   Transport.send(message);
			   System.out.println("傳送成功!");
	     }catch (MessagingException e){
		     System.out.println("傳送失敗!");
		     e.printStackTrace();
	     }
		
	}
	

	
	public ApplicationVO addApp(String group_id, String member_id, String route_id
			,String egc_contact,String egc_phone,String satellite,String radio,Integer app_status,Integer app_days
			,java.sql.Date first_day,Integer dailybed_provided,Integer meal_provided,String locations) {

		ApplicationVO appVO = new ApplicationVO();

		appVO.setGroup_id(group_id);
		appVO.setMember_id(member_id);
		appVO.setRoute_id(route_id);
		appVO.setEgc_contact(egc_contact);
		appVO.setEgc_phone(egc_phone);
		appVO.setSatellite(satellite);
		appVO.setRadio(radio);
		appVO.setApp_status(app_status);
		appVO.setApp_days(app_days);
		appVO.setFirst_day(first_day);
		appVO.setDailybed_provided(dailybed_provided);
		appVO.setMeal_provided(meal_provided);
		appVO.setLocations(locations);
		dao.insert(appVO);

		return appVO;
	}

	public ApplicationVO updateApp(String app_num,String group_id, String member_id, String route_id
			,String egc_contact,String egc_phone,String satellite,String radio,Integer app_status,Integer app_days
			,java.sql.Date first_day,Integer dailybed_provided,Integer meal_provided,String locations) {

		ApplicationVO appVO = new ApplicationVO();
		appVO.setApp_num(app_num);
		appVO.setGroup_id(group_id);
		appVO.setMember_id(member_id);
		appVO.setRoute_id(route_id);
		appVO.setEgc_contact(egc_contact);
		appVO.setEgc_phone(egc_phone);
		appVO.setSatellite(satellite);
		appVO.setRadio(radio);
		appVO.setApp_status(app_status);
		appVO.setApp_days(app_days);
		appVO.setFirst_day(first_day);
		appVO.setDailybed_provided(dailybed_provided);
		appVO.setMeal_provided(meal_provided);
		appVO.setLocations(locations);
		dao.update(appVO);
		
		return appVO;
	}
	
	
	public ApplicationVO updateApp_One(Integer app_status,String app_num) {

		ApplicationVO appVO = new ApplicationVO();
		appVO.setApp_num(app_num);
		appVO.setApp_status(app_status);
		dao.update_One(appVO);
		
		return appVO;
	}

	

	public List<ApplicationVO> getAll() {
		return dao.getAll();
	}

	public  ApplicationVO getOneApp(String app_num) {
		return dao.findByPrimaryKey(app_num);
	}

	/*新增方法*/
	
	public  List<ApplicationVO> getOneApp_List(String member_id) {
		return dao.getApp_One(member_id);
	}
	
	public  List<ApplicationVO> getStatus_List(Integer app_status) {
		return dao.getStatus_List(app_status);
	}
	
	
	
	
public static void main(String[] args) {

	ApplicationService  dao = new ApplicationService();	
//	dao.transfer_Mail("kiss78832@gmail.com","安安你好", "白癡你好");
//	dao.updateApp("A000004","G000001","A001","R001","M000005","M000006","M0007","M007",10,10,java.sql.Date.valueOf("2002-01-01"),10,10,"222,排雲2,排雲");
//	dao.addApp("G000001","A001","R001","M000005","M000006","M000007","M000007",100,100,java.sql.Date.valueOf("2002-01-01"),10,10,"排雲,排雲,排雲");
//	dao.updateApp_One(3,"A000002");
//  dao.getOneApp("A002");

	}
}