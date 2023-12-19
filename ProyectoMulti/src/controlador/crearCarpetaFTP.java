package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

import org.apache.commons.net.ftp.FTPClient;

import vista.VentanaFTP;

public class crearCarpetaFTP implements ActionListener {
	
private FTPClient cliente; 
private VentanaFTP vtnFtp;
	
	public crearCarpetaFTP(FTPClient cliente, VentanaFTP vtnFtp) {
		this.cliente = cliente;
		this.vtnFtp = vtnFtp;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method
		
		try {
			String nombreCarpeta = JOptionPane.showInputDialog("Introduzca el nombre de la carpeta");
			OperacionesFTP.crearCarpeta(cliente, vtnFtp.getRutaSeleccionada().getText(), nombreCarpeta);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
