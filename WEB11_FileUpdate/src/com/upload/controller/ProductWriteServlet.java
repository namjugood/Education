package com.upload.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.upload.dao.ProductDao;
import com.upload.dto.ProductVO;

/**
 * Servlet implementation class ProductWriteServlet
 */
@WebServlet("/productwrite.do")
public class ProductWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("product/productWrite.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		ServletContext context = getServletContext();
		String path = context.getRealPath("fileUpload");
		MultipartRequest multi = new MultipartRequest(
				request, path, 20*1024*1024, "UTF-8", new DefaultFileRenamePolicy()	);
		
		// 전달된 파라미터들을 MultipartRequest를 이용하여 추출
		String name = multi.getParameter("name");
		int price = Integer.parseInt(multi.getParameter("price"));
		String description = multi.getParameter("description");
		String pictureurl = multi.getFilesystemName("pictureurl");
		
		//productVO에 저장
		ProductVO pvo = new ProductVO();
		pvo.setName(name);
		pvo.setPrice(price);
		pvo.setDescription(description);
		pvo.setPictureurl(pictureurl);
		
		ProductDao pdao = ProductDao.getInstance();
		pdao.insertProduct(pvo);
		response.sendRedirect("productlist.do");
		
		
	}

}
