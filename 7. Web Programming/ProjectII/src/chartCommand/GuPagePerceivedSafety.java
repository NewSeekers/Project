package chartCommand;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import chartModel.CGuPageDao;

public class GuPagePerceivedSafety implements CCommand {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		CGuPageDao dao = CGuPageDao.getInstance();
		String guNameValue = request.getParameter("guNameValue");
		String year = request.getParameter("year");
		System.out.println("연도 : "+year+"구이름 : "+guNameValue);
		JSONObject perceivedSafety = dao.getPerceivedSafety(year, guNameValue);
//		System.out.println(perceivedSafety);
		response.setHeader("Access-Control-Allow-Origin","*");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
        // JSON 데이터 전송
        response.getWriter().write(perceivedSafety.toString());
	}
}