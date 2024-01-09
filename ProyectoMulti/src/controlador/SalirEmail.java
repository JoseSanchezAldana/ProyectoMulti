package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vista.Menu;
import vista.VentanaEmail;

public class SalirEmail implements ActionListener {
	
	private VentanaEmail vtnEmail;
	private Menu menu;

	public SalirEmail(VentanaEmail vtnEmail, Menu menu) {
		this.vtnEmail = vtnEmail;
		this.menu = menu;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		vtnEmail.frame.dispose();
		menu.frame.setVisible(true);

	}

}
