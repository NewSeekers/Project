package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import chartCommand.CCommand;
import chartCommand.GuPageChart;
import chartCommand.GuPagePerceivedSafety;
import chartCommand.GuPageSecuGrade;
import chartCommand.GuPageSecufacil;
import chartCommand.SeoulArrestChart;
import chartCommand.SeoulCrimelChart;

/**
 * Servlet implementation class ServiceController
 */
@WebServlet("/chart/*")
public class ChartController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			actionDo(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			actionDo(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
   
	
	public void actionDo(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		System.out.println("차트컨트롤러 actionDo 실행");
		String uri = request.getRequestURI();
		System.out.println("uri: " +uri);
		String conPath = request.getContextPath();
		System.out.println("contextPath: "+conPath);
		String servPath = request.getServletPath();
		System.out.println("servletPath: "+servPath);
		String com = uri.substring(servPath.length()+conPath.length());
		System.out.println("com: "+com);
		
		 
		if(com.equals("/callCrime.do")) {
			CCommand seoulcc = new SeoulCrimelChart();
			seoulcc.execute(request, response);
			
		}else if(com.equals("/callArrest.do")) {
			CCommand seoulac = new SeoulArrestChart();
			seoulac.execute(request, response);
		}else if(com.equals("/guPage_chart.do")) {
			response.setHeader("Access-Control-Allow-Origin","*");
			response.setContentType("application/json");
			GuPageChart guPage = new GuPageChart();
			JSONArray ar_rate = guPage.execute(request, response);
			PrintWriter out = response.getWriter();
			out.print(ar_rate.toString());
			out.flush();
		}else if(com.equals("/guPage_perceivedSafety.do")) {
//			System.out.println("guPage_perceivedSafety.do 받음");
			response.setHeader("Access-Control-Allow-Origin","*");
			response.setContentType("application/json");
			GuPagePerceivedSafety guPage = new GuPagePerceivedSafety();
			JSONObject safety = guPage.execute(request, response);
			System.out.println("safety"+safety);
			PrintWriter out = response.getWriter();
			out.print(safety.toString());
			out.flush();
		}else if(com.equals("/guPage_secufacil.do")) {
			System.out.println("guPage_secufacil.do 받음");
			response.setHeader("Access-Control-Allow-Origin","*");
			response.setContentType("application/json");
			GuPageSecufacil guPage = new GuPageSecufacil();
			JSONObject secufacil = guPage.execute(request, response);
			System.out.println("secufacil"+secufacil);
			PrintWriter out = response.getWriter();
			out.print(secufacil.toString());
			out.flush();
		}else if(com.equals("/guPage_secuGrade.do")) {
			System.out.println("guPage_secufacil.do 받음");
			response.setHeader("Access-Control-Allow-Origin","*");
			response.setContentType("application/json");
			GuPageSecuGrade guPage = new GuPageSecuGrade();
			JSONObject secuGrade = guPage.execute(request, response);
			System.out.println("secufacil"+secuGrade);
			PrintWriter out = response.getWriter();
			out.print(secuGrade.toString());
			out.flush();
		}
		
		
		
	}
	
	
	
	
	
	
	
}
