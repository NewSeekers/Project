package boardCommand;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import boardModel.BDao;
import boardModel.BDto;

public class BIndexBoardCommand implements BCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		BDao dao = new BDao();
		ArrayList<BDto> dtos = dao.indexList();
		System.out.println("인덱스 리스트 불러옴 : "+dtos.get(0).getUser_Id());
		request.setAttribute("indexList", dtos);
	}
	

}
