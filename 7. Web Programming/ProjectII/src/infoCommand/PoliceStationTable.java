package infoCommand;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import infoModel.PoliceStationDao;

public class PoliceStationTable implements ICommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		PoliceStationDao dao = PoliceStationDao.getInstance();
		String guNameValue = request.getParameter("guNameValue");
		JSONArray policeStationList = dao.getPoliceStations(guNameValue);
		response.setHeader("Access-Control-Allow-Origin","*");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        // JSON 데이터 전송
        response.getWriter().write(policeStationList.toString());
	}
}
