package com.equipment.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import javax.swing.JOptionPane;

import com.equtypetotal.model.EquTotalService;


import com.equipment.model.*;
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class EquipmentServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
			
		
//		if ("getequ".equals(action)) {
//
//			try {
//			
//				EquipmentService equSVC = new EquipmentService();
//				List<EquipmentVO> list = equSVC.getAll();
//				List<EquipmentVO> name=new ArrayList<EquipmentVO>();
//				
//				for(int i=0;i<list.size();i++) {
//					
//					if(!name.contains(list.get(i))) {
//						System.out.println("有東西");
//						name.add(list.get(i));
//						
//					}else{
//						System.out.println("delete");
//						list.remove(i);
//					}
//					
//				}
//				req.setAttribute("bbb", name);		
//				
//				RequestDispatcher successView = req
//						.getRequestDispatcher("/front-end/renthome/renthome.jsp");
//				successView.forward(req, res);
//				return;
//			} catch (Exception e) {
//				throw new ServletException(e);
//			}
//		}
		
	
		
		
		
		
		if ("getOne_From06".equals(action)) {
			String url = req.getParameter("requestURL");
			try {
			
				String eq_num = new String(req.getParameter("eq_num"));

				EquipmentService equSVC = new EquipmentService();
				EquipmentVO equVO = equSVC.getOneEquipment(eq_num);

				req.setAttribute("equVO", equVO); 
			
				boolean openModal=true;
				req.setAttribute("openModal",openModal );
				
				RequestDispatcher successView = req
						.getRequestDispatcher(url);
				successView.forward(req, res);
				return;

				
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		
		
		
		
		
		
		
		
	
		if ("getOne_For_Display".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("eq_num");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入裝備編號");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				String eq_num = null;
				try {
					eq_num = new String(str);
				} catch (Exception e) {
					errorMsgs.add("裝備編號格式不正確");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始查詢資料*****************************************/
				EquipmentService equSvc = new EquipmentService();
				EquipmentVO equVO = equSvc.getOneEquipment(eq_num);
				if (equVO == null) {
					errorMsgs.add("查無資料");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("equVO", equVO); 
				String url = "/back-end/product/listOneProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); 
			
			try {
				/***************************1.接收請求參數****************************************/
				String eq_num = new String(req.getParameter("eq_num"));
				
				/***************************2.開始查詢資料****************************************/
				EquipmentService equSvc = new EquipmentService();
				EquipmentVO equVO = equSvc.getOneEquipment(eq_num);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("equVO", equVO); 
				String url = "/back-end/product/update_product_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************其他可能的錯誤處理************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料取出時失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}	
		if ("update".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			EquipmentService eqSvc = new EquipmentService();
			EquipmentVO equVO = eqSvc.getOneEquipment(req.getParameter("eq_num"));
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL");
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String eq_num = new String(req.getParameter("eq_num").trim());

				String eq_name = req.getParameter("eq_name");
				String eq_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (eq_name == null || eq_name.trim().length() == 0) {
					errorMsgs.add("ˋ裝備名稱  請勿空白");
				} else if(!eq_name.trim().matches(eq_nameReg)) { 
					errorMsgs.add("裝備名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				String eq_type = req.getParameter("eq_type").trim();
				if (eq_type == null || eq_type.trim().length() == 0) {
					errorMsgs.add("請輸入裝備類別");
				}
				
				String eq_size = req.getParameter("eq_size").trim();
				if (eq_size == null || eq_size.trim().length() == 0) {
					errorMsgs.add("請輸入裝備規格");
				}	
				
				String eq_brand = req.getParameter("eq_brand").trim();
				if (eq_brand == null || eq_brand.trim().length() == 0) {
					errorMsgs.add("請輸入裝備品牌");
				}	
				
				Integer eq_price = null;
				try {
					eq_price = new Integer(req.getParameter("eq_price").trim());
				} catch (NumberFormatException e) {
					eq_price = 0;
					errorMsgs.add("售價請填數字");
				}
				
				Integer eq_status = null;
				try {
					eq_status = new Integer(req.getParameter("eq_status").trim());
				} catch (NumberFormatException e) {
					eq_status = 0;
					errorMsgs.add("狀態請填數字");
				}
				
				byte[] eq_pic;
				Part fileupload = req.getPart("eq_pic");
				
				InputStream is = fileupload.getInputStream();
				if( is.available()==0 ) {
					eq_pic = equVO.getEq_pic();
					
				}else {
					eq_pic = getPictureByteArray(is);
				}
				
				
//				byte[] eq_pic = req.getParameter("eq_pic").getBytes();
				
				String eq_detail = req.getParameter("eq_detail").trim();
				
				String type_eq_num = req.getParameter("type_eq_num").trim();
				
				
				equVO.setEq_num(eq_num);
				equVO.setEq_name(eq_name);
				equVO.setEq_type(eq_type);
				equVO.setEq_size(eq_size);
				equVO.setEq_brand(eq_brand);
				equVO.setEq_price(eq_price);
				equVO.setEq_status(eq_status);
				equVO.setEq_pic(eq_pic);
				equVO.setEq_detail(eq_detail);
				equVO.setType_eq_num(type_eq_num);


				if (!errorMsgs.isEmpty()) {
					req.setAttribute("equVO", equVO); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/product/update_product_input.jsp");
					failureView.forward(req, res);
					return; 
				}
				
				/***************************2.開始修改資料*****************************************/
				EquipmentService equSvc = new EquipmentService();
				equVO = equSvc.updateEquipment(eq_num, eq_name, eq_type, eq_size, eq_brand, eq_price, eq_status
						, eq_pic, eq_detail, type_eq_num);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/				
                String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);   // 修改成功後,轉交回送出修改的來源網頁
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/product/update_product_input.jsp");
				failureView.forward(req, res);
			}
		}


        if ("insert".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String eq_name = req.getParameter("eq_name");
				String eq_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (eq_name == null || eq_name.trim().length() == 0) {
					errorMsgs.add("請輸入裝備名稱");
				} else if(!eq_name.trim().matches(eq_nameReg)) { 
					errorMsgs.add("裝備名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				String eq_type = req.getParameter("eq_type").trim();
				if (eq_type == null || eq_type.trim().length() == 0) {
					errorMsgs.add("請輸入裝備類別");
				}
				
				String eq_size = req.getParameter("eq_size").trim();
				if (eq_size == null || eq_size.trim().length() == 0) {
					errorMsgs.add("請輸入裝備規格");
				}	
				
				String eq_brand = req.getParameter("eq_brand").trim();
				if (eq_brand == null || eq_brand.trim().length() == 0) {
					errorMsgs.add("請輸入裝備品牌");
				}	
				
				Integer eq_price = null;
				try {
					eq_price = new Integer(req.getParameter("eq_price").trim());
					if (eq_price < 0) {
						errorMsgs.add("請輸入正確售價");
					}	 
				} catch (NumberFormatException e) {
					eq_price = 0;
					errorMsgs.add("售價請填數字.");
				} 
				
				Integer eq_status = null;
				try {
					eq_status = new Integer(req.getParameter("eq_status").trim());
				} catch (NumberFormatException e) {
					eq_status = 0;
					errorMsgs.add("狀態請填數字.");
				}
				
				Part fileupload = req.getPart("eq_pic");
				InputStream is = fileupload.getInputStream();
				byte[] eq_pic = getPictureByteArray(is);
				
//				byte[] eq_pic = req.getParameter("eq_pic").getBytes();
				
				String eq_detail = req.getParameter("eq_detail").trim();
				
				String type_eq_num = req.getParameter("type_eq_num").trim();
				
				EquipmentVO equVO = new EquipmentVO();
				equVO.setEq_name(eq_name);
				equVO.setEq_type(eq_type);
				equVO.setEq_size(eq_size);
				equVO.setEq_brand(eq_brand);
				equVO.setEq_price(eq_price);
				equVO.setEq_status(eq_status);
				equVO.setEq_pic(eq_pic);
				equVO.setEq_detail(eq_detail);
				equVO.setType_eq_num(type_eq_num);

			
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("equVO", equVO); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/product/addProduct.jsp");
					failureView.forward(req, res);
					return; 
				}
				
				
				
				
				/***************************2.開始新增資料***************************************/
				EquipmentService equSvc = new EquipmentService();
				equVO = equSvc.addEquipment(eq_name, eq_type, eq_size, eq_brand, eq_price, eq_status
						, eq_pic, eq_detail, type_eq_num);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/product/listAllPro.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/product/addProduct.jsp");
				failureView.forward(req, res);
			}
		}
		
       

	}
	
	// 使用byte[]方式
		public static byte[] getPictureByteArray(InputStream is) throws IOException {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[8192];
			int i;
			while ((i = is.read(buffer)) != -1) {
				baos.write(buffer, 0, i);
			}
			baos.close();
			is.close();

			return baos.toByteArray();
		}
		
		
		
		
		
		
		
		
		
		
		
		
		private EquipmentVO getEquipment(HttpServletRequest req) {

			String eq_name = req.getParameter("eq_name");
			String eq_price = req.getParameter("eq_price");
			String eq_type = req.getParameter("eq_type");
			String eq_size = req.getParameter("eq_size");

			EquipmentVO equipment = new EquipmentVO();

			equipment.setEq_name(eq_name);
			equipment.setEq_price(new Integer(eq_price));
			equipment.setEq_type(eq_type);
			equipment.setEq_size(eq_size);
			return equipment;
		}
		
		
		
		
		
		
		
		
		
		
}
