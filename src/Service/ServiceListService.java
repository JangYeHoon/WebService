package Service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.ServiceCenterDao;

public class ServiceListService implements Service {
	public void Excute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		ServiceCenterDao dao = new ServiceCenterDao();
		List serviceList = new ArrayList();
		String userId = (String)session.getAttribute("userId");
		
		if (userId.equals("admin")) {
			serviceList = dao.getServiceListAdmin();
		} else {
			serviceList = dao.getServiceList(userId);
		}
		
		request.setAttribute("serviceList", serviceList);
	}
}
