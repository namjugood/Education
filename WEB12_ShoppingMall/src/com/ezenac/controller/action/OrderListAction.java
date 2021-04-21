package com.ezenac.controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezenac.dao.OrderDao;
import com.ezenac.dto.MemberVO;
import com.ezenac.dto.OrderVO;

public class OrderListAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "mypage/orderList.jsp";
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO) session.getAttribute("loginUser");
		if (mvo == null) {
		      url = "shop.do?command=loginForm";
		}else {
			int oseq = Integer.parseInt(request.getParameter("oseq"));
			OrderDao odao = OrderDao.getInstance();
			ArrayList<OrderVO> list = odao.listOrderById(mvo.getId(), oseq);
			int totalPrice=0;
			for(OrderVO ovo : list) totalPrice+=ovo.getPrice2() * ovo.getQuantity();
	        request.setAttribute("orderList", list);
	        request.setAttribute("totalPrice", totalPrice);
		}
		//RequestDispatcher dispatcher= request.getRequestDispatcher(url);
		//dispatcher.forward(request, response);
		request.getRequestDispatcher(url).forward(request, response);
	}
}
