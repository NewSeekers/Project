package chartCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import chartModel.CGuPageDao;

public class GuPageChart implements CCommandGuPage {

	@Override
	public JSONArray execute(HttpServletRequest request, HttpServletResponse response) {
		CGuPageDao dao =  CGuPageDao.getInstance();
		String guNameValue = request.getParameter("guNameValue");
		JSONArray ar_rate = dao.getArRate(guNameValue);
		
		return ar_rate;
	}

}
