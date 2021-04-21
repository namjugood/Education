package com.ezen.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezen.dao.MemberDao;
import com.ezen.dto.MemberDto;

public class Login_Action implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "member/loginForm.jsp";		// 로그인 실패시 돌아갈 페이지 설정
		// parameter로 아이디 패스워드를 전달받습니다.
		String userid = request.getParameter("userid");
		String pwd = request.getParameter("pwd");
		// MemberDao의 getMember()를 실행하고 그 결과를 MemberDto에 리턴받아 담습니다.
		MemberDao mdao = MemberDao.getInstance();
		MemberDto mdto = mdao.getMember(userid);
		HttpSession session = request.getSession();	
		
		if(mdto==null)
			request.setAttribute("message", "아이디가 존재하지 않습니다");
		else {
			if(mdto.getPwd() != null) {
				if(mdto.getPwd().equals(pwd)) {
					session.setAttribute("loginUser", mdto); 
					url = "main.jsp";
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
