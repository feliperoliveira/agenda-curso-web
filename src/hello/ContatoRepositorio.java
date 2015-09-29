package hello;

import java.util.ArrayList;
import java.util.List;

public class ContatoRepositorio {
	
	private static List<Contato> lista = new ArrayList<Contato>();
	
	public static void salvar(Contato contato){
		if(contato.getId() == null){
			contato.setId(System.currentTimeMillis());
		}
		int indexOf = lista.indexOf(contato);
		if(indexOf > -1){
			lista.set(indexOf, contato);
		}else{
			lista.add(contato);
		}
	}
	
	public static List<Contato> buscarTodos(){
		return lista;
	}
	
	public static Contato buscarPorId(Long id){
		for (Contato contato : lista) {
			if(id.equals(contato.getId())){
				return contato;
			}
		}
		return null;
	}
	
	public static void remover(Contato contato){
		if(contato == null){
			return;
		}
		lista.remove(contato);
	}

}
