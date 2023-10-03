package loja.apresentacao.principal;

import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import loja.apresentacao.model.MenuOperador;
import loja.negocio.Funcionario;

public class MenuOperadorView extends MenuOperador implements ActionListener {

	private static final long serialVersionUID = -7964848446622435372L;

	public MenuOperadorView(JFrame telaLogin, Funcionario funcionario, int idSessao) {
		this.parent = telaLogin;
		this.idSessao = idSessao;
		this.funcionario = funcionario;
		
		setTitle("Menu");
		
		JLabel lblNewLabel = new JLabel("Olá, " + funcionario.getNome());
		lblNewLabel.setBounds(251, 155, 201, 37);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(MenuOperadorView.class.getResource("/resources/icon-user.png")));
		lblNewLabel_1.setBounds(287, 46, 96, 110);
		getContentPane().add(lblNewLabel_1);
		
	
		setLocationRelativeTo(null);		
	}
}
