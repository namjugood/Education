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

String name="";
String phone="";


%>
</head>
<body>
<% String userid = request.getParameter("userid"); 
	  try{
		  Class.forName(driver);
		  con = DriverManager.getConnection(url, uid, pass);
		  String sql = "select * from mem where id=?";
		  pstmt = con.prepareStatement(sql);
		  pstmt.setString(1, userid);
		  rs=pstmt.executeQuery();
		  
		  if(rs.next()){
			  name=rs.getString("name");
			  phone=rs.getString("phone");
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
<h2>회원정보 수정 폼</h2>
<form method="post" action="MemberEdit_do.jsp">
	<table>
		<tr>
			<td>이름</td>
			<td><input type="text" name="name" size="20" value="<%=name %>"></td>
		</tr>
		<tr>
			<td>아이디</td>
			<td><%=userid %><input type="hidden" name="userid" value="<%=userid %>"></td>
		</tr>
		<tr>
			<td>암호</td>
			<td><input type="password" name="pwd" size="20" ></td>
		</tr>
		<tr>
			<td>전화번호</td>
			<td><input type="text" name="phone" size="11" value="<%=phone %>"></td>
		</tr>
		<tr>
			<td><input type="submit" value="수정"></td>
			<td><input type="reset" value="취소"></td>
		</tr>
	</table>
</form>
</body>
</html>