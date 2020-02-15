package com.article.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.article.model.ArticleService;
import com.article.model.ArticleVO;
import com.ati_report.model.Ati_reportService;
import java.util.Base64;
//import sun.misc.BASE64Encoder;


@MultipartConfig(fileSizeThreshold=1024 * 1024,maxFileSize=25*1024*1024,maxRequestSize=5*5*5*1024*1024)


public class ArticleServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("進來了servlet");
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
//----------------------------------------------新增-------------------------------------------------------------		
		
		if ("insert".equals(action)) { // 來自addEmp.jsp的請求
			System.out.println("進來了新增");
			List<String> errorMsgs = new LinkedList<String>();// 將此集存儲在請求範圍中，以備我們需要時使用
			req.setAttribute("errorMsgs", errorMsgs);// 發送ErrorPage視圖。
			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				// 無須驗證，因為抓別人資料，已驗證
				String image_css = req.getParameter("image_css");  
				System.out.println("測試style:"+image_css);
				System.out.println("2");
				String member_id = req.getParameter("member_id");	
				String article_title = req.getParameter("article_title"); // 標題隨便下，不用驗證
				System.out.println("3");
				String article_content = req.getParameter("article_content");
				System.out.println("4");

				String[] tag_array = req.getParameterValues("tag");
				StringBuilder sb = new StringBuilder();
				for(String element : tag_array ){
					sb.append(element+",");
				}
				
				String tag = sb.toString() ;
				System.out.println("tag"+tag);
				
				Part article_Part = req.getPart("article_image");
				int length = article_Part.getInputStream().available();
				byte bt[] = new  byte[length]; 
				InputStream in_img = article_Part.getInputStream();
				in_img.read(bt);
					
				String article_image = Base64Img(bt);
				
//				System.out.println("測試圖片"+article_image);
				
				
				Part article_Part2 = req.getPart("article_image_2");
				int length2 = article_Part2.getInputStream().available();
				byte bt2[] = new  byte[length2]; 
				
				InputStream in_img2 = article_Part2.getInputStream();
				in_img2.read(bt2);
				
				String article_image_2 = Base64Img2(bt2);
				
				
				
				
				Part article_Part3 = req.getPart("article_image_3");
				int length3 = article_Part3.getInputStream().available();
				byte bt3[] = new  byte[length3]; 
				
				InputStream in_img3 = article_Part3.getInputStream();
				in_img3.read(bt3);
				
				String article_image_3 = Base64Img3(bt3);
				
				
				System.out.println("資料驗證成功-----");
				ArticleVO articleVO = new ArticleVO();
				articleVO.setMember_id(member_id);
				articleVO.setArticle_title(article_title);
				articleVO.setArticle_content(article_content);
				articleVO.setArticle_image(article_image);
				articleVO.setArticle_image_2(article_image_2);
				articleVO.setArticle_image_3(article_image_3);
				articleVO.setTag(tag);
				articleVO.setImage_css(image_css);
				System.out.println("測試6");
		

				// 如果有錯誤，請將用途發回表單
				if (!errorMsgs.isEmpty()) {
					System.out.println("列印errorMsgs不為空錯誤:"+errorMsgs);
					req.setAttribute("articleVO", articleVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article/article_Edit.jsp");
					failureView.forward(req, res);	
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				ArticleService articleSvc = new ArticleService();
				articleSvc.addArticle(member_id, article_title, article_content, article_image, 0, 0, tag, article_image_2, article_image_3, image_css);
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				System.out.println("文章新增成功");
				String url = "/front-end/article/article_front.jsp";
				res.sendRedirect(getServletContext().getContextPath()+url);


				/*************************** 其他可能的錯誤處理 **********************************/
		} catch (Exception e) {
			System.out.println("新增失敗");
			errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/front-end/article/article_Edit.jsp");
			failureView.forward(req, res);
		}
	}
			
		
	
		
//-----------------------------------------查詢(要用Redis?)-----------------------------------------------------------------		
		
			if ("getOne_For_Update".equals(action)) { 
				System.out.println("進入修改");
				
				List<String> errorMsgs = new LinkedList<String>();// 將此集存儲在請求範圍中，以備我們需要時使用
				req.setAttribute("errorMsgs", errorMsgs);// 發送ErrorPage視圖。
	
				// 這邊應該用標籤去搜尋，找文章
				String article_id = req.getParameter("article_id").trim(); 
				String article_image = req.getParameter("article_image").trim(); 
				Timestamp article_time =java.sql.Timestamp.valueOf(req.getParameter("article_time")); 
				System.out.println(article_time);
				
				
				ArticleVO articleVO = new ArticleVO();
				articleVO.setArticle_image(article_image);
				articleVO.setArticle_id(article_id);
				articleVO.setArticle_time(article_time);



			try {
				/***************************2.開始查詢資料****************************************/
				ArticleService articleSvc = new ArticleService();
				articleVO = articleSvc.getOneArticle(article_id);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				 // 資料庫取出的empVO物件,存入req
//				req.setAttribute("articleVO", articleVO);        
				String url = "/front-end/article/article_front.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/article/article_front.jsp");
				failureView.forward(req, res);
			}
		}
	
