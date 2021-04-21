package com.ezenac.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezenac.dao.MemberDao;
import com.ezenac.dto.MemberVO;

public class ResetPwAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "member/resetPwComplete.jsp";
		MemberVO mvo = new MemberVO();
		mvo.setId( request.getParameter("id"));
		mvo.setPwd( request.getParameter("pw"));
		MemberDao mdao = MemberDao.getInstance();
		mdao.resetPw(mvo);
		request.getRequestDispatcher(url).forward(request, response);
	}
}
