package JDBC02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class BookList_Update {

	public static void main(String[] args) {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, "scott", "tiger");
			Scanner sc = new Scanner(System.in);
			System.out.print("수정할 도서의 도서번호를 선택하세요 : ");
			String num = sc.nextLine();
			System.out.print("수정할 항목을 선택하세요. 1. 제목 2. 출판년도 3. 출고가격");
			String input = sc.nextLine();
			String sql = null;
			switch(input) {
			case "1":
				System.out.print("수정할 도서의 제목을 입력하세요 : ");
				String subject = sc.nextLine();
				sql = "Update booklist set name = ? where booknum = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1,subject);
				pstmt.setString(2, num);
				break;
			case "2":
				System.out.print("수정할 출판년도를 입력하세요 : ");
				String makeyear = sc.nextLine();
				sql = "Update booklist set makeyear = ? where booknum = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1,makeyear);
				pstmt.setString(2, num);
				break;
			case "3":
				System.out.print("수정할 출고가격을 입력하세요 : ");
				String outprice = sc.nextLine();
				sql = "Update booklist set outprice = ? where booknumnum = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1,outprice);
				pstmt.setString(2, num);
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