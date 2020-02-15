package com.staff.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class StaffJDBCDAO implements StaffDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TEST1";
	String passwd = "TEST1";
	
	private static final String INSERT_STMT = 
			"INSERT INTO STAFF (sf_id,sf_status,sf_account,sf_password,sf_name,gender,"
			+ "cellphone,sf_email) VALUES ('S'||LPAD(to_char(STAFF_SEQ.NEXTVAL), 6, '0'),?,?,?,?,?,?,?)";

	private static final String GET_ALL_STMT = 
		"SELECT sf_id,sf_status,sf_account,sf_password,sf_name,"
		+ "gender,cellphone,sf_email FROM staff order by sf_id";

	private static final String GET_ONE_STMT = 
		"SELECT sf_id,sf_status,sf_account,sf_password,sf_name,gender,"
		+ "cellphone,sf_email FROM STAFF where sf_id = ?";
	
	private static final String DELETE = 
		"DELETE FROM STAFF where SF_ID = ?";
	
	private static final String UPDATE = 
		"UPDATE STAFF set sf_status=?,sf_account=?,sf_password=?,sf_name=?,"
		+ "gender=?,cellphone=?,sf_email=? where sf_id = ?";
	private static final String GET_ONE_BY_ACCOUNT = 
			"SELECT * FROM STAFF where sf_account = ?";
	
private static final String  COMPARE = "SELECT sf_account FROM STAFF WHERE sf_account = ?";
	
	
	@Override
	public boolean compare(String sf_account) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean exist = false;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(COMPARE);
			
			pstmt.setString(1, sf_account);
			
			rs = pstmt.executeQuery();
			
			exist = rs.next();//true 存在
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally{
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return exist;
	}
	
	
	
	@Override
	public StaffVO findByAccount(String account) {
		
		StaffVO SVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_BY_ACCOUNT);

			pstmt.setString(1, account);

			rs = pstmt.executeQuery();
			
			System.out.println("查詢成功");

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				SVO = new StaffVO();
				
				SVO.setSf_id(rs.getString("sf_id"));
				SVO.setSf_account(rs.getString("sf_account"));
				SVO.setSf_password(rs.getString("sf_password"));
				SVO.setSf_name(rs.getString("sf_name"));
				SVO.setGender(rs.getInt("gender"));
				SVO.setCellphone(rs.getString("cellphone"));
				SVO.setSf_email(rs.getString("sf_email"));
				
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
		return SVO;
		
	}
	
		@Override
		public void insert(StaffVO SVO) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(INSERT_STMT);

				
				
				pstmt.setInt(1,SVO.getSf_status());
				pstmt.setString(2,SVO.getSf_account());
				pstmt.setString(3,SVO.getSf_password());
				pstmt.setString(4,SVO.getSf_name());
				pstmt.setInt(5,SVO.getGender());
				pstmt.setString(6,SVO.getCellphone());
				pstmt.setString(7,SVO.getSf_email());

				pstmt.executeUpdate();
				
				System.out.println("新增成功");

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
		public void update(StaffVO SVO) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(UPDATE);

				pstmt.setInt(1,SVO.getSf_status());
				pstmt.setString(2,SVO.getSf_account());
				pstmt.setString(3,SVO.getSf_password());
				pstmt.setString(4,SVO.getSf_name());
				pstmt.setInt(5,SVO.getGender());
				pstmt.setString(6,SVO.getCellphone());
				pstmt.setString(7,SVO.getSf_email());
				pstmt.setString(8, SVO.getSf_id());
				pstmt.executeUpdate();
				
				System.out.println("更新成功");

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
		public void delete(String SID) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(DELETE);

				pstmt.setString(1, SID);

				pstmt.executeUpdate();
				
				System.out.println("刪除成功");

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
		public StaffVO findByPrimaryKey(String SID) {
			StaffVO SVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setString(1, SID);

				rs = pstmt.executeQuery();
				
				System.out.println("查詢成功");

				while (rs.next()) {
					// empVo 也稱為 Domain objects
					SVO = new StaffVO();
					
					SVO.setSf_id(rs.getString("sf_id"));
					SVO.setSf_status(rs.getInt("sf_status"));
					SVO.setSf_account(rs.getString("sf_account"));
					SVO.setSf_password(rs.getString("sf_password"));
					SVO.setSf_name(rs.getString("sf_name"));
					SVO.setGender(rs.getInt("gender"));
					SVO.setCellphone(rs.getString("cellphone"));
					SVO.setSf_email(rs.getString("sf_email"));
					
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
			return SVO;
		}
		@Override
		public List<StaffVO> getAll() {
			List<StaffVO> list = new ArrayList<StaffVO>();
			StaffVO SVO = null;

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
					SVO = new StaffVO();
					SVO.setSf_id(rs.getString("sf_id"));
					SVO.setSf_status(rs.getInt("sf_status"));
					SVO.setSf_account(rs.getString("sf_account"));
					SVO.setSf_password(rs.getString("sf_password"));
					SVO.setSf_name(rs.getString("sf_name"));
					SVO.setGender(rs.getInt("gender"));
					SVO.setCellphone(rs.getString("cellphone"));
					SVO.setSf_email(rs.getString("sf_email"));
					list.add(SVO); // Store the row in the list
				}
				
				System.out.println("查詢成功");

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
		
		StaffJDBCDAO dao = new StaffJDBCDAO();
		
	//新增
		
//		StaffVO SVO1 = new StaffVO();
//		
//		SVO1.setSf_status(0);
//		SVO1.setSf_account("S001");
//		SVO1.setSf_password("123456");
//		SVO1.setSf_name("勞菲");
//		SVO1.setGender(0);
//		SVO1.setCellphone("0976321689");
//		SVO1.setSf_email("S0001@gmail.com");
//		
//		dao.insert(SVO1);
//		
		
		
		//修改
		
//		StaffVO SVO2 = new StaffVO();
//		
//		SVO2.setSf_id("S000001");
//		SVO2.setSf_status(0);
//		SVO2.setSf_account("S0021");
//		SVO2.setSf_password("999666");
//		SVO2.setSf_name("巴拉松");
//		SVO2.setGender(0);
//		SVO2.setCellphone("0976321689");
//		SVO2.setSf_email("S0001@gmail.com");
//		
//		dao.update(SVO2);
		
		//刪除
		
//		dao.delete("S000003");
		
		//查詢
		
		StaffVO SVO3 = dao.findByPrimaryKey("S000009");
		System.out.println(SVO3);
//		System.out.println(SVO3.getSf_id());
//		System.out.println(SVO3.getSf_status());
//		System.out.println(SVO3.getSf_account());
//		System.out.println(SVO3.getSf_password());
//		System.out.println(SVO3.getSf_name());
//		System.out.println(SVO3.getGender());
//		System.out.println(SVO3.getCellphone());
//		System.out.println(SVO3.getSf_email());
		System.out.println("=========================");
		
		//查詢
		
//		List<StaffVO> list = dao.getAll();
//		for (StaffVO aSF : list) {
//			System.out.println(aSF.getSf_id());
//			System.out.println(aSF.getSf_status());
//			System.out.println(aSF.getSf_account());
//			System.out.println(aSF.getSf_password());
//			System.out.println(aSF.getSf_name());
//			System.out.println(aSF.getGender());
//			System.out.println(aSF.getCellphone());
//			System.out.println(aSF.getSf_email());
//			System.out.println("=========================");
//		}
	}


	

}


