package chartCommand;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import chartModel.GlobalDao;

public class SeoulGlobal implements CCommand{

	public void execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		
		GlobalDao gd = new GlobalDao();
		JSONArray array = gd.getGlobal();
				
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(array.toString());
				
	}

}
