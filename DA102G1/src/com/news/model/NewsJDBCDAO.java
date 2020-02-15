package com.news.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;


public class NewsJDBCDAO implements NewsDAO{
	
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "TEST1";
	private static final String PASSWORD = "TEST1";
	
	private static final String INSERT =
			"INSERT INTO NEWS(NEWS_ID,SF_ID,NEWS_IMAGE,NEWS_CONTENT,NEWS_TIME,NEWS_SRC)" + 
			"VALUES (?,?,?,?,?,?)";
	private static final String UPDATE = 
			"UPDATE NEWS SET SF_ID=?, NEWS_IMAGE=?, NEWS_CONTENT=?, NEWS_TIME=?, NEWS_SRC=? WHERE NEWS_ID=?";
	private static final String DELETE = 
			"DELETE FROM NEWS WHERE NEWS_ID=?";
	private static final String GET_ONE_PSTMT = 
			"SELECT * FROM NEWS WHERE NEWS_ID=?";
	private static final String GET_ALL = "SELECT * FROM NEWS";

	public static void main(String[] args) {
		NewsJDBCDAO dao = new NewsJDBCDAO();
		Date date = new Date();
		
		// 新增
//		NewsVO newsVO1 = new NewsVO();
//		newsVO1.setNews_id("N002");
//		newsVO1.setSf_id("S002");
//		newsVO1.setNews_image();
//		newsVO1.setNews_content("千年難得一見大雪景");
//		newsVO1.setNews_time(new Timestamp(date.getTime()));
//		newsVO1.setNews_src("TaipeiNews");
//		dao.insert(newsVO1);
		
		// 修改
//		NewsVO newsVO2 = new NewsVO();
//		newsVO2.setNews_id("N002");
//		newsVO2.setSf_id("S002");
//		newsVO2.setNews_image();
//		newsVO2.setNews_content("流星雨近在咫尺");
//		newsVO2.setNews_time(new Timestamp(date.getTime()));
//		newsVO2.setNews_src("TaiwanNumberOne");
//		dao.update(newsVO2);
		
		// 刪除
//		dao.delete("N002");
		
		// 查詢
//		NewsVO newsVO3 = dao.findByPrimaryKey("N002");
//		System.out.print(newsVO3.getNews_id() + ",");
//		System.out.print(newsVO3.getSf_id() + ",");
//		System.out.print(newsVO3.getNews_image() + ",");
//		System.out.print(newsVO3.getNews_content()+",");
//		System.out.print(newsVO3.getNews_time() + ",");
//		System.out.print(newsVO3.getNews_src());
//		System.out.println("---------------------");
		
		// 查詢全部
		List<NewsVO> list = dao.getAll();
		for (NewsVO news : list) {
			System.out.print(news.getNews_id() + ",");
			System.out.print(news.getSf_id() + ",");
			System.out.print(news.getNews_image() + ",");
			System.out.print(news.getNews_content()+ ",");
			System.out.print(news.getNews_time() + ",");
			System.out.print(news.getNews_src());
			System.out.println("---------------------");
			System.out.println();
		}

	}

	@Override
	public void insert(NewsVO newsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT);

			pstmt.setString(1, newsVO.getNews_id());
			pstmt.setString(2, newsVO.getSf_id());
			pstmt.setBytes(3, newsVO.getNews_image());
			pstmt.setString(4, newsVO.getNews_content());
			pstmt.setTimestamp(5, newsVO.getNews_time());
			pstmt.setString(6, newsVO.getNews_src());


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
	public void update(NewsVO newsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE);


			pstmt.setString(1, newsVO.getSf_id());
			pstmt.setBytes(2, newsVO.getNews_image());
			pstmt.setString(3, newsVO.getNews_content());
			pstmt.setTimestamp(4, newsVO.getNews_time());
			pstmt.setString(5, newsVO.getNews_src());
			pstmt.setString(6, newsVO.getNews_id());

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
	public void delete(String news_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, news_id);

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
	public NewsVO findByPrimaryKey(String news_id) {
		NewsVO newsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_PSTMT);
			pstmt.setString(1, news_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				newsVO = new NewsVO();
				newsVO.setNews_id(news_id);
				newsVO.setSf_id(rs.getString("SF_ID"));
				newsVO.setNews_image(rs.getBytes("NEWS_IMAGE"));
				newsVO.setNews_content(rs.getString("NEWS_CONTENT"));
				newsVO.setNews_time(rs.getTimestamp("NEWS_TIME"));
				newsVO.setNews_src(rs.getString("NEWS_SRC"));
				
				
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
		return newsVO;
	}

	@Override
	public List<NewsVO> getAll() {
		List<NewsVO> newsVOList = new ArrayList<>();
		NewsVO newsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				newsVO = new NewsVO();
				newsVO.setNews_id(rs.getString("NEWS_ID"));
				newsVO.setSf_id(rs.getString("SF_ID"));
				newsVO.setNews_image(rs.getBytes("NEWS_IMAGE"));
				newsVO.setNews_content(rs.getString("NEWS_CONTENT"));
				newsVO.setNews_time(rs.getTimestamp("NEWS_TIME"));
				newsVO.setNews_src(rs.getString("NEWS_SRC"));
				newsVOList.add(newsVO);
			}

		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("Couldn't load database driver. " + ce.getMessage());
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
		return newsVOList;
	}

	@Override
	public List<NewsVO> getAll(Map<String, String[]> map) {
		// TODO Auto-generated method stub
		return null;
	}

}
