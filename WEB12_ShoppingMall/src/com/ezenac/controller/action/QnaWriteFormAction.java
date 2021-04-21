package com.ezenac.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezenac.dto.MemberVO;

public class QnaWriteFormAction implements Action
{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "qna/qnaWrite.jsp";
		HttpSession session = request.getSession();
	    MemberVO mvo= (MemberVO) session.getAttribute("loginUser"); 
	    if (mvo == null)
	    	url = "shop.do?command=loginFform";	    
	    request.getRequestDispatcher(url).forward(request, response);	
	}
}
