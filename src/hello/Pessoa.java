package hello;

public class Pessoa {
	
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	public static void main(String[] args) {
		Pessoa p = new Pessoa();
		p.setNome("Jo�o");
		
		Pessoa p2 = new Pessoa();
		p2.setNome("Jo�o");
		
		System.out.println(p == p2);
		System.out.println(p.equals(p2));
	}

}
