package loja.apresentacao.model;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import loja.apresentacao.gerenciamento.CadastrarFuncionarioView;
import loja.apresentacao.gerenciamento.CadastrarUsuarioView;
import loja.apresentacao.gerenciamento.EmitirRelatorioView;
import loja.apresentacao.principal.LoginView;

public class MenuGerenciamento extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 387398334661406168L;
	public JButton btnCadUser;
	public JFrame parent = new LoginView();
	public JButton btnCadFunc;
	public JButton btnEmitirRelatorio;
	
	public MenuGerenciamento() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		setSize(new Dimension(450, 300));
		
		setTitle("Menu de Gerenciamento");
		getContentPane().setLayout(null);
		
		btnCadFunc = new JButton("Cadastrar Funcionario");
		btnCadFunc.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnCadFunc.setBounds(10, 14, 144, 23);
		getContentPane().add(btnCadFunc);
		btnCadFunc.setActionCommand("cadastrar_funcionario");
		btnCadFunc.addActionListener(this);
		
		btnEmitirRelatorio = new JButton("Emitir Relatório");
		btnEmitirRelatorio.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnEmitirRelatorio.setBounds(10, 81, 144, 23);
		getContentPane().add(btnEmitirRelatorio);
		btnEmitirRelatorio.setActionCommand("emitir_relatorio");
		btnEmitirRelatorio.addActionListener(this);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnVoltar.setBounds(10, 227, 144, 23);
		getContentPane().add(btnVoltar);
		btnVoltar.setActionCommand("voltar");
		btnVoltar.addActionListener(this);

		btnCadUser = new JButton("Cadastrar Usuario");
		btnCadUser.setBounds(10, 46, 144, 23);
		btnCadUser.setFont(new Font("Tahoma", Font.PLAIN, 11));
		getContentPane().add(btnCadUser);
		btnCadUser.addActionListener(this);
		btnCadUser.setActionCommand("cadastrar_usuario");
		
		setLocationRelativeTo(null);
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
		
		telaEscolhida.setVisible(true);
		this.dispose();
	}
}
