<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function joinCheck(){
	if(document.frm.name.value.length==0){
		alert("이름을 입력하세요");
		frm.name.focus();
		return false;
	}
	if(document.frm.userid.value.length<4){
		alert("아이디는 4글자 이상이어야 합니다.");
		frm.uerid.focus();
		return false;
	}
	if(document.frm.pwd.value==""){
		alert("암호는 반드시 입력해야 합니다");
		frm.pwd.focus();
		return f
	}
	if(document.frm.pwd.value != document.frm.pwd_check.value){
		alert("암호가 일치하지 않습니다");
		frm.pwd_check.focus();
		return false;
	}
	if(document.frm.reid.value != document.frm.userid.value){
		alert("중복체크를 하지 않았거나 중복된 아이디입니다.");
		frm.userid.focus(); // hidden에는 focus를 줄 수가 없음
		return false;
	}
	return true;
}

function idCheck(){
	/* userid란에 사용하고자하는 아이디를 먼저 입력하고 중복체크버튼을 누르게 하기 위해
	userid 란에 내용이 없으면 아이디를 먼저 입력하라고 메세지를 출력합니다.*/
	if(document.frm.userid.value.length<4){
		alert("아이디를 입력하세요, 최소 4글자 입력");
		frm.userid.focus();
		return false;
	}
	var inputid = document.frm.userid.value; // 입력한 아이디 추출
	var opt = "toolbar = no, scrollbars=no, resizable=no, width=500, height=200";
	// idcheck.do에 먼저 갔다가 포워딩되어 온 페이지가 팝업창에 나타납니다.
	window.open("member.do?command=idcheck&userid=" + inputid, "idcheck", opt);
}
</script>
</head>
<body>
<h2>회원가입</h2>
'*'표시 항목은 필수 입력 항목입니다.
<form action="member.do" method="post" name="frm">
<input type="hidden" name="command" value="join">
	<table>
		<tr><td>이름</td><td><input type="text" name="name" size="20"> * </td></tr>
		<tr><td>아이디</td><td><input type="text" name="userid" size="20"> * 
			<input type="button" value="중복체크" onClick="idCheck();">
			<input type="hidden" name="reid" value=""></td></tr>
		<tr><td>암호</td><td><input type="password" name="pwd" size="20"> * </td></tr>
		<tr><td>암호확인</td><td><input type="password" name="pwd_check" size="20">&nbsp;*</td></tr>
		<tr><td>이메일</td><td><input type="text" name="email" size="20"></td></tr>
		<tr><td>전화번호</td><td><input type="text" name="phone" size="20"></td></tr>
		<tr><td>등급</td>
			<td><input type="radio" name="admin" value="0" checked> 일반회원
			 		<input type="radio" name="admin" value="1" > 관리자 </td></tr>
 		<tr><td colspan="2" align="center">
 			<input type="submit" value="확인" onClick="return joinCheck()">&nbsp;&nbsp;
 			<input type="reset" value="취소">&nbsp;&nbsp;
 			<input type="button" value="돌아가기" onClick="location.href='member.do?command=login_form'">
 			</td></tr>
	</table>
</form>
</body>
</html>