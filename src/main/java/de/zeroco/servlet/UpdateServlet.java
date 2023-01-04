package de.zeroco.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.zeroco.service.RegistrationService;

public class UpdateServlet extends HttpServlet {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String phno = req.getParameter("phno");
		String dob = req.getParameter("dob");
		String pass = req.getParameter("pass");
		String id = req.getParameter("id");
		String output = RegistrationService.updateData(name, email, phno, dob, pass, id);
		PrintWriter pw = res.getWriter();
		pw.println("data added to database : " + id + " " + output );
		res.setContentType("text/html");
		RequestDispatcher rs = req.getRequestDispatcher("Update.html");
		rs.include(req, res);
	}
}
