package com.ezenac.controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezenac.dao.MemberDao;
import com.ezenac.dto.AddressVO;

public class FindZipNumAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url="member/findZipNum.jsp";
		String dong=request.getParameter("dong");
		if(dong != null) {   // 처음 실행(dong 이 null인경우) 에서는 실행하지 않습니다
			if( dong.equals("") == false ) {
				MemberDao mdao = MemberDao.getInstance();
				ArrayList<AddressVO> list = mdao.selectAddressByDong( dong );
				//System.out.println(list.size());
				request.setAttribute("addressList", list);
			}
		}
		RequestDispatcher dispatcher=request.getRequestDispatcher(url);
		dispatcher.forward(request, response); 
	}

}
