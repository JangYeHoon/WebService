package Service;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.MultipartRequest;

import Dao.UserBean;
import Dao.UserDao;

public class JoinService implements Service {

	public void Excute(HttpServletRequest request, HttpServletResponse response) {
		UserDao dao = new UserDao();
		UserBean bean = new UserBean();
		try {
			bean.setUserId(request.getParameter("userId"));
			bean.setUserPW(request.getParameter("userPW"));
			bean.setUserPhone(request.getParameter("userPhone"));
			bean.setUserName(request.getParameter("userName"));
		} catch ( Exception e) {e.printStackTrace();}
		dao.UserJoin(bean);
	}
}
