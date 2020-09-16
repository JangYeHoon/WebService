package Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.*;

public class BoardChangeService implements Service {
	public void Excute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		BoardDao dao = new BoardDao();
		BoardBean bean = new BoardBean();
		
		bean.setBoardNum(Integer.parseInt(request.getParameter("num")));
		bean.setBoardWriter(request.getParameter("name"));
		bean.setBoardName(request.getParameter("title"));
		bean.setBoardMemo(request.getParameter("memo"));
		int userNum = (int)session.getAttribute("userNum");
		bean.setUserNum(userNum);
		
		dao.BoardChange(bean);
	}
}
