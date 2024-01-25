package infoCommand;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import infoModel.SafetyDao;

public class Safety implements ICommand{
	public void execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String year = request.getParameter("year");
		System.out.println(year);
		SafetyDao sadao = new SafetyDao();
		JSONArray array = sadao.getSafety(year);
		
		
		
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        // JSON 데이터 전송
        response.getWriter().write(array.toString());
}
}
