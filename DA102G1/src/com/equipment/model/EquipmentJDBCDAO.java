package com.equipment.model;

import java.util.*;



import java.sql.*;

public class EquipmentJDBCDAO implements EquipmentDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TEST1";
	String passwd = "TEST1";

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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	public void update(EquipmentVO equVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	public void delete(String eq_num) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, eq_num);

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
	public EquipmentVO findByPrimaryKey(String eq_num) {

		EquipmentVO equVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		return equVO;
	}
	
	@Override
	public EquipmentVO findByTypeEqNum(String type_eq_num) {

		EquipmentVO equVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	public List<EquipmentVO> findByAllTypeEqNum(String type_eq_num) {
		List<EquipmentVO> list = new ArrayList<EquipmentVO>();
		EquipmentVO equVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	public EquipmentVO findByNameAndSize(String eq_name ,String eq_size){

		EquipmentVO equVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	public List<EquipmentVO> getAll() {
		List<EquipmentVO> list = new ArrayList<EquipmentVO>();
		EquipmentVO equVO = null;

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

	public static void main(String[] args) {

		EquipmentJDBCDAO dao = new EquipmentJDBCDAO();

//		// 新增
//		EquipmentVO equVO1 = new EquipmentVO();
//
//		equVO1.setEq_name("冒險旅游涼鞋");
//		equVO1.setEq_type("涼鞋");
//		equVO1.setEq_size("26.5");
//		equVO1.setEq_brand("CHACO");
//		equVO1.setEq_price(2650);
//		equVO1.setEq_status(0);
//		equVO1.setEq_pic(null);
//		equVO1.setEq_detail("青菜低家啦");
//		equVO1.setType_eq_num("ET00000001");
//		dao.insert(equVO1);
//
//		// 修改
//		EquipmentVO equVO2 = new EquipmentVO();
//		equVO2.setEq_num("EQ00000001");
//		equVO2.setEq_name("冒險旅游涼鞋1");
//		equVO2.setEq_type("涼鞋1");
//		equVO2.setEq_size("27");
//		equVO2.setEq_brand("CHACO1");
//		equVO2.setEq_price(2651);
//		equVO2.setEq_status(1);
//		equVO2.setEq_pic(null);
//		equVO2.setEq_detail("青菜低價啦");
//		equVO2.setType_eq_num("ET00000001");
//		dao.update(equVO2);
//
//		// 刪除
//		dao.delete("EQ00000002");
//
//		// 查詢
//		EquipmentVO equVO3 = dao.findByPrimaryKey("EQ00000001");
//		System.out.print(equVO3.getEq_num() + ",");
//		System.out.print(equVO3.getEq_name() + ",");
//		System.out.print(equVO3.getEq_type() + ",");
//		System.out.print(equVO3.getEq_size() + ",");
//		System.out.print(equVO3.getEq_brand() + ",");
//		System.out.print(equVO3.getEq_price() + ",");
//		System.out.print(equVO3.getEq_status() + ",");
//		System.out.print(equVO3.getEq_pic() + ",");
//		System.out.print(equVO3.getEq_detail() + ",");
//		System.out.println(equVO3.getType_eq_num() + ",");
//		System.out.println("---------------------");
		
		// 查詢
		EquipmentVO equVO3 = dao.findByTypeEqNum("ET00000001");
		System.out.print(equVO3.getEq_num() + ",");
		System.out.print(equVO3.getEq_name() + ",");
		System.out.print(equVO3.getEq_type() + ",");
		System.out.print(equVO3.getEq_size() + ",");
		System.out.print(equVO3.getEq_brand() + ",");
		System.out.print(equVO3.getEq_price() + ",");
		System.out.print(equVO3.getEq_status() + ",");
		System.out.print(equVO3.getEq_pic() + ",");
		System.out.print(equVO3.getEq_detail() + ",");
		System.out.println(equVO3.getType_eq_num() + ",");
		System.out.println("---------------------");
		
//		// 查詢
//		EquipmentVO equVO3 = dao.findByNameAndSize("長袖外套","XL");
//		System.out.print(equVO3.getEq_num() + ",");
//		System.out.print(equVO3.getEq_name() + ",");
//		System.out.print(equVO3.getEq_type() + ",");
//		System.out.print(equVO3.getEq_size() + ",");
//		System.out.print(equVO3.getEq_brand() + ",");
//		System.out.print(equVO3.getEq_price() + ",");
//		System.out.print(equVO3.getEq_status() + ",");
//		System.out.print(equVO3.getEq_pic() + ",");
//		System.out.print(equVO3.getEq_detail() + ",");
//		System.out.println(equVO3.getType_eq_num() + ",");
//		System.out.println("---------------------");
		
//		// 查詢
//		List<EquipmentVO> list = dao.findByType("衣服");
//		for (EquipmentVO aEqu : list) {
//			System.out.print(aEqu.getEq_num() + ",");
//			System.out.print(aEqu.getEq_name() + ",");
//			System.out.print(aEqu.getEq_type() + ",");
//			System.out.print(aEqu.getEq_size() + ",");
//			System.out.print(aEqu.getEq_brand() + ",");
//			System.out.print(aEqu.getEq_price() + ",");
//			System.out.print(aEqu.getEq_status() + ",");
//			System.out.print(aEqu.getEq_pic() + ",");
//			System.out.print(aEqu.getEq_detail() + ",");
//			System.out.print(aEqu.getType_eq_num() + ",");
//			System.out.println();
			
//			// 查詢
//			List<EquipmentVO> list = dao.findBySize("鴨舌帽");
//			for (EquipmentVO aEqu : list) {
//				System.out.print(aEqu.getEq_num() + ",");
//				System.out.print(aEqu.getEq_name() + ",");
//				System.out.print(aEqu.getEq_type() + ",");
//				System.out.print(aEqu.getEq_size() + ",");
//				System.out.print(aEqu.getEq_brand() + ",");
//				System.out.print(aEqu.getEq_price() + ",");
//				System.out.print(aEqu.getEq_status() + ",");
//				System.out.print(aEqu.getEq_pic() + ",");
//				System.out.print(aEqu.getEq_detail() + ",");
//				System.out.print(aEqu.getType_eq_num() + ",");
//				System.out.println();	
		
		// 查詢
		List<EquipmentVO> list = dao.findByAllTypeEqNum("ET00000001");
		for (EquipmentVO aEqu : list) {
			System.out.print(aEqu.getEq_num() + ",");
			System.out.print(aEqu.getEq_name() + ",");
			System.out.print(aEqu.getEq_type() + ",");
			System.out.print(aEqu.getEq_size() + ",");
			System.out.print(aEqu.getEq_brand() + ",");
			System.out.print(aEqu.getEq_price() + ",");
			System.out.print(aEqu.getEq_status() + ",");
			System.out.print(aEqu.getEq_pic() + ",");
			System.out.print(aEqu.getEq_detail() + ",");
			System.out.print(aEqu.getType_eq_num() + ",");
			System.out.println();	
		}
//		// 查詢
//		List<EquipmentVO> list = dao.getAll();
//		for (EquipmentVO aEqu : list) {
//			System.out.print(aEqu.getEq_num() + ",");
//			System.out.print(aEqu.getEq_name() + ",");
//			System.out.print(aEqu.getEq_type() + ",");
//			System.out.print(aEqu.getEq_size() + ",");
//			System.out.print(aEqu.getEq_brand() + ",");
//			System.out.print(aEqu.getEq_price() + ",");
//			System.out.print(aEqu.getEq_status() + ",");
//			System.out.print(aEqu.getEq_pic() + ",");
//			System.out.print(aEqu.getEq_detail() + ",");
//			System.out.print(aEqu.getType_eq_num() + ",");
//			System.out.println();
//		}
	}
}
