package com.ezen.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezen.dao.MemberDao;

public class Delete_Action implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("userid");
		MemberDao mdao = MemberDao.getInstance();
		int result = mdao.deleteMember(userid);
		if(result==1) {
			request.setAttribute("message", "회원탈퇴가 완료되었습니다");	
		}else {
			request.setAttribute("message", "회원탈퇴 중 오류가 발생했습니다. 관리자에 문의해 주세요");
		}
		RequestDispatcher dp = request.getRequestDispatcher("member/loginForm.jsp");
		dp.forward(request, response);
	}

}
