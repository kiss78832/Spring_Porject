package com.spring.order_detail.model;

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


public class Order_DetailDAO implements  Order_DetailDAO_interface {

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
		"INSERT INTO  ORDER_DETAIL(detail_id , order_id , location_id , bed_num , bedTotal_price ,checkin_date,meal_id,meal_quantity,mealTotal_price) VALUES ('OD'||LPAD(to_char(SQU_DETAIL_ID.nextval), 8, '0'), ?, ?, ?, ?, ? ,? ,? ,?)";
	private static final String GET_ALL_STMT = 
		"SELECT detail_id, order_id , location_id , bed_num , bedTotal_price ,checkin_date,meal_id,meal_quantity,mealTotal_price FROM ORDER_DETAIL order by detail_id";
	private static final String GET_ONE_STMT = 
		"SELECT detail_id , order_id , location_id , bed_num , bedTotal_price ,checkin_date,meal_id,meal_quantity,mealTotal_price FROM ORDER_DETAIL where detail_id = ?";
	private static final String DELETE = 
		"DELETE FROM ORDER_DETAIL where detail_id = ?";
	private static final String UPDATE = 
		"UPDATE ORDER_DETAIL set order_id=? , location_id=? , bed_num=? , bedTotal_price=? ,checkin_date =?,meal_id =?,meal_quantity =? , mealTotal_price =? where detail_id = ?";
	private static final String INSERT_STMT_WITHOUTMEAL = 
			"INSERT INTO  ORDER_DETAIL(detail_id , order_id , location_id , bed_num , bedTotal_price ,checkin_date) VALUES ('OD'||LPAD(to_char(SQU_DETAIL_ID.nextval), 8, '0'), ?, ?, ?, ?, ?)";
	private static final String GET_DETAILS_BYORDER_ID = "SELECT detail_id , order_id , location_id , bed_num , bedTotal_price ,checkin_date,meal_id,meal_quantity,mealTotal_price FROM ORDER_DETAIL where ORDER_ID =?";
	
	
	@Override
	public void insert(Order_DetailVO order_DetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, order_DetailVO.getOrder_id());
			pstmt.setString(2, order_DetailVO.getLocation_id());
			pstmt.setInt(3, order_DetailVO.getBed_num());
			pstmt.setInt(4, order_DetailVO.getBedTotal_price());
			pstmt.setDate(5, order_DetailVO.getCheckin_date());
			pstmt.setString(6, order_DetailVO.getMeal_id());
			pstmt.setInt(7, order_DetailVO.getMeal_quantity());
			pstmt.setInt(8, order_DetailVO.getMealTotal_price());
			

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
	
	public void insertWithoutMeal(Order_DetailVO order_DetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT_WITHOUTMEAL);

			pstmt.setString(1, order_DetailVO.getOrder_id());
			pstmt.setString(2, order_DetailVO.getLocation_id());
			pstmt.setInt(3, order_DetailVO.getBed_num());
			pstmt.setInt(4, order_DetailVO.getBedTotal_price());
			pstmt.setDate(5, order_DetailVO.getCheckin_date());
			

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
	public void update(Order_DetailVO order_DetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, order_DetailVO.getOrder_id());
			pstmt.setString(2, order_DetailVO.getLocation_id());
			pstmt.setInt(3, order_DetailVO.getBed_num());
			pstmt.setInt(4, order_DetailVO.getBedTotal_price());
			pstmt.setDate(5, order_DetailVO.getCheckin_date());
			pstmt.setString(6, order_DetailVO.getMeal_id());
			pstmt.setInt(7, order_DetailVO.getMeal_quantity());
			pstmt.setInt(8, order_DetailVO.getMealTotal_price());
			pstmt.setString(9, order_DetailVO.getDetail_id());

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
	public void delete(String detail_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, detail_id);

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
	public Order_DetailVO findByPrimaryKey(String detail_id) {
		Order_DetailVO order_DetailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, detail_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				order_DetailVO = new Order_DetailVO();
				order_DetailVO.setDetail_id(rs.getString("detail_id"));
				order_DetailVO.setOrder_id(rs.getString("order_id"));
				order_DetailVO.setLocation_id(rs.getString("location_id"));
				order_DetailVO.setBed_num(rs.getInt("bed_num"));
				order_DetailVO.setBedTotal_price(rs.getInt("bedTotal_price"));
				order_DetailVO.setCheckin_date(rs.getDate("checkin_date"));
				order_DetailVO.setMeal_id(rs.getString("meal_id"));
				order_DetailVO.setMeal_quantity(rs.getInt("meal_quantity"));
				order_DetailVO.setMealTotal_price(rs.getInt("mealTotal_price"));
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
		return order_DetailVO;
	}

