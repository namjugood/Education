<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.ezen.dto.MemberBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	// JSP로 객체를 선언하는 방법
MemberBean member = new MemberBean();
MemberBean member2 = new MemberBean();
%>
<!-- 액션태그로 객체를 선언하는 방법 -->
<jsp:useBean id="member3" class="com.ezen.dto.MemberBean" />

◎ 객체생성 후 정보 입력<br>
<%
member3.setName("홍길동");
member3.setUserid("HongGilDong");
%>
이름 : <%=member3.getName() %><br>
아이디 : <%=member3.getUserid() %>
<br><br>
◎ 정보를 변경한 후 변경된 정보 출력하기<br>
<%
member3.setName("김하나");
member3.setUserid("KimHana");
%>
이름 : <%=member3.getName() %><br>
아이디 : <%=member3.getUserid() %>
<hr><br><br>

◎ 액션태그로 정보를 변경한 후 변경된 정보 출력하기<br><br>
<jsp:setProperty name="member3" property="name" value="박찬호 "/>
<jsp:setProperty name="member3" property="userid" value="ParkChanHo "/>
이름 : <jsp:getProperty name="member3" property="name" /><br>
아이디 : <jsp:getProperty name="member3" property="userid" />
</body>
</html>