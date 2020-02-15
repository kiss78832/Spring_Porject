
package com.rentodlist.model;

import java.util.*;

import com.rentodlist.model.RentOdListVO;
import com.dailytypetotal.model.DailyTotalVO;
import com.rentoddetail.model.RentOdDetailVO;

public interface RentOdListDAO_interface {
	public void insert(RentOdListVO rentodlistVO);

	public void update(RentOdListVO rentodlistVO);

	public void delete(String rent_odnum);

	public RentOdListVO findByPrimaryKey(String rent_odnum);
	
	public List<RentOdListVO> findByMemOrder(String member_id);

	public List<RentOdListVO> getAll();
//          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//          public List<EquipmentVO> getAll(Map<String, String[]> map); 
	

	public void insertWithOrd(RentOdListVO rentodlistVO, List<RentOdDetailVO> list);
	
	public String insertWithOrdAndDaily(RentOdListVO rentodlistVO, List<RentOdDetailVO> list ,List<DailyTotalVO> list1);
	
	public void updateWithOrdAndDaily(RentOdListVO rentodlistVO, List<RentOdDetailVO> list ,List<DailyTotalVO> list1);
}
