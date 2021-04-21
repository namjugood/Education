package com.ezenac.controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezenac.dao.CartDao;
import com.ezenac.dto.CartVO;
import com.ezenac.dto.MemberVO;

public class CartListAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "mypage/cartList.jsp";
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
		if(mvo == null) {
			url = "shop.do?command=loginForm";
		} else {
			CartDao cdao = CartDao.getInstance();
			//아이디로 검색한 장바구니 물건들을 검색하여 리스트로 받음
			ArrayList<CartVO> list = cdao.listCart(mvo.getId()); 
			int totalPrice = 0;
			// 리스트를 이용하여 현재 장바구니에 담겨 있는 상품들의 총결재금액 계산 
			for(CartVO cvo : list) totalPrice += cvo.getPrice2() * cvo.getQuantity(); 
			request.setAttribute("cartList", list);
			request.setAttribute("totalPrice", totalPrice);
		}
		RequestDispatcher dis = request.getRequestDispatcher(url);
		dis.forward(request, response);
	}

}
