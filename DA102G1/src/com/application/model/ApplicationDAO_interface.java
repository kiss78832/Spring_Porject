package com.application.model;

import java.util.*;



public interface ApplicationDAO_interface {

	public void insert(ApplicationVO appVO);
    public void update(ApplicationVO appVO);
    public void delete(String app_num);
    public ApplicationVO findByPrimaryKey(String app_num);
    public List<ApplicationVO> getAll();
    
/*-------------------------------------自訂方法--------------------------------------------*/
    public void update_One(ApplicationVO appVO);
    public List<ApplicationVO> getApp_One(String member_id);
    public List<ApplicationVO> getStatus_List(Integer app_status);
	
}
