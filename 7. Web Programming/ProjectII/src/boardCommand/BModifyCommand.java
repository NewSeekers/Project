package boardCommand;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import boardModel.BDao;

public class BModifyCommand {

	public int execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String bId = request.getParameter("bId");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		
		BDao dao = new BDao();
		int result = dao.modify(bId, bTitle, bContent);
		System.out.println(result+"<<<<<result");
		
		
		return result;
	}

}
