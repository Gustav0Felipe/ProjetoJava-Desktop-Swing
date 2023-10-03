package loja.controller;

import javax.swing.JFrame;

import loja.negocio.Funcionario;
import loja.persistencia.DAOLoja;

public class LoginController {

	/**
	 * Atráves do usuario e senha vai validar o login, vendo se a conta em questão é uma de funcionario 
	 * ou gerente, com diferentes permissões e recursos disponiveis para cada tipo de conta, e vai 
	 * retornar a tela ou recursos disponiveis para aquela conta.
	 * 
	 * @param usuario
	 * @param senha
	 */
	public Funcionario validarFuncionario(String usuario, String senha, JFrame parent) {
		
		
		//ver se o usuario e senha pertencem a um funcionario, pegar dados do funcionario e criar o objeto a partir disto.
		Funcionario funcionario = DAOLoja.validarLogin(usuario, senha);
		
		
		return funcionario;
	}

	/**
	 * Chama a camada de persistencia, nela vai pegar o funcionario e registrar o acesso na tabela de log.
	 * 
	 * @param funcionario
	 */
	public int iniciarSessao(Funcionario funcionario) {

		int idSessao = DAOLoja.iniciarSessao(funcionario);
		return idSessao;
	}

	public void fecharSessao(int idSessao) {
		DAOLoja.fecharSessao(idSessao);		
	}

	public Boolean validarAdmin(String usuario, String senha) {
		
		Boolean validar = DAOLoja.validarAdmin(usuario, senha);
		
		
		return validar;
	}

}
