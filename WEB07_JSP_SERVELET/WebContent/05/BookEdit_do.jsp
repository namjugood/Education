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

String booknum = request.getParameter("booknum");
String subject = request.getParameter("subject");
String makeyear = request.getParameter("makeyear");
String inprice = request.getParameter("inprice");
String outprice = request.getParameter("outprice");
String grade = request.getParameter("grade");

try{
	Class.forName("oracle.jdbc.driver.OracleDriver");
  	con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");
  	String sql = "update booklist set subject=?, makeyear=?, inprice=?, outprice=?, grade=? where booknum=?";
  	
  	pstmt = con.prepareStatement(sql);
  	pstmt.setString(1, subject);
	pstmt.setInt(2, Integer.parseInt(makeyear));
	pstmt.setInt(3, Integer.parseInt(inprice));
	pstmt.setInt(4, Integer.parseInt(outprice));
	pstmt.setString(5, grade);
	pstmt.setString(6, booknum);
	
	int result = pstmt.executeUpdate();
	
	if(result==1) response.sendRedirect("BookMGR.jsp");
	else response.sendRedirect("BookEdit.jsp?booknum="+booknum);
  	
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