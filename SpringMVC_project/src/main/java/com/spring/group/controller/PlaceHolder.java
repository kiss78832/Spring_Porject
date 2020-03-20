package com.spring.group.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
@WebServlet("/PlaceHolder.do")
public class PlaceHolder extends HttpServlet{
	
	static String A1 = "'<tr><td><input type=\"radio\" id=\"A1\" onclick=\"javascript:doPost(1,$(this).next().html())\">&nbsp;<label for=\"A1\"><img src=\"/DA102G1/assets/images/Icon/hiking.png\">塔塔加登山口</label></td></tr>'"; 
	static String A2 = "'<tr><td><input type=\"radio\" id=\"A2\" onclick=\"javascript:doPost(1,$(this).next().html())\">&nbsp;<label for=\"A2\"><img src=\"/DA102G1/assets/images/Icon/spruce.png\">白木林</label></td></tr>'"; 
	static String A3 = "'<tr><td><input type=\"radio\" id=\"A3\" onclick=\"javascript:doPost(1,$(this).next().html())\">&nbsp;<label for=\"A3\"><img src=\"/DA102G1/assets/images/Icon/spruce.png\">大峭壁</label></td></tr>'"; 
	static String A4 = "'<tr><td><input type=\"radio\" id=\"A4\" onclick=\"javascript:doPost(2,$(this).next().html())\">&nbsp;<label for=\"A4\"><img src=\"/DA102G1/assets/images/Icon/placeholder.png\">玉山前峰</label></td></tr>'"; 
	static String A5 = "'<tr><td><input type=\"radio\" id=\"A5\" onclick=\"javascript:doPost(2,$(this).next().html())\">&nbsp;<label for=\"A5\"><img src=\"/DA102G1/assets/images/Icon/placeholder.png\">玉山西峰</label></td></tr>'"; 
	static String A6 = "'<tr><td><input type=\"radio\" id=\"A6\" onclick=\"javascript:doPost(3,$(this).next().html())\">&nbsp;<label for=\"A6\"><img src=\"/DA102G1/assets/images/Icon/sleep.png\">排雲山莊</label></td></tr>'"; 
	static String A7 = "'<tr><td><input type=\"radio\" id=\"A7\" onclick=\"javascript:doPost(3,$(this).next().html())\">&nbsp;<label for=\"A7\"><img src=\"/DA102G1/assets/images/Icon/placeholder.png\">玉山主峰</label></td></tr>'"; 
	static String A8 = "'<tr><td><input type=\"radio\" id=\"A8\" onclick=\"javascript:doPost(4,$(this).next().html())\">&nbsp;<label for=\"A8\"><label><img src=\"/DA102G1/assets/images/Icon/placeholder.png\">玉山東峰</label></td></tr>'"; 
	static String A9 = "'<tr><td><input type=\"radio\" id=\"A9\" onclick=\"javascript:doPost(4,$(this).next().html())\">&nbsp;<label for=\"A9\"><label><img src=\"/DA102G1/assets/images/Icon/placeholder.png\">玉山北峰</label></td></tr>'"; 
	static String A10 = "'<tr><td><input type=\"radio\" id=\"A10\" onclick=\"javascript:doPost(4,$(this).next().html())\">&nbsp;<label for=\"A10\"><img src=\"/DA102G1/assets/images/Icon/placeholder.png\">玉山北北峰</label></td></tr>'"; 
	static String A12 = "'<tr><td><input type=\"radio\" id=\"A12\" onclick=\"javascript:doPost(5,$(this).next().html())\">&nbsp;<label for=\"A12\"><img src=\"/DA102G1/assets/images/Icon/sleep.png\">圓峰山屋</label></td></tr>'";
	
	/*玉山南峰*/static String A13 = "'<tr><td><input type=\"radio\" id=\"A13\" onclick=\"javascript:doPost(6,$(this).next().html())\">&nbsp;<label for=\"A13\"><img src=\"/DA102G1/assets/images/Icon/placeholder.png\">玉山南峰</label></td></tr>'"; 
	/*小南山*/static String A14 = "'<tr><td><input type=\"radio\" id=\"A14\" onclick=\"javascript:doPost(6,$(this).next().html())\">&nbsp;<label for=\"A14\"><img src=\"/DA102G1/assets/images/Icon/placeholder.png\">玉山小南山</label></td></tr>'"; 
	/*東小南山*/static String A15 = "'<tr><td><input type=\"radio\" id=\"A15\" onclick=\"javascript:doPost(6,$(this).next().html())\">&nbsp;<label for=\"A15\"><img src=\"/DA102G1/assets/images/Icon/placeholder.png\">東小南山</label></td></tr>'"; 
	/*南玉山*/static String A16 = "'<tr><td><input type=\"radio\" id=\"A16\" onclick=\"javascript:doPost(6,$(this).next().html())\">&nbsp;<label for=\"A16\"><img src=\"/DA102G1/assets/images/Icon/placeholder.png\">南玉山</label></td></tr>'"; 
	/*鹿山*/static String A17 = "'<tr><td><input type=\"radio\" id=\"A17\" onclick=\"javascript:doPost(6,$(this).next().html())\">&nbsp;<label for=\"A17\"><img src=\"/DA102G1/assets/images/Icon/placeholder.png\">鹿山</label></td></tr>'";
	
