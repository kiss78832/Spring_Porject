package com.spring.location.model;

import java.util.Base64;
import java.util.List;

public class LocationService {
	private LocationDAO_interface dao;

	public LocationService() {
		dao = new LocationDAO();
	}

	public LocationVO addLocation(String location_id, String location_name, Integer bedTotal_num, Integer bed_price,
			byte[] location_pic, Integer loc_type, Integer location_status) {

		LocationVO locationVO = new LocationVO();

		locationVO.setLocation_id(location_id);
		locationVO.setLocation_name(location_name);
		locationVO.setBedTotal_num(bedTotal_num);
		locationVO.setBed_price(bed_price);
		locationVO.setLocation_pic(location_pic);
		locationVO.setLoc_type(loc_type);
		locationVO.setLocation_status(location_status);
		dao.insert(locationVO);

		return locationVO;

	}

	public LocationVO updateLocation(String location_id, String location_name, Integer bedTotal_num, Integer bed_price,
			byte[] location_pic, Integer loc_type, Integer location_status) {
		
		LocationVO locationVO = new LocationVO();

		locationVO.setLocation_id(location_id);
		locationVO.setLocation_name(location_name);
		locationVO.setBedTotal_num(bedTotal_num);
		locationVO.setBed_price(bed_price);
		locationVO.setLocation_pic(location_pic);
		locationVO.setLoc_type(loc_type);
		locationVO.setLocation_status(location_status);
		dao.update(locationVO);

		return locationVO;
	}

	public LocationVO updateLocationNotPic(String location_id, String location_name, Integer bedTotal_num, Integer bed_price,
			 Integer loc_type, Integer location_status) {
		
		LocationVO locationVO = new LocationVO();

		locationVO.setLocation_id(location_id);
		locationVO.setLocation_name(location_name);
		locationVO.setBedTotal_num(bedTotal_num);
		locationVO.setBed_price(bed_price);
		locationVO.setLoc_type(loc_type);
		locationVO.setLocation_status(location_status);
		dao.updateNotPic(locationVO);

		return locationVO;
		
	}
	
	public List<LocationVO> getAll(){
		return dao.getAll();		
	}
	
	public LocationVO getOneLocation(String location_id) {
		return dao.findByPrimaryKey(location_id);
	}
	
	public void deleteLoc(String location_id) {
		dao.delete(location_id);
	}
	
	public String Base64Img(byte[] byteArray) {
		String encodeBase64 = Base64.getEncoder().encodeToString(byteArray);
			return encodeBase64;
	}
	
	public List<LocationVO> getLocsByLoc_type(Integer loc_type){
		return dao.getLocsByLoc_Type(loc_type);
	}
	
	public List<LocationVO> getLocsByLoc_status(Integer location_status){
		return dao.getLocsByLoc_Status(location_status);
	}
	
	/*2019-09-23新增SERVICE*/
	public LocationVO getLocationName(String location_name) {
		return dao.findLocationName(location_name);
	}
	
	public static void main(String[]args) {
		LocationService loc = new LocationService();
		List<LocationVO> list = loc.getLocsByLoc_type(0);
	System.out.println("= =:" + list.size());
	}
}
