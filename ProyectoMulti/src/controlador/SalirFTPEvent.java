package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vista.Menu;
import vista.VentanaFTP;

public class SalirFTPEvent implements ActionListener{
	
	VentanaFTP vtnFtp;
	Menu menu; 
	
	/**
	 * Cierra la ventana FTP y vuelve al menú princiapl
	 * @param vtnFtp Ventana de FTP a cerrar
	 * @param menu Ventana del Menu que se desea abrir
	 */

	public	SalirFTPEvent(VentanaFTP vtnFtp, Menu menu) {
		this.vtnFtp = vtnFtp;
		this.menu = menu;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		vtnFtp.frame.dispose();
		menu.frame.setVisible(true);
	}
}
