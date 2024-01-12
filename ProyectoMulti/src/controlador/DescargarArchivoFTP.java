package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;

import org.apache.commons.net.ftp.FTPClient;

import conexion.Conexion;
import conexion.ConexionFTP;
import modelo.Modelo;
import vista.VentanaFTP;

public class DescargarArchivoFTP implements ActionListener {
	
	VentanaFTP vtnFTP;
	FTPClient cliente;
	Conexion conexion;
	Modelo modelo;
	
	public DescargarArchivoFTP( VentanaFTP vtnFTP, FTPClient cliente, Conexion conexion, Modelo modelo) {
		this.vtnFTP=vtnFTP;
		this.cliente=cliente;
		this.conexion = conexion;
		this.modelo = modelo;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			OperacionesFTP operacionesFTP = new OperacionesFTP(vtnFTP);
			if(operacionesFTP.descargarFichero(cliente, vtnFTP.getRutaSeleccionada().getText())) {
				this.conexion.insertarDatos(LocalDate.now(), 3, modelo.getIdUsuarioBd());
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}
