package de.zeroco.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.zeroco.service.RegistrationService;

public class RegistrationServlet extends HttpServlet{

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
			String name = req.getParameter("name");
			String email = req.getParameter("email");
			String phno = req.getParameter("phno");
			String dob = req.getParameter("dob");
			int id = RegistrationService.getInsertedId(name, email, phno, dob);
			PrintWriter pw = res.getWriter();
			pw.println("data added to database and id : " + id );
		}
		
	
}