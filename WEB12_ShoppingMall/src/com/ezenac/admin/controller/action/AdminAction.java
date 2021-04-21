package com.ezenac.admin.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezenac.controller.action.Action;

public class AdminAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "admin/adminLogin.jsp";  
	    RequestDispatcher rd=request.getRequestDispatcher(url);
	    rd.forward(request, response);
	}
}
