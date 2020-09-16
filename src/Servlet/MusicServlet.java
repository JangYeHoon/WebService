package Servlet;

import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import Dao.RecommendDao;
import Service.*;

@WebServlet("/music")
public class MusicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("EUC-KR");
		String task = request.getParameter("task");
		String path = "";
		Service service = null;

		// 음악 플레이리스트
		if (task.equals("playMusic")) {
			// 플레이리스트에 추가
			service = new InsertMusicService();
			service.Excute(request, response);

			// 플레이리스트 받아오는 부분
			service = new PlayListService();
			service.Excute(request, response);

			// 추천 기능
			service = new recommendMusicService();
			service.Excute(request, response);

			path = "musicList.jsp";
		} else if (task.equals("musicRank")) {
			// 음악 랭킹 리스트 받아오는 부분
			service = new MusicRankService();
			service.Excute(request, response);

			path = "loginedHome.jsp";
		} else if (task.equals("musicVideo")) {
			// 뮤직비디오 추천 리스트 받아오는 부분
			service = new VideoRecommendService();
			service.Excute(request, response);

			path = "musicVideo.jsp";
		} else if (task.equals("VideoPlay")) {
			// 뮤직비디오 경로 받아오는 부분
			service = new VideoPlayService();
			service.Excute(request, response);

			path = "playVideo.jsp";
		} else if (task.equals("mainHome")) {
			// 처음 페이지에 필요한 정보 가져오는 부분
			service = new VideoRecommendService();
			service.Excute(request, response);

			service = new MusicRankService();
			service.Excute(request, response);
			
			service = new BoardListService();
	        service.Excute(request, response);
			path = "loginedHome.jsp";
		} else if (task.equals("addMusic")) {
			// 플레이리스트에 음악 추가하는 부분
			service = new ListInsertService();
			service.Excute(request, response);
			
			path = "./music?task=mainHome";
		} else if (task.equals("deleteMusic")) {
			service = new deleteService();
			service.Excute(request, response);

			service = new PlayListService();
			service.Excute(request, response);

			service = new recommendMusicService();
			service.Excute(request, response);

			path = "musicList.jsp";
		} else if (task.equals("likeMusic")) {
			service = new MusicLikeService();
			service.Excute(request, response);
			
			path = "music?task=mainHome";
		} else if (task.equals("goplayList")){
			service = new MusicPlayService();
			service.Excute(request, response);
			
			service = new PlayListService();
			service.Excute(request, response);

			service = new recommendMusicService();
			service.Excute(request, response);

			path = "musicList.jsp";
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
	}
}