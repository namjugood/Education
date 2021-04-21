<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${empty loginUser}">
	<jsp:forward page='board.do?command=loginform' />
</c:if>
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
	<h1>게시글 상세보기</h1>
	<table>
		<tr>
			<th>작성자</th><td>${board.userid}</td>
			<th>이메일</th><td>${board.email}</td>
		</tr>
		<tr>
			<th>작성일</th><td><fmt:formatDate value="${board.writedate}"/></td>
			<th>조회수</th><td>${board.readcount}</td>
		</tr>
		<tr><th>제목</th><td colspan="3">${board.title}</td></tr>
		<tr><th>내용</th><td colspan="3"><pre>${board.content}</pre></td></tr>
	</table>
	<br><br>
	<input type="button" value="리스트" onClick="location.href='board.do?command=boardlist'">
	<c:if test="${loginUser.userid==board.userid}">
		<input type="button" value="게시글 수정"
		onClick="open_win('board.do?command=boardpassform&num=${board.num}','update')">
		<input type="button" value="게시글 삭제"
		onClick="open_win('board.do?command=boardpassform&num=${board.num}','delete')">
	</c:if>
</div>

<!-- 여기서부터 댓글 작성 영역 -->
<br>
<c:set var="now" value="<%=new java.util.Date() %>"></c:set>
<div id="wrap" align="center">
<form action="board.do" method="post" name="frm2">
<input type="hidden" name="command" value="addreply">
<input type="hidden" name="boardnum" value="${board.num}">
	<table>
		<tr><th>작성자</th><th>작성일시</th><th>내용</th><th>&nbsp;</th></tr>
		<tr align="center">
			<td width="100">${loginUser.userid}
			<input type="hidden" name="userid" value="${loginUser.userid}"></td>
			<td width="100">
				<fmt:formatDate value="${now}" pattern="MM/dd HH:mm"/>
			<td width="670"><input type="text" name="reply" size="80">
			<td width="100">
				<input type="submit" value="답글작성" onclick="return reply_check();">
			</td>	
		</tr>
		<c:forEach var="reply" items="${replyList}">
			<tr>
				<td align="center">${reply.userid}</td>
				<td align="center">
				<fmt:formatDate value="${reply.writedate}" pattern="MM/dd HH:mm"></fmt:formatDate>
				</td>
				<td>${reply.content}</td>
				<td align="center">
					<c:if test="${reply.userid==loginUser.userid}">
						<input type="button" value="삭제" 
						onclick="location.href='board.do?command=deletereply&num=${reply.num}&boardnum=${reply.boardnum}'">	
					</c:if>&nbsp;
				</td>
			</tr>
		</c:forEach>
	</table>
</form>
</div>
</body>
</html>