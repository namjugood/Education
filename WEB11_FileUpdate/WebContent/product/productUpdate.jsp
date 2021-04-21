<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/shopping.css">
</head>
<body>
<div id="wrap" align="center">
<h1>상품 수정 - 관리자 페이지</h1>
<form method="post" enctype="multipart/form-data" action="update.do">
<input type="hidden" name="code" value="${product.code}">
<input type="hidden" name="nonUpdateImg" value="${product.pictureurl}">
	<table>
		<tr>
			<td>	<!-- 이미지가 있으면 이미지를, 없으면 noname.jpg를 표시 -->
				<c:choose>
					<c:when test="${empty product.pictureurl}">
						<img src="fileUpload/noname.jpg" width="220" height="300">
					</c:when>
					<c:otherwise>
						<img src="fileUpload/${product.pictureurl}" width="220" height="300">
					</c:otherwise>	 
				</c:choose>
			</td>
			<td>
				<table border="1">
					<tr><th style="width:80px;">상품명</th><td><input type="text"
					name="name" value="${product.name}" size="80"></td></tr>
					<tr><th>가 격</th><td><input type="text" name="price" 
					value="${product.price}"> 원</td></tr>
					<tr><th>사 진</th><td><input type="file" name="pictureurl"><br> 
					(주의사항 : 이미지를 변경할 때만 선택하세요)</td></tr>
					<tr><th>설 명</th><td><textarea cols="90" rows="10" 
					name="description">${production.description}</textarea></td></tr>
				</table>
			</td>
		</tr>
	</table>
	<br><input type="submit" value="수정">
	<input type="reset" value="다시작성">
	<input type="button" value="목록" onclick="location.href='productlist.do'">
</form>
</div>
</body>
</html>