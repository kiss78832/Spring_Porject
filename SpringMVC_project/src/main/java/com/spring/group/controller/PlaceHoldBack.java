package com.spring.group.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;
@WebServlet("/PlaceHoldBack.do")
public class PlaceHoldBack extends HttpServlet {
	


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		
		String action = req.getParameter("action");
		String tbodyHold = req.getParameter("tbodyHold");
		String locationHold = req.getParameter("locationHold");
		
		if("placeHoldBack".equals(action)) {/*選擇景點後儲存景點及選項們*/
			
			HttpSession session = req.getSession();
			
			List<String> bodyList = (List)session.getAttribute("bodyList");
			List<String> locationList = (List)session.getAttribute("locationList");
			if(bodyList==null||locationList==null) {
				bodyList = new LinkedList<String>();
				locationList = new LinkedList<String>();
			}
			
			bodyList.add(tbodyHold);
			locationList.add(locationHold);
			
			session.setAttribute("bodyList", bodyList);
			session.setAttribute("locationList", locationList);
			
			
			JSONObject jobj = new JSONObject();
			try {
				jobj.put("success", "success");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			out.println(jobj.toString());
			
		}else if("getBack".equals(action)) {/*拿到上次儲存的點及選項們*/
			
			HttpSession session = req.getSession();
			
			List<String> bodyList = (List)session.getAttribute("bodyList");
			List<String> locationList = (List)session.getAttribute("locationList");
			
			int boSize = bodyList.size();
			int loSize = locationList.size();
			
			JSONObject jobj = new JSONObject();
			
			try {
				jobj.put("tbodyHold", bodyList.get(boSize-1));
				jobj.put("locationHold", locationList.get(loSize-1));
			} catch (JSONException e) {
				e.printStackTrace();
			}
			bodyList.remove(boSize-1);
			locationList.remove(loSize-1);
			
			session.setAttribute("bodyList", bodyList);
			session.setAttribute("locationList", locationList);
			
			out.println(jobj.toString());
		}else if("endDay".equals(action)) {/*完成當日行程*/
			
			HttpSession session = req.getSession();
			
			List<String> bodyList = (List)session.getAttribute("bodyList");
			List<String> locationList = (List)session.getAttribute("locationList");
			
			JSONObject jobj = new JSONObject();
			
			String whereAmI = req.getParameter("whereAmI").trim();
			
			System.out.println("I'm here = "+whereAmI);
			try {
/*****************************此處添加住宿點及登山口*****************************/
			if("排雲山莊".equals(whereAmI)||"圓峰山屋".equals(whereAmI)||"塔塔加登山口".equals(whereAmI)
				||"東埔登山口".equals(whereAmI)||"南橫公路向陽登山口".equals(whereAmI)||"樂樂山屋".equals(whereAmI)||"中央金礦山屋".equals(whereAmI)||"白洋金礦山屋".equals(whereAmI)||"大水窟山屋".equals(whereAmI)||"塔芬谷山屋".equals(whereAmI)||"轆轆谷山屋".equals(whereAmI)||"拉庫音溪底山屋".trim().equals(whereAmI)
					||"南橫公路進涇橋登山口".trim().equals(whereAmI)||"南橫公路塔關山登山口".trim().equals(whereAmI)||"大關山隧道東側登山口".trim().equals(whereAmI)||"庫哈諾辛山屋".trim().equals(whereAmI)||"埡口山莊".trim().equals(whereAmI)
						||"向陽登山口".trim().equals(whereAmI)||"嘉明湖避難山屋".trim().equals(whereAmI)||"桃源營地".trim().equals(whereAmI)||"新仙山前營地".trim().equals(whereAmI)
							) {
					System.out.println("是住宿點");
					
				if("塔塔加登山口".equals(whereAmI)) {/**此處判斷是否為登山口**/
					jobj.put("Hiking_Gate", true);
					jobj.put("endOption", 1);
				}else if("東埔登山口".equals(whereAmI)){
					jobj.put("Hiking_Gate", true);
					jobj.put("endOption", 7);
				}else if("南橫公路向陽登山口".equals(whereAmI)){
					jobj.put("Hiking_Gate", true);
					jobj.put("endOption", 12);
				}else if("南橫公路進涇橋登山口".equals(whereAmI)){
					jobj.put("Hiking_Gate", true);
					jobj.put("endOption", 13);
				}else if("南橫公路塔關山登山口".equals(whereAmI)){
					jobj.put("Hiking_Gate", true);
					jobj.put("endOption", 14);
				}else if("大關山隧道東側登山口".equals(whereAmI)){
					jobj.put("Hiking_Gate", true);
					jobj.put("endOption", 15);
				}else if("向陽登山口".equals(whereAmI)){
					jobj.put("Hiking_Gate", true);
					jobj.put("endOption", 16);
				}else {
					jobj.put("Hiking_Gate", false);
				}
					
				
/********************************此處添加住宿點判斷********************************/
				if("排雲山莊".equals(whereAmI)) {
					jobj.put("endOption", 3);
				}else if("圓峰山屋".equals(whereAmI)) {
					jobj.put("endOption", 5);
				}else if("樂樂山屋".equals(whereAmI)) {
					jobj.put("endOption", 7);
				}else if("中央金礦山屋".equals(whereAmI)) {
					jobj.put("endOption", 7);
				}else if("白洋金礦山屋".equals(whereAmI)) {
					jobj.put("endOption", 7);
				}else if("大水窟山屋".equals(whereAmI)) {
					jobj.put("endOption", 8);
				}else if("塔芬谷山屋".equals(whereAmI)) {
					jobj.put("endOption", 9);
				}else if("轆轆谷山屋".equals(whereAmI)) {
					jobj.put("endOption", 10);
				}else if("拉庫音溪底山屋".equals(whereAmI)) {
					jobj.put("endOption", 12);
				}else if("庫哈諾辛山屋".equals(whereAmI)) {
					jobj.put("endOption", 13);
				}else if("埡口山莊".equals(whereAmI)) {
					jobj.put("endOption", 15);
				}else if("嘉明湖避難山屋".equals(whereAmI)) {
					jobj.put("endOption", 16);
				}else if("桃源營地".equals(whereAmI)) {
					jobj.put("endOption", 17);
				}else if("新仙山前營地".equals(whereAmI)) {
					jobj.put("endOption", 18);
				}
				
				jobj.put("lastEnd", true);

			}else {
					jobj.put("lastEnd", false);
			}
			
			
			} catch (JSONException e) {
				e.printStackTrace();
			}

			out.println(jobj.toString());
		}else if("clear".equals(action)) {
			HttpSession session = req.getSession();
			session.removeAttribute("bodyList");
			session.removeAttribute("locationList");
			
			JSONObject jobj = new JSONObject();
			
			try {
				jobj.put("clear", "clear");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.println(jobj.toString());
			
		}
	}

}
