package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JTextPane;
import javax.swing.border.CompoundBorder;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

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
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaFTP.class.getResource("/img/logoProyecto.png")));
		frame.getContentPane().setBackground(new Color(0, 64, 128));
		frame.setBackground(new Color(0, 64, 128));
		frame.setBounds(100, 100, 500, 500);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		JPanel panel_1 = new JPanel(null);
		frame.setContentPane(panel_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Menu.class.getResource("/img/menu.png")));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 36));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(187, 58, 71, 60);
		frame.getContentPane().add(lblNewLabel);
		
		btnFTP = new JButton("Servidor FTP");
		btnFTP.setHorizontalAlignment(SwingConstants.LEFT);
		btnFTP.setFocusPainted(false);
		btnFTP.setIcon(new ImageIcon(Menu.class.getResource("/img/iconftp.png")));
		btnFTP.setBackground(new Color(0, 64, 128));
		btnFTP.setBorderPainted(false);
		btnFTP.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnFTP.setForeground(new Color(255, 255, 255));
		btnFTP.setBounds(84, 128, 430, 80);
		btnFTP.setFocusPainted(false);
		btnFTP.setContentAreaFilled(false);
		frame.getContentPane().add(btnFTP);
		
		btnCorreoElectronico = new JButton("Correo electronico");
		btnCorreoElectronico.setHorizontalAlignment(SwingConstants.LEFT);
		btnCorreoElectronico.setIcon(new ImageIcon(Menu.class.getResource("/img/iconemail.png")));
		btnCorreoElectronico.setBackground(new Color(0, 64, 128));
		btnCorreoElectronico.setForeground(new Color(255, 255, 255));
		btnCorreoElectronico.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnCorreoElectronico.setBorderPainted(false);
		btnCorreoElectronico.setBounds(84, 206, 430, 80);
		btnCorreoElectronico.setFocusPainted(false);
		btnCorreoElectronico.setContentAreaFilled(false);
		frame.getContentPane().add(btnCorreoElectronico);
		
		btnSalir = new JButton("Salir");
		btnSalir.setHorizontalAlignment(SwingConstants.LEFT);
		btnSalir.setIcon(new ImageIcon(Menu.class.getResource("/img/iconSalir.png")));
		btnSalir.setBackground(new Color(0, 64, 128));
		btnSalir.setForeground(new Color(255, 255, 255));
		btnSalir.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnSalir.setBounds(145, 311, 200, 80);
		btnSalir.setBorderPainted(false);
		btnSalir.setFocusPainted(false);
		btnSalir.setContentAreaFilled(false);
		frame.getContentPane().add(btnSalir);
		JLabel backgroundLabel = 	new JLabel(new ImageIcon(VentanaFTP.class.getResource("/img/fondo.jpg")));
		backgroundLabel.setBounds(-44, 0, 700, 500);
		
		
		        // Add the background label to the content pane
		        frame.getContentPane().add(backgroundLabel);
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
