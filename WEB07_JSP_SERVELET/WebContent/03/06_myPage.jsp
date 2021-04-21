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
if(session.getAttribute("loginUser")==null){
	response.sendRedirect("06_LoginForm.jsp");
}
%>
아이디 : <%=session.getAttribute("loginUser") %><br>
성명 : 홍길동
주소 : 서울 마포구...
전화번호 : 010-XXXX-XXXX<br>
<input type="button" value="정보수정" />

</body>
</html>