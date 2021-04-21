<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>세션 정보를 얻어오는 메서드를 사용하기</h3>
<%
	String id_str = session.getId();	// 현재 세션의 고유코드를 얻음
	long lasttime = session.getLastAccessedTime();	// 마지막 접근 시간 - 밀리초
	long createdtime = session.getCreationTime();		// 생성시간 - 밀리초
	// 사용시간 - 결과도 밀리초 -> 1000으로 나누어 초 단위로
	long time_used = (lasttime - createdtime) / 1000;
	session.setMaxInactiveInterval(3600);
	int inactive = session.getMaxInactiveInterval() / 60;	// 수명 - 분
	boolean b_new = session.isNew();	// 새로만든 세션의 여부
%>
[1] 세션 ID는 [<%=id_str %>] 입니다. <br><hr>
[2] 당신이 웹사이트에 머문 시간은 <%=time_used %>초 입니다.<br><hr>
[3] 세션의 유효시간은 <%=inactive %>분 입니다.<br><hr>
[4] 세션이 새로 만들어졌나요? <br>
<%
	if(b_new)out.print("예, 새로운 세션을 만들었습니다.");
	else out.print("아니오, 새로운 세션을 만들지 않았습니다.");
%>
</body>
</html>