package com.authority.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AuthorityJDBCDAO implements AuthorityDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TEST1";
	String passwd = "TEST1";
	
	private static final String INSERT_STMT = 
			"INSERT INTO AUTHORITY (sf_id,fun_num) VALUES (?,?)";

	private static final String GET_ALL_STMT = 
		"SELECT sf_id,fun_num FROM AUTHORITY order by sf_id";

	private static final String GET_ONE_STMT = 
		"SELECT sf_id,fun_num FROM AUTHORITY where sf_id = ?";
	
	private static final String DELETE = //先全刪除，後新增
		"DELETE FROM AUTHORITY where sf_id = ?";
	
	private static final String UPDATE = 
		"UPDATE AUTHORITY set sf_id,fun_num where sf_id = ? and fun_num =?";

	
	
	@Override
	public void insert(AuthorityVO AVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			
			pstmt.setString(1,AVO.getSf_id());
			pstmt.setString(2,AVO.getFun_num());


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
	public void update(AuthorityVO AVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1,AVO.getSf_id());
			pstmt.setString(2,AVO.getFun_num());
			pstmt.setString(3,AVO.getSf_id());
			pstmt.setString(4,AVO.getFun_num());

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
	public void delete(String sf_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, sf_id);

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
	public List<AuthorityVO> findByPrimaryKey(String sf_id) {
		AuthorityVO AVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<AuthorityVO> list = new ArrayList<AuthorityVO>();
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, sf_id);
			

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				AVO = new AuthorityVO();
				AVO.setSf_id(rs.getString("sf_id"));
				AVO.setFun_num(rs.getString("fun_num"));
				
				list.add(AVO);
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
		return list;
	}

	@Override
	public List<AuthorityVO> getAll() {
		List<AuthorityVO> list = new ArrayList<AuthorityVO>();
		AuthorityVO AVO = null;

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
				AVO = new AuthorityVO();
				AVO.setSf_id(rs.getString("sf_id"));
				AVO.setFun_num(rs.getString("fun_num"));

				list.add(AVO); // Store the row in the list
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
		return list;
	}

	
	public static void main(String[] args) {
		
		
		AuthorityJDBCDAO dao = new AuthorityJDBCDAO();
		
		
//		//新增
		AuthorityVO AVO1 = new AuthorityVO();
		
		AVO1.setSf_id("S000001");
		AVO1.setFun_num("F004");
		
		dao.insert(AVO1);
		System.out.println("新增成功");
//		
//		//修改
//		
//		AuthorityVO AVO2 = new AuthorityVO();
//		
//		AVO2.setSf_id("S000002");
//		AVO2.setFun_num("F001");
//		
//		System.out.println("修改成功");
//		
//		//刪除
//		
//		dao.delete("S000002");
//		
//		System.out.println("刪除成功");
//		
//		//查詢
//		
//		AuthorityVO AVO3 = dao.findByPrimaryKey("S000001");
//		
//		System.out.println(AVO3.getSf_id());
//		System.out.println(AVO3.getFun_num());
//		
//		System.out.println("單筆查詢成功");
//		
//		//查詢
//		
		
		List<AuthorityVO> list =  dao.getAll();
		

		for(AuthorityVO AVO:list) {
			System.out.println(AVO.getSf_id());
			System.out.println(AVO.getFun_num());
			System.out.println("===================================");
		}
		
		
		
	}
	
}
