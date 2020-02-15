package com.application.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;




public class ApplicationJNDI implements ApplicationDAO_interface{

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB2");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = 
		"INSERT INTO application (app_num,group_id,member_id,route_id,egc_contact,egc_phone,satellite,radio,app_status,app_time,app_days,first_day,dailybed_provided,meal_provided,locations)"
		+ "VALUES ('A'||LPAD(to_char(SQU_APPLICATION.NEXTVAL),6,'0'), ?, ?, ?, ?, ?, ?, ?, ?,CURRENT_TIMESTAMP, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT app_num,group_id,member_id,route_id,egc_contact,egc_phone,satellite,radio,app_status,to_char(app_time,'yyyy-mm-dd hh:mm:ss') app_time,app_days,to_char(first_day,'yyyy-mm-dd') first_day,dailybed_provided,meal_provided,locations "
		+ "FROM application order by app_num";
	private static final String GET_ONE_STMT = 
		"SELECT app_num,group_id,member_id,route_id,egc_contact,egc_phone,satellite,radio,app_status,to_char(app_time,'yyyy-mm-dd hh:mm:ss') app_time,app_days,to_char(first_day,'yyyy-mm-dd') first_day,dailybed_provided,meal_provided,locations "
		+ "FROM application where app_num = ?";
	private static final String UPDATE = 
		"UPDATE application set group_id=?,member_id=?,route_id=?,egc_contact=?,egc_phone=?,satellite=?,radio=?,app_status=?,app_days=?,first_day=?,dailybed_provided=?,meal_provided=?,locations=? where app_num = ?";
	private static final String UPDATE_ONE =
		"UPDATE application set app_status= ? where app_num =? ";
	private static final String APP_ONE =
		"SELECT * FROM APPLICATION WHERE MEMBER_ID = ? ";
	private static final String STATUS_LIST =
			"SELECT * FROM APPLICATION WHERE app_status = ? ORDER BY app_num DESC";
	@Override
	public void insert(ApplicationVO appVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			

			pstmt.setString(1, appVO.getGroup_id());
			pstmt.setString(2, appVO.getMember_id());
			pstmt.setString(3, appVO.getRoute_id());
			pstmt.setString(4, appVO.getEgc_contact());
			pstmt.setString(5, appVO.getEgc_phone());
			pstmt.setString(6, appVO.getSatellite());
			pstmt.setString(7, appVO.getRadio());
			pstmt.setInt(8, appVO.getApp_status());
			pstmt.setInt(9, appVO.getApp_days());
			pstmt.setDate(10, appVO.getFirst_day());
			pstmt.setInt(11, appVO.getDailybed_provided());
			pstmt.setInt(12, appVO.getMeal_provided());
			pstmt.setString(13, appVO.getLocations());

			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}
	
	
	
	
	@Override
	public ApplicationVO findByPrimaryKey(String app_num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ApplicationVO appVO = null;
		ResultSet rs = null;


		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			
			pstmt.setString(1,app_num);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				appVO = new ApplicationVO();
				appVO.setApp_num(rs.getString("App_num"));
				appVO.setGroup_id(rs.getString("Group_id"));
				appVO.setMember_id(rs.getString("Member_id"));
				appVO.setRoute_id(rs.getString("Route_id"));
				appVO.setEgc_contact(rs.getString("Egc_contact"));
				appVO.setEgc_phone(rs.getString("Egc_phone"));
				appVO.setSatellite(rs.getString("Satellite"));
				appVO.setRadio(rs.getString("Radio"));
				appVO.setApp_status(rs.getInt("App_status"));
				appVO.setApp_time(rs.getTimestamp("App_time"));
				appVO.setApp_days(rs.getInt("App_days"));
				appVO.setFirst_day(rs.getDate("First_day"));
				appVO.setDailybed_provided(rs.getInt("Dailybed_provided"));
				appVO.setMeal_provided(rs.getInt("Meal_provided"));
				appVO.setLocations(rs.getString("Locations"));
				
			}
			

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return appVO;

	}
	
	
	
	
	@Override
	public List<ApplicationVO> getAll() {
		
		List<ApplicationVO > list = new ArrayList<ApplicationVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ApplicationVO appVO = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				for(int i = 1 ; i<2 ; i++) {
				appVO = new ApplicationVO();
				appVO.setApp_num(rs.getString("App_num"));
				appVO.setGroup_id(rs.getString("Group_id"));
				appVO.setMember_id(rs.getString("Member_id"));
				appVO.setRoute_id(rs.getString("Route_id"));
				appVO.setEgc_contact(rs.getString("Egc_contact"));
				appVO.setEgc_phone(rs.getString("Egc_phone"));
				appVO.setSatellite(rs.getString("Satellite"));
				appVO.setRadio(rs.getString("Radio"));
				appVO.setApp_status(rs.getInt("App_status"));
				appVO.setApp_time(rs.getTimestamp("App_time"));
				appVO.setApp_days(rs.getInt("App_days"));
				appVO.setFirst_day(rs.getDate("First_day"));
				appVO.setDailybed_provided(rs.getInt("Dailybed_provided"));
				appVO.setMeal_provided(rs.getInt("Meal_provided"));
				appVO.setLocations(rs.getString("Locations"));
				list.add(appVO); 
				}
				
			}
			

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;

	}
	
	
	
	
	
	
	
	@Override
	public void delete(String app_num) {
		
	}
	@Override
	public void update(ApplicationVO appVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
		
			pstmt.setString(1, appVO.getGroup_id());
			pstmt.setString(2, appVO.getMember_id());
			pstmt.setString(3, appVO.getRoute_id());
			pstmt.setString(4, appVO.getEgc_contact());
			pstmt.setString(5, appVO.getEgc_phone());
			pstmt.setString(6, appVO.getSatellite());
			pstmt.setString(7, appVO.getRadio());
			pstmt.setInt(8, appVO.getApp_status());
			pstmt.setInt(9, appVO.getApp_days());
			pstmt.setDate(10, appVO.getFirst_day());
			pstmt.setInt(11, appVO.getDailybed_provided());
			pstmt.setInt(12, appVO.getMeal_provided());
			pstmt.setString(13, appVO.getLocations());
			pstmt.setString(14, appVO.getApp_num());
				
			
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}	


	public void update_One(ApplicationVO appVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_ONE);
		
			
			pstmt.setInt(1, appVO.getApp_status());
			pstmt.setString(2, appVO.getApp_num());
				
			
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}	
	
	
	/*自訂方法*/
	

	@Override
	public List<ApplicationVO> getStatus_List(Integer app_status) {
		List<ApplicationVO> list = new ArrayList<ApplicationVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ApplicationVO appVO = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(STATUS_LIST);
			
			
			pstmt.setInt(1,app_status);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				appVO = new ApplicationVO();
				appVO.setApp_num(rs.getString("App_num"));
				appVO.setGroup_id(rs.getString("Group_id"));
				appVO.setMember_id(rs.getString("Member_id"));
				appVO.setRoute_id(rs.getString("Route_id"));
				appVO.setEgc_contact(rs.getString("Egc_contact"));
				appVO.setEgc_phone(rs.getString("Egc_phone"));
				appVO.setSatellite(rs.getString("Satellite"));
				appVO.setRadio(rs.getString("Radio"));
				appVO.setApp_status(rs.getInt("App_status"));
				appVO.setApp_time(rs.getTimestamp("App_time"));
				appVO.setApp_days(rs.getInt("App_days"));
				appVO.setFirst_day(rs.getDate("First_day"));
				appVO.setDailybed_provided(rs.getInt("Dailybed_provided"));
				appVO.setMeal_provided(rs.getInt("Meal_provided"));
				appVO.setLocations(rs.getString("Locations"));
				list.add(appVO); 
				
			}
			

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	@Override
	public List<ApplicationVO> getApp_One(String member_id) {
		List<ApplicationVO> list = new ArrayList<ApplicationVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ApplicationVO appVO = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(APP_ONE);
			
			
			pstmt.setString(1,member_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				appVO = new ApplicationVO();
				appVO.setApp_num(rs.getString("App_num"));
				appVO.setGroup_id(rs.getString("Group_id"));
				appVO.setMember_id(rs.getString("Member_id"));
				appVO.setRoute_id(rs.getString("Route_id"));
				appVO.setEgc_contact(rs.getString("Egc_contact"));
				appVO.setEgc_phone(rs.getString("Egc_phone"));
				appVO.setSatellite(rs.getString("Satellite"));
				appVO.setRadio(rs.getString("Radio"));
				appVO.setApp_status(rs.getInt("App_status"));
				appVO.setApp_time(rs.getTimestamp("App_time"));
				appVO.setApp_days(rs.getInt("App_days"));
				appVO.setFirst_day(rs.getDate("First_day"));
				appVO.setDailybed_provided(rs.getInt("Dailybed_provided"));
				appVO.setMeal_provided(rs.getInt("Meal_provided"));
				appVO.setLocations(rs.getString("Locations"));
				list.add(appVO); 
				
			}
			

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;

	}
	
