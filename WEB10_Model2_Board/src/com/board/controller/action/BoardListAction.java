package com.board.controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.dao.BoardDao;
import com.board.dto.BoardDto;
import com.board.util.Paging;

public class BoardListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "main.jsp";
		int page=1;	// 최초 상태 page : 1
		
		// 1 2 3 4 [5] 6 7 8 9 10 [next]
		// parameter "page"로 전달된 값이 있을 때, 그 값으로 page값을 변경합니다
		if(request.getParameter("page") != null)
			page = Integer.parseInt(request.getParameter("page"));
		
		Paging paging = new Paging();	// 페이징 객체 생성
		paging.setPage(page);		//현재 보고자 하는 페이지 값을 멤버변수에 전달 저장
		
		BoardDao bdao = BoardDao.getInstance();
		
		int count = bdao.getAllCount();	// 게시물의 갯수를 리턴해주는 메서드 호출
		System.out.println("Count : "+count);
		paging.setTotalCount(count);		// 리턴받은 총 게시물 갯수를 setTotalCount 메서드에 전달 저장
		// paging 메서드가 같이 실행되면서 각 멤버 변수들의 값들이 계산되고 저장됩니다.
		
		//ArrayList<BoardDto> list = bdao.selectAll(paging.getStartNum(),paging.getEndNum());
		ArrayList<BoardDto> list = bdao.selectAll(paging);
		
		/*
		 ArrayList<Integer> cnt = new ArrayList<Integer>(); for(BoardDto b : list) {
		  
		 } */
				
		request.setAttribute("boardList", list);
		request.setAttribute("paging", paging);	// paging 객체를 리퀘스트에 추가
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);

	}

}
