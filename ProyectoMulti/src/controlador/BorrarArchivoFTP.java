package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.apache.commons.net.ftp.FTPClient;

import vista.VentanaFTP;

public class BorrarArchivoFTP implements ActionListener {
	VentanaFTP vtnFTP;
	FTPClient cliente;
	
	public BorrarArchivoFTP(VentanaFTP vtnFTP, FTPClient cliente) {
		this.vtnFTP=vtnFTP;
		this.cliente=cliente;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		OperacionesFTP.borrarArchivoFTP(vtnFTP.getRutaSeleccionada().getText(),cliente);

	}

}
