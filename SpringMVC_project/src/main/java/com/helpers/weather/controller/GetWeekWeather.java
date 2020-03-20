package com.helpers.weather.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import redis.clients.jedis.Jedis;

public class GetWeekWeather {

	private static final String file = "WebContent/WEB-INF/weatherData/F-B0053-033.json";
	
	public static void main(String[] args) throws IOException, JSONException {
		FileReader weekWeather = new FileReader(file);
		BufferedReader br = new BufferedReader(weekWeather);
		

		StringBuilder sb = new StringBuilder();
		String str;
		while ((str = br.readLine()) != null) {
			sb.append(str);

		}
		
		br.close();
		
		
		Jedis jedis = new Jedis("localhost", 6379);
		jedis.auth("123456");
		jedis.select(1);
		
		List<String> locationCoiiection = new ArrayList<String>();
		
		locationCoiiection.add("玉山前峰");
		locationCoiiection.add("玉山西峰");
		locationCoiiection.add("玉山");
		locationCoiiection.add("玉山北峰");
		locationCoiiection.add("玉山東峰");
		locationCoiiection.add("玉山南峰");
		locationCoiiection.add("東小南山");
		locationCoiiection.add("南玉山");
		locationCoiiection.add("八通關山");
		locationCoiiection.add("秀姑巒山");
		locationCoiiection.add("大水窟山");
		locationCoiiection.add("南大水窟山");
		locationCoiiection.add("三叉山");
		locationCoiiection.add("向陽山");
		locationCoiiection.add("達芬尖山");
		locationCoiiection.add("塔芬山");
		locationCoiiection.add("轆轆山");
		locationCoiiection.add("雲峰");
		locationCoiiection.add("南雙頭山");
		locationCoiiection.add("駒盆山");
		locationCoiiection.add("馬博拉斯山");
		locationCoiiection.add("馬利加南山");
		locationCoiiection.add("馬西山");
		locationCoiiection.add("喀西帕南山");
		locationCoiiection.add("庫哈諾辛山");
		locationCoiiection.add("關山");
		locationCoiiection.add("塔關山");
		locationCoiiection.add("關山嶺山");
		locationCoiiection.add("新康山");
		locationCoiiection.add("鹿山");
		locationCoiiection.add("東郡大山");
		locationCoiiection.add("郡大山");
		locationCoiiection.add("霞山  ");
		

		JSONObject jobj = new JSONObject(sb.toString());
		JSONObject jobj2 = jobj.getJSONObject("cwbopendata");
		JSONObject jobj3 = jobj2.getJSONObject("dataset");
		JSONObject jobj4 = jobj3.getJSONObject("locations");
		JSONArray jArray = jobj4.getJSONArray("location");
	
		int count = 0;
		for(int k =0;k<jArray.length();k++) {//滾地點
			//待加入locationName判斷式
			JSONObject data = jArray.getJSONObject(k);
			String mountain = data.getString("locationName");
			
			/*過濾出需求地點*/
			if(locationCoiiection.contains(mountain)) {
				
				String lat = data.getString("lat");
				String lon = data.getString("lon");
				
				System.err.println(count++ +"."+mountain);		
				System.out.println("緯度 = "+lat);
				System.out.println("經度 = "+lon);
				
				
				/*存Redis*/
				jedis.hset("M"+count+":latlon", "lat", lat);
				jedis.hset("M"+count+":latlon", "lon", lon);
				
				System.out.println("============="+jedis.hget("中央尖山:latlon", "lat"));
				
				
				JSONArray jArray2 = data.getJSONArray("weatherElement");
				
				//加for迴圈//滾天氣元素
				for(int i =0;i< jArray2.length();i++) {
					JSONObject WE1 = jArray2.getJSONObject(i);
					String weatherElement = WE1.getString("description");
					String elementName = WE1.getString("elementName");
					
					
					if(elementName.equals("T")) {//T == 平均溫度
						
						System.err.println("天氣元素 = "+weatherElement);
						
						JSONArray jArray3 = WE1.getJSONArray("time");
						
						//加for迴圈//滾日夜時間
						for(int j =0;j< jArray3.length();j++) {
							JSONObject WE2 = jArray3.getJSONObject(j);
							String startTime = WE2.getString("startTime");
							String endTime = WE2.getString("endTime");
							String ST = (String) startTime.subSequence(8, 10);
							String ET = (String)endTime.subSequence(8, 10);
							
							String MST = (String) startTime.subSequence(5, 7);
							String MET = (String)endTime.subSequence(5, 7);
							
							if(ST.equals(ET)) {//存白天
								System.out.println(MET+ET+"D");
								String temp = WE2.getJSONObject("elementValue").getString("value");
								jedis.hset("M"+count+":T", MET+ET+"D", temp);
								
							}else {//存晚上
								System.out.println(MST+ST+"N");
								String temp = WE2.getJSONObject("elementValue").getString("value");
								jedis.hset("M"+count+":T", MST+ST+"N", temp);
							}
			
							
							
							
							System.out.println("溫度 = "+WE2.getJSONObject("elementValue").getString("value"));
						}
						
					}//平均溫度if結束
					
					if(elementName.equals("PoP12h")) {//PoP12h = 12小時降雨機率
						
						System.err.println("天氣元素 = "+weatherElement);
						
						JSONArray jArray3 = WE1.getJSONArray("time");
						
						//加for迴圈//滾日夜時間
						for(int j =0;j< jArray3.length();j++) {
							JSONObject WE2 = jArray3.getJSONObject(j);
							
							
							String startTime = WE2.getString("startTime");
							String endTime = WE2.getString("endTime");
							String ST = (String) startTime.subSequence(8, 10);
							String ET = (String)endTime.subSequence(8, 10);
							
							String MST = (String) startTime.subSequence(5, 7);
							String MET = (String)endTime.subSequence(5, 7);
							
							if(ST.equals(ET)) {
								System.out.println(MET+ET+"D");
								
								if(WE2.getJSONObject("elementValue").get("value") instanceof String) {
									String rain =WE2.getJSONObject("elementValue").getString("value");
									jedis.hset("M"+count+":PoP12h", MST+ST+"D", rain);
									
								}else {
									
									jedis.hset("M"+count+":PoP12h", MST+ST+"D", "No Data");
								}

								
								
							}else {
								System.out.println(MST+ST+"N");
								
								if(WE2.getJSONObject("elementValue").get("value") instanceof String) {
									String rain =WE2.getJSONObject("elementValue").getString("value");
									jedis.hset("M"+count+":PoP12h", MST+ST+"N", rain);
									
								}else {
									jedis.hset("M"+count+":PoP12h", MST+ST+"N", "No Data");
								}
								
							}
			
//							System.out.println("開始時間 = "+WE2.getString("startTime"));
//							System.out.println("結束時間 = "+WE2.getString("endTime"));
							
							if(WE2.getJSONObject("elementValue").get("value") instanceof String) {
								System.out.println("降雨機率 = "+WE2.getJSONObject("elementValue").getString("value"));
							}else {
								System.out.println("降雨機率 = "+"無");
							}
							
							
						}
						
					}//12小時降雨機率if結束
					
					
					if(elementName.equals("Wx")) {//Wx = 天氣現象
						
						System.err.println("天氣元素 = "+weatherElement);
						
						JSONArray jArray3 = WE1.getJSONArray("time");
						
						//加for迴圈//滾日夜時間
						for(int j =0;j< jArray3.length();j++) {
							JSONObject WE2 = jArray3.getJSONObject(j);
							
							
							String startTime = WE2.getString("startTime");
							String endTime = WE2.getString("endTime");
							String ST = (String) startTime.subSequence(8, 10);
							String ET = (String)endTime.subSequence(8, 10);
							
							String MST = (String) startTime.subSequence(5, 7);
							String MET = (String)endTime.subSequence(5, 7);
							
							if(ST.equals(ET)) {
								System.out.println(MET+ET+"D");
								
								if(WE2.getJSONArray("elementValue").getJSONObject(0).get("value") instanceof String) {
									String WS = (String)WE2.getJSONArray("elementValue").getJSONObject(0).get("value");
									jedis.hset("M"+count+":Wx", MST+ST+"D", WS);
								}else {
									jedis.hset("M"+count+":Wx", MST+ST+"D", "No Data");
								}
				
								
							}else {
								System.out.println(MST+ST+"N");
								
								if(WE2.getJSONArray("elementValue").getJSONObject(0).get("value") instanceof String) {
									String WS = (String)WE2.getJSONArray("elementValue").getJSONObject(0).get("value");
									jedis.hset("M"+count+":Wx", MST+ST+"N", WS);
								}else {
									jedis.hset("M"+count+":Wx", MST+ST+"N", "No Data");
								}
								
							}
							
							
							
							
//							System.out.println("開始時間 = "+WE2.getString("startTime"));
//							System.out.println("結束時間 = "+WE2.getString("endTime"));
							
							
							if(WE2.getJSONArray("elementValue").getJSONObject(0).get("value") instanceof String) {
								System.out.println("天氣現象 = "+WE2.getJSONArray("elementValue").getJSONObject(0).get("value"));
							}else {
								System.out.println("天氣現象 = "+"無");
							}
							
							
						}
						
					}//天氣現象if結束
					
					
					
					if(elementName.equals("WeatherDescription")) {//WeatherDescription = 天氣預報綜合描述
						
						System.err.println("天氣元素 = "+weatherElement);
						
						JSONArray jArray3 = WE1.getJSONArray("time");
						
						//加for迴圈//滾日夜時間
						for(int j =0;j< jArray3.length();j++) {
							JSONObject WE2 = jArray3.getJSONObject(j);
							
							String startTime = WE2.getString("startTime");
							String endTime = WE2.getString("endTime");
							String ST = (String) startTime.subSequence(8, 10);
							String ET = (String)endTime.subSequence(8, 10);
							
							String MST = (String) startTime.subSequence(5, 7);
							String MET = (String)endTime.subSequence(5, 7);
							
							if(ST.equals(ET)) {
								System.out.println(MET+ET+"D");
								
								if(WE2.getJSONObject("elementValue").get("value") instanceof String) {
									String WeatherDescription = WE2.getJSONObject("elementValue").getString("value");
									jedis.hset("M"+count+":WeatherDescription", MST+ST+"D", WeatherDescription);
								}else {
									jedis.hset("M"+count+":WeatherDescription", MST+ST+"D", "No Data");
								}
								
								
							}else {
								System.out.println(MST+ST+"N");
								
								if(WE2.getJSONObject("elementValue").get("value") instanceof String) {
									String WeatherDescription = WE2.getJSONObject("elementValue").getString("value");
									jedis.hset("M"+count+":WeatherDescription", MST+ST+"N", WeatherDescription);
								}else {
									jedis.hset("M"+count+":WeatherDescription", MST+ST+"N", "No Data");
								}
							}
							
							
//							System.out.println("開始時間 = "+WE2.getString("startTime"));
//							System.out.println("結束時間 = "+WE2.getString("endTime"));
							
							if(WE2.getJSONObject("elementValue").get("value") instanceof String) {
								System.out.println("天氣預報綜合描述 = "+WE2.getJSONObject("elementValue").getString("value"));
							}else {
								System.out.println("天氣預報綜合描述 = "+"無");
							}
							
							
						}
						
					}//天氣預報綜合描述if結束
					

				}//天氣元素for結束
				
			}

		}//全部結束		
			
		jedis.close();
	
	}

}
