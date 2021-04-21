<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
first의 pageContext 속성 : <%=pageContext.getAttribute("name") %><br>
first의 request 속성 : <%=request.getAttribute("name") %><br>
first의 session 속성 : <%=session.getAttribute("name") %><br>
first의 application 속성 : <%=application.getAttribute("name") %><br>
<a href="10_ObjectLife_Third.jsp"> 세 번째 페이지</a>
</body>
</html>