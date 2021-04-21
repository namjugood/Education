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
int num1 = 20;
int num2 = 7;
double num3;
int add = num1 + num2;
out.print(num1 + " + " + num2 + " = " + add + "<br>");
add = num1 - num2;
out.print(num1 + " - " + num2 + " = " + add + "<br>");
add = num1 * num2;
out.print(num1 + " x " + num2 + " = " + add + "<br>");
num3 = num1 / (double)num2;
out.print(num1 + " / " + num2 + " = " + num3 + "<br>"); %>
<%-- 출력구문 <%= %>으로 출력하기에 부담스러운 내용은 위와 같이 out.print()로 출력합니다. --%>

</body>
</html>