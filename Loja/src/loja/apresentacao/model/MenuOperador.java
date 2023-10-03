package loja.apresentacao.model;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import loja.apresentacao.operador.CadastrarProdutoView;
import loja.apresentacao.operador.CadastrarClienteView;
import loja.apresentacao.operador.ConsultarPedidoView;
import loja.apresentacao.operador.EncomendarPedidoView;
import loja.apresentacao.operador.SubirPedidoView;
import loja.controller.LoginController;
import loja.negocio.Funcionario;

public class MenuOperador extends JFrame implements ActionListener {

	
	private static final long serialVersionUID = 5436388571084157070L;
	public JFrame parent;
	public Funcionario funcionario;
	public int idSessao;
	public JButton btnCadCliente;
	public JButton btnSubirPedido;
	public JButton btnCadProduto;
	public JButton btnEncomendar;
	public JButton btnConsultarPedido;
	public JButton btnVoltar;
	
	
	public MenuOperador() {
		setSize(new Dimension(598, 300));
		
		setTitle("Menu");
		setResizable(false);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		btnCadCliente = new JButton("Cadastrar Cliente");
		btnCadCliente.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnCadCliente.setBounds(10, 14, 144, 23);
		getContentPane().add(btnCadCliente);
		btnCadCliente.setActionCommand("cadastrar_cliente");
		btnCadCliente.addActionListener(this);
		
		
		btnSubirPedido = new JButton("Subir Pedido");
		btnSubirPedido.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnSubirPedido.setBounds(10, 74, 144, 23);
		getContentPane().add(btnSubirPedido);
		btnSubirPedido.setActionCommand("subir_pedido");
		btnSubirPedido.addActionListener(this);
		
		btnCadProduto = new JButton("Cadastrar Produto");
		btnCadProduto.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnCadProduto.setBounds(10, 46, 144, 23);
		getContentPane().add(btnCadProduto);
		btnCadProduto.setActionCommand("cadastrar_produto");
		btnCadProduto.addActionListener(this);
		
		btnEncomendar = new JButton("Subir Encomenda");
		btnEncomendar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnEncomendar.setBounds(10, 103, 144, 23);
		getContentPane().add(btnEncomendar);
		btnEncomendar.setActionCommand("encomendar");
		btnEncomendar.addActionListener(this);
		
		btnConsultarPedido = new JButton("Consultar Pedido");
		btnConsultarPedido.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnConsultarPedido.setBounds(10, 132, 144, 23);
		getContentPane().add(btnConsultarPedido);
		btnConsultarPedido.setActionCommand("consultar");
		btnConsultarPedido.addActionListener(this);
		
		btnVoltar = new JButton("Voltar");
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnVoltar.setBounds(10, 227, 144, 23);
		getContentPane().add(btnVoltar);
		btnVoltar.setActionCommand("voltar");
		btnVoltar.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent evento) {
		String cmd = evento.getActionCommand();
		JFrame telaEscolhida = null;
		
		if(cmd.equals("cadastrar_cliente")) {
			telaEscolhida = new CadastrarClienteView(this.parent, idSessao, funcionario);
		}else if(cmd.equals("subir_pedido")){
			telaEscolhida = new SubirPedidoView(this.parent, idSessao, funcionario);
		}else if(cmd.equals("cadastrar_produto")) {
			telaEscolhida = new CadastrarProdutoView(this.parent, idSessao, funcionario);
		}else if(cmd.equals("encomendar")) {
			telaEscolhida = new EncomendarPedidoView(this.parent, idSessao, funcionario);
		}else if(cmd.equals("consultar")) {
			telaEscolhida = new ConsultarPedidoView(this.parent, idSessao, funcionario);
		}else if(cmd.equals("voltar")) {
			telaEscolhida = parent;		
			LoginController controller = new LoginController();
			controller.fecharSessao(idSessao);
		}
		//this.setVisible(false);
		telaEscolhida.setVisible(true);
		this.dispose();
	}

}
