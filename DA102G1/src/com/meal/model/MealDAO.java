package com.meal.model;


import java.io.ByteArrayOutputStream;

import java.io.IOException;
import java.io.InputStream;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.Part;
import javax.sql.DataSource;


public class MealDAO implements MealDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB2");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO MEAL(MEAL_ID,MEAL_NAME,MEAL_PRICE,MEAL_STATUS,MEAL_CONTENT,MEAL_PIC)VALUES(?,?,?,?,?,?)";
	private static final String UPDATE_STMT = "UPDATE MEAL SET  MEAL_NAME=? ,MEAL_PRICE=? ,MEAL_STATUS=? ,MEAL_CONTENT=? ,MEAL_PIC=? WHERE MEAL_ID=? ";
	private static final String GET_ALL_STMT = "SELECT meal_id,meal_name,meal_price,meal_status,meal_content,meal_pic FROM MEAL order by MEAL_ID";
	private static final String GET_ONE_STMT = "SELECT MEAL_ID,MEAL_NAME,MEAL_PRICE,MEAL_STATUS,MEAL_CONTENT,MEAL_PIC FROM MEAL WHERE MEAL_ID = ?";
	private static final String DELETE = "DELETE FROM MEAL where MEAL_ID = ?";
	private static final String SHOW_PIC = "SELECT MEAL_PIC FROM MEAL WHERE MEAL_ID = ?";
	private static final String UPDATE_STMT_NOTPIC = "UPDATE MEAL SET  MEAL_NAME=? ,MEAL_PRICE=? ,MEAL_STATUS=? ,MEAL_CONTENT=? WHERE MEAL_ID=? ";
	private static final String GET_ALL_STMT_BYSTATUS = "SELECT meal_id,meal_name,meal_price,meal_status,meal_content,meal_pic FROM MEAL WHERE MEAL_STATUS = ? ORDER BY MEAL_ID DESC";
	
	public List<MealVO> getMealsByMeal_status(Integer meal_status) {
//		Set<MealVO> set = new LinkedHashSet<MealVO>();
		List<MealVO> list = new LinkedList<MealVO>();
		MealVO mealVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT_BYSTATUS);
			pstmt.setInt(1, meal_status);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				mealVO = new MealVO();
				mealVO.setMeal_id(rs.getString("meal_id"));
				mealVO.setMeal_name(rs.getString("meal_name"));
				mealVO.setMeal_price(rs.getInt("meal_price"));
				mealVO.setMeal_status(rs.getInt("meal_status"));
				mealVO.setMeal_content(rs.getString("meal_content"));
				mealVO.setMeal_pic(rs.getBytes("meal_pic"));
				list.add(mealVO); 
			}
	
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}  finally {
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
	public void insert(MealVO mealVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, mealVO.getMeal_id());
			pstmt.setString(2, mealVO.getMeal_name());
			pstmt.setInt(3, mealVO.getMeal_price());
			pstmt.setInt(4, mealVO.getMeal_status());
			pstmt.setString(5, mealVO.getMeal_content());
			pstmt.setBytes(6, mealVO.getMeal_pic());

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
	public void update(MealVO mealVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, mealVO.getMeal_name());
			pstmt.setInt(2, mealVO.getMeal_price());
			pstmt.setInt(3, mealVO.getMeal_status());
			pstmt.setString(4, mealVO.getMeal_content());
			pstmt.setBytes(5, mealVO.getMeal_pic());
			pstmt.setString(6, mealVO.getMeal_id());

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
// not upload pic
	public void updateNotPic(MealVO mealVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT_NOTPIC);

			pstmt.setString(1, mealVO.getMeal_name());
			pstmt.setInt(2, mealVO.getMeal_price());
			pstmt.setInt(3, mealVO.getMeal_status());
			pstmt.setString(4, mealVO.getMeal_content());
			pstmt.setString(5, mealVO.getMeal_id());

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
	public void delete(String meal_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, meal_id);
			pstmt.executeUpdate();
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
	public MealVO findByPrimaryKey(String meal_id) {
		MealVO mealVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, meal_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				mealVO = new MealVO();
				mealVO.setMeal_id(rs.getString("meal_id"));
				mealVO.setMeal_name(rs.getString("meal_name"));
				mealVO.setMeal_price(rs.getInt("meal_price"));
				mealVO.setMeal_status(rs.getInt("meal_status"));
				mealVO.setMeal_content(rs.getString("meal_content"));
				mealVO.setMeal_pic(rs.getBytes("meal_pic"));

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
		return mealVO;

	}

	@Override
	public List<MealVO> getAll() {
		List<MealVO> list = new ArrayList<MealVO>();
		MealVO mealVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				mealVO = new MealVO();
				mealVO.setMeal_id(rs.getString("meal_id"));
				mealVO.setMeal_name(rs.getString("meal_name"));
				mealVO.setMeal_price(rs.getInt("meal_price"));
				mealVO.setMeal_status(rs.getInt("meal_status"));
				mealVO.setMeal_content(rs.getString("meal_content"));
				mealVO.setMeal_pic(rs.getBytes("meal_pic"));
				list.add(mealVO);
			}

			
		}  catch (SQLException se) {
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
	
	
	//img upload method
	public static byte[] getPictureByteArray(Part part) throws IOException {
		InputStream is = part.getInputStream();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		int i;
		while ((i = is.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
			baos.flush();
		}
		baos.close();
		is.close();

		return baos.toByteArray();
	}
	
	//img read method  
//	public static void readPicture(byte[] bytes) throws IOException {
//		FileOutputStream fos = new FileOutputStream("mealPicTest/M001.jpg");
//		fos.write(bytes);
//		fos.flush();
//		fos.close();
//		System.out.println("讀取圖片成功^___^ ");
//	}
	

	public static void main(String[] args) throws IOException {
//		MealDAO dao = new MealDAO();

		//insert test
//		MealVO mealVO1 = new MealVO();
//		mealVO1.setMeal_id("M003");
//		mealVO1.setMeal_name("大麥克");
//		mealVO1.setMeal_price(1100);
//		mealVO1.setMeal_status(1);
//		mealVO1.setMeal_content("麥當勞最好吃的食物= =");
//		
//		byte[] pic2 = MealDAO.getPictureByteArray("WebContent/meal/M001.jpg");
//		mealVO1.setMeal_pic(pic2);
//		dao.insert(mealVO1);
//		System.out.println("^____^");
		
		//update test
//		MealVO mealVO2 = new MealVO();
//		mealVO2.setMeal_name("大麥克");
//		mealVO2.setMeal_price(1100);
//		mealVO2.setMeal_status(1);
//		mealVO2.setMeal_content("麥當勞最好吃的食物= =,相傳吃了打code會變快");
//		
//		byte[] pic2 = MealDAO.getPictureByteArray("WebContent/meal/M001.jpg");
//		mealVO2.setMeal_pic(pic2);
//		mealVO2.setMeal_id("M003");
//		dao.update(mealVO2);
//		System.out.println("^____^");
		
		
		//delete test
//		dao.delete("M003");
//		
//		System.out.println("^____^");
		
		//query one
//		MealVO mealVO3 = dao.findByPrimaryKey("M003");
//		System.out.println(mealVO3.getMeal_id());
//		System.out.println(mealVO3.getMeal_name());
//		System.out.println(mealVO3.getMeal_price());
//		System.out.println(mealVO3.getMeal_status());
//		System.out.println(mealVO3.getMeal_content());
//		
//		byte[] pic = mealVO3.getMeal_pic();
//		readPicture(pic);
//		
//		System.out.println(mealVO3.getMeal_pic());
//		System.out.println("^____^");
//		
//		//query all
//		List<MealVO> list = dao.getAll();
//		for(MealVO mealVO: list) {
//			System.out.println(mealVO.getMeal_id());
//			System.out.println(mealVO.getMeal_name());
//			System.out.println(mealVO.getMeal_price());
//			System.out.println(mealVO.getMeal_status());
//			System.out.println(mealVO.getMeal_content());
//			System.out.println(mealVO.getMeal_pic());
//			System.out.println("^____^");
//		}
		
	}

}
