<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.box{position:relative; width:500px; height:50px; margin:0 auto; text-align:center; line-height:50px;}
	.attr1{postion:relative; width:248px; height:48px; float:left; background:yellowgreen; font-size:110%;
			text-align:center; line-height:50px; border:1px solid green;}
	.attr2{postion:relative; width:248px; height:48px; float:left; font-size:110%; text-align:center; 
			line-height:50px; border:1px solid green;}
	#footer{position:relative; width:500px; height:50px; text-align:center; line-height:50px;}				
</style>
<script type="text/javascript">

</script>
</head>
<body>
<form action="board.do" method="post" name="frm">
<input type="hidden" name="command" value="login">
	<div class="box"><div id="title">로그인</div></div>
	<div class="box"><!-- 아이디 입력란 -->
		<div class="attr1">아이디</div>
		<div class="attr2">&nbsp;&nbsp;
			<input type="text" size="20" name="userid" style="width:200px; height:20px;">
		</div>	
	</div>
	<div class="box"><!-- 패스워드 입력란 -->
		<div class="attr1">패스워드</div>
		<div class="attr2">&nbsp;&nbsp;
			<input type="password" size="20" name="pwd" style="width:200px; height:20px;">
		</div>	
	</div>
	<div class="box"><!-- 버튼 입력란 -->
		<div id="footer">
			<input type="submit" value="로그인" onClick="return loginCheck();">
			<input type="button" value="회원가입"
			onClick="location.href='board.do?command=joinform'"/>
		</div>	
	</div>
	<div class="box">${message}</div>
</form>
</body>
</html>