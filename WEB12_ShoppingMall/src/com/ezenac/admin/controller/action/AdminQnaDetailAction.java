package com.ezenac.admin.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezenac.controller.action.Action;
import com.ezenac.dao.QnaDao;
import com.ezenac.dto.AdminVO;
import com.ezenac.dto.QnaVO;

public class AdminQnaDetailAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "admin/qna/qnaDetail.jsp";
		HttpSession session = request.getSession();
		AdminVO avo = (AdminVO)session.getAttribute("loginAdmin");
		if( avo == null) { 
			url = "shop.do?command=admin"; 
		} else {
			String qseq = request.getParameter("qseq");
			// Qna 하나를 검색하고 리턴 받는 코드
			QnaDao qdao = QnaDao.getInstance();
			QnaVO qvo = qdao.getQna(Integer.parseInt(qseq));
			// 리턴 받은 Qna 를 request 에 저장하는 코드
			request.setAttribute("qnaVO", qvo);
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

}
