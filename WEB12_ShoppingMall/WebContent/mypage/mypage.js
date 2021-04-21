function go_order(){
	if (document.formm.quantity.value == "") {
		    alert("수량을 입력하여 주세요.");
		    document.formm.quantity.focus();
	 }else{
		 	document.formm.action ="shop.do?command=directOrderInsert";
		 	document.formm.submit();
	 }
}

function go_order_insert(){
	document.formm.action ="shop.do?command=orderInsert";
	document.formm.submit();
}


function go_cart_delete(){
	// checkbox 든  radio 든 동일한 이름의 여러개로 구성된 입력란들은  같은 이름의 개체의 갯수를 저장하는
	// length 라는 속성이 있습니다
	// 웹페이지에서 같은 이름으로 구성된 체크 박스의 갯수가 1개 라면  체크박스 배열의 length 는 undefined 가 
	// 됩니다. 또한 전달된 체크박스의 갯수가 2개 이상이면 length 는 그 갯수를 숫자로저장
	
	// 아래 코드는 체크박스(name:cseq) 의 갯수가 한개일때와 두개이상일때를 구분하여 각각 체크된 checkbox가
	// 몇개인지 갯수를 세고 value  값을 얻는 코드 입니다.
	
	var count = 0;  //  체크된 체크박스의 갯수를 카운트 하기위한 변수
	
	if(document.formm.cseq.length==undefined){  /* 체크박스 갯수가 하나 라면 */
		if (document.formm.cseq.checked == true) count++;
	}else {   /* 체크박스가 두개 이상이라면 */
		for(var i=0; i<document.formm.cseq.length; i++){
				if(document.formm.cseq[i].checked == true) count++;
		} /* 배열로 각 체크 박스를 확인해서 체크된 체크박스의 갯수를 카운트 합니다 */
	}
	
	/* 체크된 체크 박스가 하나도 없다면 그냥 함수 종료, 하나라도 체크됬다면  cart_delete 로 서브밋 합니다 */
	if (count == 0) {
	    alert("삭제할 항목을 선택해 주세요.");
	}else{
		document.formm.action = "shop.do?command=cartDelete";
	    document.formm.submit();
	}
}



function go_cart() {
	 if (document.formm.quantity.value == "") {
		    alert("수량을 입력하여 주세요.");
		    document.formm.quantity.focus();
	 }else{
		 	document.formm.action ="shop.do?command=cartInsert";
		 	document.formm.submit();
	 }
}