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
	private JButton btnNewButton;
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
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(342, 43, 97, 73);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("USUARIO");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(123, 148, 136, 28);
		frame.getContentPane().add(lblNewLabel_1);
		
		txtrUsuario = new JTextArea();
		txtrUsuario.setFont(new Font("Monospaced", Font.PLAIN, 21));
		txtrUsuario.setBackground(new Color(255, 255, 255));
		txtrUsuario.setToolTipText("");
		txtrUsuario.setWrapStyleWord(true);
		txtrUsuario.setBounds(123, 190, 500, 40);
		frame.getContentPane().add(txtrUsuario);
		
		JLabel lblNewLabel_2 = new JLabel("CONTRASEÑA");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setBounds(123, 240, 136, 28);
		frame.getContentPane().add(lblNewLabel_2);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 21));
		passwordField.setHorizontalAlignment(SwingConstants.LEFT);
		passwordField.setBackground(new Color(255, 255, 255));
		passwordField.setBounds(123, 278, 500, 40);
		frame.getContentPane().add(passwordField);
		
		btnNewButton = new JButton("Login");
		btnNewButton.setBorderPainted(false);
		btnNewButton.setForeground(new Color(0, 64, 128));
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(333, 370, 125, 40);
		frame.getContentPane().add(btnNewButton);
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
		return btnNewButton;
	}

	public void setBtnNewButton(JButton btnNewButton) {
		this.btnNewButton = btnNewButton;
	}

	public JTextArea getTxtrUsuario() {
		return txtrUsuario;
	}

	public void setTxtrUsuario(JTextArea txtrUsuario) {
		this.txtrUsuario = txtrUsuario;
	}
	
	
}
