<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>전체 회원 정보</h2>
<table border="1">
	<tr><th width="70">이름</th><th width="70">아이디</th><th width="200">이메일</th>
	<th width="150">전화</th><th width="70">등급</th></tr>
	<c:forEach var="mem" items="${memberlist}">
		<tr><td align="center">${mem.name}</td><td align="center">${mem.userid}</td>
		<td>${mem.email}</td><td align="center">${mem.phone}</td>
		<td align="center">${mem.admin}</td></tr>
	</c:forEach>
	<tr>
		<td colspan="5" align="right">
			<input type="button" onClick="location.href='member.do?command=login_form'" value="메인으로">	
		</td>
	</tr>	
</table>
</body>
</html>