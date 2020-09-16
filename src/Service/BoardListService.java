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
		
		// 총 페이지 수
		int maxpage = (int)((double)listcount/limit+0.95);
		// 현재 페이지에 보여줄 시작 페이지 수(1, 11, 21 등...)
		int startpage = (((int)((double)page / 10 + 0.9))-1) * 10 + 1;
		// 현재 페이지에 보여줄 마지막 페이지수(10, 20, 30 등...)
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
