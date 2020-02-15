package com.location.model;

import java.io.ByteArrayOutputStream;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.Part;
import javax.sql.DataSource;

public class LocationDAO implements LocationDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB2");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO ROOM_LOCATION(location_id,location_name,bedTotal_num,bed_price,location_pic,loc_type ,location_status)VALUES(?,?,?,?,?,?,?)";
	private static final String UPDATE_STMT = "UPDATE ROOM_LOCATION SET location_name=? ,bedTotal_num=? ,bed_price=? ,location_pic=? , loc_type=?, location_status=? WHERE LOCATION_ID=? ";
	private static final String GET_ALL_STMT = "SELECT location_id ,location_name ,bedTotal_num ,bed_price ,location_pic ,loc_type , location_status FROM ROOM_LOCATION order by LOCATION_ID";
	private static final String GET_ONE_STMT = "SELECT location_id ,location_name ,bedTotal_num ,bed_price ,location_pic ,loc_type , location_status FROM ROOM_LOCATION where LOCATION_ID = ?";
	private static final String DELETE = "DELETE FROM ROOM_LOCATION where LOCATION_ID = ?";
	private static final String UPDATE_STMT_NOTPIC = "UPDATE ROOM_LOCATION SET location_name=? ,bedTotal_num=? ,bed_price=? , loc_type=?, location_status=? WHERE LOCATION_ID=? ";
	private static final String GET_ALL_STMT_BYLOCTYPE = "SELECT location_id ,location_name ,bedTotal_num ,bed_price ,location_pic ,loc_type , location_status FROM ROOM_LOCATION WHERE LOC_TYPE = ?order by LOCATION_ID";
	private static final String GET_ALL_STMT_BYLOCSTATUS = "SELECT location_id ,location_name ,bedTotal_num ,bed_price ,location_pic ,loc_type , location_status FROM ROOM_LOCATION WHERE location_status = ?order by LOCATION_ID";
	//新增SQL 2019-09-23
	private static final String GET_LOCATION_NAME = "SELECT location_id ,location_name ,bedTotal_num ,bed_price ,location_pic ,loc_type , location_status FROM ROOM_LOCATION where LOCATION_NAME = ?";
	
	@Override
	public void insert(LocationVO locationVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, locationVO.getLocation_id());
			pstmt.setString(2, locationVO.getLocation_name());
			pstmt.setInt(3, locationVO.getBedTotal_num());
			pstmt.setInt(4, locationVO.getBed_price());
			pstmt.setBytes(5, locationVO.getLocation_pic());
			pstmt.setInt(6, locationVO.getLoc_type());
			pstmt.setInt(7, locationVO.getLocation_status());

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
	public void update(LocationVO locationVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, locationVO.getLocation_name());
			pstmt.setInt(2, locationVO.getBedTotal_num());
			pstmt.setInt(3, locationVO.getBed_price());
			pstmt.setBytes(4, locationVO.getLocation_pic());
			pstmt.setInt(5, locationVO.getLoc_type());
			pstmt.setInt(6, locationVO.getLocation_status());
			pstmt.setString(7, locationVO.getLocation_id());

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
	public void delete(String location_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, location_id);
			pstmt.executeUpdate();
		}catch (SQLException se) {
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
	public LocationVO findByPrimaryKey(String location_id) {
		LocationVO locationVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, location_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				locationVO = new LocationVO();
				locationVO.setLocation_id(rs.getString("location_id"));
				locationVO.setLocation_name(rs.getString("location_name"));
				locationVO.setBedTotal_num(rs.getInt("bedTotal_num"));
				locationVO.setBed_price(rs.getInt("bed_price"));
				locationVO.setLocation_pic(rs.getBytes("location_pic"));
				locationVO.setLoc_type(rs.getInt("loc_type"));
				locationVO.setLocation_status(rs.getInt("location_status"));

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
		return locationVO;
	}

	@Override
	public List<LocationVO> getAll() {

		List<LocationVO> list = new ArrayList<LocationVO>();
		LocationVO locationVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				locationVO = new LocationVO();
				locationVO.setLocation_id(rs.getString("location_id"));
				locationVO.setLocation_name(rs.getString("location_name"));
				locationVO.setBedTotal_num(rs.getInt("bedTotal_num"));
				locationVO.setBed_price(rs.getInt("bed_price"));
				locationVO.setLocation_pic(rs.getBytes("location_pic"));
				locationVO.setLoc_type(rs.getInt("loc_type"));
				locationVO.setLocation_status(rs.getInt("location_status"));
				list.add(locationVO);
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
	

	@Override
	public void updateNotPic(LocationVO locationVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT_NOTPIC);

			pstmt.setString(1, locationVO.getLocation_name());
			pstmt.setInt(2, locationVO.getBedTotal_num());
			pstmt.setInt(3, locationVO.getBed_price());
			pstmt.setInt(4, locationVO.getLoc_type());
			pstmt.setInt(5, locationVO.getLocation_status());
			pstmt.setString(6, locationVO.getLocation_id());

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
	public List<LocationVO> getLocsByLoc_Type(Integer loc_type) {
		List<LocationVO> list = new ArrayList<LocationVO>();
		LocationVO locationVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT_BYLOCTYPE);
			pstmt.setInt(1, loc_type);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				locationVO = new LocationVO();
				locationVO.setLocation_id(rs.getString("location_id"));
				locationVO.setLocation_name(rs.getString("location_name"));
				locationVO.setBedTotal_num(rs.getInt("bedTotal_num"));
				locationVO.setBed_price(rs.getInt("bed_price"));
				locationVO.setLocation_pic(rs.getBytes("location_pic"));
				locationVO.setLoc_type(rs.getInt("loc_type"));
				locationVO.setLocation_status(rs.getInt("location_status"));
				list.add(locationVO);
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

	@Override
	public List<LocationVO> getLocsByLoc_Status(Integer location_status) {
		List<LocationVO> list = new ArrayList<LocationVO>();
		LocationVO locationVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT_BYLOCSTATUS);
			pstmt.setInt(1, location_status);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				locationVO = new LocationVO();
				locationVO.setLocation_id(rs.getString("location_id"));
				locationVO.setLocation_name(rs.getString("location_name"));
				locationVO.setBedTotal_num(rs.getInt("bedTotal_num"));
				locationVO.setBed_price(rs.getInt("bed_price"));
				locationVO.setLocation_pic(rs.getBytes("location_pic"));
				locationVO.setLoc_type(rs.getInt("loc_type"));
				locationVO.setLocation_status(rs.getInt("location_status"));
				list.add(locationVO);
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
		/*新增方法2019-09-23*/
		@Override
		public LocationVO findLocationName(String location_name) {
			LocationVO locationVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_LOCATION_NAME);

				pstmt.setString(1, location_name);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					locationVO = new LocationVO();
					locationVO.setLocation_id(rs.getString("location_id"));
					locationVO.setLocation_name(rs.getString("location_name"));
					locationVO.setBedTotal_num(rs.getInt("bedTotal_num"));
					locationVO.setBed_price(rs.getInt("bed_price"));
					locationVO.setLocation_pic(rs.getBytes("location_pic"));
					locationVO.setLoc_type(rs.getInt("loc_type"));
					locationVO.setLocation_status(rs.getInt("location_status"));

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
			return locationVO;
		}
	
	
	public static void main(String[] args) throws IOException {
//		LocationDAO dao = new LocationDAO();
		
		//insert test
//		LocationVO locationVO1 = new LocationVO();
//		locationVO1.setLocation_id("A0002");
//		locationVO1.setLocation_name("白鬍子牛排");
//		locationVO1.setBedTotal_num(20);
//		locationVO1.setBed_price(10000);
//		
//		byte[] pic2 =dao.getLocPictureByteArray("WebContent/locationPic/A0002.jpg");
//		locationVO1.setLocation_pic(pic2);
//		locationVO1.setLoc_type(0);
//		locationVO1.setLocation_status(1);
//		dao.insert(locationVO1);
//		System.out.println("^____^");
		
		// update test
//		LocationVO locationVO2 = new LocationVO();
//		locationVO2.setLocation_id("A0002");
//		locationVO2.setLocation_name("白鬍子牛排");
//		locationVO2.setBedTotal_num(40);
//		locationVO2.setBed_price(10000);
//		
//		byte[] pic2 = dao.getLocPictureByteArray("WebContent/locationPic/A0002.jpg");
//		locationVO2.setLocation_pic(pic2);
//		locationVO2.setLoc_type(0);
//		locationVO2.setLocation_status(1);
//		dao.update(locationVO2);
//		System.out.println("^____^");
//		
		
		//delete test
//		dao.delete("A0002");
//		
//		System.out.println("^____^");
		
		
//		//query one
//		LocationVO locationVO3 = dao.findByPrimaryKey("A0002");
//		System.out.println(locationVO3.getLocation_id());
//		System.out.println(locationVO3.getLocation_name());
//		System.out.println(locationVO3.getBedTotal_num());
//		System.out.println(locationVO3.getBed_price());
//		
//		byte[] pic = locationVO3.getLocation_pic();
//		readLocPicture(pic);
//		
//		System.out.println(locationVO3.getLocation_pic());
//		System.out.println(locationVO3.getLoc_type());
//		System.out.println(locationVO3.getLocation_status());
//		System.out.println("^____^");
//		
//		
//		List<LocationVO> list = dao.getLocsByLoc_Type(0);
//		for(LocationVO locationVO: list) {
//			System.out.println(locationVO.getLocation_id());
//			System.out.println(locationVO.getLocation_name());
//			System.out.println(locationVO.getBedTotal_num());
//			System.out.println(locationVO.getBed_price());
//			System.out.println(locationVO.getLocation_pic());
//			System.out.println(locationVO.getLoc_type());
//			System.out.println(locationVO.getLocation_status());
//			System.out.println("^____^");
//		}
		
		
	}
	
	
	
	
}
