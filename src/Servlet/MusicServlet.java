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

		// ���� �÷��̸���Ʈ
		if (task.equals("playMusic")) {
			// �÷��̸���Ʈ�� �߰�
			service = new InsertMusicService();
			service.Excute(request, response);

			// �÷��̸���Ʈ �޾ƿ��� �κ�
			service = new PlayListService();
			service.Excute(request, response);

			// ��õ ���
			service = new recommendMusicService();
			service.Excute(request, response);

			path = "musicList.jsp";
		} else if (task.equals("musicRank")) {
			// ���� ��ŷ ����Ʈ �޾ƿ��� �κ�
			service = new MusicRankService();
			service.Excute(request, response);

			path = "loginedHome.jsp";
		} else if (task.equals("musicVideo")) {
			// �������� ��õ ����Ʈ �޾ƿ��� �κ�
			service = new VideoRecommendService();
			service.Excute(request, response);

			path = "musicVideo.jsp";
		} else if (task.equals("VideoPlay")) {
			// �������� ��� �޾ƿ��� �κ�
			service = new VideoPlayService();
			service.Excute(request, response);

			path = "playVideo.jsp";
		} else if (task.equals("mainHome")) {
			// ó�� �������� �ʿ��� ���� �������� �κ�
			service = new VideoRecommendService();
			service.Excute(request, response);

			service = new MusicRankService();
			service.Excute(request, response);
			
			service = new BoardListService();
	        service.Excute(request, response);
			path = "loginedHome.jsp";
		} else if (task.equals("addMusic")) {
			// �÷��̸���Ʈ�� ���� �߰��ϴ� �κ�
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