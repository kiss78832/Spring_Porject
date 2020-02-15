package com.ati_report.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;



public class Ati_reportJDBCDAO implements Ati_reportDAO_interface{

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TEST1";
	String passwd = "TEST1";

	private static final String INSERT_STMT = 
		"INSERT INTO ati_report (report_id,article_id,sf_id,member_id,report_status,report_time)"
		+ " VALUES ('AT'||LPAD(to_char(SQU_APPLICATION.NEXTVAL),6,'0'), ?, ?, ?, ?,CURRENT_TIMESTAMP)";
	private static final String GET_ALL_STMT = 
		"SELECT report_id,article_id,sf_id,member_id,report_status,to_char(report_time,'yyyy-mm-dd hh:mm:ss') report_time FROM ati_report order by report_id";
	private static final String GET_ONE_STMT = 
		"SELECT report_id,article_id,sf_id,member_id,report_status,report_time "
		+ "FROM ati_report where article_id = ?";
	private static final String DELETE = 
		"DELETE FROM ati_report where ARTICLE_ID = ?";
	private static final String UPDATE = 
		"UPDATE ati_report set article_id=?,sf_id=?,member_id=?,report_status=?,report_time=CURRENT_TIMESTAMP where report_id = ?";

	

	
	@Override
	public void insert(Ati_reportVO Ati_reportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			

			pstmt.setString(1, Ati_reportVO.getArticle_id());
			pstmt.setString(2, Ati_reportVO.getSf_id());
			pstmt.setString(3, Ati_reportVO.getMember_id());
			pstmt.setInt(4, Ati_reportVO.getReport_status());
			
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void update(Ati_reportVO ati_reportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
		
			pstmt.setString(1, ati_reportVO.getArticle_id());
			pstmt.setString(2, ati_reportVO.getSf_id());
			pstmt.setString(3, ati_reportVO.getMember_id());
			pstmt.setInt(4, ati_reportVO.getReport_status());
			pstmt.setString(5, ati_reportVO.getReport_id());
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void delete(String article_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, article_id);

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
	public Ati_reportVO findByPrimaryKey(String article_id) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		Ati_reportVO ati_reportVO = null;
		ResultSet rs = null;


		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			
			pstmt.setString(1,article_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				ati_reportVO = new Ati_reportVO();
				ati_reportVO.setReport_id(rs.getString("report_id"));
				ati_reportVO.setArticle_id(rs.getString("article_id"));
				ati_reportVO.setSf_id(rs.getString("sf_id"));
				ati_reportVO.setMember_id(rs.getString("member_id"));
				ati_reportVO.setReport_status(rs.getInt("report_status"));
				ati_reportVO.setReport_time(rs.getTimestamp("report_time"));
				
				
			}
			

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return ati_reportVO;
		
	}

	@Override
	public List<Ati_reportVO> getAll() {
		List<Ati_reportVO> list = new ArrayList<Ati_reportVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		Ati_reportVO ati_reportVO = null;
		ResultSet rs = null;


		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ati_reportVO = new Ati_reportVO();
				ati_reportVO.setReport_id(rs.getString("report_id"));
				ati_reportVO.setArticle_id(rs.getString("article_id"));
				ati_reportVO.setSf_id(rs.getString("sf_id"));
				ati_reportVO.setMember_id(rs.getString("member_id"));
				ati_reportVO.setReport_status(rs.getInt("report_status"));
				ati_reportVO.setReport_time(rs.getTimestamp("report_time"));
				list.add(ati_reportVO);
			}
			

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return list;
		
	}
	
	
	public static void main(String[] args) {

		Ati_reportJDBCDAO dao = new Ati_reportJDBCDAO();

		// 新增
//		Ati_reportVO ariVO = new Ati_reportVO();
//		ariVO.setArticle_id("AR000002");
//		ariVO.setSf_id("S000001");
//		ariVO.setMember_id("A001");
//		ariVO.setReport_status(2);
//		dao.insert(ariVO);


//		
//		// 修改
//		Ati_reportVO ariVO = new Ati_reportVO();
//		ariVO.setReport_id("R000002");
//		ariVO.setSf_id("S000001");
//		ariVO.setMember_id("A001");
//		ariVO.setReport_status(19);
//		ariVO.setReport_time(java.sql.Timestamp.valueOf("2010-08-22 10:12:30"));
//		ariVO.setArticle_id("AR000002");
//		dao.update(ariVO);
		
		
		
//		// 刪除
//		dao.delete("AR000011");

		
		
//		// 查詢  
		Ati_reportVO ariVO = dao.findByPrimaryKey("AR000001");
		System.out.print(ariVO.getReport_id() + ",");
		System.out.print(ariVO.getArticle_id() + ",");
		System.out.print(ariVO.getSf_id() + ",");
		System.out.print(ariVO.getMember_id() + ",");
		System.out.print(ariVO.getReport_status() + ",");
		System.out.println(ariVO.getReport_time());
		System.out.print("---------------------");


		
		
		
//		// 查詢  
//		List<Ati_reportVO> list = dao.getAll();
//		for (Ati_reportVO ariVO : list) {
//			System.out.print(ariVO.getReport_id() + ",");
//			System.out.print(ariVO.getArticle_id() + ",");
//			System.out.print(ariVO.getSf_id() + ",");
//			System.out.print(ariVO.getMember_id() + ",");
//			System.out.print(ariVO.getReport_status() + ",");
//			System.out.print(ariVO.getReport_time().toString() + ",");
//			System.out.println();
//		}
	}
}