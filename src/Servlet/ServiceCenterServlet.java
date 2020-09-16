package Servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.IOP.ServiceContext;

import Service.*;

/**
 * Servlet implementation class ServiceCenterServlet
 */
@WebServlet("/ServiceCenterServlet")
public class ServiceCenterServlet extends HttpServlet {
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
		
		if (task.equals("serviceWrite")) {
			service = new ServiceWriteService();
			service.Excute(request, response);
			
			path = "./ServiceCenterServlet?task=serviceList";
		} else if (task.equals("serviceList")) {
			service = new ServiceListService();
			service.Excute(request, response);
			
			path = "serviceList.jsp";
		} else if (task.equals("serviceContext")) {
			service = new ServiceContextService();
			service.Excute(request, response);
			
			path = "serviceContext.jsp";
		} else if (task.equals("serviceReWrite")) {
			service = new ServiceReWriteService();
			service.Excute(request, response);
			
			path = "./ServiceCenterServlet?task=serviceList";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
	}
}
