package com.spring.info.model;

import java.util.List;


public class InfoService {
	
	private InfoDAO_interface dao;

	public InfoService() {
		dao = new InfoJNDI();
	}

	public InfoVO addInfo(String route_ID, String route_Name, java.sql.Timestamp open_Time,
			Integer open_Status) {

		
		InfoVO infoVO = new InfoVO();
		infoVO.setRoute_ID(route_ID);
		infoVO.setRoute_Name(route_Name);
		infoVO.setOpen_Time(open_Time);
		infoVO.setOpen_Status(open_Status);

		dao.insert(infoVO);

		return infoVO;
	}

	public InfoVO updateInfo(String route_ID, String route_Name, java.sql.Timestamp open_Time,
			Integer open_Status) {


		InfoVO infoVO = new InfoVO();
		infoVO.setRoute_ID(route_ID);
		infoVO.setRoute_Name(route_Name);
		infoVO.setOpen_Time(open_Time);
		infoVO.setOpen_Status(open_Status);
		dao.update(infoVO);	
		return infoVO;
	}

	public void deleteInfo(String route_ID) {
		dao.delete(route_ID);
	}

	public InfoVO getOneInfo(String route_ID) {
		return dao.findByPrimaryKey(route_ID);
	}

	public List<InfoVO> getAll() {
		return dao.getAll();
	}
	
	public static void main(String[] args) {

		InfoService  dao = new InfoService();	
//		dao.updateInfo("R002","南橫三山",java.sql.Timestamp.valueOf("2019-10-02 10:11:10"),1);
//		dao.addInfo("R002","玉山群峰線",java.sql.Timestamp.valueOf("2019-10-02 10:10:10"),1);
	
	}
		
}
