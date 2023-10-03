package loja.apresentacao.gerenciamento;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import loja.apresentacao.model.MenuGerenciamento;
import loja.controller.GerenciamentoController;
import loja.negocio.Funcionario;
import loja.util.LojaDocumentFilter;

public class CadastrarUsuarioView extends MenuGerenciamento{
	
	private static final long serialVersionUID = 1405991327848988899L;
	private JPasswordField passwordField;
	private JTextField txtUser;
	
	private GerenciamentoController controller = new GerenciamentoController();
	private List<Funcionario> funcionarios = controller.listarFuncionarios();
	private JComboBox<?> cbxFunc;
	
	public CadastrarUsuarioView() {
		
		btnCadUser.setBackground(new Color(255, 255, 255));

		
		JLabel lblUser = new JLabel("Usuário:");
		lblUser.setBounds(164, 18, 70, 14);
		getContentPane().add(lblUser);
		
		JLabel lblPass = new JLabel("Senha:");
		lblPass.setBounds(164, 50, 70, 14);
		getContentPane().add(lblPass);
		
		passwordField = LojaDocumentFilter.createPassWordFieldLimit(30);
		passwordField.setBounds(234, 47, 100, 20);
		getContentPane().add(passwordField);
		
		
		txtUser = LojaDocumentFilter.createFilteredFieldLimit(50);
		txtUser.setBounds(234, 15, 100, 20);
		getContentPane().add(txtUser);
		txtUser.setColumns(10);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBounds(234, 111, 100, 23);
		getContentPane().add(btnConfirmar);
		btnConfirmar.addActionListener(this);
		btnConfirmar.setActionCommand("confirmar");
		
		JLabel lblFunc = new JLabel("Funcionário:");
		lblFunc.setBounds(164, 85, 70, 14);
		getContentPane().add(lblFunc);
		
		cbxFunc = new JComboBox<Object>(comboFuncionarios());
		cbxFunc.setBounds(234, 81, 100, 22);
		getContentPane().add(cbxFunc);
	}
	
	private Object[] comboFuncionarios() {
		Object[] nomeFuncionarios = new Object[funcionarios.size()];;
		for(int i=0; i<funcionarios.size(); i++) {
			nomeFuncionarios[i] = funcionarios.get(i).getNome();
		}	
		return nomeFuncionarios;
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
			telaEscolhida = new CadastrarUsuarioView();
			if(!txtUser.getText().strip().equals("") && !passwordField.getPassword().toString().strip().equals("")) {
			GerenciamentoController controller = new GerenciamentoController();
			controller.cadastrarUsuario(txtUser.getText(), String.valueOf(passwordField.getPassword()), cbxFunc.getSelectedItem().toString());
			}else {JOptionPane.showMessageDialog(null, "Usuario ou Senha não especificado.", "Falha de Cadastro", JOptionPane.ERROR_MESSAGE);}
		}
		
		telaEscolhida.setVisible(true);
		this.dispose();
	}
}
