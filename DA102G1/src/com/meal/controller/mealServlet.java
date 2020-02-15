package com.meal.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

//import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.json.JSONObject;

import com.meal.model.MealDAO;
import com.meal.model.MealService;
import com.meal.model.MealVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 20, maxRequestSize = 1024 * 1024 * 5 * 5)


@ServerEndpoint("/Meal/{userName}")
public class mealServlet extends HttpServlet {
	private static final Set<Session> connectedSessions = Collections.synchronizedSet(new HashSet<>());
	

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getSelect".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {

				String meal_id = req.getParameter("meal_id");

				MealService mealSvc = new MealService();
				MealVO mealVO = mealSvc.getOneMeal(meal_id);
				List<MealVO> list = new LinkedList<MealVO>();
				list.add(mealVO);
				
				HttpSession session = req.getSession();
				
				session.setAttribute("getOneMeal", list);
				
				String status_selected = (String) session.getAttribute("status_selected");
				status_selected = "4";
				session.setAttribute("status_selected" , status_selected);

				String url = "/back-end/meal/mealMge.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {

				errorMsgs.add("選擇套餐資料失敗" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/meal/mealMge.jsp");
				failureView.forward(req, res);
			}

		}

		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {

				String meal_id = new String(req.getParameter("meal_id").trim());
				String meal_idReg = "[M]{1}[0-9]{3}";
				if (meal_id == null || meal_id.trim().length() == 0) {
					errorMsgs.add("套餐編號: 請勿空白");
				} else if (!meal_id.trim().matches(meal_idReg)) {
					errorMsgs.add("套餐編號:只能是開頭為M後三個接數字且長度需在4");
				}

				// create new service object
				MealService mealSvc = new MealService();

				boolean notShow = false;
				List<MealVO> list = mealSvc.getAll();
				for (MealVO mealVO : list) {
					if (mealVO.getMeal_id().equals(meal_id)) {
						errorMsgs.add("套餐編號不得重複");
						notShow = true;
						meal_id = "";
						break;
					}
				}

				String meal_name = req.getParameter("meal_name");
				String meal_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";

				if (meal_name == null || meal_name.trim().length() == 0) {
					errorMsgs.add("套餐名稱:請勿空白");
				} else if (!meal_name.trim().matches(meal_nameReg)) {
					errorMsgs.add("套餐名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				Integer meal_price = null;
				try {
					meal_price = new Integer(req.getParameter("meal_price").trim());

					if (meal_price > 99999 || meal_price < 0) {
						errorMsgs.add("套餐價格介於0~99999之間");
					}

				} catch (NumberFormatException nfe) {
					meal_price = 0;
					errorMsgs.add("套餐價格請填數字");
				}

				String strMeal_status = req.getParameter("meal_status");
				Integer meal_status = Integer.parseInt(strMeal_status);

				String meal_content = req.getParameter("meal_content").trim();
				if (meal_content == null || meal_content.trim().length() == 0) {
					errorMsgs.add("套餐內容:請勿空白");
				}

				if (meal_content.trim().length() > 30) {
					errorMsgs.add("套餐內容長度不得大於30個字元");
				}

				Part filePart = req.getPart("meal_pic");
				byte[] meal_pic = MealDAO.getPictureByteArray(filePart);

				MealVO mealVO = new MealVO();
				mealVO.setMeal_id(meal_id);
				mealVO.setMeal_name(meal_name);
				mealVO.setMeal_price(meal_price);
				mealVO.setMeal_status(meal_status);
				mealVO.setMeal_content(meal_content);
				mealVO.setMeal_pic(meal_pic);

				if (!errorMsgs.isEmpty()) {
					String base64Img = mealSvc.Base64Img(mealVO.getMeal_pic());
					if (notShow == false) {
						req.setAttribute("base64Img", base64Img);
					}
					req.setAttribute("mealVO", mealVO);
					req.setAttribute("errorShow", "fail");
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/meal/mealMge.jsp");
					failureView.forward(req, res);
					return;// break
				}

				mealVO = mealSvc.addMeal(meal_id, meal_name, meal_price, meal_status, meal_content, meal_pic);

				
				JSONObject jobj = new JSONObject();
				
				jobj.put("action", "insertMeal");
				jobj.put("meal_id",meal_id);
				jobj.put("meal_name",meal_name);
				jobj.put("meal_price",meal_price);
				jobj.put("meal_content",meal_content);
				
				
				for (Session session : connectedSessions) {
					if (session.isOpen())
						session.getAsyncRemote().sendText(jobj.toString());
				}
				
				
				String url = "/back-end/meal/mealMge.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/meal/mealMge.jsp");
				failureView.forward(req, res);

			}

		}

		// delete data
		if ("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String meal_id = new String(req.getParameter("meal_id"));

				MealService mealSvc = new MealService();
				mealSvc.deleteMeal(meal_id);

				String url = "/back-end/meal/mealMge.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("刪除套餐資料失敗" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/meal/mealMge.jsp");
				failureView.forward(req, res);

			}

		}

		// update forward
		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String meal_id = new String(req.getParameter("meal_id").trim());
				MealService mealSvc = new MealService();
				MealVO mealVO = mealSvc.getOneMeal(meal_id);
				req.setAttribute("mealVO", mealVO);
				req.setAttribute("disabled", true);
				req.setAttribute("updateShow", "update");
				String url = "/back-end/meal/mealMge.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("取得準備要更新的套餐資料失敗 " + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/meal/mealMge.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String meal_id = new String(req.getParameter("meal_id").trim());

				String meal_name = req.getParameter("meal_name");
				String meal_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";

				if (meal_name == null || meal_name.trim().length() == 0) {
					errorMsgs.add("套餐名稱:請勿空白");
				} else if (!meal_name.trim().matches(meal_nameReg)) {
					errorMsgs.add("套餐名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				Integer meal_price = null;
				try {
					meal_price = new Integer(req.getParameter("meal_price").trim());

					if (meal_price > 99999 || meal_price < 0) {
						errorMsgs.add("套餐價格介於0~99999之間");
					}

				} catch (NumberFormatException nfe) {
					meal_price = 0;
					errorMsgs.add("套餐價格請填數字");
				}

				String strMeal_status = req.getParameter("meal_status");
				Integer meal_status = Integer.parseInt(strMeal_status);

				String meal_content = req.getParameter("meal_content").trim();
				if (meal_content == null || meal_content.trim().length() == 0) {
					errorMsgs.add("套餐內容:請勿空白");
				}
				if (meal_content.trim().length() > 30) {
					errorMsgs.add("套餐內容長度不得大於30個字元");
				}

				Part filePart = req.getPart("meal_pic");
				byte[] meal_pic = MealDAO.getPictureByteArray(filePart);

				MealVO mealVO = new MealVO();
				mealVO.setMeal_id(meal_id);
				mealVO.setMeal_name(meal_name);
				mealVO.setMeal_price(meal_price);
				mealVO.setMeal_status(meal_status);
				mealVO.setMeal_content(meal_content);
				mealVO.setMeal_pic(meal_pic);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("mealVO", mealVO);
					req.setAttribute("errorShow", "fail");
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/meal/mealMge.jsp");
					failureView.forward(req, res);
					return;// break
				}

				MealService mealSvc = new MealService();
				mealVO = mealSvc.updateMeal(meal_id, meal_name, meal_price, meal_status, meal_content, meal_pic);
				
				
				JSONObject jobj = new JSONObject();
				
				jobj.put("action", "updateMeal");
				jobj.put("meal_id",meal_id);
				jobj.put("meal_name",meal_name);
				jobj.put("meal_price",meal_price);
				jobj.put("meal_content",meal_content);
				
				
				for (Session session : connectedSessions) {
					if (session.isOpen())
						session.getAsyncRemote().sendText(jobj.toString());
				}
				
				
				req.removeAttribute("mealVO");
//				req.setAttribute("mealVO", mealVO);
				
				HttpSession session = req.getSession();
				String status_selected = (String) session.getAttribute("status_selected");
				status_selected = "all";
				session.setAttribute("status_selected" , status_selected);
				
				String url = "/back-end/meal/mealMge.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("更新套餐資料失敗" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/meal/mealMge.jsp");
				failureView.forward(req, res);

			}

		}

		if ("updateNotPic".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String meal_id = new String(req.getParameter("meal_id").trim());

				String meal_name = req.getParameter("meal_name");
				String meal_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";

				if (meal_name == null || meal_name.trim().length() == 0) {
					errorMsgs.add("套餐名稱:請勿空白");
				} else if (!meal_name.trim().matches(meal_nameReg)) {
					errorMsgs.add("套餐名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				Integer meal_price = null;
				try {
					meal_price = new Integer(req.getParameter("meal_price").trim());

					if (meal_price > 99999 || meal_price < 0) {
						errorMsgs.add("套餐價格介於0~99999之間");
					}

				} catch (NumberFormatException nfe) {
					meal_price = 0;
					errorMsgs.add("套餐價格請填數字");
				}

				String strMeal_status = req.getParameter("meal_status");
				Integer meal_status = Integer.parseInt(strMeal_status);

				String meal_content = req.getParameter("meal_content").trim();
				if (meal_content == null || meal_content.trim().length() == 0) {
					errorMsgs.add("套餐內容:請勿空白");
				}

				if (meal_content.trim().length() > 30) {
					errorMsgs.add("套餐內容長度不得大於30個字元");
				}

				MealVO mealVO = new MealVO();
				mealVO.setMeal_id(meal_id);
				mealVO.setMeal_name(meal_name);
				mealVO.setMeal_price(meal_price);
				mealVO.setMeal_status(meal_status);
				mealVO.setMeal_content(meal_content);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("mealVO", mealVO);
					req.setAttribute("errorShow", "fail");
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/meal/mealMge.jsp");
					failureView.forward(req, res);
					return;// break
				}

				MealService mealSvc = new MealService();
				mealVO = mealSvc.updateMealNotPic(meal_id, meal_name, meal_price, meal_status, meal_content);

				JSONObject jobj = new JSONObject();
				
				jobj.put("action", "updateMeal");
				jobj.put("meal_id",meal_id);
				jobj.put("meal_name",meal_name);
				jobj.put("meal_price",meal_price);
				jobj.put("meal_content",meal_content);
				
				
				for (Session session : connectedSessions) {
					if (session.isOpen())
						session.getAsyncRemote().sendText(jobj.toString());
				}
				
				
				
				req.setAttribute("mealVO", mealVO);
				
				HttpSession session = req.getSession();
				String status_selected = (String) session.getAttribute("status_selected");
				status_selected = "all";
				session.setAttribute("status_selected" , status_selected);
				
				
				String url = "/back-end/meal/mealMge.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("更新套餐資料失敗" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/meal/mealMge.jsp");
				failureView.forward(req, res);

			}

		}

		if ("getAllByStatus".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String Strmeal_status = new String(req.getParameter("meal_status"));

				Integer meal_status = new Integer(Strmeal_status);

				String status_selected = "2";

				if (3 == (meal_status)) {
					status_selected = "1";
					System.out.println("meal_status=3");
					
					HttpSession session = req.getSession();
					session.setAttribute("status_selected", status_selected);

					String url = "/back-end/meal/mealMge.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
					return;
				}

				MealService mealSvc = new MealService();
				List<MealVO> list = mealSvc.getMealsByMeal_status(meal_status);

				HttpSession session = req.getSession();

				session.setAttribute("MealsByMeal_status", list);

				session.setAttribute("status_selected", status_selected);

				String url = "/back-end/meal/mealMge.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("取得全部套餐資料失敗" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/meal/mealMge.jsp");
				failureView.forward(req, res);
			}

		}
		
		

	}

	
	//web socket methods
	
	@OnOpen
	public void onOpen(@PathParam("userName") String userName, Session userSession) throws IOException {
		connectedSessions.add(userSession);
		String text = String.format("Session ID = %s, connected; userName = %s", userSession.getId(), userName);
		System.out.println(text);
	}

	@OnMessage
	public void onMessage(Session userSession, String message) {
		for (Session session : connectedSessions) {
			if (session.isOpen())
				session.getAsyncRemote().sendText(message);
		}
		System.out.println("Message received: " + message);
	}

	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		connectedSessions.remove(userSession);
		String text = String.format("session ID = %s, disconnected; close code = %d; reason phrase = %s",
				userSession.getId(), reason.getCloseCode().getCode(), reason.getReasonPhrase());
		System.out.println(text);
	}

	@OnError
	public void onError(Session userSession, Throwable e) {
		System.out.println("Error: " + e.toString());
	}
	
}
