package com.spring.info.model;

import java.util.*;

public interface InfoDAO_interface {
	public void insert(InfoVO infoVO);
	public void update(InfoVO infoVO);
	public void delete(String route_id);
	public InfoVO findByPrimaryKey(String route_num);
	public List<InfoVO> getAll();
//	public List<InfoVO> getAll(Map<String, String[]> map);

}
