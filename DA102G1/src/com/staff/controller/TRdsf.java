package com.staff.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TRdsf {

	public static void main(String[] args) throws JSONException {
			
		String AA = "0970516983";
		String Reg4 = "^09[0-9]{8}$";
		
		if(AA.matches(Reg4)) {
			System.out.println("正確");
		}else {
			System.out.println("錯誤");
		}
		
		
//		ArrayList list = new ArrayList();
//		
//		Map ls = new LinkedHashMap();
//		
//		ls.put("圓峰山屋", "圓峰山屋1");
//		ls.put("排雲山莊", "排雲山莊1");
//		
////		ls.remove("圓峰山屋");
//		
//		System.out.println(ls.toString());
//		
//		JSONArray array = new JSONArray();
//		
//		JSONObject jobj = new JSONObject();
//		array.put(ls);
//		
//		jobj.put("result", ls);
//		
//		System.out.println(array.toString());
//		
//		System.out.println(jobj);
		
		List<String> list = new LinkedList();
		
		list.add("11111");
		list.add("22222");
		list.add("33333");
		
		list.size();
		System.out.println(list.get(list.size()-1));
		list.remove(list.size()-1);
		System.out.println(list.get(list.size()-1));
	}

}
