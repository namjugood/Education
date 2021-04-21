package com.board.controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.dao.BoardDao;
import com.board.dto.BoardDto;
import com.board.dto.ReplyDto;

public class BoardViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "board/boardView.jsp";
		String num = request.getParameter("num");
		
		BoardDao bdao = BoardDao.getInstance();
		bdao.updateReadCount(num);	// 조회수 카운트 올리는 메서드
		BoardDto bdto = bdao.getBoardOne(num);	// 게시물 조회 및 리턴 메서드	
		
		// 현재 게시물에 작성된 댓글들을 검색하여 리스트로 리턴받습니다
		ArrayList<ReplyDto> replyList = bdao.selectAllReply(num);
		
		request.setAttribute("board", bdto);
		// 댓글 리스트를 리퀘스트에 저장하여 전달합니다
		request.setAttribute("replyList", replyList);
		
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
