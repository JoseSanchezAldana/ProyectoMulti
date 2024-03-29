/*
 * @proyect ProyectoMultidisciplinar_23/24
 * @author Grupo_4
 * @version 1.0
 */

package controlador;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import conexion.Conexion;
import conexion.ConexionFTP;
import modelo.Modelo;
import vista.Login;
import vista.Menu;
import vista.VentanaEmail;
import vista.VentanaFTP;

public class MenuEvent implements ActionListener {

	private Menu menu;
	private Modelo modelo;
	private Login login;
	private Conexion conexion;

	public MenuEvent(Menu menu, Modelo modelo, Login login, Conexion conexion) {
		this.menu = menu;
		this.modelo = modelo;
		this.login = login;
		this.conexion = conexion;
	}
	/**
	 * Registro del evento que se produce en el menú, dependiendo del botón que se seleccione se abrirá la ventana de correo, la del FTP o se saldrá del menú y se volverá a la ventana de iniciar sesión
	 */

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == menu.getBtnFTP()) {
			System.out.println(modelo.getPasword() + "    " + modelo.getUsuario());
			ConexionFTP conexionFTP = new ConexionFTP(modelo);

			if (conexionFTP.isLogin()) {
				VentanaFTP vtnFtp = new VentanaFTP(conexionFTP, modelo);
				vtnFtp.frame.setVisible(true);
				menu.frame.dispose();
				vtnFtp.getTree().addTreeSelectionListener(new SeleccionTree(vtnFtp));
				vtnFtp.getBtnSalir().addActionListener(new SalirFTPEvent(vtnFtp, menu));
				vtnFtp.getBtnBorrarCarpeta().addActionListener(new BorrarArchivoFTP(vtnFtp, conexionFTP.getCliente(), conexion, modelo));
				vtnFtp.getBtnCrearCarpeta().addActionListener(new CrearCarpetaFTP(vtnFtp, conexionFTP.getCliente(), conexion, modelo));
				vtnFtp.getBtnDescargarArchivo().addActionListener(new DescargarArchivoFTP(vtnFtp, conexionFTP.getCliente(), conexion, modelo));
				vtnFtp.getBtnSubirArchivo().addActionListener(new SubirArchivoFTP(vtnFtp, conexionFTP.getCliente(), conexion, modelo));
			}else {
				JOptionPane.showMessageDialog(null, "USUARIO NO DADO DE ALTA EN EL SERVICIO FTP", "ERROR CONECTAR FTP", JOptionPane.INFORMATION_MESSAGE);
			}
		} else if(e.getSource() == menu.getBtnCorreoElectronico()){
			menu.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			menu.getBtnCorreoElectronico().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

			VentanaEmail vtnEmail = new VentanaEmail(this.modelo);			
			vtnEmail.frame.setVisible(true);
			vtnEmail.getBtnRedactar().addActionListener(new EnviaMailEvent(vtnEmail, modelo));
			
			vtnEmail.getBtnSalir().addActionListener(er ->{
				vtnEmail.PararRefresco();
			});
			
			vtnEmail.getBtnSalir().addActionListener(new SalirEmail(vtnEmail, menu));
			
			vtnEmail.getBtnRefrescar().addActionListener(ex -> {
				vtnEmail.DespertarRefrescar();
			});
			
			menu.getBtnCorreoElectronico().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			menu.frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			menu.frame.dispose();
			
		}else if(e.getSource() == menu.getBtnSalir()) {
			menu.frame.dispose();
			login.frame.setVisible(true);
			login.getTxtrUsuario().setText("");
			login.getPasswordField().setText("");
		}
	}
	
	/**
	 * Comprueba si el usuario tiene o no una ruta seleccionada en el JTree
	 * @param vtnFtp Ventana del FTP
	 * @return True si se tiene una ruta seleccionada, false en caso contrario. 
	 */
	
	public static boolean comprobarRuta (VentanaFTP vtnFtp) {
		boolean estaVacio = false;
		if(vtnFtp.getRutaSeleccionada().getText().equalsIgnoreCase("")) {
			estaVacio = true;
		}
		return estaVacio;
	}
}
