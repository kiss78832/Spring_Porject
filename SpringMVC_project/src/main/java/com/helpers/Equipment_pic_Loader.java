package com.helpers;

import java.sql.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.io.*;
import java.nio.file.Files; // Java 7 : java.nio.file

public class Equipment_pic_Loader {
	
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "DA102G1";
	private static final String PASSWORD = "123456";
	
	private static final String UPDATE = "UPDATE EQUIPMENT set EQ_PIC=?,EQ_DETAIL=? where EQ_NUM = ?";

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
		
		String id = null;
		for (int i = 1; i <= 12; i++) {

//			String picName1 = i + "equipment.jpg";
//			String picName2 = (i+10) + "equipment.jpg";

			try {
				Class.forName(DRIVER);
				con = DriverManager.getConnection(URL, USER, PASSWORD);
				
				String detail = null;
				byte[] pic = null;

				switch (i) {
				
				case 1:
					id = "EQ00000001";
					pic = getPictureByteArray("C:/DA102_WebApp/eclipse_WTP_workspace/DA102G1/WebContent/assets/images/EquipmentData/BLOB/01_tent001.jpg");
					detail = getLongString("C:/DA102_WebApp/eclipse_WTP_workspace/DA102G1/WebContent/assets/images/EquipmentData/CLOB/01_tent001.txt");
					
					break;
				case 2:
					id = "EQ00000002";
					pic = getPictureByteArray("C:/DA102_WebApp/eclipse_WTP_workspace/DA102G1/WebContent/assets/images/EquipmentData/BLOB/02_tent002.jpg");
					detail = getLongString("C:/DA102_WebApp/eclipse_WTP_workspace/DA102G1/WebContent/assets/images/EquipmentData/CLOB/02_tent002.txt");
					break;
				case 3:
					id = "EQ00000003";
					pic = getPictureByteArray("C:/DA102_WebApp/eclipse_WTP_workspace/DA102G1/WebContent/assets/images/EquipmentData/BLOB/03_Alpenstock001.jpg");
					detail = getLongString("C:/DA102_WebApp/eclipse_WTP_workspace/DA102G1/WebContent/assets/images/EquipmentData/CLOB/03_Alpenstock001.txt");
					
					break;
				case 4:
					id = "EQ00000004";
					pic = getPictureByteArray("C:/DA102_WebApp/eclipse_WTP_workspace/DA102G1/WebContent/assets/images/EquipmentData/BLOB/04_Alpenstock002.jpg");
					detail = getLongString("C:/DA102_WebApp/eclipse_WTP_workspace/DA102G1/WebContent/assets/images/EquipmentData/CLOB/04_Alpenstock002.txt");
					break;
					
					
				case 5:
					id = "EQ00000005";
					pic = getPictureByteArray("C:/DA102_WebApp/eclipse_WTP_workspace/DA102G1/WebContent/assets/images/EquipmentData/BLOB/05_HeadLight001.jpg");
					detail = getLongString("C:/DA102_WebApp/eclipse_WTP_workspace/DA102G1/WebContent/assets/images/EquipmentData/CLOB/05_HeadLight001.txt");
					
					break;
				case 6:
					id = "EQ00000006";
					pic = getPictureByteArray("C:/DA102_WebApp/eclipse_WTP_workspace/DA102G1/WebContent/assets/images/EquipmentData/BLOB/06_HeadLight002.jpg");
					detail = getLongString("C:/DA102_WebApp/eclipse_WTP_workspace/DA102G1/WebContent/assets/images/EquipmentData/CLOB/06_HeadLight002.txt");
					
					break;
				case 7:
					id = "EQ00000007";
					pic = getPictureByteArray("C:/DA102_WebApp/eclipse_WTP_workspace/DA102G1/WebContent/assets/images/EquipmentData/BLOB/07_HeadLight003.jpg");
					detail = getLongString("C:/DA102_WebApp/eclipse_WTP_workspace/DA102G1/WebContent/assets/images/EquipmentData/CLOB/07_HeadLight003.txt");
					
					break;
				case 8:
					id = "EQ00000008";
					pic = getPictureByteArray("C:/DA102_WebApp/eclipse_WTP_workspace/DA102G1/WebContent/assets/images/EquipmentData/BLOB/08_IceAxe001.jpg");
					detail = getLongString("C:/DA102_WebApp/eclipse_WTP_workspace/DA102G1/WebContent/assets/images/EquipmentData/CLOB/08_IceAxe001.txt");
					
					break;
				case 9:
					id = "EQ00000009";
					pic = getPictureByteArray("C:/DA102_WebApp/eclipse_WTP_workspace/DA102G1/WebContent/assets/images/EquipmentData/BLOB/09_HeadGuard001.jpg");
					detail = getLongString("C:/DA102_WebApp/eclipse_WTP_workspace/DA102G1/WebContent/assets/images/EquipmentData/CLOB/09_HeadGuard001.txt");
					
					break;
				case 10:
					id = "EQ00000010";
					pic = getPictureByteArray("C:/DA102_WebApp/eclipse_WTP_workspace/DA102G1/WebContent/assets/images/EquipmentData/BLOB/10_Line001.jpg");
					detail = getLongString("C:/DA102_WebApp/eclipse_WTP_workspace/DA102G1/WebContent/assets/images/EquipmentData/CLOB/10_Line001.txt");
					
					break;
				case 11:
					id = "EQ00000011";
					pic = getPictureByteArray("C:/DA102_WebApp/eclipse_WTP_workspace/DA102G1/WebContent/assets/images/EquipmentData/BLOB/11_ProtectedWear001.jpg");
					detail = getLongString("C:/DA102_WebApp/eclipse_WTP_workspace/DA102G1/WebContent/assets/images/EquipmentData/CLOB/11_ProtectedWear001.txt");
					
					break;
				case 12:
					id = "EQ00000012";
					pic = getPictureByteArray("C:/DA102_WebApp/eclipse_WTP_workspace/DA102G1/WebContent/assets/images/EquipmentData/BLOB/12_FootWear001.jpg");
					detail = getLongString("C:/DA102_WebApp/eclipse_WTP_workspace/DA102G1/WebContent/assets/images/EquipmentData/CLOB/12_FootWear001.txt");
					
					break;
				}		
			
				System.out.println("\n\n Update the database... ");
				pstmt = con.prepareStatement(UPDATE);
				
				pstmt.setBytes(1, pic);
				pstmt.setString(2, detail);
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

	// CLOB寫入
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

}
