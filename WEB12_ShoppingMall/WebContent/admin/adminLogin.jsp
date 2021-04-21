<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="admin/css/admin.css">
<script type="text/javascript">
function worker_check(){
	  if(document.frm.workId.value==""){
	      	alert("아이디를 입력하세요.");
	      	return false;
	  }else if(document.frm.workPwd.value==""){
	     	alert("비밀번호를 입력하세요.");
	      	return false;
	  }
	  return true;  
}
</script>
</head>
<body>
<div id="wrap">
<header>
	<div id="logo"><img src="admin/images/bar_01.gif" style="float:left;">
	<img src="admin/images/text.gif"></div>
</header><div class="clear"></div>
<article>
<div id="loginform">
<form name="frm" method="post" action="shop.do">
<input type="hidden" name="command" value="adminLogin">
<table>
	<tr><td>아 이 디</td><td>
		<input type="text" name="workId" size="10"></td></tr>
	<tr><td>비밀번호</td>
		<td><input type="password" name="workPwd" size="10"></td></tr>
	<tr align="center" ><td  colspan="2">
		<input class="btn" type="submit" value="로그인" onClick="return workerCheck();">
		<br><br><h4 style="color:red">${message}</h4></td></tr>
</table></form></div></article>
</div>
</body>
</html>