//------------------------------------------------修改愛心----------------------------------------------------------				
		if ("like_ajax".equals(action)) { 
			System.out.println("測試愛心");
			String article_id = req.getParameter("id"); 
			String add = req.getParameter("add");
			ArticleService srv = new ArticleService();
			ArticleVO articleVO = srv.getOneArticle(article_id);
			if(articleVO != null) {
				if("true".equals(add)){
					articleVO.setLike_c(articleVO.getLike_c() + 1);
					srv.updateArticle(articleVO);
				} else {
					articleVO.setLike_c(articleVO.getLike_c() - 1);
					srv.updateArticle(articleVO);
				}
			}
			System.out.println("測試成功");
			
			res.setContentType("text/plain");
			PrintWriter out = res.getWriter();
			out.write("");
			out.flush();
			out.close();
		}
			
		
//------------------------------------------------傳送style----------------------------------------------------------				
				if ("imag_css".equals(action)) { 
					System.out.println("成功進入ajax");
					
		
					System.out.println("測試成功");
					
					res.setContentType("text/plain");
					PrintWriter out = res.getWriter();
					out.write("123");
					out.flush();
					out.close();
				}		
		
//------------------------------------------------修改文章----------------------------------------------------------		

		if ("update".equals(action)) { 
			System.out.println("進入修改文章");
			List<String> errorMsgs = new LinkedList<String>();// 將此集存儲在請求範圍中，以備我們需要時使用
			req.setAttribute("errorMsgs", errorMsgs);// 發送ErrorPage視圖。

			String article_id = req.getParameter("article_id").trim();
			ArticleVO articleVO = new ArticleVO();
			articleVO.setArticle_id(article_id);


		try {
			
			
			/***************************3.準備轉交(Send the Success view)************/
			 // 資料庫取出的empVO物件,存入req
			req.setAttribute("articleVO", articleVO);        
			String url = "/front-end/article/article_modify.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交jsp
			successView.forward(req, res);

			/***************************其他可能的錯誤處理**********************************/
		} catch (Exception e) {
			errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/front-end/article/article_modify.jsp");
			failureView.forward(req, res);
		}
	}
		
