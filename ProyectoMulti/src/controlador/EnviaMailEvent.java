package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vista.VentanaEmail;
import vista.VentanaRedactarMail;

public class EnviaMailEvent implements ActionListener {
	
	private VentanaEmail vtnEmail;

	public EnviaMailEvent(VentanaEmail vtnEmail) {
		this.vtnEmail = vtnEmail;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		VentanaRedactarMail vtnRedactarMail = new VentanaRedactarMail();
		vtnRedactarMail.frame.setVisible(true);
		vtnEmail.frame.dispose();
		vtnRedactarMail.getBtnSalir().addActionListener(new SalirRedactarMailEvent(vtnRedactarMail, vtnEmail));
	}

}
