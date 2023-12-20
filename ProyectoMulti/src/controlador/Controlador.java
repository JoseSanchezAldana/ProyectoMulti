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
		LoginEvent loginevt = new LoginEvent(login, conexion,modelo);
		login.getBtnLogin().addActionListener(loginevt);
		login.getBtnSalir().addActionListener(loginevt);
		
	}
}
