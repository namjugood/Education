<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
request.setCharacterEncoding("UTF-8");

String id = request.getParameter("userid");
String name = request.getParameter("name");
String pwd = request.getParameter("pwd");
String nickname = request.getParameter("nickname");
String phone = request.getParameter("phone");
String email = request.getParameter("email");
%>
<jsp:useBean id="member" class="com.ezen.dto.MemberBean" scope="request" />
<jsp:setProperty name="member" property="name" value="<%=name %>" />
<jsp:setProperty name="member" property="userid" value="<%=id %>" />
<jsp:setProperty name="member" property="pwd" value="<%=pwd %>" />
<jsp:setProperty name="member" property="nickname" value="<%=nickname %>" />
<jsp:setProperty name="member" property="email" value="<%=email %>" />
<jsp:setProperty name="member" property="phone" value="<%=phone %>" />

<%
// request.setAttribute("member", member);
// scope="request" 로 인해 request에 저장이 되어 있어 위의 내용은 없어도 무관
RequestDispatcher dispatcher = request.getRequestDispatcher("08_useBeanActionTag03.jsp");
dispatcher.forward(request, response);
%>
