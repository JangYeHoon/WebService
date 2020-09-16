package Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import Dao.*;

public class CommentListService implements Service {
	public void Excute(HttpServletRequest request, HttpServletResponse response) {
		int boardNum = Integer.parseInt(request.getParameter("num"));
		List commentList = new ArrayList();
		
		CommentDao dao = new CommentDao();
		commentList = dao.CommentList(boardNum);
		
		request.setAttribute("commentList", commentList);
	}
}
