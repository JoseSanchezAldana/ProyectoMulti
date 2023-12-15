package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vista.Menu;
import vista.VentanaEmail;
import vista.VentanaFTP;

public class MenuEvent implements ActionListener{
	
	Menu menu;

	public MenuEvent(Menu menu) {
		this.menu = menu;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == menu.getBtnFTP()) {
			VentanaFTP vtnFtp = new VentanaFTP();
			vtnFtp.frame.setVisible(true);
			menu.frame.dispose();
		}else {
			System.out.println("Boton email");
			VentanaEmail vtnEmail = new VentanaEmail();
			vtnEmail.frame.setVisible(true);
			menu.frame.dispose();
		}
		
	}
}
