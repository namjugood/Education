package com.board.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.dao.BoardDao;
import com.board.dto.ReplyDto;

public class AddReplyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ReplyDto rdto = new ReplyDto();
		int boardnum=Integer.parseInt(request.getParameter("boardnum"));
		
		rdto.setUserid(request.getParameter("userid"));
		rdto.setContent(request.getParameter("reply"));
		rdto.setBoardnum(boardnum);
		
		BoardDao bdao = BoardDao.getInstance();
		bdao.insertReply(rdto);
		
		response.sendRedirect("board.do?command=boardview&num=" + boardnum);

	}

}
