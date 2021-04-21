package com.ezen.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ezen.dto.MemberDto;

public class MemberDao {
	// 싱클턴 이용
	private MemberDao() {	}	// 생성자는 private로 외부 호출을 차단합니다.
	private static MemberDao ist = new MemberDao();
	// 차단된 생성자는 클래스 내부에서 호출이 가능하므로, private로 외부차단된 유일한 인스턴스를
	// 만들고 스태틱으로 선언된 레퍼런스 변수에 저장합니다.
	public static MemberDao getInstance() { return ist; }
	// 클래스의 인스턴스가 필요한 곳에서 리턴받아 사요가능한 ist를 리턴해주는 함수를 public으로 생성합니다.
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String driver= "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String uid = "scott";
	String pass = "tiger";
	
	public ArrayList<MemberDto> selectAll() {
		ArrayList<MemberDto> list = new ArrayList<MemberDto>();
		String sql = "select * from member";
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, uid, pass);
			pstmt = con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				MemberDto mdto = new MemberDto();
				mdto.setUserid(rs.getString("userid"));
				mdto.setName(rs.getString("name"));
				mdto.setPwd(rs.getString("pwd"));
				mdto.setEmail(rs.getString("email"));
				mdto.setPhone(rs.getString("phone"));
				mdto.setAdmin(rs.getInt("admin"));
				list.add(mdto);
			}	
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(con != null) con.close();
				if(pstmt != null) pstmt.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	
	
	
	public int update(MemberDto mdto) {
		int result=0;
		String sql = "update member set name=?, pwd=?, email=?, phone=?, "
				+ " admin=? where userid=?";
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, uid, pass);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mdto.getName());
			pstmt.setString(2, mdto.getPwd());
			pstmt.setString(3, mdto.getEmail());
			pstmt.setString(4, mdto.getPhone());
			pstmt.setInt(5, mdto.getAdmin());
			pstmt.setString(6, mdto.getUserid());
			
			result=pstmt.executeUpdate();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(con != null) con.close();
				if(pstmt != null) pstmt.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	
	
	public int delete(String userid) {
		int result=0;
		String sql = "delete from member where userid=?";
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, uid, pass);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			result=pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(con != null) con.close();
				if(pstmt != null) pstmt.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	

	public int insertMember(MemberDto mdto) {
		int result=0;
		String sql = "insert into member values(?,?,?,?,?,?)";
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, uid, pass);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mdto.getName());
			pstmt.setString(2, mdto.getUserid());
			pstmt.setString(3, mdto.getPwd());
			pstmt.setString(4, mdto.getEmail());
			pstmt.setString(5, mdto.getPhone());
			pstmt.setInt(6, mdto.getAdmin());
			
			/* 가입 오류발생
			pstmt.setString(1, "name");
			pstmt.setString(2, "userid");
			pstmt.setString(3, "pwd");
			pstmt.setString(4, "email");
			pstmt.setString(5, "phone");
			pstmt.setInt(6, Integer.parseInt("admin"));
			*/
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(con != null) con.close();
				if(pstmt != null) pstmt.close();
				if(rs != null) rs.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	
	public int confirmID(String userid) {
		int result = 0;
		String sql = "select userid from member where userid=?";
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, uid, pass);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				result=1;	// 아이디 중복되어 사용 불가능
			}else {
				result=0;	// 사용 가능
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(con != null) con.close();
				if(pstmt != null) pstmt.close();
				if(rs != null) rs.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	

	public MemberDto selectMember(String userid) {
		MemberDto mdto = null;
		String sql = "select * from member where userid=?";
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, uid, pass);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				mdto=new MemberDto();
				mdto.setUserid(rs.getString("userid"));
				mdto.setName(rs.getString("name"));
				mdto.setPwd(rs.getString("pwd"));
				mdto.setEmail(rs.getString("email"));
				mdto.setPhone(rs.getString("phone"));
				mdto.setAdmin(rs.getInt("admin") );
			}
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(con != null) con.close();
				if(pstmt != null) pstmt.close();
				if(rs != null) rs.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return mdto;
	}



	

	


	

}
