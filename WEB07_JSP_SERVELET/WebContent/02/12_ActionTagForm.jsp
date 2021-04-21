<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="12_ActionTag_do.jsp">
	아이디 : <input type="text" name="userID"><br>
	암 &nbsp; 호 : <input type="password" name="userPwd"><br>
	<input type="radio" name="loginCheck" value="user" checked="checked">사용자
	<input type="radio" name="loginCheck" value="manager">관리자
	<input type="submit" value="로그인">
</form>
</body>
</html>