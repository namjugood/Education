package com.ezenac.admin.controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezenac.controller.action.Action;
import com.ezenac.dao.AdminDao;
import com.ezenac.dto.AdminVO;
import com.ezenac.dto.ProductVO;
import com.ezenac.util.Paging;

public class AdminProductListAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "admin/product/productList.jsp";
		HttpSession session = request.getSession();
		AdminVO id = (AdminVO)session.getAttribute("loginAdmin");
		int page = 1;
		if( id == null) { 
			url = "shop.do?command=admin"; 
		}else {
			
			if( request.getParameter("first")!=null ) {
				session.removeAttribute("page");
				session.removeAttribute("key");
			}
			
			String key = "";
			
			if( request.getParameter("key") != null ) {
				key = request.getParameter("key");
				session.setAttribute("key", key);
			} else if( session.getAttribute("key")!= null ) {
				key = (String)session.getAttribute("key");
			} else {
				session.removeAttribute("key");
				key = "";
			} 

			// 최초 페이지는 1 이지만, 나중에 파라미터로 전달된 페이지가 있다면 그 페이지가 현재페이가 되도록 설정
			if( request.getParameter("page") != null ) {
				page = Integer.parseInt(request.getParameter("page"));
				session.setAttribute("page", page);
			} else if( session.getAttribute("page")!= null ) {
				page = (int) session.getAttribute("page");
			} else {
				page = 1;
				session.removeAttribute("page");
			}
			
			AdminDao adao = AdminDao.getInstance();
			Paging paging = new Paging(); // 페이지 객체 생성
			paging.setPage(page); // 설정된 페이지를 멤버변수(page)에 저장
			int count = adao.getAllCount("product", "name", key); // 전체 레코드수를 조회하여 count 에 저장
			paging.setTotalCount(count); // count 값을 totalCount 에 저장하고 paging() 호출
			System.out.println(count);
			// 설정된 페이지에 맞게 게시물을 조회하여 리스트로 리턴 받습니다
			ArrayList<ProductVO> productList = adao.listProduct(paging, key);
			request.setAttribute("paging", paging);  // 최종 설정된 paging 을 전달
			
			request.setAttribute("productList",productList);
			request.setAttribute("key", key);
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
}
