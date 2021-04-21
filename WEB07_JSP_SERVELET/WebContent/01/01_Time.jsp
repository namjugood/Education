<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- JSP : Java Server Page
	서버 내에서 페이지 표시에 필요한 연산을 마친 후 결과만 클라이언트로 보내주는 웹프로그래밍 언어
	JSP 이외의 웹 프로그래밍 언어로는 ASP, PHP가 있으며, 서버 및 환경에 따라 프로그래밍 언어 종류를 선택적으로 사용하게 됩니다. -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
JAVA SERVER PAGE<br />
현재시각 : <%= new java.util.Date() %>
<!-- Java.util.Date d = new java.util.Date();	d를 body의 해당위치에 표시 -->

<%-- JSP 주석 --%>
<%-- HTML 파일 안에 JSP 문법이 끼어들어 코딩될 때는 <% %>를 사용합니다 --%>
<%-- 다만 페이지에 출력될 내용을 기술한다면 <%= %> 를 쓰고 변수 이름이나 출력할 내용을 씁니다 --%>

<!-- HTML5와 JSP가 동시 사용되기 때문에 주석의 양식은 위의 두 가지를 모두 사용할 수 있습니다. -->

<!-- JSP코드는 연산기능 및 프로그래밍 언어가 갖고 있는 모든 기능이 포함되어 있으며, 그 명령들은 서버에서
실행됩니다. 명령이 실행된 결과가 페이지에 포함되는 것이므로, 클리이언트에서는 코드내용을 볼 수 없으며, 결과만
받아 보게 됩니다.
실제 코드 : new java.util.Date() 
클라이언트가 본 페이지의 소스 코드 : Wed Dec 23 09:55:35 KST 2020 -->

<!-- 게시판을 화면에 출력해야 하는 경우		데이터베이스에서 데이터를 select 해 오는 동작부터 리스트에 담아
전송하게 되는 동작까지 서버에서 JSP와 SQL로 실행하게 하며, 결과의 내용만 클라이언트에 전송해 HTML과 
CSS로 잘 정리한 게시판을 화면에서 볼 수 있게 되는 것입니다. -->

<!-- 지금처름 페이지에 JSP코드가 섞여있다면 서버는 JSP코드부터 모두 실행한 후에 페이지를 클라이언트에
전송하게 됩니다. -->
</body>
</html>