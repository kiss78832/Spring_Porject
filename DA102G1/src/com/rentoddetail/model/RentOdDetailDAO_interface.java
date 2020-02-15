package com.rentoddetail.model;

import java.sql.Connection;
import java.util.*;

import com.rentoddetail.model.RentOdDetailVO;
import com.rentodlist.model.RentOdListVO;

public interface RentOdDetailDAO_interface {
	public void insert(RentOdDetailVO rentoddetailVO);

	public void update(RentOdDetailVO rentoddetailVO);

	public void delete(String rent_odnum, String eq_num);

	public RentOdDetailVO findByPrimaryKey(String rent_odnum, String eq_num);

	public List<RentOdDetailVO> getAll();
//          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//          public List<EquipmentVO> getAll(Map<String, String[]> map); 
	
	public List<RentOdDetailVO> findByDetail(String rent_odnum);
	
	public void insert2 (RentOdDetailVO rentoddetailVO, java.sql.Connection con);
	
	public void update2 (RentOdDetailVO rentoddetailVO , Connection con);
}
