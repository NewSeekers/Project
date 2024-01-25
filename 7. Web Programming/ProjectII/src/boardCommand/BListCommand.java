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
		int pageBtnNum = -1;
		int showListNum = 10;
		if(listSize != 0) {
		pageBtnNum = (int) Math.ceil(listSize/showListNum);
		}
		request.setAttribute("pageBtnNum", pageBtnNum);
		
		
		
		
		
		
		ArrayList<BDto> dtos = dao.list();
		request.setAttribute("list", dtos);
		
		
		
		
		
		
		
		
		
	}
}
