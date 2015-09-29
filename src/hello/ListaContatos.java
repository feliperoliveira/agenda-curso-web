package hello;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListaContatos extends HttpServlet{
	

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		RequestDispatcher requestDispatcher = req.getRequestDispatcher("index.html");
//		requestDispatcher.forward(req, resp);
		resp.sendRedirect(req.getContextPath()+"/index.html");
		
	}
}
