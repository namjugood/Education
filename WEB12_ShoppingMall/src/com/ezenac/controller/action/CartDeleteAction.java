package com.ezenac.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezenac.dao.CartDao;

public class CartDeleteAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "shop.do?command=cartList";
		String[] cseqArr = request.getParameterValues("cseq");
		CartDao cdao = CartDao.getInstance();
		
		// 배열에 있는 카드 번호들을 cseq 변수에 하나씩 전달하며 cdao.deleteCart(cseq); 를 반복 실행
		for(String cseq : cseqArr) 
			cdao.deleteCart(cseq);
		
		response.sendRedirect(url);
		
	}

}
