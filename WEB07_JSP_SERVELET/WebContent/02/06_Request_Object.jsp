<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- request 객체가 담고 있는 정보들 -->
컨텍스트 패스 : <%=request.getContextPath() %>	<br>
요청방식 : <%=request.getMethod() %>	<br>
요청한 URL : <%=request.getRequestURL() %>	<br>
요청한 URI : <%=request.getRequestURI() %>	<br>
서버의 이름 : <%=request.getServerName() %>	<br>
프로토콜 : <%=request.getProtocol() %>	<br>
<!-- 모든 jsp파일에는 response 객체와 request 객체가 포함되어 있습니다.
request는 주로 서버에서 관련 자료를 받는 역할을 담당하고,
response는 주로 서버로 또는 화면으로 관련 자료를 보내거나 출력하는 역할을 담당합니다.
out.print() 함수로 사용되는 out객체도 현재 파일에 담겨있는 객체이며 자유롭게 사용이 가능합니다. -->
</body>
</html>