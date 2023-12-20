package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;

import conexion.ConexionFTP;
import modelo.Modelo;
import vista.VentanaFTP;

public class SubirArchivoFTP implements ActionListener {
	
	VentanaFTP vtnFTP;
	FTPClient cliente;
	
	public SubirArchivoFTP(VentanaFTP vtnFTP, FTPClient cliente) {
		this.vtnFTP=vtnFTP;
		this.cliente=cliente;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		//cliente = ConexionFTP.getCliente();
		
		try {
			OperacionesFTP operacionesFTP = new OperacionesFTP(vtnFTP);
			operacionesFTP.subirFichero(this.cliente, vtnFTP.getRutaSeleccionada().getText());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		

	}

}
