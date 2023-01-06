package de.zeroco.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.zeroco.dao.RegistrationDao;
import de.zeroco.service.RegistrationService;

public class GetServlet extends HttpServlet {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		String email = req.getParameter("email");
		if (RegistrationDao.checkEmailPhno(email, null)) {
			List<Map<String, Object>> userList = RegistrationService.get(email);
			req.setAttribute("data", userList);
			req.getRequestDispatcher("GetOutput.jsp").forward(req, res);
		} else {
			req.setAttribute("msg", "No records found..!");
			req.getRequestDispatcher("Get.jsp").forward(req, res);
		}
	}
}