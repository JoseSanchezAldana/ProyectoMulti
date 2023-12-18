package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;

public class VentanaEmail {

	public JFrame frame;

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
		frame.setBackground(new Color(0, 64, 128));
		frame.getContentPane().setBackground(new Color(0, 64, 128));
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	}

}