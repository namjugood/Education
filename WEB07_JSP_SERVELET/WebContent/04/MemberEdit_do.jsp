<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.DriverManager" %>
<%@page import="java.sql.ResultSet" %>
<%@page import="java.sql.PreparedStatement" %>
<%@page import="java.sql.Connection" %> 

<%
request.setCharacterEncoding("UTF-8");
Connection con = null;
PreparedStatement pstmt = null;
ResultSet rs = null;

String name = request.getParameter("name");
String id = request.getParameter("userid");
String pwd = request.getParameter("pwd");
String phone = request.getParameter("phone");

try{
	Class.forName("oracle.jdbc.driver.OracleDriver");
  	con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");
  	String sql = "update mem set name=?, pwd=?, phone=? where id=?";
  	
  	pstmt = con.prepareStatement(sql);
	pstmt.setString(1, name);
	pstmt.setString(2, pwd);
	pstmt.setString(3, phone);
	pstmt.setString(4, id);
	
	int result = pstmt.executeUpdate();
	
	if(result==1) response.sendRedirect("MemberMGR.jsp");
	else response.sendRedirect("MemberEdit.jsp?userid="+id);
  	
}catch(Exception e){
	  e.printStackTrace();
}finally{
	  try{
		  if(pstmt != null) pstmt.close();
		  if(rs != null) rs.close();
		  if(con != null) con.close();
	  }catch(Exception e){
		  e.printStackTrace();
	  }
}
%>