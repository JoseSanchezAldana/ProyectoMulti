package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import org.apache.commons.net.ftp.FTPClient;

import conexion.Conexion;
import conexion.ConexionFTP;
import modelo.Modelo;
import vista.VentanaFTP;

/**
 * Evento que realiza la operación de borrar cuando se pulsa el botón correspondiente. 
 */
public class BorrarArchivoFTP implements ActionListener {
	VentanaFTP vtnFTP;
	FTPClient ftpClient;
	Conexion conexion;
	Modelo modelo;
	
	public BorrarArchivoFTP(VentanaFTP vtnFTP, FTPClient ftpClient, Conexion conexion, Modelo modelo) {
		this.vtnFTP=vtnFTP;
		this.ftpClient=ftpClient;
		this.conexion = conexion;
		this.modelo = modelo;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		OperacionesFTP operacionesFTP = new OperacionesFTP(vtnFTP);
		if (operacionesFTP.borrarArchivoFTP(vtnFTP.getRutaSeleccionada().getText(), ftpClient)) {
			this.conexion.insertarDatos(LocalDate.now(), 5, modelo.getIdUsuarioBd());
		}
	}

}
