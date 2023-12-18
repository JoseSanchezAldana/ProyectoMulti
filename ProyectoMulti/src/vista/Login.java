package vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class Login {

	public JFrame frame;
	private JPasswordField passwordField;
	private JButton btnLogin;
	private JTextArea txtrUsuario;

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frame = new JFrame(); 
		frame.getContentPane().setBackground(new Color(0, 64, 128));
		frame.setBounds(100, 100, 400, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(133, 51, 97, 53);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("USUARIO");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(64, 114, 136, 28);
		frame.getContentPane().add(lblNewLabel_1);
		
		txtrUsuario = new JTextArea();
		txtrUsuario.setFont(new Font("Monospaced", Font.BOLD, 15));
		txtrUsuario.setBackground(new Color(255, 255, 255));
		txtrUsuario.setToolTipText("");
		txtrUsuario.setWrapStyleWord(true);
		txtrUsuario.setBounds(64, 156, 250, 30);
		frame.getContentPane().add(txtrUsuario);
		
		JLabel lblNewLabel_2 = new JLabel("CONTRASEÑA");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setBounds(64, 206, 136, 28);
		frame.getContentPane().add(lblNewLabel_2);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		passwordField.setHorizontalAlignment(SwingConstants.LEFT);
		passwordField.setBackground(new Color(255, 255, 255));
		passwordField.setBounds(64, 244, 250, 30);
		frame.getContentPane().add(passwordField);
		
		btnLogin = new JButton("Login");
		btnLogin.setBorderPainted(false);
		btnLogin.setForeground(new Color(0, 64, 128));
		btnLogin.setBackground(new Color(255, 255, 255));
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnLogin.setBounds(124, 298, 125, 40);
		frame.getContentPane().add(btnLogin);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setForeground(new Color(0, 64, 128));
		btnSalir.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSalir.setBorderPainted(false);
		btnSalir.setBackground(Color.WHITE);
		btnSalir.setBounds(124, 363, 125, 40);
		frame.getContentPane().add(btnSalir);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}

	public void setPasswordField(JPasswordField passwordField) {
		this.passwordField = passwordField;
	}

	public JButton getBtnNewButton() {
		return btnLogin;
	}

	public void setBtnNewButton(JButton btnNewButton) {
		this.btnLogin = btnNewButton;
	}

	public JTextArea getTxtrUsuario() {
		return txtrUsuario;
	}

	public void setTxtrUsuario(JTextArea txtrUsuario) {
		this.txtrUsuario = txtrUsuario;
	}
}
