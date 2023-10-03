package loja.persistencia;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import loja.negocio.Cliente;
import loja.negocio.Funcionario;
import loja.negocio.Pedido;
import loja.negocio.Produto;
import loja.util.LojaUtil;

public class DAOLoja {

	public static Connection getConnection() throws SQLException {
		// para realizar a conexao preciso de tres informa��es.
		String url = LojaUtil.get("url");
		/*
		 * vinculado ao banco de dados, se eu colocar fixo, vou vincular a
		 * camada ao MySQL, para se tornar independente vou ter que dar um jeito
		 * de parametrizar, como usando um arquivo de confiura��o, lido pelo
		 * EstacionamentoUtil;
		 */
		String usuario = LojaUtil.get("usuario");
		String senha = LojaUtil.get("senha");

		Connection conexao = DriverManager.getConnection(url, usuario, senha);
		/*
		 * passando a instancia de conexao, quem faz isso e o driver manager.
		 */
		return conexao;
	}
	
	public static void closeConnection(Connection conexao) {
		if (conexao != null) {
			try {
				conexao.close();
			} catch (SQLException e) {
				// s� um alerta no console se n�o conseguir fechar a conex�o.
				e.printStackTrace();
			}
		}
	}
	
	public static Funcionario validarLogin(String usuario, String senha) {
		Connection conexao = null;
		//pega o funcionario passando usuario e senha.
		String cmd = LojaUtil.get("autenticar.funcionario");
		
		Funcionario funcionario = null;
		
		try {
			conexao = getConnection();
			PreparedStatement ps = conexao.prepareStatement(cmd);
				ps.setString(1, usuario);
				ps.setString(2, senha);
				
				ResultSet result = ps.executeQuery();
				//só tem um registro, pega todas as colunas dele.
				if(result.next()) {
					int id = result.getInt("IdFuncionario");
					String nome = result.getString("Nome");
					String cargo = result.getString("Cargo");
					double salario = result.getInt("Salario");
					String telefone = result.getString("Telefone");
					String email = result.getString("Email");
					String endereco = result.getString("Endereco");
					
					funcionario = new Funcionario(id, nome, cargo, salario, telefone, email, endereco);
					
					return funcionario;
				}	
				
				
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection(conexao);
		}
		return null;
	}
	
	public static Boolean validarAdmin(String usuario, String senha) {
		Connection conexao = null;
		String admin = LojaUtil.get("autenticar.admin");
		
		try {
			conexao = getConnection();
			PreparedStatement ps = conexao.prepareStatement(admin);
				ps.setString(1, usuario);
				ps.setString(2, senha);
				
				ResultSet result = ps.executeQuery();
				if(result.next()) {
					String nome = result.getString(1);
					if(nome != null) {
						return true;
					}
				}	
				
				
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection(conexao);
			
		}
		return false;
	}
	
	
	public static int iniciarSessao(Funcionario funcionario) {
		Connection conexao = null;
		
		String cmd = LojaUtil.get("historico.login.iniciar");
		int idLogAcesso = 0;
		
		
		try {
			conexao = getConnection();
			CallableStatement cstmt = conexao.prepareCall(cmd);
			cstmt.setInt(1, funcionario.getId());
			cstmt.setString(2, funcionario.getNome());
			cstmt.registerOutParameter("idSessao", java.sql.Types.INTEGER);//é um inteiro e vou armazenar na variavel id log acesso.
		
			cstmt.execute();
			
			idLogAcesso = cstmt.getInt("idSessao");
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection(conexao);
		}
		System.out.println("ID da sessão = " + idLogAcesso);
		return idLogAcesso;
	}

	public static void fecharSessao(int idSessao) {
		Connection conexao = null;
		
		String cmd = LojaUtil.get("historico.login.fechar");
	
		try {
			conexao = getConnection();
			CallableStatement cstmt = conexao.prepareCall(cmd);
			cstmt.setInt(1, idSessao);
	
			cstmt.execute();
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection(conexao);
		}		
	}

	/**
	 * Cadastra o cliente no banco de dados ultilizando as informações passadas.
	 * 
	 * 
	 * @param nome Nome do cliente
	 * @param email Email do cliente
	 * @param telefone Telefone do cliente (Opcional)
	 * @param endereco Endereco do cliente
	 * @param codFunc Funcionario que cadastrou o cliente
	 */
	public static void cadastrarCliente(String nome, String email, String telefone, String endereco, int codFunc) {
		Connection conexao = null;
			
		String cmd = LojaUtil.get("cadastro.cliente");
		
		try {
			conexao = getConnection();
			PreparedStatement ps = conexao.prepareStatement(cmd);
			ps.setString(1, nome);
			ps.setString(2, telefone);
			ps.setString(3, email);
			ps.setString(4, endereco);
			ps.setInt(5, codFunc);
			
			ps.execute();
			
		
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection(conexao);
		}
		
	}

	/**
	 * Retorna uma lista com todos os pedidos.
	 * @return
	 */
	
	public static List<Produto> listarProdutos() {
		Connection conexao = null;
		String cmd = LojaUtil.get("listar.produto");
		List<Produto> produtos = new ArrayList<>();
		
		try {
			conexao = getConnection();
			PreparedStatement ps = conexao.prepareStatement(cmd);
		
			ResultSet rs = ps.executeQuery();		
			
			while(rs.next()) {
				 int id = rs.getInt("Codigo do Produto");
				
				 String nome = rs.getString("Nome do Produto");
				
				 String desc = rs.getString("Descricao");

				 int qtd_estq = rs.getInt("Estoque");
				 
				 double custo = rs.getDouble("Custo");
				
				 double valor = rs.getDouble("Valor de Venda");
				 
				 Produto produto = new Produto(id, nome, desc, custo, qtd_estq, valor);
				 
				 produtos.add(produto);
			}
			
		} catch (SQLException e) {
			assert false: ("ERRO: " + e.getCause());
		}finally {
			closeConnection(conexao);
		}
		
		
		return produtos;
	}

