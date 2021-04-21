package JDBC02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Booklist_Select {

	public static void main(String[] args) {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "scott";
		String pw = "tiger";
		String driver = "oracle.jdbc.driver.OracleDriver";
		Connection con = null;
		PreparedStatement pstmt = null;	// SQL 연결 및 실행클래스
		ResultSet rs = null;	// SQL 명령수행 결과를 저장하는 클래스
		
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, id, pw);
			// 데이터베이스 연결 후에는 SQL 명령을 사용하기 위해 String 변수에 SQL 명령을 준비합니다.
			// 데이터베이스에 제공되어질 명령이므로 String 형으로 준비합니다.
			String sql = "select * from booklist";
			
			// preparedStatement : SQL 문을 Connection 객체에 적용하여 결과를 얻어낼 도구
			pstmt = con.prepareStatement(sql);	// 도구 연결(준비)
			// SQL 명령에 의해 얻어진 결과를 rs에 저장합니다
			rs = pstmt.executeQuery(); // SQL 실행결과 리턴 : 형식 ResultSet
			System.out.println("번호\t책제목\t발행년도\t입고가격\t출고가격\t등급");
			System.out.println("------------------------------------------------------");
			//rs.next() : 다음 레코드로 이동.		다른 레코드가 있으면 true, 없으면 false 리턴
			while(rs.next()) {
				int booknum = rs.getInt("booknum");
				String subject = rs.getString("subject");
				int makeyear = rs.getInt("makeyear");
				int inprice = rs.getInt("inprice");
				int outprice = rs.getInt("outprice");
				String grade = rs.getString("grade");
				System.out.printf("%d\t%s\t%d\t%d\t%d\t%s\n", booknum, subject, makeyear, inprice, outprice, grade);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			if(con != null) con.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}

	}

}