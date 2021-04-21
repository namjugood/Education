package com.ezenac.controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezenac.dao.ProductDao;
import com.ezenac.dto.ProductVO;

public class IndexAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "index.jsp";
		
		// ProductDao 인스턴스 생성
		ProductDao pdao = ProductDao.getInstance();
		
		// best, new 상품 조회 및 리턴
		ArrayList<ProductVO> newlist = pdao.getNewList();
		ArrayList<ProductVO> bestlist = pdao.getBestList();
		
		// 리퀘스트에 저장
		request.setAttribute("newProductList", newlist);
		request.setAttribute("bestProductList", bestlist);
		
		// 포워딩
		RequestDispatcher dispatcher= request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
}
