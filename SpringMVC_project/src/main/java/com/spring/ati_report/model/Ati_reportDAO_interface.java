package com.spring.ati_report.model;

import java.util.*;



public interface Ati_reportDAO_interface {

	 public void insert(Ati_reportVO Ati_reportVO);
     public void update(Ati_reportVO Ati_reportVO);
     public void delete(String article_id);
     public Ati_reportVO findByPrimaryKey(String article_id);
     public List<Ati_reportVO> getAll();

	
}
