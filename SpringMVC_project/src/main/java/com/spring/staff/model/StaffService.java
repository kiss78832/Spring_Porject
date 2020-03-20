package com.spring.staff.model;

import java.util.List;

public class StaffService {

	private StaffDAO_interface dao;
	
	public StaffService() {
		dao = new StaffDAO();
	}
	
	
	public StaffVO findByAccount(String account) {
		
		return dao.findByAccount(account);
	}
	
	public StaffVO addStaff(Integer sf_status,String sf_account,
			String sf_password,String sf_name,Integer gender,String cellphone,
			String sf_email) {
		StaffVO staffVO = new StaffVO();
		

		staffVO.setSf_status(sf_status);
		staffVO.setSf_account(sf_account);
		staffVO.setSf_password(sf_password);
		staffVO.setSf_name(sf_name);
		staffVO.setGender(gender);
		staffVO.setCellphone(cellphone);
		staffVO.setSf_email(sf_email);
		
		dao.insert(staffVO);
		
		return staffVO;
	}
	
	public StaffVO updateStaff(Integer sf_status,String sf_account,
			String sf_name,Integer gender,String cellphone,
			String sf_email,String sf_id) {
		
		
		StaffVO staffVO = new StaffVO();
		
		staffVO.setSf_status(sf_status);
		staffVO.setSf_account(sf_account);
		staffVO.setSf_password(dao.findByAccount(sf_account).getSf_password());
		staffVO.setSf_name(sf_name);
		staffVO.setGender(gender);
		staffVO.setCellphone(cellphone);
		staffVO.setSf_email(sf_email);
		staffVO.setSf_id(sf_id);
		
		dao.update(staffVO);
		
		return staffVO;
	}
	
	
	public void deleteStaff(String sf_id) {
		dao.delete(sf_id);
	}
	
	public StaffVO getOneStaff(String sf_id) {

		return dao.findByPrimaryKey(sf_id);
	}
	
	
	public List<StaffVO> getAll(){
		return dao.getAll();
	}
	
	public boolean compare(String sf_account) {
		return dao.compare(sf_account);
	}
	
}
