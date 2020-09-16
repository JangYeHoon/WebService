package Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.ServiceCenterBean;
import Dao.ServiceCenterDao;

public class ServiceWriteService implements Service {
	public void Excute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		ServiceCenterBean bean = new ServiceCenterBean();
		ServiceCenterDao dao = new ServiceCenterDao();
		
		String userId = (String)session.getAttribute("userId");
		bean.setServicecenterName(userId);
		bean.setServicecenterTitle(request.getParameter("serviceTitle"));
		bean.setServicecenterContents(request.getParameter("serviceContents"));
		
		dao.ServiceWrite(bean);
	}
}
