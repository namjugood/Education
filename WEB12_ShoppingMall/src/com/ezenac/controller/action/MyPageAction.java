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

public class MyPageAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "mypage/mypage.jsp";
		HttpSession session = request.getSession();
	    MemberVO mvo = (MemberVO) session.getAttribute("loginUser");
	    if(mvo==null) {
	    	url = "shop.do?command=login_form";
	    }else {
	    	// 지금 나타내려고 하는 목록은  하나의 주문안에 있는 상품 리스트가 아니라
	    	// 그 상품들을 품고 있는 주문들의 리스트입니다.
	    	// 주문들의 리스트들중 아직 배송 처리 또는 발송처리가 안된 주문들의 리스트
	    	// 주문한 상품들 -> 리스트:주문1건  -> 주문들의 리스트
	    	// 따라서 화면에 표시될때는 주문에 들어 있는 대표상품 이름을 이용해서  
	    	// XXXX 포함 2건 과 같이 표시할 예정입니다.
	    	// 다만 주문한 상품들은 orderDetail 에서 표시하고 현재는 표히하지 않을 예정이니
	    	// 주문을 대표하는 (상품명 : XXXX 포함 2건) 주문의 리트스가 만들어 질 예정입니다
	    	OrderDao odao = OrderDao.getInstance();
	    	//  최종 주문의 목록이 담길 리스트 생성(OrderVO.name : XXXX 포함 2건)
	    	ArrayList<OrderVO> orderList = new ArrayList<OrderVO>();
	    	
	    	// order_view 를 통해서 현재 진행중인 주문(처리전)의 주문 번호(oseq)들을 리스트로 리턴받음
	    	ArrayList<Integer> oseqList	= odao.selectSeqOrderIng(mvo.getId());
	    	
	    	for( int oseq : oseqList ) {  // 리스트에 담긴 주문 번호들을 하나씩 꺼내서 반복실행
	    		// 현재 oseq 값과 로그인 아이디를 이요해서 주문 리스트를 검색(대상:order_view)
	    		// (주문번호 한건의 주문상세내역들(oder_view 의 상품들))
	    		ArrayList<OrderVO> orderListIng = odao.listOrderById(mvo.getId(), oseq);
	    		
	    		// 위명령에서 검색한 리스트 중 첫번째를 따로 보관 (주문 대표상품 이름 사용을 위해)
	    		OrderVO ovo = orderListIng.get(0);
	    		
	    		// 따로 보관한 첫번째 주문의 상품명을   "ovo상품명 포함 x 건" 으로 수정
	    		ovo.setPname(ovo.getPname() + " 포함 " + orderListIng.size() + " 건");
	    		//ovo.setPname(ovo.getPname() + " 외 " + (orderListIng.size()-1) + " 건");
	    		
	    		// orderListIng 내의 주문상품들의 가격을 이용하여 총 결제금액을 계산하고
	    		int totalPrice = 0;
	            for (OrderVO ovo1 : orderListIng)  
	            	totalPrice += ovo1.getPrice2() * ovo1.getQuantity();
	            // 위의 대표상품 ovo 의 금액으로 저장
	            ovo.setPrice2(totalPrice);
	            
	            //	완성된 주문 내역1건을  상단에서 생성한 orderList 에 추가
	            orderList.add(ovo);
	    	}
	    	request.setAttribute("title", "진행 중인 주문 내역");
	        request.setAttribute("orderList", orderList);	    	
	    }
	    request.getRequestDispatcher(url).forward(request, response);
		
	}
	
}
