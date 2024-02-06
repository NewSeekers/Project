package frontController;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import controller.BoardController;
import infoCommand.DataLoadingQuartz;
import infoCommand.PoliceStationApiData;

/**
 * Servlet implementation class FrontController
 */
@WebServlet(description = "get command", urlPatterns = { "*.do" })
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		PoliceStationApiData policeStation = new PoliceStationApiData();
		
		
		//globalData call
		JobDetail jobDetail = JobBuilder.newJob(DataLoadingQuartz.class)
                .withIdentity("dataLoadingQuartz", "group1")
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("dataLoadingTrigger", "group1")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInHours(24)
                        .repeatForever())
                .build();

        try {
            Scheduler scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.start();
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            throw new ServletException("Error initializing Quartz scheduler", e);
        }
		
		
		
		
		
		
	}
	
	
	
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
//			System.out.println("프론트 list.do 항목 실행");
			viewPage="/board"+com;
		} else if(com.equals("/")||com.equals("/index.do")) {
//			System.out.println("프론트 index.do 항목 실행");
			viewPage="/board"+com;
		} else if(com.equals("/callCrime.do")) {
//			System.out.println("프론트 callCrime.do 항목 실행");
			viewPage="/chart"+com;
			System.out.println("/chart"+com);
		}else if(com.equals("/callArrest.do")){
//			System.out.println("프론트 callArrest.do 항목 실행");
			viewPage="/chart"+com;
			System.out.println("/chart"+com);
		}else if(com.equals("/callGuGrade.do")) {
//			System.out.println("프론트 callGuGrade.do 항목 실행");
			viewPage="/info"+com;
		}else if(com.equals("/secuIndex.do")) {
//			System.out.println("프론트 secuIndex.do 항목 실행");
			viewPage="/info"+com;
		}else if(com.equals("/safety.do")) {
//			System.out.println("프론트 safety.do 항목 실행");
			viewPage="/info"+com;
		}else if(com.equals("/secuFaci.do")) {
//			System.out.println("프론트 secuFaci.do 항목 실행");
			viewPage="/info"+com;
		}else if(com.equals("/policeStation.do")) {
//			System.out.println("프론트 policeStation.do 항목 실행");
			viewPage="/info"+com;
		}else if(com.equals("/secuInfo.do")) {
//			System.out.println("프론트 secuInfo.do 항목 실행");
			viewPage="/chart"+com;
		}else if(com.equals("/perceivedSecuInfo.do")) {
//			System.out.println("프론트 perceivedSecuInfo 항목 실행");
			viewPage="/chart"+com;
		}else if(com.equals("/write.do")) {
//			System.out.println("프론트 write.do 항목 실행");
			viewPage="/board"+com;
		}else if(com.equals("/write_view.do")) {
//			System.out.println("프론트 write_view.do 항목 실행");
			viewPage="/board"+com;
		}else if(com.equals("/modify.do")) {
//			System.out.println("프론트 modify.do 항목 실행");
			viewPage="/board"+com;
		}else if(com.equals("/delete.do")) {
//			System.out.println("프론트 delete.do 항목 실행");
			viewPage="/board"+com;
		}else if(com.equals("/reply.do")) {
//			System.out.println("프론트 reply.do 항목 실행");
			viewPage="/board"+com;
		}else if(com.equals("/content_view.do")) {
//			System.out.println("프론트 content_view.do 항목 실행");
			viewPage="/board"+com;
		}else if(com.equals("/reply_view.do")) {
//			System.out.println("프론트 reply_view.do 항목 실행");
			viewPage="/board"+com;
		}else if(com.equals("/callPredict.do")) {
//			System.out.println("프론트 callPredict.do 항목 실행");
			viewPage="/chart"+com;
		}else if(com.equals("/guPage_chart.do")) {
//			System.out.println("프론트 guPage_chart.do 항목 실행");
			viewPage="/chart"+com;
		}else if(com.equals("/guPage_perceivedSafety.do")) {
//			System.out.println("프론트 guPage_perceivedSafety.do 항목 실행");
			viewPage="/chart"+com;
		}else if(com.equals("/guPage_secufacil.do")) {
//			System.out.println("프론트 guPage_secufacil.do 항목 실행");
			viewPage="/chart"+com;
		}else if(com.equals("/guPage_secuGrade.do")) {
//			System.out.println("프론트 guPage_secuGrade.do 항목 실행");
			viewPage="/chart"+com;
		}else if(com.equals("/join.do")) {
//			System.out.println("프론트 join.do 항목 실행");
			viewPage="/member"+com;
		}else if(com.equals("/login.do")) {
//			System.out.println("프론트 login.do 항목 실행");
			viewPage="/member"+com;
		}else if(com.equals("/logout.do")) {
//			System.out.println("프론트 logout.do 항목 실행");
			viewPage="/member"+com;
		}else if(com.equals("/modifyLogin.do")) {
			System.out.println("프론트 modifyLogin.do 항목 실행");
			viewPage="/member"+com;
		}else if(com.equals("/loginOk.do")) {
//			System.out.println("프론트 join.do 항목 실행");
			viewPage="/member"+com;
		}else if(com.equals("/callGlobal.do")) {
			viewPage="/chart"+com;
		}
		
		if(viewPage != null) {
		request.getRequestDispatcher(viewPage).forward(request, response);}
	}

}
