<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%-- 표현 언어(EL, Expression Language 
: JSP의 전달값이나 변수값 등의 자료를 출력할 때의 번거로움을 간단히 해결하기 위해 만들어진 출력문법 --%>
JSP 문법 사용 1 : <%="Hello" %><br>
JSP 문법 사용 2 : <%out.println("Hello"); %><br>
EL 문법 사용 : ${"Hello"}<br>
<%-- EL 언어의 특징 : 모든 표현을 ${}를 이용하여 표현, 출력합니다. 위 언어 표현의 경우
${} 안에 JSP에서 값을 저장하고 있는 변수의 이름을 써도 출력됩니다. 다만 별도의 변수 설정이
필요합니다.--%>
<%
String s = "Hello World";
%>
JSP 문법 사용 1 : <%=s %><br>
JSP 문법 사용 2 : <% out.println(s); %><br>
EL 문법 사용 : ${s}<br><%-- JSTL 변수 설정 후에 출력이 가능합니다 --%>
<br>
정수형 : ${10} <br>
실수형 : ${5.6} <br>
문자열형 : ${"홍길동"} <br>
논리형 : ${true} <br>
null : ${null} <br>
<br>
<%-- 표현 언어(산술 연산) --%>
10과 2의 합 : ${10 + 2}<br>
<!-- \${10-2}는 모양 그대로 출력, ${10-2}는 계산된 결과 출력 -->
\${10 - 2} : ${10 - 2}<br>
\${10 * 2} = ${10 * 2}<br>
\${10 / 2} = ${10 / 2}<br>
\${10 % 3} = ${10 % 3}<br>
\${10 mod 3} = ${10 mod 3}<br>
<br>
<%-- 표현 언어 (비교 연산) --%>
\${10==2} = ${10==2}<br>
\${10 eq 2} = ${10 eq 2}<br>
\${10 < 2} = ${10< 2}<br>
\${10 lt 2} = ${10 lt 2}<br>
\${5 >= 4} = ${5 <= 4}<br>
\${empty("")} = ${empty("")}<br>
<br>
<!-- 서로 다른 자료형간의 연산 -->
\${"2" + 5} = ${"2" + 5}<br>
\${"2" * 5} = ${"2" * 5}<br>
\${null + 1} = ${null + 1}<br>
<br>
<%-- error 
\${"이젠" + "아이티 아카데미"} = ${"이젠"+"아이티 아카데미"}<br>
\${"일" + 5} = ${"일" + 5}<br>
--%>
<%-- 문자열 연결(EL 3.0추가) --%>
\${"문자" += "열" += "연결"} = ${"문자" += "열" += "연결"}<br>
<br>
<%
	request.setAttribute("name", "홍길동");
	pageContext.setAttribute("name", "강감찬");
%>
<%=request.getAttribute("name") %><br>
${requestScope.name}<br>
${name}	
<!-- Attribute의 이름이 중복되었을 때 우선순위 : 
pageContext > request > session >application 순서로 먼저 출력됩니다-->
<br>
<br>
</body>
</html>