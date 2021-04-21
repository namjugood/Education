<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function idok(userid){
	/* 팝업창의 입력란에 있는 아이디를 joinForm.jsp의 아이디 입력란에 복사 */
	opener.frm.userid.value=document.frm.userid.value;
	/* 팝업창의 입력란에 있는 아이디를 joinForm.jsp의 hidden 입력란에 복사 */
	opener.frm.reid.value=document.frm.userid.value;
	/* 팝업창 close(); */
	self.close();
}
</script>
</head>
<body>
<form action="idcheck.do" method="get" name="frm">
	아이디 : <input type="text" name="userid" value="${userid}">
	<input type="submit" value="중복체크">
</form>

<c:if test="${result==1}">
	${userid}는 이미 사용 중인 아이디입니다.
	<script type="text/javascript">
		opener.document.frm.userid.value="";
		/* 팝업창을 오픈한 주체 : opener */
	</script>
</c:if>
<c:if test="${result==0}">
	${userid}는 사용 가능한 아이디입니다.
	<input type="button" value="사용" onClick="idok('${userid}')">
</c:if>
</body>
</html>