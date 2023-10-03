package loja.negocio;

public class Produto {

	public int id;
	
	private String nome;
	
	private String desc;

	private double custo;
	
	private int qtd_estq;
	
	private double valor;
	
	public Produto(int id, String nome, String desc, double custo, int qtd_estq, double valor) {
		this.id = id;
		this.nome = nome;
		this.desc = desc;
		this.custo = custo;
		this.qtd_estq = qtd_estq;
		this.valor = valor;
	}

	public Produto(String nome, String desc, int qtd_estq, double custo,  double valor) {
		this.nome = nome;
		this.desc = desc;
		this.custo = custo;
		this.qtd_estq = qtd_estq;
		this.valor = valor;
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

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public double getCusto() {
		return custo;
	}

	public void setCusto(double custo) {
		this.custo = custo;
	}

	public int getQtd_estq() {
		return qtd_estq;
	}

	public void setQtd_estq(int qtd_estq) {
		this.qtd_estq = qtd_estq;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
	
	
	
}
