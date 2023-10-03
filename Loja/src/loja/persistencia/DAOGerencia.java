package loja.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import loja.negocio.Funcionario;
import loja.negocio.Pedido;
import loja.util.LojaUtil;

public class DAOGerencia {
	
	public static Connection getConnection() throws SQLException {
		String url = LojaUtil.get("url");
	
		String usuario = LojaUtil.get("usuario");
		String senha = LojaUtil.get("senha");

		Connection conexao = DriverManager.getConnection(url, usuario, senha);

		return conexao;
	}
	
	public static void closeConnection(Connection conexao) {
		if (conexao != null) {
			try {
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void cadastrarFuncionario(Funcionario funcionario) {
		Connection conexao = null;
		String cmd = LojaUtil.get("cadastro.funcionario");
		
		try {
			conexao = getConnection();
			
			PreparedStatement ps = conexao.prepareStatement(cmd);
		
			ps.setString(1, funcionario.getNome());
			ps.setString(2, funcionario.getCargo());
			ps.setDouble(3, funcionario.getSalario());
			ps.setString(4, funcionario.getTelefone());
			ps.setString(5, funcionario.getEmail());
			ps.setString(6, funcionario.getEndereco());
	
			ps.execute();
			
		} catch (SQLException e) {
			assert false: "Problema De Sql em Cadastrar Funcionario." + e.getCause();
		}
	}
	public static List<Pedido> emitirRelatorio(int ano, int mes) {
		Connection conexao = null;
		String cmd = LojaUtil.get("emitir.relatorio");
		List<Pedido> pedidos = new ArrayList<>();
		
		try {
			conexao = getConnection();
			PreparedStatement ps = conexao.prepareStatement(cmd);
			ps.setInt(1, ano);
			ps.setInt(2, mes);
			
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

	public static void cadastrarUsuario(String user, String pass, String funcionario) {
		Connection conexao = null;
		String cmd = LojaUtil.get("cadastro.usuario.sistema");
		
		try {
			conexao = getConnection();
			PreparedStatement ps = conexao.prepareStatement(cmd);
			ps.setString(1, funcionario);
			ps.setString(2, user);
			ps.setString(3, pass);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection(conexao);
		}
	}

	public static List<Funcionario> listarFuncionarios() {
		Connection conexao = null;
		String cmd = LojaUtil.get("listar.funcionario");
		List<Funcionario> funcionarios = new ArrayList<>();
		try {
			conexao = getConnection();
			PreparedStatement ps = conexao.prepareStatement(cmd);
			
			ResultSet result = ps.executeQuery();
			
			while(result.next()) {
				
				int id = result.getInt(1);
		
				String nome = result.getString(2);
		
				String cargo = result.getString(3);

				double salario = result.getDouble(4);
				
				String telefone = result.getString(5);
				
				String email = result.getString(6);

				String endereco = result.getString(7);
				
				
				Funcionario funcionario = new Funcionario(id, nome, cargo, salario, telefone, email, endereco);
			
				funcionarios.add(funcionario);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection(conexao);
		}
		return funcionarios;
	}
}
