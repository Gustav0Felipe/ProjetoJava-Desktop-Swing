package loja.apresentacao.operador;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import loja.negocio.Pedido;
import loja.util.LojaDocumentFilter;

//consulta a partir do numero da mesa
public class ConsultarPedidoView extends MenuOperador implements ActionListener{
	
	private static final long serialVersionUID = 7844965213544341112L;
	private JTextField txtPedido;
	private JTable tblPedido;
	private JScrollPane scrollPane;
	
	private CaixaController caixaController = new CaixaController();
	private List<Pedido> pedidos = caixaController.listarPedidos();

	public ConsultarPedidoView(JFrame parent, int idSessao, Funcionario funcionario) {
		this.parent = parent;
		this.idSessao = idSessao;
		this.funcionario = funcionario;
		
		btnConsultarPedido.setBackground(new Color(255, 255, 255));
		
		setSize(new Dimension(900, 300));
		
		setTitle("Consulta de Pedidos");
		setResizable(false);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	
		JLabel lblPedido = new JLabel("Pedido:");
		lblPedido.setBounds(164, 18, 46, 14);
		getContentPane().add(lblPedido);
		
		txtPedido = LojaDocumentFilter.createFilteredFieldInteger();
		txtPedido.setBounds(220, 15, 43, 20);
		getContentPane().add(txtPedido);
		txtPedido.setColumns(10);
		
		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.setBounds(273, 14, 89, 23);
		getContentPane().add(btnFiltrar);
		btnFiltrar.setActionCommand("filtrar");
		btnFiltrar.addActionListener(this);
		
		JButton btnAll = new JButton("All");
		btnAll.setBounds(378, 14, 89, 23);
		getContentPane().add(btnAll);
		btnAll.setActionCommand("all");
		btnAll.addActionListener(this);
		
		
		JButton btnFinalizar = new JButton("Finalizar");
		btnFinalizar.setBounds(483, 14, 89, 23);
		getContentPane().add(btnFinalizar);
		btnFinalizar.setActionCommand("finalizar");
		btnFinalizar.addActionListener(this);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(164, 50, 700, 200);
		getContentPane().add(scrollPane);
		
		tblPedido = new JTable();
		tblPedido.setFillsViewportHeight(true);
		tblPedido.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Pedido", "Funcionario", "Cliente", "Inicio", "Fim", "Valor", "Status"
			}
		));
		tblPedido.setBounds(164, 50, 700, 200);
		scrollPane.setViewportView(tblPedido);
		

		setLocationRelativeTo(null);

	}
	

	private Object[][] listarPedidos() {
		Object[][] objPedidos = new Object[pedidos.size()][7];
		for(int i = 0; i<pedidos.size(); i ++) {
			objPedidos[i][0] = pedidos.get(i).getId();			
			objPedidos[i][1] = pedidos.get(i).getFuncionario();			
			objPedidos[i][2] = pedidos.get(i).getCliente();			
			objPedidos[i][3] = pedidos.get(i).getDataInicial();			
			objPedidos[i][4] = pedidos.get(i).getDataFinal();			
			objPedidos[i][5] = pedidos.get(i).getValorTotal();			
			objPedidos[i][6] = pedidos.get(i).getStatus();			
			
		}
		return objPedidos;
	}
	private Object[][] filtrarPedidos(){
		Object[][] objPedidos = new Object[pedidos.size()][7];
		int idPedido = Integer.parseInt(txtPedido.getText());
		
		for(int i = 0; i<pedidos.size(); i ++) {
			if(pedidos.get(i).getId() == idPedido) {
				objPedidos[i][0] = pedidos.get(i).getId();			
				objPedidos[i][1] = pedidos.get(i).getFuncionario();			
				objPedidos[i][2] = pedidos.get(i).getCliente();			
				objPedidos[i][3] = pedidos.get(i).getDataInicial();			
				objPedidos[i][4] = pedidos.get(i).getDataFinal();			
				objPedidos[i][5] = pedidos.get(i).getValorTotal();			
				objPedidos[i][6] = pedidos.get(i).getStatus();			
			return objPedidos;
			}
		}
		return objPedidos;
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
		
		else if(cmd.equals("filtrar")) {
			if(!txtPedido.getText().strip().equals("")) {
				telaEscolhida = this;
				Object [][] allPedidos = filtrarPedidos();
				
				tblPedido = new JTable();
				tblPedido.setFillsViewportHeight(true);
				tblPedido.setModel(new DefaultTableModel(
					allPedidos,
					new String[] {
						"Pedido", "Funcionario", "Cliente", "Inicio", "Fim", "Valor", "Status"
					}
				));
				tblPedido.setBounds(164, 50, 408, 200);
				scrollPane.setViewportView(tblPedido);		
			}else {JOptionPane.showMessageDialog(null, "Codigo não especificado.", "Falha de Cadastro", JOptionPane.ERROR_MESSAGE);}
			
		}else if(cmd.equals("all")) {
			telaEscolhida = this;
			Object [][] allPedidos = listarPedidos();
			
			tblPedido = new JTable();
			tblPedido.setFillsViewportHeight(true);
			tblPedido.setModel(new DefaultTableModel(
				allPedidos,
				new String[] {
					"Pedido", "Funcionario", "Cliente", "Inicio", "Fim", "Valor", "Status"
				}
			));
			tblPedido.setBounds(164, 50, 408, 200);
			scrollPane.setViewportView(tblPedido);
		}else if(cmd.equals("finalizar")) {
			telaEscolhida = this;
			if(!txtPedido.getText().strip().equals("")) {
				caixaController.finalizarEncomenda(Integer.parseInt(txtPedido.getText()));
				pedidos = caixaController.listarPedidos();
				
				
				Object [][] allPedidos = filtrarPedidos();
				
				tblPedido = new JTable();
				tblPedido.setFillsViewportHeight(true);
				tblPedido.setModel(new DefaultTableModel(
					allPedidos,
					new String[] {
						"Pedido", "Funcionario", "Cliente", "Inicio", "Fim", "Valor", "Status"
					}
				));
				tblPedido.setBounds(164, 50, 408, 200);
				
				scrollPane.setViewportView(tblPedido);
			}else {JOptionPane.showMessageDialog(null, "Codigo não especificado.", "Falha de Cadastro", JOptionPane.ERROR_MESSAGE);}
		}
		tblPedido.getColumnModel().getColumn(3).setMinWidth(120);
		tblPedido.getColumnModel().getColumn(4).setMinWidth(120);
		
		
		//this.setVisible(false);
		telaEscolhida.setVisible(true);
		
		
		if(!cmd.equals("all") && !cmd.equals("filtrar") && !cmd.equals("finalizar")){
			this.dispose();
		}
	}		
}
