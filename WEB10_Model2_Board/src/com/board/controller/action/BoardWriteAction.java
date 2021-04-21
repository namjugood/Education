package com.board.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.dao.BoardDao;
import com.board.dto.BoardDto;

public class BoardWriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		BoardDto bdto = new BoardDto();
		bdto.setUserid(request.getParameter("userid"));
		bdto.setPass(request.getParameter("pass"));
		bdto.setEmail(request.getParameter("email"));
		bdto.setTitle(request.getParameter("title"));
		bdto.setContent(request.getParameter("content"));
		BoardDao bdao = BoardDao.getInstance();
		bdao.insertBoard(bdto);
		
		response.sendRedirect("board.do?command=boardlist");
		// .forward(request, response) 된 jsp페이지는 
		// 최종페이지(게시물이 추가된 페이지)에서 새로고침을 눌렀을 때 
		// boardWrite.jsp에서부터 전달된 request 값들로 지금의 execute까지 다시 실행합니다
		// 새로고침 : 최종 페이지를 포워딩 해준 execute부터 다시 새로고침한다는 뜻입니다.
		// 그래서 의도치않게 새로고침으로 방금 추가한 게시물이 하나 더 추가되는 결과가 생기게 됩니다
		// 그래서 포워딩이 아니라, sendRedirect를 실행하여 forward로 연결될 수 있는 실행을 끊어줍니다. 

	}

}