	/************************************南二線************************************/
	static String B1 = "'<tr><td><input type=\"radio\" id=\"B1\" onclick=\"javascript:doPost(7,$(this).next().html())\">&nbsp;<label for=\"B1\"><img src=\"/DA102G1/assets/images/Icon/hiking.png\">東埔登山口</label></td></tr>'"; 
	static String B2 = "'<tr><td><input type=\"radio\" id=\"B2\" onclick=\"javascript:doPost(7,$(this).next().html())\">&nbsp;<label for=\"B2\"><img src=\"/DA102G1/assets/images/Icon/sleep.png\">樂樂山屋</label></td></tr>'"; 
	static String B3 = "'<tr><td><input type=\"radio\" id=\"B3\" onclick=\"javascript:doPost(7,$(this).next().html())\">&nbsp;<label for=\"B3\"><img src=\"/DA102G1/assets/images/Icon/placeholder.png\">八通關</label></td></tr>'"; 
	static String B4 = "'<tr><td><input type=\"radio\" id=\"B4\" onclick=\"javascript:doPost(7,$(this).next().html())\">&nbsp;<label for=\"B4\"><img src=\"/DA102G1/assets/images//Icon/placeholder.png\">巴奈伊克</label></td></tr>'"; 
	static String B5 = "'<tr><td><input type=\"radio\" id=\"B5\" onclick=\"javascript:doPost(7,$(this).next().html())\">&nbsp;<label for=\"B5\"><img src=\"/DA102G1/assets/images/Icon/sleep.png\">中央金礦山屋</label></td></tr>'"; 
	static String B6 = "'<tr><td><input type=\"radio\" id=\"B6\" onclick=\"javascript:doPost(7,$(this).next().html())\">&nbsp;<label for=\"B6\"><img src=\"/DA102G1/assets/images/Icon/sleep.png\">白洋金礦山屋</label></td></tr>'"; 
	static String B7 = "'<tr><td><input type=\"radio\" id=\"B7\" onclick=\"javascript:doPost(8,$(this).next().html())\">&nbsp;<label for=\"B7\"><img src=\"/DA102G1/assets/images/Icon/placeholder.png\">秀姑坪</label></td></tr>'"; 
	static String B8 = "'<tr><td><input type=\"radio\" id=\"B8\" onclick=\"javascript:doPost(8,$(this).next().html())\">&nbsp;<label for=\"B8\"><img src=\"/DA102G1/assets/images/Icon/placeholder.png\">大水窟山</label></td></tr>'"; 
	static String B9 = "'<tr><td><input type=\"radio\" id=\"B9\" onclick=\"javascript:doPost(8,$(this).next().html())\">&nbsp;<label for=\"B9\"><img src=\"/DA102G1/assets/images/Icon/sleep.png\">大水窟山屋</label></td></tr>'"; 
	static String B10 = "'<tr><td><input type=\"radio\" id=\"B10\" onclick=\"javascript:doPost(8,$(this).next().html())\">&nbsp;<label for=\"B10\"><img src=\"/DA102G1/assets/images/Icon/placeholder.png\">南大水窟山</label></td></tr>'"; 
	static String B11 = "'<tr><td><input type=\"radio\" id=\"B11\" onclick=\"javascript:doPost(9,$(this).next().html())\">&nbsp;<label for=\"B11\"><img src=\"/DA102G1/assets/images/Icon/placeholder.png\">達芬尖山</label></td></tr>'"; 
	static String B12 = "'<tr><td><input type=\"radio\" id=\"B12\" onclick=\"javascript:doPost(9,$(this).next().html())\">&nbsp;<label for=\"B12\"><img src=\"/DA102G1/assets/images/Icon/sleep.png\">塔芬谷山屋</label></td></tr>'"; 
	static String B13 = "'<tr><td><input type=\"radio\" id=\"B13\" onclick=\"javascript:doPost(9,$(this).next().html())\">&nbsp;<label for=\"B13\"><img src=\"/DA102G1/assets/images/Icon/placeholder.png\">塔芬山</label></td></tr>'"; 
	static String B14 = "'<tr><td><input type=\"radio\" id=\"B14\" onclick=\"javascript:doPost(10,$(this).next().html())\">&nbsp;<label for=\"B14\"><img src=\"/DA102G1/assets/images/Icon/spruce.png\">塔芬池</label></td></tr>'"; 
	static String B15 = "'<tr><td><input type=\"radio\" id=\"B15\" onclick=\"javascript:doPost(10,$(this).next().html())\">&nbsp;<label for=\"B15\"><img src=\"/DA102G1/assets/images/Icon/placeholder.png\">轆轆山</label></td></tr>'"; 
	static String B16 = "'<tr><td><input type=\"radio\" id=\"B16\" onclick=\"javascript:doPost(10,$(this).next().html())\">&nbsp;<label for=\"B16\"><img src=\"/DA102G1/assets/images/Icon/sleep.png\">轆轆谷山屋</label></td></tr>'"; 
	static String B17 = "'<tr><td><input type=\"radio\" id=\"B17\" onclick=\"javascript:doPost(11,$(this).next().html())\">&nbsp;<label for=\"B17\"><img src=\"/DA102G1/assets/images/Icon/placeholder.png\">雲峰</label></td></tr>'";
	static String B18 = "'<tr><td><input type=\"radio\" id=\"B18\" onclick=\"javascript:doPost(11,$(this).next().html())\">&nbsp;<label for=\"B18\"><img src=\"/DA102G1/assets/images/Icon/placeholder.png\">南雙頭山</label></td></tr>'"; 
	static String B19 = "'<tr><td><input type=\"radio\" id=\"B19\" onclick=\"javascript:doPost(12,$(this).next().html())\">&nbsp;<label for=\"B19\"><img src=\"/DA102G1/assets/images/Icon/sleep.png\">拉庫音溪底山屋</label></td></tr>'"; 
	static String B20 = "'<tr><td><input type=\"radio\" id=\"B20\" onclick=\"javascript:doPost(12,$(this).next().html())\">&nbsp;<label for=\"B20\"><img src=\"/DA102G1/assets/images/Icon/spruce.png\">嘉明湖</label></td></tr>'"; 
	static String B21 = "'<tr><td><input type=\"radio\" id=\"B21\" onclick=\"javascript:doPost(12,$(this).next().html())\">&nbsp;<label for=\"B21\"><img src=\"/DA102G1/assets/images/Icon/placeholder.png\">三叉山</label></td></tr>'"; 
	static String B22 = "'<tr><td><input type=\"radio\" id=\"B22\" onclick=\"javascript:doPost(12,$(this).next().html())\">&nbsp;<label for=\"B22\"><img src=\"/DA102G1/assets/images/Icon/placeholder.png\">向陽山</label></td></tr>'"; 
	static String B23 = "'<tr><td><input type=\"radio\" id=\"B23\" onclick=\"javascript:doPost(12,$(this).next().html())\">&nbsp;<label for=\"B23\"><img src=\"/DA102G1/assets/images/Icon/hiking.png\">南橫公路向陽登山口</label></td></tr>'"; 

