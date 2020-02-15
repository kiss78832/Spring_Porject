package helpers;


import java.sql.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.io.*;
import java.nio.file.Files; // Java 7 : java.nio.file

public class Member_pic_Loader {

//	static {
//		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	public static void main(String argv[]) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String userid = "DA102G1";
		String passwd = "123456";
		String id = null;
		for (int i = 1; i <= 7; i++) {

//			String picName1 = i + "c.jpg";
//			String picName2 = (i+10) + "c.jpg";


			try {
				con = DriverManager.getConnection(url, userid, passwd);
				File pic1 = null;
				File pic2 = null;

				
				if (i == 1) {
					id = "A000";
					pic1 = new File("C:/DA102_WebApp/eclipse_WTP_workspace/DA102G1/WebContent/assets/images/snowpeak/fire3.jpg");
					pic2 = new File("C:/DA102_WebApp/eclipse_WTP_workspace/DA102G1/WebContent/assets/images/Profile_picture/Bear-Grylls-quotes-1x1.jpg");
				}else if(i == 2){
					id = "A001";
					pic1 = new File("C:/DA102_WebApp/eclipse_WTP_workspace/DA102G1/WebContent/assets/images/snowpeak/forest-1743206.jpg");
					pic2 = new File("C:/DA102_WebApp/eclipse_WTP_workspace/DA102G1/WebContent/assets/images/Profile_picture/little_wu.gif");
				}else if(i == 3){//
					id = "A002";
					pic1 = new File("C:/DA102_WebApp/eclipse_WTP_workspace/DA102G1/WebContent/assets/images/snowpeak/forest-1743206.jpg");
					pic2 = new File("C:/DA102_WebApp/eclipse_WTP_workspace/DA102G1/WebContent/assets/images/Profile_picture/unnamed.jpg");

				}else if(i == 4){//
					id = "A003";
					pic1 = new File("C:/DA102_WebApp/eclipse_WTP_workspace/DA102G1/WebContent/assets/images/snowpeak/forest-1743206.jpg");
					pic2 = new File("C:/DA102_WebApp/eclipse_WTP_workspace/DA102G1/WebContent/assets/images/Profile_picture/FISH.png");

				}else if(i == 5){//
					id = "A004";
					pic1 = new File("C:/DA102_WebApp/eclipse_WTP_workspace/DA102G1/WebContent/assets/images/snowpeak/forest-1743206.jpg");
					pic2 = new File("C:/DA102_WebApp/eclipse_WTP_workspace/DA102G1/WebContent/assets/images/Profile_picture/linkusu.jpg");
				}else if(i == 6){//
					id = "A005";
					pic1 = new File("C:/DA102_WebApp/eclipse_WTP_workspace/DA102G1/WebContent/assets/images/snowpeak/forest-1743206.jpg");
					pic2 = new File("C:/DA102_WebApp/eclipse_WTP_workspace/DA102G1/WebContent/assets/images/Profile_picture/pika.jpg");
				}else if(i == 7){//
					id = "A006";
					pic1 = new File("C:/DA102_WebApp/eclipse_WTP_workspace/DA102G1/WebContent/assets/images/snowpeak/forest-1743206.jpg");
					pic2 = new File("C:/DA102_WebApp/eclipse_WTP_workspace/DA102G1/WebContent/assets/images/Profile_picture/monaluto.jpg");
				}
				byte[] buffer1 = Files.readAllBytes(pic1.toPath()); // Java 7 : java.nio.file
				byte[] buffer2 = Files.readAllBytes(pic2.toPath()); // Java 7 : java.nio.file


				System.out.println("\n\nUpdate the database... ");
				pstmt = con.prepareStatement("UPDATE MEMBER SET BACK_IMG = ?, M_PHOTO = ? WHERE MEMBER_ID = ?");

				pstmt.setBytes(1, buffer1);
				pstmt.setBytes(2, buffer2);

				pstmt.setString(3, id);
				int rowsUpdated = pstmt.executeUpdate();

				System.out.print("Changed " + rowsUpdated);

				if (1 == rowsUpdated)
					System.out.println(" row.");
				else
					System.out.println(" rows.");

				pstmt.close();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					con.close();
				} catch (SQLException e) {
				}
			}

		}
	}

}
