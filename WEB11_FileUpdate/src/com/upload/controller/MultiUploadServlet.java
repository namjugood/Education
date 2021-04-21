package com.upload.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class MultiUploadServlet
 */
@WebServlet("/upload2.do")
public class MultiUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MultiUploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String savePath = "fileUpload";
		ServletContext context = getServletContext();
		String uploadFilePath = context.getRealPath(savePath);
		try {	
			MultipartRequest multi = new MultipartRequest(
					request,
					uploadFilePath,
					5*1024*1024,
					"UTF-8",
					new DefaultFileRenamePolicy()
					);
			// 이전 예제에서 getFileSystemName() 메서드로 파일 이름을 얻어내고 그 외의 항목도
			// getParameter()를 사용했으나, 현재는 여러 파일이 동시에 업로드 되었기 때문에
			// Enumeration을 이용하며, 업로드 된 내용을 하나씩 엑세스하고 엑세스 한 객체에
			// getFileSystemName()과 getParameter()를 사용합니다.
			
			Enumeration files = multi.getFileNames();
			// multi.getFileNames() : 전송된 파일 이름을 파일단위로 저장한 파일이름객체 리스트
			while(files.hasMoreElements()) {	// 파일 요소의 갯수만큼 반복실행
				String file = (String) files.nextElement();	// 다음 파일 추출
				String file_name = multi.getFilesystemName(file);	// 파일이름 추출
				String ori_file_name = multi.getOriginalFileName(file);	// 실제파일 이름 추출
				// ori_file_name은 DefaultFileRenamePolicy()에 의해 업로드 파일명과 달라질 수 있습니다.
				out.print("<br> 업로드 된 파일명 : " + file_name);
				out.print("<br> 원본 파일명 : " + ori_file_name);
				out.print("<hr>");
			}
		}catch(Exception e) {
			System.out.println("파일 업로드 실패" + e);
		}
	}

}
