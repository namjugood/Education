package com.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.board.dto.BoardDto;
import com.board.dto.ReplyDto;
import com.board.util.DatabaseManager;
import com.board.util.Paging;

public class BoardDao {
	private BoardDao() {}
	private static BoardDao ist = new BoardDao();
	public static BoardDao getInstance() { return ist; }
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public void insertBoard(BoardDto bdto) {
		String sql = "insert into board(num, userid, email, pass, title, content)"
				+ "values(board_seq.nextVal,?,?,?,?,?)";
		con=DatabaseManager.getConnection();
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, bdto.getUserid());
			pstmt.setString(2, bdto.getEmail());
			pstmt.setString(3, bdto.getPass());
			pstmt.setString(4, bdto.getTitle());
			pstmt.setString(5, bdto.getContent());
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		DatabaseManager.close(con, pstmt, rs);
	}
	
	public void updateReadCount(String num) {
		String sql = "update board set readcount = readcount +1 where num=?";
		con=DatabaseManager.getConnection();
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(num));
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		DatabaseManager.close(con, pstmt, rs);
	}
	
	public BoardDto getBoardOne(String num) {
		BoardDto bdto = new BoardDto();
		String sql = "select * from board where num=?";
		con=DatabaseManager.getConnection();
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(num));
			rs=pstmt.executeQuery();
			if(rs.next()) {
				bdto.setNum(rs.getInt("num"));
				bdto.setUserid(rs.getString("userid"));
				bdto.setPass(rs.getString("pass"));
				bdto.setEmail(rs.getString("email"));
				bdto.setTitle(rs.getString("title"));
				bdto.setContent(rs.getString("content"));
				//bdto.setWritedate(rs.getTimestamp("writedate"));
				//bdto.setReadcount(rs.getInt("readcount"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		DatabaseManager.close(con, pstmt, rs);
		return bdto;
	}
	
	
	public ArrayList<BoardDto> selectAll(Paging paging) {
		ArrayList <BoardDto> list = new ArrayList<>();
		// String sql = "select * from board order by num desc";
		String sql = "select * from "
				+ " (select * from "
				+ " (select rownum as rn, t.* from"
				+ " (select * from board order by num desc) t"
				+ " ) where rn >= ?"
				+ " ) where rn <= ?";
		int startNum = paging.getStartNum();	// startNum 추출
		int endNum = paging.getEndNum();		// endNum 추출
		System.out.println(startNum + " " + endNum);
		// 게시물의 개수가 천문학적으로 늘어났을 때, and나 or의 조건을 사용하면 속도가 현저하게 느려짐
		con=DatabaseManager.getConnection();
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, startNum);
			pstmt.setInt(2, endNum);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				BoardDto bdto = new BoardDto();
				bdto.setNum(rs.getInt("num"));
				bdto.setUserid(rs.getString("userid"));
				bdto.setEmail(rs.getString("email"));
				bdto.setPass(rs.getString("pass"));
				bdto.setTitle(rs.getString("title"));
				bdto.setContent(rs.getString("content"));
				bdto.setReadcount(rs.getInt("readcount"));
				bdto.setWritedate(rs.getTimestamp("writedate"));
				
				String sql2 = "select count(*) as cnt from reply where boardnum=?";
				int num = rs.getInt("num");
				PreparedStatement pstmt2 = con.prepareStatement(sql2);
				pstmt2.setInt(1,  num);
				ResultSet rs2 = pstmt2.executeQuery();
				
				if(rs2.next()) bdto.setReplycnt(rs2.getInt("cnt"));
				else bdto.setReplycnt(0);
				
				pstmt2.close();
				rs2.close();
				
				list.add(bdto);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		DatabaseManager.close(con, pstmt, rs);
		return list;
	}

	public void updateBoard(BoardDto bdto) {
		String sql = "update board set pass=?, email=?, title=?, content=? where num=?";
		con=DatabaseManager.getConnection();
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, bdto.getPass());
			pstmt.setString(2, bdto.getEmail());
			pstmt.setString(3, bdto.getTitle());
			pstmt.setString(4, bdto.getContent());
			pstmt.setInt(5, bdto.getNum());
		}catch(SQLException e) {
			e.printStackTrace();
		}
		DatabaseManager.close(con, pstmt, rs);
	}
	
	public void deleteBoard(String num) {
		String sql = "delete from board where num=?";
		con=DatabaseManager.getConnection();
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(num));
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getAllCount() {
		int count=0;
		String sql = "select count(*) as cnt from board";
		con = DatabaseManager.getConnection();
		try {
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next())	count=rs.getInt("cnt");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DatabaseManager.close(con, pstmt, rs);
		return count;
	}

	public void insertReply(ReplyDto rdto) {
		
		String sql = "insert into reply(num, boardnum, userid, content) "
				+ " values(reply_seq.nextVal,?,?,?)";
		con = DatabaseManager.getConnection();
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, rdto.getBoardnum());
			pstmt.setString(2, rdto.getUserid());
			pstmt.setString(3, rdto.getContent());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DatabaseManager.close(con, pstmt, rs);
	}

	public ArrayList<ReplyDto> selectAllReply(String num) {
		ArrayList<ReplyDto> list = new ArrayList<ReplyDto>();
		String sql = "select * from reply where boardnum=? order by num desc";
		con = DatabaseManager.getConnection();
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(num));
			rs=pstmt.executeQuery();
			while(rs.next()) {
				ReplyDto rdto = new ReplyDto();
				rdto.setNum(rs.getInt("num"));
				rdto.setBoardnum(rs.getInt("boardnum"));
				rdto.setUserid(rs.getString("userid"));
				rdto.setWritedate(rs.getTimestamp("writedate"));
				rdto.setContent(rs.getString("content"));
				
				list.add(rdto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DatabaseManager.close(con, pstmt, rs);
		return list;
	}

	public void deleteReply(String num) {
		String sql = "delete from reply where num=?";
		con=DatabaseManager.getConnection();
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(num));
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
