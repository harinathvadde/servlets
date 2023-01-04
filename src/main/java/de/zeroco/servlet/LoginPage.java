package de.zeroco.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginPage extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String userName = req.getParameter("email");
		String password = req.getParameter("pass");
		PrintWriter pw = res.getWriter();
		if (userName.equalsIgnoreCase("harinath46@gmail.com")) {
			if (password.equalsIgnoreCase("harinath@123")) {
				pw.println("<h1> !!..WELCOME TO HARINATH HOME PAGE..!!</h1>");
			} else {
				pw.println("<h1> entered wront password");
			}
		} else {
			pw.println("<h1> entered wront username");
		}
	}
}
