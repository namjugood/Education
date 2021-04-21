package com.ezen.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezen.dao.MemberDao;

public class IdCheck_Action implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("userid");
		MemberDao mdao = MemberDao.getInstance();
		int result = mdao.confirmID(userid);	// 아이디 중복 체크 후 결과를 리턴받습니다
		request.setAttribute("userid", userid);// 중복체크한 아이디 저장
		request.setAttribute("result", result);	// 중복체크 결과 저장
		RequestDispatcher dp = request.getRequestDispatcher("member/idcheck.jsp");
		dp.forward(request, response);
		
	}

}
