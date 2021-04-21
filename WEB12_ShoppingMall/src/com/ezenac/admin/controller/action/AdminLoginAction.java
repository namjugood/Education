package com.ezenac.admin.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezenac.controller.action.Action;
import com.ezenac.dao.AdminDao;
import com.ezenac.dto.AdminVO;

public class AdminLoginAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "shop.do?command=admin";
		String msg = "";  // 로그인 결과를 저장했다가 request 를 통해 전송될 변수
		String workId = request.getParameter("workId");
	    String workPwd = request.getParameter("workPwd");
	    // AdminDao 생성후 인스턴스를 얻습니다
	    AdminDao wdao = AdminDao.getInstance();
	    //AdminDao 안의 workerCheck 메서드 생성후 호출합니다
	    AdminVO avo = wdao.workerCheck(workId);
	    
	    if( avo == null) msg = "없는 아이디 입니다";
	    else 	if( avo.getPwd() == null ) msg = "관리장 정보 오류. 개발자에게 문의하세요";
	    else 	if( !avo.getPwd().equals(workPwd)) msg = "암호가 다릅니다";
	    else {
	    	HttpSession session = request.getSession();
	    	session.setAttribute("loginAdmin", avo);
	    	System.out.println(2);
	    	url = "shop.do?command=adminProductList";
   		}
	    request.setAttribute("message", msg);
	    request.getRequestDispatcher(url).forward(request, response);
	}
}
