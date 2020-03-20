package com.spring.dailyBed.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class DailyBedDAO implements DailyBedDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB2");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = 
			"INSERT INTO  DAILYBED(dailybed_id,location_id ,remaining_total,provided_total,dailyBed_date) VALUES ('DB'||LPAD(to_char(SQU_DAILYBED.NEXTVAL), 8, '0') ,?, ?, ?, ?)";
	private static final String GET_ALL_STMT =
			"SELECT dailybed_id,location_id ,remaining_total,provided_total,dailyBed_date FROM DAILYBED order by dailybed_id";
	private static final String GET_ONE_STMT = 
			"SELECT dailybed_id,location_id ,remaining_total,provided_total,dailyBed_date FROM DAILYBED where dailybed_id = ?";
	private static final String UPDATE = 
			"UPDATE DAILYBED set remaining_total=?,provided_total=? where dailyBed_id = ?";
	private static final String GET_ALL_STMTBYLOC_ID = "SELECT dailybed_id,location_id ,remaining_total,provided_total,dailyBed_date FROM DAILYBED where location_id = ? order by dailybed_id";
	private static final String GET_ALL_STMTBYDAILYBED_DATE = "SELECT dailybed_id,location_id ,remaining_total,provided_total,dailyBed_date FROM DAILYBED where dailyBed_date = ? order by dailybed_id";
	private static final String GET_ALL_STMTBYDATE_AND_LOCATION = "SELECT dailybed_id,location_id ,remaining_total,provided_total,dailyBed_date FROM DAILYBED where location_id = ? AND to_char(dailyBed_date,'MM') = ? AND to_char(dailyBed_date,'YYYY') = ? order by dailybed_id";
	private static final String GET_ALL_STMTBYDATE = "SELECT dailybed_id,location_id ,remaining_total,provided_total,dailyBed_date FROM DAILYBED where to_char(dailyBed_date,'DD') = ? AND to_char(dailyBed_date,'MM') = ? AND to_char(dailyBed_date,'YYYY') = ? order by dailybed_id";
	private static final String GET_ONE_STMTBYDATE = "SELECT dailybed_id,location_id ,remaining_total,provided_total,dailyBed_date FROM DAILYBED where to_char(dailyBed_date,'DD') = ? AND to_char(dailyBed_date,'MM') = ? AND to_char(dailyBed_date,'YYYY') = ? AND LOCATION_ID = ? order by dailybed_id";
	private static final String GET_ALL_STMTBYFUllDATE_AND_LOCATION = "SELECT dailybed_id,location_id ,remaining_total,provided_total,dailyBed_date FROM DAILYBED where location_id = ? AND dailyBed_date = ? order by dailybed_id";
	
	private static final String GET_ALL_DIFFERENT_LOCATION_ID = "SELECT DISTINCT LOCATION_ID FROM DAILYBED";
	
	public List<String> getAllDifferentLoc() {
		List<String> list = new ArrayList<String>();
		String location_id;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_DIFFERENT_LOCATION_ID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				location_id = rs.getString("location_id");

				list.add(location_id);
			}

		} catch (SQLException se) {
			se.printStackTrace();
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void insert(DailyBedVO dailyBedVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, dailyBedVO.getLocation_id());
			pstmt.setInt(2, dailyBedVO.getRemaining_total());
			pstmt.setInt(3, dailyBedVO.getProvided_total());
			pstmt.setDate(4, dailyBedVO.getDailyBed_date());

			pstmt.executeUpdate();

		}  catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void update(DailyBedVO dailyBedVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			
			pstmt.setInt(1, dailyBedVO.getRemaining_total());
			pstmt.setInt(2,dailyBedVO.getProvided_total());
			pstmt.setString(3,dailyBedVO.getDailyBed_id());

			pstmt.executeUpdate();

		}  catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public DailyBedVO findByPrimaryKey(String dailyBed_id) {
		DailyBedVO dailyBedVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, dailyBed_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				dailyBedVO = new DailyBedVO();
				dailyBedVO.setDailyBed_id(rs.getString("dailyBed_id"));
				dailyBedVO.setLocation_id(rs.getString("location_id"));
				dailyBedVO.setRemaining_total(rs.getInt("remaining_total"));
				dailyBedVO.setProvided_total(rs.getInt("provided_total"));
				dailyBedVO.setDailyBed_date(rs.getDate("dailyBed_date"));

			}

		}  catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

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
		return dailyBedVO;
	}
	@Override
	public List<DailyBedVO> getAll() {
		List<DailyBedVO> list = new ArrayList<DailyBedVO>();
		DailyBedVO dailyBedVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				dailyBedVO = new DailyBedVO();
				dailyBedVO.setDailyBed_id(rs.getString("dailyBed_id"));
				dailyBedVO.setLocation_id(rs.getString("location_id"));
				dailyBedVO.setRemaining_total(rs.getInt("remaining_total"));
				dailyBedVO.setProvided_total(rs.getInt("provided_total"));
				dailyBedVO.setDailyBed_date(rs.getDate("dailyBed_date"));

				list.add(dailyBedVO);
			}

		}  catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	public List<DailyBedVO> getAllByDailyBed_date(Date dailyBed_date) {
		List<DailyBedVO> list = new ArrayList<DailyBedVO>();
		DailyBedVO dailyBedVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMTBYDAILYBED_DATE );
			pstmt.setDate(1, dailyBed_date);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				dailyBedVO = new DailyBedVO();
				dailyBedVO.setDailyBed_id(rs.getString("dailyBed_id"));
				dailyBedVO.setLocation_id(rs.getString("location_id"));
				dailyBedVO.setRemaining_total(rs.getInt("remaining_total"));
				dailyBedVO.setProvided_total(rs.getInt("provided_total"));
				dailyBedVO.setDailyBed_date(rs.getDate("dailyBed_date"));

				list.add(dailyBedVO);
			}
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	
	public List<DailyBedVO> getAllByLoc_id(String location_id) {
		List<DailyBedVO> list = new ArrayList<DailyBedVO>();
		DailyBedVO dailyBedVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMTBYLOC_ID);
			pstmt.setString(1, location_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				dailyBedVO = new DailyBedVO();
				dailyBedVO.setDailyBed_id(rs.getString("dailyBed_id"));
				dailyBedVO.setLocation_id(rs.getString("location_id"));
				dailyBedVO.setRemaining_total(rs.getInt("remaining_total"));
				dailyBedVO.setProvided_total(rs.getInt("provided_total"));
				dailyBedVO.setDailyBed_date(rs.getDate("dailyBed_date"));

				list.add(dailyBedVO);
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	
	public List<DailyBedVO> getAllByLoc_idAndDate(String location_id , int year , int month) {
		List<DailyBedVO> list = new ArrayList<DailyBedVO>();
		DailyBedVO dailyBedVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMTBYDATE_AND_LOCATION);
			pstmt.setString(1, location_id);
			pstmt.setInt(2, month);
			pstmt.setInt(3, year);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				dailyBedVO = new DailyBedVO();
				dailyBedVO.setDailyBed_id(rs.getString("dailyBed_id"));
				dailyBedVO.setLocation_id(rs.getString("location_id"));
				dailyBedVO.setRemaining_total(rs.getInt("remaining_total"));
				dailyBedVO.setProvided_total(rs.getInt("provided_total"));
				dailyBedVO.setDailyBed_date(rs.getDate("dailyBed_date"));

				list.add(dailyBedVO);
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	
	public List<DailyBedVO> getAllByDate( int year , int month , int date) {
		List<DailyBedVO> list = new ArrayList<DailyBedVO>();
		DailyBedVO dailyBedVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMTBYDATE);
			pstmt.setInt(1, date);
			pstmt.setInt(2, month);
			pstmt.setInt(3, year);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				dailyBedVO = new DailyBedVO();
				dailyBedVO.setDailyBed_id(rs.getString("dailyBed_id"));
				dailyBedVO.setLocation_id(rs.getString("location_id"));
				dailyBedVO.setRemaining_total(rs.getInt("remaining_total"));
				dailyBedVO.setProvided_total(rs.getInt("provided_total"));
				dailyBedVO.setDailyBed_date(rs.getDate("dailyBed_date"));

				list.add(dailyBedVO);
			}
			

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	
	public DailyBedVO getLocVOByDate( int year , int month , int date ,String location_id) {
		DailyBedVO dailyBedVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMTBYDATE);
			pstmt.setInt(1, date);
			pstmt.setInt(2, month);
			pstmt.setInt(3, year);
			pstmt.setString(4, location_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				dailyBedVO = new DailyBedVO();
				dailyBedVO.setDailyBed_id(rs.getString("dailyBed_id"));
				dailyBedVO.setLocation_id(rs.getString("location_id"));
				dailyBedVO.setRemaining_total(rs.getInt("remaining_total"));
				dailyBedVO.setProvided_total(rs.getInt("provided_total"));
				dailyBedVO.setDailyBed_date(rs.getDate("dailyBed_date"));
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return dailyBedVO ;
	}
	
	public DailyBedVO getDailyBedVOByFullDate(String location_id , Date date) {
		DailyBedVO dailyBedVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMTBYFUllDATE_AND_LOCATION);
			pstmt.setString(1, location_id);
			pstmt.setDate(2, date);
			rs = pstmt.executeQuery();	
		
			while (rs.next()) {
				
				dailyBedVO = new DailyBedVO();
				dailyBedVO.setDailyBed_id(rs.getString("dailyBed_id"));
				dailyBedVO.setLocation_id(rs.getString("location_id"));	
				dailyBedVO.setRemaining_total(rs.getInt("remaining_total"));
				dailyBedVO.setProvided_total(rs.getInt("provided_total"));
				dailyBedVO.setDailyBed_date(rs.getDate("dailyBed_date"));
				
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return dailyBedVO ;
	}
	
	
	
	
	public static void main(String[]args) {
		DailyBedDAO dao = new DailyBedDAO();
		
		//insert test
//		DailyBedVO dailyBedVO1 = new DailyBedVO();
//		dailyBedVO1.setLocation_id("A0001");
//		dailyBedVO1.setRemaining_total(120);
//		dailyBedVO1.setProvided_total(0);
//		dailyBedVO1.setDailyBed_date(java.sql.Date.valueOf("2019-08-15"));
//		
//		dao.insert(dailyBedVO1);
//		System.out.println("硬啦");
		
		//update test
		
//		DailyBedVO dailyBedVO2 = new DailyBedVO();
//		dailyBedVO2.setDailyBed_id("DB00000002");
//		dailyBedVO2.setRemaining_total(100);
//		dailyBedVO2.setProvided_total(20);
//		dao.update(dailyBedVO2);
//		System.out.println("success^___^");
		
		// QUERY ONE
//		DailyBedVO dailyBedVO3 = dao.findByPrimaryKey("DB00000002");
//		System.out.println(dailyBedVO3.getDailyBed_id());
//		System.out.println(dailyBedVO3.getLocation_id());
//		System.out.println(dailyBedVO3.getRemaining_total());
//		System.out.println(dailyBedVO3.getProvided_total());
//		System.out.println(dailyBedVO3.getDailyBed_date());
		
		
		//Query all
//		List<DailyBedVO> list = dao.getAll();
//		for(DailyBedVO dailyBedVO : list) {
//			System.out.println(dailyBedVO.getDailyBed_id());
//			System.out.println(dailyBedVO.getLocation_id());
//			System.out.println(dailyBedVO.getRemaining_total());
//			System.out.println(dailyBedVO.getProvided_total());
//			System.out.println(dailyBedVO.getDailyBed_date());
//		}
		
	}

	
}
