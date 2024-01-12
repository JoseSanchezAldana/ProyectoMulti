package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vista.VentanaEmail;
import vista.VentanaRedactarMail;

public class SalirRedactarMailEvent implements ActionListener {
	
	private VentanaRedactarMail vtnRedactarMail;
	private VentanaEmail vtnEmail;
	/**
	 * Salir de la ventana de redacción del email
	 * @param vtnRedactarMail Ventana a cerrar
	 * @param vtnEmail Ventana del email a la que volver
	 */

	public SalirRedactarMailEvent(VentanaRedactarMail vtnRedactarMail, VentanaEmail vtnEmail) {
		this.vtnRedactarMail = vtnRedactarMail;
		this.vtnEmail = vtnEmail;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		vtnRedactarMail.frame.dispose();
		vtnEmail.frame.setVisible(true);

	}

}
