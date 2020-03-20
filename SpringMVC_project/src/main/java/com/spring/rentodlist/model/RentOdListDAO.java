package com.spring.rentodlist.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.spring.dailytypetotal.model.DailyTotalDAO;
import com.spring.dailytypetotal.model.DailyTotalVO;
import com.spring.rentoddetail.model.RentOdDetailDAO;
import com.spring.rentoddetail.model.RentOdDetailVO;

public class RentOdListDAO implements RentOdListDAO_interface {

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

			con = ds.getConnection();
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
	public void update(RentOdListVO rentodlistVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
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
	public void delete(String rent_odnum) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, rent_odnum);

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public RentOdListVO findByPrimaryKey(String rent_odnum) {

		RentOdListVO rentodlistVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
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

			con = ds.getConnection();
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
	public List<RentOdListVO> findByMemOrder(String member_id) {
		List<RentOdListVO> list = new ArrayList<RentOdListVO>();
		RentOdListVO rentodlistVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
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
	public void insertWithOrd(RentOdListVO rentodlistVO, List<RentOdDetailVO> list) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			
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
			
			
			

			String next_rent_odnum = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_rent_odnum = rs.getString(1);
				System.out.println("自增主鍵值= " + next_rent_odnum +"(剛新增成功的訂單編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			// 再同時新增員工
			RentOdDetailDAO dao = new RentOdDetailDAO();
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
	public String  insertWithOrdAndDaily(RentOdListVO rentodlistVO, List<RentOdDetailVO> list ,List<DailyTotalVO> list1)  {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			
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
			
			
			

			String next_rent_odnum = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_rent_odnum = rs.getString(1);
				System.out.println("自增主鍵值= " + next_rent_odnum +"(剛新增成功的訂單編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			// 再同時新增員工
			RentOdDetailDAO dao = new RentOdDetailDAO();
			System.out.println("list.size()-A="+list.size());
			for (RentOdDetailVO aEmp : list) {
				aEmp.setRent_odnum(new String(next_rent_odnum)) ;
				dao.insert2(aEmp,con);
			}
			
			DailyTotalDAO dao1 = new DailyTotalDAO();
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
	public void updateWithOrdAndDaily(RentOdListVO rentodlistVO, List<RentOdDetailVO> list ,List<DailyTotalVO> list1)  {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			
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
			
			System.out.println("部門新增成功");

			// 再同時新增員工
			RentOdDetailDAO dao = new RentOdDetailDAO();

			for (RentOdDetailVO aEmp : list) {
				dao.update2(aEmp,con);
				System.out.println("員工新增成功");
			}
			
			DailyTotalDAO dao1 = new DailyTotalDAO();
			for (DailyTotalVO aDai : list1) {
				dao1.update2(aDai,con);
				System.out.println("每日裝備新增成功");
			}


			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			
			con.setAutoCommit(true);
			// Handle any driver errors
			
			System.out.println("全部新增成功");
			
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
	
}