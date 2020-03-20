package com.spring.equipment.model;

import java.io.IOException;
import java.util.*;

public interface EquipmentDAO_interface {
	public void insert(EquipmentVO equVO);

	public void update(EquipmentVO equVO);

	public void delete(String eq_num);

	public EquipmentVO findByPrimaryKey(String eq_num);
	
	public EquipmentVO findByTypeEqNum(String type_eq_num);
	
	public List<EquipmentVO> findByType(String eq_type);
	
	public List<EquipmentVO> findBySize(String eq_name);
	
	public EquipmentVO findByNameAndSize(String eq_name ,String eq_size);

	public List<EquipmentVO> getAll();
	
	public List<EquipmentVO> findByAllTypeEqNum(String type_eq_num);
	
	
	
//          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//          public List<EquipmentVO> getAll(Map<String, String[]> map); 
}