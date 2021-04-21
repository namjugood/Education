package ezen.useServlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Joindo
 */
@WebServlet("/Joindo")
public class Joindo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Joindo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String id=request.getParameter("id");
		String pwd=request.getParameter("pwd");
		String gender=request.getParameter("gender");
		String chk_mail=request.getParameter("chk_mail");
		String content=request.getParameter("content");
		String items[]=request.getParameterValues("item");
		String job=request.getParameter("job");
		String interests[]=request.getParameterValues("interest");
		
		request.setAttribute("id", id);
		request.setAttribute("pwd", pwd);
		
		if(Integer.parseInt(gender)==1)	request.setAttribute("gender", "남자");
		else request.setAttribute("gender", "여자");
		if(Integer.parseInt(chk_mail)==1)	request.setAttribute("chk_mail", "수신");
		else request.setAttribute("chk_mail", "거부");
		
		request.setAttribute("content", content);
		
		String [] goods = new String[items.length];
		// 전달된 item의 갯수만큼의 크기를 갖는 goods 배열 생성
		
		for(int i=0; i<items.length; i++) {
			if(items[i].equals("A")) goods[i] = "신발";
			else if(items[i].equals("B")) goods[i] = "가방";
			else if(items[i].equals("C")) goods[i] = "벨트";
			else if(items[i].equals("D")) goods[i] = "모자";
			else if(items[i].equals("E")) goods[i] = "시계";
			else goods[i] = "쥬얼리";
		}
		request.setAttribute("goods", goods);
		
		if(job.equals("I")) request.setAttribute("job", "학생");
		else if(job.equals("II")) request.setAttribute("job", "컴퓨터/인터넷");
		if(job.equals("III")) request.setAttribute("job", "언론");
		if(job.equals("IV")) request.setAttribute("job", "공무원");
		if(job.equals("V")) request.setAttribute("job", "군인");
		if(job.equals("VI")) request.setAttribute("job", "서비스업");
		else request.setAttribute("job", "교육");
		
		String [] inter = new String[interests.length];
		for(int i=0;i<interests.length;i++) {
			if(interests[i].equals("가")) inter[i] = "에스프레소";
			else if(interests[i].equals("나")) inter[i] = "로스팅";
			else if(interests[i].equals("다")) inter[i] = "생두";
			else if(interests[i].equals("라")) inter[i] = "원두";
			else inter[i] = "핸드드립";
		}
		request.setAttribute("interests", inter);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("02/11_Servlet_Ex_ok.jsp");
		dispatcher.forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
