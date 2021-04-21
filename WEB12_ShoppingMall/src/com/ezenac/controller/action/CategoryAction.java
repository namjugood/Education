package com.ezenac.controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezenac.dao.ProductDao;
import com.ezenac.dto.ProductVO;

public class CategoryAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "product/productkind.jsp";
		String kind = request.getParameter("kind");
		ProductDao pdao = ProductDao.getInstance();
		ArrayList<ProductVO> productKindList = pdao.listKindProduct(kind);
		request.setAttribute("productKindList", productKindList);
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
