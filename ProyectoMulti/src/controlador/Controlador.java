package controlador;

import vista.Login;

public class Controlador {
	public Controlador() {
		Login login = new Login();
		login.frame.setVisible(true);
		login.getBtnNewButton().addActionListener(new LoginEvent(login));
	}
}
