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

public class OrderAllAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "mypage/mypage.jsp";
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO) session.getAttribute("loginUser");
		if (mvo == null) {
		      url = "shop.do?command=login_form";
		} else {
			OrderDao odao = OrderDao.getInstance();
			ArrayList<OrderVO> orderList = new ArrayList<OrderVO>();
			// 이미 처리된 주문내역까지 조회하는 메서드 호출
			ArrayList<Integer> oseqList= odao.oseqListAll(mvo.getId());	
			for (int oseq : oseqList) {
				 // 처리와 미처리 구분 없이 조회하는 메서드 호출 
				 ArrayList<OrderVO> orderListAll = odao.listOrderById2(mvo.getId(), oseq);
				 OrderVO ovo = orderListAll.get(0);
				 ovo.setPname( ovo.getPname() + "  포함 " + orderListAll.size() + "건");
				 int totalPrice = 0;
				 for (OrderVO ovo1 : orderListAll)  
					 totalPrice += ovo1.getPrice2() * ovo1.getQuantity();
				 ovo.setPrice2(totalPrice);
				 orderList.add(ovo);
			 }			
			request.setAttribute("title", "총 주문 내역");
			request.setAttribute("orderList", orderList);
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

}
