package com.spring.viewpoint.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ViewpointDAO implements Viewpoint_interface {
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
			"INSERT INTO viewpoint (itinerary_id, spot_id, day) VALUES (?, ?, ?)";
	private static final String UPDATE = 
			"UPDATE viewpoint set day=? where itinerary_id=? and spot_id=?";
	private static final String DELETE = 
			"DELETE FROM viewpoint where itinerary_id=? and spot_id=?";
	private static final String GET_ONE_STMT = 
			"SELECT * FROM viewpoint where itinerary_id=? and spot_id=?";
	private static final String GET_ALL_STMT = 
			"SELECT itinerary_id, spot_id, day FROM viewpoint order by itinerary_id";
	
	
	@Override
	public void insert(ViewpointVO viewpointVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(ISDERT_STMT);
			pstmt.setString(1, viewpointVO.getItinerary_id());
			pstmt.setString(2, viewpointVO.getSpot_id());
			pstmt.setInt(3, viewpointVO.getDay());
			
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
	public void update(ViewpointVO viewpointVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, viewpointVO.getDay());
			pstmt.setString(2, viewpointVO.getItinerary_id());
			pstmt.setString(3, viewpointVO.getSpot_id());
			
			
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
	public void delete(String itinerary_id, String spot_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1,itinerary_id );
			pstmt.setString(2,spot_id );

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
	public ViewpointVO findByPrimaryKey(String itinerary_id, String spot_id) {
		ViewpointVO viewpointVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, itinerary_id);
			pstmt.setString(2, spot_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				viewpointVO = new ViewpointVO();
				viewpointVO.setItinerary_id(rs.getString("itinerary_id"));
				viewpointVO.setSpot_id(rs.getString("spot_id"));
				viewpointVO.setDay(rs.getInt("day"));
				
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
		return viewpointVO;
	}

	@Override
	public List<ViewpointVO> getAll() {
		List<ViewpointVO> list = new ArrayList<ViewpointVO>();
		ViewpointVO viewpointVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				viewpointVO = new ViewpointVO();
				viewpointVO.setItinerary_id(rs.getString("itinerary_id"));
				viewpointVO.setSpot_id(rs.getString("spot_id"));
				viewpointVO.setDay(rs.getInt("day"));
				list.add(viewpointVO); 
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
	
	public static void main(String[]args) {
		ViewpointDAO dao = new ViewpointDAO();
		
//		新增
		ViewpointVO viewpointVO1 = new ViewpointVO();
		viewpointVO1.setItinerary_id("I000003");
		viewpointVO1.setSpot_id("SPOT003");
		viewpointVO1.setDay(2);
		dao.insert(viewpointVO1);
//		
//		修改
//		ViewpointVO viewpointVO2 = new ViewpointVO();
//		viewpointVO2.setDay(4);
//		viewpointVO2.setItinerary_id("I000006");
//		viewpointVO2.setSpot_id("SPOT003");
//		dao.update(viewpointVO2);
		
//		刪除
//		dao.delete("I000001", "SPOT002");
		
//		查詢
		ViewpointVO viewpointVO3 = dao.findByPrimaryKey("I000001", "S001");
		System.out.print(viewpointVO3.getItinerary_id() +",");
		System.out.print(viewpointVO3.getSpot_id() + ",");
		System.out.println(viewpointVO3.getDay() +",");
		System.out.println("==========================================");
		
//		List查詢
		List<ViewpointVO> list = dao.getAll();
		for (ViewpointVO viewpointVO4 : list) {
			System.out.print(viewpointVO4.getItinerary_id() + ",");
			System.out.print(viewpointVO4.getSpot_id() + ",");
			System.out.println(viewpointVO4.getDay() + ",");
			System.out.println();
		}

	}
}