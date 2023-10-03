package loja.negocio;

public class Pedido {
	
	
		private int id;
		private String funcionario;
		private String cliente;
		private String dataInicial;
		private String dataFinal;		
		private double valorTotal;
		private String status;
		
		
		public Pedido(int id, String funcionario, String cliente, String dataInicial2, String dataFinal2,
				double valorTotal, String status) {
			super();
			this.id = id;
			this.funcionario = funcionario;
			this.cliente = cliente;
			this.dataInicial = dataInicial2;
			this.dataFinal = dataFinal2;
			this.valorTotal = valorTotal;
			this.status = status;
		}
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getFuncionario() {
			return funcionario;
		}
		public void setFuncionario(String funcionario) {
			this.funcionario = funcionario;
		}
		public String getCliente() {
			return cliente;
		}
		public void setCliente(String cliente) {
			this.cliente = cliente;
		}
		public String getDataInicial() {
			return dataInicial;
		}
		public void setDataInicial(String dataInicial) {
			this.dataInicial = dataInicial;
		}
		public String getDataFinal() {
			return dataFinal;
		}
		public void setDataFinal(String dataFinal) {
			this.dataFinal = dataFinal;
		}
		public double getValorTotal() {
			return valorTotal;
		}
		public void setValorTotal(double valorTotal) {
			this.valorTotal = valorTotal;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		
	

		
		
}
