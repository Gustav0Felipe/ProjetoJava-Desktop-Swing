package loja.apresentacao.operador;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import loja.apresentacao.model.MenuOperador;
import loja.controller.CaixaController;
import loja.controller.LoginController;
import loja.negocio.Funcionario;
import loja.util.LojaDocumentFilter;

public class CadastrarClienteView extends MenuOperador implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4542811415950056447L;
	private JFormattedTextField txfTelefone;
	private JTextField txtEndereco;
	private JTextField txtNome;
	private JTextField txtEmail;

	
	public CadastrarClienteView(JFrame parent, int idSessao, Funcionario funcionario) {
		this.parent = parent;
		this.idSessao = idSessao;
		this.funcionario = funcionario;
		
		btnCadCliente.setBackground(new Color(255, 255, 255));
		
		getContentPane().setLayout(null);
		
		setResizable(false);
		setSize(new Dimension(598, 300));
		setTitle("Cadastro de Cliente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(184, 17, 59, 14);
		getContentPane().add(lblNome);
		
		JLabel lblEndereco = new JLabel("Endereço:");
		lblEndereco.setBounds(184, 42, 59, 14);
		getContentPane().add(lblEndereco);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(184, 67, 59, 14);
		getContentPane().add(lblTelefone);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(184, 90, 59, 14);
		getContentPane().add(lblEmail);
		
		txtNome = LojaDocumentFilter.createFilteredFieldString(255);
		txtNome.setColumns(10);
		txtNome.setBounds(272, 14, 100, 20);
		getContentPane().add(txtNome);
		
		txtEndereco = LojaDocumentFilter.createFilteredFieldLimit(255);
		txtEndereco.setColumns(10);
		txtEndereco.setBounds(272, 39, 100, 20);
		getContentPane().add(txtEndereco);
		
		
		try {
			txfTelefone = new JFormattedTextField(new MaskFormatter("(##)#####-####"));
		} catch (ParseException e) {
			assert false : "Padrão de telefone errado.";
		}
		txfTelefone.setColumns(10);
		txfTelefone.setBounds(272, 64, 100, 20);
		getContentPane().add(txfTelefone);
		
		txtEmail = LojaDocumentFilter.createFilteredFieldLimit(100);
		txtEmail.setColumns(10);
		txtEmail.setBounds(272, 87, 100, 20);
		getContentPane().add(txtEmail);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBounds(184, 113, 188, 23);
		getContentPane().add(btnConfirmar);
		btnConfirmar.setActionCommand("confirmar");
		btnConfirmar.addActionListener(this);
		
		setLocationRelativeTo(null);		
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
		
		
		else if(cmd.equals("confirmar")){
			telaEscolhida = new CadastrarClienteView(this.parent, idSessao, funcionario);

			String nome = txtNome.getText().strip();
			String email = txtEmail.getText().strip();
			String telefone = txfTelefone.getText().strip();
			String endereco = txtEndereco.getText().strip();
			
			if(!nome.equals("") && !email.equals("") && !telefone.equals("") && !endereco.equals("")) {
			
			CaixaController controller = new CaixaController();
			
			controller.cadastrarCliente(nome, email, telefone, endereco, funcionario.getId());
			JOptionPane.showMessageDialog(null, "Cliente Cadastrado com Sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(null, "Todos os valores são obrigatorios", "Falha de Cadastro", JOptionPane.ERROR_MESSAGE);
			}
			
		}
		
		//this.setVisible(false);
		telaEscolhida.setVisible(true);
		txtNome.setText("");
		txtEndereco.setText("");
		txfTelefone.setText("");
		txtEmail.setText("");
		this.dispose();
	}
}
