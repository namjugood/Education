package com.board.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.dao.MemberDao;
import com.board.dto.MemberDto;

public class JoinAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDto mdto = new MemberDto();
		mdto.setUserid(request.getParameter("userid"));
		mdto.setName(request.getParameter("name"));
		mdto.setPwd(request.getParameter("pwd"));
		mdto.setEmail(request.getParameter("email"));
		mdto.setPhone(request.getParameter("phone"));
		mdto.setAdmin(Integer.parseInt(request.getParameter("admin")));
		mdto.setUserid(request.getParameter("userid"));
		
		MemberDao mdao = MemberDao.getInstance();
		mdao.insertMember(mdto);
		response.sendRedirect("board.do?command=loginform");
	}
}
