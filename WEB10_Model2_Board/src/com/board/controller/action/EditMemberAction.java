package com.board.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.board.dao.MemberDao;
import com.board.dto.MemberDto;

public class EditMemberAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		MemberDto mdto = new MemberDto();
		mdto.setName(request.getParameter("name"));
		mdto.setUserid(request.getParameter("userid"));
		mdto.setPwd(request.getParameter("pwd"));
		mdto.setEmail(request.getParameter("email"));
		mdto.setPhone(request.getParameter("phone"));
		mdto.setAdmin(Integer.parseInt(request.getParameter("admin")));
		
		MemberDao mdao = MemberDao.getInstance();
		mdao.editMember(mdto);
		
		HttpSession session = request.getSession();
		session.setAttribute("loginUser", mdto);
		
		//RequestDispatcher dp = request.getRequestDispatcher("board.do?command=boardlist");
		//dp.forward(request, response);
		
		//Action ac = new BoardListAction();
		//ac.execute(request, response);
		
		new BoardListAction().execute(request, response);
	}

}
