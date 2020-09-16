package Service;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.*;

public class BoardViewService implements Service{
	public void Excute(HttpServletRequest request, HttpServletResponse response) {
		BoardDao dao = new BoardDao();
		BoardBean bean = new BoardBean();
		
		int num = Integer.parseInt(request.getParameter("num"));
		dao.setReadCountUpdate(num);
		bean = dao.getBoardView(num);
		
		request.setAttribute("boardView", bean);
	}
}
