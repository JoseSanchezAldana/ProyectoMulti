/*
 * @proyect ProyectoMultidisciplinar_23/24
 * @author Grupo_4
 * @version 1.0
 */

package vista;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Toolkit;

import controlador.moverPantalla;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Cursor;

public class Menu {

	public JFrame frame;
	private JButton btnFTP;
	private JButton btnCorreoElectronico;
	private JButton btnSalir;

	/**
	 * Crea la ventana.
	 */
	public Menu() {
		initialize();
	}

	/**
	 * Inicializa los componentes de la ventana.
	 */
	private void initialize() {
		frame = new JFrame("Sesión iniciada con: ");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaFTP.class.getResource("/img/logoProyecto.png")));
		frame.getContentPane().setBackground(new Color(0, 64, 128));
		frame.setBackground(new Color(0, 64, 128));
		frame.setBounds(100, 100, 500, 500);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		JPanel panel_1 = new JPanel(null);
		frame.setContentPane(panel_1);
		moverPantalla.centrar(frame);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Menu.class.getResource("/img/menu.png")));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 36));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(187, 58, 71, 60);
		frame.getContentPane().add(lblNewLabel);
		
		btnFTP = new JButton("Servidor FTP");
		btnFTP.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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
		btnCorreoElectronico.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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
		btnSalir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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
