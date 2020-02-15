package com.article.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ArticleJNDI implements ArticleDAO_interface {
	
	
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

//	to_char(article_time, 'FmMon', 'NLS_DATE_LANGUAGE =American')
	private static final String INSERT_STMT = 
			"INSERT INTO article (article_id,member_id,article_title,article_content,article_image,article_time,watched_c,like_c,tag,article_image_2,article_image_3,image_css)"
			+ " VALUES ('AR'||LPAD(to_char(SQU_APPLICATION.NEXTVAL),6,'0'), ?, ?, ?, ?, CURRENT_TIMESTAMP, ?, ?, ?, ?, ?,?)";
		private static final String GET_ALL_STMT = 
			"SELECT member_id,article_id,article_title,article_content,article_image,article_time,watched_c,like_c,tag,article_image_2,article_image_3,image_css FROM ARTICLE order by article_id DESC";
		private static final String GET_ONE_STMT = 
			"SELECT member_id,article_id,article_title,article_content,article_image,to_char(article_time,'yyyy-mm-dd hh:mm:ss') article_time,watched_c,like_c,tag,article_image_2,article_image_3,image_css FROM ARTICLE where article_id = ? ";
		private static final String DELETE = 
			"DELETE FROM article where article_id = ?";
		private static final String UPDATE = 
			"UPDATE article set member_id=?,article_title=?,article_content=?,article_image=?,article_time=CURRENT_TIMESTAMP,watched_c=?,like_c=?,tag=? ,article_image_2 = ?, article_image_3 = ? ,image_css= ?  where article_id = ? ";
		private static final String FIND_TAG = 
			"SELECT * FROM article where tag like ? ";
		private static final String MSG_COUNT = 
				"SELECT COUNT(*) FROM message where article_id =?";
		/*2019-09-19新增*/
		private static final String All_STATUS = 
				"SELECT * FROM article where article_status =?";

		@Override
		public void insert(ArticleVO articleVO) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);
			
				pstmt.setString(1, articleVO.getMember_id());
				pstmt.setString(2, articleVO.getArticle_title());
				pstmt.setString(3, articleVO.getArticle_content());
				pstmt.setString(4, articleVO.getArticle_image());
				pstmt.setInt(5, articleVO.getWatched_c());
				pstmt.setInt(6, articleVO.getLike_c());
				pstmt.setString(7, articleVO.getTag());
				pstmt.setString(8, articleVO.getArticle_image_2());
				pstmt.setString(9, articleVO.getArticle_image_3());
				pstmt.setString(10, articleVO.getImage_css());
					
				pstmt.executeUpdate();

			}catch (SQLException se) {
				se.printStackTrace();
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
		public void update(ArticleVO articleVO) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);
			
				pstmt.setString(1, articleVO.getMember_id());
				pstmt.setString(2, articleVO.getArticle_title());
				pstmt.setString(3, articleVO.getArticle_content());
				pstmt.setString(4, articleVO.getArticle_image());
				pstmt.setInt(5, articleVO.getWatched_c());
				pstmt.setInt(6, articleVO.getLike_c());
				pstmt.setString(7, articleVO.getTag());
				pstmt.setString(8, articleVO.getArticle_image_2());
				pstmt.setString(9, articleVO.getArticle_image_3());
				pstmt.setString(10, articleVO.getImage_css());
				pstmt.setString(11, articleVO.getArticle_id());
				
					
				pstmt.executeUpdate();

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

				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE);

				pstmt.setString(1, article_id);

				pstmt.executeUpdate();

				// Handle any driver errors
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
		public ArticleVO findByPrimaryKey(String article_id) {
			
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ArticleVO articleVO = null;
			ResultSet rs = null;
			
		

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);
				
				pstmt.setString(1,article_id);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					
					articleVO = new ArticleVO();
					articleVO.setMember_id(rs.getString("member_id"));
					articleVO.setArticle_id(rs.getString("article_id"));
					articleVO.setArticle_title(rs.getString("article_title"));
					articleVO.setArticle_content(rs.getString("article_content"));
					articleVO.setArticle_image(rs.getString("article_image"));
					articleVO.setArticle_time(rs.getTimestamp("article_time"));
					articleVO.setWatched_c(rs.getInt("watched_c"));
					articleVO.setLike_c(rs.getInt("like_c"));
					articleVO.setTag(rs.getString("tag"));
					articleVO.setArticle_image_2(rs.getString("article_image_2"));
					articleVO.setArticle_image_3(rs.getString("article_image_3"));
					articleVO.setImage_css(rs.getString("image_css"));
					
				}
				

			}catch (SQLException se) {
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
			return articleVO;
			
		}
		
		
		@Override
		public List<ArticleVO> getAll() {

			
			List<ArticleVO> list = new ArrayList<ArticleVO>();
			Connection con = null;
			PreparedStatement pstmt = null;
			ArticleVO articleVO = null;
			ResultSet rs = null;


			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT);
				
				rs = pstmt.executeQuery();

				while (rs.next()) {
					articleVO = new ArticleVO();
					articleVO.setMember_id(rs.getString("member_id"));
					articleVO.setArticle_id(rs.getString("article_id"));
					articleVO.setArticle_title(rs.getString("article_title"));
					articleVO.setArticle_content(rs.getString("article_content"));
					articleVO.setArticle_image(rs.getString("article_image"));
					articleVO.setArticle_time(rs.getTimestamp("article_time"));
					articleVO.setWatched_c(rs.getInt("watched_c"));
					articleVO.setLike_c(rs.getInt("like_c"));
					articleVO.setTag(rs.getString("tag"));
					articleVO.setArticle_image_2(rs.getString("article_image_2"));
					articleVO.setArticle_image_3(rs.getString("article_image_3"));
					articleVO.setImage_css(rs.getString("image_css"));
					list.add(articleVO);
				}
				

			}catch (SQLException se) {
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
		public List<ArticleVO> find_tag(String element) {
			List<ArticleVO> list = new ArrayList<ArticleVO>();
			Connection con = null;
			PreparedStatement pstmt = null;
			ArticleVO articleVO = null;
			ResultSet rs = null;

			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(FIND_TAG);
				
				pstmt.setString(1,"%"+element+"%");
				rs = pstmt.executeQuery();

				while (rs.next()) {
					articleVO = new ArticleVO();
					articleVO.setMember_id(rs.getString("member_id"));
					articleVO.setArticle_id(rs.getString("article_id"));
					articleVO.setArticle_title(rs.getString("article_title"));
					articleVO.setArticle_content(rs.getString("article_content"));
					articleVO.setArticle_image(rs.getString("article_image"));
					articleVO.setArticle_time(rs.getTimestamp("article_time"));
					articleVO.setWatched_c(rs.getInt("watched_c"));
					articleVO.setLike_c(rs.getInt("like_c"));
					articleVO.setTag(rs.getString("tag"));
					articleVO.setArticle_image_2(rs.getString("article_image_2"));
					articleVO.setArticle_image_3(rs.getString("article_image_3"));
					articleVO.setImage_css(rs.getString("image_css"));
					list.add(articleVO);
				}
				

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
		public Integer msg_count(String article_id) {

			Connection con = null;
			PreparedStatement pstmt = null;
			int msg_count = 0;
			ResultSet rs = null;
			
		

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(MSG_COUNT);
				
				pstmt.setString(1,article_id);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					msg_count = rs.getInt(1);
				}
				

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
			return msg_count;
		}
		
		
		
		/*2019-09-19新增*/		
		@Override
		public List<ArticleVO> getAll_status(Integer article_status) {
			List<ArticleVO> list = new ArrayList<ArticleVO>();
			Connection con = null;
			PreparedStatement pstmt = null;
			ArticleVO articleVO = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(All_STATUS);
				
				pstmt.setInt(1,article_status);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					articleVO = new ArticleVO();
					articleVO.setMember_id(rs.getString("member_id"));
					articleVO.setArticle_id(rs.getString("article_id"));
					articleVO.setArticle_title(rs.getString("article_title"));
					articleVO.setArticle_content(rs.getString("article_content"));
					articleVO.setArticle_image(rs.getString("article_image"));
					articleVO.setArticle_time(rs.getTimestamp("article_time"));
					articleVO.setWatched_c(rs.getInt("watched_c"));
					articleVO.setLike_c(rs.getInt("like_c"));
					articleVO.setTag(rs.getString("tag"));
					articleVO.setArticle_image_2(rs.getString("article_image_2"));
					articleVO.setArticle_image_3(rs.getString("article_image_3"));
					list.add(articleVO);
				}
				

			}  catch (SQLException se) {
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

			ArticleJNDI dao = new ArticleJNDI();
			
			//新增
//			ArticleVO articleVO1 = new ArticleVO();
//			articleVO1.setMember_id("A001");
//			articleVO1.setArticle_title("安安你好");
//			articleVO1.setArticle_content("一點都不好");
//			articleVO1.setArticle_image(null);
//			articleVO1.setWatched_c(12);
//			articleVO1.setLike_c(21);
//			articleVO1.setTag("玉山11");
//			articleVO1.setArticle_image_2("334455");
//			articleVO1.setArticle_image_3("336699");
//			articleVO1.setImage_css("aaaaaaa");
//			dao.insert(articleVO1);

	//
//			// 修改
//			ArticleVO articleVO1 = new ArticleVO();
//			articleVO1.setMember_id("A001");
//			articleVO1.setArticle_title("安安");
//			articleVO1.setArticle_content("好");
//			articleVO1.setArticle_image(null);
//			articleVO1.setWatched_c(12);
//			articleVO1.setLike_c(21);
//			articleVO1.setTag("玉山");
//			articleVO1.setArticle_image_2(null);
//			articleVO1.setArticle_image_3(null);
//			articleVO1.setImage_css("ssss");
//			articleVO1.setArticle_id("AR000016");
//			dao.update(articleVO1);
//			
			
//			// 刪除
			dao.delete("AR000011");
	//
			// 查詢
			
//			ArticleVO articleVO3 = dao.findByPrimaryKey("AR000001");
//			System.out.print(articleVO3.getMember_id() + ",");
//			System.out.print(articleVO3.getArticle_id() + ",");
//			System.out.print(articleVO3.getArticle_title() + ",");
//			System.out.print(articleVO3.getArticle_content() + ",");
//			System.out.print(articleVO3.getArticle_image() + ",");
//			System.out.print(articleVO3.getArticle_time().toString()+",");
//			System.out.print(articleVO3.getWatched_c() + ",");
//			System.out.print(articleVO3.getLike_c() + ",");
//			System.out.print(articleVO3.getTag() + ",");
//			System.out.print(articleVO3.getArticle_image_2() + ",");
//			System.out.print(articleVO3.getArticle_image_3() + ",");
//			System.out.println("---------------------");
	//
//			
			
//			// 查詢
//			List<ArticleVO> list = dao.getAll();
//			for (ArticleVO articleVO3 : list) {
//				System.out.print(articleVO3.getMember_id() + ",");
//				System.out.print(articleVO3.getArticle_id() + ",");
//				System.out.print(articleVO3.getArticle_title() + ",");
//				System.out.print(articleVO3.getArticle_content() + ",");
//				System.out.print(articleVO3.getArticle_image() + ",");
//				System.out.print(articleVO3.getArticle_time().toString()+",");
//				System.out.print(articleVO3.getWatched_c() + ",");
//				System.out.print(articleVO3.getLike_c() + ",");
//				System.out.print(articleVO3.getTag() + ",");
//				System.out.print(articleVO3.getArticle_image_2() + ",");
//				System.out.println(articleVO3.getArticle_image_3() + ",");
//				System.out.println(articleVO3.getImage_css() + ",");
//				System.out.print("---------------------");
//				
//			}
		}



		



		
	}
