package com.spring.dailyBed.model;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.spring.location.model.LocationService;
import com.spring.location.model.LocationVO;

public class DailyBedService {

	private DailyBedDAO_interface dao;

	public DailyBedService() {
		dao = new DailyBedDAO();
	}

	public DailyBedVO addDailyBed(String location_id, Integer remaining_total, Integer provided_total,
			Date dailyBed_date) {

		DailyBedVO dailyBedVO = new DailyBedVO();

		dailyBedVO.setLocation_id(location_id);
		dailyBedVO.setRemaining_total(remaining_total);
		dailyBedVO.setProvided_total(provided_total);
		dailyBedVO.setDailyBed_date(dailyBed_date);

		dao.insert(dailyBedVO);

		return dailyBedVO;

	}

	public DailyBedVO updateDailyBed(String dailyBed_id, Integer reservedNumber) {

		DailyBedVO dailyBedVO = dao.findByPrimaryKey(dailyBed_id);

		String location_id = dailyBedVO.getLocation_id();
		Date dailyBed_date = dailyBedVO.getDailyBed_date();
		Integer provided_total = dailyBedVO.getProvided_total();
		Integer remaining_total = dailyBedVO.getRemaining_total();
		remaining_total -= reservedNumber;
		provided_total += reservedNumber;

		dailyBedVO.setDailyBed_id(dailyBed_id);
		dailyBedVO.setLocation_id(location_id);
		dailyBedVO.setRemaining_total(remaining_total);
		dailyBedVO.setProvided_total(provided_total);
		dailyBedVO.setDailyBed_date(dailyBed_date);

		dao.update(dailyBedVO);

		return dailyBedVO;

	}
	
	//cancel need to addback remaining number and minus provided_total
	public DailyBedVO cancelDailyBed(String dailyBed_id, Integer reservedNumber) {

		DailyBedVO dailyBedVO = dao.findByPrimaryKey(dailyBed_id);

		String location_id = dailyBedVO.getLocation_id();
		Date dailyBed_date = dailyBedVO.getDailyBed_date();
		Integer provided_total = dailyBedVO.getProvided_total();
		Integer remaining_total = dailyBedVO.getRemaining_total();
		remaining_total += reservedNumber;
		provided_total -= reservedNumber;

		dailyBedVO.setDailyBed_id(dailyBed_id);
		dailyBedVO.setLocation_id(location_id);
		dailyBedVO.setRemaining_total(remaining_total);
		dailyBedVO.setProvided_total(provided_total);
		dailyBedVO.setDailyBed_date(dailyBed_date);

		dao.update(dailyBedVO);

		return dailyBedVO;
	}
	
	 public List<String> getAllDifferentLoc(){
		 return dao.getAllDifferentLoc();
	 }

	public List<DailyBedVO> getAll() {
		return dao.getAll();
	}

	public DailyBedVO getOneDailyBed(String dailyBed_id) {
		return dao.findByPrimaryKey(dailyBed_id);
	}

	public List<DailyBedVO> getDailyBedsByLoc_id(String location_id) {
		return dao.getAllByLoc_id(location_id);
	}

	public List<DailyBedVO> getDailyBedsByDailyBed_date(Date dailyBed_date) {
		return dao.getAllByDailyBed_date(dailyBed_date);
	}

	public List<DailyBedVO> getAllByLoc_idAndDate(String location_id, int year, int month) {
		return dao.getAllByLoc_idAndDate(location_id, year, month);
	}
	
	//filtered remaining_total  
	public List<DailyBedVO> getAllByDate(int year, int month, int date) {
		List<DailyBedVO> list = dao.getAllByDate(year, month, date);
		System.out.println(list.size());

		List<DailyBedVO> temp = new LinkedList<DailyBedVO>();

		LocationService locSvc = new LocationService();
		for (DailyBedVO dailyBedVO : list) {
			if (locSvc.getOneLocation(dailyBedVO.getLocation_id()).getLoc_type() == 0) {
				if (dailyBedVO.getRemaining_total() == 0) {
					temp.add(dailyBedVO);
				}
			}
			if (locSvc.getOneLocation(dailyBedVO.getLocation_id()).getLoc_type() == 1) {
				if (dailyBedVO.getRemaining_total() / 4 == 0 || dailyBedVO.getRemaining_total() < 4) {
					temp.add(dailyBedVO);
				}
			}

		}

		for (DailyBedVO dailyBedVO : temp) {
			list.remove(dailyBedVO);
		}

		System.out.println(list.size());

		return list;
	}


	public List<String> getLocNameByDate(int year, int month, int date) {
		List<DailyBedVO> list = dao.getAllByDate(year, month, date);

		List<DailyBedVO> temp = new LinkedList<DailyBedVO>();

		LocationService locSvc = new LocationService();
		for (DailyBedVO dailyBedVO : list) {
			if (locSvc.getOneLocation(dailyBedVO.getLocation_id()).getLoc_type() == 0) {
				if (dailyBedVO.getRemaining_total() == 0) {
					temp.add(dailyBedVO);
				}
			}
			if (locSvc.getOneLocation(dailyBedVO.getLocation_id()).getLoc_type() == 1) {
				if (dailyBedVO.getRemaining_total() / 4 == 0 || dailyBedVO.getRemaining_total() < 4) {
					temp.add(dailyBedVO);
				}
			}
		}

		for (DailyBedVO dailyBedVO : temp) {
			list.remove(dailyBedVO);
		}

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

		List<String> locNameList = new LinkedList<String>();
		for (DailyBedVO dailyBedVO : list) {
			LocationVO locationVO = locSvc.getOneLocation(dailyBedVO.getLocation_id());
			String loc_name = locationVO.getLocation_name();
			locNameList.add(loc_name);
		}

		String jsonStr1 = gson.toJson(locNameList);
		System.out.println(jsonStr1);

		System.out.println("locNameList.size():" + locNameList.size());

		return locNameList;
	}

	public DailyBedVO getLocVOByDate(int year, int month, int date, String location_id) {
		return dao.getLocVOByDate(year, month, date, location_id);

	}

	public DailyBedVO getDailyBedVOByFullDate(String location_id, Date date) {
		return dao.getDailyBedVOByFullDate(location_id, date);
	}
	
	public List<LocationVO>getLocListStillCanProvide(Date date){

		List<LocationVO> locList = new LinkedList<LocationVO>();
		
		DailyBedService dailyBedSvc = new DailyBedService();
		LocationService locSvc = new LocationService();
		
		List<DailyBedVO> dailyBedVOlist = dailyBedSvc
				.getDailyBedsByDailyBed_date(date);
		
		
		for (DailyBedVO dailyBedVO : dailyBedVOlist) {

			String location_id = dailyBedVO.getLocation_id();
			System.out.println(location_id);
			LocationVO locVO = locSvc.getOneLocation(location_id);
			Integer loc_type = locVO.getLoc_type();
			System.out.println(loc_type);
			if (loc_type == 0) {
				if (dailyBedVO.getRemaining_total() > 0) {
					locList.add(locVO);
				}
			}
			if (loc_type == 1) {
				System.out.println(dailyBedVO.getRemaining_total());
				if (dailyBedVO.getRemaining_total() / 4 != 0 && dailyBedVO.getRemaining_total() > 4) {
					locList.add(locVO);
				}
			}

		}
		
		return locList;
		
		
	}

}
