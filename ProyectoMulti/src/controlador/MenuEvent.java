package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

	public MenuEvent(Menu menu, Modelo modelo, Login login) {
		this.menu = menu;
		this.modelo = modelo;
		this.login = login;
	}

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
				vtnFtp.getBtnBorrarCarpeta().addActionListener(new BorrarArchivoFTP(vtnFtp, conexionFTP.getCliente()));
				vtnFtp.getBtnCrearCarpeta().addActionListener(new CrearCarpetaFTP(vtnFtp, conexionFTP.getCliente()));
				vtnFtp.getBtnDescargarArchivo().addActionListener(new DescargarArchivoFTP(vtnFtp, conexionFTP.getCliente()));
				vtnFtp.getBtnSubirArchivo().addActionListener(new SubirArchivoFTP(vtnFtp, conexionFTP.getCliente()));
			}
		} else if(e.getSource() == menu.getBtnCorreoElectronico()){
			VentanaEmail vtnEmail = new VentanaEmail();
			vtnEmail.frame.setVisible(true);
			vtnEmail.getBtnRedactar().addActionListener(new EnviaMailEvent(vtnEmail));
			vtnEmail.getBtnSalir().addActionListener(new SalirEmail(vtnEmail, menu));
			menu.frame.dispose();
		}else if(e.getSource() == menu.getBtnSalir()) {
			menu.frame.dispose();
			login.frame.setVisible(true);
			login.getTxtrUsuario().setText("");
			login.getPasswordField().setText("");
		}
	}
	
	public static boolean comprobarRuta (VentanaFTP vtnFtp) {
		boolean estaVacio = false;
		if(vtnFtp.getRutaSeleccionada().getText().equalsIgnoreCase("")) {
			estaVacio = true;
		}
		return estaVacio;
	}
}
