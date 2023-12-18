package controlador;

import conexion.Conexion;
import vista.Login;

public class Controlador {
	
	Conexion conexion;
	
	public Controlador() throws ClassNotFoundException {
		Conexion conexion = new Conexion();
		Login login = new Login();
		login.frame.setVisible(true);
		login.getBtnNewButton().addActionListener(new LoginEvent(login, conexion));
		
	}
}
