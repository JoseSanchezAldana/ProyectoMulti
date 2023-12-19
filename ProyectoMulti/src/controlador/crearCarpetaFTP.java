package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;

public class crearCarpetaFTP implements ActionListener {
	
private FTPClient cliente; 
	
	public crearCarpetaFTP(FTPClient cliente) {
		this.cliente = cliente;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method
		
		try {
			OperacionesFTP.crearCarpeta(cliente, null, null); //pasar directorio y nombre de la carpeta
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
