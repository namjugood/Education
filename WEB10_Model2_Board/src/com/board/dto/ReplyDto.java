package com.board.dto;

import java.sql.Timestamp;

public class ReplyDto {
	private int num;
	private int boardnum;
	private String userid;
	private Timestamp writedate;
	private String content;
	
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getBoardnum() {
		return boardnum;
	}
	public void setBoardnum(int boardnum) {
		this.boardnum = boardnum;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String suerid) {
		this.userid = suerid;
	}
	public Timestamp getWritedate() {
		return writedate;
	}
	public void setWritedate(Timestamp timestamp) {
		this.writedate = timestamp;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
