package com.spring.dailytypetotal.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DailyTotalJDBCDAO implements DailyTotalDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TEST1";
	String passwd = "TEST1";

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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, dailytotalVO.getType_eq_num());
			pstmt.setDate(2, dailytotalVO.getEq_date());
			pstmt.setInt(3, dailytotalVO.getDaily_eq_qty());
			pstmt.setInt(4, dailytotalVO.getStart_qty());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, dailytotalVO.getType_eq_num());
			pstmt.setDate(2, dailytotalVO.getEq_date());
			pstmt.setInt(3, dailytotalVO.getDaily_eq_qty());
			pstmt.setInt(4, dailytotalVO.getStart_qty());
			pstmt.setString(5, dailytotalVO.getDailyeq_num());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public void delete(String dailyeq_num) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, dailyeq_num);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public DailyTotalVO findByPrimaryKey(String dailyeq_num) {

		DailyTotalVO dailytotalVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
				list.add(dailytotalVO); // Store the row in the list

			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
		return list;
	}
	
	@Override
	public List<DailyTotalVO> getDate(String type_eq_num ,java.sql.Date startdate,java.sql.Date enddate) {
		List<DailyTotalVO> list = new ArrayList<DailyTotalVO>();
		DailyTotalVO dailytotalVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
				list.add(dailytotalVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
		return list;
	}
	
	public List<DailyTotalVO> getOneEqDate(String type_eq_num) {
		List<DailyTotalVO> list = new ArrayList<DailyTotalVO>();
		DailyTotalVO dailytotalVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
				list.add(dailytotalVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
		return list;
	}
	
	public List<DailyTotalVO> getMoreDate(String type_eq_num ,java.sql.Date ex_return_date) {
		List<DailyTotalVO> list = new ArrayList<DailyTotalVO>();
		DailyTotalVO dailytotalVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
				list.add(dailytotalVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

	public static void main(String[] args) {

		DailyTotalJDBCDAO dao = new DailyTotalJDBCDAO();

//		// 新增
//		DailyTotalVO dailytotalVO1 = new DailyTotalVO();
//		dailytotalVO1.setType_eq_num("ET00000001");
//		dailytotalVO1.setEq_date(java.sql.Date.valueOf("2019-08-15"));
//		dailytotalVO1.setDaily_eq_qty(2);
//		dailytotalVO1.setStart_qty(2);
//		dao.insert(dailytotalVO1);
//
//		// 修改
//		DailyTotalVO dailytotalVO2 = new DailyTotalVO();
//		dailytotalVO2.setDailyeq_num("DT00000001");
//		dailytotalVO2.setType_eq_num("ET00000001");
//		dailytotalVO2.setEq_date(java.sql.Date.valueOf("2019-09-15"));
//		dailytotalVO2.setDaily_eq_qty(3);
//		dailytotalVO2.setStart_qty(3);
//		dao.update(dailytotalVO2);
//
//		// 刪除
//		dao.delete("DT00000002");
//
//		// 查詢
//		DailyTotalVO dailytotalVO3 = dao.findByPrimaryKey("DT00000001");
//		System.out.print(dailytotalVO3.getDailyeq_num() + ",");
//		System.out.print(dailytotalVO3.getType_eq_num() + ",");
//		System.out.print(dailytotalVO3.getEq_date() + ",");
//		System.out.print(dailytotalVO3.getDaily_eq_qty() + ",");
//		System.out.print(dailytotalVO3.getStart_qty() + ",");
//		System.out.println("---------------------");

//		// 查詢
//		List<DailyTotalVO> list = dao.getAll();
//		for (DailyTotalVO aEqu : list) {
//			System.out.print(aEqu.getDailyeq_num() + ",");
//			System.out.print(aEqu.getType_eq_num() + ",");
//			System.out.print(aEqu.getEq_date() + ",");
//			System.out.print(aEqu.getDaily_eq_qty() + ",");
//			System.out.print(aEqu.getStart_qty() + ",");
//			System.out.println();
		
//		// 查詢
//		List<DailyTotalVO> list = dao.getOneEqDate("ET00000001");
//		for (DailyTotalVO aEqu : list) {
//			System.out.print(aEqu.getDailyeq_num() + ",");
//			System.out.print(aEqu.getType_eq_num() + ",");
//			System.out.print(aEqu.getEq_date() + ",");
//			System.out.print(aEqu.getDaily_eq_qty() + ",");
//			System.out.print(aEqu.getStart_qty() + ",");
//			System.out.println();
//		}
		
		
		// 查詢
		List<DailyTotalVO> list = dao.getMoreDate("ET00000001",java.sql.Date.valueOf("2019-09-10") );
		for (DailyTotalVO aEqu : list) {
			System.out.print(aEqu.getDailyeq_num() + ",");
			System.out.print(aEqu.getType_eq_num() + ",");
			System.out.print(aEqu.getEq_date() + ",");
			System.out.print(aEqu.getDaily_eq_qty() + ",");
			System.out.print(aEqu.getStart_qty() + ",");
			System.out.println();
		}
		
		
		
//		List<DailyTotalVO> list = dao.getDate("ET00000001",java.sql.Date.valueOf("2005-07-01"),java.sql.Date.valueOf("2021-09-10"));
//		for (DailyTotalVO aEqu : list) {
//			System.out.print(aEqu.getDailyeq_num() + ",");
//			System.out.print(aEqu.getType_eq_num() + ",");
//			System.out.print(aEqu.getEq_date() + ",");
//			System.out.print(aEqu.getDaily_eq_qty() + ",");
//			System.out.print(aEqu.getStart_qty() + ",");
//			System.out.println();
//		}
	}
}
