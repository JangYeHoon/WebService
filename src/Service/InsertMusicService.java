package Service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.MusicDao;
import Dao.PlaylistBean;

public class InsertMusicService implements Service {
	public void Excute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		MusicDao dao = new MusicDao();
		PlaylistBean bean = new PlaylistBean();

		bean.setMusicNum(Integer.parseInt(request.getParameter("title")));
		int userNum = (int) session.getAttribute("userNum");
		bean.setUserNum(userNum);

		if (dao.checkPlayList(bean)) {
			dao.MusicInsert(bean);
		} 
		request.setAttribute("musicPath", Integer.toString(bean.getMusicNum()));
	}
}