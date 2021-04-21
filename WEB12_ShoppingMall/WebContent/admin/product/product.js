function go_total(){
	document.frm.key.value="";
	document.frm.action = "shop.do?command=adminProductList&page=1";
	document.frm.submit();
}

function go_search(){
	if( document.frm.key.value=="") return;
	document.frm.action = "shop.do?command=adminProductList&page=1";
	document.frm.submit();
}


function go_list(){
	location.href = "shop.do?command=adminQnaList";
}

function go_view(qseq){
	var theForm = document.frm;
    theForm.action = "shop.do?command=adminQnaDetail&qseq=" + qseq;
    theForm.submit();
}


function go_order_save(){
	var count = 0;
	/* 체크된 체크 박스 갯수를 카운트 하는 코드 - 참고:command=cartDelete*/
	if (document.frm.result.length == undefined) {
	     	if (document.frm.result.checked == true)  	count++;
	} else {
	  	for ( var i = 0; i < document.frm.result.length; i++) {
	    	if (document.frm.result[i].checked == true) count++;
	    }
	}    
	if (count == 0) {
		        alert("주문처리할 항목을 선택해 주세요.");
	} else {
		        document.frm.action = "shop.do?command=adminOrderSave";
		        document.frm.submit();
	}
}

function go_mod_save(){
	if (document.frm.kind.value == '') {
		  alert('상품분류를 선택하세요'); 	  document.frm.kind.focus();
	 } else if (document.frm.name.value == '') {
		  alert('상품명을 입력하세요');	  document.frm.name.focus();
	 } else if (document.frm.price1.value == '') {
		  alert('원가를 입력하세요');	  document.frm.price1.focus();
	 } else if (document.frm.price2.value == '') {
		  alert('판매가를 입력하세요');	  document.frm.price2.focus();
	 } else if (document.frm.content.value == '') {
		  alert('상품상세를 입력하세요');	  document.frm.content.focus();
	 }else{
		if( confirm('수정하시겠습니까?') ){
			 document.frm.action = "shop.do?command=adminProductUpdate";
			 document.frm.submit();
		}
	}
}


function go_mod(pseq){
	document.frm.action = "shop.do?command=adminProductUpdateForm&pseq=" + pseq;
	document.frm.submit();
}



function go_mov(){
	location.href = "shop.do?command=adminProductList";
}

function go_save(){
	var theForm = document.frm;
	if( theForm.kind.value==""){
		alert('상품분류를 선택하세요');		theForm.kind.focus();
	}  else if (theForm.name.value == '') {
		alert('상품명을 입력하세요.'); 	theForm.name.focus();	
	} else if (theForm.price1.value == '') {
		alert('원가를 입력하세요.'); 		theForm.price1.focus();
	} else if (theForm.price2.value == '') {
		alert('판매가를 입력하세요.'); 		theForm.price2.focus();
	} else if (theForm.content.value == '') {
		alert('상품상세를 입력하세요.'); 		theForm.content.focus();
	} else if (theForm.image.value == '') {
		alert('상품이미지들 입력하세요.'); 	theForm.image.focus();	
	} else{
		theForm.action = "shop.do?command=adminProductWrite";
		theForm.submit();
	}
}
function go_wrt(){
	document.frm.action = "shop.do?command=adminProductWriteForm";
	document.frm.submit();
}

function go_detail(pseq){
	document.frm.action = "shop.do?command=adminProductDetail&pseq=" + pseq;
	document.frm.submit();
}