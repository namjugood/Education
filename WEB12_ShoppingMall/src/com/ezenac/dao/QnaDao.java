package com.ezenac.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ezenac.dto.QnaVO;
import com.ezenac.util.Dbman;

public class QnaDao {
	private QnaDao() { }
	private static QnaDao ist = new QnaDao();
	public static QnaDao getInstance() { 	return ist;	}
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	
	public QnaVO getQna(int qseq) {
		QnaVO qvo = new QnaVO();
		String sql = "select * from qna where qseq=?";
		try {
			con = Dbman.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,  qseq);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				qvo.setQseq(qseq);
				qvo.setSubject(rs.getString("subject"));
				qvo.setContent(rs.getString("content"));
				qvo.setId(rs.getString("id"));
				qvo.setIndate(rs.getTimestamp("indate"));
				qvo.setReply(rs.getString("reply"));
				qvo.setRep(rs.getString("rep"));
			}
		} catch (SQLException e) {e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs);}	
		return qvo;
	}
	
	
	
	
	public void insertQna(QnaVO qvo, String id) {
		String sql = "insert into qna (qseq, subject, content, id) "
				+ "values(qna_seq.nextval, ?, ?, ?)";
		try {
			con = Dbman.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, qvo.getSubject());
		    pstmt.setString(2, qvo.getContent());
		    pstmt.setString(3, id);
		    pstmt.executeUpdate();  
		} catch (SQLException e) {e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs);}	
	}
	
	
	
	
	public ArrayList<QnaVO> listQna(String id){
		ArrayList<QnaVO> list = new ArrayList<QnaVO>();
		String sql = "select * from qna where id=? order by qseq desc";
		try {
			con = Dbman.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,  id);
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
		} catch (SQLException e) {e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs);}	
		return list;
	}
}
