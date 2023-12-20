package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

import org.apache.commons.net.ftp.FTPClient;

import conexion.ConexionFTP;
import modelo.Modelo;
import vista.VentanaFTP;

public class CrearCarpetaFTP implements ActionListener {
	
	VentanaFTP vtnFTP;
	FTPClient cliente;
	
	public CrearCarpetaFTP(VentanaFTP vtnFTP, FTPClient cliente) {
		this.vtnFTP=vtnFTP;
		this.cliente=cliente;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method
		try {
			
			OperacionesFTP operacionesFTP = new OperacionesFTP(vtnFTP);
			operacionesFTP.crearCarpeta(cliente, vtnFTP.getRutaSeleccionada().getText());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
