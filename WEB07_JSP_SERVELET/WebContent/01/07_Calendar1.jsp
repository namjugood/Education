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
	<%
	int k=1;
	for(int i=0; i<=4; i++){ %>
	<tr bgcolor="white" height="60" align="right">
		<%for(int j=0; j<=6; j++){ %>
			<td>&nbsp;</td>
		<%} %>
	</tr>	
<%} %>
	<tr bgcolor="white" height="50">
		<td colspan="7">
			<input type="button" value="이전 달" onClick="location.href=
			'07_Calendar1.jsp?sYear=<%=pYear %>&sMonth=<%=pMonth %>' ">
			<input type="button" value="다음 달" onClick="location.href=
			'07_Calendar1.jsp?sYear=<%=nYear %>&sMonth=<%=nMonth %>' ">
		</td>
	</tr>
</table>

</body>
</html>