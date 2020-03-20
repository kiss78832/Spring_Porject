package com.spring.dailyBed.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.spring.dailyBed.model.DailyBedService;
import com.spring.dailyBed.model.DailyBedVO;
import com.spring.location.model.LocationService;
import com.spring.location.model.LocationVO;

@WebServlet("/dailyBedServlet")
public class dailyBedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public dailyBedServlet() {
		super();

	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		res.setContentType("text/html; charset=UTF-8");
		

		if ("getSelect".equals(action)) {

			String jsonStr = "";

			String location_id = req.getParameter("location_id");

			DailyBedService dailyBedSvc = new DailyBedService();

			List<DailyBedVO> list = dailyBedSvc.getAllByLoc_idAndDate(location_id,
					new Integer(req.getParameter("year")), new Integer(req.getParameter("month")) + 1);

			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

			jsonStr = gson.toJson(list);

			System.out.println(jsonStr);

			PrintWriter out = res.getWriter();

			out.print(jsonStr);
			out.flush();

		}

		if ("locSelect".equals(action)) {
			System.out.println("comcomcomlocselect");
			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String location_id = req.getParameter("location_id");
				LocationService locationSvc = new LocationService();
				LocationVO locationVO = locationSvc.getOneLocation(location_id);
				req.setAttribute("locationVO", locationVO);
				
				String url = "/front-end/dailyBed/Reserved_Query.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("選擇據點資料失敗" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/dailyBed/Reserved_Query.jsp");
				failureView.forward(req, res);
			}

		}
		
		if ("locStatusSelect".equals(action)) {

			Integer location_status = Integer.parseInt(req.getParameter("location_status"));
			
			System.out.println("location_status:"+ location_status);


			LocationService locSvc = new LocationService();

			List<LocationVO> list = locSvc.getLocsByLoc_status(location_status);
			
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

			String jsonStr = gson.toJson(list);

			System.out.println(jsonStr);

			PrintWriter out = res.getWriter();

			out.print(jsonStr);
			out.flush();

		}

	}

}
