<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String job = request.getParameter("job");
	String interests[] = request.getParameterValues("interest");
%>
	당신이 선택한 직업 : <%=job %><br>
	당신이 선택한 관심분야 : <br>
<%
	if(interests==null) out.print("선택한 항목이 없습니다.");
	else{
		for(String interest : interests){
%>
			<%=interest %>,
<%						
		}
	}
%>	
</body>
</html>