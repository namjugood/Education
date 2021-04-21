package JDBC01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// 자바에서 지원하는 데이터베이스 연결 클래스
public class JDBC_Connect {

	public static void main(String[] args) {
		Connection con = null;	// 연결된 Connection 객체의 주소를 저장할 레퍼런스 변수
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "scott";
		String pw = "tiger";
		String driver = "oracle.jdbc.driver.OracleDriver";
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,	id, pw);
			// 입력되는 첫 번째 값 : 연결 URL
			// 두 번째 값 : 연결 Driver class
			// 세 번째 값 : 사용자 계정 ID
			// 네 번째 값 : 사용자 계정 password
			// getConnection() 메서드의 리턴값 : 연결된 Database Connection 객체
			System.out.println("데이터베이스 연결 성공");
		} catch (SQLException e) {
			System.out.println("데이터베이스 연결 실패 : DB 연결 정보 오류");
		} catch (ClassNotFoundException e) {
			System.out.println("데이터베이스 연결 실패 : 드라이버 클래스 파일 오류");
		}catch (Exception e) {
		System.out.println("기타의 사유로 인한 연결 실패");
		}
		try {
			if(con != null) con.close();
			System.out.println("데이터베이스 종료");
		}catch(SQLException e) { }
		
	}

}
