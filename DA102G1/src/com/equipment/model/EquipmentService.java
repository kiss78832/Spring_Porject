package com.equipment.model;

import java.util.ArrayList;
import java.util.List;

public class EquipmentService {

	private EquipmentDAO_interface dao;

	public EquipmentService() {
		dao = new EquipmentDAO();
	}

	public EquipmentVO addEquipment(String eq_name, String eq_type, String eq_size, String eq_brand, Integer eq_price, Integer eq_status
			, byte[] eq_pic, String eq_detail, String type_eq_num) {

		EquipmentVO equVO = new EquipmentVO();

		equVO.setEq_name(eq_name);
		equVO.setEq_type(eq_type);
		equVO.setEq_size(eq_size);
		equVO.setEq_brand(eq_brand);
		equVO.setEq_price(eq_price);
		equVO.setEq_status(eq_status);
		equVO.setEq_pic(eq_pic);
		equVO.setEq_detail(eq_detail);
		equVO.setType_eq_num(type_eq_num);
		dao.insert(equVO);

		return equVO;
	}

	public EquipmentVO updateEquipment(String eq_num, String eq_name, String eq_type, String eq_size, String eq_brand, Integer eq_price, Integer eq_status
			, byte[] eq_pic, String eq_detail, String type_eq_num) {

		EquipmentVO equVO = new EquipmentVO();

		equVO.setEq_num(eq_num);
		equVO.setEq_name(eq_name);
		equVO.setEq_type(eq_type);
		equVO.setEq_size(eq_size);
		equVO.setEq_brand(eq_brand);
		equVO.setEq_price(eq_price);
		equVO.setEq_status(eq_status);
		equVO.setEq_pic(eq_pic);
		equVO.setEq_detail(eq_detail);
		equVO.setType_eq_num(type_eq_num);
		dao.update(equVO);

		return equVO;
	}

	public void deleteEquipment(String eq_num) {
		dao.delete(eq_num);
	}

	public EquipmentVO getOneEquipment(String eq_num) {
		return dao.findByPrimaryKey(eq_num);
	}
	
	public EquipmentVO getOneTypeEqNum(String type_eq_num) {
		return dao.findByTypeEqNum(type_eq_num);
	}
	
	public EquipmentVO getOneEquipmentByNameAndSize(String eq_name,String eq_size) {
		return dao.findByNameAndSize(eq_name ,eq_size);
	}
	
	public  List<EquipmentVO> getAllTypeEqNum(String type_eq_num) {
		return dao.findByAllTypeEqNum(type_eq_num);
	}
	
	public  List<EquipmentVO> getOneType(String eq_type) {
		List<EquipmentVO> list = dao.findByType(eq_type);
		List<EquipmentVO> name=new ArrayList<EquipmentVO>();
		for(int i=0;i<list.size();i++) {
			
			if(!name.contains(list.get(i))) {

				name.add(list.get(i));
				
			}else{

				list.remove(i);
			}
			}
		return name;
	}
	
	public  List<EquipmentVO> getOneSize(String eq_name) {
		return dao.findBySize(eq_name);
	}

	public List<EquipmentVO> getAll() {
		return dao.getAll();
	}
	public List<EquipmentVO> getAllnorepeat() {
		
		List<EquipmentVO> list = dao.getAll();
		List<EquipmentVO> name=new ArrayList<EquipmentVO>();
		for(int i=0;i<list.size();i++) {
			
			if(!name.contains(list.get(i))) {

				name.add(list.get(i));
				
			}else{

				list.remove(i);
			}
			}
		return name;
	}
}
