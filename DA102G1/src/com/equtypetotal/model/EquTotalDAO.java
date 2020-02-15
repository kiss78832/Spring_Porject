package com.equtypetotal.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.equipment.model.EquipmentVO;
import com.equipment.model.EquipmentVO;

public class EquTotalDAO implements EquTotalDAO_interface {

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

	private static final String INSERT_STMT = "INSERT INTO EQ_TYPE_TOTAL (TYPE_EQ_NUM, TYPE_EQ_NAME,TYPE_EQ_QTY) VALUES ('ET'||LPAD(to_char(SEQ_TYPE_TOTAL.NEXTVAL), 8, '0'),?,?)";
	private static final String GET_ALL_STMT = "SELECT TYPE_EQ_NUM, TYPE_EQ_NAME,TYPE_EQ_QTY FROM EQ_TYPE_TOTAL order by TYPE_EQ_NUM";
	private static final String GET_ONE_STMT = "SELECT TYPE_EQ_NUM, TYPE_EQ_NAME, TYPE_EQ_QTY FROM EQ_TYPE_TOTAL where TYPE_EQ_NUM = ?";
	private static final String GET_Equs_ByTypeTotal_STMT = "SELECT SELECT EQ_NUM, EQ_NAME, EQ_TYPE, EQ_SIZE, EQ_BRAND,EQ_PRICE,EQ_STATUS,EQ_PIC,EQ_DETAIL,TYPE_EQ_NUM FROM EQUIPMENT where TYPE_EQ_NUM  = ? order by EQ_NUM";
	private static final String DELETE_Equs = "DELETE FROM EQUIPMENT where TYPE_EQ_NUM = ?";
	private static final String DELETE_Typetotal = "DELETE FROM EQ_TYPE_TOTAL where TYPE_EQ_NUM = ?";
	private static final String UPDATE = "UPDATE EQ_TYPE_TOTAL set  TYPE_EQ_NAME, TYPE_EQ_QTY=? where TYPE_EQ_NUM = ?";

	@Override
	public void insert(EquTotalVO equtotalVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, equtotalVO.getType_eq_name());
			pstmt.setInt(2, equtotalVO.getType_eq_qty());

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
	public void update(EquTotalVO equtotalVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, equtotalVO.getType_eq_name());
			pstmt.setInt(2, equtotalVO.getType_eq_qty());
			pstmt.setString(3, equtotalVO.getType_eq_num());

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
	public void delete(String type_eq_num) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_Equs);

			pstmt.setString(1, type_eq_num);

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
	public EquTotalVO findByPrimaryKey(String type_eq_num) {

		EquTotalVO equtotalVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, type_eq_num);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				equtotalVO = new EquTotalVO();
				equtotalVO.setType_eq_num(rs.getString("type_eq_num"));
				equtotalVO.setType_eq_name(rs.getString("type_eq_name"));
				equtotalVO.setType_eq_qty(rs.getInt("type_eq_qty"));
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
		return equtotalVO;
	}

	@Override
	public List<EquTotalVO> getAll() {
		List<EquTotalVO> list = new ArrayList<EquTotalVO>();
		EquTotalVO equtotalVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				equtotalVO = new EquTotalVO();
				equtotalVO.setType_eq_num(rs.getString("type_eq_num"));
				equtotalVO.setType_eq_name(rs.getString("type_eq_name"));
				equtotalVO.setType_eq_qty(rs.getInt("type_eq_qty"));
				list.add(equtotalVO); // Store the row in the list
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
	public Set<EquipmentVO> getEqusByTypeTotal(String type_eq_num) {
		Set<EquipmentVO> set = new LinkedHashSet<EquipmentVO>();
		EquipmentVO equipmentVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_Equs_ByTypeTotal_STMT);
			pstmt.setString(1, type_eq_num);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				equipmentVO = new EquipmentVO();
				equipmentVO.setEq_num(rs.getString("eq_num"));
				equipmentVO.setEq_name(rs.getString("eq_name"));
				equipmentVO.setEq_type(rs.getString("eq_type"));
				equipmentVO.setEq_size(rs.getString("eq_size"));
				equipmentVO.setEq_brand(rs.getString("eq_brand"));
				equipmentVO.setEq_price(rs.getInt("eq_price"));
				equipmentVO.setEq_status(rs.getInt("eq_status"));
				equipmentVO.setEq_pic(rs.getBytes("eq_pic"));
				equipmentVO.setEq_detail(rs.getString("eq_detail"));
				equipmentVO.setType_eq_num(rs.getString("type_eq_num"));
				set.add(equipmentVO); // Store the row in the vector

			}
	
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return set;
	}
}