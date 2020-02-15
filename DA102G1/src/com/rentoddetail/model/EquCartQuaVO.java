package com.rentoddetail.model;

import com.equipment.model.EquipmentVO;

public class EquCartQuaVO extends EquipmentVO{
	private int quantity;

	public EquCartQuaVO(String eq_num,String eq_name,String eq_type, String eq_size, String eq_brand, Integer eq_price, Integer eq_status , byte[] eq_pic, String eq_detail ,String type_eq_num,int quantity) {
		super(eq_num, eq_name, eq_type, eq_size, eq_brand, eq_price, eq_status, eq_pic, eq_detail, type_eq_num);
		this.quantity = quantity;
	}

	public EquCartQuaVO(EquipmentVO equipmentVO, int quantity) {
		this(equipmentVO.getEq_num() ,equipmentVO.getEq_name() ,equipmentVO.getEq_type() ,equipmentVO.getEq_size() ,equipmentVO.getEq_brand() ,equipmentVO.getEq_price() ,equipmentVO.getEq_status() ,equipmentVO.getEq_pic() ,equipmentVO.getEq_detail() ,equipmentVO.getType_eq_num() , quantity);
		this.quantity = quantity;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
