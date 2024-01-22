package chartCommand;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

import chartModel.CrimeDao;
import chartModel.CrimeDto;

public class SeoulCrimelChart implements CCommand {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {

		CrimeDao crimedao = new CrimeDao();
		List<CrimeDto> list = crimedao.getCrimeNum();

		JSONArray jsonArr = new JSONArray();
		
		for (CrimeDto dto : list) {
			JSONObject obj = new JSONObject();
			
			obj.put("year", dto.getYear());
			obj.put("homicide", dto.getHomicide());
			obj.put("robber", dto.getRobber());
			obj.put("sexual", dto.getSexual());
			obj.put("theft", dto.getTheft());
			obj.put("violence", dto.getViolence());
			jsonArr.put(obj);
		}
		
		
		String jsonString = jsonArr.toString(); // 자바직렬화
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(jsonString);
		out.flush();

	}
}