/*
 * @proyect ProyectoMultidisciplinar_23/24
 * @author Grupo_4
 * @version 1.0
 */

package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;

import org.apache.commons.net.ftp.FTPClient;

import conexion.Conexion;
import conexion.ConexionFTP;
import modelo.Modelo;
import vista.VentanaFTP;
/**
 * Evento que realiza la operación de subir el archivo cuando se pulsa el botón correspondiente. 
 */
public class SubirArchivoFTP implements ActionListener {
	
	VentanaFTP vtnFTP;
	FTPClient cliente;
	Conexion conexion;
	Modelo modelo;
	
	public SubirArchivoFTP(VentanaFTP vtnFTP, FTPClient cliente, Conexion conexion, Modelo modelo) {
		this.vtnFTP=vtnFTP;
		this.cliente=cliente;
		this.conexion = conexion;
		this.modelo = modelo;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {

		
		try {
			OperacionesFTP operacionesFTP = new OperacionesFTP(vtnFTP);
			if(operacionesFTP.subirFichero(this.cliente, vtnFTP.getRutaSeleccionada().getText())) {
				this.conexion.insertarDatos(LocalDate.now(), 2, modelo.getIdUsuarioBd());
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		

	}

}
