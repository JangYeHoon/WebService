package Service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.MusicDao;

public class VideoRecommendService implements Service {
	public void Excute(HttpServletRequest request, HttpServletResponse response) {
		List videoRecommend = new ArrayList();
		MusicDao dao = new MusicDao();
		videoRecommend = dao.videoRecommend();
		
		request.setAttribute("videoRecommend", videoRecommend);
	}
}
