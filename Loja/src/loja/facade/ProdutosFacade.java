package loja.facade;

import java.util.List;

import loja.negocio.Produto;
import loja.persistencia.DAOLoja;

public class ProdutosFacade {
	public static List<Produto> produtos = DAOLoja.listarProdutos();

	public static List<Produto> getProdutos() {
		return produtos;
	}
}
