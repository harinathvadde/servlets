package de.zeroco.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddServlet
 */
@WebServlet("/AddServlet")
public class AddServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
		int numOne = Integer.parseInt(req.getParameter("num1"));
		int numTwo = Integer.parseInt(req.getParameter("num2"));
		int result = numOne + numTwo;
		PrintWriter pw = res.getWriter();
		pw.println("<h1>result :" + result +"</h1>");
	}
}
