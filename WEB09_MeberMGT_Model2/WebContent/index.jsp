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
<%response.sendRedirect("member.do?command=login_form");%>
<!-- 서블릿 하나로 모든 페이지를 호출하는 준비, 커맨드 뒤의 링크만 기능(페이지)에따라 달라짐 -->
</body>
</html>