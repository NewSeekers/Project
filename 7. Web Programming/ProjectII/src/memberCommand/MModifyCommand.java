package memberCommand;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import memberModel.MemberDao;
import memberModel.MemberDto;



public class MModifyCommand implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Modify 서비스 호출");
		String id = request.getParameter("id");
		MemberDao dao = MemberDao.getInstance();
		MemberDto dto = dao.getMember(id);
	
		System.out.println("modifyLogin ::::: " + dto);
		request.setAttribute("modifyLogin", dto);
		 
    }
}