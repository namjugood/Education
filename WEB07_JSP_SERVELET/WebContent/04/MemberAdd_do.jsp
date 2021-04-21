<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.DriverManager" %>
<%@page import="java.sql.ResultSet" %>
<%@page import="java.sql.PreparedStatement" %>
<%@page import="java.sql.Connection" %> 
<%@page import="java.sql.DriverAction" %>   
<%
Connection con = null;
PreparedStatement pstmt = null;
String driver= "oracle.jdbc.driver.OracleDriver";
String url = "jdbc:oracle:thin:@localhost:1521:xe";
String uid = "scott";
String pass = "tiger";

String name = request.getParameter("name");
String userid = request.getParameter("userid");
String pwd = request.getParameter("pwd");
String phone = request.getParameter("phone");

try{
	Class.forName(driver);
	con = DriverManager.getConnection(url, uid, pass);
	String sql = "insert into mem(name, id, pwd, phone) values(?, ?, ?, ?)";
	pstmt = con.prepareStatement(sql);
	pstmt.setString(1, name);
	pstmt.setString(2, userid);
	pstmt.setString(3, pwd);
	pstmt.setString(4, phone);
	
	int result = pstmt.executeUpdate();
	
	if(result==1) response.sendRedirect("MemberMGR.jsp");
	else response.sendRedirect("MemberAdd.jsp");
	
}catch(Exception e){
	e.printStackTrace();
}finally{
	try{
		if(pstmt != null) pstmt.close();
		if(con != null)	con.close();
	}catch(Exception e){
		e.printStackTrace();
	}
}

%>