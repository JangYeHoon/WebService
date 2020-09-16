package Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Service.BoardChangeService;
import Service.BoardChangeViewService;
import Service.BoardDeleteService;
import Service.BoardViewService;
import Service.BoardWriteService;
import Service.CommentListService;
import Service.CommentWriteService;
import Service.Service;
import Service.deleteCommentService;

/**
 * Servlet implementation class BoardServlet
 */
@WebServlet("/BoardServlet")
public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("EUC-KR");
		String task = request.getParameter("task");
		String path = "";
		Service service = null;
	
		if (task.equals("BoardView")) {
			service = new BoardViewService();
			service.Excute(request, response);
			
			service = new CommentListService();
			service.Excute(request, response);
			
			path = "Board_View.jsp";
		
		} else if (task.equals("BoardWrite")) {
			service = new BoardWriteService();
			service.Excute(request, response);
			
			response.setContentType("text/html; charset=EUC-KR");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('작성되었습니다.');");
			out.println("location.href='./music?task=mainHome';");
			out.println("</script>");
			out.close();
			return;
		} else if (task.equals("BoardChange")) {
			service = new BoardChangeViewService();
			service.Excute(request, response);
			
			path = "Board_Change.jsp";
		} else if (task.equals("BoardChangeFinish")) {
			service = new BoardChangeService();
			service.Excute(request, response);
			
			response.setContentType("text/html; charset=EUC-KR");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('수정하였습니다.');");
			out.println("location.href='./music?task=mainHome';");
			out.println("</script>");
			out.close();
			return;
		} else if (task.equals("BoardDelete")) {
			service = new BoardDeleteService();
			service.Excute(request, response);
			
			response.setContentType("text/html; charset=EUC-KR");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('삭제하였습니다.');");
			out.println("location.href='./music?task=mainHome#fragment-3';");
			out.println("</script>");
			out.close();
			return;
		} else if (task.equals("CommentWrite")) {
			service = new CommentWriteService();
			service.Excute(request, response);
			
			service = new BoardViewService();
			service.Excute(request, response);
			
			service = new CommentListService();
			service.Excute(request, response);
			
			path = "Board_View.jsp";
		} else if (task.equals("deleteComment")) {
			service = new deleteCommentService();
			service.Excute(request, response);
			
			service = new BoardViewService();
			service.Excute(request, response);
			
			service = new CommentListService();
			service.Excute(request, response);
			
			path = "Board_View.jsp";
		} 
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
	}
}