	/************************************南橫三山及關山線************************************/
	static String C1 = "'<tr><td><input type=\"radio\" id=\"C1\" onclick=\"javascript:doPost(13,$(this).next().html())\">&nbsp;<label for=\"C1\"><img src=\"/DA102G1/assets/images/Icon/hiking.png\">南橫公路進涇橋登山口</label></td></tr>'"; 
	static String C2 = "'<tr><td><input type=\"radio\" id=\"C2\" onclick=\"javascript:doPost(13,$(this).next().html())\">&nbsp;<label for=\"C2\"><img src=\"/DA102G1/assets/images/Icon/sleep.png\">庫哈諾辛山屋</label></td></tr>'"; 
	static String C3 = "'<tr><td><input type=\"radio\" id=\"C3\" onclick=\"javascript:doPost(13,$(this).next().html())\">&nbsp;<label for=\"C3\"><img src=\"/DA102G1/assets/images/Icon/placeholder.png\">庫哈諾辛山</label></td></tr>'";
	static String C4 = "'<tr><td><input type=\"radio\" id=\"C4\" onclick=\"javascript:doPost(13,$(this).next().html())\">&nbsp;<label for=\"C4\"><img src=\"/DA102G1/assets/images/Icon/placeholder.png\">關山北峰</label></td></tr>'"; 
	static String C5 = "'<tr><td><input type=\"radio\" id=\"C5\" onclick=\"javascript:doPost(13,$(this).next().html())\">&nbsp;<label for=\"C5\"><img src=\"/DA102G1/assets/images/Icon/placeholder.png\">關山</label></td></tr>'"; 
	static String C6 = "'<tr><td><input type=\"radio\" id=\"C6\" onclick=\"javascript:doPost(14,$(this).next().html())\">&nbsp;<label for=\"C6\"><img src=\"/DA102G1/assets/images/Icon/hiking.png\">南橫公路塔關山登山口</label></td></tr>'"; 
	static String C7 = "'<tr><td><input type=\"radio\" id=\"C7\" onclick=\"javascript:doPost(14,$(this).next().html())\">&nbsp;<label for=\"C7\"><img src=\"/DA102G1/assets/images/Icon/placeholder.png\">塔關山</label></td></tr>'"; 
	static String C8 = "'<tr><td><input type=\"radio\" id=\"C8\" onclick=\"javascript:doPost(15,$(this).next().html())\">&nbsp;<label for=\"C8\"><img src=\"/DA102G1/assets/images/Icon/sleep.png\">埡口山莊</label></td></tr>'";
	static String C9 = "'<tr><td><input type=\"radio\" id=\"C9\" onclick=\"javascript:doPost(15,$(this).next().html())\">&nbsp;<label for=\"C9\"><img src=\"/DA102G1/assets/images/Icon/placeholder.png\">關山嶺山</label></td></tr>'"; 
	static String C10 = "'<tr><td><input type=\"radio\" id=\"C10\" onclick=\"javascript:doPost(15,$(this).next().html())\">&nbsp;<label for=\"C10\"><img src=\"/DA102G1/assets/images/Icon/hiking.png\">大關山隧道東側登山口</label></td></tr>'"; 
	
