<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet" %>
<%@page import="java.sql.Connection" %>
<%@page import="java.sql.PreparedStatement" %>
<%@page import="java.sql.DriverManager" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
String driver = "oracle.jdbc.driver.OracleDriver";
String url = "jdbc:oracle:thin:@localhost:1521:xe";
String uid = "scott";
String pass = "tiger";

Connection con = null;
PreparedStatement pstmt = null;
ResultSet rs = null;

%>
</head>
<body>
<table width='800' border='1' >
<tr><th width="50">번호</th><th width="240">도서명</th><th width="80">출판년도</th>
<th width="100">입고가격</th><th width="100">출고가격</th><th width="200">등급</th></tr>
<%
	try{
		Class.forName(driver);
		con = DriverManager.getConnection(url, uid, pass);
		String sql = "select * from booklist";
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		while(rs.next()){
			String anchor="<tr><td align='center'><a href='BookEdit.jsp?booknum="
					+rs.getString("booknum")+" '>"
					+rs.getString("booknum") + "</a></td>";
			out.println(anchor);
			out.println("<td>"+rs.getString("subject")+"</td>");
			out.println("<td align='center'>"+rs.getInt("makeyear")+"</td>");
			out.println("<td align='center'>"+rs.getInt("inprice")+"</td>");
			out.println("<td align='center'>"+rs.getInt("outprice")+"</td>");
			out.println("<td align='center'>"+rs.getString("grade")+"</td></tr>");	// tr의 끝
		}
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		try{
			if(rs != null)	rs.close();
			if(pstmt != null) pstmt.close();
			if(con != null)	con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
%>
<tr><td colspan="6" align="center">
	<input type="button" value="도서추가" onClick=" location.href='BookAdd.jsp' "/>
</td></tr>

</table>
</body>
</html>