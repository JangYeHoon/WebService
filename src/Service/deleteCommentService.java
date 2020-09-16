package Service;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.*;

public class deleteCommentService implements Service {
	public void Excute(HttpServletRequest request, HttpServletResponse response) {
		CommentDao dao = new CommentDao();

		int num = Integer.parseInt(request.getParameter("commentNum"));
		System.out.println(num);
		dao.deleteComment(num);
	}
}
