package vista;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JTree;
import javax.swing.ScrollPaneConstants;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import conexion.ConexionFTP;
import modelo.Modelo;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Font;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class VentanaFTP {

	public JFrame frame;
	private JButton btnSubirArchivo;
	private JButton btnDescargarArchivo;
	private JButton btnSalir;
	private JButton btnBorrarCarpeta;
	private JButton btnCrearCarpeta;
	private ConexionFTP conexionFTP;
	private Modelo modelo;
	private static DefaultMutableTreeNode root;
	private static DefaultTreeModel modeloTree;
	private JTree tree;
	private JLabel rutaSeleccionada;
	private JScrollPane scroll;
	/**
	 * Create the application.
	 */
	public VentanaFTP(ConexionFTP conexionFTP, Modelo modelo) {
		this.conexionFTP = conexionFTP;
		this.modelo = modelo;
		initialize(conexionFTP, modelo);
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(ConexionFTP conexionFTP, Modelo modelo) {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 64, 128));
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);

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

		JScrollPane scrollPane = crearRaiz(conexionFTP.getCliente(), modelo);
		scrollPane.setBounds(72, 86, 287, 298);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		frame.getContentPane().add(scrollPane);
		
		rutaSeleccionada = new JLabel("");
		rutaSeleccionada.setForeground(new Color(255, 255, 255));
		rutaSeleccionada.setFont(new Font("Tahoma", Font.BOLD, 18));
		rutaSeleccionada.setBounds(296, 469, 392, 19);
		frame.getContentPane().add(rutaSeleccionada);
	}

	public JScrollPane crearRaiz(FTPClient cliente, Modelo modelo) {
		root = new DefaultMutableTreeNode(modelo.getDirectorioInicial());
		modeloTree = new DefaultTreeModel(root);
		crear(root, cliente, modelo.getDirectorioInicial());
		tree = new JTree();
		tree.setModel(modeloTree);
		scroll = new JScrollPane(tree);
		return scroll;

	}

	private void crear(DefaultMutableTreeNode nodo, FTPClient cliente, String directorioInicial) {
		try {
			// cambiamos de posicion el directorio
			if (cliente.changeWorkingDirectory(directorioInicial)) {

				// Obtenemos los hijos para ir creando los nodos
				FTPFile[] files = cliente.listFiles();

				if (files != null) {

					int contador = 0;
					for (FTPFile f : files) {// recorremos los ficheros y las carpetas hijas
						DefaultMutableTreeNode hijo = new DefaultMutableTreeNode(f.getName()); // creamos un nodo con su
																								// nombre
						hijo.setAllowsChildren(true);
						modeloTree.insertNodeInto(hijo, nodo, contador);// lo insertamos en su padre
						contador++;// para cambiar la posicion de los hijos
						if (f.isDirectory()) {// si es directorio llamada recursiva
							crear(hijo, cliente, "./" + f.getName());
						}
					}
				} else {
					System.out.println("pepe");
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void actualizarArbol() {
	    DefaultMutableTreeNode root = new DefaultMutableTreeNode(modelo.getDirectorioInicial());
	    DefaultTreeModel nuevoModelo = new DefaultTreeModel(root);
	    crear(root, conexionFTP.getCliente(), modelo.getDirectorioInicial());

	    // Actualiza el modelo del árbol
	    tree.setModel(nuevoModelo);

	    // Notifica al JTree para que vuelva a pintarse
	    ((DefaultTreeModel) tree.getModel()).reload();
	}
	
	
	public JLabel getRutaSeleccionada() {
		return rutaSeleccionada;
	}

	public void setRutaSeleccionada(JLabel rutaSeleccionada) {
		this.rutaSeleccionada = rutaSeleccionada;
	}


	public JTree getTree() {
		return tree;
	}

	public void setTree(JTree tree) {
		this.tree = tree;
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


	public JScrollPane getScroll() {
		return scroll;
	}


	public void setScroll(JScrollPane scroll) {
		this.scroll = scroll;
	}
	
}
