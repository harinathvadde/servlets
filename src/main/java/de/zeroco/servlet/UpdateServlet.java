package de.zeroco.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.zeroco.dao.RegistrationDao;
import de.zeroco.service.RegistrationService;

public class UpdateServlet extends HttpServlet {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		String email = req.getParameter("email");
		String name = req.getParameter("name");
		String newEmail = req.getParameter("newemail");
		String phone = req.getParameter("phone");
		String dob = req.getParameter("dob");
		String pass = req.getParameter("password");
		if (RegistrationDao.checkEmailPhno(email, null)) {
			String output = RegistrationService.updateData(name, newEmail, phone, dob, pass, email);
			req.setAttribute("update",email + " data " + output);
			req.getRequestDispatcher("Update.jsp").forward(req, res);
		} else {
			req.setAttribute("update","No records found..!");
			req.getRequestDispatcher("Update.jsp").forward(req, res);
		}
		
	}
}
