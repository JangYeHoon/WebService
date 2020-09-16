package Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.MusicBean;
import Dao.MusicDao;

public class VideoPlayService implements Service {
	public void Excute(HttpServletRequest request, HttpServletResponse response) {
		int title = Integer.parseInt(request.getParameter("title"));
		MusicDao dao = new MusicDao();
		MusicBean bean = new MusicBean();
		bean = dao.videoPath(title);
		
		request.setAttribute("videoPlay", bean);
	}
}
