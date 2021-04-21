package com.ezen.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Update_Form_Action implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//String userid = request.getParameter("userid");
		//MemberDao mdao = MemberDao.getInstance();
		//MemberDto mdto = mdao.getMember(userid);
		//request.setAttribute("mdto", mdto);
		
		RequestDispatcher dp = request.getRequestDispatcher("member/updateForm.jsp");
		dp.forward(request, response);

	}

}
