package hello;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static hello.util.PageUtil.*;

@WebServlet({ "/contatos", "/contato/novo", "/contato/editar", "/contato/salvar", "/contato/remover" })
public class ContatoControle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		executar(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void executar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getContextPath();
		String uri = request.getRequestURI();

		if (uri.equals(path + "/contatos")) {
			listar(request, response);
		} else if (uri.equals(path + "/contato/novo")) {
			// redireciono para o formulário de novo contato
			novo(request, response);
		} else if (uri.equals(path + "/contato/editar")) {
			// redireciono para o formulário de editar contato
			editar(request, response);
		} else if (uri.equals(path + "/contato/salvar")) {
			// salva o contato e redireciona para a lista de contatos
			salvar(request, response);
		} else if (uri.equals(path + "/contato/remover")) {
			// remove o contato e redireciona para a lista de contatos
			remover(request, response);
		}
	}

	// #########################################################################
	// AÇÕES
	// #########################################################################
	private void listar(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		response.getWriter().println(montarPaginaListar(ContatoRepositorio.buscarTodos()));

	}

	private void novo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("novo.html");
		dispatcher.forward(request, response);

	}

	private void editar(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String sId = request.getParameter("id");
		Long id = sId != null && !"".equals(sId) ? Long.valueOf(sId) : null;
		Contato contato = ContatoRepositorio.buscarPorId(id);
		if (contato != null) {
			montarFormularioEditar(contato, response);
		} else {
			mostrarPaginaContatoNaoEncontrado(response);
		}
	}

	private void salvar(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String sId = request.getParameter("id");
		Long id = sId != null && !"".equals(sId) ? Long.valueOf(sId) : null;
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String telefone = request.getParameter("telefone");
		Contato contato = new Contato(id, nome, email, telefone);
		ContatoRepositorio.salvar(contato);
		response.sendRedirect(request.getContextPath() + "/contatos");

	}

	private void remover(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String sId = request.getParameter("id");
		Long id = sId != null && !"".equals(sId) ? Long.valueOf(sId) : null;
		Contato contato = ContatoRepositorio.buscarPorId(id);
		ContatoRepositorio.remover(contato);
		response.sendRedirect(request.getContextPath() + "/contatos");

	}

	// #########################################################################
	// MÉTODOS UTILITÁRIOS
	// #########################################################################
	private String montarPaginaListar(List<Contato> contatos) {
		StringBuilder pagina = new StringBuilder();
		pagina.append(addDoctype()).append(abrirHtml()).append(criarHead("Lista Contatos")).append(abrirBody())
				.append(adicionarHeader("Lista de Contatos"))//
				.append(adicionarLinkNovo()).append(adicionarTabelaContatos(contatos)).append(fecharBody())
				.append(fecharHTML());
		return pagina.toString();
	}

	private String adicionarTabelaContatos(List<Contato> contatos) {
		StringBuilder tabela = new StringBuilder();
		tabela.append(abrirTabela()).append(criarTHead("Nome", "Email", "Telefone", "Ações")).append(abrirTBody());
		for (Contato contato : contatos) {
			tabela.append(criarLinha(contato));
		}
		tabela.append(fecharTBody()).append(fecharTabela());
		return tabela.toString();
	}

	private String criarLinha(Contato contato) {
		return String.format(
				"<tr><td>%s</td><td>%s</td><td>%s</td><td><a href=\"%s\">Editar</a>/<a href=\"%s\">Remover</a></td>",
				contato.getNome(), contato.getEmail(), contato.getTelefone(), "contato/editar?id=" + contato.getId(),
				"contato/remover?id=" + contato.getId());
	}

	private void mostrarPaginaContatoNaoEncontrado(HttpServletResponse response) throws IOException {
		StringBuilder pagina = new StringBuilder();
		pagina.append(addDoctype()).append(abrirHtml()).append(criarHead("Contato não encontrado")).append(abrirBody())
				.append(adicionarHeader("Contato não encontado")).append(fecharBody()).append(fecharHTML());
		response.setContentType("text/html");
		response.getWriter().println(pagina.toString());
	}

	private void montarFormularioEditar(Contato contato, HttpServletResponse response) throws IOException {
		StringBuilder pagina = new StringBuilder();
		pagina.append(addDoctype()).append(abrirHtml()).append(criarHead("Editar Contato")).append(abrirBody())
				.append(adicionarHeader("Editar Contato"))//
				.append(abrirFormulario("salvar", "post"))
				.append(adicionarInput("hidden", contato.getId().toString(), "id")).append(linha())
				.append(label("Nome")).append(linha())//
				.append(adicionarInput("text", contato.getNome(), "nome")).append(linha()).append(label("Email"))
				.append(linha()).append(adicionarInput("text", contato.getEmail(), "email"))//
				.append(linha()).append(label("Telefone")).append(linha())
				.append(adicionarInput("text", contato.getTelefone(), "telefone"))//
				.append(linha()).append(linha()).append(adicionarInputSubmit("Salvar")).append(fecharFormulario())//
				.append(fecharBody()).append(fecharHTML());

		response.setContentType("text/html");
		response.getWriter().println(pagina.toString());

	}

}
