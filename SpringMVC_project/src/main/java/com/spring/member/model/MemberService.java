package com.spring.member.model;

import java.sql.Date;
import java.util.List;

public class MemberService {
	
	private MemberDAO_interface dao;
	
	public MemberService() {
		dao = new MemberDAO();
	}
	
	

	
	
	public MemberVO addMember(String member_id,String password,String m_name,
			Integer gender,Date birthday,String cellphone,String m_email,
			byte[] m_photo,Integer validation,Date registered,Integer adventure_point,
			String outdoor_exp,byte[] back_img,String nick_name,Integer raiders_rate) {
		MemberVO memberVO = new MemberVO();
		memberVO.setMember_id(member_id);
		memberVO.setPassword(password);
		memberVO.setM_name(m_name);
		memberVO.setGender(gender);
		memberVO.setBirthday(birthday);
		memberVO.setCellphone(cellphone);
		memberVO.setM_email(m_email);
		memberVO.setM_photo(m_photo);
		memberVO.setValidation(validation);
		memberVO.setRegistered(registered);
		memberVO.setAdventure_point(adventure_point);
		memberVO.setOutdoor_exp(outdoor_exp);
		memberVO.setBack_img(back_img);
		memberVO.setNick_name(nick_name);
		memberVO.setRaiders_rate(raiders_rate);
		
		dao.insert(memberVO);
		
		return memberVO;
	}

	public MemberVO updateMember(String member_id,String password,String m_name,
			Integer gender,Date birthday,String cellphone,String m_email,
			byte[] m_photo,Integer validation,Date registered,Integer adventure_point,
			String outdoor_exp,byte[] back_img,String nick_name,Integer raiders_rate) {
		
		MemberVO memberVO = new MemberVO();
		memberVO.setMember_id(member_id);
		memberVO.setPassword(password);
		memberVO.setM_name(m_name);
		memberVO.setGender(gender);
		memberVO.setBirthday(birthday);
		memberVO.setCellphone(cellphone);
		memberVO.setM_email(m_email);
		memberVO.setM_photo(m_photo);
		memberVO.setValidation(validation);
		memberVO.setRegistered(registered);
		memberVO.setAdventure_point(adventure_point);
		memberVO.setOutdoor_exp(outdoor_exp);
		memberVO.setBack_img(back_img);
		memberVO.setNick_name(nick_name);
		memberVO.setRaiders_rate(raiders_rate);
		
		dao.update(memberVO);
		
		return memberVO;
	}

	public void deleteMember(String member_id) {
		dao.delete(member_id);
	}
	

	public MemberVO getOneMember(String member_id) {
		return dao.findByPrimaryKey(member_id);
	}

	public List<MemberVO> getAll() {
		return dao.getAll();
	}


	
	public void updateMemberData(String m_name,String member_id,String password,
			Integer gender,java.sql.Date birthday,String cellphone,String m_email) {
		
		MemberVO memberVO = new MemberVO();
		memberVO.setM_name(m_name);
		memberVO.setMember_id(member_id);
		memberVO.setPassword(password);
		memberVO.setGender(gender);
		memberVO.setBirthday(birthday);
		memberVO.setCellphone(cellphone);
		memberVO.setM_email(m_email);
		
		System.out.println("say something");
		
		dao.updateData(memberVO);
		
	}

	public boolean signUp(String m_name,String member_id,String password,
			String m_email) {
		MemberVO memberVO = new MemberVO();
		
		
		memberVO.setM_name(m_name);
		memberVO.setMember_id(member_id);
		memberVO.setPassword(password);
		memberVO.setM_email(m_email);
		
		boolean isAdd = dao.signUp(memberVO);
		
		return isAdd;
	}
	
	public boolean compareWith(String member_id) {
		boolean exist =  dao.compareWith(member_id);
		return exist;
	}
	
	public MemberVO cardUpdate(String nick_name,String outdoor_exp,
		byte[] m_photo,byte[] back_img,String member_id) {
		MemberVO memberVO = new MemberVO();
		
		memberVO.setNick_name(nick_name);
		memberVO.setOutdoor_exp(outdoor_exp);
		memberVO.setM_photo(m_photo);
		memberVO.setBack_img(back_img);
		memberVO.setMember_id(member_id);
		
		dao.cardUpdate(memberVO);
		
		return memberVO;
	}
	
	public Integer memberCount() {
		
		return dao.memberCount();
	}
	
	
	public String[] forgetPassword(String member_id, String m_email) {
		return dao.forgetPassword(member_id, m_email);
	}
}
