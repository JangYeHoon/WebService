package Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Service {
	void Excute(HttpServletRequest request, HttpServletResponse response);
}