package chartCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import chartModel.CGuPageDao;

public class GuPageSecufacil implements CCommandJSONObject{

	@Override
	public JSONObject execute(HttpServletRequest request, HttpServletResponse response) {
		CGuPageDao dao = CGuPageDao.getInstance();
		String guNameValue = request.getParameter("guNameValue");
		JSONObject secufacil = dao.getSecufacil(guNameValue);
		return secufacil;
	}
	
}
