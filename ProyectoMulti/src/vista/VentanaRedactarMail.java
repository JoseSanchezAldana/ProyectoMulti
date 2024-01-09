package vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaRedactarMail {

	public JFrame frame;
	private JTextArea txtDe;
	private JButton btnEnviar;
	private JButton btnSalir;
	private JTextArea txtCC;
	private JTextArea txtCCO;
	private JTextArea txtAsunto;

	/**
	 * Create the application.
	 */
	public VentanaRedactarMail() {
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
		
		JLabel labelDe = new JLabel("De:");
		labelDe.setForeground(new Color(255, 255, 255));
		labelDe.setFont(new Font("Tahoma", Font.BOLD, 15));
		labelDe.setBounds(72, 51, 39, 40);
		frame.getContentPane().add(labelDe);
		
		txtDe = new JTextArea();
		txtDe.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtDe.setForeground(new Color(255, 255, 255));
		txtDe.setOpaque(false);
		txtDe.setFont(new Font("Monospaced", Font.BOLD, 15));
		txtDe.setBackground(new Color(255, 255, 255));
		txtDe.setToolTipText("");
		txtDe.setWrapStyleWord(true);
		txtDe.setBounds(115, 59, 495, 30);
		frame.getContentPane().add(txtDe);
		
		JLabel labelCC = new JLabel("CC:");
		labelCC.setForeground(Color.WHITE);
		labelCC.setFont(new Font("Tahoma", Font.BOLD, 15));
		labelCC.setBounds(72, 101, 39, 40);
		panel_1.add(labelCC);
		
		txtCC = new JTextArea();
		txtCC.setWrapStyleWord(true);
		txtCC.setToolTipText("");
		txtCC.setOpaque(false);
		txtCC.setForeground(Color.WHITE);
		txtCC.setFont(new Font("Monospaced", Font.BOLD, 15));
		txtCC.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtCC.setBackground(Color.WHITE);
		txtCC.setBounds(115, 109, 495, 30);
		panel_1.add(txtCC);
		
		JLabel labelCCO = new JLabel("CCO:");
		labelCCO.setForeground(Color.WHITE);
		labelCCO.setFont(new Font("Tahoma", Font.BOLD, 15));
		labelCCO.setBounds(72, 151, 39, 40);
		panel_1.add(labelCCO);
		
		txtCCO = new JTextArea();
		txtCCO.setWrapStyleWord(true);
		txtCCO.setToolTipText("");
		txtCCO.setOpaque(false);
		txtCCO.setForeground(Color.WHITE);
		txtCCO.setFont(new Font("Monospaced", Font.BOLD, 15));
		txtCCO.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtCCO.setBackground(Color.WHITE);
		txtCCO.setBounds(118, 159, 492, 30);
		panel_1.add(txtCCO);
		
		JTextArea txtMensaje = new JTextArea();
		txtMensaje.setWrapStyleWord(true);
		txtMensaje.setToolTipText("");
		txtMensaje.setOpaque(false);
		txtMensaje.setForeground(Color.WHITE);
		txtMensaje.setFont(new Font("Monospaced", Font.BOLD, 15));
		txtMensaje.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtMensaje.setBackground(Color.WHITE);
		txtMensaje.setBounds(72, 251, 538, 114);
		panel_1.add(txtMensaje);
		
		JLabel labelAsunto = new JLabel("ASUNTO:");
		labelAsunto.setForeground(Color.WHITE);
		labelAsunto.setFont(new Font("Tahoma", Font.BOLD, 15));
		labelAsunto.setBounds(72, 196, 70, 40);
		panel_1.add(labelAsunto);
		
		txtAsunto = new JTextArea();
		txtAsunto.setWrapStyleWord(true);
		txtAsunto.setToolTipText("");
		txtAsunto.setOpaque(false);
		txtAsunto.setForeground(Color.WHITE);
		txtAsunto.setFont(new Font("Monospaced", Font.BOLD, 15));
		txtAsunto.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtAsunto.setBackground(Color.WHITE);
		txtAsunto.setBounds(152, 204, 458, 30);
		panel_1.add(txtAsunto);
		
		btnEnviar = new JButton("Enviar");
		btnEnviar.setIcon(new ImageIcon(VentanaRedactarMail.class.getResource("/img/enviarMail.png")));
		btnEnviar.setOpaque(false);
		btnEnviar.setHorizontalAlignment(SwingConstants.LEFT);
		btnEnviar.setForeground(Color.WHITE);
		btnEnviar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnEnviar.setFocusPainted(false);
		btnEnviar.setContentAreaFilled(false);
		btnEnviar.setBorderPainted(false);
		btnEnviar.setBackground(new Color(0, 64, 128));
		btnEnviar.setBounds(324, 387, 131, 30);
		panel_1.add(btnEnviar);
		
		btnSalir = new JButton("Salir");
		btnSalir.setIcon(new ImageIcon(VentanaRedactarMail.class.getResource("/img/salida32.png")));
		btnSalir.setOpaque(false);
		btnSalir.setHorizontalAlignment(SwingConstants.LEFT);
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSalir.setFocusPainted(false);
		btnSalir.setContentAreaFilled(false);
		btnSalir.setBorderPainted(false);
		btnSalir.setBackground(new Color(0, 64, 128));
		btnSalir.setBounds(479, 387, 131, 30);
		panel_1.add(btnSalir);
		
		JLabel backgroundLabel = new JLabel(new ImageIcon(VentanaFTP.class.getResource("/img/fondo.jpg")));
		backgroundLabel.setBounds(-14, -17, 700, 500);
		frame.getContentPane().add(backgroundLabel);
		JPanel panel = new JPanel();
		panel.setBounds(44, 28, 10, 10);
		panel_1.add(panel);
		
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JTextArea getTxtDe() {
		return txtDe;
	}

	public void setTxtDe(JTextArea txtDe) {
		this.txtDe = txtDe;
	}

	public JButton getBtnEnviar() {
		return btnEnviar;
	}

	public void setBtnEnviar(JButton btnEnviar) {
		this.btnEnviar = btnEnviar;
	}

	public JButton getBtnSalir() {
		return btnSalir;
	}

	public void setBtnSalir(JButton btnSalir) {
		this.btnSalir = btnSalir;
	}

	public JTextArea getTxtCC() {
		return txtCC;
	}

	public void setTxtCC(JTextArea txtCC) {
		this.txtCC = txtCC;
	}

	public JTextArea getTxtCCO() {
		return txtCCO;
	}

	public void setTxtCCO(JTextArea txtCCO) {
		this.txtCCO = txtCCO;
	}

	public JTextArea getTxtAsunto() {
		return txtAsunto;
	}

	public void setTxtAsunto(JTextArea txtAsunto) {
		this.txtAsunto = txtAsunto;
	}
}
