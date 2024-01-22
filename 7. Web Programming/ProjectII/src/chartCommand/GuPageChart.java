package chartCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import chartModel.CDao;

public class GuPageChart implements CCommandGuPage {

	@Override
	public JSONArray execute(HttpServletRequest request, HttpServletResponse response) {
		CDao dao =  CDao.getInstance();
		JSONArray list = dao.getChart();
		return list;
	}

}