	/************************************新康斷線線************************************/
	static String D1 = "'<tr><td><input type=\"radio\" id=\"D1\" onclick=\"javascript:doPost(16,$(this).next().html())\">&nbsp;<label for=\"D1\"><img src=\"/DA102G1/assets/images/Icon/hiking.png\">向陽登山口</label></td></tr>'"; 
	static String D2 = "'<tr><td><input type=\"radio\" id=\"D2\" onclick=\"javascript:doPost(16,$(this).next().html())\">&nbsp;<label for=\"D2\"><img src=\"/DA102G1/assets/images/Icon/placeholder.png\">三叉山</label></td></tr>'";
	static String D3 = "'<tr><td><input type=\"radio\" id=\"D3\" onclick=\"javascript:doPost(16,$(this).next().html())\">&nbsp;<label for=\"D3\"><img src=\"/DA102G1/assets/images/Icon/sleep.png\">嘉明湖避難山屋</label></td></tr>'"; 
	static String D4 = "'<tr><td><input type=\"radio\" id=\"D4\" onclick=\"javascript:doPost(17,$(this).next().html())\">&nbsp;<label for=\"D4\"><img src=\"/DA102G1/assets/images/Icon/placeholder.png\">布新叉路口</label></td></tr>'";
	static String D5 = "'<tr><td><input type=\"radio\" id=\"D5\" onclick=\"javascript:doPost(17,$(this).next().html())\">&nbsp;<label for=\"D5\"><img src=\"/DA102G1/assets/images/Icon/sleep.png\">桃源營地</label></td></tr>'"; 
	static String D6 = "'<tr><td><input type=\"radio\" id=\"D6\" onclick=\"javascript:doPost(18,$(this).next().html())\">&nbsp;<label for=\"D6\"><img src=\"/DA102G1/assets/images/Icon/placeholder.png\">新仙山</label></td></tr>'";
	static String D7 = "'<tr><td><input type=\"radio\" id=\"D7\" onclick=\"javascript:doPost(18,$(this).next().html())\">&nbsp;<label for=\"D7\"><img src=\"/DA102G1/assets/images/Icon/placeholder.png\">新康山</label></td></tr>'";
	static String D8 = "'<tr><td><input type=\"radio\" id=\"D8\" onclick=\"javascript:doPost(18,$(this).next().html())\">&nbsp;<label for=\"D8\"><img src=\"/DA102G1/assets/images/Icon/sleep.png\">新仙山前營地</label></td></tr>'"; 
	static String D9 = "'<tr><td><input type=\"radio\" id=\"D9\" onclick=\"javascript:doPost(16,$(this).next().html())\">&nbsp;<label for=\"D9\"><img src=\"/DA102G1/assets/images/Icon/placeholder.png\">向陽山</label></td></tr>'";
	/*嘉明湖*/static String D10 = "'<tr><td><input type=\"radio\" id=\"D10\" onclick=\"javascript:doPost(16,$(this).next().html())\">&nbsp;<label for=\"D10\"><img src=\"/DA102G1/assets/images/Icon/spruce.png\">嘉明湖</label></td></tr>'"; 
	
