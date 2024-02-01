package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import memberCommand.MCommand;
import memberCommand.MLoginOkCommand;
import memberCommand.MLogoutCommand;
import memberCommand.MModifyCommand;

/**
 * Servlet implementation class MemberController
 */
@WebServlet("/member/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MFrontController doGet 호출");
		actionDo(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MFrontController doPost 호출");
		actionDo(request, response);
	}
	
	protected void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MFrontController actionDo 호출");
		request.setCharacterEncoding("utf-8");
		
		String ViewPage = null;
		MCommand command = null;
		
		String uri = request.getRequestURI();
		String conpath = request.getContextPath();
		String servPath = request.getServletPath();
		String com = uri.substring(conpath.length()+servPath.length());
		System.out.println("MFrontController com : "+com);
		System.out.println("MFrontController servPath : "+servPath);
		
		if(com.equals("/join.do")) {
			System.out.println("join 호출");
			ViewPage = "/join.jsp";
		} else if(com.equals("/login.do")) {
			System.out.println("login 호출");
			ViewPage = "/login.jsp";
		} else if(com.equals("/logout.do")) {
			System.out.println("logout 호출");
			command = new MLogoutCommand();
			command.execute(request, response);
			ViewPage = "/Index.jsp";
		} else if(com.equals("/modifyLogin.do")) {
			System.out.println("modifyLogin 호출");
			command = new MModifyCommand();
			command.execute(request, response);
			System.out.println("");
			ViewPage = "/modifyLogin.jsp";
		} else if(com.equals("/loginOk.do")) {
			System.out.println("loginOk 호출");
			command = new MLoginOkCommand();
			command.execute(request, response);
			ViewPage = "/Index.jsp";
		}
		
		if(ViewPage != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(ViewPage);
			dispatcher.forward(request, response);
		}
	}

}
