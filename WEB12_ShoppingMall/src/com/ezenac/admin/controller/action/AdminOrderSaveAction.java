package com.ezenac.admin.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezenac.controller.action.Action;
import com.ezenac.dao.AdminDao;
import com.ezenac.dto.AdminVO;

public class AdminOrderSaveAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "shop.do?command=adminOrderList";
		HttpSession session = request.getSession();
		AdminVO avo = (AdminVO)session.getAttribute("loginAdmin");
		if( avo == null) { 
			url = "shop.do?command=admin"; 
		} else {
			// 체크된 체크 박스들의 value값들(odseq들)을 배열로 전달 받습니다
			String[] resultArr = request.getParameterValues("result");
			AdminDao adao = AdminDao.getInstance();
			// 배열의 요소들(odseq들)을 하나씩 꺼내어 해당 주문의 처리여부르 처리로 업데이트 합니다
			for( String odseq : resultArr)
				adao.UpdateOrderResult(odseq);
		}
		response.sendRedirect(url);	
	}

}