	/************************************八通關************************************/
//	static String E1 = "'<tr><td><input type=\"radio\" id=\"E1\" onclick=\"javascript:doPost(19,$(this).next().html())\">&nbsp;<label for=\"E1\"><img src=\"/DA102G1/assets/images/Icon/hiking.png\">東埔登山入口</label></td></tr>'"; 
//	static String E2 = "'<tr><td><input type=\"radio\" id=\"E2\" onclick=\"javascript:doPost(19,$(this).next().html())\">&nbsp;<label for=\"E2\"><img src=\"/DA102G1/assets/images/Icon/sleep.png\">樂樂山屋</label></td></tr>'"; 
//	static String E3 = "'<tr><td><input type=\"radio\" id=\"E3\" onclick=\"javascript:doPost(19,$(this).next().html())\">&nbsp;<label for=\"E3\"><img src=\"/DA102G1/assets/images/Icon/placeholder.png\">八通關</label></td></tr>'"; 
//	static String E4 = "'<tr><td><input type=\"radio\" id=\"E4\" onclick=\"javascript:doPost(19,$(this).next().html())\">&nbsp;<label for=\"E4\"><img src=\"/DA102G1/assets/images//Icon/placeholder.png\">巴奈伊克</label></td></tr>'"; 
//	static String E5 = "'<tr><td><input type=\"radio\" id=\"E5\" onclick=\"javascript:doPost(19,$(this).next().html())\">&nbsp;<label for=\"E5\"><img src=\"/DA102G1/assets/images/Icon/sleep.png\">中央金礦山屋</label></td></tr>'"; 
//	static String E6 = "'<tr><td><input type=\"radio\" id=\"E6\" onclick=\"javascript:doPost(19,$(this).next().html())\">&nbsp;<label for=\"E6\"><img src=\"/DA102G1/assets/images/Icon/sleep.png\">白洋金礦山屋</label></td></tr>'"; 
//	static String E7 = "'<tr><td><input type=\"radio\" id=\"E7\" onclick=\"javascript:doPost(19,$(this).next().html())\">&nbsp;<label for=\"E7\"><img src=\"/DA102G1/assets/images/Icon/placeholder.png\">秀姑坪</label></td></tr>'"; 
//	static String E8 = "'<tr><td><input type=\"radio\" id=\"E8\" onclick=\"javascript:doPost(19,$(this).next().html())\">&nbsp;<label for=\"E8\"><img src=\"/DA102G1/assets/images/Icon/placeholder.png\">大水窟山</label></td></tr>'"; 
//	static String E9 = "'<tr><td><input type=\"radio\" id=\"E9\" onclick=\"javascript:doPost(19,$(this).next().html())\">&nbsp;<label for=\"E9\"><img src=\"/DA102G1/assets/images/Icon/sleep.png\">大水窟山屋</label></td></tr>'"; 
//	static String E10 = "'<tr><td><input type=\"radio\" id=\"E10\" onclick=\"javascript:doPost(20,$(this).next().html())\">&nbsp;<label for=\"E10\"><img src=\"/DA102G1/assets/images/Icon/sleep.png\">托馬斯駐在所</label></td></tr>'"; 
//	static String E11 = "'<tr><td><input type=\"radio\" id=\"E11\" onclick=\"javascript:doPost(20,$(this).next().html())\">&nbsp;<label for=\"E11\"><img src=\"/DA102G1/assets/images/Icon/placeholder.png\">多美麗</label></td></tr>'"; 
//	static String E12 = "'<tr><td><input type=\"radio\" id=\"E12\" onclick=\"javascript:doPost(20,$(this).next().html())\">&nbsp;<label for=\"E12\"><img src=\"/DA102G1/assets/images/Icon/placeholder.png\">抱崖山屋</label></td></tr>'"; 
//	static String E13 = "'<tr><td><input type=\"radio\" id=\"E13\" onclick=\"javascript:doPost(21,$(this).next().html())\">&nbsp;<label for=\"E13\"><img src=\"/DA102G1/assets/images/Icon/placeholder.png\">瓦拉米山屋</label></td></tr>'"; 
//	static String E14 = "'<tr><td><input type=\"radio\" id=\"E14\" onclick=\"javascript:doPost(21,$(this).next().html())\">&nbsp;<label for=\"E14\"><img src=\"/DA102G1/assets/images//Icon/placeholder.png\">黃麻</label></td></tr>'"; 
//	static String E15 = "'<tr><td><input type=\"radio\" id=\"E15\" onclick=\"javascript:doPost(21,$(this).next().html())\">&nbsp;<label for=\"E15\"><img src=\"/DA102G1/assets/images//Icon/placeholder.png\">佳心</label></td></tr>'"; 
//	static String E16 = "'<tr><td><input type=\"radio\" id=\"E16\" onclick=\"javascript:doPost(21,$(this).next().html())\">&nbsp;<label for=\"E16\"><img src=\"/DA102G1/assets/images/Icon/hiking.png\">山風登山口</label></td></tr>'"; 

	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException,ServletException {
		doPost(req,res);
	}
	
	public void doPost(HttpServletRequest req , HttpServletResponse res) throws IOException,ServletException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = res.getWriter();
		
