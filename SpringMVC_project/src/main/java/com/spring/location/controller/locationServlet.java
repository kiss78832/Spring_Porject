package com.spring.location.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.spring.dailyBed.model.DailyBedService;
import com.spring.location.model.LocationDAO;
import com.spring.location.model.LocationService;
import com.spring.location.model.LocationVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 20, maxRequestSize = 1024 * 1024 * 5 * 5)

public class locationServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("post");
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getSelect".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {

				String location_id = req.getParameter("location_id");

				LocationService locSvc = new LocationService();
				LocationVO locationVO = locSvc.getOneLocation(location_id);
				List<LocationVO> list = new LinkedList<LocationVO>();
				list.add(locationVO);
				
				HttpSession session = req.getSession();
				
				session.setAttribute("getOneLocation", list);
				
				String status_selected = (String) session.getAttribute("status_selected");
				status_selected = "one";
				session.setAttribute("status_selected" , status_selected);

				String type_selected = (String) session.getAttribute("type_selected");
				type_selected = "one";
				session.setAttribute("type_selected" , type_selected);
				
				String url = "/back-end/location/locationMge.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {

				errorMsgs.add("選擇套餐資料失敗" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/location/locationMge.jsp");
				failureView.forward(req, res);
			}

		}

		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {

				String location_id = new String(req.getParameter("location_id").trim());
				String location_idReg = "[A]{1}[0-9]{4}";
				if (location_id == null || location_id.trim().length() == 0) {
					errorMsgs.add("據點編號: 請勿空白");
				} else if (!location_id.trim().matches(location_idReg)) {
					errorMsgs.add("據點編號:只能是開頭為A後四個接數字且長度需在5");
				}

				// create new service object
				LocationService locSvc = new LocationService();

				boolean notShow = false;
				List<LocationVO> list = locSvc.getAll();
				for (LocationVO locationVO : list) {
					if (locationVO.getLocation_id().equals(location_id)) {
						errorMsgs.add("據點編號不得重複");
						notShow = true;
						location_id = "";
						break;
					}
				}

				String location_name = req.getParameter("location_name");
				String location_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";

				if (location_name == null || location_name.trim().length() == 0) {
					errorMsgs.add("據點名稱:請勿空白");
				} else if (!location_name.trim().matches(location_nameReg)) {
					errorMsgs.add("據點名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				
				Integer bedTotal_num = null;
				try {
					bedTotal_num = new Integer(req.getParameter("bedTotal_num").trim());

					if (bedTotal_num > 999 || bedTotal_num <= 0) {
						errorMsgs.add("床位總數須介於1~999之間");
					}

				} catch (NumberFormatException nfe) {
					bedTotal_num = 0;
					errorMsgs.add("床位總數請填數字");
				}

				Integer bed_price = null;
				try {
					bed_price = new Integer(req.getParameter("bed_price").trim());

					if (bed_price > 99999 || bed_price < 0) {
						errorMsgs.add("床位價格須介於0~99999之間");
					}

				} catch (NumberFormatException nfe) {
					bed_price = 0;
					errorMsgs.add("床位價格請填數字");
				}
				
				Part filePart = req.getPart("location_pic");
				byte[] location_pic = LocationDAO.getPictureByteArray(filePart);
				
				String strLoc_type = req.getParameter("loc_type");
				Integer loc_type = Integer.parseInt(strLoc_type);
				
				String strLocation_status = req.getParameter("location_status");
				Integer location_status = Integer.parseInt(strLocation_status);
				

				LocationVO locationVO = new LocationVO();

				locationVO.setLocation_id(location_id);
				locationVO.setLocation_name(location_name);
				locationVO.setBedTotal_num(bedTotal_num);
				locationVO.setBed_price(bed_price);
				locationVO.setLocation_pic(location_pic);
				locationVO.setLoc_type(loc_type);
				locationVO.setLocation_status(location_status);

				if (!errorMsgs.isEmpty()) {
					String base64Img = locSvc.Base64Img(locationVO.getLocation_pic());
					if (notShow == false) {
						req.setAttribute("base64Img", base64Img);
					}
					req.setAttribute("locationVO", locationVO);
					req.setAttribute("errorShow", "fail");
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/location/locationMge.jsp");
					failureView.forward(req, res);
					return;// break
				}
			
				locationVO = locSvc.addLocation(location_id, location_name, bedTotal_num, bed_price, location_pic, loc_type, location_status);

				//insert newLoc this month and next Month  dailyBedVO
				
				DailyBedService dailyBedSvc = new DailyBedService();
				
				LocalDate now = LocalDate.now();
				int nextMonthDays = now.plusMonths(1).lengthOfMonth();
				
				for (int i = 1; i <= nextMonthDays; i++) {
					
					Integer remaining_total = locationVO.getBedTotal_num();
					Integer provided_total = 0;
					java.sql.Date dailyBed_date = java.sql.Date.valueOf(now.plusMonths(1).withDayOfMonth(i));
					System.out.println(dailyBed_date);
					dailyBedSvc.addDailyBed(location_id, remaining_total, provided_total, dailyBed_date);
				}
				
				int thisMonthDays = now.lengthOfMonth();
					for (int i = 1; i <= thisMonthDays; i++) {
					
					Integer remaining_total = locationVO.getBedTotal_num();
					Integer provided_total = 0;
					java.sql.Date dailyBed_date = java.sql.Date.valueOf(now.withDayOfMonth(i));
					System.out.println(dailyBed_date);
					dailyBedSvc.addDailyBed(location_id, remaining_total, provided_total, dailyBed_date);
					}
				
				
				
				String url = "/back-end/location/locationMge.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/location/locationMge.jsp");
				failureView.forward(req, res);

			}

		}

		// delete data
		if ("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String location_id = new String(req.getParameter("location_id"));

				LocationService locSvc = new LocationService();
				locSvc.deleteLoc(location_id);

				String url = "/back-end/location/locationMge.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("刪除據點資料失敗" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/location/locationMge.jsp");
				failureView.forward(req, res);

			}

		}

		// update forward
		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String location_id = new String(req.getParameter("location_id").trim());
				LocationService locSvc = new LocationService();
				LocationVO locationVO = locSvc.getOneLocation(location_id);
				req.setAttribute("locationVO", locationVO);
				req.setAttribute("disabled", true);
				req.setAttribute("updateShow", "update");
				String url = "/back-end/location/locationMge.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("取得準備要更新的據點資料失敗 " + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/location/locationMge.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String location_id = new String(req.getParameter("location_id").trim());

				// create new service object
				LocationService locSvc = new LocationService();
				

				String location_name = req.getParameter("location_name");
				String location_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";

				if (location_name == null || location_name.trim().length() == 0) {
					errorMsgs.add("據點名稱:請勿空白");
				} else if (!location_name.trim().matches(location_nameReg)) {
					errorMsgs.add("據點名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				
				Integer bedTotal_num = null;
				try {
					bedTotal_num = new Integer(req.getParameter("bedTotal_num").trim());

					if (bedTotal_num > 999 || bedTotal_num <= 0) {
						errorMsgs.add("床位總數須介於1~999之間");
					}

				} catch (NumberFormatException nfe) {
					bedTotal_num = 0;
					errorMsgs.add("床位總數請填數字");
				}

				Integer bed_price = null;
				try {
					bed_price = new Integer(req.getParameter("bed_price").trim());

					if (bed_price > 99999 || bed_price < 0) {
						errorMsgs.add("床位價格須介於0~99999之間");
					}

				} catch (NumberFormatException nfe) {
					bed_price = 0;
					errorMsgs.add("床位價格請填數字");
				}
				
				Part filePart = req.getPart("location_pic");
				byte[] location_pic = LocationDAO.getPictureByteArray(filePart);
				
				String strLoc_type = req.getParameter("loc_type");
				Integer loc_type = Integer.parseInt(strLoc_type);
				
				String strLocation_status = req.getParameter("location_status");
				Integer location_status = Integer.parseInt(strLocation_status);
				
			
				LocationVO locationVO = new LocationVO();

				locationVO.setLocation_id(location_id);
				locationVO.setLocation_name(location_name);
				locationVO.setBedTotal_num(bedTotal_num);
				locationVO.setBed_price(bed_price);
				locationVO.setLocation_pic(location_pic);
				locationVO.setLoc_type(loc_type);
				locationVO.setLocation_status(location_status);

			
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("locationVO", locationVO);
					req.setAttribute("errorShow", "fail");
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/location/locationMge.jsp");
					failureView.forward(req, res);
					return;// break
				}
			
				locationVO = locSvc.updateLocation(location_id, location_name, bedTotal_num, bed_price, location_pic, loc_type, location_status);
				
				req.removeAttribute("locationVO");
				
				
				HttpSession session = req.getSession();
				String status_selected = (String) session.getAttribute("status_selected");
				status_selected = "all";
				session.setAttribute("status_selected" , status_selected);

				String type_selected = (String) session.getAttribute("type_selected");
				type_selected = "all";
				session.setAttribute("type_selected" , type_selected);
				
				
				String url = "/back-end/location/locationMge.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("更新據點資料失敗" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/location/locationMge.jsp");
				failureView.forward(req, res);

			}

		}

		if ("updateNotPic".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String location_id = new String(req.getParameter("location_id").trim());

				// create new service object
				LocationService locSvc = new LocationService();

				String location_name = req.getParameter("location_name");
				String location_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";

				if (location_name == null || location_name.trim().length() == 0) {
					errorMsgs.add("據點名稱:請勿空白");
				} else if (!location_name.trim().matches(location_nameReg)) {
					errorMsgs.add("據點名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				
				Integer bedTotal_num = null;
				try {
					bedTotal_num = new Integer(req.getParameter("bedTotal_num").trim());

					if (bedTotal_num > 999 || bedTotal_num <= 0) {
						errorMsgs.add("床位總數須介於1~999之間");
					}

				} catch (NumberFormatException nfe) {
					bedTotal_num = 0;
					errorMsgs.add("床位總數請填數字");
				}

				Integer bed_price = null;
				try {
					bed_price = new Integer(req.getParameter("bed_price").trim());

					if (bed_price > 99999 || bed_price < 0) {
						errorMsgs.add("床位價格須介於0~99999之間");
					}

				} catch (NumberFormatException nfe) {
					bed_price = 0;
					errorMsgs.add("床位價格請填數字");
				}
				
				String strLoc_type = req.getParameter("loc_type");
				Integer loc_type = Integer.parseInt(strLoc_type);
				
				String strLocation_status = req.getParameter("location_status");
				Integer location_status = Integer.parseInt(strLocation_status);
				

				LocationVO locationVO = new LocationVO();

				locationVO.setLocation_id(location_id);
				locationVO.setLocation_name(location_name);
				locationVO.setBedTotal_num(bedTotal_num);
				locationVO.setBed_price(bed_price);
				locationVO.setLoc_type(loc_type);
				locationVO.setLocation_status(location_status);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("locationVO", locationVO);
					req.setAttribute("errorShow", "fail");
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/location/locationMge.jsp");
					failureView.forward(req, res);
					return;
				}

				locationVO = locSvc.updateLocationNotPic(location_id, location_name, bedTotal_num, bed_price, loc_type, location_status);

				HttpSession session = req.getSession();
				String status_selected = (String) session.getAttribute("status_selected");
				status_selected = "all";
				session.setAttribute("status_selected" , status_selected);

				String type_selected = (String) session.getAttribute("type_selected");
				type_selected = "all";
				session.setAttribute("type_selected" , type_selected);
				
				
				
				req.setAttribute("locationVO", locationVO);
				String url = "/back-end/location/locationMge.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("更新據點資料失敗" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/location/locationMge.jsp");
				failureView.forward(req, res);

			}

		}

		if ("getAllByType".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String StrLoc_type = new String(req.getParameter("loc_type"));

				Integer loc_type = new Integer(StrLoc_type);
				
				System.out.println("loc_type=" + loc_type);

				String type_selected = "2";

				if (3 == (loc_type)) {
					type_selected = "1";
					System.out.println("loc_type=3 want to get all list");
					
					String status_selected = "all";
					HttpSession session = req.getSession();
					session.setAttribute("type_selected", type_selected);
					session.setAttribute("status_selected", status_selected);

					String url = "/back-end/location/locationMge.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
					return;
				}

				LocationService locSvc = new LocationService();
				List<LocationVO> list = locSvc.getLocsByLoc_type(loc_type);
				HttpSession session = req.getSession();

				session.setAttribute("LocsByLoc_type", list);
				session.setAttribute("type_selected", type_selected);
				
				String url = "/back-end/location/locationMge.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("取得據點資料失敗" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/location/locationMge.jsp");
				failureView.forward(req, res);
			}

		}
		
		
		if ("getAllByStatus".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				System.out.println("111");
				String StrLocation_status = new String(req.getParameter("location_status"));

				Integer location_status = new Integer(StrLocation_status);

				String status_selected = "2";

				if (3 == (location_status)) {
					status_selected = "1";
					String type_selected = "all";
					
					HttpSession session = req.getSession();
					session.setAttribute("status_selected", status_selected);
					session.setAttribute("type_selected", type_selected);
					
					String url = "/back-end/location/locationMge.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
					return;
				}

				LocationService locSvc = new LocationService();
				List<LocationVO> list = locSvc.getLocsByLoc_status(location_status);

				HttpSession session = req.getSession();

				session.setAttribute("LocsByLoc_status", list);

				session.setAttribute("status_selected", status_selected);

				String url = "/back-end/location/locationMge.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("取得據點資料失敗" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/location/locationMge.jsp");
				failureView.forward(req, res);
			}

		}
		
		

	}

}
