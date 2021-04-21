<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/board.css">
<script src="script/board.js" type="text/javascript"></script>
<script type="text/javascript">

</script>
</head>
<body>
<form action="board.do" method="post" name="frm">
<input type="hidden" name="command" value="idcheck">
	아이디 : <input type="text" name="userid" value="${userid}">
	<input type="submit" value="중복체크">
</form>
	<c:if test="${result==1}">
		<script type="text/javascript">
			opener.document.frm.userid.value="";
		</script>
		${userid}는 이미 사용 중인 아이디입니다.
	</c:if>
	<c:if test="${result==0}">
		${userid}는 사용 가능한 아이디입니다.
		<input type="button" value="사용" onClick="idok('${userid}')">
	</c:if>
</body>
</html>