	public static List<Cliente> listarClientes() {
		Connection conexao = null;
		String cmd = LojaUtil.get("listar.cliente");
		List<Cliente> clientes = new ArrayList<>();
		try {
			conexao = getConnection();
			PreparedStatement ps = conexao.prepareStatement(cmd);
			
			ResultSet result = ps.executeQuery();
			
			while(result.next()) {
				
				int id = result.getInt(1);
		
				String nome = result.getString(2);
		
				String telefone = result.getString(3);
				
				String email = result.getString(4);

				String endereco = result.getString(5);
				
				int codfunc = result.getInt(6);
				
				Cliente cliente = new Cliente(id, nome, telefone, email, endereco, codfunc);
			
				clientes.add(cliente);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection(conexao);
		}
		return clientes;
	}
	

	public static List<Pedido> listarPedidos() {
		Connection conexao = null;
		String cmd = LojaUtil.get("listar.pedido");
		List<Pedido> pedidos = new ArrayList<>();
		
		try {
			conexao = getConnection();
			PreparedStatement ps = conexao.prepareStatement(cmd);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int numeroPedido = rs.getInt("Pedido");
				String nomeFuncionario = rs.getString("Funcionario");
				String nomeCliente = rs.getString("Cliente");
				String dataInicial = rs.getString("Data Inicial");
				String dataFinal = rs.getString("Data Final");
				Double valorTotal = rs.getDouble("Valor Total");				
				String status = rs.getString("Status");
				
				Pedido pedido = new Pedido(numeroPedido, nomeFuncionario, nomeCliente, dataInicial, dataFinal, valorTotal, status);
				
				pedidos.add(pedido);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection(conexao);
		}
		
		return pedidos;
	}
	
	
	public static void subirPedido(List<String> itens, int funcionario) {
		Connection conexao = null;
		String subirPedido = LojaUtil.get("subir.pedido");
		String subirPedidoItens = LojaUtil.get("subir.pedido.itens");
		int numeroPedido = 0;
		try {
			conexao = getConnection();
			CallableStatement cs = conexao.prepareCall(subirPedido);
			
			cs.setString(1, itens.get(0));//cliente
			cs.setInt(2, funcionario);
			cs.registerOutParameter("numPedido", java.sql.Types.INTEGER);
			
			cs.execute();
			numeroPedido = cs.getInt("numPedido");
			
			CallableStatement csItens = conexao.prepareCall(subirPedidoItens);
			
			for(int i=0; i<itens.size(); i = i + 6) {
				csItens.setInt(1, numeroPedido);
				csItens.setInt(2, Integer.parseInt(itens.get(i+1)));//IdProduto
				csItens.setInt(3, Integer.parseInt(itens.get(i+3)));//Quantidade
				csItens.execute();
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection(conexao);
		}
	}

	public static void cadastrarProduto(List<Produto> produtos) {
		String cmd = LojaUtil.get("cadastro.produtos");
		Connection conexao = null;
		
		try {
			conexao = getConnection();
			CallableStatement cs = conexao.prepareCall(cmd);

			
			for(Produto p : produtos) {
				cs.setString(1, p.getNome());
				cs.setString(2, p.getDesc());
				cs.setDouble(3, p.getCusto());
				cs.setDouble(4, p.getValor());
				cs.setInt(5, p.getQtd_estq());
				
				cs.execute();
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection(conexao);
		}
	}

	public static void subirEncomenda(List<String> encomendas, int idFunc) {
		//A LISTA CONTEM:
		//IDCLIENTE 
		//IDPRODUTO NOMEPRODUTO QUANTIDADE VALOR TOTAL 
		Connection conexao = null;
		
		String subirEncomenda = LojaUtil.get("subir.encomenda");
		String subirEncomendaItens = LojaUtil.get("subir.encomenda.itens");
		int numeroPedido = 0;
		try {
			conexao = getConnection();
			CallableStatement cs = conexao.prepareCall(subirEncomenda);
			
			cs.setInt(1, Integer.parseInt(encomendas.get(6)));//cliente
			cs.setInt(2, idFunc);
			cs.registerOutParameter("NumPedido", java.sql.Types.INTEGER);
			
			cs.execute();
			numeroPedido = cs.getInt("NumPedido");
			
			CallableStatement csItens = conexao.prepareCall(subirEncomendaItens);
			
			for(int i=0; i<encomendas.size(); i = i + 7) {
				csItens.setInt(1, numeroPedido);
				csItens.setInt(2, Integer.parseInt(encomendas.get(i+1)));//IdProduto
				csItens.setInt(3, Integer.parseInt(encomendas.get(i+3)));//Quantidade
				csItens.execute();
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection(conexao);
		}
	}

	public static void finalizarEncomenda(int idPedido) {
		Connection conexao = null;
		String cmd = LojaUtil.get("finalizar.encomenda");		
		
		
		try {
			conexao = getConnection();
			PreparedStatement ps = conexao.prepareStatement(cmd);
			
			ps.setInt(1, idPedido);
			ps.execute();		
		} catch (SQLException e) {
			assert false: "Problema De Sql em Finalizar encomeda:" + e.getCause();
		}
		
	}

	


	
	
}
