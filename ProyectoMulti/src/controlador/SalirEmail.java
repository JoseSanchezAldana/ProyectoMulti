/*
 * @proyect ProyectoMultidisciplinar_23/24
 * @author Grupo_4
 * @version 1.0
 */

package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vista.Menu;
import vista.VentanaEmail;

public class SalirEmail implements ActionListener {
	
	private VentanaEmail vtnEmail;
	private Menu menu;

	/**
	 * Cierra la ventana del mail y vuelve al menú princiapl
	 * @param vtnEmail Ventana a cerrar
	 * @param menu Menu a abrir. 
	 */
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
