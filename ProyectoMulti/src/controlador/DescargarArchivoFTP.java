package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;

import conexion.ConexionFTP;
import modelo.Modelo;
import vista.VentanaFTP;

public class DescargarArchivoFTP implements ActionListener {
	
	VentanaFTP vtnFTP;
	FTPClient cliente;
	
	public DescargarArchivoFTP( VentanaFTP vtnFTP, FTPClient cliente) {
		this.vtnFTP=vtnFTP;
		this.cliente=cliente;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			OperacionesFTP operacionesFTP = new OperacionesFTP(vtnFTP);
			operacionesFTP.descargarFichero(cliente, vtnFTP.getRutaSeleccionada().getText());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}
