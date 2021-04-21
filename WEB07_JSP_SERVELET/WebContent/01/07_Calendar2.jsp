<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="java.util.Calendar" %> <!-- JSP의 import 명령 -->
<html>
<style type="text/css">
	table>tbody>tr>td{font-size:110%; font-weight:bold;}
	table>tbody>tr>td:first-child{color:red;}
	table>tbody>tr>td:last-child{color:blue;}
</style>
<head>
<%!
/* 이 영역은 변수나 객체를 선언하는 용도로 많이 사용합니다. JSP 영역표기와 !를 함께 사용하여
선언부로 사용합니다 */
Calendar sDay = Calendar.getInstance();	// Calendar 객체로 오늘 날짜의 객체를 얻습니다.
int sYear=sDay.get(Calendar.YEAR);			// 년도 추출
int sMonth=sDay.get(Calendar.MONTH) +1;		// 월 추출
int pYear = 0;
int pMonth = 0;
int nYear = 0;
int nMonth = 0;
%>
<meta charset="UTF-8">
<title>07_Calendar</title>
</head>
<body>
<%	/* 이전달 다음달 출력 내용 */
	if(request.getParameter("sYear")!=null){
		sYear=Integer.parseInt(request.getParameter("sYear"));
		sMonth=Integer.parseInt(request.getParameter("sMonth"));
	}
	/* 전달된 파라미터 값이 null이 아니면, 전달된 값으로 년과 월을 바꿉니다.
	null이라면 위에서 계산한 오늘 날짜의 년, 월로 설정합니다*/
	if(sMonth-1==0){	// 현재 월이 1월인 경우의 이전달
		pYear=sYear-1;	// 전년도
		pMonth=12;		// 12월
	}else{
		pYear=sYear;		// 현재 월이 1월이 아닌 경우의 이전달
		pMonth=sMonth-1;	// 이전달로 이동
	}
	if(sMonth+1==13){	// 현재 월이 12월인 경우의 다음달
		nYear=sYear+1;	// 다음년도
		nMonth=1;			// 1월
	}else{
		nYear=sYear;		// 현재 월이 12월이 아닌 경우의 다음달
		nMonth=sMonth+1;	// 다음달(+1)
	}
%>
<h1><%=sYear %> 년  <%=sMonth %> 월</h1>
<table width="560" align="left" cellspacing="1" bgcolor="black">
	<tr bgcolor="white" height="50">
		<td align="center" width="80">일</td>
		<td align="center" width="80">월</td>
		<td align="center" width="80">화</td>
		<td align="center" width="80">수</td>
		<td align="center" width="80">목</td>
		<td align="center" width="80">금</td>
		<td align="center" width="80">토</td>
	</tr>
	<tr bgcolor="white" height="60" align="right">
	<%
		int k=1;		
		for(int i=0; i<=5; i++){ %>
		<tr bgcolor="white" height="60" align="right">
			<%for(int j=0; j<=6; j++){ %>
					<td><%=k++ %></td>
			<%} %>
		</tr>	
	<%} %>
	<tr bgcolor="white" height="50">
		<td colspan="7">
			<input type="button" value="이전 달" onClick="location.href=
			'07_Calendar2.jsp?sYear=<%=pYear %>&sMonth=<%=pMonth %>' ">
			<input type="button" value="다음 달" onClick="location.href=
			'07_Calendar2.jsp?sYear=<%=nYear %>&sMonth=<%=nMonth %>' ">
		</td>
	</tr>
</table>

</body>
</html>