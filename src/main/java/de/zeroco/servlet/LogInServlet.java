package de.zeroco.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.zeroco.dao.RegistrationDao;

public class LogInServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		if (RegistrationDao.logIn(email, password)) {
			req.setAttribute("msg", "");
			req.getRequestDispatcher("Welcome.jsp").forward(req, res);
		} else {
			req.setAttribute("msg", "Login Failed..! User Email or Phone Number incorrect");
			req.getRequestDispatcher("Login.jsp").forward(req, res);
		}
	}
}
