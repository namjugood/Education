package com.ezenac.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ezenac.dto.CartVO;
import com.ezenac.dto.OrderVO;
import com.ezenac.dto.ProductVO;
import com.ezenac.util.Dbman;

public class OrderDao {

	private OrderDao() { }
	private static OrderDao ist = new OrderDao();
	public static OrderDao getInstance() { 	return ist;	}
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	
	public int directInsertOrder(int pseq, int quantity,  String id) {
		int oseq = 0;
		//1. 전달된 아이디를 이용하여 orders 테이블에 레코드를 추가합니다
		String sql = "insert into orders(oseq, id) values(orders_seq.nextval, ?)";
		con = Dbman.getConnection();
		try {		
			pstmt = con.prepareStatement(sql);
	 		pstmt.setString(1, id);
			pstmt.executeUpdate();
			pstmt.close();
			// 2. 추가한 주문(orders테이블의 추가된 레코드)의 주문번호 조회
			sql = "select max(oseq) from orders";
			pstmt = con.prepareStatement(sql); 
			rs = pstmt.executeQuery();
			if(rs.next()) oseq = rs.getInt(1);
			pstmt.close();
			// 3. 조회된 주문번호와 상품번호, 수량 등으로 order_detail 테이블에 레코드 추가
			sql = "insert into order_detail(odseq, oseq, pseq, quantity) "
			  		+ "values(order_detail_seq.nextval, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,  oseq);
			pstmt.setInt(2, pseq);
			pstmt.setInt(3, quantity);
			pstmt.executeUpdate();
		} catch (SQLException e) {	e.printStackTrace();	 
		} finally { Dbman.close(con, pstmt, rs); } 
		//4. oseq 값 리턴
		return oseq;
	}
	
	
	
	public ArrayList<OrderVO> listOrderById2(String id, int oseq){
		ArrayList<OrderVO> list = new ArrayList<OrderVO>();
		String sql = "select * from order_view where id=? and oseq=?";
		try {
			  con = Dbman.getConnection();
			  pstmt = con.prepareStatement(sql); 
			  pstmt.setString(1, id);
			  pstmt.setInt(2, oseq);
			  rs = pstmt.executeQuery();
			  while(rs.next()) {
				    OrderVO ovo = new OrderVO();
					ovo.setOdseq(rs.getInt("odseq"));
					ovo.setOseq(rs.getInt("oseq"));
					ovo.setId(rs.getString("id"));
					ovo.setIndate(rs.getTimestamp("indate"));
					ovo.setMname(rs.getString("mname"));
					ovo.setZipnum(rs.getString("zip_num"));
					ovo.setAddress(rs.getString("address"));
					ovo.setPhone(rs.getString("phone"));
					ovo.setPseq(rs.getInt("pseq"));
					ovo.setQuantity(rs.getInt("quantity"));
					ovo.setPname(rs.getString("pname"));
					ovo.setPrice2(rs.getInt("price2"));
					ovo.setResult(rs.getString("result"));
					list.add(ovo);
			  }	  
		} catch (Exception e) { e.printStackTrace();
	    } finally { Dbman.close(con, pstmt, rs); }	  
		return list;
	}
	
	
	
	
	
	public ArrayList<Integer> oseqListAll(String id){
		ArrayList<Integer> list = new ArrayList<Integer>();
		String sql = "select distinct oseq from order_view where id=? "
				+ "order by oseq desc";
		try {
			  con = Dbman.getConnection();
			  pstmt = con.prepareStatement(sql); 
			  pstmt.setString(1, id);
			  rs = pstmt.executeQuery();
			  while (rs.next()) {
					list.add(rs.getInt(1));
			  }
		} catch (Exception e) { e.printStackTrace();
	    } finally { Dbman.close(con, pstmt, rs); }
		return list;
	}
	
	
	
	public ArrayList<Integer> selectSeqOrderIng(String id){
		ArrayList<Integer> list = new ArrayList<Integer>();
		String sql = "select distinct oseq from order_view where id=? and result='1' "
				+ "order by oseq desc";
		try {
			  con = Dbman.getConnection();
			  pstmt = con.prepareStatement(sql); 
			  pstmt.setString(1, id);
			  rs = pstmt.executeQuery();
			  while (rs.next()) {
					list.add(rs.getInt(1));
			  }
		} catch (Exception e) { e.printStackTrace();
	    } finally { Dbman.close(con, pstmt, rs); }    
		return list;
	}
	
	
	
	public ArrayList<OrderVO> listOrderById(String id, int oseq){
		ArrayList<OrderVO> list = new ArrayList<OrderVO>();
		String sql = "select * from order_view where id=? and result ='1' and oseq=?";
		try {
			  con = Dbman.getConnection();
			  pstmt = con.prepareStatement(sql); 
			  pstmt.setString(1, id);
			  pstmt.setInt(2, oseq);
			  rs = pstmt.executeQuery();
			  while(rs.next()) {
				    OrderVO ovo = new OrderVO();
					ovo.setOdseq(rs.getInt("odseq"));
					ovo.setOseq(rs.getInt("oseq"));
					ovo.setId(rs.getString("id"));
					ovo.setIndate(rs.getTimestamp("indate"));
					ovo.setMname(rs.getString("mname"));
					ovo.setZipnum(rs.getString("zip_num"));
					ovo.setAddress(rs.getString("address"));
					ovo.setPhone(rs.getString("phone"));
					ovo.setPname(rs.getString("pname"));
					ovo.setPrice2(rs.getInt("price2"));
					ovo.setPseq(rs.getInt("pseq"));
					ovo.setQuantity(rs.getInt("quantity"));
					ovo.setResult(rs.getString("result"));
					list.add(ovo);
			  }
		} catch (Exception e) { e.printStackTrace();
	    } finally { Dbman.close(con, pstmt, rs); }  	  
		return list;
	}
	
	
	
	public int insertOrder(ArrayList<CartVO> list, String id) {
		int Oseq = 0;
		// 1. 주문 번호(시퀀스 자동입력)와 구매자 아이디로 orders 테이블에 레코드 추가
		String sql = "insert into orders(oseq, id) values(orders_seq.nextval, ?)";
		try {		
			con = Dbman.getConnection();
 			pstmt = con.prepareStatement(sql);
 			pstmt.setString(1, id);
			pstmt.executeUpdate();
			pstmt.close();
			
			//2. Orders 테이블에 시퀀스로 입력된 가장 마지막(방금추가한) 주문 번호 조회
			sql = "select max(oseq) from orders";
			pstmt = con.prepareStatement(sql); 
			rs = pstmt.executeQuery();
			if(rs.next()) Oseq = rs.getInt(1);
			pstmt.close();
			
			//3. list 의 주문들을 Orders 에서 얻은 마지막 주문 번호와 함꼐 order_detail 에 추가
			for(CartVO cvo : list) {
				sql = "insert into order_detail(odseq, oseq, pseq, quantity) "
				  		+ "values(order_detail_seq.nextval, ?, ?, ?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, Oseq);
				pstmt.setInt(2, cvo.getPseq());
				pstmt.setInt(3, cvo.getQuantity());
				pstmt.executeUpdate();
				pstmt.close();
				
				//4. order_detail 에 추가된 카트 내용은 처리된 내역으로 삭제
				/* sql = "delete from cart where cseq = ?"; */
				sql = "Update cart set result='2' where cseq=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, cvo.getCseq());
				pstmt.executeUpdate();
			}
		} catch (SQLException e) {	e.printStackTrace();	 
		} finally { Dbman.close(con, pstmt, rs); } 
		return Oseq;
	}
}
