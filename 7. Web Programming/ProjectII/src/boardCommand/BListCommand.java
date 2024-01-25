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
		int listSize = dao.getLIstSize(); // 게시물 전체 개수
		System.out.println("리스트사이즈"+listSize);
		int pageBtnNum = 0; //총 버튼 수
		int showListNum = 10; //한 화면에 나타낼 게시물 수
		int showBtnNum = 5; // 한 화면에 나타낼 버튼 수
		if(listSize != 0) {
		pageBtnNum = (int) Math.ceil(listSize/showListNum); // 총 페이지수 
		}
		
		int currentPage = Integer.parseInt(request.getParameter("page"));
		System.out.println("currentPage"+currentPage);
		int pageGroup = (int) Math.ceil(1/pageBtnNum);
		request.setAttribute("currentPage", currentPage);
		
		
		int startPage = Math.max(1, currentPage - showBtnNum / 2);
		request.setAttribute("startPage",startPage);
		request.setAttribute("endPage", pageBtnNum);
		
		
		ArrayList<BDto> dtos = dao.list(currentPage);
		request.setAttribute("list", dtos);
		
		
		
		
		
		
		
		
		
	}
}
