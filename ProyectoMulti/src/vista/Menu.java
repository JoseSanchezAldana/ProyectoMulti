package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.border.CompoundBorder;
import javax.swing.JLabel;

public class Menu {

	public JFrame frame;
	private JButton btnFTP;
	private JButton btnCorreoElectronico;

	/**
	 * Create the application.
	 */
	public Menu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 64, 128));
		frame.setBackground(new Color(0, 64, 128));
		frame.setBounds(100, 100, 800, 600);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		btnFTP = new JButton("Servidor FTP");
		btnFTP.setBorderPainted(false);
		btnFTP.setFont(new Font("Tahoma", Font.BOLD, 32));
		btnFTP.setForeground(new Color(0, 64, 128));
		btnFTP.setBounds(216, 197, 350, 60);
		frame.getContentPane().add(btnFTP);
		
		btnCorreoElectronico = new JButton("Correo electronico");
		btnCorreoElectronico.setForeground(new Color(0, 64, 128));
		btnCorreoElectronico.setFont(new Font("Tahoma", Font.BOLD, 32));
		btnCorreoElectronico.setBorderPainted(false);
		btnCorreoElectronico.setBounds(216, 329, 350, 60);
		frame.getContentPane().add(btnCorreoElectronico);
		
		JLabel lblNewLabel = new JLabel("MENU");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 36));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(328, 87, 115, 60);
		frame.getContentPane().add(lblNewLabel);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JButton getBtnFTP() {
		return btnFTP;
	}

	public void setBtnFTP(JButton btnFTP) {
		this.btnFTP = btnFTP;
	}

	public JButton getBtnCorreoElectronico() {
		return btnCorreoElectronico;
	}

	public void setBtnCorreoElectronico(JButton btnCorreoElectronico) {
		this.btnCorreoElectronico = btnCorreoElectronico;
	}
	
	
}
