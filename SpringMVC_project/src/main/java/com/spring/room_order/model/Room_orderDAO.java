package com.spring.room_order.model;

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

import com.spring.order_detail.model.Order_DetailDAO;
import com.spring.order_detail.model.Order_DetailVO;

public class Room_orderDAO implements Room_orderDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB2");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO  ROOM_ORDER(order_id,member_id,group_id,order_price,order_status,payment_status, booking_day) VALUES ('O'||LPAD(to_char(SQU_ORDER_ID.nextval), 9, '0'),?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT order_id , member_id,group_id,order_price,order_status,payment_status,order_time, booking_day FROM ROOM_ORDER order by order_id";
	private static final String GET_ONE_STMT = "SELECT order_id , member_id,group_id,order_price,order_status,payment_status,order_time, booking_day FROM ROOM_ORDER WHERE order_id =?";
	private static final String UPDATE = "UPDATE ROOM_ORDER set member_id=?,group_id=?,order_price=?,order_status=?,payment_status=?, booking_day=? where order_id = ?";
	private static final String INSERT_STMT_WITHOUTGROUP = "INSERT INTO  ROOM_ORDER(order_id,member_id,order_price,order_status,payment_status, booking_day) VALUES ('O'||LPAD(to_char(SQU_ORDER_ID.nextval), 9, '0'),?,?,?,?,?)";
	private static final String GET_ONE_STMT_BYLASTEST_DATE = "SELECT * FROM  (   SELECT * FROM   ROOM_ORDER  ORDER BY ORDER_TIME DESC ) WHERE MEMBER_ID=? AND ROWNUM = 1";

	private static final String GET_COUNT_BY_NOTPAID = "SELECT COUNT(*) FROM ROOM_ORDER WHERE PAYMENT_STATUS = 1";

	private static final String GET_COUNT_BY_ALREADYPAID = "SELECT COUNT(*) FROM ROOM_ORDER WHERE PAYMENT_STATUS = 2";

	private static final String UPDATE_ORDER_STATUS = "UPDATE ROOM_ORDER set order_status=?,payment_status=? where order_id = ?";
	private static final String GET_ALL_STMT_BY_ORDERSTATUS = "SELECT order_id , member_id,group_id,order_price,order_status,payment_status,order_time, booking_day FROM ROOM_ORDER WHERE ORDER_STATUS=? order by order_id";
	private static final String GET_ALL_STMT_BY_PAYMENTSTATUS = "SELECT order_id , member_id,group_id,order_price,order_status,payment_status,order_time, booking_day FROM ROOM_ORDER WHERE PAYMENT_STATUS=? order by order_id";

	private static final String GET_ALL_DIFFERENT_MEMBER_ID = "SELECT DISTINCT MEMBER_ID FROM ROOM_ORDER";

	private static final String GET_ALL_STMT_BY_MEMBER_ID = "SELECT order_id , member_id,group_id,order_price,order_status,payment_status,order_time, booking_day FROM ROOM_ORDER WHERE MEMBER_ID =? order by order_id";

	private static final String GET_ALL_DIFFERENT_GROUP_ID = "SELECT DISTINCT GROUP_ID FROM ROOM_ORDER";
	private static final String GET_ALL_STMT_BY_GROUP_ID = "SELECT order_id , member_id,group_id,order_price,order_status,payment_status,order_time, booking_day FROM ROOM_ORDER WHERE GROUP_ID =? order by order_id";

	private static final String GET_ALL_TODAY="Select * From ROOM_ORDER Where ORDER_TIME >= trunc(sysdate) And ORDER_TIME < trunc(sysdate) + 1";
	private static final String GET_ALL_IN_3DAYS="SELECT * FROM ROOM_ORDER WHERE ORDER_TIME > sysdate-3";
	private static final String GET_ALL_IN_7DAYS="SELECT * FROM ROOM_ORDER WHERE ORDER_TIME > sysdate-7";
	private static final String GET_ALL_IN_30DAYS="SELECT * FROM ROOM_ORDER WHERE ORDER_TIME > sysdate-30";
	private static final String GET_COUNT_TODAY="Select COUNT(*) From ROOM_ORDER Where ORDER_TIME >= trunc(sysdate) And ORDER_TIME < trunc(sysdate) + 1";
	
	@Override
	public void insert(Room_orderVO room_orderVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, room_orderVO.getMember_id());
			pstmt.setString(2, room_orderVO.getGroup_id());
			pstmt.setInt(3, room_orderVO.getOrder_price());
			pstmt.setInt(4, room_orderVO.getOrder_status());
			pstmt.setInt(5, room_orderVO.getPayment_status());
			pstmt.setInt(6, room_orderVO.getBooking_day());

			pstmt.executeUpdate();
		}  catch (SQLException e) {
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

	public void insertWithOrders(Room_orderVO room_orderVO , List<Order_DetailVO> list) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			
			con.setAutoCommit(false);
			
			String cols[] = {"order_id"};
			pstmt = con.prepareStatement(INSERT_STMT, cols);
			pstmt.setString(1, room_orderVO.getMember_id());
			pstmt.setString(2, room_orderVO.getGroup_id());
			pstmt.setInt(3, room_orderVO.getOrder_price());
			pstmt.setInt(4, room_orderVO.getOrder_status());
			pstmt.setInt(5, room_orderVO.getPayment_status());
			pstmt.setInt(6, room_orderVO.getBooking_day());
			pstmt.executeUpdate();
			
			String next_roomOrderNo = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if(rs.next()) {
				next_roomOrderNo= rs.getString(1);
				System.out.println("success");
			}else {
				System.out.println("fail");
			}
			rs.close();
			
			Order_DetailDAO dao = new Order_DetailDAO();
			for(Order_DetailVO order_detailVO : list) {
				order_detailVO.setOrder_id(new String(next_roomOrderNo));
				if("0".equals(order_detailVO.getMeal_id())) {
				dao.insertWithoutMeal(order_detailVO, con);
				}else {
					dao.insertWithMeal(order_detailVO, con);
				}
			}
			
			con.commit();
			con.setAutoCommit(true);
			
			System.out.println("success");
			
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
	
	public void insertWithoutGroup(Room_orderVO room_orderVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT_WITHOUTGROUP);

			pstmt.setString(1, room_orderVO.getMember_id());
			pstmt.setInt(2, room_orderVO.getOrder_price());
			pstmt.setInt(3, room_orderVO.getOrder_status());
			pstmt.setInt(4, room_orderVO.getPayment_status());
			pstmt.setInt(5, room_orderVO.getBooking_day());

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
	public void update(Room_orderVO resbedVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, resbedVO.getMember_id());
			pstmt.setString(2, resbedVO.getGroup_id());
			pstmt.setInt(3, resbedVO.getOrder_price());
			pstmt.setDouble(4, resbedVO.getOrder_status());
			pstmt.setDouble(5, resbedVO.getPayment_status());
			pstmt.setInt(6, resbedVO.getBooking_day());
			pstmt.setString(7, resbedVO.getOrder_id());

			pstmt.executeUpdate();

		} catch (SQLException se) {
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

	public void updateOrder_status(Room_orderVO resbedVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_ORDER_STATUS);

			pstmt.setInt(1, resbedVO.getOrder_status());
			pstmt.setInt(2, resbedVO.getPayment_status());
			pstmt.setString(3, resbedVO.getOrder_id());

			pstmt.executeUpdate();

		} catch (SQLException se) {
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
	public Room_orderVO findByPrimaryKey(String order_id) {
		Room_orderVO room_orderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, order_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				room_orderVO = new Room_orderVO();
				room_orderVO.setOrder_id(rs.getString("order_id"));
				room_orderVO.setMember_id(rs.getString("member_id"));
				room_orderVO.setGroup_id(rs.getString("group_id"));
				room_orderVO.setOrder_price(rs.getInt("order_price"));
				room_orderVO.setOrder_status(rs.getInt("order_status"));
				room_orderVO.setPayment_status(rs.getInt("payment_status"));
				room_orderVO.setOrder_time(rs.getTimestamp("order_time"));
				room_orderVO.setBooking_day(rs.getInt("booking_day"));
			}

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
		return room_orderVO;
	}

	@Override
	public List<Room_orderVO> getAll() {
		List<Room_orderVO> list = new ArrayList<Room_orderVO>();
		Room_orderVO room_orderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				room_orderVO = new Room_orderVO();
				room_orderVO.setOrder_id(rs.getString("order_id"));
				room_orderVO.setMember_id(rs.getString("member_id"));
				room_orderVO.setGroup_id(rs.getString("group_id"));
				room_orderVO.setOrder_price(rs.getInt("order_price"));
				room_orderVO.setOrder_status(rs.getInt("order_status"));
				room_orderVO.setPayment_status(rs.getInt("payment_status"));
				room_orderVO.setOrder_time(rs.getTimestamp("order_time"));
				room_orderVO.setBooking_day(rs.getInt("booking_day"));
				list.add(room_orderVO);
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
	
	public List<Room_orderVO> getAllByDateRange(Integer date_range) {
		List<Room_orderVO> list = new ArrayList<Room_orderVO>();
		Room_orderVO room_orderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			
			switch(date_range) {
			case 1:
				pstmt = con.prepareStatement(GET_ALL_TODAY);
				break;
			case 3:
				pstmt = con.prepareStatement(GET_ALL_IN_3DAYS);
				break;
			case 7:
				pstmt = con.prepareStatement(GET_ALL_IN_7DAYS);
				break;
			case 30:
				pstmt = con.prepareStatement(GET_ALL_IN_30DAYS);
				break;
			}
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				room_orderVO = new Room_orderVO();
				room_orderVO.setOrder_id(rs.getString("order_id"));
				room_orderVO.setMember_id(rs.getString("member_id"));
				room_orderVO.setGroup_id(rs.getString("group_id"));
				room_orderVO.setOrder_price(rs.getInt("order_price"));
				room_orderVO.setOrder_status(rs.getInt("order_status"));
				room_orderVO.setPayment_status(rs.getInt("payment_status"));
				room_orderVO.setOrder_time(rs.getTimestamp("order_time"));
				room_orderVO.setBooking_day(rs.getInt("booking_day"));
				list.add(room_orderVO);
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
	
	

	public List<Room_orderVO> getAllByMember_id(String member_id) {
		List<Room_orderVO> list = new ArrayList<Room_orderVO>();
		Room_orderVO room_orderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT_BY_MEMBER_ID);
			pstmt.setString(1, member_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				room_orderVO = new Room_orderVO();
				room_orderVO.setOrder_id(rs.getString("order_id"));
				room_orderVO.setMember_id(rs.getString("member_id"));
				room_orderVO.setGroup_id(rs.getString("group_id"));
				room_orderVO.setOrder_price(rs.getInt("order_price"));
				room_orderVO.setOrder_status(rs.getInt("order_status"));
				room_orderVO.setPayment_status(rs.getInt("payment_status"));
				room_orderVO.setOrder_time(rs.getTimestamp("order_time"));
				room_orderVO.setBooking_day(rs.getInt("booking_day"));
				list.add(room_orderVO);
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

	public List<Room_orderVO> getAllByGroup_id(String group_id) {
		List<Room_orderVO> list = new ArrayList<Room_orderVO>();
		Room_orderVO room_orderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT_BY_GROUP_ID);
			pstmt.setString(1, group_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				room_orderVO = new Room_orderVO();
				room_orderVO.setOrder_id(rs.getString("order_id"));
				room_orderVO.setMember_id(rs.getString("member_id"));
				room_orderVO.setGroup_id(rs.getString("group_id"));
				room_orderVO.setOrder_price(rs.getInt("order_price"));
				room_orderVO.setOrder_status(rs.getInt("order_status"));
				room_orderVO.setPayment_status(rs.getInt("payment_status"));
				room_orderVO.setOrder_time(rs.getTimestamp("order_time"));
				room_orderVO.setBooking_day(rs.getInt("booking_day"));
				list.add(room_orderVO);
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

	public List<String> getAllDifferentMember() {
		List<String> list = new ArrayList<String>();
		String member_id;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_DIFFERENT_MEMBER_ID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				member_id = rs.getString("member_id");

				list.add(member_id);
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

	public List<String> getAllDifferentGroup() {
		List<String> list = new ArrayList<String>();
		String group_id;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_DIFFERENT_GROUP_ID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				group_id = rs.getString("group_id");

				list.add(group_id);
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

	public Room_orderVO getLastestOne(String member_id) {
		Room_orderVO room_orderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT_BYLASTEST_DATE);

			pstmt.setString(1, member_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				room_orderVO = new Room_orderVO();
				room_orderVO.setOrder_id(rs.getString("order_id"));
				room_orderVO.setMember_id(rs.getString("member_id"));
				room_orderVO.setGroup_id(rs.getString("group_id"));
				room_orderVO.setOrder_price(rs.getInt("order_price"));
				room_orderVO.setOrder_status(rs.getInt("order_status"));
				room_orderVO.setPayment_status(rs.getInt("payment_status"));
				room_orderVO.setOrder_time(rs.getTimestamp("order_time"));
				room_orderVO.setBooking_day(rs.getInt("booking_day"));
			}

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
		return room_orderVO;
	}

	public int getAllByNotPaid() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_COUNT_BY_NOTPAID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				count = rs.getInt("COUNT(*)");
			}

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
		return count;
	}

	public int getAllByAlreadyPaid() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_COUNT_BY_ALREADYPAID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				count = rs.getInt("COUNT(*)");
			}

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
		return count;
	}

	public List<Room_orderVO> getAllByOrderStatus(Integer order_status) {
		List<Room_orderVO> list = new ArrayList<Room_orderVO>();
		Room_orderVO room_orderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT_BY_ORDERSTATUS);
			pstmt.setInt(1, order_status);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				room_orderVO = new Room_orderVO();
				room_orderVO.setOrder_id(rs.getString("order_id"));
				room_orderVO.setMember_id(rs.getString("member_id"));
				room_orderVO.setGroup_id(rs.getString("group_id"));
				room_orderVO.setOrder_price(rs.getInt("order_price"));
				room_orderVO.setOrder_status(rs.getInt("order_status"));
				room_orderVO.setPayment_status(rs.getInt("payment_status"));
				room_orderVO.setOrder_time(rs.getTimestamp("order_time"));
				room_orderVO.setBooking_day(rs.getInt("booking_day"));
				list.add(room_orderVO);
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

	public List<Room_orderVO> getAllByPaymentStatus(Integer payment_status) {
		List<Room_orderVO> list = new ArrayList<Room_orderVO>();
		Room_orderVO room_orderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT_BY_PAYMENTSTATUS);
			pstmt.setInt(1, payment_status);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				room_orderVO = new Room_orderVO();
				room_orderVO.setOrder_id(rs.getString("order_id"));
				room_orderVO.setMember_id(rs.getString("member_id"));
				room_orderVO.setGroup_id(rs.getString("group_id"));
				room_orderVO.setOrder_price(rs.getInt("order_price"));
				room_orderVO.setOrder_status(rs.getInt("order_status"));
				room_orderVO.setPayment_status(rs.getInt("payment_status"));
				room_orderVO.setOrder_time(rs.getTimestamp("order_time"));
				room_orderVO.setBooking_day(rs.getInt("booking_day"));
				list.add(room_orderVO);
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
	
	public int getCountToday() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_COUNT_TODAY);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				count = rs.getInt("COUNT(*)");
			}

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
		return count;
	}

}
