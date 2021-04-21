<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.Enumeration" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>세션에 저장된 값 하나씩 추출</h3>
<%
String id = (String) session.getAttribute("id");
String pwd = (String) session.getAttribute("pwd");
Integer age = (Integer) session.getAttribute("age");
%>

id : <%=id %><br>
pwd : <%=pwd %><br>
age : <%=age %><br>
<!-- 세션의 수명은 브라우저가 닫힐 때까지 입니다. 브라우저 하위수준인 탭이 닫혀도
세션은 살아있습니다 -->

<h3>세션에 설정된 모든 값 얻어오기</h3>
<%
	Enumeration<String> names = session.getAttributeNames();
	// page import="java.util.Enumeration" 임포트 필요
	// 세션의 변수이름 목록을 얻어와서, 이름의 갯수만큼 반복실행합니다
	while(names.hasMoreElements()){
		String name = names.nextElement().toString();
		// 반복실행되는 동안 이름을 getAttribute에 넣어서 변수의 저장된 값을 추출합니다.
		String value = session.getAttribute(name).toString();
		out.println(name+" : "+value+"<br>");
	}
%>
</body>
</html>