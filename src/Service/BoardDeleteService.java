package Service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.*;

public class BoardDeleteService implements Service {
	public void Excute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		int num = Integer.parseInt(request.getParameter("num"));
		int userNum = (int)session.getAttribute("userNum");
		boolean userCheck = false;
		BoardDao dao = new BoardDao();
		userCheck = dao.isBoardWriter(num, userNum);
		
		if (userCheck == false) {
			try {
			response.setContentType("text/html; charset=EUC-KR");
			PrintWriter out = response.getWriter();
			out = response.getWriter();
			out.println("<script>");
			out.println("alert('권한이 없습니다.');");
			out.println("location.href='./music?task=mainHome';");
			out.println("</script>");
			out.close();
			return ;
			} catch (IOException e) {}
		}
		
		dao.BoardDelete(num);
	}
}
