package de.zeroco.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
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
		String phno = req.getParameter("phno");
		String dob = req.getParameter("dob");
		String password = req.getParameter("password");
		PrintWriter pw = res.getWriter();
		if (RegistrationDao.checkEmailPhno(email, phno)) { 
			pw.println("user email or phone number already exist");
			res.setContentType("text/html");
			RequestDispatcher rs = req.getRequestDispatcher("Registration.html");
			rs.include(req, res);
		} else {
			int id = RegistrationService.getInsertedId(name, email, phno, dob, password);
			pw.println("data added to database and id : " + id + " for user : " + name);
			res.setContentType("text/html");
			RequestDispatcher rs = req.getRequestDispatcher("Registration.html");
			rs.include(req, res);
		}
	}
}