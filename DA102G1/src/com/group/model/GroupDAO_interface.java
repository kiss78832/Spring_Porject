package com.group.model;
import java.util.*;
import com.itinerary.model.*;
import com.application.model.*;


public interface GroupDAO_interface {
	public void insert(GroupVO groupVO);
	public void update(GroupVO groupVO);
	public void delete(String group_id);
	public GroupVO findByPrimaryKey(String group_id);
	public List<GroupVO> getAll();
//	public List<GroupVO> getAll(Map<String, String[]> map);
	
	/*************自定方法*****************/
	public String insertGroup(GroupVO groupVO);
	public GroupVO findByGroupId(String group_id);
	public void updateGroup(GroupVO groupVO);
	public void changeGroup(GroupVO groupVO);
	public GroupVO memberByGroupId(String group_id);
	public void updateStatus(GroupVO groupVO); 
	public List<GroupVO>getGroupLeader(String gp_leader, Integer gp_status, Integer gp_status1, Integer gp_status2);
	public void insterApplication(ApplicationVO applicationVO);//送出申請
	public List<GroupVO>GroupHistory(String gp_leader, Integer gp_status);
	public List<GroupVO>joinGroup(Integer gp_status, Integer gp_status1);
	
}
 