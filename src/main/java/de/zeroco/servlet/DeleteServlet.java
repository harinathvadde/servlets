package de.zeroco.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.zeroco.service.RegistrationService;

public class DeleteServlet extends HttpServlet {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		String email = req.getParameter("email");
		String output = RegistrationService.deleteData(email);
		PrintWriter pw = res.getWriter();
		pw.println(output);
		res.setContentType("text/html");
		RequestDispatcher rs = req.getRequestDispatcher("Delete.html");
		rs.include(req, res);
	} 
}
