package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;



/**
 * Servlet Filter implementation class EncoderFilter
 */
//@WebFilter("/*")
public class EncoderFilter implements Filter {
	ServletContext ctx;
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("utf-8 인코딩");
		ctx = fConfig.getServletContext();
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String context = ((HttpServletRequest) request).getContextPath();		
		String pathinfo = ((HttpServletRequest) request).getRequestURI();		
		String realPath = request.getRealPath(pathinfo);
		String mesg = "context 정보:" + context + "\n uri 정보:"+ pathinfo + "\n 물리적경로 " +  realPath;
		System.out.println(mesg);
		
		long begin = System.currentTimeMillis();
		chain.doFilter(request, response);
		long end = System.currentTimeMillis();
		System.out.println("작업시간" + (end-begin)+"ms");
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */

}