	@Override
	public List<Order_DetailVO> getAll() {
		List<Order_DetailVO> list = new ArrayList<Order_DetailVO>();
		Order_DetailVO order_DetailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				order_DetailVO = new Order_DetailVO();
				order_DetailVO.setDetail_id(rs.getString("detail_id"));
				order_DetailVO.setOrder_id(rs.getString("order_id"));
				order_DetailVO.setLocation_id(rs.getString("location_id"));
				order_DetailVO.setBed_num(rs.getInt("bed_num"));
				order_DetailVO.setBedTotal_price(rs.getInt("bedTotal_price"));
				order_DetailVO.setCheckin_date(rs.getDate("checkin_date"));
				order_DetailVO.setMeal_id(rs.getString("meal_id"));
				order_DetailVO.setMeal_quantity(rs.getInt("meal_quantity"));
				order_DetailVO.setMealTotal_price(rs.getInt("mealTotal_price"));
				list.add(order_DetailVO); 
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

	
	public List<Order_DetailVO> getDetailsByOrder_id(String order_id) {
		List<Order_DetailVO> list = new ArrayList<Order_DetailVO>();
		Order_DetailVO order_DetailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_DETAILS_BYORDER_ID);
			pstmt.setString(1, order_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				order_DetailVO = new Order_DetailVO();
				order_DetailVO.setDetail_id(rs.getString("detail_id"));
				order_DetailVO.setOrder_id(rs.getString("order_id"));
				order_DetailVO.setLocation_id(rs.getString("location_id"));
				order_DetailVO.setBed_num(rs.getInt("bed_num"));
				order_DetailVO.setBedTotal_price(rs.getInt("bedTotal_price"));
				order_DetailVO.setCheckin_date(rs.getDate("checkin_date"));
				order_DetailVO.setMeal_id(rs.getString("meal_id"));
				order_DetailVO.setMeal_quantity(rs.getInt("meal_quantity"));
				order_DetailVO.setMealTotal_price(rs.getInt("mealTotal_price"));
				list.add(order_DetailVO); 
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
	
	//自增主鍵值綁定 不用再新增connection
	
	public void insertWithMeal(Order_DetailVO order_DetailVO , Connection con) {
		PreparedStatement pstmt = null;

		try {

     		pstmt = con.prepareStatement(INSERT_STMT);

     		pstmt.setString(1, order_DetailVO.getOrder_id());
			pstmt.setString(2, order_DetailVO.getLocation_id());
			pstmt.setInt(3, order_DetailVO.getBed_num());
			pstmt.setInt(4, order_DetailVO.getBedTotal_price());
			pstmt.setDate(5, order_DetailVO.getCheckin_date());
			pstmt.setString(6, order_DetailVO.getMeal_id());
			pstmt.setInt(7, order_DetailVO.getMeal_quantity());
			pstmt.setInt(8, order_DetailVO.getMealTotal_price());	

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-emp");
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
		}
		
		
	}
	
	
	public void insertWithoutMeal(Order_DetailVO order_DetailVO , Connection con) {
		PreparedStatement pstmt = null;

		try {

			pstmt = con.prepareStatement(INSERT_STMT_WITHOUTMEAL);

			pstmt.setString(1, order_DetailVO.getOrder_id());
			pstmt.setString(2, order_DetailVO.getLocation_id());
			pstmt.setInt(3, order_DetailVO.getBed_num());
			pstmt.setInt(4, order_DetailVO.getBedTotal_price());
			pstmt.setDate(5, order_DetailVO.getCheckin_date());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-emp");
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
		}
		
		
	}
	
	
	
	
	
	public static void main(String[] args) {

//		Order_DetailDAO dao = new Order_DetailDAO();
//MEAL DATE　ＤＥＬＥＴＥ　下面還沒改！！
		// 新增
//		Order_DetailVO order_DetailVO1 = new Order_DetailVO();
//		order_DetailVO1.setOrder_id("O000000001");
//		order_DetailVO1.setLocation_id("A0001");
//		order_DetailVO1.setBed_num(4);
//		order_DetailVO1.setBedTotal_price(30678);
//		order_DetailVO1.setCheckin_date(java.sql.Date.valueOf("2019-08-15"));
//		order_DetailVO1.setMeal_id("M001");
//		order_DetailVO1.setMeal_quantity(8);
//		order_DetailVO1.setMeal_date(java.sql.Date.valueOf("2019-08-15"));
//		order_DetailVO1.setMealTotal_price(8800);
//		dao.insert(order_DetailVO1);
//		System.out.println("^__^");

//		// 修改
//		Order_DetailVO order_DetailVO2 = new Order_DetailVO();
//		order_DetailVO2.setDetail_id("OD00000002");
//		order_DetailVO2.setOrder_id("O000000001");
//		order_DetailVO2.setLocation_id("A0001");
//		order_DetailVO2.setBed_num(6);
//		order_DetailVO2.setBedTotal_price(60678);
//		order_DetailVO2.setCheckin_date(java.sql.Date.valueOf("2019-08-15"));
//		order_DetailVO2.setMeal_id("M001");
//		order_DetailVO2.setMeal_quantity(6);
//		order_DetailVO2.setMeal_date(java.sql.Date.valueOf("2019-08-15"));
//		order_DetailVO2.setMealTotal_price(6600);
//		dao.update(order_DetailVO2);
//		System.out.println("^__^");
//
//		// 刪除
//		dao.delete("OD00000002");
//		System.out.println("^__^");
//
//		// 查詢
//		
//		Order_DetailVO order_DetailVO3 = dao.findByPrimaryKey("OD00000002");
//		System.out.println(order_DetailVO3.getDetail_id());
//		System.out.println(order_DetailVO3.getOrder_id());
//		System.out.println(order_DetailVO3.getLocation_id());
//		System.out.println(order_DetailVO3.getBed_num());
//		System.out.println(order_DetailVO3.getBedTotal_price());
//		System.out.println(order_DetailVO3.getCheckin_date());
//		System.out.println(order_DetailVO3.getMeal_id());
//		System.out.println(order_DetailVO3.getMeal_quantity());
//		System.out.println(order_DetailVO3.getMeal_date());
//		System.out.println(order_DetailVO3.getMealTotal_price());
//		
//		System.out.println("^__^");

//
//		// 查詢
//		List<Order_DetailVO> list = dao.getAll();
//		for (Order_DetailVO order_DetailVO : list) {
//			System.out.println(order_DetailVO.getDetail_id());
//			System.out.println(order_DetailVO.getOrder_id());
//			System.out.println(order_DetailVO.getLocation_id());
//			System.out.println(order_DetailVO.getBed_num());
//			System.out.println(order_DetailVO.getBedTotal_price());
//			System.out.println(order_DetailVO.getCheckin_date());
//			System.out.println(order_DetailVO.getMeal_id());
//			System.out.println(order_DetailVO.getMeal_quantity());
//			System.out.println(order_DetailVO.getMeal_date());
//			System.out.println(order_DetailVO.getMealTotal_price());
//		}
		
	}
	
	
	
	
}
