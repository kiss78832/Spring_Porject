package com.equtypetotal.model;

import java.util.List;
import java.util.Set;

import com.equipment.model.EquipmentVO;

public class EquTotalService {

	private EquTotalDAO_interface dao;

	public EquTotalService() {
		dao = new EquTotalDAO();
	}

	public EquTotalVO addEquTotal(String type_eq_name,Integer type_eq_qty) {

		EquTotalVO equtotalVO = new EquTotalVO();
		equtotalVO.setType_eq_name(type_eq_name);
		equtotalVO.setType_eq_qty(type_eq_qty);
		dao.insert(equtotalVO);

		return equtotalVO;
	}

	public EquTotalVO updateEquTotalt(String type_eq_num, String type_eq_name,Integer type_eq_qty) {

		EquTotalVO equtotalVO = new EquTotalVO();

		equtotalVO.setType_eq_num(type_eq_num);
		equtotalVO.setType_eq_name(type_eq_name);
		equtotalVO.setType_eq_qty(type_eq_qty);
		dao.update(equtotalVO);

		return equtotalVO;
	}

	public void deleteEquTotal(String type_eq_num) {
		dao.delete(type_eq_num);
	}

	public EquTotalVO getOneEquTotal(String type_eq_num) {
		return dao.findByPrimaryKey(type_eq_num);
	}

	public List<EquTotalVO> getAll() {
		return dao.getAll();
	}
	
	public Set<EquipmentVO> getEqusByTypeTotal(String type_eq_num){
		return dao.getEqusByTypeTotal(type_eq_num);
	}
}
