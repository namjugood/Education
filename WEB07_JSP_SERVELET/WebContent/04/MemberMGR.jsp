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
<%!
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
<table width='800' border='1'>
<tr><th>이름</th><th>아이디</th><th>암호</th><th>전화번호</th></tr>
<!-- <tr><td></td></tr> -->
<!-- 데이터베이스에 연결하고, mem 테이블에서 레코드들을 읽어와서 레코드 갯수만큼 <tr></tr>을
반복 추가합니다 -->
<%
	try{
		Class.forName(driver);
		con = DriverManager.getConnection(url, uid, pass);
		String sql = "select * from mem";
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		while(rs.next()){
			// 레코드 단위로 <tr><td></td>...</tr>이 추가됩니다.
			out.println("<tr><td>"+rs.getString("name")+"</td>");	// tr의 시작
			String anchor="<td><a href='MemberEdit.jsp?userid="
					+rs.getString("id")+" '>"
					+rs.getString("id") + "</a></td>";
			// 현재의 id값으로 각 레코드별 다른 링크를 생성하여 anchor 태그에 링크를 걸어줍니다 
			out.println(anchor);
			out.println("<td>"+rs.getString("pwd")+"</td>");
			out.println("<td>"+rs.getString("phone")+"</td></tr>");	// tr의 끝
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
<tr><td colspan="4" align="center">
	<input type="button" value="멤버추가" onClick=" location.href='MemberAdd.jsp' "/>
</td></tr>

</table>
</body>
</html>