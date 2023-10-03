package loja.controller;

import java.util.List;

import loja.negocio.Funcionario;
import loja.negocio.Pedido;
import loja.persistencia.DAOGerencia;
import loja.util.LojaUtil;

public class GerenciamentoController {

	
	/**
	 * Emite relátorio sobre faturamento do restaurante em determinado dia, mes, e detalhes sobre pedidos.
	 * 
	 * @return Uma instancia da tela de emitir relatório.
	 */
	public List<Pedido> emitirRelatorio(int ano, int mes) {
		List<Pedido> pedidos = DAOGerencia.emitirRelatorio(ano, mes);
		return pedidos;
	}
	
	public void cadastrarFuncionario(String nome, String endereco, String telefone, String email, String cargo,
			Double salario) {

		Funcionario funcionario = LojaUtil.criarFuncionario(nome, endereco, telefone, email, cargo, salario);
		
		DAOGerencia.cadastrarFuncionario(funcionario);
	}

	public void cadastrarUsuario(String user, String pass, String funcionario) {
		
		
		DAOGerencia.cadastrarUsuario(user, pass, funcionario);
		
		
	}

	public List<Funcionario> listarFuncionarios() {

		List<Funcionario> funcionarios = DAOGerencia.listarFuncionarios();
		
		return funcionarios;
	}
}
