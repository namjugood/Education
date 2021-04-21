package JDBC02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class BookList_Insert {

	public static void main(String[] args) {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, "scott", "tiger");
			Scanner sc = new Scanner(System.in);
			System.out.print("책 이름을 입력하세요 : ");
			String subject = sc.nextLine();
			System.out.print("발행 년도를 입력하세요 : ");
			String makeyear = sc.nextLine();
			System.out.print("구매 가격을 입력하세요 : ");
			String inprice = sc.nextLine();
			System.out.print("판매가격을 입력하세요 : ");
			String outprice = sc.nextLine();
			System.out.print("구매 등급을 입력하세요 : ");
			String grade = sc.nextLine();

			String sql = "insert into Booklist values(booklist_seq.nextVal,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,  subject);
			pstmt.setInt(2,  Integer.parseInt(makeyear));
			pstmt.setInt(3,  Integer.parseInt(inprice));
			pstmt.setInt(4,  Integer.parseInt(outprice));
			pstmt.setString(5,  grade);
			
			int result = pstmt.executeUpdate();
			if(result==1)	System.out.println("저장 성공");
			else System.out.println("저장 실패");
			
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