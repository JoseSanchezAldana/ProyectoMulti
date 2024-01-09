package vista;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaEmail {

	public JFrame frame;
	private JButton btnSalir;
	private JButton btnRedactar;

	/**
	 * Create the application.
	 */
	public VentanaEmail() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaFTP.class.getResource("/img/logoProyecto.png")));
		frame.getContentPane().setBackground(new Color(0, 64, 128));
		frame.setBounds(100, 100, 700, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		ImageIcon backgroundImage = new ImageIcon(VentanaFTP.class.getResource("/img/logoProyecto.png"));
		JPanel panel_1 = new JPanel(null);
		frame.setContentPane(panel_1);
        
        btnSalir = new JButton("Salir");
		btnSalir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSalir.setHorizontalAlignment(SwingConstants.LEFT);
		btnSalir.setIcon(new ImageIcon(VentanaFTP.class.getResource("/img/salida32.png")));
		btnSalir.setFocusPainted(false);
		btnSalir.setContentAreaFilled(false);
		btnSalir.setForeground(new Color(255, 255, 255));
		btnSalir.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSalir.setBorderPainted(false);
		btnSalir.setBackground(new Color(0, 64, 128));
		btnSalir.setBounds(44, 141, 200, 30);
		btnSalir.setOpaque(false);
		frame.getContentPane().add(btnSalir);
		
		JPanel panelBandeja = new JPanel();
		panelBandeja.setBounds(307, 58, 345, 373);
		panel_1.add(panelBandeja);
		
		btnRedactar = new JButton("Redactar");
		btnRedactar.setIcon(new ImageIcon(VentanaEmail.class.getResource("/img/lapiz.png")));
		btnRedactar.setOpaque(false);
		btnRedactar.setHorizontalAlignment(SwingConstants.LEFT);
		btnRedactar.setForeground(Color.WHITE);
		btnRedactar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnRedactar.setFocusPainted(false);
		btnRedactar.setContentAreaFilled(false);
		btnRedactar.setBorderPainted(false);
		btnRedactar.setBackground(new Color(0, 64, 128));
		btnRedactar.setBounds(44, 86, 200, 30);
		panel_1.add(btnRedactar);
		
		JLabel backgroundLabel = new JLabel(new ImageIcon(VentanaFTP.class.getResource("/img/fondo.jpg")));
		backgroundLabel.setBounds(-14, -17, 700, 500);
		frame.getContentPane().add(backgroundLabel);
		JPanel panel = new JPanel();
		panel.setBounds(44, 28, 10, 10);
		panel_1.add(panel);
		
	}

	public JButton getBtnSalir() {
		return btnSalir;
	}

	public void setBtnSalir(JButton btnSalir) {
		this.btnSalir = btnSalir;
	}

	public JButton getBtnRedactar() {
		return btnRedactar;
	}

	public void setBtnRedactar(JButton btnRedactar) {
		this.btnRedactar = btnRedactar;
	}
}
