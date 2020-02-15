package com.rentoddetail.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.rentoddetail.model.RentOdDetailVO;
import com.rentodlist.model.RentOdListDAO;
import com.rentodlist.model.RentOdListVO;

public class RentOdDetailDAO implements RentOdDetailDAO_interface {

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

	private static final String INSERT_STMT = "INSERT INTO RENT_ODLIST_DETAIL (RENT_ODNUM, EQ_NUM, QUANTITY) VALUES (?,?,?)";
	private static final String GET_ALL_STMT = "SELECT RENT_ODNUM, EQ_NUM, QUANTITY FROM RENT_ODLIST_DETAIL order by RENT_ODNUM";
	private static final String GET_ONE_STMT = "SELECT RENT_ODNUM, EQ_NUM, QUANTITY FROM RENT_ODLIST_DETAIL where RENT_ODNUM = ? AND EQ_NUM=?";
	private static final String GET_DETAIL_STMT = "SELECT RENT_ODNUM, EQ_NUM, QUANTITY FROM RENT_ODLIST_DETAIL where RENT_ODNUM = ? ";
	private static final String DELETE = "DELETE FROM RENT_ODLIST_DETAIL where RENT_ODNUM = ? AND EQ_NUM=?";

	private static final String UPDATE = "UPDATE RENT_ODLIST_DETAIL set QUANTITY=? where RENT_ODNUM=? and EQ_NUM=?";

	@Override
	public void insert(RentOdDetailVO rentoddetailVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, rentoddetailVO.getRent_odnum());
			pstmt.setString(2, rentoddetailVO.getEq_num());
			pstmt.setInt(3, rentoddetailVO.getQuantity());

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
	public void update(RentOdDetailVO rentoddetailVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(2, rentoddetailVO.getRent_odnum());
			pstmt.setString(3, rentoddetailVO.getEq_num());
			pstmt.setInt(1, rentoddetailVO.getQuantity());

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
	public void delete(String rent_odnum, String eq_num) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, rent_odnum);
			pstmt.setString(2, eq_num);

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
	public RentOdDetailVO findByPrimaryKey(String rent_odnum, String eq_num) {

		RentOdDetailVO rentoddetailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, rent_odnum);
			pstmt.setString(2, eq_num);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				rentoddetailVO = new RentOdDetailVO();
				rentoddetailVO.setRent_odnum(rs.getString("rent_odnum"));
				rentoddetailVO.setEq_num(rs.getString("eq_num"));
				rentoddetailVO.setQuantity(rs.getInt("quantity"));

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
		return rentoddetailVO;
	}
	
	@Override
	public List<RentOdDetailVO> findByDetail(String rent_odnum) {
		List<RentOdDetailVO> list = new ArrayList<RentOdDetailVO>();
		RentOdDetailVO rentoddetailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_DETAIL_STMT);
			pstmt.setString(1, rent_odnum);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				rentoddetailVO = new RentOdDetailVO();
				rentoddetailVO.setRent_odnum(rs.getString("rent_odnum"));
				rentoddetailVO.setEq_num(rs.getString("eq_num"));
				rentoddetailVO.setQuantity(rs.getInt("quantity"));
				list.add(rentoddetailVO); // Store the row in the list
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
	public List<RentOdDetailVO> getAll() {
		List<RentOdDetailVO> list = new ArrayList<RentOdDetailVO>();
		RentOdDetailVO rentoddetailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				rentoddetailVO = new RentOdDetailVO();
				rentoddetailVO.setRent_odnum(rs.getString("rent_odnum"));
				rentoddetailVO.setEq_num(rs.getString("eq_num"));
				rentoddetailVO.setQuantity(rs.getInt("quantity"));
				list.add(rentoddetailVO); // Store the row in the list
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
	public void insert2 (RentOdDetailVO rentoddetailVO , Connection con) {

		PreparedStatement pstmt = null;

		try {

			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, rentoddetailVO.getRent_odnum());
			pstmt.setString(2, rentoddetailVO.getEq_num());
			pstmt.setInt(3, rentoddetailVO.getQuantity());

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
	public void update2 (RentOdDetailVO rentoddetailVO , Connection con) {

		PreparedStatement pstmt = null;

		try {

			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(2, rentoddetailVO.getRent_odnum());
			pstmt.setString(3, rentoddetailVO.getEq_num());
			pstmt.setInt(1, rentoddetailVO.getQuantity());
			System.out.println(rentoddetailVO.getQuantity());
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