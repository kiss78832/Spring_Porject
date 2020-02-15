package com.info.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class InfoJNDI implements InfoDAO_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB2");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
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
	
	
	
	

	@Override
	public void insert(InfoVO infoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT);

			pstmt.setString(1, infoVO.getRoute_ID());
			pstmt.setString(2, infoVO.getRoute_Name());
			pstmt.setTimestamp(3, infoVO.getOpen_Time());
			pstmt.setInt(4, infoVO.getOpen_Status());


			pstmt.executeUpdate();

			// Handle any driver errors
		}catch (SQLException se) {
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);


			pstmt.setString(1, infoVO.getRoute_Name());
			pstmt.setTimestamp(2, infoVO.getOpen_Time());
			pstmt.setInt(3, infoVO.getOpen_Status());
			pstmt.setString(4, infoVO.getRoute_ID());

			pstmt.executeUpdate();

			// Handle any driver errors
		}catch (SQLException se) {
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, route_ID);

			pstmt.executeUpdate();

			// Handle any driver errors
		}catch (SQLException se) {
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
			con = ds.getConnection();
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
		}catch (SQLException se) {
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

			con = ds.getConnection();
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

		}catch (SQLException se) {
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
