package com.dailytypetotal.model;


import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import com.rentoddetail.model.RentOdDetailVO;

public interface DailyTotalDAO_interface {
	public void insert(DailyTotalVO dailytotalVO);

	public void update(DailyTotalVO dailytotalVO);

	public void delete(String dailyeq_num);

	public DailyTotalVO findByPrimaryKey(String dailyeq_num);

	public List<DailyTotalVO> getOneEqDate(String type_eq_num);
	
	public List<DailyTotalVO> getAll();
//          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//          public List<EquipmentVO> getAll(Map<String, String[]> map); 

	public List<DailyTotalVO> getDate(String type_eq_num,Date startdate,Date enddate);
	
	public void insert2 (DailyTotalVO dailytotalVO, java.sql.Connection con);
	
	public void update2 (DailyTotalVO dailytotalVO , Connection con) ;
	
	public List<DailyTotalVO> getMoreDate(String type_eq_num,Date ex_return_date);
}
