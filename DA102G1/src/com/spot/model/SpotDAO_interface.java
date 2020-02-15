package com.spot.model;

import java.util.*;
import com.spot.model.SpotVO;

public interface SpotDAO_interface {
	public void insert(SpotVO spotVO);					//新增
    public void update(SpotVO spotVO);					//修改
    public void delete(String spot_id);					//刪除
    public SpotVO findByPrimaryKey(String spot_id);		//查詢
    public List<SpotVO> getAll();						 
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
    //  public List<EmpVO> getAll(Map<String, String[]> map); 
    
    /***************************自訂方法*******************************/
    public List<SpotVO>getspot(String main_road);
}
