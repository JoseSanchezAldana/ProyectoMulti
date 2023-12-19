package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import conexion.ConexionFTP;
import modelo.Modelo;
import vista.Menu;
import vista.VentanaEmail;
import vista.VentanaFTP;

public class MenuEvent implements ActionListener{
	
	private Menu menu;
	private Modelo modelo;

	public MenuEvent(Menu menu,Modelo modelo) {
		this.menu = menu;
		this.modelo = modelo;
	}

	@Override
	public void actionPerformed(ActionEvent e) {	
		if (e.getSource() == menu.getBtnFTP()) {
			ConexionFTP conexionFTP = new ConexionFTP(modelo);
			VentanaFTP vtnFtp = new VentanaFTP(conexionFTP,modelo);
			vtnFtp.frame.setVisible(true);
			menu.frame.dispose();
			vtnFtp.getBtnSalir().addActionListener(new SalirFTPEvent(vtnFtp, menu));
			vtnFtp.getBtnBorrarCarpeta().addActionListener(new BorrarArchivoFTP());
			vtnFtp.getBtnCrearCarpeta().addActionListener(new crearCarpetaFTP());
			vtnFtp.getBtnDescargarArchivo().addActionListener(new descargarArchivoFTP(conexionFTP.getCliente()));
			vtnFtp.getBtnSubirArchivo().addActionListener(new subirArchivoFTP(conexionFTP.getCliente()));
			
		}else {
			VentanaEmail vtnEmail = new VentanaEmail();
			vtnEmail.frame.setVisible(true);
			menu.frame.dispose();
		}
		
	}
}
