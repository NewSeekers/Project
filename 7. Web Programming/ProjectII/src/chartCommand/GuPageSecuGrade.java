package chartCommand;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import chartModel.CGuPageDao;

public class GuPageSecuGrade implements CCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		CGuPageDao dao = CGuPageDao.getInstance();
		String guNameValue = request.getParameter("guNameValue");
		JSONObject secuGrade = dao.getSecugrade(guNameValue);
		System.out.println("구 이름, 인구 수 : "+secuGrade);
		response.setHeader("Access-Control-Allow-Origin","*");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
        // JSON 데이터 전송
        response.getWriter().write(secuGrade.toString());
	}
}
