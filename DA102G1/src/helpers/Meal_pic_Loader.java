package helpers;

import java.sql.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.io.*;
import java.nio.file.Files; // Java 7 : java.nio.file

public class Meal_pic_Loader {

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
		for (int i = 1; i <= 12; i++) {

			try {
				con = DriverManager.getConnection(url, userid, passwd);
				File pic1 = null;

				if (i == 1) {
					id = "M001";
					pic1 = new File(
							"C:/DA102_WebApp/eclipse_WTP_workspace/DA102G1/WebContent/assets/images/Meal/M001.jpg");
				} else if (i == 2) {
					id = "M002";
					pic1 = new File(
							"C:/DA102_WebApp/eclipse_WTP_workspace/DA102G1/WebContent/assets/images/Meal/M002.jpg");
				} else if (i == 3) {
					id = "M003";
					pic1 = new File(
							"C:/DA102_WebApp/eclipse_WTP_workspace/DA102G1/WebContent/assets/images/Meal/M003.jpg");
				} else if (i == 4) {
					id = "M004";
					pic1 = new File(
							"C:/DA102_WebApp/eclipse_WTP_workspace/DA102G1/WebContent/assets/images/Meal/M004.jpg");
				} else if (i == 5) {
					id = "M005";
					pic1 = new File(
							"C:/DA102_WebApp/eclipse_WTP_workspace/DA102G1/WebContent/assets/images/Meal/M005.jpg");
				} else if (i == 6) {
					id = "M006";
					pic1 = new File(
							"C:/DA102_WebApp/eclipse_WTP_workspace/DA102G1/WebContent/assets/images/Meal/M006.jpg");
				} else if (i == 7) {
					id = "M007";
					pic1 = new File(
							"C:/DA102_WebApp/eclipse_WTP_workspace/DA102G1/WebContent/assets/images/Meal/M007.jpg");
				} else if (i == 8) {
					id = "M008";
					pic1 = new File(
							"C:/DA102_WebApp/eclipse_WTP_workspace/DA102G1/WebContent/assets/images/Meal/M008.jpg");
				} else if (i == 9) {
					id = "M009";
					pic1 = new File(
							"C:/DA102_WebApp/eclipse_WTP_workspace/DA102G1/WebContent/assets/images/Meal/M009.jpg");
				} else if (i == 10) {
					id = "M010";
					pic1 = new File(
							"C:/DA102_WebApp/eclipse_WTP_workspace/DA102G1/WebContent/assets/images/Meal/M010.jpg");
				} else if (i == 11) {
					id = "M011";
					pic1 = new File(
							"C:/DA102_WebApp/eclipse_WTP_workspace/DA102G1/WebContent/assets/images/Meal/M011.jpg");
				} else if (i == 12) {
					id = "M012";
					pic1 = new File(
							"C:/DA102_WebApp/eclipse_WTP_workspace/DA102G1/WebContent/assets/images/Meal/M012.jpg");
				}

				byte[] buffer1 = Files.readAllBytes(pic1.toPath()); // Java 7 : java.nio.file

				System.out.println("\n\nUpdate the database... ");
				pstmt = con.prepareStatement("UPDATE MEAL SET MEAL_PIC = ? WHERE MEAL_ID = ?");

				pstmt.setBytes(1, buffer1);

				pstmt.setString(2, id);
				int rowsUpdated = pstmt.executeUpdate();

				System.out.print("Changed " + rowsUpdated);

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
