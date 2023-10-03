package loja.negocio;

public class Cliente {
	
	public int id;
	
	public String nome;
	
	public String telefone;
	
	public String email;

	public String endereco;
	
	public int codfunc;

	

	public Cliente(int id, String nome, String telefone, String email, String endereco, int codfunc) {
		this.id = id;
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.endereco = endereco;
		this.codfunc = codfunc;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
