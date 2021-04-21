package JDBC01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class JDBC_Update {

	public static void main(String[] args) {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, "scott", "tiger");
			Scanner sc = new Scanner(System.in);
			System.out.print("수정할 회원의 번호를 선택하세요 : ");
			String num = sc.nextLine();
			System.out.print("수정할 항목을 선택하세요. 1. 이름 2. 이메일 3. 전화");
			String input = sc.nextLine();
			String sql = null;
			switch(input) {
			case "1":
				System.out.print("수정할 이름을 입력하세요 : ");
				String name = sc.nextLine();
				sql = "Update customer set name = ? where num = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1,name);
				pstmt.setInt(2, Integer.parseInt(num));
				break;
			case "2":
				System.out.print("수정할 이메일을 입력하세요 : ");
				String email = sc.nextLine();
				sql = "Update customer set email = ? where num = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1,email);
				pstmt.setInt(2, Integer.parseInt(num));
				break;
			case "3":
				System.out.print("수정할 전화번호를 입력하세요 : ");
				String tel = sc.nextLine();
				sql = "Update customer set tel = ? where num = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1,tel);
				pstmt.setInt(2, Integer.parseInt(num));
				break;
			}
			int result = pstmt.executeUpdate();
			if(result == 1)		System.out.println("수정 성공");
			else System.out.println("수정 실패");
		}catch (SQLException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			if(pstmt != null) pstmt.close();
			if(con != null) con.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}	
	}

}
