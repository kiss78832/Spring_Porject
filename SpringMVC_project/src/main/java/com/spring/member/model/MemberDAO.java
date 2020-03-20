package com.spring.member.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class MemberDAO implements MemberDAO_interface {
	

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
	
	private static final String INSERT_STMT = 
			"INSERT INTO member (member_id,password,m_name,gender,birthday,cellphone,"
			+ "m_email,m_photo,validation,adventure_point,outdoor_exp,"
			+ "back_img,nick_name,raiders_rate) VALUES (?, ?, ?, ?, ?, ?, ?,?,?, ?, ?, ?, ?,?)";
		private static final String GET_ALL_STMT = 
			"SELECT member_id,password,m_name,gender,birthday,cellphone,m_email,m_photo,validation,"
			+ "registered,adventure_point,outdoor_exp,back_img,nick_name,raiders_rate FROM member order by member_id";
	
		private static final String GET_ONE_STMT = 
			"SELECT member_id,password,m_name,gender,birthday,cellphone,m_email,m_photo,validation"
			+ ",registered,adventure_point,outdoor_exp,back_img,nick_name,raiders_rate FROM member where member_id = ?";
		
		private static final String DELETE = 
			"DELETE FROM member where member_id = ?";
		
		private static final String UPDATE = 
			"UPDATE member set password=?,m_name=?,gender=?,birthday=?,cellphone=?,m_email=?,m_photo=?,"
			+ "validation=?,adventure_point=?,outdoor_exp=?,back_img=?,nick_name=?,"
			+ "raiders_rate=? where member_id = ?";
		
		/*新增指令*/
		
		/*會員資料更新*/
		private static final String UPDATE_MEMBER= 
				"UPDATE member set m_name=?,password=?,gender=?,"
				+ "birthday=?,cellphone=?,m_email=? where member_id = ?";
		
		/*會員註冊*/
		private static final String INSERT_MEMBER = 
				"INSERT INTO member (member_id,password,m_name,m_email,validation) VALUES (?, ?, ?, ?, ?)";
		
		/*會員名片更新*/
		private static final String CARD_UPDATE = 
				"UPDATE member set nick_name=?,outdoor_exp=?,"
				+ "m_photo=?,back_img=? where member_id=?";
		
		/*找會員ID是否存在*/
		private static final String COMPARE ="SELECT member_id FROM MEMBER WHERE member_id =?";
		
		/*判斷帳密是否正確*/
		private static final String FIND_BY_ID_PASWD = 
				"SELECT * FROM member WHERE member_id = ? AND password = ?";
		/*查詢會員總人數*/
		private static final String MEMBERCOUNT =
				"SELECT COUNT(member_id) FROM MEMBER";
		
		
		/*忘記密碼*/
		private static final String FORGETPASS = 
				"SELECT m_email,password FROM member WHERE member_id = ?";
		
		@Override
		public String[] forgetPassword(String member_id, String m_email) {
			Connection con = null;
			PreparedStatement pstmt =null;
			ResultSet rs = null;
			String[] data = new  String[2]; 
			boolean result = false;
			
			String db_pass = null;
			String db_email = null;
			
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(FORGETPASS);
				
				pstmt.setString(1, member_id);
				
				rs = pstmt.executeQuery();

				if(rs.next()) {
					db_pass = rs.getString("password");
					db_email = rs.getString("m_email");
				}

				
				if(m_email.equals(db_email)) {
					result = true;
					data[0] = "true";
					data[1] = db_pass;
				}else {
					result = false;
					data[0] = "false";
					data[1] = "reject";
				}
				
			} catch(SQLException e) {
				throw new RuntimeException("a DB error occured "+e.getMessage());
			}finally {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			return data;
		}
		
		@Override
		public Integer memberCount() {
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null ;
			Integer MEMBERSUM = null;
			
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(MEMBERCOUNT);
				rs = pstmt.executeQuery();
				rs.next();
				MEMBERSUM = rs.getInt(1);
				
			} catch (SQLException e) {
				throw new RuntimeException("A DB error occured . "+e.getMessage());
			} finally {
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
			
			return MEMBERSUM;
		}

		
		
		
		@Override
		public boolean isMember(String member_id, String password) {
			Connection con = null;
			PreparedStatement pstmt = null;
			boolean isMember = false;
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(FIND_BY_ID_PASWD);
				pstmt.setString(1, member_id);
				pstmt.setString(2, password);
				ResultSet rs = pstmt.executeQuery();
				isMember = rs.next();
				
			} catch (SQLException e) {
				throw new RuntimeException("A DB error occured . "+e.getMessage());
			} finally {
				try {
					if (pstmt != null) {
						pstmt.close();
					}
					if (con != null) {
						con.close();	
					}
				}catch(SQLException se) {
					se.printStackTrace();
				}
			}
			return isMember;
		}


			
		
	@Override
		public boolean compareWith(String member_id) {
			Connection con= null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			boolean exist;
			
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(COMPARE);
				
				pstmt.setString(1, member_id);
				
				rs = pstmt.executeQuery();
				
				exist = rs.next();
				rs.close();
				
			} catch(SQLException e ) {
				throw new RuntimeException("A DB error occured . "+e.getMessage());
			} finally {
				
				if(rs != null) {
					try {
						rs.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if(pstmt!=null) {
					try {
						pstmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if(con!=null) {
					try {
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
			return exist;
		}

	@Override
		public void cardUpdate(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(CARD_UPDATE);

			pstmt.setString(1, memberVO.getNick_name());
			pstmt.setString(2, memberVO.getOutdoor_exp());
			pstmt.setBytes(3, memberVO.getM_photo());
			pstmt.setBytes(4, memberVO.getBack_img());
			pstmt.setString(5, memberVO.getMember_id());
			
			pstmt.executeUpdate();

			
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
		public boolean signUp(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean isAdded = false;

		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_MEMBER);
			
	
			
			pstmt.setString(1, memberVO.getMember_id());
			pstmt.setString(2, memberVO.getPassword());
			pstmt.setString(3, memberVO.getM_name());
			pstmt.setString(4, memberVO.getM_email());
			pstmt.setInt(5, 1);
			
			
			int count = pstmt.executeUpdate();
			
			
			isAdded = count > 0 ? true : false;

			
		} catch (SQLException e) {
			e.printStackTrace();
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
		return isAdded;
	}

	@Override
		public void updateData(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_MEMBER);

			pstmt.setString(1, memberVO.getM_name());
			pstmt.setString(2, memberVO.getPassword());
			pstmt.setInt(3, memberVO.getGender());
			pstmt.setDate(4, memberVO.getBirthday());
			pstmt.setString(5, memberVO.getCellphone());
			pstmt.setString(6, memberVO.getM_email());
			pstmt.setString(7, memberVO.getMember_id());
			
			pstmt.executeUpdate();

			
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
	public void insert(MemberVO memberVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
	
			
			pstmt.setString(1, memberVO.getMember_id());
			pstmt.setString(2, memberVO.getPassword());
			pstmt.setString(3, memberVO.getM_name());
			pstmt.setInt(4, memberVO.getGender());
			pstmt.setDate(5, memberVO.getBirthday());
			pstmt.setString(6, memberVO.getCellphone());
			pstmt.setString(7, memberVO.getM_email());
			pstmt.setBytes(8, memberVO.getM_photo());
			pstmt.setInt(9, memberVO.getValidation());
			
			pstmt.setInt(10, memberVO.getAdventure_point());
			pstmt.setString(11, memberVO.getOutdoor_exp());
			pstmt.setBytes(12, memberVO.getBack_img());
			pstmt.setString(13, memberVO.getNick_name());
			pstmt.setInt(14, memberVO.getRaiders_rate());
			
			pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
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
	public void update(MemberVO memberVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, memberVO.getPassword());
			pstmt.setString(2, memberVO.getM_name());
			pstmt.setInt(3, memberVO.getGender());
			pstmt.setDate(4, memberVO.getBirthday());
			pstmt.setString(5, memberVO.getCellphone());
			pstmt.setString(6, memberVO.getM_email());
			pstmt.setBytes(7, memberVO.getM_photo());
			pstmt.setInt(8, memberVO.getValidation());
			pstmt.setInt(9, memberVO.getAdventure_point());
			pstmt.setString(10, memberVO.getOutdoor_exp());
			pstmt.setBytes(11, memberVO.getBack_img());
			pstmt.setString(12, memberVO.getNick_name());
			pstmt.setInt(13, memberVO.getRaiders_rate());
			pstmt.setString(14, memberVO.getMember_id());


			pstmt.executeUpdate();

			
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
	public void delete(String member_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, member_id);

			pstmt.executeUpdate();

		
		}catch (SQLException se) {
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
	public MemberVO findByPrimaryKey(String member_id) {
		
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, member_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				memberVO = new MemberVO();
				memberVO.setMember_id(rs.getString("member_id"));
				memberVO.setPassword(rs.getString("password"));
				memberVO.setM_name(rs.getString("m_name"));
				memberVO.setGender(rs.getInt("gender"));
				memberVO.setBirthday(rs.getDate("birthday"));
				memberVO.setCellphone(rs.getString("cellphone"));
				memberVO.setM_email(rs.getString("m_email"));
				memberVO.setM_photo(rs.getBytes("m_photo"));
				memberVO.setValidation(rs.getInt("validation"));
				memberVO.setRegistered(rs.getDate("registered"));
				memberVO.setAdventure_point(rs.getInt("adventure_point"));
				memberVO.setOutdoor_exp(rs.getString("outdoor_exp"));
				memberVO.setBack_img(rs.getBytes("back_img"));
				memberVO.setNick_name(rs.getString("nick_name"));
				memberVO.setRaiders_rate(rs.getInt("raiders_rate"));
			
				
			}


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
		return memberVO;
	}

	@Override
	public List<MemberVO> getAll() {
		
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO memberVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				memberVO = new MemberVO();
				memberVO.setMember_id(rs.getString("member_id"));
				memberVO.setPassword(rs.getString("password"));
				memberVO.setM_name(rs.getString("m_name"));
				memberVO.setGender(rs.getInt("gender"));
				memberVO.setBirthday(rs.getDate("birthday"));
				memberVO.setCellphone(rs.getString("cellphone"));
				memberVO.setM_email(rs.getString("m_email"));
				memberVO.setM_photo(rs.getBytes("m_photo"));
				memberVO.setValidation(rs.getInt("validation"));
				memberVO.setRegistered(rs.getDate("registered"));
				memberVO.setAdventure_point(rs.getInt("adventure_point"));
				memberVO.setOutdoor_exp(rs.getString("outdoor_exp"));
				memberVO.setBack_img(rs.getBytes("back_img"));
				memberVO.setNick_name(rs.getString("nick_name"));
				memberVO.setRaiders_rate(rs.getInt("raiders_rate"));
				list.add(memberVO); // Store the row in the list
			}


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
		
		MemberJDBCDAO dao = new MemberJDBCDAO();
		System.out.println(dao.memberCount());
		//新增
		MemberVO memberVO1 = new MemberVO();
		
		
//		memberVO1.setMember_id("A005");
//		memberVO1.setPassword("123456");
//		memberVO1.setM_name("貝爾");
//		memberVO1.setGender(1);
//		memberVO1.setBirthday(java.sql.Date.valueOf("2002-01-01"));
//		memberVO1.setCellphone("0970516983");
//		memberVO1.setM_email("A005@gmail.com");
//		memberVO1.setM_photo(null);
//		memberVO1.setValidation(1);
//		memberVO1.setRegistered(java.sql.Date.valueOf("2002-01-01"));
//		memberVO1.setAdventure_point(50);
//		memberVO1.setOutdoor_exp("運動讚");
//		memberVO1.setBack_img(null);
//		memberVO1.setNick_name("食物鏈頂端");
//		memberVO1.setRaiders_rate(90);
//		
//		dao.insert(memberVO1);
//		
//		System.out.println("新增成功");
//		
//		//修改
//		MemberVO memberVO2 = new MemberVO();
//		
//		
//		
//		memberVO2.setPassword("123456");
//		memberVO2.setM_name("羅南");
//		memberVO2.setGender(1);
//		memberVO2.setBirthday(java.sql.Date.valueOf("2002-01-01"));
//		memberVO2.setCellphone("0970516983");
//		memberVO2.setM_email("A005@gmail.com");
//		memberVO2.setM_photo(null);
//		memberVO2.setValidation(1);
//		memberVO2.setAdventure_point(50);
//		memberVO2.setOutdoor_exp("運動讚");
//		memberVO2.setBack_img(null);
//		memberVO2.setNick_name("食物鏈頂端");
//		memberVO2.setRaiders_rate(90);
//		memberVO2.setMember_id("A005");
//		
//		dao.update(memberVO2);
//		
//		System.out.println("修改成功");
//		
//		
//		//刪除
//		dao.delete("A003");
//		
//		
//		//查詢
//		
//		MemberVO memberVO3 = dao.findByPrimaryKey("A001");
//		System.out.println(memberVO3.getMember_id());
//		System.out.println(memberVO3.getPassword());
//		System.out.println(memberVO3.getM_name());
//		System.out.println(memberVO3.getGender());
//		System.out.println(memberVO3.getBirthday());
//		System.out.println(memberVO3.getCellphone());
//		System.out.println(memberVO3.getM_email());
//		System.out.println(memberVO3.getM_photo());
//		System.out.println(memberVO3.getValidation());
//		System.out.println(memberVO3.getRegistered());
//		System.out.println(memberVO3.getAdventure_point());
//		System.out.println(memberVO3.getOutdoor_exp());
//		System.out.println(memberVO3.getBack_img());
//		System.out.println(memberVO3.getNick_name());
//		System.out.println(memberVO3.getRaiders_rate());
//		
//		//查詢
//		
//		List<MemberVO> list = dao.getAll();
//		for (MemberVO aMember : list) {
//			System.out.println(aMember.getMember_id());
//			System.out.println(aMember.getPassword());
//			System.out.println(aMember.getM_name());
//			System.out.println(aMember.getGender());
//			System.out.println(aMember.getBirthday());
//			System.out.println(aMember.getCellphone());
//			System.out.println(aMember.getM_email());
//			System.out.println(aMember.getM_photo());
//			System.out.println(aMember.getValidation());
//			System.out.println(aMember.getRegistered());
//			System.out.println(aMember.getAdventure_point());
//			System.out.println(aMember.getOutdoor_exp());
//			System.out.println(aMember.getBack_img());
//			System.out.println(aMember.getNick_name());
//			System.out.println(aMember.getRaiders_rate());
//			
//			System.out.println();
//		}
		
	}
	

}
