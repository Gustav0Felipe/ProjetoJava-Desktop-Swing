package loja.apresentacao.gerenciamento;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import loja.apresentacao.model.MenuGerenciamento;
import loja.controller.GerenciamentoController;
import loja.negocio.Pedido;

public class EmitirRelatorioView extends MenuGerenciamento{
	
	private static final long serialVersionUID = -1308764762141490966L;
	private JTable tblRelatorio;
	private JComboBox<String> cbxAno;
	private JComboBox<String> cbxMes;
	private JScrollPane scrollPane; 
	private JLabel lblTotalFaturado;
	private JLabel lblFatura;
	private double fatura;
	
	public EmitirRelatorioView() {
		
		btnEmitirRelatorio.setBackground(new Color(255, 255, 255));
		
		setSize(new Dimension(900, 300));
		
		cbxAno = new JComboBox<>(new String[] {"2024","2023"});
		cbxAno.setBounds(202, 46, 81, 22);
		getContentPane().add(cbxAno);
		
		cbxMes = new JComboBox<>(new String[] {"8","9","10"});
		cbxMes.setBounds(333, 46, 84, 22);
		getContentPane().add(cbxMes);
		
		JButton btnListar = new JButton("Listar");
		btnListar.setBounds(427, 46, 89, 23);
		getContentPane().add(btnListar);
		btnListar.setActionCommand("listar");
		btnListar.addActionListener(this);
		
		JLabel lblAno = new JLabel("Ano:");
		lblAno.setBounds(174, 50, 46, 14);
		getContentPane().add(lblAno);
		
		JLabel lblNewLabel = new JLabel("Mês:");
		lblNewLabel.setBounds(293, 50, 30, 14);
		getContentPane().add(lblNewLabel);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(174, 81, 700, 169);
		getContentPane().add(scrollPane);
		
		tblRelatorio = new JTable();
		tblRelatorio.setFillsViewportHeight(true);
		tblRelatorio.setModel(new DefaultTableModel(
			new Object[][] {}
			,
			new String[] {
				"Pedido", "Funcionario", "Cliente", "Inicio", "Fim", "Valor", "Status"}));
		tblRelatorio.setBounds(164, 50, 700, 200);
		scrollPane.setViewportView(tblRelatorio);
		
		lblTotalFaturado = new JLabel("Total Faturado:");
		lblTotalFaturado.setBounds(526, 50, 89, 14);
		getContentPane().add(lblTotalFaturado);
		
		lblFatura = new JLabel("");
		lblFatura.setBounds(625, 50, 70, 14);
		getContentPane().add(lblFatura);
	}
	
	private Object[][] listarPedidos(List<Pedido> pedidos) {
		Object[][] objRelatorio = new Object[pedidos.size()][7];
		for(int i = 0; i<pedidos.size(); i ++) {
			objRelatorio[i][0] = pedidos.get(i).getId();			
			objRelatorio[i][1] = pedidos.get(i).getFuncionario();			
			objRelatorio[i][2] = pedidos.get(i).getCliente();			
			objRelatorio[i][3] = pedidos.get(i).getDataInicial();			
			objRelatorio[i][4] = pedidos.get(i).getDataFinal();			
			objRelatorio[i][5] = pedidos.get(i).getValorTotal();			
			objRelatorio[i][6] = pedidos.get(i).getStatus();			
			
			fatura = fatura + pedidos.get(i).getValorTotal();		
			}
		return objRelatorio;
	}
	
	@Override
	public void actionPerformed(ActionEvent evento) {
		GerenciamentoController controller = new GerenciamentoController();
		String cmd = evento.getActionCommand();
		JFrame telaEscolhida = null;
		
		if(cmd.equals("cadastrar_funcionario")) {
			telaEscolhida = new CadastrarFuncionarioView();
		}else if(cmd.equals("cadastrar_usuario")) {
			telaEscolhida = new CadastrarUsuarioView();
		}else if(cmd.equals("emitir_relatorio")) {
			telaEscolhida = new EmitirRelatorioView();
		}
		
		else if(cmd.equals("listar")) {
			telaEscolhida = this;
			int ano = Integer.parseInt((String) cbxAno.getSelectedItem());
			int mes = Integer.parseInt((String) cbxMes.getSelectedItem());
			List<Pedido> pedidos = controller.emitirRelatorio(ano, mes);
			
			Object[][] relatorio = listarPedidos(pedidos);
			
			lblFatura.setText(String.valueOf(fatura));
			fatura = 0;
			tblRelatorio = new JTable();
			tblRelatorio.setFillsViewportHeight(true);
			tblRelatorio.setModel(new DefaultTableModel(
				relatorio,
				new String[] {
					"Pedido", "Funcionario", "Cliente", "Inicio", "Fim", "Valor", "Status"
				}));
			tblRelatorio.setBounds(164, 50, 700, 200);
			scrollPane.setViewportView(tblRelatorio);
		}else if(cmd.equals("voltar")) {
			telaEscolhida = this.parent;
		}
		tblRelatorio.getColumnModel().getColumn(3).setMinWidth(120);
		tblRelatorio.getColumnModel().getColumn(4).setMinWidth(120);
		
		telaEscolhida.setVisible(true);
		
		if(!cmd.equals("listar")) {
			this.dispose();
		}
	}
}
