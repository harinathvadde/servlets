package de.zeroco.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.zeroco.service.RegistrationService;

public class DeleteServlet extends HttpServlet {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String userId = req.getParameter("id");
		String output = RegistrationService.deleteData(userId);
		PrintWriter pw = res.getWriter();
		pw.println(output);
	} 
}