		String action = req.getParameter("action");
		System.out.println("i'm in post");
		
		if("getPlace".equals(action)) {
			
			String option = req.getParameter("option");
			String location = req.getParameter("location");
			
			int index = location.indexOf(">");
			String location1 = location.substring(index+1);
			
			System.out.println("option = "+option);
			System.out.println("location = "+location);
/*==================================玉山線  start====================================*/		
			if("1".equals(option)) {
				Map<String,String> map = new LinkedHashMap<String,String>();
				
				map.put("塔塔加登山口", A1);
				map.put("玉山前峰", A4);
				map.put("白木林", A2);
				map.put("大峭壁", A3);
				map.put("排雲山莊",A6 );
				map.put("玉山西峰", A5);
				map.put("玉山主峰", A7);
				map.put("圓峰山屋", A12);
				
				map.remove(location1);
				
				JSONObject jobj = new JSONObject();
				
				try {
					jobj.put("location", location);
					jobj.put("option", map);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
				out.println(jobj.toString());
			}else if("2".equals(option)) {
				Map<String,String> map = new LinkedHashMap<String,String>();
				
				map.put("塔塔加登山口", A1);
				map.put("玉山前峰", A4);
				map.put("白木林", A2);
				map.put("大峭壁", A3);
				map.put("排雲山莊",A6 );
				map.put("玉山西峰", A5);

				
				map.remove(location1);
				
				JSONObject jobj = new JSONObject();
				
				try {
					jobj.put("location", location);
					jobj.put("option", map);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
				out.println(jobj.toString());
			}else if("3".equals(option)) {
				Map<String,String> map = new LinkedHashMap<String,String>();

				map.put("塔塔加登山口", A1);
				map.put("排雲山莊", A6);
				map.put("玉山西峰", A5);
				map.put("玉山主峰", A7);
				map.put("玉山東峰", A8);
				map.put("玉山北峰", A9);
				map.put("玉山北北峰", A10);
				map.put("圓峰山屋", A12);

				
				map.remove(location1);
				
				JSONObject jobj = new JSONObject();
				
				try {
					jobj.put("location", location);
					jobj.put("option", map);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
				out.println(jobj.toString());
				
			}else if("4".equals(option)) {
				Map<String,String> map = new LinkedHashMap<String,String>();
				
				map.put("排雲山莊", A6);
				map.put("玉山主峰", A7);
				map.put("玉山東峰", A8);
				map.put("玉山北峰", A9);
				map.put("玉山北北峰", A10);
				map.put("圓峰山屋", A12);

				map.remove(location1);
				
				JSONObject jobj = new JSONObject();
				
				try {
					jobj.put("location", location);
					jobj.put("option", map);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
				out.println(jobj.toString());
				
				
			}else if("5".equals(option)) {

				Map<String,String> map = new LinkedHashMap<String,String>();
				
				map.put("排雲山莊", A6);
				map.put("塔塔加登山口", A1);
				map.put("玉山主峰", A7);
				map.put("玉山東峰", A8);
				map.put("玉山北峰", A9);
				map.put("玉山北北峰", A10);
				map.put("玉山南峰", A13);
				map.put("小南山", A14);
				map.put("東小南山", A15);
				map.put("南玉山", A16);
				map.put("鹿山", A17);

				map.remove(location1);
				
				JSONObject jobj = new JSONObject();
				
				try {
					jobj.put("location", location);
					jobj.put("option", map);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
				out.println(jobj.toString());

				
				
				
			}else if("6".equals(option)) {

				Map<String,String> map = new LinkedHashMap<String,String>();
				
				map.put("圓峰山屋", A12);
				map.put("玉山南峰", A13);
				map.put("小南山", A14);
				map.put("東小南山", A15);
				map.put("南玉山", A16);
				map.put("鹿山", A17);

				map.remove(location1);
				
				JSONObject jobj = new JSONObject();
				
				try {
					jobj.put("location", location);
					jobj.put("option", map);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
				out.println(jobj.toString());
			}
/*==================================玉山線  end====================================*/			
			

/*==================================南二線  start====================================*/		
			if("7".equals(option)) {
				Map<String,String> map = new LinkedHashMap<String,String>();
				
				map.put("東埔登山口", B1);
				map.put("樂樂山屋", B2);
				map.put("八通關", B3);
				map.put("巴奈伊克", B4);
				map.put("中央金礦山屋",B5 );
				map.put("白洋金礦山屋", B6);
				map.put("大水窟山", B8);
				map.remove(location1);
				
				JSONObject jobj = new JSONObject();
				
				try {
					jobj.put("location", location);
					jobj.put("option", map);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
				out.println(jobj.toString());
			}else if("8".equals(option)) {
				Map<String,String> map = new LinkedHashMap<String,String>();
				
				map.put("東埔登山口", B1);
				map.put("樂樂山屋", B2);
				map.put("八通關", B3);
				map.put("巴奈伊克", B4);
				map.put("中央金礦山屋",B5 );
				map.put("白洋金礦山屋", B6);
				map.put("秀姑坪", B7);
				map.put("大水窟山", B8);
				map.put("大水窟山屋", B9);
				map.put("南大水窟山", B10);
				map.put("達芬尖山", B11);

				
				map.remove(location1);
				
				JSONObject jobj = new JSONObject();
				
				try {
					jobj.put("location", location);
					jobj.put("option", map);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
				out.println(jobj.toString());
			}else if("9".equals(option)) {
				Map<String,String> map = new LinkedHashMap<String,String>();
				
				map.put("八通關", B3);
				map.put("巴奈伊克", B4);
				map.put("中央金礦山屋",B5 );
				map.put("白洋金礦山屋", B6);
				map.put("中央金礦山屋",B5 );
				map.put("白洋金礦山屋", B6);
				map.put("秀姑坪", B7);
				map.put("大水窟山", B8);
				map.put("大水窟山屋", B9);
				map.put("南大水窟山", B10);
				map.put("達芬尖山", B11);
				map.put("塔芬谷山屋", B12);
				map.put("塔芬池", B14);
				
				map.remove(location1);
				
				JSONObject jobj = new JSONObject();
				
				try {
					jobj.put("location", location);
					jobj.put("option", map);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
				out.println(jobj.toString());
				
			}else if("10".equals(option)) {
				Map<String,String> map = new LinkedHashMap<String,String>();
				
				map.put("大水窟山屋", B9);
				map.put("南大水窟山", B10);
				map.put("達芬尖山", B11);
				map.put("塔芬谷山屋", B12);
				map.put("塔芬山", B13);
				map.put("塔芬池", B14);
				map.put("轆轆山", B15);
				map.put("轆轆谷山屋", B16);
				map.put("雲峰", B17);

				map.remove(location1);
				
				JSONObject jobj = new JSONObject();
				
				try {
					jobj.put("location", location);
					jobj.put("option", map);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
				out.println(jobj.toString());
				
				
			}else if("11".equals(option)) {

				Map<String,String> map = new LinkedHashMap<String,String>();
				map.put("塔芬谷山屋", B12);
				map.put("塔芬池", B14);
				map.put("轆轆山", B15);
				map.put("轆轆谷山屋", B16);
				map.put("雲峰", B17);
				map.put("南雙頭山", B18);
				map.put("嘉明湖", B20);
				map.put("三叉山", B21);
				map.put("拉庫音溪底山屋", B19);
				map.remove(location1);
				
				JSONObject jobj = new JSONObject();
				
				try {
					jobj.put("location", location);
					jobj.put("option", map);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
				out.println(jobj.toString());
					
			}else if("12".equals(option)) {

				Map<String,String> map = new LinkedHashMap<String,String>();
				map.put("南雙頭山", B18);
				map.put("拉庫音溪底山屋", B19);
				map.put("嘉明湖", B20);
				map.put("三叉山", B21);
				map.put("向陽山", B22);
				map.put("南橫公路向陽登山口", B23);
				map.remove(location1);
				
				JSONObject jobj = new JSONObject();
				
				try {
					jobj.put("location", location);
					jobj.put("option", map);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
				out.println(jobj.toString());
			}
/*==================================南二線  end====================================*/

/*==================================南橫三山及關山線  start====================================*/		
			if("13".equals(option)) {
				Map<String,String> map = new LinkedHashMap<String,String>();
				
				map.put("南橫公路進涇橋登山口", C1);
				map.put("庫哈諾辛山屋", C2);
				map.put("庫哈諾辛山", C3);
				map.put("關山北峰", C4);
				map.put("關山", C5);
				map.put("南橫公路塔關山登山口", C6);
				map.put("塔關山", C7);
				map.remove(location1);
				
				JSONObject jobj = new JSONObject();
				
				try {
					jobj.put("location", location);
					jobj.put("option", map);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
				out.println(jobj.toString());
			}else if("14".equals(option)) {
				Map<String,String> map = new LinkedHashMap<String,String>();
				
				map.put("南橫公路進涇橋登山口", C1);
				map.put("庫哈諾辛山屋", C2);
				map.put("庫哈諾辛山", C3);
				map.put("關山北峰", C4);
				map.put("關山", C5);
				map.put("南橫公路塔關山登山口", C6);
				map.put("塔關山", C7);
				map.put("關山嶺山", C9);

				map.remove(location1);
				
				JSONObject jobj = new JSONObject();
				
				try {
					jobj.put("location", location);
					jobj.put("option", map);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
				out.println(jobj.toString());
			}else if("15".equals(option)) {
				Map<String,String> map = new LinkedHashMap<String,String>();
		
				map.put("南橫公路塔關山登山口", C6);
				map.put("塔關山", C7);
				map.put("關山嶺山", C9);
				map.put("埡口山莊", C8);
				map.put("大關山隧道東側登山口", C10);
				
				map.remove(location1);
				
				JSONObject jobj = new JSONObject();
				
				try {
					jobj.put("location", location);
					jobj.put("option", map);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			
				out.println(jobj.toString());
			}
/*==================================南橫三山及關山線  end====================================*/	
			
/*==================================新康斷線線  start====================================*/		
			if("16".equals(option)) {
				Map<String,String> map = new LinkedHashMap<String,String>();
				map.put("向陽登山口", D1);
				map.put("嘉明湖", D10);
				map.put("三叉山", D2);
				map.put("向陽山", D9);
				map.put("嘉明湖山屋",D3);
				map.put("連理山前（桃源）營地", D5);
				map.put("布新叉路口", D4);
				map.remove(location1);
				
				JSONObject jobj = new JSONObject();
				
				try {
					jobj.put("location", location);
					jobj.put("option", map);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
				out.println(jobj.toString());
			}else if("17".equals(option)) {
				Map<String,String> map = new LinkedHashMap<String,String>();
				map.put("嘉明湖", D10);
				map.put("布新叉路口", D4);
				map.put("連理山前（桃源）營地", D5);
				map.put("新仙山", D6);
				map.put("新仙山營地", D8);
				map.remove(location1);
				
				JSONObject jobj = new JSONObject();
				
				try {
					jobj.put("location", location);
					jobj.put("option", map);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
				out.println(jobj.toString());
			}else if("18".equals(option)) {
				Map<String,String> map = new LinkedHashMap<String,String>();
				map.put("三叉山", D2);
				map.put("布新叉路口", D4);
				map.put("連理山前（桃源）營地", D5);
				map.put("新仙山", D6);
				map.put("新康山", D7);
				map.put("新仙山營地", D8);
				
				map.remove(location1);
				
				JSONObject jobj = new JSONObject();
				
				try {
					jobj.put("location", location);
					jobj.put("option", map);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			
				out.println(jobj.toString());
			}
/*==================================新康斷線線  end====================================*/
			
/*==================================八通關  start====================================*/		
//			if("19".equals(option)) {
//				Map<String,String> map = new LinkedHashMap<String,String>();
//				
//				map.put("東埔登山入口", E1);
//				map.put("樂樂山屋", E2);
//				map.put("八通關", E3);
//				map.put("巴奈伊克", E4);
//				map.put("中央金礦山屋", E5);
//				map.put("白洋金礦山屋", E6);
//				map.put("秀姑坪", E7);
//				map.put("大水窟山", E8);
//				map.put("大水窟山屋", E9);
//				map.put("托馬斯駐在所", E10);
//				map.put("多美麗", E11);
//				map.remove(location1);
//				
//				JSONObject jobj = new JSONObject();
//				
//				try {
//					jobj.put("location", location);
//					jobj.put("option", map);
//				} catch (JSONException e) {
//					e.printStackTrace();
//				}
//				
//				out.println(jobj.toString());
//			}else if("20".equals(option)) {
//				Map<String,String> map = new LinkedHashMap<String,String>();
//				
//				map.put("中央金礦山屋", E5);
//				map.put("大水窟山", E8);
//				map.put("白洋金礦山屋", E6);
//				map.put("托馬斯駐在所", E10);
//				map.put("多美麗", E11);
//				map.put("抱崖山屋", E12);
//				map.put("瓦拉米山屋", E13);
//				map.put("黃麻", E14);
//				
//				map.remove(location1);
//				
//				JSONObject jobj = new JSONObject();
//				
//				try {
//					jobj.put("location", location);
//					jobj.put("option", map);
//				} catch (JSONException e) {
//					e.printStackTrace();
//				}
//				
//				out.println(jobj.toString());
//			}else if("21".equals(option)) {
//				Map<String,String> map = new LinkedHashMap<String,String>();
//				
//				map.put("托馬斯駐在所", E10);
//				map.put("多美麗", E11);
//				map.put("瓦拉米山屋", E13);
//				map.put("黃麻", E14);
//				map.put("佳心", E15);
//				map.put("山風登山口", E16);
//				
//				map.remove(location1);
//				
//				JSONObject jobj = new JSONObject();
//				
//				try {
//					jobj.put("location", location);
//					jobj.put("option", map);
//				} catch (JSONException e) {
//					e.printStackTrace();
//				}
//				
//				out.println(jobj.toString());
//	
//			}
/*==================================八通關  end====================================*/
		}
	}
	
}
