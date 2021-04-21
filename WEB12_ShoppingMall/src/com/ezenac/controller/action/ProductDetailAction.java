package com.ezenac.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezenac.dao.ProductDao;
import com.ezenac.dto.ProductVO;

public class ProductDetailAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url="product/productDetail.jsp";
		String pseq = request.getParameter("pseq");
		ProductVO pvo = new ProductVO();
		ProductDao pdao = ProductDao.getInstance();
		pvo = pdao.getProduct(pseq);
		
		request.setAttribute("productVo", pvo);
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
