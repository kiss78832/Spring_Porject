package com.spring.spot.model;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SpotJDBCDAO implements SpotDAO_interface {
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "TEST1";
	private static final String PASSWORD = "TEST1";

	private static final String INSERT_STMT = "INSERT INTO spot (spot_id, spot_name, spot_intro, spot_pic, spot_lng, spot_lat, spot_point, spot_cabinpoint, spot_status, auth_spot, main_road) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ? ,? ,? ,?)";
	private static final String GET_ALL_STMT = "SELECT spot_id, spot_name, spot_intro, spot_pic, spot_lng, spot_lat, spot_point, spot_cabinpoint, spot_status, auth_spot, main_road FROM spot order by spot_id";
	private static final String GET_ONE_STMT = "SELECT spot_id, spot_name, spot_intro, spot_pic, spot_lng, spot_lat, spot_point, spot_cabinpoint, spot_status, auth_spot, main_road  FROM spot where spot_id = ?";
	private static final String DELETE = "DELETE FROM spot where spot_id = ?";
	private static final String UPDATE = "UPDATE spot set spot_name=?, spot_intro=?, spot_pic=?, spot_lng=?, spot_lat=?, spot_point=?, spot_cabinpoint=?, spot_status=?, auth_spot=?, main_road=? where spot_id = ?";
	
	/****************************自訂方法*******************************/
	private static final String GET_SPOT = 
			"SELECT spot_id, spot_name,  spot_cabinpoint, main_road FROM spot order by main_road";

	

	
	
	// 新增
	@Override
	public void insert(SpotVO spotVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);

			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, spotVO.getSpot_id());
			pstmt.setString(2, spotVO.getSpot_name());
			pstmt.setString(3, spotVO.getSpot_intro()); // CLOB
			pstmt.setBytes(4, spotVO.getSpot_pic()); 	// BLOB
			pstmt.setDouble(5, spotVO.getSpot_lat());
			pstmt.setDouble(6, spotVO.getSpot_lng());
			pstmt.setInt(7, spotVO.getSpot_point());
			pstmt.setInt(8, spotVO.getSpot_cabinpoint());
			pstmt.setInt(9, spotVO.getSpot_status());
			pstmt.setInt(10, spotVO.getAuth_spot());
			pstmt.setString(11, spotVO.getMain_road());

			pstmt.executeUpdate();

		} catch (SQLException | ClassNotFoundException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
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

	// 修改
	@Override
	public void update(SpotVO spotVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);

			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, spotVO.getSpot_name());
			pstmt.setString(2, spotVO.getSpot_intro());
			pstmt.setBytes(3, spotVO.getSpot_pic());
			pstmt.setDouble(4, spotVO.getSpot_lng());
			pstmt.setDouble(5, spotVO.getSpot_lat());
			pstmt.setInt(6, spotVO.getSpot_point());
			pstmt.setInt(7, spotVO.getSpot_cabinpoint());
			pstmt.setInt(8, spotVO.getSpot_status());
			pstmt.setInt(9, spotVO.getAuth_spot());
			pstmt.setString(10, spotVO.getMain_road());
			pstmt.setString(11, spotVO.getSpot_id());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException | ClassNotFoundException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
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

	// 刪除
	@Override
	public void delete(String spot_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);

			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, spot_id);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException | ClassNotFoundException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
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

	// 查詢
	@Override
	public SpotVO findByPrimaryKey(String spot_id) {
		SpotVO spotVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);

			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, spot_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				spotVO = new SpotVO();
				spotVO.setSpot_id(rs.getString("spot_id"));
				spotVO.setSpot_name(rs.getString("spot_name"));
				spotVO.setSpot_intro(rs.getString("spot_intro")); // CLOB
				spotVO.setSpot_pic(rs.getBytes("spot_pic")); // BLOB
				spotVO.setSpot_lng(rs.getDouble("spot_lng"));
				spotVO.setSpot_lat(rs.getDouble("spot_lat"));
				spotVO.setSpot_point(rs.getInt("spot_point"));
				spotVO.setSpot_cabinpoint(rs.getInt("spot_cabinpoint"));
				spotVO.setSpot_status(rs.getInt("spot_status"));
				spotVO.setAuth_spot(rs.getInt("auth_spot"));
				spotVO.setMain_road(rs.getString("main_road"));
			}

		} catch (SQLException | ClassNotFoundException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
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

		return spotVO;
	}

	// 萬用複合查詢
	@Override
	public List<SpotVO> getAll() {
		List<SpotVO> list = new ArrayList<SpotVO>();
		SpotVO spotVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);

			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				spotVO = new SpotVO();
				spotVO.setSpot_id(rs.getString("spot_id"));
				spotVO.setSpot_name(rs.getString("spot_name"));
				spotVO.setSpot_intro(rs.getString("spot_intro"));
				spotVO.setSpot_pic(rs.getBytes("spot_pic"));
				spotVO.setSpot_lng(rs.getDouble("spot_lng"));
				spotVO.setSpot_lat(rs.getDouble("spot_lat"));
				spotVO.setSpot_point(rs.getInt("spot_point"));
				spotVO.setSpot_cabinpoint(rs.getInt("spot_cabinpoint"));
				spotVO.setSpot_status(rs.getInt("spot_status"));
				spotVO.setAuth_spot(rs.getInt("auth_spot"));
				spotVO.setMain_road(rs.getString("main_road"));
				list.add(spotVO); // Store the row in the list
			}

		} catch (SQLException | ClassNotFoundException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
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
	//CLOB寫入
	public static String getLongString(String path) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(path));
		StringBuilder sb = new StringBuilder(); // StringBuffer is thread-safe!
		String str;
		while ((str = br.readLine()) != null) {
			sb.append(str);
			sb.append("\n");
		}
		br.close();

		return sb.toString();
	}

	// BLOB 寫入
	public static byte[] getPictureByteArray(String path) {
		File file = null;
		FileInputStream fis = null;
		ByteArrayOutputStream baos = null;

		try {
			file = new File(path);
			fis = new FileInputStream(file);
			baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[8192];
			int i;
			while ((i = fis.read(buffer)) != -1) {
				baos.write(buffer, 0, i); // 將長度i的buffer從第0個位元開始寫入
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (baos != null) {
				try {
					baos.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}

			if (fis != null) {
				try {
					fis.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return baos.toByteArray();
	}
	
	
	@Override
	public List<SpotVO> getspot(String main_road) {
		
		List<SpotVO> list = new ArrayList<SpotVO>();
		SpotVO spotVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);

			pstmt = con.prepareStatement(GET_SPOT);
			rs = pstmt.executeQuery();
			CharSequence spot = "1";
			
			while (rs.next()) {
				spotVO = new SpotVO();
				
				boolean retval = rs.getString("main_road").contains(spot);
				if(retval == true) {
				spotVO.setSpot_id(rs.getString("spot_id"));
				spotVO.setSpot_name(rs.getString("spot_name"));
				spotVO.setSpot_cabinpoint(rs.getInt("spot_cabinpoint"));
				System.out.println(retval);
				list.add(spotVO); 
				}
			}
		} catch (SQLException | ClassNotFoundException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
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
	
	

	public static void main(String[] args) throws IOException {

		SpotJDBCDAO spotdao = new SpotJDBCDAO();

		// 新增
//		String clob = getLongString("WebContent/spot/intro/Yushan001.txt");
//		byte[] pic = getPictureByteArray("WebContent/spot/images/Yushan001.jpg");
//		
//		SpotVO spotVO1 = new SpotVO();
//		spotVO1.setSpot_id("SPOT001");
//		spotVO1.setSpot_name("塔塔加登山口");
//		spotVO1.setSpot_intro(clob);	//CLOB
//		spotVO1.setSpot_pic(pic);		//BLOB
//		spotVO1.setSpot_lng(new Double(111.11111111));
//		spotVO1.setSpot_lat(new Double(111.11111111));
//		spotVO1.setSpot_point(new Integer(1));
//		spotVO1.setSpot_cabinpoint(new Integer(1));
//		spotVO1.setSpot_status(new Integer(1));
//		spotVO1.setAuth_spot(new Integer(1));
//		spotVO1.setMain_road("1");
//
//		spotdao.insert(spotVO1);
//		System.out.println("新增成功!");
		
		// 查詢
//		SpotVO spot03 = spotdao.findByPrimaryKey("SPOT001");
//		System.out.print("Id:" + spot03.getSpot_id() + ",");
//		System.out.print("Name:" + spot03.getSpot_name() + ",");
//		System.out.print("LNG:" + spot03.getSpot_lng() + ",");
//		System.out.print("LAT:" + spot03.getSpot_lat() + ",");
//		System.out.print("Point:" + spot03.getSpot_point() + ",");
//		System.out.print("CabinPoint :" + spot03.getSpot_cabinpoint() + ",");
//		System.out.print("Spot status:" + spot03.getSpot_status() + ",");
//		System.out.print("Spot Auth:" + spot03.getAuth_spot() + ",");
//		System.out.println("Main road:" + spot03.getMain_road());

		// 修改
//		String clob2 = getLongString("WebContent/spot/intro/Yushan002.txt");
//		byte[] pic2 = getPictureByteArray("WebContent/spot/images/Yushan002.jpg");
//		
//		SpotVO spotVO2 = new SpotVO();
//		spotVO2.setSpot_id("SPOT001");
//		spotVO2.setSpot_name("孟祿亭");	
//		spotVO2.setSpot_intro(clob2);	//CLOB	
//		spotVO2.setSpot_pic(pic2);		//BLOB
//		spotVO2.setSpot_lng(new Double(222.22222222));
//		spotVO2.setSpot_lat(new Double(222.22222222));
//		spotVO2.setSpot_point(new Integer(2));
//		spotVO2.setSpot_cabinpoint(new Integer(2));
//		spotVO2.setSpot_status(new Integer(2));
//		spotVO2.setAuth_spot(new Integer(2));
//		spotVO2.setMain_road("123");
//
//		spotdao.update(spotVO2);
//		System.out.println("修改成功!");

		// 查詢ListVO
//		List<SpotVO> list = spotdao.getAll();
//		for (SpotVO spot : list) {
//			System.out.print("Id:" + spot.getSpot_id() + ",");
//			System.out.print("Name:" + spot.getSpot_name() + ",");
//			System.out.print("LNG:" + spot.getSpot_lng() + ",");
//			System.out.print("LAT:" + spot.getSpot_lat() + ",");
//			System.out.print("Point:" + spot.getSpot_point() + ",");
//			System.out.print("CabinPoint :" + spot.getSpot_cabinpoint() + ",");
//			System.out.print("Spot status:" + spot.getSpot_status() + ",");
//			System.out.print("Spot Auth:" + spot.getAuth_spot() + ",");
//			System.out.println("Main road:" + spot.getMain_road());
//		}
		
		// 刪除
//		spotdao.delete("SPOT001");
//		System.out.println("刪除成功!");
		
		//查詢ListVO
		List<SpotVO> list = spotdao.getspot("1");
		for (SpotVO spot : list) {
			
			System.out.print("Id:" + spot.getSpot_id() + ",");
			System.out.print("Name:" + spot.getSpot_name() + ",");
			System.out.println("CabinPoint :" + spot.getSpot_cabinpoint() + ",");
//			System.out.println("Main road:" + spot.getMain_road());
//			
		}
	}

}
