<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
String id = request.getParameter("id");
String pwd = request.getParameter("pwd");
if(id.equals("hong") && pwd.equals("1234")){
	session.setAttribute("loginUser", id);		// 로그인 정보를 세션에 저장합니다.
	response.sendRedirect("06_main.jsp");
}else{
	response.sendRedirect("06_LoginForm.jsp");
}
%>