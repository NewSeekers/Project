package boardCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import boardModel.BDao;

public class BReplyCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String user_Id = request.getParameter("user_Id");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String group_num = request.getParameter("group_num");
		String step_num = request.getParameter("step_num");
		String indent_num = request.getParameter("indent_num");
		
		BDao dao = new BDao();
		dao.reply(user_Id, title, content, group_num, step_num, indent_num);
		
	}

}
