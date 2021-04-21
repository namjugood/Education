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
session.invalidate();		// 현재 세션아이디에 저장된 세션의 내용을 모두 지웁니다.
%>

<script type="text/javascript">
alert("로그아웃되었습니다.");
location.href="06_LoginForm.jsp";
</script>

</body>
</html>