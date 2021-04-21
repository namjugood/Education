<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:useBean id="member" class="com.ezen.dto.MemberBean" scope="request" />
<%
	member = (com.ezen.dto.MemberBean)request.getAttribute("member");
%>
<h2>입력 완료된 회원 정보</h2>
<table>
	<tr><td>이름</td>
	<td><jsp:getProperty name="member" property="name" /></td></tr>
	<tr><td>아이디</td><td><%=member.getUserid() %></td></tr>
	<tr><td>별명</td><td><%=member.getNickname() %></td></tr>
	<tr><td>비밀번호</td><td><%=member.getPwd() %></td></tr>
	<tr><td>이메일</td><td><%=member.getEmail() %></td></tr>
	<tr><td>전화번호</td><td><%=member.getPhone() %></td></tr>
</table>
</body>
</html>