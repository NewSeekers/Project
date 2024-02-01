package memberCommand;



import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import memberModel.MemberDao;
import memberModel.MemberDto;




public class MLoginOkCommand implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		MemberDao dao = MemberDao.getInstance();
		int checkNum = dao.userCheck(id, pw);
		
		if (checkNum == -1) {
            request.setAttribute("errorMessage", "ID does not exist.");
            
        } else if (checkNum == 0) {
            request.setAttribute("errorMessage", "Incorrect password.");
        } else if (checkNum == 1) {
            MemberDto dto = dao.getMember(id);
            if (dto == null) {
                request.setAttribute("errorMessage", "Member does not exist.");

            } else {
                // Successful login
                String name = dto.getName();
                HttpSession session = request.getSession();
                session.setAttribute("id", id);
                session.setAttribute("name", name);
                session.setAttribute("ValidMem", "yes");
            }
        }
	}

}
