package de.zeroco.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.zeroco.dao.RegistrationDao;
import de.zeroco.service.RegistrationService;

public class DeleteServlet extends HttpServlet {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		String email = req.getParameter("email");
		if (RegistrationDao.checkEmailPhno(email, null)) {
			String output = RegistrationService.deleteData(email);
			req.setAttribute("delete", output);
			req.getRequestDispatcher("Delete.jsp").forward(req, res);
		} else {
			req.setAttribute("delete", "No..Records Found..!");
			req.getRequestDispatcher("Delete.jsp").forward(req, res);
		}
		
	} 
}
