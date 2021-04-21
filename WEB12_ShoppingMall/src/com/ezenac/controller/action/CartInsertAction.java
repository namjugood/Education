package com.ezenac.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezenac.dao.CartDao;
import com.ezenac.dto.CartVO;
import com.ezenac.dto.MemberVO;

public class CartInsertAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 장바구니에 상품을 추가 한후 바로 장바구니 리스트로 이동하여 목록을 확인하게 합니다
		String url = "shop.do?command=cartList";
		
		// 장시간 움직임이 없으면 자동 로그아웃되게 된 설정으로 로그인을 체크합니다
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
		if(mvo == null) {
			url = "shop.do?command=loginForm";
		} else {
			CartVO  cvo = new CartVO();   // 카트 내용 저장 객체 생성
			cvo.setId(mvo.getId());  
			cvo.setPseq(Integer.parseInt(request.getParameter("pseq")));
			cvo.setQuantity(Integer.parseInt(request.getParameter("quantity")));
			CartDao cdao = CartDao.getInstance();  // 카트 dao 인스턴스 생성
			cdao.insertCart(cvo);   // 카트 레코드 삽입 메서드 실행
		}
		// 테이블 레코드 삽입 동작에서는 포워드 대신 샌드리다이렉트를 사용합니다
		response.sendRedirect(url);
	}

}
