package com.ezenac.admin.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezenac.controller.action.Action;
import com.ezenac.dao.AdminDao;
import com.ezenac.dto.AdminVO;
import com.ezenac.dto.QnaVO;

public class AdminQnaRepsaveAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "shop.do?command=adminQnaDetail";
		HttpSession session = request.getSession();
		AdminVO avo = (AdminVO)session.getAttribute("loginAdmin");
		if( avo == null) { 
			url = "shop.do?command=admin"; 
		} else {
			AdminDao adao = AdminDao.getInstance();
			QnaVO qvo = new QnaVO();
			qvo.setQseq(Integer.parseInt(request.getParameter("qseq")));
			qvo.setReply(request.getParameter("reply"));
			adao.updateQna(qvo);
			url = url + "&qseq=" + request.getParameter("qseq");
		}
		response.sendRedirect(url);
	}
}
