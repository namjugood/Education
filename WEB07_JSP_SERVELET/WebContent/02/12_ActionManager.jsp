<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>관리자로 로그인 성공</h3>
<%
String userName = request.getParameter("userName");
// String userName = (String)request.getAttribute("userName"); // - null 값이 전송됨
out.print(userName);
%>
(<%=request.getParameter("userID") %>)님 환영합니다.
</body>
</html>