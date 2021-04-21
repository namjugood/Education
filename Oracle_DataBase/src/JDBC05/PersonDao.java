package JDBC05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PersonDao {
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
	
	public ArrayList<PersonDto> select() {
		ArrayList<PersonDto> list = new ArrayList<PersonDto>();
		con=getConnection();
		String sql = "select * from person";
		try {
			pstmt = con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				PersonDto pdto = new PersonDto();
				pdto.setPersonnum(rs.getString("personnum"));
				pdto.setPersonname(rs.getString("personname"));
				pdto.setPhone(rs.getString("phone"));
				pdto.setBirth(rs.getDate("birth"));
				pdto.setEnterdate(rs.getDate("enterdate"));
				pdto.setBpoint(rs.getInt("bpoint"));
				pdto.setAge(rs.getInt("age"));
				pdto.setGender(rs.getString("gender"));
				list.add(pdto);
			} 
		}catch (SQLException e) {
			e.printStackTrace();
		}	
		close();
		return list;
	}

	public int insert(PersonDto pdto) {
		int result = 0;
		String sql = "select * from person";
		con=getConnection();
		try {
			sql = "insert into person values(person_seq.nextVal,?,?,?,sysdate,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,  pdto.getPersonname());
			pstmt.setString(2,  pdto.getPhone());
			pstmt.setDate(3, pdto.getBirth());
			pstmt.setInt(4, pdto.getBpoint());
			pstmt.setInt(5, pdto.getAge());
			pstmt.setString(6,  pdto.getGender());
			result = pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return result;
	}
	
	public int delete(String personnum) {
		int result = 0;
		String sql = "delete from person where personnum=?";
		con=getConnection();	
		try {	
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, personnum);
			result = pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}	
		close();
		return result;
	}
	
	public int update(PersonDto pdto) {
		int result = 0;
		PersonDto dtoOrigin = new PersonDto();;
		String sql = "select * from person where personnum=?";
		con=getConnection();
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,  pdto.getPersonnum());
			rs=pstmt.executeQuery();
			if(rs.next()) {
				dtoOrigin.setPersonnum(rs.getString("personnum"));
				dtoOrigin.setPersonname(rs.getString("personname"));
				dtoOrigin.setPhone(rs.getString("phone"));
				dtoOrigin.setBirth(rs.getDate("birth"));
				dtoOrigin.setBpoint(rs.getInt("bpoint"));
				dtoOrigin.setAge(rs.getInt("age"));
				dtoOrigin.setGender(rs.getString("gender"));
			}
			pstmt.close();
			rs.close();
			if(!pdto.getPersonname().equals("")) dtoOrigin.setPersonname(pdto.getPersonname());
			if(!pdto.getPhone().equals("")) dtoOrigin.setPhone(pdto.getPhone());
			if(pdto.getBirth() !=null) dtoOrigin.setBirth(pdto.getBirth());
			if(pdto.getBpoint()!=0) dtoOrigin.setBpoint(pdto.getBpoint());
			if(pdto.getAge()!=0) dtoOrigin.setAge(pdto.getAge());
			if(!pdto.getGender().equals("")) dtoOrigin.setGender(pdto.getGender());
			
			sql="update person set personname=?, phone=?, birth=?, bpoint=?, age=?, gender=? where "
					+ " personnum=?";
			
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,  dtoOrigin.getPersonname());
			pstmt.setString(2,  dtoOrigin.getPhone());
			pstmt.setDate(3,  dtoOrigin.getBirth());
			pstmt.setInt(4,  dtoOrigin.getBpoint());
			pstmt.setInt(5,  dtoOrigin.getAge());
			pstmt.setString(6,  dtoOrigin.getGender());
			pstmt.setString(7,  dtoOrigin.getPersonnum());
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		close();
		return result;
	}	
	
}
