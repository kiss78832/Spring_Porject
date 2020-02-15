package helpers;

import com.member.model.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class PicReader extends HttpServlet {
	Connection con;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");//請求的編碼
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
/*=========================== 加 .trim();  =================================*/
		
		try {
			String member_id = ((MemberVO)req.getSession().getAttribute("memberVO")).getMember_id();
			
			System.out.println(member_id);
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT m_photo FROM MEMBER WHERE member_id ='"+member_id+"'" );
			if (rs.next()) {
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("m_photo"));
				byte[] buf = new byte[4 * 1024]; // 4K buffer
				int len;

				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);
				}
				in.close();
			} else {//錯誤參數
//				res.sendError(HttpServletResponse.SC_NOT_FOUND);//原本的錯誤葉面
				InputStream in = getServletContext().getResourceAsStream("/assets/images/noData/O.jpg");
				byte[] buf = new byte[in.available()];//抓資料庫不能用available()
				in.read(buf);
				out.write(buf);
				in.close();
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {//捕捉空值
//			System.out.println(e);
			InputStream in = getServletContext().getResourceAsStream("/assets/images/noData/O.jpg");
			byte[] buf = new byte[in.available()];//抓資料庫不能用available()，因為網絡通訊往往是間斷性的
			in.read(buf);
			out.write(buf);
			in.close();
		}
	}

	public void init() throws ServletException {
		try {
			Context ctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB2");
			con = ds.getConnection();
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void destroy() {
		try {
			if (con != null) con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
}
