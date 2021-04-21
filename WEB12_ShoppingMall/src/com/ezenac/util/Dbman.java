package com.ezenac.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Dbman {
	public static Connection getConnection(){
		Connection con = null;
		try {
			// server.xml 파일에서 Context 태그로 된 내용을 읽어서 저장하기 위한 객체 생성
			Context initContext = new InitialContext();
			// 객체 초기화
			Context envContext = (Context)initContext.lookup("java:/comp/env");
			//name 이 jdbc/myoracle 이란 개체 찾아서 리턴된 값을 DataSource가 받음. DBCP 객체 제어권
			DataSource ds = (DataSource)envContext.lookup("jdbc/myoracle");
			// 생성된 DBCP 에서 연결 객체를 하나 전달 받습니다
			con = ds.getConnection();
		} catch (NamingException e) { e.printStackTrace();
		} catch (SQLException e) { e.printStackTrace();
		} 
		return con;
	}
	public static void close(Connection con, PreparedStatement pstmt, ResultSet rs) {
		try {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(con!=null) con.close();
		}catch(Exception e) {	
			e.printStackTrace(); 
		}
	}
}
