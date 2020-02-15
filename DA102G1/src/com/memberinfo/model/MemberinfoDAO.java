package com.memberinfo.model;

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



public class MemberinfoDAO implements Memberinfo_interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB2");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String ISDERT_STMT=
			"INSERT INTO member_info (group_id, member_id, address, identity, egc_contact, egc_phone, m_name, birthday, cellphone, m_email) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE = 
			"UPDATE member_info set address=?, identity=?, egc_contact=?, egc_phone=? where group_id=? and member_id=?";
	private static final String DELETE = 
			"DELETE FROM member_info where group_id =? and member_id = ?";
	private static final String GET_ONE_STMT = 
			"SELECT * FROM member_info where group_id= ? and member_id=?";
	private static final String GET_ALL_STMT = 
			"SELECT group_id, member_id, address, identity, egc_contact, egc_phone FROM member_info order by group_id ";
	/******************************************/
	private static final String COUNT_GROUPNBP = 
			"select count(*) from member_info where group_id =?";
	private static final String MEMBERINFO_LIST = 
			"SELECT * FROM member_info order by group_id ";
	/*品傑的*/
	private static final String FIND_GROUPNAME = 
			"select * from MEMBER_INFO where group_id =?";
	/*0920*/
	private static final String ISDERT_FOR_LEADER =
			"INSERT INTO member_info (group_id, member_id) VALUES (?, ?)";
	/*0921*/
	private static final String FIND_GROUPMEMBER = 
			"select * from MEMBER_INFO where member_id =?";
	
	
	@Override   
	public  List<MemberinfoVO> find_GroupName(String group_id) {
		
		List<MemberinfoVO> list = new ArrayList<MemberinfoVO>();
		MemberinfoVO memberinfoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_GROUPNAME);
			pstmt.setString(1, group_id);
			
			rs = pstmt.executeQuery();
		

				while (rs.next()) {
					System.out.println("近來");
					memberinfoVO = new MemberinfoVO();
					memberinfoVO.setGroup_id(rs.getString("group_id"));
					memberinfoVO.setM_name(rs.getString("m_name"));
					memberinfoVO.setMember_id(rs.getString("member_id"));
					memberinfoVO.setM_email(rs.getString("m_email"));
					memberinfoVO.setCellphone(rs.getString("cellphone"));
					
					memberinfoVO.setAddress(rs.getString("address"));
					memberinfoVO.setIdentity(rs.getString("identity"));
					memberinfoVO.setEgc_contact(rs.getString("egc_contact"));
					memberinfoVO.setEgc_phone(rs.getString("egc_phone"));
					list.add(memberinfoVO);
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
	
	@Override
	public void insert(MemberinfoVO memberinfoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(ISDERT_STMT);
			
			pstmt.setString(1, memberinfoVO.getGroup_id());
			pstmt.setString(2, memberinfoVO.getMember_id());
			pstmt.setString(3, memberinfoVO.getAddress());
			pstmt.setString(4, memberinfoVO.getIdentity());
			pstmt.setString(5, memberinfoVO.getEgc_contact());
			pstmt.setString(6, memberinfoVO.getEgc_phone());
			pstmt.setString(7, memberinfoVO.getM_name());
			pstmt.setDate(8, memberinfoVO.getBirthday());
			pstmt.setString(9, memberinfoVO.getCellphone());
			pstmt.setString(10, memberinfoVO.getM_email());
			
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
	public void update(MemberinfoVO memberinfoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, memberinfoVO.getAddress());
			pstmt.setString(2, memberinfoVO.getIdentity());
			pstmt.setString(3, memberinfoVO.getEgc_contact());
			pstmt.setString(4, memberinfoVO.getEgc_phone());
			pstmt.setString(5, memberinfoVO.getGroup_id());
			pstmt.setString(6, memberinfoVO.getMember_id());
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
	public void delete(String group_id, String member_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1,group_id );
			pstmt.setString(2,member_id );

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
	public MemberinfoVO findByPrimaryKey(String group_id, String member_id) {
		MemberinfoVO memberinfoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, group_id);
			pstmt.setString(2, member_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				memberinfoVO = new MemberinfoVO();
				memberinfoVO.setGroup_id(rs.getString("group_id"));
				memberinfoVO.setMember_id(rs.getString("member_id"));
				memberinfoVO.setAddress(rs.getString("address"));
				memberinfoVO.setIdentity(rs.getString("identity"));
				memberinfoVO.setEgc_contact(rs.getString("egc_contact"));
				memberinfoVO.setEgc_phone(rs.getString("egc_phone"));
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
		return memberinfoVO;
	}

	@Override
	public List<MemberinfoVO> getAll() {
		List<MemberinfoVO> list = new ArrayList<MemberinfoVO>();
		MemberinfoVO memberinfoVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
		
				memberinfoVO = new MemberinfoVO();
				memberinfoVO.setGroup_id(rs.getString("group_id"));
				memberinfoVO.setMember_id(rs.getString("member_id"));
				memberinfoVO.setAddress(rs.getString("address"));
				memberinfoVO.setIdentity(rs.getString("identity"));
				memberinfoVO.setEgc_contact(rs.getString("egc_contact"));
				memberinfoVO.setEgc_phone(rs.getString("egc_phone"));
				list.add(memberinfoVO); 
				// Store the row in the list
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
	
	/************自訂方法*****************/
	
	@Override
	public int countByGroupId(String group_id) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int records = 0;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(COUNT_GROUPNBP);
			pstmt.setString(1, group_id);
			
			rs = pstmt.executeQuery();
			
		  while (rs.next()) {
			   records = rs.getInt(1);
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
		
		return records;
	}

	
	@Override
	public List<MemberinfoVO> memberinfoDetail(String group_id ) {
		List<MemberinfoVO> list2 = new ArrayList<MemberinfoVO>();
		MemberinfoVO memberinfoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(MEMBERINFO_LIST);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
		
				memberinfoVO = new MemberinfoVO();
				memberinfoVO.setGroup_id(rs.getString("group_id"));
				memberinfoVO.setMember_id(rs.getString("member_id"));
				memberinfoVO.setM_name(rs.getString("m_name"));
				memberinfoVO.setM_email(rs.getString("m_email"));
				memberinfoVO.setAddress(rs.getString("address"));
				memberinfoVO.setIdentity(rs.getString("identity"));
				memberinfoVO.setEgc_contact(rs.getString("egc_contact"));
				memberinfoVO.setEgc_phone(rs.getString("egc_phone"));
				list2.add(memberinfoVO); 
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
		return list2;
	}
	
	
	@Override
	public void insertForLeader(MemberinfoVO memberinfoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(ISDERT_FOR_LEADER);
			
			pstmt.setString(1, memberinfoVO.getGroup_id());
			pstmt.setString(2, memberinfoVO.getMember_id());
			
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
	public List<MemberinfoVO> find_GroupMember(String member_id) {
		List<MemberinfoVO> list3 = new ArrayList<MemberinfoVO>();
		MemberinfoVO memberinfoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_GROUPMEMBER);
			pstmt.setString(1, member_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
		
				memberinfoVO = new MemberinfoVO();
				memberinfoVO.setGroup_id(rs.getString("group_id"));
				memberinfoVO.setMember_id(rs.getString("member_id"));
				memberinfoVO.setM_name(rs.getString("m_name"));
				memberinfoVO.setM_email(rs.getString("m_email"));
				memberinfoVO.setAddress(rs.getString("address"));
				memberinfoVO.setIdentity(rs.getString("identity"));
				memberinfoVO.setEgc_contact(rs.getString("egc_contact"));
				memberinfoVO.setEgc_phone(rs.getString("egc_phone"));
				list3.add(memberinfoVO); 
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
		return list3;
	}
	
	
	public static void main(String[]args) {
		MemberinfoDAO dao = new MemberinfoDAO();
		
//		新增
		MemberinfoVO memberinfoVO1 = new MemberinfoVO();
		memberinfoVO1.setGroup_id("G000004");
		memberinfoVO1.setMember_id("A002");
		memberinfoVO1.setAddress("桃園縣中壢市自強1路");
		memberinfoVO1.setIdentity("F124159258");
		memberinfoVO1.setEgc_contact("劉哲豪");
		memberinfoVO1.setEgc_phone("0900301568");
		memberinfoVO1.setM_name("劉哲豪");
		memberinfoVO1.setBirthday(java.sql.Date.valueOf("2019-08-19"));
		memberinfoVO1.setCellphone("09554131");
		memberinfoVO1.setM_email("g8123@gmail.com");
		dao.insert(memberinfoVO1);
		
//		修改
//		MemberinfoVO memberinfoVO2 = new MemberinfoVO();
//		memberinfoVO2.setGroup_id("G000003");
//		memberinfoVO2.setMember_id("A003");
//		memberinfoVO2.setAddress("桃園縣中壢市自強8路");
//		memberinfoVO2.setIdentity("S124159258");
//		memberinfoVO2.setEgc_contact("豪哥");
//		memberinfoVO2.setEgc_phone("0955301568");
//		dao.update(memberinfoVO2);
//			
			
//		刪除
//		dao.delete("G000003", "A002");
		
//		查詢
//		MemberinfoVO memberinfoVO3 = dao.findByPrimaryKey("G000002", "A002");
//		System.out.print(memberinfoVO3.getGroup_id() +",");
//		System.out.print(memberinfoVO3.getMember_id() + ",");
//		System.out.print(memberinfoVO3.getAddress() +",");
//		System.out.print(memberinfoVO3.getIdentity() +",");
//		System.out.print(memberinfoVO3.getEgc_contact() +",");
//		System.out.println(memberinfoVO3.getEgc_phone() +",");
//		System.out.println("==========================================");
			
//		List查詢
//		List<MemberinfoVO> list = dao.getAll();
//		for (MemberinfoVO memberinfoVO4 : list) {
//			System.out.print(memberinfoVO4.getGroup_id() + ",");
//			System.out.print(memberinfoVO4.getMember_id() + ",");
//			System.out.print(memberinfoVO4.getAddress() + ",");
//			System.out.print(memberinfoVO4.getIdentity() + ",");
//			System.out.print(memberinfoVO4.getEgc_contact() + ",");
//			System.out.println(memberinfoVO4.getEgc_phone() + ",");
//			System.out.println();
//		}
		
//		int memberinfoVO6 = dao.countByGroupId("G000001");
//			System.out.println(memberinfoVO6);
	}

	


}
