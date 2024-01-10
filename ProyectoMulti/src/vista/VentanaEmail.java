package vista;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.mail.Message;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import controlador.LeerBandejaMail;
import controlador.LoginEvent;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

import modelo.Modelo;
import modelo.DatosPanelBandeja;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class VentanaEmail {

	public JFrame frame;
	private JButton btnSalir;
	private JButton btnRedactar;
	private JButton btnRefrescar;
	private Runnable runRefresco;
	private Thread thRefresco;


	private Modelo model;

	/**
	 * Create the application.
	 */
	public VentanaEmail(Modelo model) {
		
		this.model = model;
		initialize();
		
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		runRefresco = new LeerBandejaMail(this, this.model);
		thRefresco = new Thread(runRefresco);
		
		frame = new JFrame("Sesión iniciada con: "+ model.getUsuario());
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
		
		btnRedactar = new JButton("Redactar");
		btnRedactar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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
		
		btnRefrescar = new JButton("Refrescar");
		btnRefrescar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRefrescar.setIcon(new ImageIcon(VentanaEmail.class.getResource("/img/refrescar.png")));
		btnRefrescar.setOpaque(false);
		btnRefrescar.setHorizontalAlignment(SwingConstants.LEFT);
		btnRefrescar.setForeground(Color.WHITE);
		btnRefrescar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnRefrescar.setFocusPainted(false);
		btnRefrescar.setContentAreaFilled(false);
		btnRefrescar.setBorderPainted(false);
		btnRefrescar.setBackground(new Color(0, 64, 128));
		btnRefrescar.setBounds(512, 45, 200, 30);
		panel_1.add(btnRefrescar);
		
		
		
		DefaultTableModel model = new DefaultTableModel();
		JTable table = new JTable(model);
		table.setModel(model);
		table.setAutoCreateRowSorter(true);
		table.setEnabled(false);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(281, 86, 354, 320);
		
		
		
		//List<DatosPanelBandeja> datos = LeerBandejaMail.ObtenerDatosPanelMensajes(messages);
		
		List<DatosPanelBandeja> datos = LeerBandejaMail.ObtenerTitulosMensajes(this.model.getUsuario(), LoginEvent.decrypt(this.model.getPasword(), this.model.getKey()));
		
		String[] encabezados = {"De: ", "Asunto: "};

		model.setColumnIdentifiers(encabezados);

		for (DatosPanelBandeja dato : datos) {
		    model.addRow(new Object[]{dato.getParade(), dato.getAsunto()});
		}
				
		
		panel_1.add(scrollPane);		
			
		
		
		JLabel backgroundLabel = new JLabel(new ImageIcon(VentanaFTP.class.getResource("/img/fondo.jpg")));
		backgroundLabel.setBounds(-14, -17, 700, 500);
		frame.getContentPane().add(backgroundLabel);
		JPanel panel = new JPanel();
		panel.setBounds(44, 28, 10, 10);
		panel_1.add(panel);	
		
		
	}
	
	//LLAMADA
	//LeerBandejaMail.LeerBandeja(this.model.getUsuario(), LoginEvent.decrypt(this.model.getPasword(), this.model.getKey()));
	
	private void PrimerRefrescar() {

		thRefresco.run();
	}
			

	public JButton getBtnRefrescar() {
		return btnRefrescar;
	}

	public void setBtnRefrescar(JButton btnRefrescar) {
		this.btnRefrescar = btnRefrescar;
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
