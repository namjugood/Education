<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%

String userID=request.getParameter("userID");
String userPwd=request.getParameter("userPwd");
String loginCheck=request.getParameter("loginCheck");
String url="";
/*
//sendRedirect를 사용한 경우
 if(userID.equals("scott") && userPwd.equals("1234") && loginCheck.equals("user"))
	response.sendRedirect("12_ActionUser.jsp?userID="+userID+"&userName=Gildong");
else if(userID.equals("scott") && userPwd.equals("1234") && loginCheck.equals("manager"))
	response.sendRedirect("12_ActionManager.jsp?userID="+userID+"&userName=Gildong");
else
	response.sendRedirect("12_ActionTagForm.jsp"); 

// --> sendRedirect로 끌어온 경우 결과창은 getParameter로 받아야 함
*/

//forward를 사용한 경우

/* if(userID.equals("scott") && userPwd.equals("1234") && loginCheck.equals("user")){
	url="12_ActionUser.jsp";
	request.setAttribute("userName", "홍길동");
}else if(userID.equals("scott") && userPwd.equals("1234") && loginCheck.equals("manager"))
	url="12_ActionManager.jsp";
	request.setAttribute("userName", "홍길남");
}else
	response.sendRedirect("12_ActionTagForm.jsp");

RequestDispatcher dispatcher = request.getRequestDispatcher(url);
dispatcher.forward(request, response); 

//--> forward로 끌어온 경우 결과창은 getAttribute로 받아야 함
*/

// 액션태그를 사용한 경우(사용 빈도는 적음)
if(userID.equals("scott") && userPwd.equals("1234") && loginCheck.equals("user")){
 %>
 	<jsp:forward page="12_ActionUser.jsp">
 	<jsp:param value="Gildong" name="userName" />
 	</jsp:forward>
 
 <%
 }else if(userID.equals("scott") && userPwd.equals("1234") && loginCheck.equals("manager")){
 %>
 	<jsp:forward page="12_ActionManager.jsp">
 	<jsp:param value="Gildong" name="userName" />
 	</jsp:forward>
 <%
 }else {
	response.sendRedirect("12_ActionTagForm.jsp");
 }
 %>
