package com.ezenac.controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezenac.dao.OrderDao;
import com.ezenac.dto.MemberVO;
import com.ezenac.dto.OrderVO;

public class OrderDetailAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "mypage/orderDetail.jsp";
		HttpSession session = request.getSession();
	    MemberVO mvo = (MemberVO) session.getAttribute("loginUser");
	    if (mvo == null) {
	    	url = "shop.do?command=loginForm";
	    } else {
	    	int oseq=Integer.parseInt(request.getParameter("oseq"));
	    	// 주문번호와 아이디로 검색, 처리유무 상관없음
	    	OrderDao odao = OrderDao.getInstance();
	    	ArrayList<OrderVO> orderList = odao.listOrderById2(mvo.getId(), oseq);
	    	// 총금액 계산    	
	    	int totalPrice=0;
	    	for(OrderVO ovo :orderList) 
	    		totalPrice+=ovo.getPrice2()*ovo.getQuantity();
	    	// 상품의 첫줄 대표 항목표시를 위한 저장
	    	request.setAttribute("orderDetail", orderList.get(0));  
	    	request.setAttribute("orderList", orderList);
	        request.setAttribute("totalPrice", totalPrice);	   
	    }
	    request.getRequestDispatcher(url).forward(request, response);
	}

}
