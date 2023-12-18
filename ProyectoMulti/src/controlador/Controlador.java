package controlador;

import conexion.Conexion;
import modelo.Modelo;
import vista.Login;

public class Controlador {
	
	Conexion conexion;
	
	public Controlador() throws ClassNotFoundException {
		Modelo modelo = new Modelo();
		Conexion conexion = new Conexion(modelo);
		Login login = new Login();
		login.frame.setVisible(true);
		login.getBtnNewButton().addActionListener(new LoginEvent(login, conexion,modelo));
		
	}
}
