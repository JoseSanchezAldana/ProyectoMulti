package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;

import conexion.ConexionFTP;

public class subirArchivoFTP implements ActionListener {
	
	private FTPClient cliente; 
	
	public subirArchivoFTP(FTPClient cliente) {
		this.cliente = cliente;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		//cliente = ConexionFTP.getCliente();
		
		try {
			OperacionesFTP.subirFichero(this.cliente, null);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		

	}

}
