package com.board.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.dao.BoardDao;

public class DeleteReplyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String num = request.getParameter("num");
		String boardnum = request.getParameter("boardnum");
		BoardDao bdao = BoardDao.getInstance();
		bdao.deleteReply(num);
		
		response.sendRedirect("board.do?command=boardview&num="+ boardnum);
	}

}
