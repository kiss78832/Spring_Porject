package com.spring.itinerary.model;

import java.util.List;

public class ItineraryService {
	
	private Itinerary_interface dao;
	
	public ItineraryService() {
		dao = new ItineraryDAO();
	}
	
	public ItineraryVO addItinerary(String group_id, Integer total_day, String trip_break, String trip_schedule) {
		
		   ItineraryVO  itineraryVO = new  ItineraryVO();
		  
		   itineraryVO.setGroup_id(group_id);
		   itineraryVO.setTotal_day(total_day);
		   itineraryVO.setTrip_break(trip_break);
		   itineraryVO.setTrip_schedule(trip_schedule);
		   String str = dao.insert(itineraryVO);
		   itineraryVO.setItinerary_id(str);
		   return itineraryVO;
	}
	
	public ItineraryVO updateItinerary(String itinerary_id, String group_id,
		    Integer total_day) {
		
		ItineraryVO itineraryVO = new ItineraryVO();
		
		itineraryVO.setGroup_id(group_id);
		itineraryVO.setItinerary_id(itinerary_id);
		itineraryVO.setTotal_day(total_day);
		
		return itineraryVO;
	}
	
	public void deleteItinerary(String itinerary_id) {
		dao.delete(itinerary_id);
	}
	
	public ItineraryVO getOneItinerary(String itinerary_id) {
		return dao.findByPrimaryKey(itinerary_id);
	}
	
	public List<ItineraryVO> getAll(){
		return dao.getAll();
	}
	/*************************自訂方法*******************************/
	public ItineraryVO findByGroup(String group_id) {
		return dao.findByGroup(group_id);
	}
}