public static void main(String[] args) {

	ApplicationJNDI dao = new ApplicationJNDI();

	// 新增
//	ApplicationVO appVO = new ApplicationVO();
//	
//	appVO.setGroup_id("G000001");
//	appVO.setMember_id("A001");
//	appVO.setRoute_id("R001");
//	appVO.setEgc_contact("M000005");
//	appVO.setEgc_phone("M000006");
//	appVO.setSatellite("M000007");
//	appVO.setRadio("M000008");
//	appVO.setApp_status(100);
//	appVO.setApp_days(22);
//	appVO.setFirst_day(java.sql.Date.valueOf("2002-01-01"));
//	appVO.setDailybed_provided(12);
//	appVO.setMeal_provided(12);
//	dao.insert(appVO);
	
//	// 修改	
//	ApplicationVO appVO = new ApplicationVO();
//	appVO.setApp_num("A000035");
//	appVO.setApp_status(0);
//	dao.update_One(appVO);
	
	
	
//	// 修改
//	ApplicationVO appVO = new ApplicationVO();
//	appVO.setApp_num("A000021");
//	appVO.setGroup_id("G000001");
//	appVO.setMember_id("A001");
//	appVO.setRoute_id("R001");
//	appVO.setEgc_contact("M000005");
//	appVO.setEgc_phone("M000006");
//	appVO.setSatellite("M000007");
//	appVO.setRadio("M000008");
//	appVO.setApp_status(100);
//	appVO.setApp_days(22);
//	appVO.setFirst_day(java.sql.Date.valueOf("2002-01-01"));
//	appVO.setDailybed_provided(21);
//	appVO.setMeal_provided(21);
//	dao.update(appVO);
//
//	// 刪除
//	dao.delete(7014);
//
	// 查詢
	
//	ApplicationVO appVO = dao.findByPrimaryKey("A000002");
//	System.out.print(appVO.getApp_num() + ",");
//	System.out.print(appVO.getGroup_id() + ",");
//	System.out.print(appVO.getMember_id() + ",");
//	System.out.print(appVO.getRoute_id() + ",");
//	System.out.print(appVO.getEgc_contact() + ",");
//	System.out.print(appVO.getEgc_phone() + ",");
//	System.out.print(appVO.getSatellite() + ",");
//	System.out.print(appVO.getRadio() + ",");
//	System.out.print(appVO.getApp_status() + ",");
//	System.out.print(appVO.getApp_time().toString() + ",");
//	System.out.print(appVO.getApp_days()+ ",");
//	System.out.print(appVO.getDailybed_provided()+ ",");
//	System.out.println(appVO.getMeal_provided());
//	
//	System.out.println("---------------------");


	

	// 查詢
	List<ApplicationVO> list = dao.getApp_One("A001");
	for (ApplicationVO appVO : list) {
	System.out.print(appVO.getApp_num() + ",");
	System.out.print(appVO.getGroup_id() + ",");
	System.out.print(appVO.getMember_id() + ",");
	System.out.print(appVO.getRoute_id() + ",");
	System.out.print(appVO.getEgc_contact() + ",");
	System.out.print(appVO.getEgc_phone() + ",");
	System.out.print(appVO.getSatellite() + ",");
	System.out.print(appVO.getRadio() + ",");
	System.out.print(appVO.getApp_status() + ",");
	System.out.print(appVO.getApp_time().toString() + ",");
	System.out.print(appVO.getApp_days()+",");
	System.out.print(appVO.getDailybed_provided()+",");
	System.out.println(appVO.getMeal_provided());
	}

}









}