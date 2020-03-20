package com.spring.equipment.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class EquipmentDAO implements EquipmentDAO_interface {

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

	private static final String INSERT_STMT = "INSERT INTO EQUIPMENT (EQ_NUM, EQ_NAME, EQ_TYPE, EQ_SIZE, EQ_BRAND,EQ_PRICE,EQ_STATUS,EQ_PIC,EQ_DETAIL,TYPE_EQ_NUM) VALUES ('EQ'||LPAD(to_char(SEQ_EQ.NEXTVAL), 8, '0'), ?,?, ?, ?, ?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT EQ_NUM, EQ_NAME, EQ_TYPE, EQ_SIZE, EQ_BRAND,EQ_PRICE,EQ_STATUS,EQ_PIC,EQ_DETAIL,TYPE_EQ_NUM FROM EQUIPMENT order by EQ_NUM";
	private static final String GET_ONE_STMT = "SELECT EQ_NUM, EQ_NAME, EQ_TYPE, EQ_SIZE, EQ_BRAND,EQ_PRICE,EQ_STATUS,EQ_PIC,EQ_DETAIL,TYPE_EQ_NUM FROM EQUIPMENT where EQ_NUM = ?";
	private static final String GET_ONE_TYPEEQNUM = "SELECT EQ_NUM, EQ_NAME, EQ_TYPE, EQ_SIZE, EQ_BRAND,EQ_PRICE,EQ_STATUS,EQ_PIC,EQ_DETAIL,TYPE_EQ_NUM FROM EQUIPMENT where TYPE_EQ_NUM = ?";
	private static final String GET_ONE_TYPE = "SELECT EQ_NUM, EQ_NAME, EQ_TYPE, EQ_SIZE, EQ_BRAND,EQ_PRICE,EQ_STATUS,EQ_PIC,EQ_DETAIL,TYPE_EQ_NUM FROM EQUIPMENT where EQ_TYPE = ?";
	private static final String DELETE = "DELETE FROM EQUIPMENT where EQ_NUM = ?";
	private static final String GET_ONE_SIZE = "SELECT EQ_NUM, EQ_NAME, EQ_TYPE, EQ_SIZE, EQ_BRAND,EQ_PRICE,EQ_STATUS,EQ_PIC,EQ_DETAIL,TYPE_EQ_NUM FROM EQUIPMENT where EQ_NAME = ?";
	private static final String GET_ONE_EQTYPE = "SELECT EQ_NUM, EQ_NAME, EQ_TYPE, EQ_SIZE, EQ_BRAND,EQ_PRICE,EQ_STATUS,EQ_PIC,EQ_DETAIL,TYPE_EQ_NUM FROM EQUIPMENT where EQ_NAME = ? AND EQ_SIZE=?";
	private static final String UPDATE = "UPDATE EQUIPMENT set EQ_NAME=?,  EQ_TYPE=?, EQ_SIZE=?, EQ_BRAND=?, EQ_PRICE=?, EQ_STATUS=? ,EQ_PIC=?,EQ_DETAIL=?,TYPE_EQ_NUM=? where EQ_NUM = ?";
	@Override
	public void insert(EquipmentVO equVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, equVO.getEq_name());
			pstmt.setString(2, equVO.getEq_type());
			pstmt.setString(3, equVO.getEq_size());
			pstmt.setString(4, equVO.getEq_brand());
			pstmt.setInt(5, equVO.getEq_price());
			pstmt.setInt(6, equVO.getEq_status());
			pstmt.setBytes(7, equVO.getEq_pic());
			pstmt.setString(8, equVO.getEq_detail());
			pstmt.setString(9, equVO.getType_eq_num());

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
	public void update(EquipmentVO equVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, equVO.getEq_name());
			pstmt.setString(2, equVO.getEq_type());
			pstmt.setString(3, equVO.getEq_size());
			pstmt.setString(4, equVO.getEq_brand());
			pstmt.setInt(5, equVO.getEq_price());
			pstmt.setInt(6, equVO.getEq_status());
			pstmt.setBytes(7, equVO.getEq_pic());
			pstmt.setString(8, equVO.getEq_detail());
			pstmt.setString(9, equVO.getType_eq_num());
			pstmt.setString(10, equVO.getEq_num());

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
	public void delete(String eq_num) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, eq_num);

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
	public EquipmentVO findByPrimaryKey(String eq_num) {

		EquipmentVO equVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, eq_num);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				equVO = new EquipmentVO();
				equVO.setEq_num(rs.getString("eq_num"));
				equVO.setEq_name(rs.getString("eq_name"));
				equVO.setEq_type(rs.getString("eq_type"));
				equVO.setEq_size(rs.getString("eq_size"));
				equVO.setEq_brand(rs.getString("eq_brand"));
				equVO.setEq_price(rs.getInt("eq_price"));
				equVO.setEq_status(rs.getInt("eq_status"));
				equVO.setEq_pic(rs.getBytes("eq_pic"));
				equVO.setEq_detail(rs.getString("eq_detail"));
				equVO.setType_eq_num(rs.getString("type_eq_num"));
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
		return equVO;
	}
	
	@Override
	public EquipmentVO findByTypeEqNum(String type_eq_num) {

		EquipmentVO equVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_TYPEEQNUM);

			pstmt.setString(1, type_eq_num);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				equVO = new EquipmentVO();
				equVO.setEq_num(rs.getString("eq_num"));
				equVO.setEq_name(rs.getString("eq_name"));
				equVO.setEq_type(rs.getString("eq_type"));
				equVO.setEq_size(rs.getString("eq_size"));
				equVO.setEq_brand(rs.getString("eq_brand"));
				equVO.setEq_price(rs.getInt("eq_price"));
				equVO.setEq_status(rs.getInt("eq_status"));
				equVO.setEq_pic(rs.getBytes("eq_pic"));
				equVO.setEq_detail(rs.getString("eq_detail"));
				equVO.setType_eq_num(rs.getString("type_eq_num"));
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
		return equVO;
	}
	
	@Override
	public List<EquipmentVO> findByType(String eq_type) {
		List<EquipmentVO> list = new ArrayList<EquipmentVO>();
		EquipmentVO equVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_TYPE);
			pstmt.setString(1, eq_type);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				equVO = new EquipmentVO();
				equVO.setEq_num(rs.getString("eq_num"));
				equVO.setEq_name(rs.getString("eq_name"));
				equVO.setEq_type(rs.getString("eq_type"));
				equVO.setEq_size(rs.getString("eq_size"));
				equVO.setEq_brand(rs.getString("eq_brand"));
				equVO.setEq_price(rs.getInt("eq_price"));
				equVO.setEq_status(rs.getInt("eq_status"));
				equVO.setEq_pic(rs.getBytes("eq_pic"));
				equVO.setEq_detail(rs.getString("eq_detail"));
				equVO.setType_eq_num(rs.getString("type_eq_num"));
				list.add(equVO); // Store the row in the list
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
	public List<EquipmentVO> findByAllTypeEqNum(String type_eq_num) {
		List<EquipmentVO> list = new ArrayList<EquipmentVO>();
		EquipmentVO equVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_TYPEEQNUM);
			pstmt.setString(1, type_eq_num);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				equVO = new EquipmentVO();
				equVO.setEq_num(rs.getString("eq_num"));
				equVO.setEq_name(rs.getString("eq_name"));
				equVO.setEq_type(rs.getString("eq_type"));
				equVO.setEq_size(rs.getString("eq_size"));
				equVO.setEq_brand(rs.getString("eq_brand"));
				equVO.setEq_price(rs.getInt("eq_price"));
				equVO.setEq_status(rs.getInt("eq_status"));
				equVO.setEq_pic(rs.getBytes("eq_pic"));
				equVO.setEq_detail(rs.getString("eq_detail"));
				equVO.setType_eq_num(rs.getString("type_eq_num"));
				list.add(equVO); // Store the row in the list
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
	public EquipmentVO findByNameAndSize(String eq_name ,String eq_size){

		EquipmentVO equVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_EQTYPE);

			pstmt.setString(1, eq_name);
			pstmt.setString(2, eq_size);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				equVO = new EquipmentVO();
				equVO.setEq_num(rs.getString("eq_num"));
				equVO.setEq_name(rs.getString("eq_name"));
				equVO.setEq_type(rs.getString("eq_type"));
				equVO.setEq_size(rs.getString("eq_size"));
				equVO.setEq_brand(rs.getString("eq_brand"));
				equVO.setEq_price(rs.getInt("eq_price"));
				equVO.setEq_status(rs.getInt("eq_status"));
				equVO.setEq_pic(rs.getBytes("eq_pic"));
				equVO.setEq_detail(rs.getString("eq_detail"));
				equVO.setType_eq_num(rs.getString("type_eq_num"));
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
		return equVO;
	}
	
	@Override
	public List<EquipmentVO> findBySize(String eq_name) {
		List<EquipmentVO> list = new ArrayList<EquipmentVO>();
		EquipmentVO equVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_SIZE);
			pstmt.setString(1, eq_name);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				equVO = new EquipmentVO();
				equVO.setEq_num(rs.getString("eq_num"));
				equVO.setEq_name(rs.getString("eq_name"));
				equVO.setEq_type(rs.getString("eq_type"));
				equVO.setEq_size(rs.getString("eq_size"));
				equVO.setEq_brand(rs.getString("eq_brand"));
				equVO.setEq_price(rs.getInt("eq_price"));
				equVO.setEq_status(rs.getInt("eq_status"));
				equVO.setEq_pic(rs.getBytes("eq_pic"));
				equVO.setEq_detail(rs.getString("eq_detail"));
				equVO.setType_eq_num(rs.getString("type_eq_num"));
				list.add(equVO); // Store the row in the list
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
	public List<EquipmentVO> getAll() {
		List<EquipmentVO> list = new ArrayList<EquipmentVO>();
		EquipmentVO equVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				equVO = new EquipmentVO();
				equVO.setEq_num(rs.getString("eq_num"));
				equVO.setEq_name(rs.getString("eq_name"));
				equVO.setEq_type(rs.getString("eq_type"));
				equVO.setEq_size(rs.getString("eq_size"));
				equVO.setEq_brand(rs.getString("eq_brand"));
				equVO.setEq_price(rs.getInt("eq_price"));
				equVO.setEq_status(rs.getInt("eq_status"));
				equVO.setEq_pic(rs.getBytes("eq_pic"));
				equVO.setEq_detail(rs.getString("eq_detail"));
				equVO.setType_eq_num(rs.getString("type_eq_num"));
				list.add(equVO); // Store the row in the list
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
}