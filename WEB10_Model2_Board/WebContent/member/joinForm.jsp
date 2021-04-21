<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/board.css">
<script src="script/board.js"></script>
</head>
<body>
<div id="wrap" align="center">
	<h1>사용자 등록</h1>
	<form name="frm" method="post" action="board.do">
	<input type="hidden" name="command" value="join">
	<table>
		<tr><th>아이디</th>
			<td><input type="text" name="userid" size="20"> *
			<input type="button" value="중복체크" onClick="idCheck();">
			<input type="hidden" name="re_id"></td></tr>
		<tr><th>이름</th><td><input type="text" size="20" name="name"> * </td></tr>
		<tr><th>비밀번호</th><td><input type="password" name="pwd"> * </td></tr>	
		<tr><th>비밀번호확인</th><td><input type="password" name="pwd_check"> * </td></tr>	
		<tr><th>이메일</th><td><input type="text" size="30" name="email"> * </td></tr>	
		<tr><th>전화번호</th><td><input type="text" size="20" name="phone"></td></tr>
		<tr><th>등급</th>
			<td><input type="radio" name="admin" value="0" checked="checked"> 일반회원
			<input type="radio" name="admin" value="1" > 관리자</td></tr>	
	</table><br><br>
	<input type="submit" value="등록" onClick="return joinCheck()">
	<input type="reset" value="다시작성">
	<input type="button" value="뒤로" onClick="location.href='board.do?command=loginform'">
	</form>
</div>
</body>
</html>