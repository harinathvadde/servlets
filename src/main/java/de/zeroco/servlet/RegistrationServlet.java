package de.zeroco.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.zeroco.dao.RegistrationDao;
import de.zeroco.service.RegistrationService;

public class RegistrationServlet extends HttpServlet {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String phone = req.getParameter("phone");
		String dob = req.getParameter("dob");
		String password = req.getParameter("password");
		if (RegistrationDao.checkEmailPhno(email, phone)) { 
			req.setAttribute("insertFailed", "Registration Failed..! Email or Phone number already exist.");
			req.getRequestDispatcher("Registration.jsp").forward(req, res);
		} else {
			int id = RegistrationService.getInsertedId(name, email, phone, dob, password);
			req.setAttribute("insertSuccess", "data added to database and id : " + id + " for user : " + name);
			req.getRequestDispatcher("Registration.jsp").forward(req, res);
		}
	}
}