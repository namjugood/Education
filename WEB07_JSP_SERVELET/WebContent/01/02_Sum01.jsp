<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- JSP는 JAVA에서 사용하던 거의 모든 문법이 똑같이 사용됩니다. -->
<!-- 화면구성을 HTML5와 CSS가 모두 담당해왔으나, 그 중 일부를 아래와 같이 JSP가 대신 담당해 줄 것입니다. -->

<%
	int sum=0;
	for(int i=1; i<=1000; i++){
		sum+=i;
	}
%>
1부터 1000까지의 합<%= sum %>	
</body>
</html>