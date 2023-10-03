package loja.apresentacao.principal;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import loja.apresentacao.model.MenuGerenciamento;
import loja.controller.LoginController;
import loja.negocio.Funcionario;
import loja.util.LojaDocumentFilter;

public class LoginView extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = -3461382428199581632L;
	private JPasswordField txtSenha;
	private JTextField txtUsuario;
	
	public static void main(String[] args) {
		JFrame tela = new LoginView();
		tela.setVisible(true);
	}
	
	public LoginView() {
		//Lembrar do setSize e setLocationRelativeTo
		setSize(new Dimension(260,220));
		
		setTitle("Login");
		setResizable(false);
		getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.setBounds(49, 129, 118, 23);
		btnEntrar.addActionListener(this);
		btnEntrar.setActionCommand("entrar");
		panel.add(btnEntrar);
		
		
		txtUsuario = LojaDocumentFilter.createFilteredFieldLimit(50);
		txtUsuario.setBounds(98, 54, 86, 20);
		panel.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		txtSenha = LojaDocumentFilter.createPassWordFieldLimit(30);
		txtSenha.setColumns(10);
		txtSenha.setBounds(98, 93, 86, 20);
		panel.add(txtSenha);
		
		
		JLabel lblUsuario = new JLabel("Usuário:");
		lblUsuario.setBounds(33, 57, 59, 14);
		panel.add(lblUsuario);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(33, 96, 59, 14);
		panel.add(lblSenha);
		
		//Lembrar
		setLocationRelativeTo(null);
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String usuario = txtUsuario.getText();
		String senha = String.valueOf(txtSenha.getPassword());		
		
		
		//Antes valores null davam erro, MAS ERA POR QUE O CONTROLLER RETORNAVA UM JFRAME NULO E TENTAVA MOSTRAR.
		if(!usuario.equals("") && !senha.equals("")) {
			LoginController controller = new LoginController();
			
			Funcionario funcionario = controller.validarFuncionario(usuario, senha, this);//(usuario, senha, this)
		
			Boolean admin = false;
			admin = controller.validarAdmin(usuario, senha);
			
			
			if(funcionario != null) {
				int idSessao = controller.iniciarSessao(funcionario);
				
				JFrame menu = new MenuOperadorView(this, funcionario, idSessao);
				
				this.setVisible(false);
				menu.setVisible(true);
			}
			else if(admin != null && admin) {
				MenuGerenciamento menuGerencial = new MenuGerenciamento();
				this.dispose();
				menuGerencial.setVisible(true);
			}
			else  {
				JOptionPane.showMessageDialog(null, "Conta não encontrada.", "Falha login", JOptionPane.ERROR_MESSAGE);
			}
			
		}else {
			JOptionPane.showMessageDialog(null, "Usuario ou senha não informado.", "Falha login", JOptionPane.ERROR_MESSAGE);
		}
		
		this.txtSenha.setText("");
		this.txtUsuario.setText("");
	}
}
