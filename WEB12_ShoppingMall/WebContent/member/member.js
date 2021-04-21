function move_pw(){
	document.frm.action="shop.do?command=findPwForm";
	document.frm.submit();
}


function move_Id(){
	document.frm.action="shop.do?command=findId";
	document.frm.submit();
}

function find_id(){
	var url = "shop.do?command=findIdPwd";
	var opt = "toolbar=no, menubar=no, scrollbars=no, resizable=no, width=700, ";
	opt = opt + "height=500, top=300, left=300";
	window.open(url, "Find Id/Pw", opt);
}

function go_update(){
	if (document.joinForm.pwd.value == "") {
	    alert("비밀번호를 입력해 주세요.");
	    document.joinForm.pwd.focus();
	}else if ((document.joinForm.pwd.value != document.joinForm.pwdCheck.value)) {
	    alert("비밀번호가 일치하지 않습니다.");
	    document.joinForm.pwd.focus();
	}else if (document.joinForm.name.value == "") {
	    alert("이름을 입력해 주세요.");
	    document.joinForm.name.focus();
	} else if (document.joinForm.email.value == "") {
	    alert("이메일을 입력해 주세요.");
	    document.joinForm.email.focus();
	}else {
	    document.joinForm.action = "shop.do";
	    document.joinForm.submit();
	}
}


function go_save(){
	if (document.joinForm.id.value == "") {
	    alert("아이디를 입력하여 주세요."); 	    
	    document.joinForm.id.focus();
	} else if(document.joinForm.reid.value=""){
		alert("아이디 중복확인을 클릭해주세요");		
		document.joinForm.id.focus();
	} else if (document.joinForm.pwd.value == "") {
	    alert("비밀번호를 입력해 주세요.");	    
	    document.joinForm.pwd.focus();
	}else if ((document.joinForm.pwd.value != document.joinForm.pwdCheck.value)) {
	    alert("비밀번호가 일치하지 않습니다.");	    
	    document.joinForm.pwd.focus();
	}else if (document.joinForm.name.value == "") {
	    alert("이름을 입력해 주세요.");	    
	    document.joinForm.name.focus();
	} else if (document.joinForm.email.value == "") {
	    alert("이메일을 입력해 주세요.");	   
	    document.joinForm.email.focus();
	}else{
		document.joinForm.action = "shop.do";
	    document.joinForm.submit();
	}
}

function post_zip(){
	var url = "shop.do?command=findZipNum";
	var opt = "toolbar=no, menubar=no, scrollbars=no, resizable=no, width=550, ";
	opt = opt + "height=300, top=300, left=300";
	window.open(url, "Find zip num", opt);
}




function idok(){
	opener.joinForm.id.value=document.idCheckFrm.id.value; 
	opener.joinForm.reid.value=document.idCheckFrm.id.value;
	self.close();
}


function idcheck(){
	if(document.joinForm.id.value==""){
		alert('아이디를 입력하여 주세요');
		document.joinForm.id.focus();
		return;
	}
	var url = "shop.do?command=idCheckForm&id=" + document.joinForm.id.value;
    var opt = "toolbar=no, menubar=no, resizable=no, width=500, height=250";
    window.open(url, "IdCheck", opt);
}


function go_next(){
	if( document.contractFrm.okon1[0].checked == true ){
		document.contractFrm.action ="shop.do?command=joinForm";
		document.contractFrm.submit(); 
	}else{
		alert('약관에 동의하셔야 회원 가입이 가능합니다.');
	}
}




function loginCheck(){
	if(document.loginFrm.id.value == ""){
		alert("아이디를 입력하세요");
		document.loginFrm.id.focus();
		return false;
	}
	if(document.loginFrm.pwd.value == ""){
		alert("비밀번호를 입력하세요");
		document.loginFrm.pwd.focus();
		return false;
	}
	return true;
}