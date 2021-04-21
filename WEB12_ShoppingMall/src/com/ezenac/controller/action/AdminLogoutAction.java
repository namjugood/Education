package com.ezenac.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezenac.dto.AdminVO;

public class AdminLogoutAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url="shop.do?command=admin";
		HttpSession session = request.getSession();
		// session.invalidate(); 모든 로그인 세션 제거
		AdminVO avo = (AdminVO)session.getAttribute("loginAdmin");
		session.removeAttribute("loginAdmin");
		request.getRequestDispatcher(url).forward(request, response);
	}

}
