package com.viewpoint.model;

import java.util.List;

public class ViewpointService {
	
	private Viewpoint_interface dao;
	
	public ViewpointService() {
		dao = new ViewpointDAO();
	}
	
	public ViewpointVO addViewpoint(String itinerary_id, String spot_id
			,Integer day) {
		
		ViewpointVO viewpointVO = new ViewpointVO();
		
		viewpointVO.setItinerary_id(itinerary_id);
		viewpointVO.setSpot_id(spot_id);
		viewpointVO.setDay(day);
		dao.insert(viewpointVO);
		return viewpointVO;
	}
	
	public ViewpointVO updateViewpoint(String itinerary_id, String spot_id
			,Integer day) {
		ViewpointVO viewpointVO = new ViewpointVO();
		
		viewpointVO.setItinerary_id(itinerary_id);
		viewpointVO.setSpot_id(spot_id);
		viewpointVO.setDay(day);
		return viewpointVO;
	}
	
	public void deleteViewpoint(String itinerary_id, String spot_id ) {
		dao.delete(itinerary_id, spot_id);
	}
	
	public ViewpointVO getOneViewpoint(String itinerary_id, String spot_id) {
		return dao.findByPrimaryKey(itinerary_id, spot_id);
	}
	
	public List<ViewpointVO> getAll(){
		return dao.getAll();
	}
}
