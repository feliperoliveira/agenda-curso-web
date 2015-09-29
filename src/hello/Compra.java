package hello;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Compra extends HttpServlet{
	

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cartao = req.getParameter("cartao");
		String[] parameterValues = req.getParameterValues("item");
		System.out.println("#########Items incluidos###########");
		for (String item : parameterValues) {
			System.out.println(item);
		}
		System.out.println("###############################3");
		Enumeration<String> parameterNames = req.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String parameter = (String) parameterNames.nextElement();
			System.out.println("Nome do parametro = "+parameter);
		}
		
		Enumeration<String> headerNames = req.getHeaderNames();
		System.out.println("#############Informações do Header");
		while (headerNames.hasMoreElements()) {
			String key = headerNames.nextElement();
			System.out.println(key);
			System.out.println(req.getHeader(key));
			
		}
		System.out.println("###########################################");
		resp.setHeader("meuHeader", "testando 123");
		
		String email = getServletConfig().getInitParameter("emailDe");
		System.out.println("==>Tenho que mandar um email de "+ email);
		
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("compra.html");
		requestDispatcher.forward(req, resp);
	}
}
