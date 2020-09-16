package Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.MusicBean;
import Dao.MusicDao;
import Dao.PlaylistBean;
import Dao.RecommendDao;;

public class recommendMusicService implements Service{
	public void Excute(HttpServletRequest request, HttpServletResponse response){
		RecommendDao dao = new RecommendDao();
		HttpSession session = request.getSession();
		int userNum = (int)session.getAttribute("userNum");
		MusicBean bean = new MusicBean(); 
		dao.setValue();
		int musicNum = -1;
		//������� �÷��̸���Ʈ�� ���� ����� ������ �뷡Num�� ����
		musicNum = dao.recommendMusic(userNum);
		dao.getMusicInfo(musicNum, bean);
		request.setAttribute("recommendMusic", bean);
	}	
}
