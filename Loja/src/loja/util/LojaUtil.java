package loja.util;

import java.io.IOException;
import java.util.Properties;

import loja.negocio.Funcionario;
import loja.negocio.Produto;

public class LojaUtil {
	
	
	public static String get(String propriedade) {
		
		//classe que lê arquivos de propriedades, carrega arquivos nesse formato a partir do metodo load.
		Properties prop = new Properties();
		String valor = null;
		
		//recebe um FileInputStream apontando para o arquivo, tenho que tratar exceção então.
		try {//quando é um recurso interno eu uso um metodo que carrega um recurso para processamento ou leitura.
			//prop.load(new FileInputStream("/resources/configuration"));
			prop.load(LojaUtil.class.getResourceAsStream("/resources/configuration.txt"));//tava dando erro por que o arquivo eu não tinha especificado que era txt, ai o tipo tava dando disparidade e não tava achando.
			valor = prop.getProperty(propriedade);//depois de carregar o arquivo, vai pegar a propriedade que passei, pega o valor através da chave dela.
		} catch (IOException e) {
			//Se não tiver configuração não consigo continuar a aplicação, representaria um bug na minha aplicação, então o melhor tratamento é com o assert.
			assert false : "Configuração não carregada.";
		}
		return valor;
	}

	public Produto criarProduto(String nome, String desc, String quantidade, String custo, String valor) {
		
		Produto produto = new Produto(nome, desc, Integer.parseInt(quantidade) ,Double.parseDouble(custo), Double.parseDouble(valor));
		
		
		return produto;
		
	}

	public static Funcionario criarFuncionario(String nome, String endereco, String telefone, String email, String cargo,
			Double salario) {

		Funcionario funcionario = new Funcionario(nome, cargo, salario, telefone, email, endereco);
		
		return funcionario;
	}
	public static int testeInt() {
		return 2;
	}
}
