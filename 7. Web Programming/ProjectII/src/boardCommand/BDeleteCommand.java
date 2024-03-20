package boardCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import boardModel.BDao;

public class BDeleteCommand implements BCommand {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String community_num = request.getParameter("community_num");
		BDao dao = new BDao();
		dao.delete(community_num);
	}
}
