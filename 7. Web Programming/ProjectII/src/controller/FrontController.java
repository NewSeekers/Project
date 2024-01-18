package controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FrontController
 */
@WebServlet(description = "get command", urlPatterns = { "*.do" })
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionDo(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionDo(request, response);
	}

	public void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("actionDo run");
		
		
		String viewPage = null;
		String BCommand = null;
		String MCommand = null;
		String CCommand = null;
		String ICommand = null;
		
		
		String uri = request.getRequestURI();
		System.out.println("유알아이"+uri);
		String ctxPath = request.getContextPath();
		System.out.println("컨텍스트패스:"+ctxPath);
		String com = uri.substring(ctxPath.length());
		System.out.println(com);
		String servPath = request.getServletPath();
		System.out.println("서브패스"+servPath);
		String serv = request.getPathInfo();
		System.out.println("패스인포:"+serv);
		
		System.out.println(com.indexOf("/board"));
		if(com.indexOf("/board")!=-1 ) {
			com=com.substring(6);
			System.out.println("이프문 안에서"+com);
			System.out.println("인덱스오브실행");
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html charset=utf-8");
			request.getRequestDispatcher("controller/BoardController.java").forward(request, response);
			
		}
		
	}
	
	
	
}
