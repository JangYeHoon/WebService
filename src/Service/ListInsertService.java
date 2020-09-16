package Service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.MusicDao;
import Dao.PlaylistBean;

public class ListInsertService implements Service {
	public void Excute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		MusicDao dao = new MusicDao();
		PlaylistBean bean = new PlaylistBean();

		bean.setMusicNum(Integer.parseInt(request.getParameter("title")));
		int userNum = (int)session.getAttribute("userNum");
		bean.setUserNum(userNum);

		if( dao.checkPlayList(bean) ) {
			dao.MusicInsert(bean);
			response.setContentType("text/html; charset=euc-kr");
			PrintWriter out;
			try {
				out = response.getWriter();
				out.println("<script>");
				out.println("alert('플레이리스트에 추가되었습니다.');");
				out.println("location.href='./music?task=mainHome';");
				out.println("</script>");
				out.close();
			} catch (IOException e) {}
		} else {
			response.setContentType("text/html; charset=euc-kr");
			PrintWriter out;
			try {
				out = response.getWriter();
				out.println("<script>");
				out.println("alert('존재하는 곡입니다.');");
				out.println("location.href='./music?task=mainHome';");
				out.println("</script>");
				out.close();
			} catch (IOException e) {}
		}
	}
}
