package com.ezenac.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ezenac.dto.AddressVO;
import com.ezenac.dto.MemberVO;
import com.ezenac.util.Dbman;

public class MemberDao {
	private MemberDao() { }
	private static MemberDao ist = new MemberDao();
	public static MemberDao getInstance() { 	return ist;	}
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public void resetPw(MemberVO mvo) {
		String sql = "update member set pwd = ? where id = ?";
		con = Dbman.getConnection();
		try {	
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,  mvo.getPwd() );
			pstmt.setString(2, mvo.getId());
		    pstmt.executeUpdate();
		} catch (SQLException e) { e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs); }
	}
	
	public MemberVO confirmIdnamePhone(String id, String name, String phone) {
		MemberVO mvo = null;
		String sql = "select * from member "
				+ "where id=? and name=? and phone=?";
		con = Dbman.getConnection();
		try {	
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,  id);
			pstmt.setString(2, name);
			pstmt.setString(3, phone);
		    rs = pstmt.executeQuery();
		    if(rs.next()){
		    	mvo = new MemberVO();
		        mvo.setId(rs.getString("id"));
		        mvo.setPwd(rs.getString("pwd"));
		        mvo.setName(rs.getString("name"));
		        mvo.setEmail(rs.getString("email"));
		        mvo.setZip_num(rs.getString("zip_num"));
		        mvo.setAddress(rs.getString("address"));
		        mvo.setPhone(rs.getString("phone"));
		        mvo.setUseyn(rs.getString("useyn"));
		        mvo.setIndate(rs.getTimestamp("indate"));
		    } 
		} catch (SQLException e) { e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs); }
		return mvo;
	}
	
	
	
	
	public MemberVO confirmPhone(String name, String phone) {
		MemberVO mvo = null;
		String sql = "select * from member where name=? and phone=?";
		con = Dbman.getConnection();
		try {	
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, phone);
		    rs = pstmt.executeQuery();
		    if(rs.next()){
		    	mvo = new MemberVO();
		        mvo.setId(rs.getString("id"));
		        mvo.setPwd(rs.getString("pwd"));
		        mvo.setName(rs.getString("name"));
		        mvo.setEmail(rs.getString("email"));
		        mvo.setZip_num(rs.getString("zip_num"));
		        mvo.setAddress(rs.getString("address"));
		        mvo.setPhone(rs.getString("phone"));
		        mvo.setUseyn(rs.getString("useyn"));
		        mvo.setIndate(rs.getTimestamp("indate"));
		    } 
		} catch (SQLException e) { e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs); }
		return mvo;
	}
	
	
	
	
	public void updateMember(MemberVO mvo) {
		String sql = "Update member set pwd=?, name=?, zip_num=?, address=?, "
				+ "email=?, phone=? where id = ?";
		 con = Dbman.getConnection();
		try {
		      pstmt = con.prepareStatement(sql);      
			  pstmt.setString(1, mvo.getPwd());
			  pstmt.setString(2, mvo.getName());
			  pstmt.setString(3, mvo.getZip_num());
			  pstmt.setString(4, mvo.getAddress());
			  pstmt.setString(5, mvo.getEmail());
			  pstmt.setString(6, mvo.getPhone());
			  pstmt.setString(7, mvo.getId());
			  pstmt.executeUpdate();
		} catch (Exception e) { e.printStackTrace();
	    } finally { Dbman.close(con, pstmt, rs); }   
	}
	
	
	
	
	public void insertMember(MemberVO mvo) {
		String sql = "insert into member(id, pwd, name, zip_num, address, email, "
				+ "phone) values(?, ?, ?, ?, ?, ?, ?)";
		con = Dbman.getConnection();
		try {
		      pstmt = con.prepareStatement(sql);
		      pstmt.setString(1, mvo.getId());      
			  pstmt.setString(2, mvo.getPwd());
			  pstmt.setString(3, mvo.getName());
			  pstmt.setString(4, mvo.getZip_num());
			  pstmt.setString(5, mvo.getAddress());
			  pstmt.setString(6, mvo.getEmail());
			  pstmt.setString(7, mvo.getPhone());
			  pstmt.executeUpdate();
		} catch (Exception e) { e.printStackTrace();
	    } finally { Dbman.close(con, pstmt, rs); }   
	}
	
	
	
	public ArrayList<AddressVO> selectAddressByDong( String dong ){
		ArrayList<AddressVO> list = new ArrayList<AddressVO>();
		String sql = "select * from address where dong like '%'||?||'%'";
		con = Dbman.getConnection();
		try {
		      pstmt = con.prepareStatement(sql);
		      pstmt.setString(1, dong);
		      rs = pstmt.executeQuery();
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
		      
		} catch (Exception e) { e.printStackTrace();
	    } finally { Dbman.close(con, pstmt, rs); }      
		return list;
	}
	
	
	
	
	public int confirmID(String id) {
		int result = 0;
		String sql = "select * from member where id=?";
		con = Dbman.getConnection();
		try {	
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
		    rs = pstmt.executeQuery();
		    if( rs.next() ) result = 1;
		} catch (SQLException e) { e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs); }
		return result;
	}
	
	
	
	
	public MemberVO getMember(String id) {
		MemberVO mvo = null;
		String sql = "select * from member where id=?"; 
		con = Dbman.getConnection();
		try {	
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
		    rs = pstmt.executeQuery();
		    if(rs.next()){
		    	mvo = new MemberVO();
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
		} catch (SQLException e) { e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs); }
		return mvo;
	}
}
