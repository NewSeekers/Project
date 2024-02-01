package chartCommand;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import chartModel.PredictDao;

public class PredictChart implements CCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String region = request.getParameter("region");
		
		PredictDao predDao = new PredictDao();
		String predData = predDao.getPredData(region).toString();
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(predData);
		out.flush();
	}

}