//-----------------------------------------修改文章_確定修改-----------------------------------------------------------------		
		
		if ("update_final".equals(action)) { 
			System.out.println("進入確定修改");
			
			List<String> errorMsgs = new LinkedList<String>();// 將此集存儲在請求範圍中，以備我們需要時使用
			req.setAttribute("errorMsgs", errorMsgs);// 發送ErrorPage視圖。

			
			
			

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				// 無須驗證，因為抓別人資料，已驗證
				
				String member_id = req.getParameter("member_id");
				String article_title = req.getParameter("article_title"); // 標題隨便下，不用驗證
				String article_content = req.getParameter("article_content");
				String[] tag_array = req.getParameterValues("tag");
				String article_id = req.getParameter("article_id");
				Integer like_c = new Integer(req.getParameter("like_c"));
				Integer watched_c = new Integer(req.getParameter("watched_c"));
				String image_css = req.getParameter("image_css");  
				System.out.println("測試style:"+image_css);
				
				System.out.println(member_id);
				System.out.println(article_title);
				System.out.println(article_content);
				System.out.println(article_id);
				System.out.println(tag_array);
				System.out.println(like_c);
				System.out.println(watched_c);
				
				//hashtag串接
				StringBuilder sb = new StringBuilder();
				for(String element : tag_array ){
					sb.append(element+",");
				}
				
				String tag = sb.toString() ;
				
				Part article_Part = req.getPart("article_image");
				int length = article_Part.getInputStream().available();
				byte bt[] = new  byte[length]; 
				
				InputStream in_img = article_Part.getInputStream();
				in_img.read(bt);
					
				String article_image = Base64Img(bt);
				
				
				
				Part article_Part2 = req.getPart("article_image_2");
				int length2 = article_Part2.getInputStream().available();
				byte bt2[] = new  byte[length2]; 
				
				InputStream in_img2 = article_Part2.getInputStream();
				in_img2.read(bt2);
				
				String article_image_2 = Base64Img2(bt2);
				

				Part article_Part3 = req.getPart("article_image_3");
				int length3 = article_Part3.getInputStream().available();
				byte bt3[] = new  byte[length3]; 
				
				InputStream in_img3 = article_Part3.getInputStream();
				in_img3.read(bt3);
				
				String article_image_3 = Base64Img3(bt3);
				
				System.out.println("資料驗證成功-----");
				ArticleService articleSvc = new ArticleService();
				ArticleVO articleVO = articleSvc.getOneArticle(article_id);
				
				articleVO.setMember_id(member_id);
				articleVO.setArticle_id(article_id);
				articleVO.setLike_c(like_c);
				articleVO.setWatched_c(watched_c);
				articleVO.setArticle_title(article_title);
				articleVO.setArticle_content(article_content);
				articleVO.setTag(tag);
				articleVO.setImage_css(image_css);
				//判斷是否有圖片傳進
				if(!article_image.equals("data:image/jpg;base64,")) {
					articleVO.setArticle_image(article_image);
				}
				
				if(!article_image_2.equals("data:image/jpg;base64,")) {
					articleVO.setArticle_image_2(article_image_2);
				}
				
				if(!article_image_3.equals("data:image/jpg;base64,")) {
					articleVO.setArticle_image_3(article_image_3);
				}
				
				// 如果有錯誤，請將用途發回表單
				if (!errorMsgs.isEmpty()) {
					System.out.println("錯誤了拉");
					System.out.println(errorMsgs);
					req.setAttribute("articleVO", articleVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article/article_Edit.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				
				articleSvc.updateArticle(articleVO);
				
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				System.out.println("文章修改成功");
				String url = "/front-end/article/article.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
		} catch (Exception e) {
			System.out.println("修改失敗"+e.getMessage());
			errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/front-end/article/article_Edit.jsp");
			failureView.forward(req, res);
		}
	}
		
		
				
//-----------------------------------------標籤找文章-----------------------------------------------------------------		
				
				if ("findtag".equals(action)) { 
					System.out.println("進入標籤找文章");
					
					List<String> errorMsgs = new LinkedList<String>();// 將此集存儲在請求範圍中，以備我們需要時使用
					req.setAttribute("errorMsgs", errorMsgs);// 發送ErrorPage視圖。
		
					String element = req.getParameter("element").trim(); 
					System.out.println("element:"+element);

				try {
					/***************************2.開始用標籤找文章****************************************/
					ArticleService articleSvc = new ArticleService();

					/***************************3.查詢完成,準備轉交(Send the Success view)************/
					 // 資料庫取出的empVO物件,存入req
					req.setAttribute("element", element); 
					System.out.println("成功搜尋");
					String url = "/front-end/article/article_findtag.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交
					successView.forward(req, res);

					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/article/article_findtag.jsp");
					failureView.forward(req, res);
				}
			}
				
				
//------------------------------------------------刪除文章----------------------------------------------------------		

				if ("delete".equals(action)) { 
					System.out.println("進入刪除文章");
					List<String> errorMsgs = new LinkedList<String>();// 將此集存儲在請求範圍中，以備我們需要時使用
					req.setAttribute("errorMsgs", errorMsgs);// 發送ErrorPage視圖。

					String article_id = req.getParameter("article_id").trim();
					
					

				try {
					/***************************3.準備刪除(Send the Success view)************/
					ArticleService articleSvc = new ArticleService();
					Ati_reportService ari_Svc = new Ati_reportService();
					System.out.println(article_id);
//					ari_Svc.deleteAti(article_id);
					articleSvc.deleteArticle(article_id);       
					String url = "/front-end/article/article.jsp";

					res.sendRedirect(getServletContext().getContextPath()+url);

					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
					System.out.println(e.getMessage());
					System.out.println("這邊是刪除錯誤區");
					res.sendRedirect(getServletContext().getContextPath()+"/front-end/article/article.jsp");
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/front-end/article/article.jsp");
//					failureView.forward(req, res);
				}
			}						
//-----------------------------------------檢舉文章-----------------------------------------------------------------		
				
				if ("report".equals(action)) { 
					System.out.println("進入檢舉");
					
					List<String> errorMsgs = new LinkedList<String>();// 將此集存儲在請求範圍中，以備我們需要時使用
					req.setAttribute("errorMsgs", errorMsgs);// 發送ErrorPage視圖。
		
					String article_id = req.getParameter("article_id").trim();
					String member_id = req.getParameter("member_id").trim();
					System.out.println(article_id);
					System.out.println(member_id);
					

				try {
					/***************************2.開始檢舉文章****************************************/

					Ati_reportService ati_reportSvc = new Ati_reportService();
					ati_reportSvc.addAti(article_id, "", member_id,1);
					
					
					/***************************3.查詢完成,準備轉交(Send the Success view)************/
					 // 資料庫取出的empVO物件,存入req
					System.out.println("檢舉成功");
//					String url = "/front-end/article/article_front.jsp";
//					RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 
//					successView.forward(req, res);
					res.sendRedirect(getServletContext().getContextPath()+"/front-end/article/article_front.jsp");
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					System.out.println("檢舉失敗");
					errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/article/article_front.jsp");
					failureView.forward(req, res);
				}
			}	
			
//-----------------------------------------閱讀文章-----------------------------------------------------------------		
				
				if ("read".equals(action)) { 
					System.out.println("進入閱讀");
					
					List<String> errorMsgs = new LinkedList<String>();// 將此集存儲在請求範圍中，以備我們需要時使用
					req.setAttribute("errorMsgs", errorMsgs);// 發送ErrorPage視圖。
		
					

					
					

				try {
					/***************************2.開始閱讀文章****************************************/
					String article_id = req.getParameter("article_id");
//					String member_id = req.getParameter("member_id").trim();
					System.out.println(article_id);
					
					ArticleService articleSvc = new ArticleService();
					//增加觀看次數
					ArticleVO watchVO = articleSvc.getOneArticle(article_id);
					int watched_c = watchVO.getWatched_c()+1;
					watchVO.setWatched_c(watched_c);
					articleSvc.updateArticle(watchVO);
					
					
//					ArticleVO articleVO = new ArticleVO();
//					articleVO.setArticle_id(article_id);
//					articleVO.setMember_id(member_id);
					
					
					
					
					/***************************3.查詢完成,準備轉交(Send the Success view)************/
					 // 資料庫取出的empVO物件,存入req
					req.setAttribute("article_id", article_id); 
					String url = "/front-end/article/article_content.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 
					successView.forward(req, res);

					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					System.out.println("閱讀失敗");
					errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/article/article_content.jsp");
					failureView.forward(req, res);
				}
			}				
		
				
				
				

				
//----------dopost跟文章類別括號-----------------		
	}

	public String Base64Img(byte[] byteArray) {
		String encodeBase64 = Base64.getEncoder().encodeToString(byteArray);
			return "data:image/jpg;base64,"+encodeBase64;
	}
	
	public String Base64Img2(byte[] byteArray) {
		String encodeBase64 = Base64.getEncoder().encodeToString(byteArray);
			return "data:image/jpg;base64,"+encodeBase64;
	}
	
	public String Base64Img3(byte[] byteArray) {
		String encodeBase64 = Base64.getEncoder().encodeToString(byteArray);
			return "data:image/jpg;base64,"+encodeBase64;
	}

//public static String getImg(InputStream in_img) throws IOException{
//	  byte[] data = new byte[in_img.available()];
//			in_img.read(data);
//			in_img.close();
//			
////			public String Base64Img(byte[] byteArray) {
////				String encodeBase64 = Base64.getEncoder().encodeToString(byteArray);
////					return encodeBase64;
////			}
//			
//			BASE64Encoder encoder = new BASE64Encoder();
//			return "data:image/jpg;base64,"+encoder.encode(data);
//	}
//
//public static String getImg2(InputStream in_img2) throws IOException{
//	  byte[] data2 = new byte[in_img2.available()];
//			in_img2.read(data2);
//			in_img2.close();
//			
//			BASE64Encoder encoder2 = new BASE64Encoder();
//			return "data:image/jpg;base64,"+encoder2.encode(data2);
//	}
//
//public static String getImg3(InputStream in_img3) throws IOException{
//	  byte[] data3 = new byte[in_img3.available()];
//			in_img3.read(data3);
//			in_img3.close();
//			
//			BASE64Encoder encoder3 = new BASE64Encoder();
//			return "data:image/jpg;base64,"+encoder3.encode(data3);
//	}


}






