package chartCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import chartModel.CGuPageDao;

public class GuPagePerceivedSafety implements CCommandJSONObject {
	
	@Override
	public JSONObject execute(HttpServletRequest request, HttpServletResponse response) {
		CGuPageDao dao = CGuPageDao.getInstance();
		String guNameValue = request.getParameter("guNameValue");
		String year = request.getParameter("year");
		System.out.println("연도 : "+year+"구이름 : "+guNameValue);
		JSONObject perceivedSafety = dao.getPerceivedSafety(year, guNameValue);
		System.out.println(perceivedSafety);
		return perceivedSafety;
	}

}
