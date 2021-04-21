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
request.setCharacterEncoding("UTF-8");
String driver= "oracle.jdbc.driver.OracleDriver";
String url = "jdbc:oracle:thin:@localhost:1521:xe";
String uid = "scott";
String pass = "tiger";

String subject = request.getParameter("subject");
String makeyear = request.getParameter("makeyear");
String inprice = request.getParameter("inprice");
String outprice = request.getParameter("outprice");
String grade = request.getParameter("grade");

try{
	Class.forName(driver);
	con = DriverManager.getConnection(url, uid, pass);
	String sql = "insert into booklist(booknum, subject, makeyear, inprice, outprice, grade)" 
			+" values(booklist_seq.nextVal,?,?,?,?,?)";
	pstmt = con.prepareStatement(sql);
	pstmt.setString(1, subject);
	pstmt.setInt(2, Integer.parseInt(makeyear));
	pstmt.setInt(3, Integer.parseInt(inprice));
	pstmt.setInt(4, Integer.parseInt(outprice));
	pstmt.setString(5, grade);
	
	int result = pstmt.executeUpdate();
	
	if(result==1) response.sendRedirect("BookMGR.jsp");
	else response.sendRedirect("BookAdd.jsp");
	
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