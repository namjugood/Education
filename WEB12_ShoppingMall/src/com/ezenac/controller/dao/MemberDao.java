package com.ezenac.controller.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ezenac.controller.util.Dbman;
import com.ezenac.dto.AddressVO;
import com.ezenac.dto.MemberVO;

public class MemberDao {
	private MemberDao() {}
	private static MemberDao ist = new MemberDao();
	public static MemberDao getInstance() {	 return ist;	}
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public int confirmID(String id) {
		int result=0;
		String sql = "select id from member where id=?";
		con = Dbman.getConnection();
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next())	result=1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Dbman.close(con,  pstmt,  rs);
		return result;
	}
	
	
	public MemberVO getMember(String id) {
		MemberVO mvo = null;
		String sql = "select * from member where id=?";
		con = Dbman.getConnection();  
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				mvo=new MemberVO();
				mvo.setId(id);
				mvo.setPwd(rs.getString("pwd"));
				mvo.setName(rs.getString("name"));
				mvo.setEmail(rs.getString("email"));
				mvo.setZip_num(rs.getString("zip_num"));
				mvo.setAddress(rs.getString("address"));
				mvo.setPhone(rs.getString("phone"));
				mvo.setUseyn(rs.getString("useyn"));
				mvo.setIndate(rs.getTimestamp("indate"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Dbman.close(con, pstmt, rs);
		}
		return mvo;
	}


	public ArrayList<AddressVO> selectAddressByDong(String dong) {
		ArrayList<AddressVO> list = new ArrayList<AddressVO>();
		String sql = "select * from address where dong like '%'||?||'%'";
		// ||로 '%'와 ?를 이어 붙이는 형식('%?%'은 ?안에 값 대입이 불가)
		con = Dbman.getConnection();  
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, dong);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				AddressVO avo = new AddressVO();
				avo.setZip_num(rs.getString("zip_num"));
				avo.setSido(rs.getString("sido"));
				avo.setGugun(rs.getString("gugun"));
				avo.setDong(rs.getString("dong"));
				avo.setZip_code(rs.getString("zip_code"));
				avo.setBunji(rs.getString("bunji"));
				list.add(avo);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			Dbman.close(con, pstmt, rs);
		}
		return list;
	}


	public void insertMember(MemberVO mdto) {
		String sql="insert into member(id, pwd, name, zip_num, address, email,phone)"
				+ " values(?,?,?,?,?,?,?)";
		con=Dbman.getConnection();
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, mdto.getId());
			pstmt.setString(2, mdto.getPwd());
			pstmt.setString(3, mdto.getName());
			pstmt.setString(4, mdto.getZip_num());
			pstmt.setString(5, mdto.getAddress());
			pstmt.setString(6, mdto.getEmail());
			pstmt.setString(7, mdto.getPhone());
			pstmt.executeUpdate();
		} catch (SQLException e) {
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			Dbman.close(con, pstmt, rs);
		}
		
	}


	public void updateMember(MemberVO mvo) {
		String sql = "update set pwd=?, name=?, zip_num=?, address=?, "
				+ "email=?, phone=? where id=?";
		con=Dbman.getConnection();
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, mvo.getPwd());
			pstmt.setString(2, mvo.getName());
			pstmt.setString(3, mvo.getZip_num());
			pstmt.setString(4, mvo.getAddress());
			pstmt.setString(5, mvo.getEmail());
			pstmt.setString(6, mvo.getPhone());
			pstmt.setString(7, mvo.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Dbman.close(con, pstmt, rs);
		}
		
	}
			
}
