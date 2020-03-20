package com.spring.equipment.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.spring.dailytypetotal.model.DailyTotalService;
import com.spring.dailytypetotal.model.DailyTotalVO;
import com.spring.equipment.model.EquipmentService;
import com.spring.equipment.model.EquipmentVO;
import com.spring.rentodlist.model.RentOdListVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class ShoppingCartServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();

		List<EquipmentVO> buylist = (Vector<EquipmentVO>) session.getAttribute("shoppingcart");
		String action = req.getParameter("action");

//		if (!action.equals("CHECKOUT")) {
//
//		
//			if (action.equals("DELETE")) {
//				if(buylist.size()==1) {
//					session.removeAttribute("shoppingcart");
//					session.removeAttribute("amount");
////					String url = "/front-end/renthome/renthome.jsp";
////					RequestDispatcher rd = req.getRequestDispatcher(url);
////					rd.forward(req, res);
//				}else {
//				String del = req.getParameter("del");
//				int d = Integer.parseInt(del);
//				buylist.remove(d);
//				}
//			}
//			
//			else if (action.equals("ADD")) {
//			
//				String quantity = req.getParameter("quantity");
//				String eq_num = req.getParameter("eq_num");
//				String eq_size = req.getParameter("eq_size");
//				EquipmentService equSVC = new EquipmentService();
//				EquipmentVO aequVO = equSVC.getOneEquipment(eq_num);
//				aequVO.setEq_size(eq_size);
//				aequVO.setQuantity((new Integer(quantity)).intValue());
//				if (buylist == null) {
//					buylist = new Vector<EquipmentVO>();
//					buylist.add(aequVO);
//				} else {
//					if (buylist.contains(aequVO)) {
//						EquipmentVO innerEqu = buylist.get(buylist.indexOf(aequVO));
//						innerEqu.setQuantity(innerEqu.getQuantity() + aequVO.getQuantity());
//					} else {
//						buylist.add(aequVO);
//					}
//				}
//			}
//			double total = 0;
//			for (int i = 0; i < buylist.size(); i++) {
//				EquipmentVO order = buylist.get(i);
//				Integer price = order.getEq_price();
//				Integer quantity = order.getQuantity();
//				total += (price * quantity);
//			}
//			String amount = String.valueOf(total);
//			session.setAttribute("amount", amount);
//
//
//			session.setAttribute("shoppingcart", buylist);
//			String url = getServletContext().getContextPath()+"/front-end/renthome/shoppingCart.jsp";
//			 res.sendRedirect(url);
//			
//		}
//
//		
//		else if (action.equals("CHECKOUT")) {
//			String url = "/front-end/renthome/orderform.jsp";
//			RequestDispatcher rd = req.getRequestDispatcher(url);
//			rd.forward(req, res);
//		}
//	}

		if (!action.equals("CHECKOUT")) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String url = req.getParameter("requestURL");
			if (action.equals("DELETE")) {
				if (buylist.size() == 1) {
					session.removeAttribute("shoppingcart");
					session.removeAttribute("amount");
//				String url ="/front-end/renthome/renthome.jsp";
//				RequestDispatcher rd = req.getRequestDispatcher("/front-end/renthome/renthome.jsp");
//				rd.forward(req, res);
					res.sendRedirect(getServletContext().getContextPath() + "/front-end/equipment/shoppingCart.jsp");
					return;
				} else {
					String del = req.getParameter("del");
					int d = Integer.parseInt(del);
					buylist.remove(d);
				}
			}

			else if (action.equals("ADD")) {

				String quantity = req.getParameter("quantity");

				if ((new Integer(quantity)).intValue() <= 0) {
					errorMsgs.add("請輸入大於0的數量");
				}

				String eq_name = req.getParameter("eq_name");
				String eq_num = req.getParameter("eq_num");
				String eq_size = req.getParameter("eq_size");

//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher successView = req
//					.getRequestDispatcher(url);
//					successView.forward(req, res);
//					return;
//				}

				EquipmentService equSVC = new EquipmentService();
				EquipmentVO aequVO = equSVC.getOneEquipmentByNameAndSize(eq_name, eq_size);
				aequVO.setQuantity((new Integer(quantity)).intValue());
				if (buylist == null) {
					buylist = new Vector<EquipmentVO>();
					buylist.add(aequVO);
				} else {
					boolean test = true;
					for (int i = 0; i < buylist.size(); i++) {
						if (buylist.get(i).getEq_name().equals(aequVO.getEq_name())
								&& buylist.get(i).getEq_size().equals(aequVO.getEq_size())) {
							EquipmentVO innerEqu = buylist.get(i);
							innerEqu.setQuantity(innerEqu.getQuantity() + aequVO.getQuantity());
							test = false;
						}
					}
					if (test) {
						buylist.add(aequVO);
					}

				}
			}
			Integer total = 0;
			for (int i = 0; i < buylist.size(); i++) {
				EquipmentVO order = buylist.get(i);
				Integer price = order.getEq_price();
				Integer quantity = order.getQuantity();
				total += (price * quantity);
			}
			String amount = String.valueOf(total);
			session.setAttribute("amount", amount);

			session.setAttribute("shoppingcart", buylist);
