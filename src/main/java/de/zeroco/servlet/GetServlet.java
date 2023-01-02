package de.zeroco.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.zeroco.service.RegistrationService;

public class GetServlet extends HttpServlet {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String userId = req.getParameter("id");
		List<Map<String, Object>> output = RegistrationService.get(userId);
		PrintWriter pw = res.getWriter();
		pw.println(output);
	}
}
