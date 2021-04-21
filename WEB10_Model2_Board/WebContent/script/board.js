function boardCheck(){
	if(document.frm.pass.value==""){
		alert("암호는 반드시 입력하여야 합니다.");
		frm.pass.focus();
		return false;
	}
	if(document.frm.title.value==""){
		alert("제목을 입력하세요.");
		frm.title.focus();
		return false;
	}	
	if(document.frm.content.value==""){
		alert("내용을 입력하세요.");
		frm.pass.focus();
		return false;
	}
	if(document.frm.email.value==""){
		alert("이메일을 입력하세요");
		frm.email.focus();
		return false;
	}
	return true;
}	

function idCheck(){
	if(document.frm.userid.value.length<4){
		alert("아이디를 입력하세요, 최소 4글자 입력");
		frm.userid.focus();
		return false;
	}
	var inputid = document.frm.userid.value 
	var opt = "toolbar = no, menubar=no, scrollbars=no, resizable=no, width=500, height=200";
	window.open("board.do?command=idcheck&userid=" + inputid, "idcheck", opt);
}
	
	
function idok(userid){
	opener.frm.userid.value=document.frm.userid.value;
	opener.frm.re_id.value=document.frm.userid.value;
	
	self.close();
}	


function joinCheck(userid){
	if(document.frm.userid.value.length<4){
		alert("아이디를 입력하세요, 최소 4글자 입력");
		frm.userid.focus();
		return false;
	}
	if(document.frm.pwd.value==""){
		alert("암호는 반드시 입력하여야 합니다.");
		frm.pwd.focus();
		return false;
	}
	if(document.frm.pwd.value != document.frm.pwd_check.value){
		alert("암호가 일치하지 않습니다.");
		frm.pwd_check.focus();
		return false;
	}	
	if(document.frm.name.value.length==0){
		alert("이름을 입력하세요.");
		frm.name.focus();
		return false;
	}
	if(document.frm.re_id.value.length==""){
		alert("아이디 중복체크를 하지 않았습니다.");
		frm.userid.focus();
		return false;
	}
	return true;
}

function editCheck(){
	if(document.frm.pwd.value==""){
		alert("비밀번호를 입력하세요");
		frm.pwd.focus();
		return false;
	}
	if(document.frm.pwd.value != document.frm.pwdcheck.value){
		alert("비밀번호가 일치하지 않습니다.");
		frm.pwdcheck.focus();
		return false;
	}
	if(document.frm.name.value.length==0){
		alert("이름을 입력하세요");
		frm.name.focus();
		return false;
	}
	return true;	
}


function open_win(url, name){
	var opt = "toolbar=no, menubar=no, resizable=no, width=500, height=230";
	window.open(url, name, opt);
}

function reply_check(){
	if(document.frm2.reply.value==""){
		alert("댓글을 입력하세요");
		document.frm2.reply.focus();
		return false;
	}
	return true;	
}