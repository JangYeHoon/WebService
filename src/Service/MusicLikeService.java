package Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.MusicDao;
import Dao.PlaylistBean;

public class MusicLikeService implements Service {
	
	public void Excute(HttpServletRequest request, HttpServletResponse response) {
		MusicDao dao = new MusicDao();
		
		dao.MusicLikeCountPlus(Integer.parseInt(request.getParameter("title")));
	}
}
