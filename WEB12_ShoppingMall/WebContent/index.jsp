<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>

<!--메인 이미지 시작 --->
<div id="main_img">
   	<img src="images/main_img.jpg"  style="border-radius:20px 20px 20px 20px;
   					border:2px solid white;">    
</div>
<!--메인 이미지 끝 --->


<!-- 신 상품 -->
<h2> New Item</h2>   
<div id="bestProduct">
 	<!-- 조회된 상품의 갯수만큼(최대4개) 상품의 이미지, 이름, 가격이 나열됩니다. -->
	<c:forEach items="${newProductList}"  var="productVo">
		<div id="item">
			<a href="shop.do?command=productDetail&pseq=${productVo.pseq}">
	    		<img src="product_images/${productVo.image}" />
	    		<h3> ${productVo.name} - 
	   			<fmt:formatNumber value="${productVo.price2}" type="currency"></fmt:formatNumber>
	   			</h3>
	   		</a>   
	   	</div>
	</c:forEach>
</div>

<div class="clear"></div>
<!-- 베스트 상품 -->
<h2> Best Item</h2>     
<div id="bestProduct">         
    <c:forEach items="${bestProductList}"  var="productVo">
   		<div id="item">
			<a href="shop.do?command=productDetail&pseq=${productVo.pseq}">
   			<img src="product_images/${productVo.image}" />
   			<h3> ${productVo.name} - 
			<fmt:formatNumber value="${productVo.price2}" type="currency"></fmt:formatNumber>
   			</h3></a>  
		</div>
	</c:forEach>      
</div>

<%@ include file="footer.jsp" %>