package boardCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import boardModel.BDao;
import boardModel.BDto;

public class BContentCommand implements BCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html; charset=UTF-8");
		//jsp에서 hidden input으로 전달된 값을 받음.
		String community_num = request.getParameter("community_num");
		BDao dao = new BDao();
		BDto dto = dao.contentView(community_num);
		request.setAttribute("content_view", dto);
		
		//bName을 기반으로 해당 글과 연결된 작성자 정보 가져오기
		BDto selectedPost = dao.getPostById(community_num);
		String contentName = selectedPost.getUser_Id();
		String loggedInUserId = (String)request.getSession().getAttribute("id");
		System.out.println("ture로 들어가기전"+loggedInUserId);
		if(loggedInUserId != null && loggedInUserId.equals(contentName)) {
			request.setAttribute("showEditButton", true);
		}
	}

}
