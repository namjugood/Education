<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method="get" action="06_Select_to.jsp">
	<label for="job">직업</label>
	<select id="job" name="job" size="1">
		<option value="">선택하세요</option>
		<option value="학생1">학생</option>
		<option value="컴퓨터/인터넷1">컴퓨터/인터넷</option>
		<option value="언론1">언론</option>
		<option value="공무원1">공무원</option>
		<option value="군인1">군인</option>
	</select>
	<label for="interest" style="float:left;">관심분야</label>
	<select id="interest" name="interest" size='3' multiple="multiple">
		<option value="에스프레소1">에스프레소</option>
		<option value="로스팅1">로스팅</option>
		<option value="생두1">생두</option>
		<option value="원두1">원두</option>
		<option value="핸드드립1">핸드드립</option>
	</select><br><br>
	<input type="submit" value="전송">
</form>
</body>
</html>