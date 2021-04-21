<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel = "stylesheet" type="text/css" href="css/board.css">
<script type="script/board.jsp" type="text/javascript"></script>
</head>
<body>
<div id="wrap" align="center">
<h1>게시글 수정</h1>
<form name="frm" method="post" action="board.do">
<input type="hidden" name="num" value="${board.num}">
<input type="hidden" name="command" value="boardupdate">
<table>
	<tr><th>작성자</th><td>${board.userid}
	<input type="hidden" name="userid" value="${loginUser.userid}"></td></tr>
	<tr><th>비밀번호</th><td><input type="password" name="pass" size="12"> * 
	필수(게시물 수정 삭제 시 필요합니다)</td></tr>
	<tr><th>이메일</th><td><input type="text" value="${board.email}" name="email"></td></tr>
	<tr><th>제목</th>
		<td><input type="text" value="${board.title}" name="title"></td>
	</tr>
	<tr><th>내용</th><td>
	<textarea cols="70" rows="15" name="content">${board.content}</textarea>
	</td></tr>
	</table><br>
<input type="submit" value="수정" onclick="return boardCheck();">
<input type="reset" value="다시작성">
<input type="button" value="목록" onclick="location.href='board.do?command=board_list'">
</form>
</div>
</body>
</html>
