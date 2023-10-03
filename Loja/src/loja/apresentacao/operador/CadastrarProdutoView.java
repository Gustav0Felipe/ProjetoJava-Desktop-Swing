package loja.apresentacao.operador;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
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
import loja.negocio.Funcionario;
import loja.negocio.Produto;
import loja.util.LojaDocumentFilter;
import loja.util.LojaUtil;

public class CadastrarProdutoView extends MenuOperador implements ActionListener{

	private static final long serialVersionUID = 6848367477421436514L;
	private JTextField txtProduto;
	private JTextField txtDesc;
	private JTable tblPedido;
	private JTextField txfQuantidade;
	private JTextField txfCusto;
	private JTextField txfValor;
	private JScrollPane scrollPane;
	private List<Produto> produtos = new LinkedList<>();
	
	public CadastrarProdutoView(JFrame telaLogin, int idSessao, Funcionario funcionario) {
		this.parent = telaLogin;
		this.idSessao = idSessao;
		this.funcionario = funcionario;
		
		btnCadProduto.setBackground(new Color(255, 255, 255));
		
		setSize(new Dimension(598, 300));
		
		setTitle("Cadastro de Produto");
		setResizable(false);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(164, 137, 86, 23);
		getContentPane().add(btnAdd);
		btnAdd.setActionCommand("add");
		btnAdd.addActionListener(this);
		
		JButton btnConcluir = new JButton("Concluir");
		btnConcluir.setBounds(266, 137, 86, 23);
		getContentPane().add(btnConcluir);
		btnConcluir.setActionCommand("concluir");
		btnConcluir.addActionListener(this);
		
		JLabel lblQtd = new JLabel("Quantidade:");
		lblQtd.setBounds(164, 65, 68, 14);
		getContentPane().add(lblQtd);
		
		JLabel lblProduto = new JLabel("Produto:");
		lblProduto.setBounds(164, 15, 68, 14);
		getContentPane().add(lblProduto);
		
		JLabel lblDesc = new JLabel("Descrição:");
		lblDesc.setBounds(164, 40, 68, 14);
		getContentPane().add(lblDesc);
		
		JLabel lblCusto = new JLabel("Custo:");
		lblCusto.setBounds(164, 90, 68, 14);
		getContentPane().add(lblCusto);
		
		JLabel lblValor = new JLabel("Valor:");
		lblValor.setBounds(164, 115, 68, 14);
		getContentPane().add(lblValor);
		

		txtProduto = LojaDocumentFilter.createFilteredFieldLimit(75);
		txtProduto.setBounds(266, 12, 86, 20);
		txtProduto.setColumns(10);
		getContentPane().add(txtProduto);
		
		txtDesc = LojaDocumentFilter.createFilteredFieldLimit(255);
		txtDesc.setBounds(266, 37, 86, 20);
		getContentPane().add(txtDesc);
		txtDesc.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(164, 164, 408, 86);
		getContentPane().add(scrollPane);
	
		
		tblPedido = new JTable();
		tblPedido.setFillsViewportHeight(true);
		tblPedido.setModel(new DefaultTableModel(
			new Object[][] {}
			,
			new String[] {
				"Produto", "Descri\u00E7\u00E3o", "Estoque", "Custo", "Valor"
			}
		));
		tblPedido.setBounds(164, 164, 408, 86);
		scrollPane.setViewportView(tblPedido);
		
		
		

		
		txfQuantidade = LojaDocumentFilter.createFilteredFieldInteger();
		txfQuantidade.setBounds(266, 62, 86, 20);
		getContentPane().add(txfQuantidade);
		txfQuantidade.setColumns(10);
		
		txfCusto =LojaDocumentFilter.createFilteredFieldDouble();
		txfCusto.setBounds(266, 87, 86, 20);
		getContentPane().add(txfCusto);
		txfCusto.setColumns(10);
		
		txfValor = LojaDocumentFilter.createFilteredFieldDouble();
		txfValor.setBounds(266, 112, 86, 20);
		getContentPane().add(txfValor);
		txfValor.setColumns(10);
		
		setLocationRelativeTo(null);		
	}
	
	
	private Object[][] preencherCadastroProdutos() {
		LojaUtil util = new LojaUtil();
		Produto produto = util.criarProduto(txtProduto.getText(),txtDesc.getText(),txfQuantidade.getText(),txfCusto.getText(),txfValor.getText());
		
		produtos.add(produto);
		
		Object [][] objProdutos = new Object[produtos.size()][5];
		for(int i = 0; i<produtos.size(); i++) {
			objProdutos[i][0] = produtos.get(i).getNome();
			objProdutos[i][1] = produtos.get(i).getDesc();			
			objProdutos[i][2] = produtos.get(i).getQtd_estq();
			objProdutos[i][3] = produtos.get(i).getCusto();
			objProdutos[i][4] = produtos.get(i).getValor();
		}
		return objProdutos;
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
			
			if(!txfCusto.getText().strip().equals("") && !txfQuantidade.getText().strip().equals("") && !txtProduto.getText().strip().equals("") && !txfValor.getText().strip().equals("")
					&& !txtDesc.getText().strip().equals("")) {
			Object [][] objProduto = preencherCadastroProdutos();
			
			
			tblPedido = new JTable();
			tblPedido.setFillsViewportHeight(true);
			tblPedido.setModel(new DefaultTableModel(
				objProduto,
				new String[] {
					"Produto", "Descri\u00E7\u00E3o", "Estoque", "Custo", "Valor"
				}));
			tblPedido.setBounds(164, 164, 408, 86); //Não consigo criar uma nova tela só com a posição, ela tem que estar dentro do painel, se não crio um objeto java sem adicionar ao painel, ele não aparece então.
			scrollPane.setViewportView(tblPedido);
			}else {
				JOptionPane.showMessageDialog(null, "Todos os valores são obrigatorios", "Falha de Cadastro", JOptionPane.ERROR_MESSAGE);
			}
		}else if(cmd.equals("concluir")) {
			telaEscolhida = this;
			if(produtos.size() >= 1) {
			CaixaController controller = new CaixaController();
			controller.cadastrarProduto(produtos);
			
			Object[][] inicial = new Object[6][5];
			produtos = new LinkedList<>();
			tblPedido = new JTable();
			tblPedido.setFillsViewportHeight(true);
			tblPedido.setModel(new DefaultTableModel(
				inicial
				,
				new String[] {
					"Produto", "Descri\u00E7\u00E3o", "Estoque", "Custo", "Valor"
				}
			));
			tblPedido.setBounds(164, 164, 408, 86); 
			scrollPane.setViewportView(tblPedido);
			}else {
				JOptionPane.showMessageDialog(null, "Cadastro vazio não permitido.", "Falha de Cadastro", JOptionPane.ERROR_MESSAGE);
			}
			
		}
		
		telaEscolhida.setVisible(true);
		txtProduto.setText("");
		txtDesc.setText("");
		txfQuantidade.setText("");
		txfCusto.setText("");
		txfValor.setText("");
		if(!cmd.equals("add") && !cmd.equals("concluir")) {
			this.dispose();			
		}
	}
}
