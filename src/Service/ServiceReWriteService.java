package Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.ServiceCenterBean;
import Dao.ServiceCenterDao;

public class ServiceReWriteService implements Service {
	public void Excute(HttpServletRequest request, HttpServletResponse response) {
		ServiceCenterDao dao = new ServiceCenterDao();
		ServiceCenterBean bean = new ServiceCenterBean();
		
		bean.setServicecenterName(request.getParameter("name"));
		bean.setServicecenterTitle(request.getParameter("serviceTitle"));
		bean.setServicecenterContents(request.getParameter("serviceContents"));
		bean.setServicecenterReNum(Integer.parseInt(request.getParameter("num")));
		bean.setServicecenterReCheck(1);
		
		dao.ServiceReply(bean);
	}
}
