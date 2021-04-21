<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${empty loginUser}">
	<jsp:forward page='board.do?command=loginform'></jsp:forward>
</c:if>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/board.css">
</head>
<body>
<div id="wrap" align="center">
<h1>게시글 리스트</h1>
<table class="list">
	<tr>
		<td colspan="5" style="border:white; text-align:rignt">
			<div style="float:left">${loginUser.name}(${loginUser.userid}) 님 로그인 
				<input type="button" value="회원정보수정"
				onClick="location.href='board.do?command=editmemberform'"/>
				<input type="button" value="로그아웃"
				onClick="location.href='board.do?command=logout'"/>
			</div>
			<div style="float:right;">
				<a href="board.do?command=boardwriteform">게시글 등록</a>
			</div>		
		</td>
	</tr>
	<tr><th>번호</th><th>제목</th><th>작성자</th><th>작성일</th><th>조회</th></tr>
	<c:forEach var="board" items="${boardList}">
		<tr class="record" align="center">
			<td>${board.num}</td>
			<td align="left">
				<a href="board.do?command=boardview&num=${board.num}">
				${board.title}&nbsp; 
				<c:if test="${board.replycnt>0}">
					<span style="color:red;font-weight:bold;">[${board.replycnt}]</span>
				</c:if>
				</a></td>
			<td>${board.userid}</td>
			<td><fmt:formatDate value="${board.writedate}"/></td>
			<td>${board.readcount}</td>	
	</c:forEach>	
</table><br />
	<jsp:include page="/paging/paging.jsp">
		<jsp:param name="page" value="${paging.page}" />
		<jsp:param name="beginPage" value="${paging.beginPage}" />
		<jsp:param name="endPage" value="${paging.endPage}" />
		<jsp:param name="prev" value="${paging.prev}" />
		<jsp:param name="next" value="${paging.next}" />
	</jsp:include>
	
	<%-- <div id="paging">
		<c:url var="action" value="board.do?command=boardlist" /><!-- 링크경로 저장 -->
		<!-- 이전버튼 설정 -->
		<c:if test="${paging.prev}">
			<a href="board.do?command=boardList&page=${paging.beginPage-1}">[prev]</a>&nbsp;
			<a href="${action}&page=${paging.beginPage-1}">[prev]</a>&nbsp;
		</c:if>
		<c:forEach begin="${paging.beginPage}" end="${paging.endPage}" var="index">
			<c:choose>
				<c:when test="${paging.page==index}">
					<span style="color:red; font-weight:bold">${index}&nbsp;</span><!-- 링크없이 표시 -->
				</c:when>
				<c:otherwise>
					<a href="${action}&page=${index}">${index}</a>&nbsp;
				</c:otherwise>	
			</c:choose>
		</c:forEach>
		<c:if test="${paging.next}">
			<a href="${action}&page=${paging.endPage+1}">[next]</a>&nbsp;
		</c:if>
	</div> --%>
</div>
</body>
</html>