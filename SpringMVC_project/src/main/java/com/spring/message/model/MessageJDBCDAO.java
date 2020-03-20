package com.spring.message.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;



public class MessageJDBCDAO implements MessageDAO_interface{
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TEST1";
	String passwd = "TEST1";

	private static final String INSERT_STMT = 
		"INSERT INTO Message(message_id,member_id,article_id,msg_content,msg_time)"
		+ " VALUES ('ME'||LPAD(to_char(SQU_APPLICATION.NEXTVAL),6,'0'), ?, ?, ?,CURRENT_TIMESTAMP)";
	private static final String GET_ALL_STMT = 
		"SELECT message_id,member_id,article_id,msg_content,msg_time "
		+ "FROM Message order by message_id";
	private static final String GET_ONE_STMT = 
		"SELECT message_id,member_id,article_id,msg_content,msg_time FROM Message where message_id = ?";
	private static final String DELETE = 
		"DELETE FROM ati_report where message_id = ?";
	private static final String UPDATE = 
		"UPDATE Message set member_id=?,article_id=?,msg_content=?,msg_time=CURRENT_TIMESTAMP where message_id = ?";
	private static final String GET_ALL_MSG = 
			"SELECT message_id,member_id,article_id,msg_content,msg_time FROM Message where article_id= ?  order by msg_time";

	
	@Override
	public void insert(MessageVO msgVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			

			pstmt.setString(1, msgVO.getMember_id());
			pstmt.setString(2, msgVO.getArticle_id());
			pstmt.setString(3, msgVO.getMsg_content());
			
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
	public void update(MessageVO msgVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
		
			pstmt.setString(4, msgVO.getMessage_id());
			pstmt.setString(1, msgVO.getMember_id());
			pstmt.setString(2, msgVO.getArticle_id());
			pstmt.setString(3, msgVO.getMsg_content());
				
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
	public void delete(String message_num) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, message_num);

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
	public MessageVO findByPrimaryKey(String message_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		MessageVO messageVO = null;
		ResultSet rs = null;


		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			
			pstmt.setString(1,message_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				messageVO = new MessageVO();
				messageVO.setMessage_id(rs.getString("message_id"));
				messageVO.setMember_id(rs.getString("member_id"));
				messageVO.setArticle_id(rs.getString("article_id"));
				messageVO.setMsg_content(rs.getString("msg_content"));
				messageVO.setMsg_time(rs.getTimestamp("msg_time"));
				
				
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
		return messageVO;

	}
	
	
	@Override
	public List<MessageVO> getAll() {
		
		List<MessageVO> list = new ArrayList<MessageVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		MessageVO messageVO = null;
		ResultSet rs = null;


		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				messageVO = new MessageVO();
				messageVO.setMessage_id(rs.getString("message_id"));
				messageVO.setMember_id(rs.getString("member_id"));
				messageVO.setArticle_id(rs.getString("article_id"));
				messageVO.setMsg_content(rs.getString("msg_content"));
				messageVO.setMsg_time(rs.getTimestamp("msg_time"));
				list.add(messageVO);
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
	
	
	@Override
	public List<MessageVO> getMsg(String member_id) {
		List<MessageVO> list = new ArrayList<MessageVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		MessageVO messageVO = null;
		ResultSet rs = null;


		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_MSG);
			pstmt.setString(1,member_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				messageVO = new MessageVO();
				messageVO.setMessage_id(rs.getString("message_id"));
				messageVO.setMember_id(rs.getString("member_id"));
				messageVO.setArticle_id(rs.getString("article_id"));
				messageVO.setMsg_content(rs.getString("msg_content"));
				messageVO.setMsg_time(rs.getTimestamp("msg_time"));
				list.add(messageVO);
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

		MessageJDBCDAO dao = new MessageJDBCDAO();

		// 新增
//		MessageVO magVO = new MessageVO();
//		magVO.setMember_id("A001");
//		magVO.setArticle_id("AR000001");
//		magVO.setMsg_content("你老師55卡好");
//		dao.insert(magVO);
	
//		
//		// 修改
//		MessageVO magVO = new MessageVO();
//		magVO.setMessage_id("ME000024");
//		magVO.setMember_id("A001");
//		magVO.setArticle_id("AR000001");
//		magVO.setMsg_content("好好好33");
//		magVO.setMsg_time(java.sql.Timestamp.valueOf("2010-06-20 10:12:30"));
//		dao.update(magVO);
		
		
		
//		// 刪除
//		dao.delete(7014);
//
		
		
//		// 查詢  
//		MessageVO magVO = dao.findByPrimaryKey("ME000002");
//		System.out.print(magVO.getMessage_id() + ",");
//		System.out.print(magVO.getMember_id() + ",");
//		System.out.print(magVO.getArticle_id() + ",");
//		System.out.print(magVO.getMember_id() + ",");
//		System.out.print(magVO.getMsg_content() + ",");
//		System.out.println(magVO.getMsg_time());
//		System.out.print("---------------------");

		
//		// 查詢  抓到資料確是空
		List<MessageVO> list = dao.getMsg("A001");
		for (MessageVO magVO : list) {
			System.out.print(magVO.getMessage_id() + ",");
			System.out.print(magVO.getMember_id() + ",");
			System.out.print(magVO.getArticle_id() + ",");
			System.out.print(magVO.getMember_id() + ",");
			System.out.print(magVO.getMsg_content() + ",");
			System.out.println(magVO.getMsg_time());
			System.out.println();
		}
	}


	
}