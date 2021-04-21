package com.ezenac.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezenac.dto.MemberVO;

public class FindPwStep3Action implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
String url = "member/resetPw.jsp";
		
		MemberVO mvo = new MemberVO();
		String confirmNum = request.getParameter("confirmNum");
		mvo.setId( request.getParameter("id") );
		mvo.setName( request.getParameter("name") );
		mvo.setPhone( request.getParameter("phone") );
		if(!confirmNum.equals("0000"))
			url = "member/findPwconfirmNumber.jsp";
		
		request.setAttribute("member",mvo);
		request.getRequestDispatcher(url).forward(request, response);		
		
	}

}
