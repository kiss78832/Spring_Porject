package com.viewpoint.model;

import java.util.List;



public interface Viewpoint_interface {
	public void insert(ViewpointVO viewpointVO);
	public void update(ViewpointVO viewpointVO);
	public void delete(String itinerary_id, String spot_id);
	public ViewpointVO findByPrimaryKey(String itinerary_id, String spot_id );
	public List<ViewpointVO> getAll();
//	public List<ViewpointVO> getAll(Map<String, String[]> map);
}
