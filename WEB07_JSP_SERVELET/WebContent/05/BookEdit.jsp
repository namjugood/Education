<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.DriverManager" %>
<%@page import="java.sql.ResultSet" %>
<%@page import="java.sql.PreparedStatement" %>
<%@page import="java.sql.Connection" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%!
Connection con = null;
PreparedStatement pstmt = null;
ResultSet rs = null;

String driver= "oracle.jdbc.driver.OracleDriver";
String url = "jdbc:oracle:thin:@localhost:1521:xe";
String uid = "scott";
String pass = "tiger";

String subject="";
int makeyear=0;
int inprice=0;
int outprice=0;
String grade="전체";


%>
</head>
<body>
<% String booknum = request.getParameter("booknum"); 
	  try{
		  Class.forName(driver);
		  con = DriverManager.getConnection(url, uid, pass);
		  String sql = "select * from booklist where booknum=?";
		  pstmt = con.prepareStatement(sql);
		  pstmt.setString(1, booknum);
		  rs=pstmt.executeQuery();
		  
		  if(rs.next()){
			  subject=rs.getString("subject");
			  makeyear=rs.getInt("makeyear");
			  inprice=rs.getInt("inprice");
			  outprice=rs.getInt("outprice");
			  grade=rs.getString("grade");
		  }
	  }catch(Exception e){
		  e.printStackTrace();
	  }finally{
		  try{
			  if(pstmt != null) pstmt.close();
			  if(con != null) con.close();
		  }catch(Exception e){
			  e.printStackTrace();
		  }
	  }

%>


<form method="get" action="BookEdit_do.jsp">
	<table>
		<tr><td>번호</td><td><%=booknum %><input type="hidden" name="booknum" size="40" value="<%=booknum %>"></td></tr>
		<tr><td>도서명</td><td><input type="text" name="subject" size="40" value="<%=subject %>"></td></tr>
		<tr><td>출판년도</td><td><input type="text" name="makeyear" size="20" value="<%=makeyear %>"></td></tr>
		<tr><td>입고가격</td><td><input type="text" name="inprice" size="20" value="<%=inprice %>"></td></tr>
		<tr><td>출고가격</td><td><input type="text" name="outprice" size="20" value="<%=outprice %>"></td></tr>
		<tr><td>등급</td><td>
			<input type="radio" name="grade" value="전체"
			<%if(grade.equals("전체")) {%>
				checked
			<%} %>	>전체&nbsp;
			<input type="radio" name="grade" value="성인전용"
			<%if(grade.equals("성인전용")) {%>
				checked
			<%} %>	>성인전용&nbsp;
			<input type="radio" name="grade" value="청소년구매가능"
			<%if(grade.equals("청소년구매가능")) {%>
				checked
			<%} %>	>청소년구매가능&nbsp;
			
			</td></tr>
		<tr><td><input type="submit" value="수정"></td>
				<td><input type="reset" value="취소"></td></tr>
	</table>

</form>
</body>
</html>