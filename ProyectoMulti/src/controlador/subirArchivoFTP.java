package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;

import conexion.ConexionFTP;
import vista.VentanaFTP;

public class subirArchivoFTP implements ActionListener {
	
	private FTPClient cliente; 
	private VentanaFTP vtnFtp;
	
	public subirArchivoFTP(FTPClient cliente, VentanaFTP vtnFtp) {
		this.cliente = cliente;
		this.vtnFtp = vtnFtp;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		//cliente = ConexionFTP.getCliente();
		
		try {
			OperacionesFTP.subirFichero(this.cliente, vtnFtp.getRutaSeleccionada().getText());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		

	}

}
