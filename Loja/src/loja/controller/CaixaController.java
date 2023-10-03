package loja.controller;

import java.util.List;

import loja.negocio.Cliente;
import loja.negocio.Pedido;
import loja.negocio.Produto;
import loja.persistencia.DAOLoja;

public class CaixaController {

	
	//
	/**
	 * Vai pegar a lista de itens que foram adicionados ao pedido na tela de subir pedido e passar para o DAO.
	 * @param itens
	 * @param funcionario 
	 */
	public void subirPedido(List<String> itens, int funcionario) {
		DAOLoja.subirPedido(itens, funcionario);
	}
	
	public void finalizarEncomenda(int idPedido) {
		DAOLoja.finalizarEncomenda(idPedido);
	}

	public void cadastrarCliente(String nome, String email, String telefone, String endereco, int codFunc) {
		
		DAOLoja.cadastrarCliente(nome, email , telefone , endereco, codFunc);
		
	}

	public List<Produto> listarProdutos() {
		List<Produto> produtos = DAOLoja.listarProdutos();
		
		
		//LOGICA PARA INDICAR AQUELES SEM ESTOQUE.
		
		return produtos;
	}

	public void cadastrarProduto(List<Produto> produtos) {
		
		DAOLoja.cadastrarProduto(produtos);
		

	}

	public List<Cliente> listarClientes() {
		List<Cliente> clientes = DAOLoja.listarClientes();
		return clientes;
	}

	public void subirEncomenda(List<String> encomendas, int idFunc) {
		DAOLoja.subirEncomenda(encomendas, idFunc);
		
	}

	/**
	 * Chama o dao para listar todos os pedidos.
	 * @return
	 */
	public List<Pedido> listarPedidos() {
		List<Pedido> pedidos = DAOLoja.listarPedidos();
		
		return pedidos;
		
		
	}
	
	
}
