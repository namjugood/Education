<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
	if(window.name == "update"){
		/* 게시물 수정 폼으로 이동 */
		window.opener.location.href = "board.do?command=boardupdateform&num=${param.num}";
	}else if(window.name =="delete"){
		/* 게시물 삭제 폼으로 이동 */
		window.opener.location.href = "board.do?command=boarddeleteform&num=${param.num}";
	}	
	self.close();
</script>	
</body>
</html>