package boardCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import boardModel.BDao;

public class BWriteCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String user_Id = request.getParameter("user_Id");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		BDao dao = new BDao();
		dao.write(user_Id, title, content);
	}
}
