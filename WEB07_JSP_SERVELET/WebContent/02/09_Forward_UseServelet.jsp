<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 서블릿을 사용한다는 것은 앞선 예제의 XXX_do.jsp파일의 역할을 서버 내부에 감춰서 공개하지 않을 웹클래스에
일힘하는 것과 같습니다.
프로그래밍 명령 등의 로직과 알고리즘이 공개되는 것이 부담스럽거나, 자료이동을 감추고자 할 때, 지금과 같은 서블릿을 사용합니다. '
서블릿을 사용한다는 것은 form 태그의 Action속성의 대상을 jsp파일로 하는 것이 아니라, 따로 명명된 웹클래스(서블릿)으로 연결하고,
해당 필요한 처리를 서블릿에서 처리한 후 결과를 결과페이지(jsp) 파일로 forward하는 것을 의미합니다.-->
<form method="post" action="../Forward_UseServlet">
<!-- 사용할 서블릿의 위치는 파일들이 저장되는 최상단 위치이기때문에 현재 페이지에서의 상대위치는 (../)로 연결하여 지정합니다-->
	<label for="userid">아이디 : </label>
	<input type="text" name="id" id="userid"><br>
	<label for="userpwd">암&nbsp; 호 : </label>
	<input type="password" name="pwd" id="userpwd"><br>
	<input type="submit" value="로그인">
</form>

</body>
</html>