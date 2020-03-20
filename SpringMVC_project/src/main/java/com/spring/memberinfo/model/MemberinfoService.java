package com.spring.memberinfo.model;

import java.util.List;

public class MemberinfoService {
	
	private Memberinfo_interface dao;
	
	
	
	public MemberinfoService() {
		dao = new MemberinfoDAO();
	}
	
	
	public MemberinfoVO addMemberinfo(String group_id, String member_id, String address, 
			String identity, String egc_contact, String egc_phone, String m_name,
			java.sql.Date birthday, String cellphone, String m_email) {
			
		   MemberinfoVO memberinfoVO = new MemberinfoVO();
		   
		   memberinfoVO.setGroup_id(group_id.trim());
		   memberinfoVO.setMember_id(member_id.trim());
		   memberinfoVO.setAddress(address);
		   memberinfoVO.setIdentity(identity);
		   memberinfoVO.setEgc_contact(egc_contact);
		   memberinfoVO.setEgc_phone(egc_phone);
		   memberinfoVO.setM_name(m_name);
		  
		   memberinfoVO.setBirthday(birthday);
		   memberinfoVO.setCellphone(cellphone);
		   memberinfoVO.setM_email(m_email);
	   
		   dao.insert(memberinfoVO);
		  
		   return memberinfoVO;
	}
	
	public MemberinfoVO updatememberinfo(String group_id, String member_id, String address, 
			String identity, String egc_contact, String egc_phone) {
			
			MemberinfoVO memberinfoVO = new MemberinfoVO();
			
			memberinfoVO.setGroup_id(group_id);
			memberinfoVO.setMember_id(member_id);
			memberinfoVO.setAddress(address);
			memberinfoVO.setIdentity(identity);
			memberinfoVO.setEgc_contact(egc_contact);
			memberinfoVO.setEgc_phone(egc_phone);
			dao.update(memberinfoVO);
			return memberinfoVO;
	}
	
	public void deleteMemberinfo(String group_id, String member_id) {
		dao.delete(group_id, member_id);
	}
	
	public MemberinfoVO getOneMemberinfo(String group_id, String member_id) {
		return dao.findByPrimaryKey(group_id, member_id);
	}
	
	public List<MemberinfoVO> getAll(){
		return dao.getAll();
	}
	
	/************************自訂方法********************************/
	public int countByGroupId(String group_id) {
		return dao.countByGroupId(group_id);
	}
	
	public List<MemberinfoVO>memberinfoDetail(String group_id){
		return dao.memberinfoDetail(group_id);
	}
	/************************自訂方法********************************/
	public List<MemberinfoVO> find_GroupName(String group_id) {
		return dao.find_GroupName(group_id);
	}
	
	public MemberinfoVO insertForLeader(String group_id, String member_id) {
		   MemberinfoVO memberinfoVO = new MemberinfoVO();
		   memberinfoVO.setGroup_id(group_id.trim());
		   memberinfoVO.setMember_id(member_id.trim());
		   dao.insert(memberinfoVO);
		   return memberinfoVO;
	}
	
	public List<MemberinfoVO> find_GroupMember(String member_id) {
		return dao.find_GroupMember(member_id);
	}
	
}
