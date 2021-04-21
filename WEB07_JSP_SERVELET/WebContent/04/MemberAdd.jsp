<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>회원정보 입력 폼</h2>
<form method="post" action="MemberAdd_do.jsp">
	<table>
		<tr><td>이름</td><td><input type="text" name="name" size="20"></td></tr>
		<tr><td>아이디</td><td><input type="text" name="userid" size="20"></td></tr>
		<tr><td>암호</td><td><input type="password" name="pwd" size="20"></td></tr>
		<tr><td>전화번호</td><td><input type="text" name="phone" size="11"></td></tr>
		<tr><td><input type="submit" value="전송"></td>
				<td><input type="reset" value="취소"></td></tr>
	</table>

</form>
</body>
</html>