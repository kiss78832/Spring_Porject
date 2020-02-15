package com.info.model;

import java.sql.*;
import java.util.*;

public class InfoJDBCDAO implements InfoDAO_interface{
	
	String DRIVER = "oracle.jdbc.driver.OracleDriver";
	String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	String USER = "TEST1";
	String PASSWORD = "TEST1";
	
	private static final String INSERT =
			"INSERT INTO ROUTE_INFO(ROUTE_ID,ROUTE_NAME,OPEN_TIME,OPEN_STATUS)" + 
			"VALUES (?,?,?,?)";
	private static final String UPDATE = 
			"UPDATE ROUTE_INFO SET ROUTE_NAME=?, OPEN_TIME=?, OPEN_STATUS=? WHERE ROUTE_ID=?";
	private static final String DELETE = 
			"DELETE FROM ROUTE_INFO WHERE ROUTE_ID=?";
	private static final String GET_ONE_PSTMT = 
			"SELECT * FROM ROUTE_INFO WHERE ROUTE_ID=?";
	private static final String GET_ALL = "SELECT * FROM ROUTE_INFO";
	
	
	
	public static void main(String[] args) {
		InfoJDBCDAO dao = new InfoJDBCDAO();
		
		// 新增
//		InfoVO infoVO1 = new InfoVO();
//		infoVO1.setRoute_ID("A002");
//		infoVO1.setRoute_Name("靜漢麥當勞");
//		infoVO1.setOpen_Time(java.sql.Timestamp.valueOf("2019-08-30"));
//		infoVO1.setOpen_Status(1);
//		dao.insert(infoVO1);
		
		// 修改
//		InfoVO infoVO2 = new InfoVO();
//		infoVO2.setRoute_ID("A002");
//		infoVO2.setRoute_Name("柏楊麥當勞");
//		infoVO2.setOpen_Time(java.sql.Timestamp.valueOf("2019-08-30"));
//		infoVO2.setOpen_Status(1);
//		dao.update(infoVO2);
		
		// 刪除
//		dao.delete("A002");
		
		// 查詢
//		InfoVO infoVO3 = dao.findByPrimaryKey("A002");
//		System.out.print(infoVO3.getRoute_ID() + ",");
//		System.out.print(infoVO3.getRoute_Name() + ",");
//		System.out.print(infoVO3.getOpen_Time() + ",");
//		System.out.print(infoVO3.getOpen_Status());
//		System.out.println("---------------------");
		
		// 查詢全部
		List<InfoVO> list = dao.getAll();
		for (InfoVO info : list) {
			System.out.print(info.getRoute_ID() + ",");
			System.out.print(info.getRoute_Name() + ",");
			System.out.print(info.getOpen_Time() + ",");
			System.out.print(info.getOpen_Status());
			System.out.println();
		}

	}

	@Override
	public void insert(InfoVO infoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT);

			pstmt.setString(1, infoVO.getRoute_ID());
			pstmt.setString(2, infoVO.getRoute_Name());
			pstmt.setTimestamp(3, infoVO.getOpen_Time());
			pstmt.setInt(4, infoVO.getOpen_Status());


			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	public void update(InfoVO infoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE);


			pstmt.setString(1, infoVO.getRoute_Name());
			pstmt.setTimestamp(2, infoVO.getOpen_Time());
			pstmt.setInt(3, infoVO.getOpen_Status());
			pstmt.setString(4, infoVO.getRoute_ID());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	public void delete(String route_ID) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, route_ID);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	public InfoVO findByPrimaryKey(String route_ID) {
		InfoVO infoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_PSTMT);
			pstmt.setString(1, route_ID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// empVo 也稱為 Domain objects
				infoVO = new InfoVO();
				infoVO.setRoute_ID(route_ID);
				infoVO.setRoute_Name(rs.getString("ROUTE_NAME"));
				infoVO.setOpen_Time(rs.getTimestamp("OPEN_TIME"));
				infoVO.setOpen_Status(rs.getInt("OPEN_STATUS"));
			}
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
		return infoVO;
	}

	@Override
	public List<InfoVO> getAll() {
		List<InfoVO> infoVOList = new ArrayList<>();
		InfoVO infoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				infoVO = new InfoVO();
				infoVO.setRoute_ID(rs.getString("ROUTE_ID"));
				infoVO.setRoute_Name(rs.getString("ROUTE_NAME"));
				infoVO.setOpen_Time(rs.getTimestamp("OPEN_TIME"));
				infoVO.setOpen_Status(rs.getInt("OPEN_STATUS"));
				infoVOList.add(infoVO);
			}

		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("Couldn't load database driver. " + ce.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return infoVOList;
	}
	

//	@Override
//	public List<InfoVO> getAll(Map<String, String[]> map) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
