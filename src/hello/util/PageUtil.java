package hello.util;

public class PageUtil {
	
	public static String addDoctype() {
		return "<!doctype html>";
	}
	public static String abrirHtml() {
		return "<html>";
	}

	public static String criarHead(String titulo) {
		return "<head><title>"+titulo+"</title></head>";
	}

	public static String abrirBody() {
		return "<body>";
	}

	public static String adicionarHeader(String titulo) {
		
		return "<h1>"+titulo+"</h1>";
	}

	public static String adicionarLinkNovo() {
		return "<a href=\"contato/novo\">Novo Contato</a><br/>";
	}
	
	public static String abrirTabela() {
		return "<table>";
	}

	public static String criarTHead(String... itens)  {
		StringBuilder thead = new StringBuilder();
		thead.append("<thead>").append("<tr>");
		for (String item : itens) {
			thead.append("<th>").append(item).append("</th>");
		}
		thead.append("</tr>").append("</thead>");
		return thead.toString();
	}

	public static String abrirTBody() {
		return "<tbody>";
	}
	
	public static String fecharTBody() {
		return "</tbody>";
	}

	public static String fecharTabela() {
		return "</table>";
	}

	public static String fecharBody() {
		return "</body>";
	}

	public static String fecharHTML() {
		return "</html>";
	}
	
	public static String fecharFormulario() {
		return "</form>";
	}

	public static String abrirFormulario(String action, String method) {
		return "<form action=\""+action+"\" method=\""+method+"\">";
	}

	public static Object label(String label) {
		return "<label>"+label+"</label>";
	}

	public static String linha() {
		return "<br/>";
	}

	public static String adicionarInput(String type, String value, String name) {
		// TODO Auto-generated method stub
		return "<input type=\""+type+"\" value=\""+value+"\" name=\""+name+"\">";
	}

	public static String adicionarInputSubmit(String titulo) {
		return adicionarInput("submit", titulo,"salvar");
	}


}
