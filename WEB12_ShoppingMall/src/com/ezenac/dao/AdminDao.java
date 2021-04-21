package com.ezenac.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ezenac.dto.AdminVO;
import com.ezenac.dto.MemberVO;
import com.ezenac.dto.OrderVO;
import com.ezenac.dto.ProductVO;
import com.ezenac.dto.QnaVO;
import com.ezenac.util.Dbman;
import com.ezenac.util.Paging;

public class AdminDao {
	private AdminDao() {}
	private static AdminDao instance = new AdminDao();
	public static AdminDao getInstance() { return instance;}
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	
	public void updateQna(QnaVO qvo) {
		String sql = "update qna set reply=?, rep='2' where qseq=?";
		try {
			con = Dbman.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, qvo.getReply());
			pstmt.setInt(2,  qvo.getQseq());
			pstmt.executeUpdate();
		} catch (SQLException e) { e.printStackTrace();
		} finally {Dbman.close(con, pstmt, rs);}
	}
	
	
	public ArrayList<QnaVO> listQna(Paging paging, String key){
		int startNum = paging.getStartNum();
	    int endNum = paging.getEndNum();
		ArrayList<QnaVO> list = new ArrayList<QnaVO>();
		String sql = "select * from ( select * from ("
				+ "select rownum as rn, q.* from"
				+ "((select * from qna where subject like '%'||?||'%' "
				+ " order by qseq desc) q)"
				+ ") where rn>=? ) where rn<=?";
		 try {
				con = Dbman.getConnection();
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1,  key);
				pstmt.setInt(2,  startNum);
				pstmt.setInt(3,  endNum);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					QnaVO qvo = new QnaVO();
					qvo.setQseq(rs.getInt("qseq"));
					qvo.setSubject(rs.getString("subject"));
					qvo.setContent(rs.getString("content"));
					qvo.setId(rs.getString("id"));
					qvo.setIndate(rs.getTimestamp("indate"));
					qvo.setReply(rs.getString("reply"));
					qvo.setRep(rs.getString("rep"));
			        list.add(qvo);
				}
			} catch (SQLException e) { e.printStackTrace();
			} finally {Dbman.close(con, pstmt, rs); 	}
		return list;
	}
	
	
	
	public void UpdateOrderResult(String odseq) {
		String sql = "Update order_detail set result='2' where odseq=?";
		try {
			  con = Dbman.getConnection();
			  pstmt = con.prepareStatement(sql); 
			  pstmt.setInt(1,  Integer.parseInt(odseq));
			  pstmt.executeUpdate();
		} catch (Exception e) { e.printStackTrace();
	    } finally { Dbman.close(con, pstmt, rs); } 
	}
	
	
	
	public ArrayList<OrderVO> listOrder(Paging paging, String key){
		ArrayList<OrderVO> list  = new ArrayList<OrderVO>();
		int startNum = paging.getStartNum();
	    int endNum = paging.getEndNum();
	    String sql = "select * from ( "
	    		+ "select * from ("
	    		+ "select rownum as rn, o.* from"
	    		+ "((select * from order_view where mname like '%'||?||'%' "
	    		+ " order by result, oseq desc) o)"
	    		+ ") where rn>=? "
	    		+ ") where rn<=?";
	    try {
			  con = Dbman.getConnection();
			  pstmt = con.prepareStatement(sql);
			  pstmt.setString(1,  key);
			  pstmt.setInt(2, startNum);
			  pstmt.setInt(3, endNum);
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
	
	
	
	
	
	public ArrayList<MemberVO> listMember( Paging paging, String key){
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		int startNum = paging.getStartNum();
	    int endNum = paging.getEndNum();
		//String sql = "select * from member  order by indate desc";
	    String sql = "select * from ( "
	    		+ "select * from ( "
	    		+ "select rownum as rn, m.* from "
	    		+ "((select * from member  where name like '%'||?||'%' "
	    		+ " order by indate desc) m)"
	    		+ " ) where rn >= ?"
	    		+ " ) where rn <= ?";
		try {
			  con = Dbman.getConnection();
			  pstmt = con.prepareStatement(sql);
			  pstmt.setString(1, key);
			  pstmt.setInt(2, startNum);
			  pstmt.setInt(3, endNum);
			  rs = pstmt.executeQuery();
			  while(rs.next()) {
				  MemberVO mvo = new MemberVO();
					mvo.setId(rs.getString("id"));
					mvo.setPwd(rs.getString("pwd"));
					mvo.setName(rs.getString("name"));
					mvo.setEmail(rs.getString("email"));
					mvo.setZip_num(rs.getString("zip_num"));
					mvo.setAddress(rs.getString("address"));
					mvo.setPhone(rs.getString("phone"));
					mvo.setUseyn(rs.getString("useyn"));
					mvo.setIndate(rs.getTimestamp("indate"));
					list.add(mvo);
			  }
		} catch (Exception e) { e.printStackTrace();
	    } finally { Dbman.close(con, pstmt, rs); } 
		return list;
	}
	
	
	
	
	public void updateProduct(ProductVO pvo) {
		String sql = "update product set kind=?, useyn=?, name=?, price1=?, "
				+ "price2=?, price3=?, content=?, image=?, bestyn=?  where pseq=?";
		try {
			con = Dbman.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pvo.getKind());
		    pstmt.setString(2, pvo.getUseyn());
		    pstmt.setString(3, pvo.getName());
		    pstmt.setInt(4, pvo.getPrice1());
		    pstmt.setInt(5, pvo.getPrice2());
		    pstmt.setInt(6, pvo.getPrice3());
		    pstmt.setString(7, pvo.getContent());
		    pstmt.setString(8, pvo.getImage());
		    pstmt.setString(9, pvo.getBestyn());
		    pstmt.setInt(10, pvo.getPseq());
		    pstmt.executeUpdate();
		} catch (SQLException e) {e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs);}	
	}
	
	
	
	
	
	public void insertProduct(ProductVO pvo) {
		String sql = "insert into product(pseq, kind, name, price1, price2, price3, "
				+ " content, image) values( product_seq.nextVal, ?,?,?,?,?,?,?)";
		try {
			con = Dbman.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pvo.getKind());      
		    pstmt.setString(2, pvo.getName());
		    pstmt.setInt(3, pvo.getPrice1());
		    pstmt.setInt(4, pvo.getPrice2());
		    pstmt.setInt(5, pvo.getPrice3());
		    pstmt.setString(6, pvo.getContent());
		    pstmt.setString(7, pvo.getImage());  
		    pstmt.executeUpdate();
		} catch (SQLException e) {	e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs); 	}
	}
	
	
	
	
	public int getAllCount(String tablename, String fieldname, String key) {
		int count=0;
		String sql = "select count(*) as count from " + tablename
				+ " where " + fieldname + " like '%'||?||'%'";
		try {
			  con = Dbman.getConnection();
			  pstmt = con.prepareStatement(sql); 
			  pstmt.setString(1, key);
			  rs = pstmt.executeQuery();
			  if(rs.next()) count = rs.getInt("count");
		} catch (Exception e) { e.printStackTrace();
	    } finally { Dbman.close(con, pstmt, rs); } 
		return count;
	}
	
	
	
	
	public ArrayList<ProductVO> listProduct(Paging paging, String key){
		ArrayList<ProductVO> list = new ArrayList<ProductVO>();
		//String sql = "select * from product order by pseq desc";
		String sql = "select * from (" // 2번 괄호 시작
				+ "select * from ( "  //1번 괄호 시작
				+ "select rownum as rn, p.* from "
				+ "((select * from product where name like '%'||?||'%' order by pseq desc) p)"
				+ ") where rn >= ? " //1번 괄호 끝
				+ ") where rn <= ?";  //2번 괄호 끝
		int startNum = paging.getStartNum();
	    int endNum = paging.getEndNum();
		try {
			con = Dbman.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,  key);
			pstmt.setInt(2,  startNum);
			pstmt.setInt(3,  endNum);
			rs = pstmt.executeQuery();
		    while(rs.next()) {
		    	ProductVO pvo = new ProductVO();
    	    	pvo.setPseq(rs.getInt("pseq"));
    	    	pvo.setIndate(rs.getTimestamp("indate"));
    	    	pvo.setName(rs.getString("name"));
    	    	pvo.setPrice1(rs.getInt("price1"));
    	    	pvo.setPrice2(rs.getInt("price2"));
    	    	pvo.setPrice3(rs.getInt("price3"));
    	    	pvo.setImage(rs.getString("image"));
    	    	pvo.setUseyn(rs.getString("useyn"));
    	        pvo.setBestyn(rs.getString("bestyn"));
    	    	list.add(pvo);
		    }
		} catch (SQLException e) {e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs);}	
		return list;
	}
	
	
	
	
	public AdminVO workerCheck(String id) {
		AdminVO avo = null;
		String sql = "select * from worker where id=?";
		try {
			con = Dbman.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if( rs.next() ) {
				avo = new AdminVO();
				avo.setId( rs.getString("id") );
				avo.setPwd( rs.getString("pwd") );
				avo.setName( rs.getString("name") );
				avo.setPhone( rs.getString("phone") );
			}
		} catch (SQLException e) {e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs);}		
		return avo;
	}
}
