package chartCommand;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import chartModel.CGuPageDao;

public class GuPageSecufacil implements CCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		CGuPageDao dao = CGuPageDao.getInstance();
		String guNameValue = request.getParameter("guNameValue");
		JSONObject secufacil = dao.getSecufacil(guNameValue);
		response.setHeader("Access-Control-Allow-Origin","*");
		response.setContentType("application/json");
		System.out.println("secufacil : "+secufacil);
        response.getWriter().write(secufacil.toString());
	}
}
