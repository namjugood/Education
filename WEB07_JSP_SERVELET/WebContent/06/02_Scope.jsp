<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
request.setAttribute("name", "request man"); //2
session.setAttribute("name", "session man"); //3
application.setAttribute("name", "request man"); //4
pageContext.setAttribute("name", "page man"); //1
%>

name : ${name}<hr>
page 속성 : ${pageScope.name}<br>
request 속성 : ${requestScope.name}<br>
session 속성 : ${sessionScope.name}<br>
application 속성 : ${applicationScope.name}<br>
</body>
</html>