package com.ezenac.admin.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezenac.controller.action.Action;
import com.ezenac.dao.ProductDao;
import com.ezenac.dto.AdminVO;
import com.ezenac.dto.ProductVO;

public class AdminProductDetailAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "admin/product/productDetail.jsp";
		HttpSession session = request.getSession();
		AdminVO avo = (AdminVO)session.getAttribute("loginAdmin");
		if( avo == null) { 
			url = "shop.do?command=admin"; 
		} else {
			String pseq = request.getParameter("pseq");
			ProductDao pdao = ProductDao.getInstance();
			ProductVO pvo = pdao.getProduct(pseq);  // 이미 만들어져 있는 메서드 사용
			
			// 카테고리 별 타이틀을 배열에 저장
			String kindList[] = { "0", "Heels", "Boots", "Sandals", "Snicakers", "Slipers","Sale" };
			// 검색해온 상품에서 카테고리 추출
			int index = Integer.parseInt(pvo.getKind()); // 상품의 카테고리(kind)값을 정수로 변환
			request.setAttribute("productVO", pvo);
			// 추출한 카테고리 번호로 배열에서 해당 타이틀 지목 추출
			request.setAttribute("kind", kindList[index]); // 정수로 변화된 kind 값으로 배열에서 선택저장
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

}
