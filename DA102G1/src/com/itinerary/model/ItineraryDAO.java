package com.itinerary.model;

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

import com.group.model.*;

import com.group.model.GroupVO;

public class ItineraryDAO implements Itinerary_interface{
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
			"INSERT INTO itinerary (itinerary_id, group_id, total_day, trip_break, trip_schedule) VALUES ('I'||LPAD(to_char(SQU_ITINERARY.NEXTVAL),6,'0'), ?, ?, ?, ?)";
	private static final String UPDATE = 
			"UPDATE itinerary set group_id=?, total_day=? where itinerary_id= ?";
	private static final String DELETE = 
			"DELETE FROM itinerary where itinerary_id = ?";
	private static final String GET_ONE_STMT = 
			"SELECT * FROM itinerary where itinerary_id = ?";
	private static final String GET_ALL_STMT = 
			"SELECT itinerary_id, group_id, total_day FROM itinerary order by itinerary_id";
	/***************************自訂方法**********************************/
	private static final String GET_ONE_DETAILED = 
			"SELECT * FROM itinerary where group_id = ?";
	
	@Override
	public String insert(ItineraryVO itineraryVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String nextitinerary_id="";
		
		try {
			con = ds.getConnection();
			
			String[] cols = {"itinerary_id"};
			pstmt = con.prepareStatement(ISDERT_STMT, cols);
			
			pstmt.setString(1, itineraryVO.getGroup_id());
			pstmt.setInt(2, itineraryVO.getTotal_day());
			pstmt.setString(3, itineraryVO.getTrip_break());
			pstmt.setString(4, itineraryVO.getTrip_schedule());
			pstmt.executeUpdate();
			
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()){			
				nextitinerary_id = rs.getString(1);
//					System.out.println("自增主鍵值 = " + nextitinerary_id);
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
		return nextitinerary_id;

	}
	
	
	@Override
	public void update(ItineraryVO itineraryVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			
			pstmt.setString(1, itineraryVO.getGroup_id());
			pstmt.setInt(2, itineraryVO.getTotal_day());
			pstmt.setString(3, itineraryVO.getItinerary_id());

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
	public void delete(String itinerary_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, itinerary_id);

			pstmt.executeUpdate();
			// Handle any SQL errors
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
	public ItineraryVO findByPrimaryKey(String itinerary_id) {
		ItineraryVO itineraryVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, itinerary_id );

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				itineraryVO = new ItineraryVO();
				itineraryVO.setItinerary_id(rs.getString("itinerary_id"));
				itineraryVO.setGroup_id(rs.getString("group_id"));
				itineraryVO.setTotal_day(rs.getInt("total_day"));
				
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
		return itineraryVO;
	}

	
	@Override
	public List<ItineraryVO> getAll() {
		List<ItineraryVO> list = new ArrayList<ItineraryVO>();
		ItineraryVO itineraryVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				// empVO 也稱為 Domain objects
				itineraryVO = new ItineraryVO();
				itineraryVO.setItinerary_id(rs.getString("itinerary_id"));
				itineraryVO.setGroup_id(rs.getString("group_id"));
				itineraryVO.setTotal_day(rs.getInt("total_day"));
				list.add(itineraryVO); // Store the row in the list
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
	/**************************自訂方法***********************************/
	@Override
	public ItineraryVO findByGroup(String group_id) {
		ItineraryVO itineraryVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_DETAILED);

			pstmt.setString(1, group_id );

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				itineraryVO = new ItineraryVO();
				itineraryVO.setItinerary_id(rs.getString("itinerary_id"));
				itineraryVO.setGroup_id(rs.getString("group_id"));
				itineraryVO.setTotal_day(rs.getInt("total_day"));
				itineraryVO.setTrip_break(rs.getString("trip_break"));
				itineraryVO.setTrip_schedule(rs.getString("trip_schedule"));
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
		return itineraryVO;
	}
	
	

//	@Override
//	public List<ItineraryVO> getAll(Map<String, String[]> map) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	public static void main(String[] args) {
		
		ItineraryDAO dao = new ItineraryDAO();
		
//		新增
//		ItineraryVO itineraryVO1 = new ItineraryVO();
//		itineraryVO1.setGroup_id("G000001");
//		itineraryVO1.setTotal_day(10);
//		dao.insert(itineraryVO1);

		
//		修改
//		ItineraryVO itineraryVO2 = new ItineraryVO();
//		itineraryVO2.setItinerary_id("I000002");
//		itineraryVO2.setGroup_id("G000001");
//		itineraryVO2.setTotal_day(9);
//		dao.update(itineraryVO2);
		
//		刪除
//		dao.delete("I000003");

//      查詢
//		ItineraryVO itineraryVO3 = dao.findByPrimaryKey("I000001");
//		System.out.print(itineraryVO3.getItinerary_id() + ",");
//		System.out.print(itineraryVO3.getGroup_id() + ",");
//		System.out.println(itineraryVO3.getTotal_day() + ",");
//		System.out.println("=============================");
	
//		查詢<List>
//		List<ItineraryVO> list = dao.getAll();
//		for (ItineraryVO itineraryVO4 : list) {
//			System.out.print(itineraryVO4.getItinerary_id()+ ",");
//			System.out.print(itineraryVO4.getGroup_id() + ",");
//			System.out.print(itineraryVO4.getTotal_day() + ",");
//			System.out.println();
//		}
	}


	
	
}