<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import = "java.util.Enumeration" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	session.setAttribute("s_name1", "저는 세션에 저장된 첫 번째 값입니다.");
	session.setAttribute("s_name2", "저는 세션에 저장된 두 번째 값입니다.");
	session.setAttribute("s_name3", "저는 세션에 저장된 세 번째 값입니다.");
	out.print(" <h3> >> 세션 값을 삭제하기 전 << </h3> ");
	Enumeration names = session.getAttributeNames();
	while(names.hasMoreElements()){
		String name = names.nextElement().toString();
		String value = session.getAttribute(name).toString();
		out.println(name + " : " + value + "<br>");
	}
	
	// 이름을 지정하여 세션에 저장된 객체를 제거합니다.
	session.removeAttribute("s_name2");
	out.print("<hr> <h3> >> 세션값을 삭제한 후 << </h3>");
	names=session.getAttributeNames();
	while(names.hasMoreElements()){
		String name = names.nextElement().toString();
		String value = session.getAttribute(name).toString();
		out.println(name + " : " + value + "<br>");
	}
%>
</body>
</html>