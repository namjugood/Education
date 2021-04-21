<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 하나의 작업단위(회원가입, 로그인, 게시물 등록 등)를 구성하기 위해 사용되는 입력란, 선택란 들은
작업단위로 구분되는 form 태그 안에 넣어 사용합니다 -->
<form name="reg_frm" action="04_Action_to.jsp" method="get">
<!-- name : 스크립트에서 다른것들과 구분하여 부를 이름으로 사용
action : 입력란 선택란에 입력하고 선택한 내용이 전달될 대상 파일
method : 전달방식을 의미하며, get은 일반 전송방식, post는 감춰진 전달방식으로 주로 사용합니다. -->
	이름 : <input type="text" name="name" value=""><br>
	주민등록번호 : <input type="text" name="jumin_1" value=""> -
	<input type="text" name="jumin_2" value=""><br>
	아이디 : <input type="text" name="id" value=""><br>
	비밀번호 : <input type="password" name="pwd" value=""><br>
	비밀번호 확인 : <input type="password" name="pwd_re" value=""><br>
	<input type="submit" value="회원가입">
</form>
<!-- 회원가입 버튼이 클릭되면 action에 있는 파일이름과 각 입력란의 name, value 들이 조합되어
아래의 경로로 이동합니다. 
http:''localhost:8090/WEB07_JSP_SERVLET/01/04_Action_to.jsp?name=xxx&jumin_1=xxx&
junmin_2=xxx	&id=xxx&pwd=xxx&pwd_re=xxx
-->
</body>
</html>