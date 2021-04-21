package com.ezenac.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezenac.dao.OrderDao;
import com.ezenac.dto.MemberVO;

public class DirectOrderInsertAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "shop.do?command=order_list";
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO) session.getAttribute("loginUser");
		if (mvo == null) {
		    url = "shop.do?command=login_form";
		}else {
			int pseq = Integer.parseInt(request.getParameter("pseq") );
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			OrderDao odao = OrderDao.getInstance();
			int Oseq = odao.directInsertOrder(pseq, quantity, mvo.getId());
			
			url = "shop.do?command=orderList&oseq="+ Oseq;
		}
		response.sendRedirect(url);
	}

}
