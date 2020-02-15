package com.memberinfo.model;

import java.util.List;

public interface Memberinfo_interface {
	public void insert(MemberinfoVO memberinfoVO);
	public void update(MemberinfoVO memberinfoVO);
	public void delete(String group_id, String member_id);
	public MemberinfoVO findByPrimaryKey(String group_id, String member_id);
	public List<MemberinfoVO> getAll();
	
//	public List<MemberVO> getAll(Map<String, String[]> map);
	
	/*****************************/
	public int countByGroupId(String group_id);
	
	public List<MemberinfoVO> memberinfoDetail(String group_id);
	public  List<MemberinfoVO> find_GroupName(String group_id) ;
	public void insertForLeader(MemberinfoVO memberinfoVO);
	public  List<MemberinfoVO> find_GroupMember(String member_id);
}
