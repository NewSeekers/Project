package chartCommand;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;

import chartModel.CrimeDao;
import chartModel.CrimeDto;

public class SeoulArrestChart implements CCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {

		CrimeDao crimedao = new CrimeDao();
		List<CrimeDto> list = crimedao.getArrestNum();

		JSONArray jsonArr = new JSONArray();
		
		for (CrimeDto dto : list) {
			JSONObject obj = new JSONObject();
			
			obj.put("year", dto.getYear());
			obj.put("homiArRate", dto.getHomiArRate());
			obj.put("robArRate", dto.getRobArRate());
			obj.put("sexArRate", dto.getSexArRate());
			obj.put("thefArRate", dto.getThefArRate());
			obj.put("violArRate", dto.getViolArRate());
			jsonArr.put(obj);
		}
		
		//gson stringify
		 String jsonString = new Gson().toJson(jsonArr);
		
		
		
	}
	

}
