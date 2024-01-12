package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;

import javax.swing.JOptionPane;

import org.apache.commons.net.ftp.FTPClient;

import conexion.Conexion;
import conexion.ConexionFTP;
import modelo.Modelo;
import vista.VentanaFTP;

/**
 * Evento que realiza la operación de crear carpeta cuando se pulsa el botón correspondiente. 
 */
public class CrearCarpetaFTP implements ActionListener {
	
	VentanaFTP vtnFTP;
	FTPClient cliente;
	Conexion conexion;
	Modelo modelo;
	
	public CrearCarpetaFTP(VentanaFTP vtnFTP, FTPClient cliente, Conexion conexion, Modelo modelo) {
		this.vtnFTP=vtnFTP;
		this.cliente=cliente;
		this.conexion = conexion;
		this.modelo = modelo;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method
		try {
			
			OperacionesFTP operacionesFTP = new OperacionesFTP(vtnFTP);
			if (operacionesFTP.crearCarpeta(cliente, vtnFTP.getRutaSeleccionada().getText())) {
				this.conexion.insertarDatos(LocalDate.now(), 4, modelo.getIdUsuarioBd());
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
