package com.spring.spot.model;

import java.util.List;

public class SpotService {

	public SpotDAO_interface dao;

	public SpotService() {
		dao = new SpotJDBCDAO();
	}

	public SpotVO addSpot(String spot_id, String spot_name, String spot_intro, byte[] spot_pic, Double spot_lng,
			Double spot_lat, Integer spot_point, Integer spot_cabinpoint, Integer spot_status, Integer auth_spot,
			String main_road) {

		SpotVO spotVO = new SpotVO();

		spotVO.setSpot_id(spot_id);
		spotVO.setSpot_name(spot_name);
		spotVO.setSpot_intro(spot_intro); // CLOB
		spotVO.setSpot_pic(spot_pic); // BLOB
		spotVO.setSpot_lng(spot_lng);
		spotVO.setSpot_lat(spot_lat);
		spotVO.setSpot_point(spot_point);
		spotVO.setSpot_cabinpoint(spot_cabinpoint);
		spotVO.setSpot_status(spot_status);
		spotVO.setAuth_spot(auth_spot);
		spotVO.setMain_road(main_road);
		dao.insert(spotVO);

		return spotVO;
	}

	public SpotVO updateSpot(String spot_id, String spot_name, String spot_intro, byte[] spot_pic, Double spot_lng,
			Double spot_lat, Integer spot_point, Integer spot_cabinpoint, Integer spot_status, Integer auth_spot,
			String main_road) {

		SpotVO spotVO = new SpotVO();

		spotVO.setSpot_id(spot_id);
		spotVO.setSpot_name(spot_name);
		spotVO.setSpot_intro(spot_intro); // CLOB
		spotVO.setSpot_pic(spot_pic); // BLOB
		spotVO.setSpot_lng(spot_lng);
		spotVO.setSpot_lat(spot_lat);
		spotVO.setSpot_point(spot_point);
		spotVO.setSpot_cabinpoint(spot_cabinpoint);
		spotVO.setSpot_status(spot_status);
		spotVO.setAuth_spot(auth_spot);
		spotVO.setMain_road(main_road);
		dao.update(spotVO);

		return spotVO;
	}

	public void deleteSpot(String spot_id) {
		dao.delete(spot_id);
	}

	public SpotVO getOneSpot(String spot_id) {
		return dao.findByPrimaryKey(spot_id);
	}

	public List<SpotVO> getAll() {
		return dao.getAll();
	}
	/***************自訂方法(找出特定景點)******************/
	public List<SpotVO> getspot(String main_road) {
		return dao.getspot(main_road);
	}
}
