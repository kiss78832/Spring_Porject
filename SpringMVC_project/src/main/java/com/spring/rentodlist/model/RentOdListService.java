package com.spring.rentodlist.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class RentOdListService {

	private RentOdListDAO_interface dao;

	public RentOdListService() {
		dao = new RentOdListDAO();
	}

	public RentOdListVO addRentOdList(String member_id, String rent_payment, java.sql.Date rsved_rent_date, java.sql.Date real_rent_date
		, java.sql.Date ex_return_date, java.sql.Date real_return_date, Integer od_status, Integer od_total_price, String rent_name 
		, String rent_phone) {

		RentOdListVO rentodlistVO = new RentOdListVO();

		rentodlistVO.setMember_id(member_id);
		rentodlistVO.setRent_payment(rent_payment);
		rentodlistVO.setRsved_rent_date(rsved_rent_date);
		rentodlistVO.setReal_rent_date(real_rent_date);
		rentodlistVO.setEx_return_date(ex_return_date);
		rentodlistVO.setReal_return_date(real_return_date);
		rentodlistVO.setOd_status(od_status);
		rentodlistVO.setOd_total_price(od_total_price);
		rentodlistVO.setRent_name(rent_name);
		rentodlistVO.setRent_phone(rent_phone);
		dao.insert(rentodlistVO);

		return rentodlistVO;
	}

	public RentOdListVO updateRentOdList(String rent_odnum, String member_id, String rent_payment, java.sql.Date rsved_rent_date, java.sql.Date real_rent_date
			, java.sql.Date ex_return_date, java.sql.Date real_return_date, Integer od_status, Integer od_total_price, String rent_name 
			, String rent_phone) {

		RentOdListVO rentodlistVO = new RentOdListVO();
		
		rentodlistVO.setRent_odnum(rent_odnum);
		rentodlistVO.setMember_id(member_id);
		rentodlistVO.setRent_payment(rent_payment);
		rentodlistVO.setRsved_rent_date(rsved_rent_date);
		rentodlistVO.setReal_rent_date(real_rent_date);
		rentodlistVO.setEx_return_date(ex_return_date);
		rentodlistVO.setReal_return_date(real_return_date);
		rentodlistVO.setOd_status(od_status);
		rentodlistVO.setOd_total_price(od_total_price);
		rentodlistVO.setRent_name(rent_name);
		rentodlistVO.setRent_phone(rent_phone);
		dao.update(rentodlistVO);

		return rentodlistVO;
	}

	public void deleteRentOdList(String rent_odnum) {
		dao.delete(rent_odnum);
	}

	public RentOdListVO getOneRentOdList(String rent_odnum) {
		return dao.findByPrimaryKey(rent_odnum);
	}

	public List<RentOdListVO> getAll() {
		return dao.getAll();
	}
	
	public List<RentOdListVO> getAllMemRentOdList(String member_id) {
		return dao.findByMemOrder(member_id);
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

	
	   public static void main(String[] args) {
		   RentOdListService test = new RentOdListService();
		   List<String> abc = test.getDays("2018-06-28", "2019-07-3");
		   for(String item:abc){
		        System.out.println(item);
		    }
//	        System.out.println(getDays("2018-06-28", "2019-07-3"));
	    }
	
}
