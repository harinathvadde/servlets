package de.zeroco.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		
		String email = req.getParameter("email");
		String pass = req.getParameter("pass");
		
		if(Validation.checkUser(email, pass))
        {
            RequestDispatcher rs = req.getRequestDispatcher("Welcome");
            rs.forward(req, res);
        }
        else
        {
           out.println("Username or Password incorrect");
           RequestDispatcher rs = req.getRequestDispatcher("index.html");
           rs.include(req, res);
        }
	}
	
}
