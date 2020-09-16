package Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.*;

public class CommentWriteService implements Service {
	public void Excute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		int boardNum = Integer.parseInt(request.getParameter("num"));
		int userNum = (int)session.getAttribute("userNum");
		
		CommentDao dao = new CommentDao();
		CommentBean bean = new CommentBean();
		
		bean.setBoardNum(boardNum);
		bean.setUserNum(userNum);
		bean.setCommentName(request.getParameter("name"));
		bean.setCommentMemo(request.getParameter("memo"));
		
		dao.CommentWrite(bean);
	}
}
