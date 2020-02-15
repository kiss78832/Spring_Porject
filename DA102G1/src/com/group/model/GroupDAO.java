package com.group.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.application.model.*;
import com.itinerary.model.ItineraryVO;

public class GroupDAO implements GroupDAO_interface  {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB2");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
																																															
	private static final String ISDERT_STMT=
			"INSERT INTO group_up (group_id, gp_leader, gp_name, gp_status, gp_nbp, app_time, satellite, radio, group_report, dailybed_provided, meal_provided, first_day, end_day, target_loca) VALUES ( 'G'||LPAD(to_char(SQU_GROUP_ID.NEXTVAL), 6, '0'), ?, ?, ?, ?, CURRENT_TIMESTAMP, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE = 
			"UPDATE group_up set gp_leader=?, gp_name=?, gp_status=?, gp_nbp=?, satellite=?, radio=?, group_report=?, dailybed_provided=?, meal_provided=?, first_day=?, end_day=?, target_loca=? where group_id = ?";
	private static final String DELETE = 
			"DELETE FROM group_up where group_id = ?";
	private static final String GET_ONE_STMT = 
			"SELECT * FROM group_up where group_id = ?";
	private static final String GET_ALL_STMT = 
			"SELECT group_id, gp_leader, gp_name, gp_status, gp_nbp, app_time, satellite, radio, group_report, dailybed_provided, meal_provided, first_day, end_day, target_loca FROM group_up order by group_id";
	
	
	/********************自訂方法*******************************/
	private static final String ISDERT_GROUP=
			"INSERT INTO group_up (group_id, gp_leader, gp_name, gp_status, gp_nbp, satellite, group_report, dailybed_provided, meal_provided, first_day,  target_loca, radio, route_id) VALUES ( 'G'||LPAD(to_char(SQU_GROUP_ID.NEXTVAL), 6, '0'),?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String FIND_BY_GROUPID=
			"SELECT group_id, gp_leader, gp_name, gp_nbp, first_day, satellite, radio, target_loca, route_id  FROM group_up where group_id =?";
	private static final String UPDATE_GROUP=
			"UPDATE group_up set  gp_name=?, gp_nbp=?, satellite=?, radio=?, first_day=?, target_loca=? where group_id = ?";
	private static final String MEMBERE_FIND_BY_GROUPID=
			"SELECT group_id, gp_leader, gp_name, gp_nbp, first_day, satellite, radio, target_loca, route_id FROM group_up where group_id =?";
	private static final String UPDATESTATUS = 
			"UPDATE group_up set gp_status=? where group_id = ?";
	private static final String UPDATESTA_CHECK = 
			"UPDATE group_up set gp_status=? where group_id = ?";
	private static final String GET_GROUP_LEADER = 
			"SELECT * FROM group_up where gp_leader = ? and (gp_status=? or gp_status=? or gp_status=?)";
	private static final String INSERT_GROUP = 
			"INSERT INTO application (app_num, group_id, member_id, route_id, app_status,  satellite, radio, app_time, app_days, dailybed_provided, meal_provided, first_day, locations)"
			+ "VALUES ('A'||LPAD(to_char(SQU_APPLICATION.NEXTVAL),6,'0'), ?, ?, ?, ?, ?, ?,CURRENT_TIMESTAMP, ?, ?, ?, ?, ?)";
	private static final String GROUP_HISTORY = 
			"SELECT * FROM group_up where gp_leader= ? and gp_status=?"; 
	private static final String JOINGROUP= 
			"SELECT * FROM group_up where gp_status=? or gp_status=? ORDER BY GROUP_ID DESC";
	
	
	@Override
	public void insert(GroupVO groupVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(ISDERT_STMT);

			pstmt.setString(1, groupVO.getGp_leader());
			pstmt.setString(2, groupVO.getGp_name());
			pstmt.setInt(3, groupVO.getGp_status());
			pstmt.setInt(4, groupVO.getGp_nbp());
			pstmt.setString(5, groupVO.getSatellite());
			pstmt.setString(6, groupVO.getRadio());
			pstmt.setInt(7, groupVO.getGroup_report());
			pstmt.setInt(8, groupVO.getDailybed_provided());
			pstmt.setInt(9, groupVO.getMeal_provided());
			pstmt.setDate(10, groupVO.getFirst_day());
			pstmt.setString(11, groupVO.getTarget_loca());
			
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
	public void update(GroupVO groupVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, groupVO.getGp_leader());
			pstmt.setString(2, groupVO.getGp_name());
			pstmt.setInt(3, groupVO.getGp_status());
			pstmt.setInt(4, groupVO.getGp_nbp());
			pstmt.setString(5, groupVO.getSatellite());
			pstmt.setString(6, groupVO.getRadio());
			pstmt.setInt(7, groupVO.getGroup_report());
			pstmt.setInt(8, groupVO.getDailybed_provided());
			pstmt.setInt(9, groupVO.getMeal_provided());
			pstmt.setDate(10, groupVO.getFirst_day());
			pstmt.setString(11, groupVO.getGroup_id());
			pstmt.setString(12, groupVO.getTarget_loca());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
	public void delete(String group_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		System.out.println(group_id);
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, group_id);

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
	public GroupVO findByPrimaryKey(String group_id) {
		GroupVO groupVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, group_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
			
				groupVO = new GroupVO();
				groupVO.setGroup_id(rs.getString("group_id"));
				groupVO.setGp_leader(rs.getString("gp_leader"));
				groupVO.setGp_name(rs.getString("gp_name"));
				groupVO.setGp_status(rs.getInt("gp_status"));
				groupVO.setGp_nbp(rs.getInt("gp_nbp"));
				groupVO.setApp_time(rs.getTimestamp("app_time"));
				groupVO.setSatellite(rs.getString("satellite"));
				groupVO.setRadio(rs.getString("radio"));
				groupVO.setGroup_report(rs.getInt("group_report"));
				groupVO.setDailybed_provided(rs.getInt("dailybed_provided"));
				groupVO.setMeal_provided(rs.getInt("meal_provided"));
				groupVO.setFirst_day(rs.getDate("first_day"));
				groupVO.setTarget_loca(rs.getString("target_loca"));
			}
		
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
		return groupVO;
	}
	
