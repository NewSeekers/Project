package chartCommand;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import chartModel.CGuPageDao;

public class GuPageChart implements CCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException {
		CGuPageDao dao =  CGuPageDao.getInstance();
		String guNameValue = request.getParameter("guNameValue");
		JSONArray ar_rate = dao.getArRate(guNameValue);
		response.setHeader("Access-Control-Allow-Origin","*");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
        // JSON 데이터 전송
        response.getWriter().write(ar_rate.toString());
	}
}
