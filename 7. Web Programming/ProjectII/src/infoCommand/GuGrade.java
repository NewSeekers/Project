package infoCommand;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;

import chartModel.CrimeDto;
import infoModel.GuGradeDao;
import infoModel.GuGradeDto;

public class GuGrade implements ICommand {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		GuGradeDao ggdao = new GuGradeDao();
		List<GuGradeDto> list = ggdao.getGuGradeNum();

		JSONArray jsonArr = new JSONArray();

		for (GuGradeDto dto : list) {
			JSONObject obj = new JSONObject();

			obj.put("y2004", dto.getY2004());
			obj.put("y2005", dto.getY2005());
			obj.put("y2006", dto.getY2006());
			obj.put("y2007", dto.getY2007());
			obj.put("y2008", dto.getY2008());
			obj.put("y2009", dto.getY2009());
			obj.put("y2010", dto.getY2010());
			obj.put("y2011", dto.getY2011());
			obj.put("y2012", dto.getY2012());
			obj.put("y2013", dto.getY2013());
			obj.put("y2014", dto.getY2014());
			obj.put("y2015", dto.getY2015());
			obj.put("y2016", dto.getY2016());
			obj.put("y2017", dto.getY2017());
			obj.put("y2018", dto.getY2018());
			obj.put("y2019", dto.getY2019());
			obj.put("y2020", dto.getY2020());
			obj.put("y2021", dto.getY2021());
			obj.put("y2022", dto.getY2022());

			jsonArr.put(obj);
		}
		String jsonString = jsonArr.toString(); // 자바직렬화

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(jsonString);
		out.flush();

	}

	// TODO Auto-generated method stub

}
