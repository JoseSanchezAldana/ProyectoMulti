package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vista.Menu;
import vista.VentanaFTP;

public class SalirFTPEvent implements ActionListener{
	
	VentanaFTP vtnFtp;
	Menu menu;

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
