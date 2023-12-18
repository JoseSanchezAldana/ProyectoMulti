package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JTree;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaFTP {

	public JFrame frame;
	private JButton btnSubirArchivo;
	private JButton btnDescargarArchivo;
	private JButton btnSalir;
	private JButton btnBorrarCarpeta;
	private JButton btnCrearCarpeta;

	/**
	 * Create the application.
	 */
	public VentanaFTP() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 64, 128));
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTree tree = new JTree();
		tree.setBounds(86, 86, 287, 298);
		frame.getContentPane().add(tree);
		
		JLabel lblNewLabel = new JLabel("Ip server: ");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(86, 38, 287, 29);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Usuario: ");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(456, 43, 250, 19);
		frame.getContentPane().add(lblNewLabel_1);
		
		btnSubirArchivo = new JButton("Subir archivo");
		btnSubirArchivo.setForeground(new Color(0, 64, 128));
		btnSubirArchivo.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSubirArchivo.setBorderPainted(false);
		btnSubirArchivo.setBackground(new Color(255, 255, 255));
		btnSubirArchivo.setBounds(456, 86, 150, 30);
		frame.getContentPane().add(btnSubirArchivo);
		
		btnDescargarArchivo = new JButton("Descargar archivo");
		btnDescargarArchivo.setForeground(new Color(0, 64, 128));
		btnDescargarArchivo.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnDescargarArchivo.setBorderPainted(false);
		btnDescargarArchivo.setBackground(Color.WHITE);
		btnDescargarArchivo.setBounds(456, 155, 150, 30);
		frame.getContentPane().add(btnDescargarArchivo);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSalir.setForeground(new Color(0, 64, 128));
		btnSalir.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSalir.setBorderPainted(false);
		btnSalir.setBackground(Color.WHITE);
		btnSalir.setBounds(456, 354, 150, 30);
		frame.getContentPane().add(btnSalir);
		
		btnBorrarCarpeta = new JButton("Borrar carpeta");
		btnBorrarCarpeta.setForeground(new Color(0, 64, 128));
		btnBorrarCarpeta.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnBorrarCarpeta.setBorderPainted(false);
		btnBorrarCarpeta.setBackground(Color.WHITE);
		btnBorrarCarpeta.setBounds(456, 287, 150, 30);
		frame.getContentPane().add(btnBorrarCarpeta);
		
		btnCrearCarpeta = new JButton("Crear carpeta");
		btnCrearCarpeta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCrearCarpeta.setForeground(new Color(0, 64, 128));
		btnCrearCarpeta.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnCrearCarpeta.setBorderPainted(false);
		btnCrearCarpeta.setBackground(Color.WHITE);
		btnCrearCarpeta.setBounds(456, 220, 150, 30);
		frame.getContentPane().add(btnCrearCarpeta);
		
		JLabel lblNewLabel_2 = new JLabel("Carpeta seleccionada: ");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_2.setBounds(86, 469, 527, 19);
		frame.getContentPane().add(lblNewLabel_2);
	}

	public JButton getBtnSubirArchivo() {
		return btnSubirArchivo;
	}

	public void setBtnSubirArchivo(JButton btnSubirArchivo) {
		this.btnSubirArchivo = btnSubirArchivo;
	}

	public JButton getBtnDescargarArchivo() {
		return btnDescargarArchivo;
	}

	public void setBtnDescargarArchivo(JButton btnDescargarArchivo) {
		this.btnDescargarArchivo = btnDescargarArchivo;
	}

	public JButton getBtnSalir() {
		return btnSalir;
	}

	public void setBtnSalir(JButton btnSalir) {
		this.btnSalir = btnSalir;
	}

	public JButton getBtnBorrarCarpeta() {
		return btnBorrarCarpeta;
	}

	public void setBtnBorrarCarpeta(JButton btnBorrarCarpeta) {
		this.btnBorrarCarpeta = btnBorrarCarpeta;
	}

	public JButton getBtnCrearCarpeta() {
		return btnCrearCarpeta;
	}

	public void setBtnCrearCarpeta(JButton btnCrearCarpeta) {
		this.btnCrearCarpeta = btnCrearCarpeta;
	}
	
	
}
