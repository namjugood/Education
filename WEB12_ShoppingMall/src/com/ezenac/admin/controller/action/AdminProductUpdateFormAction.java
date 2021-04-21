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

public class AdminProductUpdateFormAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "admin/product/productUpdate.jsp";
		HttpSession session = request.getSession();
		AdminVO avo = (AdminVO)session.getAttribute("loginAdmin");
		if( avo == null) { 
			url = "shop.do?command=admin"; 
		}else {
			// 전달된 상품번호로 상품상세내역을 조회하고 리퀘스트에 저장합니다
			String pseq = request.getParameter("pseq");
			ProductDao pdao = ProductDao.getInstance();
			ProductVO pvo = pdao.getProduct(pseq);
			request.setAttribute("productVO", pvo);
			
			// 현재 상품의 카테고리(kind)값을 상품 종류로 변환하여 리퀘스트에 저장합니다
			String kindList[] = { "Heels", "Boots", "Sandals", "Shcakers", "Slipers", "Sale" };    
			request.setAttribute("kindList", kindList);
			int index = Integer.parseInt(pvo.getKind());
			request.setAttribute("kind", kindList[index]);
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
}
