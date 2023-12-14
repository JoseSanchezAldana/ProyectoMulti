package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vista.Login;
import vista.Menu;

public class LoginEvent implements ActionListener {
	
	Login login;

	public LoginEvent(Login login) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Menu menu = new Menu();
		menu.frame.setVisible(true);
		

	}

}
