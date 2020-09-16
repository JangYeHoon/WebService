package Service;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.*;

public class BoardListService implements Service{
	public void Excute(HttpServletRequest request, HttpServletResponse response) {		
		BoardDao dao = new BoardDao();
		List boardList = new ArrayList();
		
		int page = 1;
		int limit = 10;
		
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		int listcount = dao.getListCount();
		boardList = dao.getBoardList(page, limit);
		
		// �� ������ ��
		int maxpage = (int)((double)listcount/limit+0.95);
		// ���� �������� ������ ���� ������ ��(1, 11, 21 ��...)
		int startpage = (((int)((double)page / 10 + 0.9))-1) * 10 + 1;
		// ���� �������� ������ ������ ��������(10, 20, 30 ��...)
		int endpage = maxpage;
		
		if (endpage>startpage+10-1) endpage = startpage + 10 - 1;
		
		request.setAttribute("page", page);
		request.setAttribute("maxpage", maxpage);
		request.setAttribute("startpage", startpage);
		request.setAttribute("endpage", endpage);
		request.setAttribute("listcount", listcount);
		request.setAttribute("boardlist", boardList);
	}
}
