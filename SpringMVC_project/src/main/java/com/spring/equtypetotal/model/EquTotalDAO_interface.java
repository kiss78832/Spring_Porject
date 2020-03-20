package com.spring.equtypetotal.model;

import java.util.List;
import java.util.Set;

import com.spring.equipment.model.EquipmentVO;

public interface EquTotalDAO_interface {
	public void insert(EquTotalVO equtotalVO);

	public void update(EquTotalVO equtotalVO);

	public void delete(String type_eq_num);

	public EquTotalVO findByPrimaryKey(String type_eq_num);

//
	public List<EquTotalVO> getAll();
//          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//          public List<EquipmentVO> getAll(Map<String, String[]> map);
	
	 public Set<EquipmentVO> getEqusByTypeTotal(String type_eq_num);
}
