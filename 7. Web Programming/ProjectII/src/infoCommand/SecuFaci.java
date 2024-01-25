package infoCommand;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import infoModel.SecuFaciDao;

public class SecuFaci implements ICommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		
		
		SecuFaciDao sfdao = new SecuFaciDao();
		JSONArray array = sfdao.getSecuFaci();
				 
		
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        // JSON 데이터 전송
        response.getWriter().write(array.toString());
	}

}
