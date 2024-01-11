package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import modelo.Modelo;
import vista.VentanaEmail;
import vista.VentanaRedactarMail;
import controlador.EnviarCorreo;

public class EnviaMailEvent implements ActionListener {
	
	private VentanaEmail vtnEmail;
	private Modelo modelo;

	public EnviaMailEvent(VentanaEmail vtnEmail, Modelo model) {
		this.vtnEmail = vtnEmail;
		this.modelo = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		VentanaRedactarMail vtnRedactarMail = new VentanaRedactarMail(modelo);
		vtnRedactarMail.frame.setVisible(true);
		vtnEmail.frame.dispose();
		vtnRedactarMail.getBtnSalir().addActionListener(new SalirRedactarMailEvent(vtnRedactarMail, vtnEmail));
		vtnRedactarMail.getBtnEnviar().addActionListener(new EnviarCorreo(vtnRedactarMail, vtnEmail, modelo));
	}

}
