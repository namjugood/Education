package JDBC04;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class In_OutDao {
	static String driver = "oracle.jdbc.driver.OracleDriver";
	static String url = "jdbc:oracle:thin:@localhost:1521:xe";
	static Connection con = null;
	static PreparedStatement pstmt = null;
	static ResultSet rs = null;
	
	public static Connection getConnection() {
		Connection cnn = null;
		try {
			Class.forName(driver);
			cnn = DriverManager.getConnection(url, "scott", "tiger");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return cnn;
	}
	
	public static void close() {
		try {
			if(pstmt != null) pstmt.close();
			if(con != null) con.close();
			if(rs != null) rs.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<In_OutDto> select() {
		ArrayList<In_OutDto> list = new ArrayList<In_OutDto>();
		con=getConnection();
		String sql = "select * from in_out";
		try {
			pstmt = con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				In_OutDto idto = new In_OutDto();
				idto.setOut_date(rs.getDate("out_date"));
				idto.setIndexk(rs.getInt("indexk"));
				idto.setBooknum(rs.getString("booknum"));
				idto.setPersonnum(rs.getString("personnum"));
				idto.setDiscount(rs.getInt("discount"));
				list.add(idto);
			} 
		}catch (SQLException e) {
			e.printStackTrace();
		}	
		close();
		return list;
	}
	
	public int insert(In_OutDto idto) {
		int result = 0;
		con=getConnection();
		String sql = "insert into in_out values(sysdate,?,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,  idto.getIndexk());
			pstmt.setString(2, idto.getBooknum());
			pstmt.setString(3, idto.getPersonnum());
			pstmt.setInt(4, idto.getDiscount());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return result;
	}
}
