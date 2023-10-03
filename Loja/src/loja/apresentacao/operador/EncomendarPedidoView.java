package loja.apresentacao.operador;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import loja.apresentacao.model.MenuOperador;
import loja.controller.CaixaController;
import loja.controller.LoginController;
import loja.negocio.Cliente;
import loja.negocio.Funcionario;
import loja.negocio.Produto;
import loja.util.LojaDocumentFilter;

public class EncomendarPedidoView extends MenuOperador implements ActionListener {
	
	private static final long serialVersionUID = -7972368034432196269L;
	private JTextField txtQuantidade;
	private JComboBox<?> cbxProduto;
	private JComboBox<?> cbxCliente;
	private JTable tblPedido;
	private JScrollPane scrollPane;
	
	private CaixaController caixaController = new CaixaController();
	private List<Produto> produtos = caixaController.listarProdutos();
	private List<Cliente> clientes = caixaController.listarClientes();
	private List<String> encomendas = new ArrayList<>();
	
	public EncomendarPedidoView(JFrame parent, int idSessao, Funcionario funcionario) {
		this.parent = parent;
		this.idSessao = idSessao;
		this.funcionario = funcionario;
		
		btnEncomendar.setBackground(new Color(255, 255, 255));

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(598, 300));
		
		setTitle("Encomenda");
		getContentPane().setLayout(null);
		

		JButton btnAdd = new JButton("Adicionar");
		btnAdd.setBounds(164, 101, 89, 23);
		getContentPane().add(btnAdd);
		btnAdd.setActionCommand("add");
		btnAdd.addActionListener(this);
		
		JButton btnConcluir = new JButton("Concluir");
		btnConcluir.setBounds(263, 101, 89, 23);
		getContentPane().add(btnConcluir);
		btnConcluir.setActionCommand("concluir");
		btnConcluir.addActionListener(this);
		
		JLabel lblQtd = new JLabel("Quantidade:");
		lblQtd.setBounds(164, 72, 89, 14);
		getContentPane().add(lblQtd);
		
		JLabel lblCliente = new JLabel("Cliente:");
		lblCliente.setBounds(164, 18, 89, 14);
		getContentPane().add(lblCliente);
		
		JLabel lblProduto = new JLabel("Produto:");
		lblProduto.setBounds(165, 45, 88, 14);
		getContentPane().add(lblProduto);
		
		txtQuantidade = LojaDocumentFilter.createFilteredFieldInteger();
		txtQuantidade.setBounds(266, 69, 86, 20);
		getContentPane().add(txtQuantidade);
		txtQuantidade.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(164, 134, 408, 116);
		getContentPane().add(scrollPane);
		
		Object[] objProdutos = comboProdutos();
		cbxProduto = new JComboBox<Object>(objProdutos);
		cbxProduto.setBounds(266, 42, 86, 20);
		getContentPane().add(cbxProduto);
		
		tblPedido = new JTable();
		tblPedido.setFillsViewportHeight(true);
		tblPedido.setModel(new DefaultTableModel(
			new Object[][] {}
			,
			new String[] {
					"Cliente", "Produto", "Nome" ,"Quantidade", "Valor", "Total"
			}
		));
		scrollPane.setViewportView(tblPedido);
		tblPedido.setBounds(164, 135, 408, 115);
		
		Object[] objClientes = comboClientes();
		cbxCliente = new JComboBox<Object>(objClientes);
		cbxCliente.setBounds(266, 15, 86, 20);
		getContentPane().add(cbxCliente);
	
		setLocationRelativeTo(null);
	}
	
	private Object[] comboProdutos() {
		Object[] nomeProdutos = new Object[produtos.size()];;
		for(int i=0; i<produtos.size(); i++) {
			if(produtos.get(i).getQtd_estq() > 0) {
			nomeProdutos[i] = produtos.get(i).getNome();
			}else {
			nomeProdutos[i] = produtos.get(i).getNome() + " SEM ESTOQUE";		
			}
		}	
		return nomeProdutos;
	}
	
	private Object[] comboClientes() {
		Object[] nomeClientes = new Object[clientes.size()];;
		for(int i=0; i<clientes.size(); i++) {
			nomeClientes[i] = clientes.get(i).getNome();
		}	
		return nomeClientes;
	}
	
	private Object[][] preencherTabelaEncomendas() {
		
		Produto produto = null;
		for(Produto p : produtos) {
			if(p.getNome().equals(cbxProduto.getSelectedItem())) {
				produto = p;
			}
		}
		
		Cliente cliente = null;
		for(Cliente c : clientes) {
			if(c.getNome().equals(cbxCliente.getSelectedItem())) {
				cliente = c;
			}
		}
		
		String nomeCliente = cbxCliente.getSelectedItem().toString(); 
		String idProduto = String.valueOf(produto.getId());
		String nomeProduto = produto.getNome();
		String quantidade = txtQuantidade.getText();
		
		encomendas.add(nomeCliente);
		encomendas.add(idProduto);
		encomendas.add(nomeProduto);
		encomendas.add(quantidade);
		encomendas.add(String.valueOf(produto.getValor()));
		encomendas.add(String.valueOf(produto.getValor() * Integer.parseInt(quantidade)));
		encomendas.add(String.valueOf(cliente.getId()));
		int linha = 0;
		Object [][] objEncomendas = new Object[encomendas.size()/7][6];//idCliente não precisa aparecer.
		for(int i = 0; i<encomendas.size(); i = i + 7) {
			
			objEncomendas[linha][0] = encomendas.get(i);
			objEncomendas[linha][1] = encomendas.get(i+1);			
			objEncomendas[linha][2] = encomendas.get(i+2);
			objEncomendas[linha][3] = encomendas.get(i+3);
			objEncomendas[linha][4] = encomendas.get(i+4);
			objEncomendas[linha][5] = encomendas.get(i+5);
			
			linha++;
		}
		return objEncomendas;
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
		
		else if(cmd.equals("add")) {
			telaEscolhida = this;
			if(!txtQuantidade.getText().strip().equals("")) {
				
				Object [][] itensLista = preencherTabelaEncomendas();
				
				tblPedido = new JTable();
				tblPedido.setFillsViewportHeight(true);
				tblPedido.setModel(new DefaultTableModel(
						itensLista,
					new String[] {
						"Cliente", "Produto", "Nome" ,"Quantidade", "Valor", "Total"
					})); 
				tblPedido.setBounds(164, 135, 408, 115);
				scrollPane.setViewportView(tblPedido);
			}else {JOptionPane.showMessageDialog(null, "Especifique quantidade.", "Falha na Encomenda", JOptionPane.ERROR_MESSAGE);}
		}else if(cmd.equals("concluir")) {
			telaEscolhida = this;
			if(encomendas.size() >= 1) {
			caixaController.subirEncomenda(encomendas, funcionario.getId()); 
			}else {JOptionPane.showMessageDialog(null, "Sem itens na encomenda.", "Falha na Encomenda", JOptionPane.ERROR_MESSAGE);}
			
		}
		telaEscolhida.setVisible(true);
		
		if(!cmd.equals("add") && !cmd.equals("concluir")) {
		this.dispose();			
		}
	}		
}
