package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import chartCommand.GuPageChart;

/**
 * Servlet implementation class ServiceController
 */
@WebServlet("/guPage_chart")
public class ServiceController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}
	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("board actionDo 들어옴");
		
		request.setCharacterEncoding("UTF-8");
		
		String viewPage =null;
		
		
		String uri = request.getRequestURI();
		System.out.println("uri :" +uri);
		String conPath = request.getContextPath();
		System.out.println("conPath : "+conPath);
		String com = uri.substring(conPath.length());
		System.out.println("com : "+com);
		if(com.equals("/guPage_chart")) {
			System.out.println("받음");
			response.setHeader("Access-Control-Allow-Origin","*");
			response.setContentType("application/json");
			GuPageChart guPage = new GuPageChart();
			JSONArray list = guPage.execute(request, response);
			PrintWriter out = response.getWriter();
			out.print(list.toString());
			out.flush();
		}
		
		if(viewPage!=null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
		}
		
	}
}
