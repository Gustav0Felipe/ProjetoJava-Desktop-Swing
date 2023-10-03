package loja.apresentacao.operador;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
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

public class SubirPedidoView extends MenuOperador implements ActionListener{
	
	private static final long serialVersionUID = -7237241346523043131L;
	private JTextField txtQuantidade;
	private JTable tblPedido;
	private JScrollPane scrollPane;
	
	private CaixaController caixaController = new CaixaController();
	private List<Produto> produtos = caixaController.listarProdutos();
	private List<Cliente> clientes = caixaController.listarClientes();
	private List<String> itens = new LinkedList<>();
	private JComboBox<Object> boxProduto;
	private JComboBox<Object> cbxCliente;
	
	public SubirPedidoView(JFrame parent, int idSessao, Funcionario funcionario) {
		this.parent = parent;
		this.idSessao = idSessao;
		this.funcionario = funcionario;

		btnSubirPedido.setBackground(new Color(255, 255, 255));
		
		setSize(new Dimension(598, 300));
		
		setTitle("Pedido");
		setResizable(false);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		JLabel lblQtd = new JLabel("Quantidade:");
		lblQtd.setBounds(164, 65, 89, 14);
		getContentPane().add(lblQtd);
		
		JLabel lblCliente = new JLabel("Cliente:");
		lblCliente.setBounds(164, 15, 89, 14);
		getContentPane().add(lblCliente);
		
		JLabel lblProduto = new JLabel("Produto:");
		lblProduto.setBounds(164, 40, 89, 14);
		getContentPane().add(lblProduto);
		
		
		JButton btnAdd = new JButton("Adicionar");
		btnAdd.setBounds(164, 101, 89, 23);
		getContentPane().add(btnAdd);
		btnAdd.setActionCommand("add");
		btnAdd.addActionListener(this);
		
		JButton btnConcluir = new JButton("Concluir");
		btnConcluir.setBounds(266, 101, 89, 23);
		getContentPane().add(btnConcluir);
		btnConcluir.setActionCommand("concluir");
		btnConcluir.addActionListener(this);
		
		txtQuantidade = LojaDocumentFilter.createFilteredFieldInteger();
		txtQuantidade.setBounds(266, 62, 89, 20);
		getContentPane().add(txtQuantidade);
		txtQuantidade.setColumns(10);
		
		Object[] objClientes = comboClientes();
		cbxCliente = new JComboBox<Object>(objClientes);
		cbxCliente.setBounds(266, 15, 89, 20);
		getContentPane().add(cbxCliente);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(164, 134, 408, 116);
		getContentPane().add(scrollPane);
		
		tblPedido = new JTable();
		tblPedido.setFillsViewportHeight(true);
		tblPedido.setModel(new DefaultTableModel(
			new Object[][] {}
			,
			new String[] {
					"Cliente", "Produto", "Nome" ,"Quantidade", "Valor", "Total"
			}
		));
	
		tblPedido.setBounds(164, 135, 408, 115);
		scrollPane.setViewportView(tblPedido);
		
		Object[] conteudoProdutos = comboProdutos();
		boxProduto = new JComboBox<>();
		boxProduto.setModel(new DefaultComboBoxModel<>(conteudoProdutos));
		boxProduto.setBounds(266, 36, 86, 22);
		getContentPane().add(boxProduto);
		
		setLocationRelativeTo(null);
	}

	private Object[] comboClientes() {
		Object[] nomeClientes = new Object[clientes.size()];;
		for(int i=0; i<clientes.size(); i++) {
			nomeClientes[i] = clientes.get(i).getNome();
		}	
		return nomeClientes;
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
	
	private Object[][] preencherTabelaPedido(){
		Object[][] pedidoItens = new Object[1][6] ;

		Produto produto = null;
		
		for(Produto p : produtos) {
			if(p.getNome().equals(boxProduto.getSelectedItem())) {
				produto = p;
			}
		}
		
		pedidoItens[0][0] = cbxCliente.getSelectedItem().toString();		
		pedidoItens[0][1] = produto.getId();
		pedidoItens[0][2] = produto.getNome();
		pedidoItens[0][3] = txtQuantidade.getText();
		pedidoItens[0][4] = produto.getValor();
		pedidoItens[0][5] = produto.getValor() * Integer.parseInt(txtQuantidade.getText());
		
		itens.add(pedidoItens[0][0].toString());
		itens.add(pedidoItens[0][1].toString());
		itens.add(pedidoItens[0][2].toString());
		itens.add(pedidoItens[0][3].toString());
		itens.add(pedidoItens[0][4].toString());
		itens.add(pedidoItens[0][5].toString());
		
		Object [][] itensLista =  new Object[itens.size()/6][6];
		int linha = 0;
		for(int i=0; i<itens.size(); i = i + 6) {
			
			itensLista[linha][0] = itens.get(i);
			itensLista[linha][1] = itens.get(i+1);
			itensLista[linha][2] = itens.get(i+2);
			itensLista[linha][3] = itens.get(i+3);
			itensLista[linha][4] = itens.get(i+4);
			itensLista[linha][5] = itens.get(i+5);
			linha ++;
		}
			
		return itensLista;
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
			Object [][] itensLista = preencherTabelaPedido();
			
			tblPedido = new JTable();
			tblPedido.setFillsViewportHeight(true);
			tblPedido.setModel(new DefaultTableModel(
					itensLista
				,
				new String[] {
					"Cliente", "Produto", "Nome" ,"Quantidade", "Valor", "Total"
				}
			)); 
			tblPedido.setBounds(164, 135, 408, 115);
			scrollPane.setViewportView(tblPedido);
			}else {JOptionPane.showMessageDialog(null, "Quantidade não especificada.", "Falha no Pedido", JOptionPane.ERROR_MESSAGE);}
			
		}else if(cmd.equals("concluir")) {
			telaEscolhida = this;
			if(itens.size() >= 1) {
			caixaController.subirPedido(itens, funcionario.getId()); 

			tblPedido = new JTable();
			tblPedido.setFillsViewportHeight(true);
			tblPedido.setModel(new DefaultTableModel(
			new Object[][] {}
			,
			new String[] {
					"Cliente", "Produto", "Nome" ,"Quantidade", "Valor", "Total"
			}
		));
	
		tblPedido.setBounds(164, 135, 408, 115);
		scrollPane.setViewportView(tblPedido);

			}else {JOptionPane.showMessageDialog(null, "Pedido vazio.", "Falha no Pedido", JOptionPane.ERROR_MESSAGE);}

		}
	
		
		//this.setVisible(false);
		telaEscolhida.setVisible(true);
		if(!cmd.equals("add") && !cmd.equals("concluir")) {
		this.dispose();			
		}
	}
}
