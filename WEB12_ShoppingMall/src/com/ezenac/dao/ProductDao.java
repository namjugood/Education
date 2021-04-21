package com.ezenac.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ezenac.dto.ProductVO;
import com.ezenac.util.Dbman;

public class ProductDao {
	private ProductDao() { }
	private static ProductDao ist = new ProductDao();
	public static ProductDao getInstance() { 	return ist;	}
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	
	public ProductVO getProduct(String pseq) {
		ProductVO pvo = new ProductVO();
		String sql = "select * from product where pseq = ?";
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,  pseq);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				pvo.setPseq(Integer.parseInt(pseq));
				pvo.setName(rs.getString("name"));
				pvo.setKind(rs.getString("kind"));
				pvo.setPrice1(rs.getInt("price1"));
				pvo.setPrice2(rs.getInt("price2"));
				pvo.setPrice3(rs.getInt("price3"));
				pvo.setContent(rs.getString("content"));
				pvo.setImage(rs.getString("image"));
				pvo.setUseyn(rs.getString("useyn"));
				pvo.setBestyn(rs.getString("bestyn"));
				pvo.setIndate(rs.getTimestamp("indate"));
			}
		} catch (SQLException e) { e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs); }		
			
		return pvo;
	}
	
	
	public ArrayList<ProductVO> listKindProduct(String kind){
		ArrayList<ProductVO> list = new ArrayList<ProductVO>();
		String sql= "select * from product where kind=?";
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,  kind);
			rs = pstmt.executeQuery();
			while(rs.next()) {
		    	ProductVO pvo = new ProductVO();
		    	pvo.setPseq(rs.getInt("pseq"));
		    	pvo.setName(rs.getString("name"));
		    	pvo.setPrice2(rs.getInt("price2"));
		    	pvo.setImage(rs.getString("image"));		    	
		    	list.add(pvo);
		    }
		} catch (SQLException e) { e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs); }	
		return list;
	}
	
	
	public ArrayList<ProductVO> getBestList(){
		ArrayList<ProductVO> list = new ArrayList<ProductVO>();
		String sql = "select * from best_pro_view";
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ProductVO pvo = new ProductVO();
				// 베스트 상품의 이름, 가격, 이미지만 현재 필요하기 때문에 필요한 필드만 dto 에 담습니다
				pvo.setPseq(rs.getInt("pseq"));
				pvo.setName(rs.getString("name"));
				pvo.setPrice2(rs.getInt("price2"));
				pvo.setImage(rs.getString("image"));
				list.add(pvo);
			}
		} catch (SQLException e) { e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs); }
		return list;
	}
	
	public ArrayList<ProductVO> getNewList(){
		ArrayList<ProductVO> list = new ArrayList<ProductVO>();
		String sql = "select * from new_pro_view";
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ProductVO pvo = new ProductVO();
				// 신상품의 이름, 가격, 이미지만 현재 필요하기 때문에 필요한 필드만 dto 에 담습니다
				pvo.setPseq(rs.getInt("pseq"));
				pvo.setName(rs.getString("name"));
				pvo.setPrice2(rs.getInt("price2"));
				pvo.setImage(rs.getString("image"));
				list.add(pvo);
			}
		} catch (SQLException e) { e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs); }
		return list;
	}
}
