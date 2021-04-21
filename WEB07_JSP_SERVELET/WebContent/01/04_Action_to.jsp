<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>아래의 내용으로 회원가입이 완료되었습니다</h1>
	get 방식으로 전송된 주소경로 및 결과 : <br/>
	http://localhost:8090/WEB07_JSP_SERVELET/01/04_Action_to.jsp?
	name=sss&jumin_1=&jumin_2=&id=&pwd=&pwd_re=<br /><br />
	
	post 방식으로 전송된 주소경로 및 결과 : <br />
	http://localhost:8090/WEB07_JSP_SERVLET/01/03_Action_to.jsp<br /><br />
	
	<!-- form 태그에 의해 전송된 데이터들은 대상페이지의 주소를 통해 전달됩니다
	주로 get 방식에서 그 모양을 확인할 수 있으며, post 방식은 내용이 숨겨저 전달되므로 모양을 확인할 수 없습니다. 
	다만 둘 다 전달되는 내용은 같은 내용이 전달되고 사용방법도 똑같습니다.-->
	
	<!-- 전달된 내용의 사용 방법 -->
	<%
		request.setCharacterEncoding("UTF-8");	//전달내용의 한글 인코딩을 UTF-8로 맞춤
		String name = request.getParameter("name");
		String jumin_1 = request.getParameter("jumin_1");
		String jumin_2 = request.getParameter("jumin_2");
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd_re");
		String pwd_re = request.getParameter("pwd_re");
	%>
	<!-- JSP에서도 변수를 선언하고 저장하여 화면에 출력할 수 있습니다.
	전달된 form의 요소들을 하나하나 얻어내는 방법
	변수 = request.getParameter("전달요소의 name 값");
	위의 명령이 실행되면 전달요소의 value 값(name과 같이 전달된 입력내용)이 변수에 저장됩니다
	 -->
	 성명 : <%=name %><br />
	 주민번호 : <%=jumin_1 %>-<%=jumin_2 %><br />
	 아이디 : <%=id %><br />
	 비밀번호 : <%=pwd %><br />
	 비밀번호 확인 : <%=pwd_re %><br />
</body>
</html>