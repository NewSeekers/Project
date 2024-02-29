package boardCommand;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import boardModel.BDao;

public class BModifyCommand {

	public int execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		
		//jsp에서 hidden input으로 전달된 값을 받음.
		String community_num = request.getParameter("community_num");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
	
		BDao dao = new BDao();
		int result = dao.modify(community_num, title, content);
		return result;

	}

}
