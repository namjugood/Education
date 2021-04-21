<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/admin/header.jsp"%>
<%@ include file="/admin/sub_menu.jsp"%>
<script type="text/javascript">
function go_search_order(){
	if( document.frm.key.value=="") return;
	document.frm.action = "shop.do?command=adminOrderList&page=1";
	document.frm.submit();
}
function go_total_order(){
	document.frm.key.value="";
	document.frm.action = "shop.do?command=adminOrderList&page=1";
	document.frm.submit();
}
</script>
<article>
<h1>주문리스트</h1>
<form name="frm" method="post">
<table style="float: right;">
	<tr><td>주문자 이름 
		<input type="text" name="key" value="${key}" > 
		    <input class="btn" type="button" value="검색" onClick="go_search_order();">
		    <input class="btn" type="button" name="btn_total" value="전체보기 " 
		    onClick="go_total_order();">
	    </td></tr>
</table>
<table id="orderList">
	<tr><th>주문번호(처리여부)</th><th>주문자</th><th>상품명</th><th>수량</th>
	    <th>우편번호</th><th>배송지</th><th>전화</th><th>주문일</th></tr>
	<c:forEach items="${orderList}" var="orderVO">
		<tr><td><c:choose>
		       	<c:when test='${orderVO.result=="1"}'>
		       		<span style="font-weight: bold; color: blue">${orderVO.oseq}</span>
		       		(<input type="checkbox" name="result" value="${orderVO.odseq}"> 미처리)
		       	</c:when>
			    <c:otherwise>
			       	<span style="font-weight: bold; color: red">${orderVO.oseq}</span>
			       	(<input type="checkbox" checked="checked" disabled="disabled">처리완료)
			    </c:otherwise>
		    </c:choose></td>
		    <td>${orderVO.mname}</td><td>${orderVO.pname}</td>
		    <td>${orderVO.quantity}</td><td>${orderVO.zipnum}</td>
		    <td>${orderVO.address}</td> <td>${orderVO.phone}</td>
		    <td><fmt:formatDate value="${orderVO.indate}" /></td>
		</tr>
	</c:forEach>
</table>
<div class="clear"></div>
<input type="button" class="btn" style="width: 200px" value="주문처리(입금확인)" 
																					onClick="go_order_save()">
<br>
<jsp:include page="/admin/paging/paging.jsp">
    <jsp:param value="${paging.page}" name="page"/>
    <jsp:param value="${paging.beginPage}" name="beginPage"/>
    <jsp:param value="${paging.endPage}" name="endPage"/>
    <jsp:param value="${paging.prev}" name="prev"/>
    <jsp:param value="${paging.next}" name="next"/>
    <jsp:param value="adminOrderList" name="command"/>
</jsp:include>
<br>

</form>
</article>
<%@ include file="/admin/footer.jsp"%>