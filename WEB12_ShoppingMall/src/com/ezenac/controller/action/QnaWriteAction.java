package com.ezenac.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezenac.dao.QnaDao;
import com.ezenac.dto.MemberVO;
import com.ezenac.dto.QnaVO;

public class QnaWriteAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "shop.do?command=qnaList";
		HttpSession session = request.getSession();
	    MemberVO mvo = (MemberVO) session.getAttribute("loginUser");    
	    if (mvo == null) {
	    	url = "shop.do?command=loginForm";
	    }else{
	    	QnaVO qvo = new QnaVO();
	    	qvo.setSubject(request.getParameter("subject"));
	    	qvo.setContent(request.getParameter("content"));
	    	QnaDao qdao = QnaDao.getInstance();
	    	qdao.insertQna(qvo, mvo.getId());
	    }
	    response.sendRedirect(url);
	}
}
