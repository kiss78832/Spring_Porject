package com.itinerary.model;
import java.util.*;
import com.group.model.*;

public interface Itinerary_interface {
	public String insert(ItineraryVO itineraryVO);
	public void update(ItineraryVO itineraryVO);
	public void delete(String itinerary_id);
	public ItineraryVO findByPrimaryKey(String itinerary_id);
	public List<ItineraryVO> getAll();
//	public List<ItineraryVO> getAll(Map<String, String[]> map);
/*************************自定方法************************************/
	public ItineraryVO findByGroup(String group_id);
	
}
