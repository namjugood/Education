<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- JSP 선언부 : JSP에서 사용할 변수 또는 함수(메서드)를 정의하는 곳-->
<%!
	String str = "안녕하세요!!";
	int a = 5, b= -5;
	// 함수의 정의
	public int abs(int n){
		if(n<0) n=-n;	//n= -1*n
		return n;
	}
	// JSP에서 사용자가 함수(메서드)를 선언하고 사용할 때는 자바와 다르게 static을 쓰지 않아도
	// 페이지가 로딩됨과 동시에 함수를 인지하고 컴파일하여 준비합니다. %>	
</head>
<body>
<%out.print(str + "<br>");
out.print(a + "의 절대값 : " + abs(a) + "<br>");
out.print(b + "의 절대값 : " + abs(b) + "<br>");
%><br><br>
<%=str%><br>
<%=a %>의 절대값 :		<%=abs(a) %><br>
</body>
</html>