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
		String email = req.getParameter("email");
		List<Map<String, Object>> output = RegistrationService.get(email);
		PrintWriter pw = res.getWriter();
		for (Map<String, Object> map : output) {
			pw.println(map);
		}
	}
}
