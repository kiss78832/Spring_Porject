package com.spring.function.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class FunctionJDBCDAO implements FunctionDAO_interface {
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TEST1";
	String passwd = "TEST1";
	
	private static final String INSERT_STMT = 
			"INSERT INTO FUNCTION (fun_num,fun_name,fun_url) "
			+ "VALUES (?,?,?)";

	private static final String GET_ALL_STMT = 
		"SELECT fun_num,fun_name,fun_url FROM FUNCTION order by fun_num";

	private static final String GET_ONE_STMT = 
		"SELECT fun_num,fun_name,fun_url FROM FUNCTION where fun_num = ?";
	
	private static final String DELETE = 
		"DELETE FROM FUNCTION where fun_num = ?";
	
	private static final String UPDATE = 
		"UPDATE FUNCTION set fun_name=?,fun_url=?  where fun_num = ?";
	

	@Override
	public void insert(FunctionVO FVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, FVO.getFun_num());
			pstmt.setString(2, FVO.getFun_name());
			pstmt.setString(3, FVO.getFun_url());


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
	public void update(FunctionVO FVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, FVO.getFun_name() );
			pstmt.setString(2, FVO.getFun_url());
			pstmt.setString(3, FVO.getFun_num());


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
	public void delete(String F_num) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, F_num);

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
	public FunctionVO findByPrimaryKey(String F_num) {
		FunctionVO FVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, F_num);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				FVO = new FunctionVO();
				FVO.setFun_num(rs.getString("fun_num"));
				FVO.setFun_name(rs.getString("fun_name"));
				FVO.setFun_url(rs.getString("fun_url"));
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
		return FVO;
	}

	@Override
	public List<FunctionVO> getAll() {
		List<FunctionVO> list = new ArrayList<FunctionVO>();
		FunctionVO FVO = null;

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
				FVO = new FunctionVO();
				FVO.setFun_num(rs.getString("fun_num"));
				FVO.setFun_name(rs.getString("fun_name"));
				FVO.setFun_url(rs.getString("fun_url"));

				list.add(FVO); // Store the row in the list
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
		
		FunctionJDBCDAO dao = new FunctionJDBCDAO();
		
		//新增
		FunctionVO FVO1 = new FunctionVO();
		
		FVO1.setFun_num("F005");
		FVO1.setFun_name("管理會員");
		FVO1.setFun_url("http://www.efws.com");
		
		dao.insert(FVO1);
		
		System.out.println("新增成功");
		
		//修改
		
		FunctionVO FVO2 = new FunctionVO();
		
		FVO2.setFun_num("F005");
		FVO2.setFun_name("不想管理");
		FVO2.setFun_url("修改http");
		
		dao.update(FVO2);
		
		System.out.println("修改成功");
		
		
		//刪除
		
		dao.delete("F005");
		
		System.out.println("刪除成功");
		
		//查詢
		
		FunctionVO FVO3 = dao.findByPrimaryKey("F001");
		
		System.out.println(FVO3.getFun_num());
		System.out.println(FVO3.getFun_name());
		System.out.println(FVO3.getFun_url());
		System.out.println("=========================");
		
		//查詢
		
		List<FunctionVO> list = dao.getAll();
		
		for(FunctionVO aF:list) {
			
			System.out.println(FVO3.getFun_num());
			System.out.println(FVO3.getFun_name());
			System.out.println(FVO3.getFun_url());
			System.out.println("=========================");
			
		}
		
		
	}
}
