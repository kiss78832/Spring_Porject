package com.news.model;

import java.util.List;
import java.util.Map;

import com.info.model.InfoVO;

public interface NewsDAO {
	public void insert(NewsVO newsVO);
	public void update(NewsVO newsVO);
	public void delete(String news_id);
	public NewsVO findByPrimaryKey(String news_id);
	public List<NewsVO> getAll();
	public List<NewsVO> getAll(Map<String, String[]> map);

}
