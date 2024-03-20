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
import chartCommand.PerceivedSecuInfo;
import chartCommand.PredictChart;
import chartCommand.SeoulArrestChart;
import chartCommand.SeoulCrimelChart;
import chartCommand.SeoulGlobal;
import chartCommand.SeoulSecuInfo;

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
		}else if(com.contains("/callPredict.do")) {
			CCommand pred = new PredictChart();
			pred.execute(request, response);
		}else if(com.equals("/guPage_chart.do")) {
			System.out.println("guPage_chart.do 받음");
			GuPageChart guPage = new GuPageChart();
			guPage.execute(request, response);
		}else if(com.equals("/guPage_perceivedSafety.do")) {
			System.out.println("guPage_perceivedSafety.do 받음");
			GuPagePerceivedSafety guPage = new GuPagePerceivedSafety();
			guPage.execute(request, response);
		}else if(com.equals("/guPage_secufacil.do")) {
			System.out.println("guPage_secufacil.do 받음");
			GuPageSecufacil guPage = new GuPageSecufacil();
			guPage.execute(request, response);
		}else if(com.equals("/guPage_secuGrade.do")) {
			System.out.println("guPage_secufacil.do 받음");
			GuPageSecuGrade guPage = new GuPageSecuGrade();
			guPage.execute(request, response);
		}else if (com.contains("/secuInfo.do")) {
//			System.out.println("secuInfo서비스 시작=====");
			SeoulSecuInfo sSI = new SeoulSecuInfo();
			sSI.execute(request, response);
		}else if(com.contains("/perceivedSecuInfo")) {
//			System.out.println("perceivedSecuInfo 서비스 시작===");
			PerceivedSecuInfo psi = new PerceivedSecuInfo();
			psi.execute(request, response);
		}else if(com.contains("/callGlobal.do")) {
			SeoulGlobal sg = new SeoulGlobal();
			sg.execute(request, response);
		}
		
		
		
	}
	
	
	
	
	
	
	
}
