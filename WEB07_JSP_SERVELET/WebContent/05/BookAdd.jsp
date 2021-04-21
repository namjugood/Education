<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>도서 정보 추가 입력</h2>
<form method="get" action="BookAdd_do.jsp">
	<table>
		<tr><td>도서명</td><td><input type="text" name="subject" size="40"></td></tr>
		<tr><td>출판년도</td><td><input type="text" name="makeyear" size="20"></td></tr>
		<tr><td>입고가격</td><td><input type="text" name="inprice" size="20"></td></tr>
		<tr><td>출고가격</td><td><input type="text" name="outprice" size="20"></td></tr>
		<tr><td>등급</td><td>
			<input type="radio" name="grade" value="전체" checked>전체&nbsp;
			<input type="radio" name="grade" value="성인전용">성인전용&nbsp;
			<input type="radio" name="grade" value="청소년구매가능">청소년구매가능&nbsp;
			</td></tr>
		<tr><td><input type="submit" value="전송"></td>
				<td><input type="reset" value="취소"></td></tr>
	</table>

</form>
</body>
</html>