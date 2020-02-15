package com.rentodlist.model;

import java.util.*;

import com.rentodlist.model.RentOdListVO;
import com.dailytypetotal.model.DailyTotalJDBCDAO;
import com.dailytypetotal.model.DailyTotalVO;
import com.rentoddetail.model.RentOdDetailJDBCDAO;
import com.rentoddetail.model.RentOdDetailVO;

import java.sql.*;

public class RentOdListJDBCDAO implements RentOdListDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TEST1";
	String passwd = "TEST1";

	private static final String INSERT_STMT = "INSERT INTO RENT_ODLIST (RENT_ODNUM, MEMBER_ID, RENT_PAYMENT,RSVED_RENT_DATE,REAL_RENT_DATE,EX_RETURN_DATE,REAL_RETURN_DATE,OD_STATUS,OD_TOTAL_PRICE,RENT_NAME,RENT_PHONE) VALUES ('RO'||LPAD(to_char(SQU_RENT_ODLIST.NEXTVAL), 8, '0'),?,?,?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT RENT_ODNUM, MEMBER_ID,ODLIST_CREATEDATE, RENT_PAYMENT,RSVED_RENT_DATE,REAL_RENT_DATE,EX_RETURN_DATE,REAL_RETURN_DATE,OD_STATUS,OD_TOTAL_PRICE,RENT_NAME,RENT_PHONE FROM RENT_ODLIST  order by RENT_ODNUM";
	private static final String GET_ONE_STMT = "SELECT RENT_ODNUM, MEMBER_ID,ODLIST_CREATEDATE, RENT_PAYMENT,RSVED_RENT_DATE,REAL_RENT_DATE,EX_RETURN_DATE,REAL_RETURN_DATE,OD_STATUS,OD_TOTAL_PRICE,RENT_NAME,RENT_PHONE FROM RENT_ODLIST where RENT_ODNUM = ?";
	private static final String DELETE = "DELETE FROM RENT_ODLIST where RENT_ODNUM= ?";
	private static final String UPDATE = "UPDATE RENT_ODLIST set MEMBER_ID=?, RENT_PAYMENT=?,RSVED_RENT_DATE=?,REAL_RENT_DATE=?,EX_RETURN_DATE=?,REAL_RETURN_DATE=?,OD_STATUS=?,OD_TOTAL_PRICE=?,RENT_NAME=?,RENT_PHONE=? where RENT_ODNUM=?";
	private static final String GET_ALL_MEMBERORDER = "SELECT RENT_ODNUM, MEMBER_ID,ODLIST_CREATEDATE, RENT_PAYMENT,RSVED_RENT_DATE,REAL_RENT_DATE,EX_RETURN_DATE,REAL_RETURN_DATE,OD_STATUS,OD_TOTAL_PRICE,RENT_NAME,RENT_PHONE FROM RENT_ODLIST where MEMBER_ID = ?";

	@Override
	public void insert(RentOdListVO rentodlistVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, rentodlistVO.getMember_id());
			pstmt.setString(2, rentodlistVO.getRent_payment());
			pstmt.setDate(3, rentodlistVO.getRsved_rent_date());
			pstmt.setDate(4, rentodlistVO.getReal_rent_date());
			pstmt.setDate(5, rentodlistVO.getEx_return_date());
			pstmt.setDate(6, rentodlistVO.getReal_return_date());
			pstmt.setInt(7, rentodlistVO.getOd_status());
			pstmt.setInt(8, rentodlistVO.getOd_total_price());
			pstmt.setString(9, rentodlistVO.getRent_name());
			pstmt.setString(10, rentodlistVO.getRent_phone());

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
	public void update(RentOdListVO rentodlistVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, rentodlistVO.getMember_id());
			pstmt.setString(2, rentodlistVO.getRent_payment());
			pstmt.setDate(3, rentodlistVO.getRsved_rent_date());
			pstmt.setDate(4, rentodlistVO.getReal_rent_date());
			pstmt.setDate(5, rentodlistVO.getEx_return_date());
			pstmt.setDate(6, rentodlistVO.getReal_return_date());
			pstmt.setInt(7, rentodlistVO.getOd_status());
			pstmt.setInt(8, rentodlistVO.getOd_total_price());
			pstmt.setString(9, rentodlistVO.getRent_name());
			pstmt.setString(10, rentodlistVO.getRent_phone());
			pstmt.setString(11, rentodlistVO.getRent_odnum());

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
	public void delete(String rent_odnum) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, rent_odnum);

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
	public RentOdListVO findByPrimaryKey(String rent_odnum) {

		RentOdListVO rentodlistVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, rent_odnum);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				rentodlistVO = new RentOdListVO();
				rentodlistVO.setRent_odnum(rs.getString("rent_odnum"));
				rentodlistVO.setMember_id(rs.getString("member_id"));
				rentodlistVO.setOdlist_createdate(rs.getTimestamp("odlist_createdate"));
				rentodlistVO.setRent_payment(rs.getString("rent_payment"));
				rentodlistVO.setRsved_rent_date(rs.getDate("rsved_rent_date"));
				rentodlistVO.setReal_rent_date(rs.getDate("real_rent_date"));
				rentodlistVO.setEx_return_date(rs.getDate("ex_return_date"));
				rentodlistVO.setReal_return_date(rs.getDate("real_return_date"));
				rentodlistVO.setOd_status(rs.getInt("od_status"));
				rentodlistVO.setOd_total_price(rs.getInt("od_total_price"));
				rentodlistVO.setRent_name(rs.getString("rent_name"));
				rentodlistVO.setRent_phone(rs.getString("rent_phone"));

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
		return rentodlistVO;
	}

	@Override
	public List<RentOdListVO> getAll() {
		List<RentOdListVO> list = new ArrayList<RentOdListVO>();
		RentOdListVO rentodlistVO = null;

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
				rentodlistVO = new RentOdListVO();
				rentodlistVO.setRent_odnum(rs.getString("rent_odnum"));
				rentodlistVO.setMember_id(rs.getString("member_id"));
				rentodlistVO.setOdlist_createdate(rs.getTimestamp("odlist_createdate"));
				rentodlistVO.setRent_payment(rs.getString("rent_payment"));
				rentodlistVO.setRsved_rent_date(rs.getDate("rsved_rent_date"));
				rentodlistVO.setReal_rent_date(rs.getDate("real_rent_date"));
				rentodlistVO.setEx_return_date(rs.getDate("ex_return_date"));
				rentodlistVO.setReal_return_date(rs.getDate("real_return_date"));
				rentodlistVO.setOd_status(rs.getInt("od_status"));
				rentodlistVO.setOd_total_price(rs.getInt("od_total_price"));
				rentodlistVO.setRent_name(rs.getString("rent_name"));
				rentodlistVO.setRent_phone(rs.getString("rent_phone"));
				list.add(rentodlistVO); // Store the row in the list

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
	public List<RentOdListVO> findByMemOrder(String member_id) {
		List<RentOdListVO> list = new ArrayList<RentOdListVO>();
		RentOdListVO rentodlistVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_MEMBERORDER);
			
			pstmt.setString(1, member_id);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				rentodlistVO = new RentOdListVO();
				rentodlistVO.setRent_odnum(rs.getString("rent_odnum"));
				rentodlistVO.setMember_id(rs.getString("member_id"));
				rentodlistVO.setOdlist_createdate(rs.getTimestamp("odlist_createdate"));
				rentodlistVO.setRent_payment(rs.getString("rent_payment"));
				rentodlistVO.setRsved_rent_date(rs.getDate("rsved_rent_date"));
				rentodlistVO.setReal_rent_date(rs.getDate("real_rent_date"));
				rentodlistVO.setEx_return_date(rs.getDate("ex_return_date"));
				rentodlistVO.setReal_return_date(rs.getDate("real_return_date"));
				rentodlistVO.setOd_status(rs.getInt("od_status"));
				rentodlistVO.setOd_total_price(rs.getInt("od_total_price"));
				rentodlistVO.setRent_name(rs.getString("rent_name"));
				rentodlistVO.setRent_phone(rs.getString("rent_phone"));
				list.add(rentodlistVO); // Store the row in the list

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
	public void insertWithOrd(RentOdListVO rentodlistVO, List<RentOdDetailVO> list) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			// 1●設定於 pstm.executeUpdate()之前
    		con.setAutoCommit(false);
			
    		// 先新增部門
			String cols[] = {"RENT_ODNUM"};
			pstmt = con.prepareStatement(INSERT_STMT , cols);			
			pstmt.setString(1, rentodlistVO.getMember_id());
			pstmt.setString(2, rentodlistVO.getRent_payment());
			pstmt.setDate(3, rentodlistVO.getRsved_rent_date());
			pstmt.setDate(4, rentodlistVO.getReal_rent_date());
			pstmt.setDate(5, rentodlistVO.getEx_return_date());
			pstmt.setDate(6, rentodlistVO.getReal_return_date());
			pstmt.setInt(7, rentodlistVO.getOd_status());
			pstmt.setInt(8, rentodlistVO.getOd_total_price());
			pstmt.setString(9, rentodlistVO.getRent_name());
			pstmt.setString(10, rentodlistVO.getRent_phone());

			pstmt.executeUpdate();
			
			
			
			//掘取對應的自增主鍵值
			String next_rent_odnum = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_rent_odnum = rs.getString(1);
				System.out.println("自增主鍵值= " + next_rent_odnum +"(剛新增成功的部門編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			// 再同時新增員工
			RentOdDetailJDBCDAO dao = new RentOdDetailJDBCDAO();
			System.out.println("list.size()-A="+list.size());
			for (RentOdDetailVO aEmp : list) {
				aEmp.setRent_odnum(new String(next_rent_odnum)) ;
				dao.insert2(aEmp,con);
			}

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("list.size()-B="+list.size());
			System.out.println("新增部門編號" + next_rent_odnum + "時,共有員工" + list.size()
					+ "人同時被新增");
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-dept");
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
	public String insertWithOrdAndDaily(RentOdListVO rentodlistVO, List<RentOdDetailVO> list ,List<DailyTotalVO> list1) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			// 1●設定於 pstm.executeUpdate()之前
    		con.setAutoCommit(false);
			
    		// 先新增部門
			String cols[] = {"RENT_ODNUM"};
			pstmt = con.prepareStatement(INSERT_STMT , cols);			
			pstmt.setString(1, rentodlistVO.getMember_id());
			pstmt.setString(2, rentodlistVO.getRent_payment());
			pstmt.setDate(3, rentodlistVO.getRsved_rent_date());
			pstmt.setDate(4, rentodlistVO.getReal_rent_date());
			pstmt.setDate(5, rentodlistVO.getEx_return_date());
			pstmt.setDate(6, rentodlistVO.getReal_return_date());
			pstmt.setInt(7, rentodlistVO.getOd_status());
			pstmt.setInt(8, rentodlistVO.getOd_total_price());
			pstmt.setString(9, rentodlistVO.getRent_name());
			pstmt.setString(10, rentodlistVO.getRent_phone());

			pstmt.executeUpdate();
			
			
			
			//掘取對應的自增主鍵值
			String next_rent_odnum = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_rent_odnum = rs.getString(1);
				System.out.println("自增主鍵值= " + next_rent_odnum +"(剛新增成功的部門編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			// 再同時新增員工
			RentOdDetailJDBCDAO dao = new RentOdDetailJDBCDAO();
			System.out.println("list.size()-A="+list.size());
			for (RentOdDetailVO aEmp : list) {
				aEmp.setRent_odnum(new String(next_rent_odnum)) ;
				dao.insert2(aEmp,con);
			}
			
			DailyTotalJDBCDAO dao1 = new DailyTotalJDBCDAO();
			System.out.println("list.size()-A="+list.size());
			for (DailyTotalVO aDai : list1) {
				dao1.insert2(aDai,con);
			}

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("list.size()-B="+list.size());
			System.out.println("新增部門編號" + next_rent_odnum + "時,共有員工" + list.size()
					+ "人同時被新增");
			return next_rent_odnum;
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-dept");
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
	public void updateWithOrdAndDaily(RentOdListVO rentodlistVO, List<RentOdDetailVO> list ,List<DailyTotalVO> list1) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			// 1●設定於 pstm.executeUpdate()之前
    		con.setAutoCommit(false);
			
    		// 先新增部門

			pstmt = con.prepareStatement(UPDATE);			
			pstmt.setString(1, rentodlistVO.getMember_id());
			pstmt.setString(2, rentodlistVO.getRent_payment());
			pstmt.setDate(3, rentodlistVO.getRsved_rent_date());
			pstmt.setDate(4, rentodlistVO.getReal_rent_date());
			pstmt.setDate(5, rentodlistVO.getEx_return_date());
			pstmt.setDate(6, rentodlistVO.getReal_return_date());
			pstmt.setInt(7, rentodlistVO.getOd_status());
			pstmt.setInt(8, rentodlistVO.getOd_total_price());
			pstmt.setString(9, rentodlistVO.getRent_name());
			pstmt.setString(10, rentodlistVO.getRent_phone());
			pstmt.setString(11, rentodlistVO.getRent_odnum());

			pstmt.executeUpdate();
			
			
			// 再同時新增員工
			RentOdDetailJDBCDAO dao = new RentOdDetailJDBCDAO();
			for (RentOdDetailVO aEmp : list) {
				dao.update2(aEmp,con);
			}
			
			DailyTotalJDBCDAO dao1 = new DailyTotalJDBCDAO();
			for (DailyTotalVO aDai : list1) {
				dao1.update2(aDai,con);
			}

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-dept");
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
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}
	
	
	

	public static void main(String[] args) {

		RentOdListJDBCDAO dao = new RentOdListJDBCDAO();
		
		
		
//		RentOdListVO rentodlistVO = new RentOdListVO();
//		rentodlistVO.setMember_id("A001");
//		rentodlistVO.setRent_payment("CREDITCARD");
//		rentodlistVO.setRsved_rent_date(java.sql.Date.valueOf("2019-08-15"));
//		rentodlistVO.setReal_rent_date(java.sql.Date.valueOf("2019-08-15"));
//		rentodlistVO.setEx_return_date(java.sql.Date.valueOf("2019-08-17"));
//		rentodlistVO.setReal_return_date(java.sql.Date.valueOf("2019-08-17"));
//		rentodlistVO.setOd_status(1);
//		rentodlistVO.setOd_total_price(5300);
//		rentodlistVO.setRent_name("merry");
//		rentodlistVO.setRent_phone("0977777");
//		
//		List<RentOdDetailVO> testList = new ArrayList<RentOdDetailVO>(); // 準備置入員工數人
//		RentOdDetailVO detailXX = new RentOdDetailVO();   // 員工POJO1
//		detailXX.setEq_num("EQ00000001");
//		detailXX.setQuantity(2650);
//
//		RentOdDetailVO detailYY = new RentOdDetailVO();   // 員工POJO2
//		detailYY.setEq_num("EQ00000002");
//		detailYY.setQuantity(2650);
//
//		testList.add(detailXX);
//		testList.add(detailYY);
//		
//		dao.insertWithOrd(rentodlistVO , testList);
		
		
		
		
		
		
//		RentOdListVO rentodlistVO = new RentOdListVO();
//		rentodlistVO.setMember_id("A001");
//		rentodlistVO.setRent_payment("CREDITCARD");
//		rentodlistVO.setRsved_rent_date(java.sql.Date.valueOf("2019-08-15"));
//		rentodlistVO.setReal_rent_date(java.sql.Date.valueOf("2019-08-15"));
//		rentodlistVO.setEx_return_date(java.sql.Date.valueOf("2019-08-17"));
//		rentodlistVO.setReal_return_date(java.sql.Date.valueOf("2019-08-17"));
//		rentodlistVO.setOd_status(1);
//		rentodlistVO.setOd_total_price(5300);
//		rentodlistVO.setRent_name("merry");
//		rentodlistVO.setRent_phone("0977777");
//		
//		List<RentOdDetailVO> testList = new ArrayList<RentOdDetailVO>(); // 準備置入員工數人
//		RentOdDetailVO detailXX = new RentOdDetailVO();   // 員工POJO1
//		detailXX.setEq_num("EQ00000001");
//		detailXX.setQuantity(2650);
//
//		RentOdDetailVO detailYY = new RentOdDetailVO();   // 員工POJO2
//		detailYY.setEq_num("EQ00000002");
//		detailYY.setQuantity(2650);
//
//		testList.add(detailXX);
//		testList.add(detailYY);
//		
//		List<DailyTotalVO> testList1 = new ArrayList<DailyTotalVO>(); // 準備置入員工數人
//		DailyTotalVO dailyXX = new DailyTotalVO();   // 員工POJO1
//		dailyXX.setDailyeq_num("DT00000001");
//		dailyXX.setType_eq_num("ET00000001");
//		dailyXX.setEq_date(java.sql.Date.valueOf("2019-09-17"));
//		dailyXX.setDaily_eq_qty(10);
//		dailyXX.setStart_qty(2);
//
//		DailyTotalVO dailyYY = new DailyTotalVO();   // 員工POJO2
//		dailyYY.setDailyeq_num("DT00000016");
//		dailyYY.setType_eq_num("ET00000002");
//		dailyYY.setEq_date(java.sql.Date.valueOf("2019-09-17"));
//		dailyYY.setDaily_eq_qty(10);
//		dailyYY.setStart_qty(2);
//
//		testList1.add(dailyXX);
//		testList1.add(dailyYY);
//		
//		dao.insertWithOrdAndDaily(rentodlistVO , testList, testList1);
		
		
//		RentOdListVO rentodlistVO = new RentOdListVO();
//		rentodlistVO.setMember_id("A001");
//		rentodlistVO.setRent_payment("CREDITCARD");
//		rentodlistVO.setRsved_rent_date(java.sql.Date.valueOf("2019-08-15"));
//		rentodlistVO.setReal_rent_date(java.sql.Date.valueOf("2019-08-15"));
//		rentodlistVO.setEx_return_date(java.sql.Date.valueOf("2019-08-17"));
//		rentodlistVO.setReal_return_date(java.sql.Date.valueOf("2019-08-17"));
//		rentodlistVO.setOd_status(1);
//		rentodlistVO.setOd_total_price(5300);
//		rentodlistVO.setRent_name("merry");
//		rentodlistVO.setRent_phone("0977777");
//		rentodlistVO.setRent_odnum("RO00000127");
//		
//		List<RentOdDetailVO> testList = new ArrayList<RentOdDetailVO>(); // 準備置入員工數人
//		RentOdDetailVO detailXX = new RentOdDetailVO();   // 員工POJO1
//		rentodlistVO.setRent_odnum("RO00000127");
//		detailXX.setEq_num("EQ00000001");
//		detailXX.setQuantity(2650);
//
//		RentOdDetailVO detailYY = new RentOdDetailVO(); // 員工POJO2
//		rentodlistVO.setRent_odnum("RO00000127");
//		detailYY.setEq_num("EQ00000002");
//		detailYY.setQuantity(2650);
//
//		testList.add(detailXX);
//		testList.add(detailYY);
//		
//		List<DailyTotalVO> testList1 = new ArrayList<DailyTotalVO>(); // 準備置入員工數人
//		DailyTotalVO dailyXX = new DailyTotalVO();   // 員工POJO1
//		dailyXX.setDailyeq_num("DT00000001");
//		dailyXX.setType_eq_num("ET00000001");
//		dailyXX.setEq_date(java.sql.Date.valueOf("2019-09-17"));
//		dailyXX.setDaily_eq_qty(10);
//		dailyXX.setStart_qty(2);
//
//		DailyTotalVO dailyYY = new DailyTotalVO();   // 員工POJO2
//		dailyYY.setDailyeq_num("DT00000016");
//		dailyYY.setType_eq_num("ET00000002");
//		dailyYY.setEq_date(java.sql.Date.valueOf("2019-09-17"));
//		dailyYY.setDaily_eq_qty(10);
//		dailyYY.setStart_qty(2);
//
//		testList1.add(dailyXX);
//		testList1.add(dailyYY);
//		
//		dao.updateWithOrdAndDaily(rentodlistVO , testList, testList1);

		
//
//		// 新增
//		RentOdListVO rentodlistVO1 = new RentOdListVO();
//		rentodlistVO1.setMember_id("A001");
//		rentodlistVO1.setRent_payment("CREDITCARD");
//		rentodlistVO1.setRsved_rent_date(java.sql.Date.valueOf("2019-08-15"));
//		rentodlistVO1.setReal_rent_date(java.sql.Date.valueOf("2019-08-15"));
//		rentodlistVO1.setEx_return_date(java.sql.Date.valueOf("2019-08-17"));
//		rentodlistVO1.setReal_return_date(java.sql.Date.valueOf("2019-08-17"));
//		rentodlistVO1.setOd_status(1);
//		rentodlistVO1.setOd_total_price(5300);
//		rentodlistVO1.setRent_name("merry");
//		rentodlistVO1.setRent_phone("0977777");
//		dao.insert(rentodlistVO1);
//
//		// 修改
//		RentOdListVO rentodlistVO2 = new RentOdListVO();
//		rentodlistVO2.setRent_odnum("RO00000001");
//		rentodlistVO2.setMember_id("A002");
//		rentodlistVO2.setRent_payment("CREDITCARD1");
//		rentodlistVO2.setRsved_rent_date(java.sql.Date.valueOf("2019-08-16"));
//		rentodlistVO2.setReal_rent_date(java.sql.Date.valueOf("2019-08-18"));
//		rentodlistVO2.setEx_return_date(java.sql.Date.valueOf("2019-08-16"));
//		rentodlistVO2.setReal_return_date(java.sql.Date.valueOf("2019-08-18"));
//		rentodlistVO2.setOd_status(2);
//		rentodlistVO2.setOd_total_price(5301);
//		rentodlistVO2.setRent_name("merry1");
//		rentodlistVO2.setRent_phone("09777772");
//		dao.update(rentodlistVO2);
//
//		// 刪除
//		dao.delete("RO00000002");
//
//		// 查詢
//		RentOdListVO rentodlistVO3 = dao.findByPrimaryKey("RO00000001");
//		System.out.print(rentodlistVO3.getRent_odnum() + ",");
//		System.out.print(rentodlistVO3.getMember_id() + ",");
//		System.out.print(rentodlistVO3.getOdlist_createdate() + ",");
//		System.out.print(rentodlistVO3.getRent_payment() + ",");
//		System.out.print(rentodlistVO3.getRsved_rent_date() + ",");
//		System.out.print(rentodlistVO3.getReal_rent_date() + ",");
//		System.out.print(rentodlistVO3.getEx_return_date() + ",");
//		System.out.print(rentodlistVO3.getReal_return_date() + ",");
//		System.out.print(rentodlistVO3.getOd_status() + ",");
//		System.out.print(rentodlistVO3.getOd_total_price() + ",");
//		System.out.print(rentodlistVO3.getRent_name() + ",");
//		System.out.print(rentodlistVO3.getRent_phone() + ",");
//		System.out.println("---------------------");
//
//		// 查詢
//		List<RentOdListVO> list = dao.getAll();
//		for (RentOdListVO aEqu : list) {
//			System.out.print(aEqu.getRent_odnum() + ",");
//			System.out.print(aEqu.getMember_id() + ",");
//			System.out.print(aEqu.getOdlist_createdate() + ",");
//			System.out.print(aEqu.getRent_payment() + ",");
//			System.out.print(aEqu.getRsved_rent_date() + ",");
//			System.out.print(aEqu.getReal_rent_date() + ",");
//			System.out.print(aEqu.getEx_return_date() + ",");
//			System.out.print(aEqu.getReal_return_date() + ",");
//			System.out.print(aEqu.getOd_status() + ",");
//			System.out.print(aEqu.getOd_total_price() + ",");
//			System.out.print(aEqu.getRent_name() + ",");
//			System.out.print(aEqu.getRent_phone() + ",");
//			System.out.println();
//		}
		
		// 查詢
		List<RentOdListVO> list = dao.findByMemOrder("A001");
		for (RentOdListVO aEqu : list) {
			System.out.print(aEqu.getRent_odnum() + ",");
			System.out.print(aEqu.getMember_id() + ",");
			System.out.print(aEqu.getOdlist_createdate() + ",");
			System.out.print(aEqu.getRent_payment() + ",");
			System.out.print(aEqu.getRsved_rent_date() + ",");
			System.out.print(aEqu.getReal_rent_date() + ",");
			System.out.print(aEqu.getEx_return_date() + ",");
			System.out.print(aEqu.getReal_return_date() + ",");
			System.out.print(aEqu.getOd_status() + ",");
			System.out.print(aEqu.getOd_total_price() + ",");
			System.out.print(aEqu.getRent_name() + ",");
			System.out.print(aEqu.getRent_phone() + ",");
			System.out.println();
			}
	}
}
