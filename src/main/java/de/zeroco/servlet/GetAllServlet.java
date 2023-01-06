package de.zeroco.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.zeroco.service.RegistrationService;

public class GetAllServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		List<Map<String, Object>> listOfUsers = RegistrationService.getAll();
		req.setAttribute("data", listOfUsers);
		req.getRequestDispatcher("GetOutput.jsp").forward(req, res);	
	}
}