//			String url = getServletContext().getContextPath() + "/front-end/renthome/renthome.jsp";
			res.sendRedirect(url);

		}

		else if (action.equals("CHECKOUT")) {
			List<String> quantitystr = new ArrayList<String>();
			List<Integer> quannum = new ArrayList<Integer>();
			for (int i = 0; i < buylist.size(); i++) {
				quantitystr.add(req.getParameter("quantity" + i));
				System.out.println(req.getParameter("quantity" + i));
			}
			for (int i = 0; i < buylist.size(); i++) {
				String quanStr = quantitystr.get(i);
				int qnum = Integer.parseInt(quanStr);
				quannum.add(qnum);
			}
			for (int i = 0; i < buylist.size(); i++) {
				buylist.get(i).setQuantity(quannum.get(i));
			}

			Integer total = 0;
			for (int i = 0; i < buylist.size(); i++) {
				EquipmentVO order = buylist.get(i);
				Integer price = order.getEq_price();
				Integer quantity = order.getQuantity();
				total += (price * quantity);
			}
			String amount = String.valueOf(total);
			session.setAttribute("amount", amount);

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			java.sql.Date rsved_rent_date = java.sql.Date.valueOf(req.getParameter("rsved_rent_date").trim());

			java.sql.Date ex_return_date = java.sql.Date.valueOf(req.getParameter("ex_return_date").trim());

			List<EquipmentVO> equList = new ArrayList();
			try {
				DailyTotalService updateDate = new DailyTotalService();

				Boolean xx = false;
				for (int i = 0; i < buylist.size(); i++) {
					EquipmentVO order = buylist.get(i);
					Integer quantity = order.getQuantity();
					String type_eq_num = order.getType_eq_num();

					DailyTotalService sqlDate = new DailyTotalService();
					List<DailyTotalVO> listsqlDate = sqlDate.getsqlDate(type_eq_num, rsved_rent_date, ex_return_date);

					for (DailyTotalVO sqlDateVO : listsqlDate) {

						if (sqlDateVO.getStart_qty() + quantity > sqlDateVO.getDaily_eq_qty()) {

							EquipmentService equSVC = new EquipmentService();
							EquipmentVO equVO = equSVC.getOneTypeEqNum(type_eq_num);
							equVO.setQuantity(sqlDateVO.getDaily_eq_qty() - sqlDateVO.getStart_qty());
							equVO.setEq_detail(sqlDateVO.getEq_date().toString());
							equList.add(equVO);
							xx = true;
						}
					}
				}
				if (xx) {
					throw new Exception();
				}

			} catch (Exception e) {

				for (EquipmentVO equVO : equList) {
					String mg = null;
					mg = "在" + equVO.getEq_detail() + "商品名稱:" + equVO.getEq_name() + equVO.getEq_size() + "剩餘"
							+ equVO.getQuantity() + "個";
					errorMsgs.add(mg);
				}

			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/equipment/shoppingCart.jsp");
				successView.forward(req, res);
				return;
			}
			RentOdListVO rentodlistvo = new RentOdListVO();
			rentodlistvo.setEx_return_date(ex_return_date);
			rentodlistvo.setRsved_rent_date(rsved_rent_date);
			session.setAttribute("rentodlistvo", rentodlistvo);
			String url = "/front-end/rentodlist/orderform.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
		}
	}

//		if (action.equals("ADD")) {
//			HttpSession session = req.getSession();
//			List<EquipmentVO> buylist = (Vector<EquipmentVO>) session.getAttribute("shoppingcart");
//			EquipmentService equSVC = new EquipmentService();
//			EquipmentVO equVO = equSVC.getOneEquipment("eq_num");
//			session.setAttribute("equVO", equVO);
//			RequestDispatcher rd = req.getRequestDispatcher("/front-end/renthome/shoppingCart.jsp");
//			rd.forward(req, res);
//		} 
//		

}
