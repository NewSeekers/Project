package controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import boardCommand.BCommand;
import boardCommand.BContentCommand;
import boardCommand.BDeleteCommand;
import boardCommand.BListCommand;
import boardCommand.BModifyCommand;
import boardCommand.BReplyCommand;
import boardCommand.BReplyViewCommand;
import boardCommand.BWriteCommand;

/**
 * Servlet implementation class BoardController
 */

//@WebServlet("/board/*")

public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpServletRequest request;
	HttpServletResponse response;

	public BoardController(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String viewPage = null;
		BCommand command = null;

		String uri = request.getRequestURI();
		System.out.println("uri :" + uri);
		String conPath = request.getContextPath();

		System.out.println("conPath : " + conPath);

		String servPath = request.getServletPath();
		System.out.println("servPath : " + servPath);
		String com = uri.substring((conPath).length());
		System.out.println("com: " + com);

		if (com.equals("/write_view.do")) {
			viewPage = "./write_view.jsp";
			
			
		}  else if (com.equals("/write.do")) {
			System.out.println("write 들어옴");
			command = new BWriteCommand();
			command.execute(request, response);
			response.sendRedirect("list.do");
			System.out.println("리스트 두로 감");
			
			
		} else if (com.equals("/list.do")) {
			System.out.println("리스트 쩜 두 실행 ----------------------------------");
			command = new BListCommand();
			command.execute(request, response);
			viewPage = "list.jsp";
		} else if (com.equals("/modify.do")) {
			command = new BModifyCommand();
			command.execute(request, response);
			viewPage = "list.do";
		} else if (com.equals("/delete.do")) {
			command = new BDeleteCommand();
			command.execute(request, response);
			viewPage = "list.do";
		} else if (com.equals("/reply.do")) {
			command = new BReplyCommand();
			command.execute(request, response);
			viewPage = "list.do";
		}

		if (viewPage != null) {
			System.out.println("Forwarding to " + viewPage);
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);

			dispatcher.forward(request, response);
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("board doGet 들어옴");
		//actionDo(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("board doPost 들어옴");
		//actionDo(request, response);
	}

//	private void actionDo(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		System.out.println("board actionDo 들어옴");
//
//		request.setCharacterEncoding("UTF-8");
//
//		String viewPage = null;
//		BCommand command = null;
//
//		String uri = request.getRequestURI();
//		System.out.println("uri :" + uri);
//		String conPath = request.getContextPath();
//
//		System.out.println("conPath : " + conPath);
//
//		String servPath = request.getServletPath();
//		System.out.println("servPath : " + servPath);
//		String com = uri.substring((conPath + servPath).length());
////		String com = uri.substring((conPath).length());
//		System.out.println("com: " + com);
//
//		if (com.equals("/write_view.do")) {
//
//			viewPage = "./write_view.jsp";
//		} else if (com.equals("/write.do")) {
//			command = new BWriteCommand();
//			command.execute(request, response);
//			response.sendRedirect("list.do");
//
//		} else if (com.equals("/list.do")) {
//			System.out.println("리스트 쩜 두 실행 ----------------------------------");
//			command = new BListCommand();
//			command.execute(request, response);
//			viewPage = "list.jsp";
//		} else if (com.equals("/content_view.do")) {
//			command = new BContentCommand();
//			command.execute(request, response);
//			viewPage = "content_view.jsp";
//		} else if (com.equals("/modify.do")) {
//			command = new BModifyCommand();
//			command.execute(request, response);
//			viewPage = "list.do";
//		} else if (com.equals("/delete.do")) {
//			command = new BDeleteCommand();
//			command.execute(request, response);
//			viewPage = "list.do";
//		} else if (com.equals("/reply_view.do")) {
//			command = new BReplyViewCommand();
//			command.execute(request, response);
//			viewPage = "reply_view.jsp";
//		} else if (com.equals("/reply.do")) {
//			command = new BReplyCommand();
//			command.execute(request, response);
//			viewPage = "list.do";
//		} else if (com.equals("/membersAll.do")) {
//			viewPage = "board/membersAll.jsp";
//		}
//
//		if (viewPage != null) {
//			System.out.println("Forwarding to " + viewPage);
//			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
//			dispatcher.forward(request, response);
//		}
//	}
}
