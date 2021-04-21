package com.ezen.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezen.controller.action.Action;
import com.ezen.controller.action.Login_Form_Action;

/**
 * Servlet implementation class MemberServlet
 */
@WebServlet("/member.do")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command = request.getParameter("command");
		// 1. 현재 프로젝트에 Action이라는 인터페이스를 제작합니다(추상메서드 execute)	-	제작완료(Action.java)
		// 2. 더불어 각 command에 따른 명령을 담은 java class를 제작합니다
		// 3. 각 클래스는 모두 Action인터페이스를 상속(구현)하여 제작합니다
		//	   이는 Action인터페이스에 execute라는 추상메서드가 있고, 제작되는 각 클래스는 그 기능을 오버라이드하는
		// 	   execute메서드에 모든 실행명령을 기술합니다. - 제작완료(Login_Form_Action -> interface파일)
		
		// 4. 현재의 doGet에서는 전달된 command의 value에 따라 해당 기능을 갖고 있는
		//	   클래스를 Action 인터페이스의 게러펀스 변수에 할당합니다.(부모 레퍼런스 <- 자식 인스턴스)
		
		// 클래스 객체를 직접 생성, 클래스 레퍼런스 <- 클래스 인스턴스, 클래스레퍼런스로 execute 실행
		// Login_Form_Action lfa = new Login_Form_Action();
		// lfa.execute(request, response);		=> 인터페이스가 생성될때마다 반복적으로 작성해줘야 하는 번거로움이 있음
		
		// 인터페이스 레퍼런스에 클래스 인스턴스를 넣는 경우(클래스 레퍼런스 생성 후 할당)
		// 인터페이스 레퍼런스로 execute 실행
		// Action ac=null;
		// Login_Form_Action lfa = new Login_Form_Action();
		// ac=lfa;
		
		// 클래스 레퍼런스 생성없이 직접 인터페이스에 뉴 인스턴스 할당
		// ac = new Login_Form_Action();
		// ac.execute(request, response);
		
		// command에 따라 클래스를 선별하여 할당
		//	if(command.equals("login_form")) ac = new Login_Form_Action();
		//	else if(command.equals("join_form")) ac = new Login_Form_Action();
		// ac.execute(request, response);
		
		// 클래스의 인스턴스를 인터페이스 레퍼런스에 할당하는 작업을 별도로 클래스의 메서드로 대체
		ActionFactory af = ActionFactory.getInstance();
		Action ac = af.getAction(command);
		// 5. 그리고 Action에서 오버라이드 된 execute 메서드를 실행합니다.
		ac.execute(request, response);
		// 6. 각 클래스와 인터페이스의 할당(조립)은 별도의 클래스(ActionFactory)에 기능을 일임합니다.
		
		// ※ 모든 명령(command)의 입력 창구 : member.do
		// ※ 모든 명령(command)의 실행 주체 : Action
		// ※ 모든 명령(command)의 실제 명령문들 : 각 기능별 클래스의 execute 메서드
		// 인터페이스는 하나, 기능별 클래스는 여러개
		// 해당 명령에 따른 클래스의 인스턴스를 인터페이스에 할당하고 execute 명령을 실행합니다. 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}

}
