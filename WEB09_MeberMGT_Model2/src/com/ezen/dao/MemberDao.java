package com.ezen.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ezen.dto.MemberDto;
import com.ezen.util.DatabaseManager;

public class MemberDao {
	private MemberDao() {}
	private static MemberDao ist = new MemberDao();
	public static MemberDao getInstance(){ return ist; }
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public ArrayList<MemberDto> selectAll() {
		ArrayList <MemberDto> list = new ArrayList<MemberDto>();
		String sql = "select * from member";
		con=DatabaseManager.getConnection();
		try {
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				MemberDto mdto = new MemberDto();
				mdto.setUserid(rs.getString("userid"));
				mdto.setName(rs.getString("name"));
				mdto.setPwd(rs.getString("pwd"));
				mdto.setEmail(rs.getString("email"));
				mdto.setAdmin(rs.getInt("admin"));
				list.add(mdto);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		DatabaseManager.close(con, pstmt, rs);
		return list;
	}
	
	
	public int updateMember(MemberDto mdto) {
		int result = 0;
		String sql = "update member set name=?, pwd=?, email=?, phone=?, admin=?"
				+ " where userid=?";
		con = DatabaseManager.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mdto.getName());
			pstmt.setString(2, mdto.getPwd());
			pstmt.setString(3, mdto.getEmail());
			pstmt.setString(4, mdto.getPhone());
			pstmt.setInt(5, mdto.getAdmin());
			pstmt.setString(6, mdto.getUserid());
			
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		DatabaseManager.close(con,  pstmt,  rs);
		return result;
	}
	
	
	public int deleteMember(String userid) {
		int result=0;
		String sql = "delete from member where userid=?";
		con = DatabaseManager.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			result=pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		DatabaseManager.close(con,  pstmt,  rs);
		return result;
	}
	
	public int insertMember(MemberDto mdto) {
		int result=0;
		String sql = "insert into member values(?,?,?,?,?,?)";
		con = DatabaseManager.getConnection();
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, mdto.getName());
			pstmt.setString(2, mdto.getUserid());
			pstmt.setString(3, mdto.getPwd());
			pstmt.setString(4, mdto.getEmail());
			pstmt.setString(5, mdto.getPhone());
			pstmt.setInt(6, mdto.getAdmin());
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DatabaseManager.close(con,  pstmt,  rs);
		return result;
	}
	
	public int confirmID(String userid) {
		int result=0;
		String sql = "select userid from member where userid=?";
		con = DatabaseManager.getConnection();
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs=pstmt.executeQuery();
			if(rs.next())	result=1;
			else result = 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DatabaseManager.close(con,  pstmt,  rs);
		return result;
	}
	
	
	
	public MemberDto getMember(String userid) {
		MemberDto mdto = null;
		String sql = "select*from member where userid=?";
		con= DatabaseManager.getConnection();
		try {
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DatabaseManager.close(con,  pstmt,  rs);
		return mdto;
	}

}
