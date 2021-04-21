<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="13_Header.jsp" />
<%-- <%@ include file="13_Header.jsp" %>  --%>

<h1>메인 화면입니다.</h1> <br>
<a href="13_sub.jsp">서브페이지로 이동</a>

<jsp:include page="13_Footer.jsp" />
<%-- <%@ include file="13_Footer.jsp" %> --%>
</body>
</html>