package hello;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PrimeiroServlet extends HttpServlet{
	private String nome = "Felipe";
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String mensagem= "Nome Anterior: "+ nome;
		nome = "Tiago";
		mensagem += " Novo Nome: "+nome;
		mensagem+="\nEmail de :"+getServletConfig().getInitParameter("emailDe");
		
		resp.getWriter().print(mensagem);
		
	}

}
