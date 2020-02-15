package helpers;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import com.member.model.*;

public class BGReader extends HttpServlet {
	
	Connection con;
	
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException {
		
		
		req.setCharacterEncoding("UTF-8");//get的請求編碼
		res.setContentType("image/gif");//輸出的contentType
		ServletOutputStream out = res.getOutputStream();//輸出為2位元資料流
		
		String member_id = ((MemberVO)req.getSession().getAttribute("memberVO")).getMember_id().trim();
		
		String SQL = "SELECT back_img FROM MEMBER WHERE member_id ='"+member_id+"'";
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			
			if(rs.next()) {
				BufferedInputStream bis = new BufferedInputStream(rs.getBinaryStream("back_img"));
				byte[] buf = new byte[4*1024];
				int data;
				while((data = bis.read(buf))!=-1) {
					out.write(buf, 0, data);
				}
				bis.close();
			}else {
				
				InputStream in = getServletContext().getResourceAsStream("/assets/images/noData/nopic.jpg");
				
				byte[] bt = new byte[in.available()];
				in.read(bt);
				out.write(bt);
				
				in.close();

			}
			
			rs.close();
			stmt.close();
			
		} catch (Exception e) {
//			e.printStackTrace();
			
			InputStream in = getServletContext().getResourceAsStream("/assets/images/noData/nopic.jpg");
			
			byte[] bt = new byte[in.available()];
			in.read(bt);
			out.write(bt);
			in.close();
		}
		
	}
	
	public void init() {
		
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
			if(con!=null) {
				con.close();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
