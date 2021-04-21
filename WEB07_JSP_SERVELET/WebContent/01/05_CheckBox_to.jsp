<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% // String items = request.getParameter("item");
	String items[] = request.getParameterValues("item");
	if(items==null){ %>
	선택한 항목이 없습니다
<%}else{%>
	선택한 항목은 아래와 같습니다<br />
<%	for(String it : items){%>
		<%=it  %>&nbsp;&nbsp;
<%	}
	}%>	
</body>
</html>