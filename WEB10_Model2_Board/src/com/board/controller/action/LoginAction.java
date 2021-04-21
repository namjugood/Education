package com.board.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.board.dao.MemberDao;
import com.board.dto.MemberDto;

public class LoginAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("userid");
		String pwd = request.getParameter("pwd");
		String url = "member/loginForm.jsp";	
		
		MemberDao mdao = MemberDao.getInstance();
		MemberDto mdto = mdao.getMember(userid);
		
		HttpSession session = request.getSession();	
		
		if(mdto==null)
			request.setAttribute("message", "아이디가 존재하지 않습니다");
		else {
			if(mdto.getPwd() != null) {
				if(mdto.getPwd().equals(pwd)) {
					session.setAttribute("loginUser", mdto); 
					url = "board.do?command=boardlist";
				}else {
					request.setAttribute("message", "잘못된 비밀번호입니다");
				}
			}else {
				request.setAttribute("message", "회원정보 오류 - 관리자에게 문의하세요");
			}
		}
		RequestDispatcher dp = request.getRequestDispatcher(url);
		dp.forward(request, response);
	}
}
