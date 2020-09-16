package Service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.ServiceCenterBean;
import Dao.ServiceCenterDao;

public class ServiceContextService implements Service {
	public void Excute(HttpServletRequest request, HttpServletResponse response) {
		ServiceCenterDao dao = new ServiceCenterDao();
		ServiceCenterBean bean = new ServiceCenterBean();
		
		int num = Integer.parseInt(request.getParameter("num"));
		bean = dao.getContext(num);
		
		request.setAttribute("serviceContext", bean);
	}
}
