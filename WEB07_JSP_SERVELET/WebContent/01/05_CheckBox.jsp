<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>악세사리</h2>
관심항목을 선택하세요
	<hr>
	<form method="get" action="05_CheckBox_to.jsp">
		<input type="checkbox" name="item" value="shoes"> 신발
		<input type="checkbox" name="item" value="bag"> 가방
		<input type="checkbox" name="item" value="belt"> 벨트	<br>
		<input type="checkbox" name="item" value="cap"> 모자
		<input type="checkbox" name="item" value="watch"> 시계
		<input type="checkbox" name="item" value="diamond"> 주얼리	<br>
		<input type="submit" value="전송">
	</form>
	<!-- Checkbox는 다른 입력선택란과 달리, 화면에 입력하거나 입력된 내용이 전달되지 않고 태그 
	안에 별도로 지정한 value값이 전달됩니다. -->
</body>
</html>