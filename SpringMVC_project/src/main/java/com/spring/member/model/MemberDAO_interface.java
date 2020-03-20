package com.spring.member.model;

import java.util.List;


public interface MemberDAO_interface {
	
    public void insert(MemberVO mVO);
    public void update(MemberVO mVO);
    public void delete(String member_id);
    public MemberVO findByPrimaryKey(String member_id);
    public List<MemberVO> getAll();
    
    /*自訂方法*/
    public void updateData(MemberVO mVO);
    public boolean signUp(MemberVO mVO);
    public void cardUpdate(MemberVO mVO);
    public boolean compareWith(String member_id);
    public Integer memberCount();
    public String[] forgetPassword(String sf_account, String sf_email);
    
    public boolean isMember(String member_id, String password);
}
