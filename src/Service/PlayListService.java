package Service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.MusicBean;
import Dao.MusicDao;

public class PlayListService implements Service {
	public void Excute(HttpServletRequest request, HttpServletResponse response) {		
		List musicPlayList = new ArrayList();
		HttpSession session = request.getSession();
		MusicDao dao = new MusicDao();
		int userNum = (int)session.getAttribute("userNum");
		musicPlayList = dao.PlayList(userNum);
				
		List<String> musicPathList = new ArrayList<String>();
		for (int i = 0; i < musicPlayList.size(); i++ )
		{
			MusicBean bean = (MusicBean)musicPlayList.get(i);
			musicPathList.add(Integer.toString(bean.getMusicNum()));
		}
		
		request.setAttribute("musicPlayList", musicPlayList);
		request.setAttribute("musicPathList", musicPathList);
	}
}