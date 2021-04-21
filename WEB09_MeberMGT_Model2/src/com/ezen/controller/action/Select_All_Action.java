package com.ezen.controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezen.dao.MemberDao;
import com.ezen.dto.MemberDto;

public class Select_All_Action implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "";
		MemberDao mdao = MemberDao.getInstance();
		ArrayList <MemberDto> list = mdao.selectAll();
		HttpSession session = request.getSession();
		if(session.getAttribute("loginUser") == null) {
			url = "member/login.jsp";
		}else {
			request.setAttribute("memberlist", list);
			url = "member/memberSelect.jsp";
		}
		RequestDispatcher dp = request.getRequestDispatcher(url);
		dp.forward(request, response);

	}

}
