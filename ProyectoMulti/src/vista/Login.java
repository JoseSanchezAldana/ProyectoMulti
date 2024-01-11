package vista;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.CompoundBorder;
import java.awt.Cursor;

public class Login {

	public JFrame frame;
	private JPasswordField passwordField;
	private JButton btnLogin;
	private JTextField txtrUsuario;
	private JButton btnSalir;

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
		frame = new JFrame("PROYECTO MULTIDISCIPLINAR 23/24"); 
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaFTP.class.getResource("/img/logoProyecto.png")));
		frame.setBounds(100, 100, 400, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
        ImageIcon backgroundImage = new ImageIcon(VentanaFTP.class.getResource("/img/logoProyecto.png"));
        JPanel panel_1 = new JPanel(null);
        frame.setContentPane(panel_1);
		
		txtrUsuario = new JTextField();
		txtrUsuario.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtrUsuario.setForeground(new Color(255, 255, 255));
		txtrUsuario.setOpaque(false);
		txtrUsuario.setFont(new Font("Monospaced", Font.BOLD, 15));
		txtrUsuario.setBackground(new Color(255, 255, 255));
		txtrUsuario.setToolTipText("");
		
		//txtrUsuario.setWrapStyleWord(true);
		
		txtrUsuario.setBounds(72, 133, 250, 30);
		frame.getContentPane().add(txtrUsuario);
		
		btnLogin = new JButton("Login");
		btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLogin.setHorizontalAlignment(SwingConstants.LEFT);
		btnLogin.setIcon(new ImageIcon(Login.class.getResource("/img/login.png")));
		btnLogin.setBorderPainted(false);
		btnLogin.setForeground(new Color(255, 255, 255));
		btnLogin.setBackground(new Color(48, 97, 112));
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnLogin.setBounds(118, 276, 125, 40);
		btnLogin.setFocusPainted(false);
		btnLogin.setContentAreaFilled(false);
		btnLogin.setOpaque(false);
		frame.getContentPane().add(btnLogin);
		
		JLabel lblNewLabel_1 = new JLabel("USUARIO");
		lblNewLabel_1.setIcon(new ImageIcon(Login.class.getResource("/img/usuario.png")));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(72, 81, 185, 40);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("CONTRASEÑA");
		lblNewLabel_2.setIcon(new ImageIcon(Login.class.getResource("/img/contrasena.png")));
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setBounds(72, 173, 185, 40);
		frame.getContentPane().add(lblNewLabel_2);
		
		passwordField = new JPasswordField();
		passwordField.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		passwordField.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		passwordField.setForeground(new Color(255, 255, 255));
		passwordField.setOpaque(false);
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		passwordField.setHorizontalAlignment(SwingConstants.LEFT);
		passwordField.setBackground(new Color(255, 255, 255));
		passwordField.setBounds(72, 221, 250, 30);
	
		passwordField.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyPressed(KeyEvent e) {
		        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
		            btnLogin.doClick();
		        }
		    }
		});
		
		frame.getContentPane().add(passwordField);
		
		btnSalir = new JButton("Salir");
		btnSalir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSalir.setHorizontalAlignment(SwingConstants.LEFT);
		btnSalir.setIcon(new ImageIcon(Login.class.getResource("/img/salida32.png")));
		btnSalir.setBorderPainted(false);
		btnSalir.setBorder(new CompoundBorder());
		btnSalir.setForeground(new Color(255, 255, 255));
		btnSalir.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSalir.setBackground(new Color(48, 97, 112));
		btnSalir.setBounds(132, 340, 125, 40);
		btnSalir.setOpaque(false);
		btnSalir.setContentAreaFilled(false);
		btnSalir.setOpaque(false);
		frame.getContentPane().add(btnSalir);
		
        // Create a JLabel with the background image
        JLabel backgroundLabel = new JLabel(new ImageIcon(Login.class.getResource("/img/fondo.jpg")));
        backgroundLabel.setBounds(0, 0, 386, 463);
		        
		        
        // Add the background label to the content pane
        frame.getContentPane().add(backgroundLabel);
	}

	public JButton getBtnSalir() {
		return btnSalir;
	}

	public void setBtnSalir(JButton btnSalir) {
		this.btnSalir = btnSalir;
	}

	public JButton getBtnLogin() {
		return btnLogin;
	}

	public void setBtnLogin(JButton btnLogin) {
		this.btnLogin = btnLogin;
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

	public JTextField getTxtrUsuario() {
		return txtrUsuario;
	}

	public void setTxtrUsuario(JTextField txtrUsuario) {
		this.txtrUsuario = txtrUsuario;
	}
}
