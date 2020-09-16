package Service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Dao.MusicDao;

public class MusicRankService implements Service {
	public void Excute(HttpServletRequest request, HttpServletResponse response) {		
		List musiclist = new ArrayList();
		MusicDao dao = new MusicDao();
		musiclist = dao.MusicRank();
		
		request.setAttribute("musicRank", musiclist);
	}
}