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
		request.setCharacterEncoding("UTF-8");
		
		System.out.println("프론트 컨트롤러 actionDo 실행");
	
		String viewPage = null;
		
		String uri = request.getRequestURI();
		System.out.println("uri: "+uri);
		String ctxPath = request.getContextPath();
		System.out.println("contextPath: "+ctxPath);
		String com = uri.substring(ctxPath.length());
		System.out.println("com: "+com);
				
		if(com.equals("/list.do")) {
			System.out.println("프론트 list.do 항목 실행");
//			BoardController boardController = new BoardController(request,response);
			viewPage="/board"+com;
		}else if(com.equals("/callCrime.do")) {
			System.out.println("프론트 callCrime.do 항목 실행");
			viewPage="/chart"+com;
			System.out.println("/chart"+com);
		}else if(com.equals("/callArrest.do")){
			System.out.println("프론트 callArrest.do 항목 실행");
			viewPage="/chart"+com;
			System.out.println("/chart"+com);
		}else if(com.equals("/callGuGrade.do")) {
			System.out.println("프론트 callGuGrade.do 항목 실행");
			viewPage="/info"+com;
		}else if(com.equals("/secuIndex.do")) {
			System.out.println("프론트 secuIndex.do 항목 실행");
			viewPage="/info"+com;
		}else if(com.equals("/safety.do")) {
			System.out.println("프론트 safety.do 항목 실행");
			viewPage="/info"+com;
		}else if(com.equals("/secuFaci.do")) {
			System.out.println("프론트 secuFaci.do 항목 실행");
			viewPage="/info"+com;
		}else if(com.equals("/write.do")) {
			System.out.println("프론트 write.do 항목 실행");
			viewPage="/board"+com;
		}else if(com.equals("/write_view.do")) {
			System.out.println("프론트 write_view.do 항목 실행");
			viewPage="/board"+com;
		}else if(com.equals("/modify.do")) {
			System.out.println("프론트 modify.do 항목 실행");
			viewPage="/board"+com;
		}else if(com.equals("/delete.do")) {
			System.out.println("프론트 delete.do 항목 실행");
			viewPage="/board"+com;
		}else if(com.equals("/reply.do")) {
			System.out.println("프론트 reply.do 항목 실행");
			viewPage="/board"+com;
		}else if(com.equals("/content_view.do")) {
			System.out.println("프론트 content_view.do 항목 실행");
			viewPage="/board"+com;
		}else if(com.equals("/reply_view.do")) {
			System.out.println("프론트 reply_view.do 항목 실행");
			viewPage="/board"+com;
		}else if(com.equals("/callPredict.do")) {
			System.out.println("프론트 callPredict.do 항목 실행");
			viewPage="/chart"+com;
		}
		
		if(viewPage != null) {
		request.getRequestDispatcher(viewPage).forward(request, response);}
	}

}
