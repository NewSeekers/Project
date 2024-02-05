package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import boardCommand.BCommand;
import boardCommand.BContentCommand;
import boardCommand.BDeleteCommand;
import boardCommand.BIndexBoardCommand;
import boardCommand.BListCommand;
import boardCommand.BModifyCommand;
import boardCommand.BReplyCommand;
import boardCommand.BWriteCommand;

/**
 * Servlet implementation class BoardController
 */

@WebServlet("/board/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("board doGet 진입");
		actionDo(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("board doPost 진입");
		actionDo(request, response);
	}

	private void actionDo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		String viewPage = null;
		BCommand bc = null;

		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String servPath = request.getServletPath();
		String com = uri.substring((conPath).length());

		System.out.println("uri :" + uri);
		System.out.println("conPath : " + conPath);
		System.out.println("servPath : " + servPath);
		System.out.println("com: " + com);

		if (com.equals("/write_view.do")) {
			viewPage = "../write_view.jsp";
		} else if (com.contains("/write.do")) {
			System.out.println("board 컨트롤러 : write 실행");
			bc = new BWriteCommand();
			bc.execute(request, response);
			response.sendRedirect("list.do?page=1");
		} else if (com.contains("/list.do")) {
			System.out.println("board 컨트롤러 : list 실행");
			bc = new BListCommand();
			bc.execute(request, response);
//			System.out.println("ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ" + request.getAttribute("pageBtnNum"));
			viewPage="../list.jsp?page=" + request.getParameter("page");
		} else if (com.contains("/modify.do")) {
			System.out.println("board 컨트롤러 : modify 실행");
			BModifyCommand bmc = new BModifyCommand();
			int result =bmc.execute(request, response); 
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script> if (" + (result == 1) + ") {alert(\"수정에 성공했습니다.\")} else if (" + (result == 0) + ") {alert(\"수정에 실패했습니다.\")}; location.href='"+"list.do?page=1"+"';</script>");
		} else if (com.contains("/delete.do")) {
			System.out.println("board 컨트롤러 : delete 실행");
			bc = new BDeleteCommand();
			bc.execute(request, response);
			viewPage = "list.do?page=1";
		} else if (com.contains("/reply.do")) {
			System.out.println("board 컨트롤러 : reply 실행");
			bc = new BReplyCommand();
			bc.execute(request, response);
			viewPage = "list.do?page=1";
		} else if (com.contains("content_view.do")) {
			System.out.println("board 컨트롤러 : content_view 실행");
			bc = new BContentCommand();
			bc.execute(request, response);
			viewPage ="../content_view.jsp";
		} else if (com.contains("reply_view.do")) {
			System.out.println("board 컨트롤러 : reply_view 실행");
			viewPage ="../reply_view.do";
		} else if(com.contains("/")){
			// 페이지 로딩되는 순간 리스트 뿌려주려고. 
			System.out.println("board 컨트롤러 : index.do 실행");
			bc = new BIndexBoardCommand();
			bc.execute(request, response);
			viewPage = "../Index.jsp";
		}
			

		if (viewPage != null) {
			System.out.println("Forwarding to " + viewPage);
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
		}
	}
}
