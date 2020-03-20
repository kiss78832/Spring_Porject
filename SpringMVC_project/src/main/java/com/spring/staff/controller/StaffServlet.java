package com.spring.staff.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.spring.staff.model.StaffService;
import com.spring.staff.model.StaffVO;

public class StaffServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		
		String action = req.getParameter("action");
		
		if("INSERTSTAFF".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			HttpSession session = req.getSession();
			StaffService sSvc = new StaffService();
			StaffPasswordMail pass = new StaffPasswordMail();
			
			
			
			Integer sf_status = Integer.valueOf(req.getParameter("sf_status"));
			
			String sf_name = req.getParameter("sf_name");
			String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (sf_name == null || sf_name.trim().length() == 0) {
				errorMsgs.add("員工姓名 : 請勿空白");
			} else if(!sf_name.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("員工姓名 : 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
            }
			
			
			
			String sf_account = req.getParameter("sf_account");
			String Reg1 = "^[(a-zA-Z0-9_)]{2,10}$";
			if (sf_account == null || sf_account.trim().length() == 0) {
				errorMsgs.add("帳號 :  請勿空白");
			} else if(!sf_account.trim().matches(Reg1)) { //以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("帳號 : 只能是英文字母、數字和_ , 且長度必需在7到15之間");
            }else {
            	if(sSvc.compare(sf_account)) {
            		System.out.println("帳號存在");
            		errorMsgs.add("帳號 :  已被註冊");
            	}else {
            		System.out.println("帳號可使用");
            	}
            }
			
			
			
			
			Integer gender = Integer.valueOf(req.getParameter("gender"));
			
			
			String sf_email = req.getParameter("sf_email");
			String Reg3 = "^\\w{1,63}@[a-zA-Z0-9]{2,63}\\.[a-zA-Z]{2,63}(\\.[a-zA-Z]{2,63})?$";
			if (sf_email == null || sf_email.trim().length() == 0) {
				errorMsgs.add("Email : 請勿空白");
			} else if(!sf_email.trim().matches(Reg3)) { //以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("Email : 格式錯誤");
            }
					

			String cellphone = null;
			try {
				Integer Are_You_Ok = Integer.valueOf(req.getParameter("cellphone"));
				cellphone = req.getParameter("cellphone");
				System.out.println("cellphone = "+cellphone);
				String Reg4 = "^09[0-9]{8}$";
				if(cellphone==null||cellphone.trim().length()==0) {
					errorMsgs.add("電話 : 請勿空白");
				}
				else if(!cellphone.trim().matches(Reg4)){
					errorMsgs.add("電話 : 格式錯誤");
				}
			} catch (NumberFormatException e) {
				cellphone = "0";
				errorMsgs.add("電話 : 請填數字");
			}
			
			
			
			
			/*亂數產生密碼*/
			
			
			
			/**/
			
			StaffVO staffVO = new StaffVO();
			staffVO.setSf_status(sf_status);
			staffVO.setSf_account(sf_account);
			
			staffVO.setSf_name(sf_name);
			staffVO.setGender(gender);
			staffVO.setCellphone(cellphone);
			staffVO.setSf_email(sf_email);
			
			
			if (!errorMsgs.isEmpty()) {
				System.out.println("到底哪裡錯");
				req.setAttribute("staffVO", staffVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/staff/staffInsert.jsp");
				failureView.forward(req, res);
				return; //程式中斷
			}
			
			String sf_password = pass.sendMail(sf_email,sf_account);
			
			
			sSvc.addStaff(sf_status, sf_account, sf_password, sf_name, gender, cellphone, sf_email);
			
			System.out.println("Staff insert Success!");
			
			session.setAttribute("update", "success3");
			res.sendRedirect(getServletContext().getContextPath()+"/back-end/staff/listAllStaff.jsp");
			
			
		}else if("getOne_For_Update_staff".equals(action)) {
			
			
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL");	
			
			try {
				/***************************1.接收請求參數****************************************/
				String sf_id = req.getParameter("sf_id");
				
				/***************************2.開始查詢資料****************************************/
				StaffService sSvc = new StaffService();
				StaffVO staffVO = sSvc.getOneStaff(sf_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("staffVO", staffVO); 
				String url = "/back-end/staff/staffUpdate.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************其他可能的錯誤處理************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料取出時失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}else if("UPDATESTAFF".equals(action)){

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			HttpSession session = req.getSession();
			
			System.out.println("進入_sf_update");
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				
				String sf_id = new String(req.getParameter("sf_id").trim());
				
				
				String sf_name = req.getParameter("sf_name");
				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (sf_name == null || sf_name.trim().length() == 0) {
					errorMsgs.add("員工姓名: 請勿空白");
				} else if(!sf_name.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				String sf_account = req.getParameter("sf_account");
				String Reg1 = "^[(a-zA-Z0-9_)]{2,10}$";
				if (sf_account == null || sf_account.trim().length() == 0) {
					errorMsgs.add("帳號: 請勿空白");
				} else if(!sf_account.trim().matches(Reg1)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("帳號: 只能是英文字母、數字和_ , 且長度必需在7到15之間");
	            }
				
				

				
				
				String sf_email = req.getParameter("sf_email");
				String Reg3 = "^\\w{1,63}@[a-zA-Z0-9]{2,63}\\.[a-zA-Z]{2,63}(\\.[a-zA-Z]{2,63})?$";
				if (sf_email == null || sf_email.trim().length() == 0) {
					errorMsgs.add("Email: 請勿空白");
				} else if(!sf_email.trim().matches(Reg3)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("Email: 格式錯誤");
	            }
						

				String cellphone = null;
				try {
					
					cellphone = req.getParameter("cellphone");
					System.out.println("cellphone = "+cellphone);
					String Reg4 = "^09[0-9]{8}$";
					if(cellphone==null||cellphone.trim().length()==0) {
						errorMsgs.add("電話 : 請勿空白");
					}
					else if(!cellphone.trim().matches(Reg4)){
						errorMsgs.add("電話 : 格式錯誤");
					}
				} catch (NumberFormatException e) {
					cellphone = "0";
					errorMsgs.add("電話請填數字.");
				}

				Integer gender = new Integer(req.getParameter("gender").trim());
				Integer sf_status = new Integer(req.getParameter("sf_status").trim());
				

				StaffVO staffVO = new StaffVO();
				staffVO.setSf_id(sf_id);
				staffVO.setSf_status(sf_status);
				staffVO.setSf_account(sf_account);
				staffVO.setSf_name(sf_name);
				staffVO.setGender(gender);
				staffVO.setCellphone(cellphone);
				staffVO.setSf_email(sf_email);
				


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					
					req.setAttribute("staffVO", staffVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/staff/staffUpdate.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				System.out.println("開始修改資料");
				StaffService sSvc = new StaffService();
				staffVO = sSvc.updateStaff(sf_status, sf_account, sf_name, gender, cellphone, sf_email, sf_id);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/				

				
				session.setAttribute("update", "success2");
				String url = "/back-end/staff/listAllStaff.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);   // 修改成功後,轉交回送出修改的來源網頁
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/staff/staffUpdate.jsp");
				failureView.forward(req, res);
			}
		}
			
		}
		
	}
	
	


