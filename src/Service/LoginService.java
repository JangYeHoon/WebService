package Service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.UserDao;
import Dao.UserBean;

public class LoginService implements Service {
	public void Excute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		int result = -1;
		
		UserDao dao = new UserDao();
		UserBean bean = new UserBean();
		
		bean.setUserId(request.getParameter("userId"));
		bean.setUserPW(request.getParameter("userPW"));
		result = dao.UserLogin(bean);
		
		if (result == 0) {
			response.setContentType("text/html; charset=euc-kr");
			PrintWriter out;
			try {
				out = response.getWriter();
				out.println("<script>");
				out.println("alert('��й�ȣ�� ��ġ���� �ʽ��ϴ�.');");
				out.println("location.href='mainHome.jsp';");
				out.println("</script>");
				out.close();
			} catch (IOException e) {}
		} else if (result == -1) {
			response.setContentType("text/html; charset=euc-kr");
			PrintWriter out;
			try {
				out = response.getWriter();
				out.println("<script>");
				out.println("alert('���̵� �������� �ʽ��ϴ�..');");
				out.println("location.href='mainHome.jsp';");
				out.println("</script>");
				out.close();
			} catch (IOException e) {}
		} else if (result == 1) {
			session.setAttribute("userId", bean.getUserId());
			session.setAttribute("userNum", bean.getUserNum());
		}
	}
}
