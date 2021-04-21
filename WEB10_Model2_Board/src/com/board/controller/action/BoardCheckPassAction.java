package com.board.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.dao.BoardDao;
import com.board.dto.BoardDto;

public class BoardCheckPassAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = null;
		String num = request.getParameter("num");
		String pass = request.getParameter("pass");
		
		BoardDao bdao = BoardDao.getInstance();
		BoardDto bdto = bdao.getBoardOne(num);
		
		if(bdto.getPass().equals(pass)) url="board/checkSuccess.jsp";
		else {
			url="board/boardCheckPass.jsp";
			request.setAttribute("message", "비밀번호가 틀렸습니다");
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
