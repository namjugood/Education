<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function loginCheck(){
		if(document.frm.userid.value.length <=3){
			alert("아이디는 네 글자 이상이어야 합니다");
			frm.userid.focus();
			return false;
		}
		if(document.frm.pwd.value == ""){
			alert("암호는 반드시 입력해야 합니다.");
			frm.pwd.focus();
			return false;
		}
		return true;
	}
</script>
</head>
<body>
<h2>로그인</h2>
<form action="login.do" method="post" name="frm">
	<table>
	<tr><td>아이디 :&nbsp;</td><td><input type="text" name="userid"></td></tr>
	<tr><td>암 &nbsp; 호 :&nbsp;</td><td><input type="password" name="pwd"></td></tr>
	<tr><td colspan="2" align="center">
			<input type="submit" value="로그인" onClick="return loginCheck();">
			<input type="reset" value="취소">
			<input type="button" value="회원가입" onClick="location.href='join.do'">
			</td></tr>
		<tr><td colspan="2">${message}</td></tr>	
	</table>
</form>
</body>
</html>