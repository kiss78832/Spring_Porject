package com.group.model;

import java.sql.Date;
import java.util.List;

import com.application.model.*;

public class GroupService {
	private  GroupDAO_interface dao;
	
	public GroupService() {
		dao = new GroupDAO();
	}
	
	public GroupVO addGroup(String group_id, String gp_leader,String gp_name,Integer gp_status,Integer gp_nbp,
			java.sql.Timestamp app_time, String satellite, String radio, Integer group_report,Integer dailybed_provided,
			Integer meal_provided, java.sql.Date first_day, String target_loca) {
		
		GroupVO groupVO = new GroupVO();
		 
		groupVO.setGroup_id(group_id.trim());
		groupVO.setGp_leader(gp_leader);
		groupVO.setGp_name(gp_name);
		groupVO.setGp_status(gp_status);
		groupVO.setGp_nbp(gp_nbp);
		groupVO.setApp_time(app_time);
		groupVO.setSatellite(satellite);
		groupVO.setRadio(radio);
		groupVO.setGroup_report(group_report);
		groupVO.setDailybed_provided(dailybed_provided);
		groupVO.setMeal_provided(meal_provided);
		groupVO.setFirst_day(first_day);
		groupVO.setTarget_loca(target_loca);
		dao.insert(groupVO);
		
		return groupVO;
	}
	
	public GroupVO update(String group_id, String gp_leader,String gp_name,Integer gp_status,Integer gp_nbp,
			java.sql.Timestamp app_time, String satellite, String radio, Integer group_report,Integer dailybed_provided,
			Integer meal_provided, java.sql.Date first_day, String target_loca) {
		
		GroupVO groupVO = new GroupVO();
		
		groupVO.setGroup_id(group_id.trim());
		groupVO.setGp_leader(gp_leader);
		groupVO.setGp_name(gp_name);
		groupVO.setGp_status(gp_status);
		groupVO.setGp_nbp(gp_nbp);
		groupVO.setApp_time(app_time);
		groupVO.setSatellite(satellite);
		groupVO.setRadio(radio);
		groupVO.setGroup_report(group_report);
		groupVO.setDailybed_provided(dailybed_provided);
		groupVO.setMeal_provided(meal_provided);
		groupVO.setFirst_day(first_day);
		groupVO.setTarget_loca(target_loca);
		dao.update(groupVO);
		
		return groupVO;
	}
	
	public void deleteGroup(String group_id) {
		dao.delete(group_id.trim());
	}
	
	public GroupVO getOneGroup(String group_id) {
		return dao.findByPrimaryKey(group_id.trim());
	}
	
	public List<GroupVO> getAll(){
		return dao.getAll();
	}
	
	
	/***************************自訂方法***************************/
	
	public GroupVO addgroupData(String gp_leader,String gp_name,Integer gp_status,Integer gp_nbp,
			String satellite, String radio, Integer group_report,Integer dailybed_provided,
			Integer meal_provided, java.sql.Date first_day,String target_loca, String route_id) {
				
			GroupVO groupVO = new GroupVO();
			
			groupVO.setGp_leader(gp_leader.trim());
			groupVO.setGp_name(gp_name);
			groupVO.setGp_status(gp_status);
			groupVO.setGp_nbp(gp_nbp);
			groupVO.setSatellite(satellite);
			groupVO.setRadio(radio);
			groupVO.setGroup_report(group_report);
			groupVO.setDailybed_provided(dailybed_provided);
			groupVO.setMeal_provided(meal_provided);
			groupVO.setFirst_day(first_day);
			groupVO.setTarget_loca(target_loca);
			groupVO.setRoute_id(route_id);
			
			String str = dao.insertGroup(groupVO);
			
			groupVO.setGroup_id(str);
			return groupVO;
	}
	
	
	public GroupVO getOneGroupById(String group_id) {
		return dao.findByGroupId(group_id.trim());
	}
	
	
	public GroupVO updateGroup(String group_id,String gp_name, Integer gp_nbp,
			String satellite, String radio, java.sql.Date first_day, String target_loca) {
				
		GroupVO groupVO = new GroupVO();
		
		groupVO.setGroup_id(group_id.trim());
		groupVO.setGp_name(gp_name);
		groupVO.setGp_nbp(gp_nbp);
		groupVO.setSatellite(satellite);
		groupVO.setRadio(radio);
		groupVO.setFirst_day(first_day);
		groupVO.setTarget_loca(target_loca);
		dao.updateGroup(groupVO);
		
		return groupVO;
	}
	
	
	public GroupVO memberByGroupId(String group_id) {
		return dao.memberByGroupId(group_id.trim());
	}
	
	
//	更改揪團狀態
	public GroupVO updateStatus(String group_id, Integer gp_status) {
			
		GroupVO groupVO = new GroupVO();
		
		groupVO.setGroup_id(group_id.trim());
		groupVO.setGp_status(gp_status);
        dao.updateStatus(groupVO);
		return groupVO;
	}
	
	public List<GroupVO> getGroupLeader(String gp_leader, Integer gp_status, Integer gp_status1, Integer gp_status2) {
		return dao.getGroupLeader(gp_leader, gp_status, gp_status1, gp_status2);
	}
	
	public ApplicationVO insterApplication(String group_id, String member_id, String route_id, Integer app_status,
			String satellite, String radio, Integer app_days, Integer dailybed_provided, Integer meal_provided,Date first_day, String locations) {
		
		ApplicationVO applicationVO = new ApplicationVO();
		
		applicationVO.setGroup_id(group_id);
		applicationVO.setMember_id(member_id);
		applicationVO.setRoute_id(route_id);
		applicationVO.setApp_status(app_status);
		applicationVO.setSatellite(satellite);
		applicationVO.setRadio(radio);
		applicationVO.setApp_days(app_days);
		applicationVO.setDailybed_provided(dailybed_provided);
		applicationVO.setMeal_provided(meal_provided);
		applicationVO.setFirst_day(first_day);
		applicationVO.setLocations(locations);
		dao.insterApplication(applicationVO);
		return applicationVO;
	}
	
	public List<GroupVO> GroupHistory(String gp_leader, Integer gp_status){
		return dao.GroupHistory(gp_leader, gp_status);
	}
	
	public List<GroupVO> joinGroupGroup(Integer gp_status, Integer gp_status1) {
		return dao.joinGroup(gp_status, gp_status1);
	}
	
	public GroupVO changeGroup(String group_id, Integer gp_status) { //審核狀態
		GroupVO groupVO = new GroupVO();
		groupVO.setGroup_id(group_id.trim());
		groupVO.setGp_status(gp_status);
        dao.changeGroup(groupVO);
		return groupVO;
	}
	
}