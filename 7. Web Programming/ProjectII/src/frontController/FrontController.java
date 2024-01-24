package frontController;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.BoardController;

/**
 * Servlet implementation class FrontController
 */
@WebServlet(description = "get command", urlPatterns = { "*.do" })
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionDo(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionDo(request, response);
	}

	public void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("프론트 컨트롤러 actionDo 실행");
		
		
		String viewPage = null;
		String BCommand = null;
		String MCommand = null;
		String CCommand = null;
		String ICommand = null;
		
		
		String uri = request.getRequestURI();
		System.out.println("uri: "+uri);
		String ctxPath = request.getContextPath();
		System.out.println("contextPath: "+ctxPath);
		String com = uri.substring(ctxPath.length());
		System.out.println("com: "+com);
				
		if(com.equals("/list.do")) {
			System.out.println("list.do");
			BoardController boardController = new BoardController(request,response);
//			request.getRequestDispatcher("/board"+com).forward(request, response);
		}else if(com.equals("/callCrime.do")) {
			System.out.println("프론트 callCrime.do 항목 실행");
			request.getRequestDispatcher("/chart"+com).forward(request, response);
			System.out.println("/chart"+com);
		}else if(com.equals("/callArrest.do")){
			System.out.println("프론트 callArrest.do 항목 실행");
			request.getRequestDispatcher("/chart"+com).forward(request, response);
			System.out.println("/chart"+com);
		}else if(com.equals("/callGuGrade.do")) {
			System.out.println("프론트 callGuGrade.do 항목 실행");
			request.getRequestDispatcher("/info"+com).forward(request, response);
		}else if(com.contentEquals("/guPage_chart.do")) {
			System.out.println("프론트 guPage_chart.do 항목 실행");
			request.getRequestDispatcher("/chart"+com).forward(request, response);
		}
	}

}
