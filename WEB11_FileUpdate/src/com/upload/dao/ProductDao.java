package com.upload.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.upload.dto.ProductVO;
import com.upload.util.Dbman;

public class ProductDao {
	private ProductDao() {	}
	private static ProductDao instance = new ProductDao();
	public static ProductDao getInstance(){ return instance; }
	
	Connection con=null;
	PreparedStatement pstmt = null;
	ResultSet rs=null;
			
	
	public ArrayList<ProductVO> selectAll() {
		ArrayList<ProductVO> list = new ArrayList<ProductVO>();
		String sql = "select * from product order by code desc";
		try {
			con=Dbman.getConnection();
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				ProductVO pvo = new ProductVO();
				pvo.setCode(rs.getInt("code"));
				pvo.setName(rs.getString("name"));
				pvo.setPrice(rs.getInt("price"));
				pvo.setPictureurl(rs.getString("pictureurl"));
				pvo.setDescription(rs.getString("description"));
				list.add(pvo);
			}	
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			Dbman.close(con, pstmt, rs);
		}
		return list;
	}


	public ProductVO getProductOne(String code) {
		ProductVO pvo = null;
		String sql = "select * from product where code=?";
		try {
			con=Dbman.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, code);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				pvo = new ProductVO();
				pvo.setCode(rs.getInt("code"));
				pvo.setName(rs.getString("name"));
				pvo.setPrice(rs.getInt("price"));
				pvo.setPictureurl(rs.getString("pictureurl"));
				pvo.setDescription(rs.getString("description"));
			}	
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			Dbman.close(con, pstmt, rs);
		}
		return pvo;
	}


	public void insertProduct(ProductVO pvo) {
		String sql = "insert into product values(product_seq.nextVal,?,?,?,?)";
		try {
			con=Dbman.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, pvo.getName());
			pstmt.setInt(2, pvo.getPrice());
			pstmt.setString(3, pvo.getPictureurl());
			pstmt.setString(4, pvo.getDescription());
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			Dbman.close(con, pstmt, rs);
		}
		
	}


	public void updateProduct(ProductVO pVo) {
		String sql = "update product set name=?, price=?, pictureurl=?,"
				+ " description=? where code=?";
		try {
			con=Dbman.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, pVo.getName());
			pstmt.setInt(2, pVo.getPrice());
			pstmt.setString(3, pVo.getPictureurl());
			pstmt.setString(4, pVo.getDescription());
			pstmt.setInt(5, pVo.getCode());
			
			pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			Dbman.close(con, pstmt, rs);
		}
	}	
}
