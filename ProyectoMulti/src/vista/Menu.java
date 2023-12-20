package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.border.CompoundBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class Menu {

	public JFrame frame;
	private JButton btnFTP;
	private JButton btnCorreoElectronico;
	private JButton btnSalir;

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
		frame.setBounds(100, 100, 500, 500);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("MENU");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 36));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(185, 38, 115, 60);
		frame.getContentPane().add(lblNewLabel);
		
		btnFTP = new JButton("Servidor FTP");
		btnFTP.setFocusPainted(false);
		btnFTP.setIcon(new ImageIcon(getClass().getResource("/img/iconFTP.png")));
		btnFTP.setBackground(new Color(0, 64, 128));
		btnFTP.setBorderPainted(false);
		btnFTP.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnFTP.setForeground(new Color(255, 255, 255));
		btnFTP.setBounds(-14, 128, 430, 80);
		btnFTP.setFocusPainted(false);
		btnFTP.setContentAreaFilled(false);
		frame.getContentPane().add(btnFTP);
		
		btnCorreoElectronico = new JButton("Correo electronico");
		btnCorreoElectronico.setIcon(new ImageIcon(getClass().getResource("/img/iconEmail.png")));
		btnCorreoElectronico.setBackground(new Color(0, 64, 128));
		btnCorreoElectronico.setForeground(new Color(255, 255, 255));
		btnCorreoElectronico.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnCorreoElectronico.setBorderPainted(false);
		btnCorreoElectronico.setBounds(26, 218, 430, 80);
		btnCorreoElectronico.setFocusPainted(false);
		btnCorreoElectronico.setContentAreaFilled(false);
		frame.getContentPane().add(btnCorreoElectronico);
		
		btnSalir = new JButton("Salir");
		btnSalir.setIcon(new ImageIcon(getClass().getResource("/img/iconSalir.png")));
		btnSalir.setBackground(new Color(0, 64, 128));
		btnSalir.setForeground(new Color(255, 255, 255));
		btnSalir.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnSalir.setBounds(128, 327, 200, 80);
		btnSalir.setBorderPainted(false);
		btnSalir.setFocusPainted(false);
		btnSalir.setContentAreaFilled(false);
		frame.getContentPane().add(btnSalir);
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

	public JButton getBtnSalir() {
		return btnSalir;
	}

	public void setBtnSalir(JButton btnSalir) {
		this.btnSalir = btnSalir;
	}
	
	
}
