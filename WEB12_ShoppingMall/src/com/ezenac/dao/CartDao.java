package com.ezenac.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.ezenac.dto.CartVO;
import com.ezenac.util.Dbman;

public class CartDao {
	private CartDao() { }
	private static CartDao ist = new CartDao();
	public static CartDao getInstance() { 	return ist;	}
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	
	public void deleteCart(String cseq) {
		String sql = "delete from cart where cseq=?";
		try {
		      con = Dbman.getConnection();
		      pstmt = con.prepareStatement(sql); 
		      pstmt.setInt(1, Integer.parseInt(cseq));
		      pstmt.executeUpdate();
		} catch (Exception e) { e.printStackTrace();
	    } finally { Dbman.close(con, pstmt, rs); }   
	}
	
	
	
	public ArrayList<CartVO> listCart(String id){
		ArrayList<CartVO> list = new ArrayList<CartVO>();
		String sql = "select * from cart_view where id=? and result='1' order by cseq desc";
		try {
		    con = Dbman.getConnection();
		    pstmt = con.prepareStatement(sql); 
		    pstmt.setString(1, id);
		    rs = pstmt.executeQuery();
			while(rs.next()) {
				CartVO cvo = new CartVO();				
				cvo.setCseq(rs.getInt(1));
				cvo.setId(rs.getString(2));
				cvo.setMname(rs.getString(3));
				cvo.setPseq(rs.getInt(4));				
				cvo.setPname(rs.getString(5));
				cvo.setQuantity(rs.getInt(6));
				cvo.setPrice2(rs.getInt(7));
				cvo.setIndate(rs.getTimestamp(9));
				list.add(cvo);
			}
		} catch (Exception e) { e.printStackTrace();
	    } finally { Dbman.close(con, pstmt, rs); } 
		return list;
	}
	
	
	
	public void insertCart(CartVO cvo) {
		String sql = "insert into cart(cseq, id, pseq, quantity) "
				+ "values(cart_seq.nextval,?, ?, ?)";
		try {
		      con = Dbman.getConnection();
		      pstmt = con.prepareStatement(sql); 
		      pstmt.setString(1, cvo.getId());
			  pstmt.setInt(2, cvo.getPseq());
			  pstmt.setInt(3, cvo.getQuantity());
			  pstmt.executeUpdate();
		} catch (Exception e) { e.printStackTrace();
	    } finally { Dbman.close(con, pstmt, rs); }   
	}
}
