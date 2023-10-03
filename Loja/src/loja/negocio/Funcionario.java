package loja.negocio;

public class Funcionario {
	private int id;
	
	private String nome;
	
	private String cargo;
	
	private double salario;

	public String telefone;
	
	public String email;

	public String endereco;
	
	
	public Funcionario(int id, String nome, String cargo, double salario, String telefone, String email, String endereco) {
		this.id = id;
		this.nome = nome;
		this.cargo = cargo;
		this.salario = salario;
		this.telefone = telefone;
		this.email = email;
		this.endereco = endereco;
	}
	
	public Funcionario(String nome, String cargo, double salario, String telefone, String email, String endereco) {
		this.nome = nome;
		this.cargo = cargo;
		this.salario = salario;
		this.telefone = telefone;
		this.email = email;
		this.endereco = endereco;
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

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
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

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	
	
}
