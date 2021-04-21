package com.ezenac.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezenac.dao.MemberDao;
import com.ezenac.dto.MemberVO;

public class LoginAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url="member/login_fail.jsp";
		String id=request.getParameter("id");
	    String pwd=request.getParameter("pwd");
	    MemberDao mdao = MemberDao.getInstance();
	    MemberVO mvo = mdao.getMember(id);
	    HttpSession session=request.getSession();
	    if(mvo != null){
	    	if(mvo.getPwd() != null){
	    		if(mvo.getPwd().equals(pwd)){    
	    			session.setAttribute("loginUser", mvo);
			        url="shop.do?command=index";
	    		}
	    	}
	    }
	    RequestDispatcher dispatcher=request.getRequestDispatcher(url);
		dispatcher.forward(request, response); 	
	}
}
