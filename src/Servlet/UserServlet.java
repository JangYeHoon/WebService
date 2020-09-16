package Servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Service.*;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Process(request, response);
	}

	protected void Process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("EUC-KR");
		String task = request.getParameter("task");
		String path = "";
		Service service = null;
		
		if ( task != null )
		{
			if (task.equals("login")) {
				service = new LoginService();
				service.Excute(request, response);
				
				path = "mainHome.jsp";
			} else if (task.equals("logout")) {
				HttpSession session = request.getSession();
				session.invalidate();
				
				path = "mainHome.jsp";
			}
		}
		else {
			service = new JoinService();
			service.Excute(request, response);
			
			path = "mainHome.jsp";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
	}
}
