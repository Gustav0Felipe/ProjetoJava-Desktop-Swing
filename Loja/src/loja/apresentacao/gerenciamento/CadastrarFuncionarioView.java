package loja.apresentacao.gerenciamento;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import loja.apresentacao.model.MenuGerenciamento;
import loja.controller.GerenciamentoController;
import loja.util.LojaDocumentFilter;

public class CadastrarFuncionarioView extends MenuGerenciamento{

	private static final long serialVersionUID = -8553963319496324615L;
	private JFormattedTextField txfTelefone;
	private JTextField txtEndereco;
	private JTextField txtNome;
	private JTextField txtEmail;
	private JTextField txtSalario;
	private JTextField txtCargo;
	private JLabel lblCargo;
	private JLabel lblSalario;

	public CadastrarFuncionarioView() {
		
		btnCadFunc.setBackground(new Color(255, 255, 255));

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
		lblEmail.setBounds(184, 92, 59, 14);
		getContentPane().add(lblEmail);
		
		lblCargo = new JLabel("Cargo:");
		lblCargo.setBounds(184, 117, 46, 14);
		getContentPane().add(lblCargo);
		
		lblSalario = new JLabel("Salario:");
		lblSalario.setBounds(184, 142, 46, 14);
		getContentPane().add(lblSalario);
		
		txtNome = LojaDocumentFilter.createFilteredFieldString(255);
		txtNome.setColumns(10);
		txtNome.setBounds(267, 14, 107, 20);
		getContentPane().add(txtNome);
		
		txtEndereco = LojaDocumentFilter.createFilteredFieldLimit(255);
		txtEndereco.setColumns(10);
		txtEndereco.setBounds(267, 39, 107, 20);
		getContentPane().add(txtEndereco);
		
		
		try {
			txfTelefone = new JFormattedTextField(new MaskFormatter("(##)#####-####"));
		} catch (ParseException e) {
			assert false : "Padrão de telefone errado.";
		}
		txfTelefone.setColumns(10);
		txfTelefone.setBounds(267, 64, 107, 20);
		getContentPane().add(txfTelefone);
		
		txtEmail = LojaDocumentFilter.createFilteredFieldLimit(100);
		txtEmail.setColumns(10);
		txtEmail.setBounds(267, 89, 107, 20);
		getContentPane().add(txtEmail);
		
		txtCargo = LojaDocumentFilter.createFilteredFieldLimit(50);
		txtCargo.setColumns(10);
		txtCargo.setBounds(267, 114, 107, 20);
		getContentPane().add(txtCargo);
		
		txtSalario = LojaDocumentFilter.createFilteredFieldDouble();
		txtSalario.setColumns(10);
		txtSalario.setBounds(267, 139, 107, 20);
		getContentPane().add(txtSalario);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBounds(267, 170, 107, 23);
		getContentPane().add(btnConfirmar);
		btnConfirmar.setActionCommand("confirmar");
		btnConfirmar.addActionListener(this);
	}


	@Override
	public void actionPerformed(ActionEvent evento) {

		String cmd = evento.getActionCommand();
		JFrame telaEscolhida = null;
		
		
		if(cmd.equals("cadastrar_funcionario")) {
			telaEscolhida = new CadastrarFuncionarioView();
		}else if(cmd.equals("cadastrar_usuario")) {
			telaEscolhida = new CadastrarUsuarioView();
		}else if(cmd.equals("emitir_relatorio")) {
			telaEscolhida = new EmitirRelatorioView();
		}else if(cmd.equals("voltar")) {
			telaEscolhida = this.parent;
		}
		else if(cmd.equals("confirmar")) {
			telaEscolhida = new CadastrarFuncionarioView();
			
			String nome = txtNome.getText().strip();
			String endereco = txtEndereco.getText().strip();
			String telefone = txfTelefone.getText().strip();
			String email = txtEmail.getText().strip();
			String cargo = txtCargo.getText().strip();
			
			
		
			if(!nome.equals("") && !endereco.equals("") && !telefone.equals("") && !email.equals("") && !cargo.equals("") && !txtSalario.getText().strip().equals("")) {
			Double salario = Double.parseDouble(txtSalario.getText().strip());
				
			GerenciamentoController controller = new GerenciamentoController();
			controller.cadastrarFuncionario(nome, endereco, telefone, email, cargo, salario);
			}else {
				JOptionPane.showMessageDialog(null, "Todos os valores são obrigatorios", "Falha de Cadastro", JOptionPane.ERROR_MESSAGE);
			}
		}
		telaEscolhida.setVisible(true);
		this.dispose();
	}
}
