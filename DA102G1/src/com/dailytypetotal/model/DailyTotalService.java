package com.dailytypetotal.model;


import java.util.List;

import com.rentodlist.model.RentOdListService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DailyTotalService {

	private DailyTotalDAO_interface dao;

	public DailyTotalService() {
		dao = new DailyTotalDAO();
	}

	public DailyTotalVO addDailyTotal(String type_eq_num, java.sql.Date eq_date, Integer daily_eq_qty, Integer start_qty) {

		DailyTotalVO dailytotalVO = new DailyTotalVO();

		dailytotalVO.setType_eq_num(type_eq_num);
		dailytotalVO.setEq_date(eq_date);
		dailytotalVO.setDaily_eq_qty(daily_eq_qty);
		dailytotalVO.setStart_qty(start_qty);
		dao.insert(dailytotalVO);

		return dailytotalVO;
	}

	public DailyTotalVO updateDailyTotal(String dailyeq_num, String type_eq_num, java.sql.Date eq_date, Integer daily_eq_qty, Integer start_qty) {

		DailyTotalVO dailytotalVO = new DailyTotalVO();

		dailytotalVO.setDailyeq_num(dailyeq_num);
		dailytotalVO.setType_eq_num(type_eq_num);
		dailytotalVO.setEq_date(eq_date);
		dailytotalVO.setDaily_eq_qty(daily_eq_qty);
		dailytotalVO.setStart_qty(start_qty);
		dao.update(dailytotalVO);

		return dailytotalVO;
	}

	public void deleteDailyTotal(String dailyeq_num) {
		dao.delete(dailyeq_num);
	}

	public DailyTotalVO getOneDailyTotal(String dailyeq_num) {
		return dao.findByPrimaryKey(dailyeq_num);
	}

	public List<DailyTotalVO> getAll() {
		return dao.getAll();
	}
	
	public List<DailyTotalVO> getOneEqDate(String type_eq_num){
		return dao.getOneEqDate(type_eq_num);
	}
	
	public List<DailyTotalVO> getsqlDate(String type_eq_num,java.sql.Date startdate,java.sql.Date enddate){
		return dao.getDate(type_eq_num, startdate, enddate);
	}
	
	public List<DailyTotalVO>getBigDate(String type_eq_num ,java.sql.Date ex_return_date){
		return dao.getMoreDate(type_eq_num,ex_return_date);
	}
	
	
	  public static List<String> getDays(String startTime, String endTime) {

	        // 返回的日期集合
	        List<String> days = new ArrayList<String>();

	        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        try {
	            Date start = dateFormat.parse(startTime);
	            Date end = dateFormat.parse(endTime);

	            Calendar tempStart = Calendar.getInstance();
	            tempStart.setTime(start);

	            Calendar tempEnd = Calendar.getInstance();
	            tempEnd.setTime(end);
	            tempEnd.add(Calendar.DATE, +1);// 日期加1(包含结束)
	            while (tempStart.before(tempEnd)) {
	                days.add(dateFormat.format(tempStart.getTime()));
	                tempStart.add(Calendar.DAY_OF_YEAR, 1);
	            }

	        } catch (ParseException e) {
	            e.printStackTrace();
	        }

	        return days;
	    }
	  
	  public String getDateTime(){
		  SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
		  Date date = new Date();
		  String strDate = sdFormat.format(date);
		  System.out.println(strDate);
		  return strDate;
		  }

	
//	   public static void main(String[] args) {
//		   RentOdListService test = new RentOdListService();
//		   List<String> abc = test.getDays("2018-06-28", "2019-07-3");
//		   for(String item:abc){
//		        System.out.println(item);
//		    }
////	        System.out.println(getDays("2018-06-28", "2019-07-3"));
//	    }
}
