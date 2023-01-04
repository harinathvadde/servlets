package de.zeroco.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
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
		String pass = req.getParameter("pass");
		PrintWriter out = res.getWriter();
		if (RegistrationDao.logIn(email, pass)) {
			RequestDispatcher rs = req.getRequestDispatcher("Welcome");
			rs.forward(req, res);
		} else {
			out.println("Username or Password incorrect");
			res.setContentType("text/html");
			RequestDispatcher rs = req.getRequestDispatcher("Login.html");
			rs.include(req, res);
		}
	}

}