	@Override
	public List<GroupVO> getAll() {
		List<GroupVO> list = new ArrayList<GroupVO>();
		GroupVO groupVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				groupVO = new GroupVO();
				groupVO.setGroup_id(rs.getString("group_id"));
				groupVO.setGp_leader(rs.getString("gp_leader"));
				groupVO.setGp_name(rs.getString("gp_name"));
				groupVO.setGp_status(rs.getInt("gp_status"));
				groupVO.setGp_nbp(rs.getInt("gp_nbp"));
				groupVO.setApp_time(rs.getTimestamp("app_time"));
				groupVO.setSatellite(rs.getString("satellite"));
				groupVO.setRadio(rs.getString("radio"));
				groupVO.setGroup_report(rs.getInt("group_report"));
				groupVO.setDailybed_provided(rs.getInt("dailybed_provided"));
				groupVO.setMeal_provided(rs.getInt("meal_provided"));
				groupVO.setFirst_day(rs.getDate("first_day"));
				groupVO.setTarget_loca(rs.getString("target_loca"));
				list.add(groupVO); 
				// Store the row in the list
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
	
	
	
	/***********************************自訂方法*********************************************/
	@Override
	public String insertGroup(GroupVO groupVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String nextgroup_id = "";
		
		try {
			con = ds.getConnection();
	
			//存入自增主鍵
			String[] cols = {"group_id"}; 	
			pstmt = con.prepareStatement(ISDERT_GROUP, cols);
		
			pstmt.setString(1, groupVO.getGp_leader());
			pstmt.setString(2, groupVO.getGp_name());
			pstmt.setInt(3, groupVO.getGp_status());
			pstmt.setInt(4, groupVO.getGp_nbp());
			pstmt.setString(5, groupVO.getSatellite());
			pstmt.setInt(6, groupVO.getGroup_report());
			pstmt.setInt(7, groupVO.getDailybed_provided());
			pstmt.setInt(8, groupVO.getMeal_provided());
			pstmt.setDate(9, groupVO.getFirst_day());
			pstmt.setString(10, groupVO.getTarget_loca());
			pstmt.setString(11, groupVO.getRadio());
			pstmt.setString(12, groupVO.getRoute_id());
			pstmt.executeUpdate();
			
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()){			
				nextgroup_id = rs.getString(1);
					System.out.println("自增主鍵值 = " + nextgroup_id);
			} else {
				System.out.println("NO KEYS WERE GENERATED.");
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
		return nextgroup_id;
	}
	
	
	/*團主查詢詳細資料*/
	@Override
	public GroupVO findByGroupId(String group_id) {
		GroupVO groupVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
 		System.out.println(group_id);

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_GROUPID);

			pstmt.setString(1, group_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
	
				groupVO = new GroupVO();
				groupVO.setGroup_id(rs.getString("group_id"));
				groupVO.setGp_leader(rs.getString("gp_leader"));
				groupVO.setGp_name(rs.getString("gp_name"));
				groupVO.setGp_nbp(rs.getInt("gp_nbp"));
				groupVO.setFirst_day(rs.getDate("first_day"));
				groupVO.setSatellite(rs.getString("satellite"));
				groupVO.setRadio(rs.getString("radio"));
				groupVO.setTarget_loca(rs.getString("target_loca"));
				groupVO.setRoute_id(rs.getString("route_id"));

			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
		return groupVO;
	}
	
	
	
	@Override
	public void updateGroup(GroupVO groupVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_GROUP);
			pstmt.setString(1, groupVO.getGp_name());
			pstmt.setInt(2, groupVO.getGp_nbp());
			pstmt.setString(3, groupVO.getSatellite());
			pstmt.setString(4, groupVO.getRadio());
			pstmt.setDate(5, groupVO.getFirst_day());
//			pstmt.setDate(6, groupVO.getEnd_day());
			pstmt.setString(6, groupVO.getTarget_loca());
			pstmt.setString(7, groupVO.getGroup_id());
			
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
	public GroupVO memberByGroupId(String group_id) {
		GroupVO groupVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(MEMBERE_FIND_BY_GROUPID);

			pstmt.setString(1, group_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
	
				groupVO = new GroupVO();
				groupVO.setGroup_id(rs.getString("group_id"));
				groupVO.setGp_leader(rs.getString("gp_leader"));
				groupVO.setGp_name(rs.getString("gp_name"));
				groupVO.setGp_nbp(rs.getInt("gp_nbp"));
				groupVO.setFirst_day(rs.getDate("first_day"));
//				groupVO.setEnd_day(rs.getDate("end_day"));
				groupVO.setSatellite(rs.getString("satellite"));
				groupVO.setRadio(rs.getString("radio"));
				groupVO.setTarget_loca(rs.getString("target_loca"));
				groupVO.setRoute_id(rs.getString("Route_id"));
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
		return groupVO;
	}
	
	
	@Override
	public void updateStatus(GroupVO groupVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATESTATUS);
			pstmt.setInt(1, groupVO.getGp_status());
			pstmt.setString(2, groupVO.getGroup_id());
			
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
	public void changeGroup(GroupVO groupVO) {//審核狀態
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATESTA_CHECK);
			pstmt.setInt(1, groupVO.getGp_status());
			pstmt.setString(2, groupVO.getGroup_id());
			
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
	public List<GroupVO> getGroupLeader(String gp_leader, Integer gp_status, Integer gp_status1, Integer gp_status2) {
		List<GroupVO> list = new ArrayList<GroupVO>();
		GroupVO groupVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_GROUP_LEADER);
			pstmt.setString(1, gp_leader);
			pstmt.setInt(2, gp_status);
			pstmt.setInt(3, gp_status1);
			pstmt.setInt(4, gp_status2);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				groupVO = new GroupVO();
				groupVO.setGroup_id(rs.getString("group_id"));
				groupVO.setGp_leader(rs.getString("gp_leader"));
				groupVO.setGp_name(rs.getString("gp_name"));
				groupVO.setGp_status(rs.getInt("gp_status"));
				groupVO.setGp_nbp(rs.getInt("gp_nbp"));
				groupVO.setApp_time(rs.getTimestamp("app_time"));
				groupVO.setSatellite(rs.getString("satellite"));
				groupVO.setRadio(rs.getString("radio"));
				groupVO.setGroup_report(rs.getInt("group_report"));
				groupVO.setDailybed_provided(rs.getInt("dailybed_provided"));
				groupVO.setMeal_provided(rs.getInt("meal_provided"));
				groupVO.setFirst_day(rs.getDate("first_day"));
//				groupVO.setEnd_day(rs.getDate("end_day"));
				groupVO.setTarget_loca(rs.getString("target_loca"));
				list.add(groupVO); 
				// Store the row in the list
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
	public void insterApplication(ApplicationVO applicationVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_GROUP);

			pstmt.setString(1, applicationVO.getGroup_id());
			pstmt.setString(2, applicationVO.getMember_id());
			pstmt.setString(3, applicationVO.getRoute_id());
			pstmt.setInt(4, applicationVO.getApp_status());		
			pstmt.setString(5, applicationVO.getSatellite());
			pstmt.setString(6, applicationVO.getRadio());
			pstmt.setInt(7, applicationVO.getApp_days());
			pstmt.setInt(8, applicationVO.getDailybed_provided());
			pstmt.setInt(9, applicationVO.getMeal_provided());
			pstmt.setDate(10, applicationVO.getFirst_day());
			pstmt.setString(11, applicationVO.getLocations());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
	public List<GroupVO> GroupHistory(String gp_leader, Integer gp_status) {
		List<GroupVO> list2 = new ArrayList<GroupVO>();
		GroupVO groupVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GROUP_HISTORY);
			pstmt.setString(1, gp_leader);
			pstmt.setInt(2, gp_status);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				groupVO = new GroupVO();
				groupVO.setGroup_id(rs.getString("group_id"));
				groupVO.setGp_leader(rs.getString("gp_leader"));
				groupVO.setGp_name(rs.getString("gp_name"));
				groupVO.setGp_status(rs.getInt("gp_status"));
				groupVO.setGp_nbp(rs.getInt("gp_nbp"));
				groupVO.setRadio(rs.getString("radio"));
				groupVO.setGroup_report(rs.getInt("group_report"));
				groupVO.setDailybed_provided(rs.getInt("dailybed_provided"));
				groupVO.setMeal_provided(rs.getInt("meal_provided"));
				groupVO.setFirst_day(rs.getDate("first_day"));
				groupVO.setTarget_loca(rs.getString("target_loca"));
				list2.add(groupVO); 
				// Store the row in the list
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
		return list2;
	}
	
	
	@Override
	public List<GroupVO> joinGroup(Integer gp_status, Integer gp_status1) {
		List<GroupVO> list = new ArrayList<GroupVO>();
		GroupVO groupVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(JOINGROUP);
			pstmt.setInt(1, gp_status);
			pstmt.setInt(2, gp_status1);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				groupVO = new GroupVO();
				groupVO.setGroup_id(rs.getString("group_id"));
				groupVO.setGp_leader(rs.getString("gp_leader"));
				groupVO.setGp_name(rs.getString("gp_name"));
				groupVO.setGp_status(rs.getInt("gp_status"));
				groupVO.setGp_nbp(rs.getInt("gp_nbp"));
				groupVO.setApp_time(rs.getTimestamp("app_time"));
				groupVO.setSatellite(rs.getString("satellite"));
				groupVO.setRadio(rs.getString("radio"));
				groupVO.setGroup_report(rs.getInt("group_report"));
				groupVO.setDailybed_provided(rs.getInt("dailybed_provided"));
				groupVO.setMeal_provided(rs.getInt("meal_provided"));
				groupVO.setFirst_day(rs.getDate("first_day"));
//				groupVO.setEnd_day(rs.getDate("end_day"));
				groupVO.setTarget_loca(rs.getString("target_loca"));
				list.add(groupVO); 
				// Store the row in the list
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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

	
	
	
//	@Override
//	public List<GroupVO> getAll(Map<String, String[]> map) {

//		return null;
//	}
	
	public static void main(String[] args) {

		GroupDAO dao = new GroupDAO();
		ApplicationJDBCDAO appdao = new ApplicationJDBCDAO();
//		 新增
//		GroupVO groupVO1 = new GroupVO();
//		groupVO1.setGp_leader("A002");
//		groupVO1.setGp_name("一休逆嘿嘿");
//		groupVO1.setGp_status(2);
//		groupVO1.setGp_nbp(5);
//		groupVO1.setSatellite("123555564");
//		groupVO1.setRadio("5654123");
//		groupVO1.setGroup_report(1);
//		groupVO1.setDailybed_provided(5);
//		groupVO1.setMeal_provided(5);
//		groupVO1.setEntry_day(java.sql.Date.valueOf("2019-08-19"));
//		groupVO1.setEnd_day(java.sql.Date.valueOf("2019-08-30"));
//		groupVO1.setTarget_loca("玉山主峰");
//		dao.insert(groupVO1);

//		 修改
//		GroupVO groupVO2 = new GroupVO();
//		groupVO2.setGroup_id("G000006");
//		groupVO2.setGp_leader("A002");
//		groupVO2.setGp_name("嘿嘿嘿");
//		groupVO2.setGp_status(5);
//		groupVO2.setGp_nbp(6);
//		groupVO2.setSatellite("145454523");
//		groupVO2.setRadio("6666");
//		groupVO2.setGroup_report(1);
//		groupVO2.setDailybed_provided(5);
//		groupVO2.setMeal_provided(5);
//		groupVO2.setEntry_day(java.sql.Date.valueOf("2019-08-19"));
//		groupVO2.setEnd_day(java.sql.Date.valueOf("2019-08-29"));
//		groupVO2.setTarget_loca("玉山南峰");
//		dao.update(groupVO2);

		// 刪除
//		dao.delete("G000007");

//		 查詢
//		
//		GroupVO groupVO3 = dao.findByPrimaryKey("G000001");
//		System.out.print(groupVO3.getGroup_id() + ",");
//		System.out.print(groupVO3.getGp_leader()+ ",");
//		System.out.print(groupVO3.getGp_name() + ",");
//		System.out.print(groupVO3.getGp_status() + ",");
//		System.out.print(groupVO3.getGp_nbp() + ",");
//		System.out.print(groupVO3.getApp_time().toString() + ",");
//		System.out.print(groupVO3.getSatellite() + ",");
//		System.out.print(groupVO3.getRadio() + ",");
//		System.out.print(groupVO3.getGroup_report() + ",");
//		System.out.print(groupVO3.getDailybed_provided() + ",");
//		System.out.print(groupVO3.getMeal_provided() + ",");
//		System.out.print(groupVO3.getEntry_day() + ",");
//		System.out.println(groupVO3.getEnd_day() + ",");
//		System.out.println(groupVO3.getTarget_loca() + ",");
//		System.out.println("=================================");

		// 查詢
		
//		List<GroupVO> list = dao.getAll();
//		for (GroupVO groupVO4 : list) {
//			System.out.print(groupVO4.getGroup_id() + ",");
//			System.out.print(groupVO4.getGp_leader()+ ",");
//			System.out.print(groupVO4.getGp_name() + ",");
//			System.out.print(groupVO4.getGp_status() + ",");
//			System.out.print(groupVO4.getGp_nbp() + ",");
//			System.out.print(groupVO4.getApp_time().toString() + ",");
//			System.out.print(groupVO4.getSatellite() + ",");
//			System.out.print(groupVO4.getRadio() + ",");
//			System.out.print(groupVO4.getGroup_report() + ",");
//			System.out.print(groupVO4.getDailybed_provided() + ",");
//			System.out.print(groupVO4.getMeal_provided() + ",");
//			System.out.print(groupVO4.getFirst_day() + ",");
//			System.out.print(groupVO4.getEnd_day() + ",");
//			System.out.println(groupVO4.getTarget_loca());
//			
//			System.out.println();
//		}
		
		/*****************************自訂方法********************************/
		
//		 GroupVO groupVO5 = new GroupVO();
//			groupVO5.setGp_leader("A003");
//			groupVO5.setGp_name("賈賽");
//			groupVO5.setGp_status(2);
//			groupVO5.setGp_nbp(5);
//			groupVO5.setSatellite("123555564");
//			groupVO5.setRadio("5654123");
//			groupVO5.setGroup_report(1);
//			groupVO5.setDailybed_provided(5);
//			groupVO5.setMeal_provided(5);
//			groupVO5.setFirst_day(java.sql.Date.valueOf("2019-08-19"));
//			groupVO5.setEnd_day(java.sql.Date.valueOf("2019-08-30"));
//			groupVO5.setTarget_loca("習近平小熊維尼");
//			dao.insertGroup(groupVO5);
			
//			GroupVO groupVO4 = dao.findByGroupId("G000001");
//			System.out.print(groupVO4.getGroup_id()+ ",");
//			System.out.print(groupVO4.getGp_leader()+ ",");
//			System.out.print(groupVO4.getGp_name() + ",");
//			System.out.print(groupVO4.getGp_nbp() + ",");
//			System.out.print(groupVO4.getFirst_day() + ",");
//			System.out.print(groupVO4.getEnd_day() + ",");
//			System.out.print(groupVO4.getSatellite() + ",");
//			System.out.print(groupVO4.getRadio() + ",");
//			System.out.println(groupVO4.getTarget_loca() + ",");
//			System.out.println("=================================");
		
			//修改
//			GroupVO groupVO5 = new GroupVO();
//				groupVO5.setGroup_id("G000006");
//				groupVO5.setGp_leader("A003");
//				groupVO5.setGp_name("瑤齊甲賽啦");
//				groupVO5.setGp_nbp(6);
//				groupVO5.setSatellite("145454523");
//				groupVO5.setRadio("6666");
//				groupVO5.setFirst_day(java.sql.Date.valueOf("2019-08-19"));
//				groupVO5.setEnd_day(java.sql.Date.valueOf("2019-08-29"));
//				groupVO5.setTarget_loca("玉山主線");
//				dao.updateGroup(groupVO5);
		
				
//				第四方法(memberByGroupId)
//			GroupVO groupVO6 = dao.memberByGroupId("G000002");
//			System.out.print(groupVO6.getGroup_id() + ",");
//			System.out.print(groupVO6.getGp_leader()+ ",");
//			System.out.print(groupVO6.getGp_name() + ",");
//			System.out.print(groupVO6.getGp_nbp() + ",");
//			System.out.print(groupVO6.getSatellite() + ",");
//			System.out.print(groupVO6.getRadio() + ",");
//			System.out.print(groupVO6.getFirst_day() + ",");
//			System.out.println(groupVO6.getEnd_day() + ",");
//			System.out.println(groupVO6.getTarget_loca());
//			System.out.println("=================================");
		
//		第五方法(getAllByGroupLeader)
//		List<GroupVO> list = dao.getGroupLeader("A003");
//		for (GroupVO groupVO7 : list) {
//			System.out.print(groupVO7.getGroup_id() + ",");
//			System.out.print(groupVO7.getGp_leader()+ ",");
//			System.out.print(groupVO7.getGp_name() + ",");
//			System.out.print(groupVO7.getGp_status() + ",");
//			System.out.print(groupVO7.getGp_nbp() + ",");
//			System.out.print(groupVO7.getSatellite() + ",");
//			System.out.print(groupVO7.getRadio() + ",");
//			System.out.print(groupVO7.getDailybed_provided() + ",");
//			System.out.print(groupVO7.getMeal_provided() + ",");
//			System.out.print(groupVO7.getFirst_day() + ",");
//			System.out.print(groupVO7.getEnd_day() + ",");
//			System.out.println(groupVO7.getTarget_loca());
//			
//			System.out.println();
//		}
		
//		ApplicationVO applicationVO = new ApplicationVO();
//		applicationVO.setGroup_id("G000004");
//		applicationVO.setMember_id("A002");
//		applicationVO.setRoute_id("R001");
//		applicationVO.setSatellite("777777");
//		applicationVO.setRadio("7777777");
//		applicationVO.setApp_status(1);
//		applicationVO.setApp_days(2);
//		applicationVO.setFirst_day(java.sql.Date.valueOf("1999-01-01"));
//		applicationVO.setDailybed_provided(2);
//		applicationVO.setMeal_provided(1);
//		dao.insterApplication(applicationVO);
		
	}



	

}
