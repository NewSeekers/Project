package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import boardCommand.BCommand;
import boardCommand.BWriteCommand;

/**
 * Servlet implementation class BoardController
 */
@WebServlet("/board/*.do")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("board doGet 들어옴");
		actionDo(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("board doPost 들어옴");
		actionDo(request, response); 
	}
	
	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("board actionDo 들어옴");
		
		request.setCharacterEncoding("UTF-8");
		
		String viewPage =null;
		BCommand command =null;
		
		String uri = request.getRequestURI();
		System.out.println("uri :" +uri);
		String conPath = request.getContextPath();
		System.out.println("conPath : "+conPath);
		String servPath = request.getServletPath();
		System.out.println("servPath : "+servPath);
		String com = uri.substring(servPath.length());
		
		if(com.equals("/write_view.do")) {
			viewPage = "write_view.jsp";
		} else if(com.equals("/write.do")) {
			command = new BWriteCommand();
			command.execute(request, response);
			
		}
	}
}
