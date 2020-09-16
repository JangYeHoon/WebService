package Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.MusicDao;
import Dao.PlaylistBean;

public class deleteService implements Service{
	public void Excute(HttpServletRequest request, HttpServletResponse response) {		
		HttpSession session = request.getSession();
		MusicDao dao = new MusicDao();
		PlaylistBean bean = new PlaylistBean();
		
		bean.setMusicNum(Integer.parseInt(request.getParameter("title")));
		int userNum = (int)session.getAttribute("userNum");
		bean.setUserNum(userNum);
		dao.playListDelete(bean);
		
		int musicNum = dao.getmusicNum(bean);
		dao.MusicPlayCountPlus(bean.getMusicNum());
		
		request.setAttribute("musicPath", Integer.toString(musicNum));
	}
}
