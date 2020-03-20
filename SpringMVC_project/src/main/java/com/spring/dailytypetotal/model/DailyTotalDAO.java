package com.spring.dailytypetotal.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DailyTotalDAO implements DailyTotalDAO_interface {

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

	private static final String INSERT_STMT = "INSERT INTO DAILYTYPE_TOTAL (DAILYEQ_NUM, TYPE_EQ_NUM, EQ_DATE, DAILY_EQ_QTY,START_QTY) VALUES ('DT'||LPAD(to_char(SQU_DAILYTYPE_TOTAL.NEXTVAL), 8, '0'), ?,?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT DAILYEQ_NUM, TYPE_EQ_NUM, EQ_DATE, DAILY_EQ_QTY,START_QTY FROM DAILYTYPE_TOTAL order by DAILYEQ_NUM";
	private static final String GET_ONE_STMT = "SELECT DAILYEQ_NUM, TYPE_EQ_NUM, EQ_DATE, DAILY_EQ_QTY,START_QTY FROM DAILYTYPE_TOTAL where DAILYEQ_NUM = ?";
	private static final String DELETE = "DELETE FROM DAILYTYPE_TOTAL  where DAILYEQ_NUM = ?";
	private static final String UPDATE = "UPDATE DAILYTYPE_TOTAL set TYPE_EQ_NUM=?,  EQ_DATE=? ,DAILY_EQ_QTY=?,START_QTY=? where DAILYEQ_NUM = ?";
	private static final String GET_DATE = "select * from DAILYTYPE_TOTAL where TYPE_EQ_NUM =?  AND EQ_DATE between ? and ?";
	private static final String GET_ONE_EQDATE = "SELECT DAILYEQ_NUM, TYPE_EQ_NUM, EQ_DATE, DAILY_EQ_QTY,START_QTY FROM DAILYTYPE_TOTAL where TYPE_EQ_NUM = ?";
	private static final String GET_MOREEQDATE = "SELECT * FROM DAILYTYPE_TOTAL WHERE TYPE_EQ_NUM =? AND EQ_DATE >  ?";
	
	
	@Override
	public void insert(DailyTotalVO dailytotalVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, dailytotalVO.getType_eq_num());
			pstmt.setDate(2, dailytotalVO.getEq_date());
			pstmt.setInt(3, dailytotalVO.getDaily_eq_qty());
			pstmt.setInt(4, dailytotalVO.getStart_qty());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void update(DailyTotalVO dailytotalVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			con.setAutoCommit(false);
			
			
			pstmt.setString(1, dailytotalVO.getType_eq_num());
			pstmt.setDate(2, dailytotalVO.getEq_date());
			pstmt.setInt(3, dailytotalVO.getDaily_eq_qty());
			pstmt.setInt(4, dailytotalVO.getStart_qty());
			pstmt.setString(5, dailytotalVO.getDailyeq_num());
			
			pstmt.executeUpdate();
			con.commit();
			// Handle any driver errors
		} catch (SQLException se) {
			try {
				con.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			throw new RuntimeException("A database error occured. " + se.getMessage());
			
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
	public void delete(String dailyeq_num) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, dailyeq_num);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public DailyTotalVO findByPrimaryKey(String dailyeq_num) {

		DailyTotalVO dailytotalVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, dailyeq_num);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				dailytotalVO = new DailyTotalVO();
				dailytotalVO.setDailyeq_num(rs.getString("dailyeq_num"));
				dailytotalVO.setType_eq_num(rs.getString("type_eq_num"));
				dailytotalVO.setEq_date(rs.getDate("eq_date"));
				dailytotalVO.setDaily_eq_qty(rs.getInt("daily_eq_qty"));
				dailytotalVO.setStart_qty(rs.getInt("start_qty"));
			}

			// Handle any driver errors
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
		return dailytotalVO;
	}
	
	

	@Override
	public List<DailyTotalVO> getAll() {
		List<DailyTotalVO> list = new ArrayList<DailyTotalVO>();
		DailyTotalVO dailytotalVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				dailytotalVO = new DailyTotalVO();
				dailytotalVO.setDailyeq_num(rs.getString("dailyeq_num"));
				dailytotalVO.setType_eq_num(rs.getString("type_eq_num"));
				dailytotalVO.setEq_date(rs.getDate("eq_date"));
				dailytotalVO.setDaily_eq_qty(rs.getInt("daily_eq_qty"));
				dailytotalVO.setStart_qty(rs.getInt("start_qty"));
				list.add(dailytotalVO);// Store the row in the list
			}

			// Handle any driver errors
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
		return list;
	}
	
	public List<DailyTotalVO> getDate(String type_eq_num ,Date startdate,Date enddate) {
		List<DailyTotalVO> list = new ArrayList<DailyTotalVO>();
		DailyTotalVO dailytotalVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_DATE);
			
			
			pstmt.setString(1, type_eq_num);
			pstmt.setDate(2,new java.sql.Date(startdate.getTime()) );
			pstmt.setDate(3,new java.sql.Date(enddate.getTime()) );
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				dailytotalVO = new DailyTotalVO();
				dailytotalVO.setDailyeq_num(rs.getString("dailyeq_num"));
				dailytotalVO.setType_eq_num(rs.getString("type_eq_num"));
				dailytotalVO.setEq_date(rs.getDate("eq_date"));
				dailytotalVO.setDaily_eq_qty(rs.getInt("daily_eq_qty"));
				dailytotalVO.setStart_qty(rs.getInt("start_qty"));
				list.add(dailytotalVO);// Store the row in the list
			}

			// Handle any driver errors
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
		return list;
	}
	
	public List<DailyTotalVO> getMoreDate(String type_eq_num ,java.sql.Date ex_return_date) {
		List<DailyTotalVO> list = new ArrayList<DailyTotalVO>();
		DailyTotalVO dailytotalVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_MOREEQDATE);
			
			
			pstmt.setString(1, type_eq_num);
			pstmt.setDate(2,new java.sql.Date(ex_return_date.getTime()) );

			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				dailytotalVO = new DailyTotalVO();
				dailytotalVO.setDailyeq_num(rs.getString("dailyeq_num"));
				dailytotalVO.setType_eq_num(rs.getString("type_eq_num"));
				dailytotalVO.setEq_date(rs.getDate("eq_date"));
				dailytotalVO.setDaily_eq_qty(rs.getInt("daily_eq_qty"));
				dailytotalVO.setStart_qty(rs.getInt("start_qty"));
				list.add(dailytotalVO);// Store the row in the list
			}

			// Handle any driver errors
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
		return list;
	}
	
	public List<DailyTotalVO> getOneEqDate(String type_eq_num){
		List<DailyTotalVO> list = new ArrayList<DailyTotalVO>();
		DailyTotalVO dailytotalVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_EQDATE);
			
			
			pstmt.setString(1, type_eq_num);

			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				dailytotalVO = new DailyTotalVO();
				dailytotalVO.setDailyeq_num(rs.getString("dailyeq_num"));
				dailytotalVO.setType_eq_num(rs.getString("type_eq_num"));
				dailytotalVO.setEq_date(rs.getDate("eq_date"));
				dailytotalVO.setDaily_eq_qty(rs.getInt("daily_eq_qty"));
				dailytotalVO.setStart_qty(rs.getInt("start_qty"));
				list.add(dailytotalVO);// Store the row in the list
			}

			// Handle any driver errors
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
		return list;
	}
	
	
	@Override
	public void insert2 (DailyTotalVO dailytotalVO , Connection con) {

		PreparedStatement pstmt = null;

		try {

			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, dailytotalVO.getType_eq_num());
			pstmt.setDate(2, dailytotalVO.getEq_date());
			pstmt.setInt(3, dailytotalVO.getDaily_eq_qty());
			pstmt.setInt(4, dailytotalVO.getStart_qty());
			pstmt.setString(5, dailytotalVO.getDailyeq_num());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-emp");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
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
		}

	}
	
	@Override
	public void update2 (DailyTotalVO dailytotalVO , Connection con) {

		PreparedStatement pstmt = null;

		try {

			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, dailytotalVO.getType_eq_num());
			pstmt.setDate(2, dailytotalVO.getEq_date());
			pstmt.setInt(3, dailytotalVO.getDaily_eq_qty());
			pstmt.setInt(4, dailytotalVO.getStart_qty());
			pstmt.setString(5, dailytotalVO.getDailyeq_num());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-emp");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
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
		}

	}
}