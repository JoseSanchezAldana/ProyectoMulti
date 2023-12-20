package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.apache.commons.net.ftp.FTPClient;

import conexion.ConexionFTP;
import modelo.Modelo;
import vista.VentanaFTP;

public class BorrarArchivoFTP implements ActionListener {
	VentanaFTP vtnFTP;
	FTPClient ftpClient;
	
	public BorrarArchivoFTP(VentanaFTP vtnFTP, FTPClient ftpClient) {
		this.vtnFTP=vtnFTP;
		this.ftpClient=ftpClient;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		OperacionesFTP operacionesFTP = new OperacionesFTP(vtnFTP);
		operacionesFTP.borrarArchivoFTP(vtnFTP.getRutaSeleccionada().getText(), ftpClient);

	}

}
