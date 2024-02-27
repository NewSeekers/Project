package boardCommand;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import boardModel.BDao;
import boardModel.BDto;

public class BListCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		BDao dao = new BDao();
		int listSize = dao.getLIstSize();
		System.out.println("리스트사이즈"+listSize);
		
		int currentPage = Integer.parseInt(request.getParameter("page"));
		int showListNum = 10;
		int showPBtnNum = 5;
		int pageBtnNum= 0; 
		if(listSize != 0) {
			pageBtnNum = (int) Math.ceil(listSize/showListNum);
			if(listSize % showListNum != 0) {
				pageBtnNum += 1;
			}
		}
		ArrayList<BDto> dtos = dao.list(currentPage);
		
		int startPage = Math.max(1, currentPage - showPBtnNum / 2);
		
		request.setAttribute("list", dtos);
		request.setAttribute("pageBtnNum", pageBtnNum);
		request.setAttribute("showPBtnNum", showPBtnNum);
		request.setAttribute("startPage", startPage);
	}
}
