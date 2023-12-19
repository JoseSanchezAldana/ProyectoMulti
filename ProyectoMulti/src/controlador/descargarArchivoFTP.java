package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;

import vista.VentanaFTP;

public class descargarArchivoFTP implements ActionListener {
	
private FTPClient cliente; 
private VentanaFTP vtnFtp;
	
	public descargarArchivoFTP(FTPClient cliente,  VentanaFTP vtnFtp) {
		this.cliente = cliente;
		this.vtnFtp = vtnFtp;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
				
		try {
//			String directorioDondeGuardar = OperacionesFTP.seleccionarDirectorioConJFileChooser(); 
//			OperacionesFTP.descargarFichero(cliente, vtnFtp.getRutaSeleccionada().getText(), directorioDondeGuardar);
			if(cliente.changeWorkingDirectory("//JoseAlumno")) {
			//	BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(null))
				cliente.retrieveFile("D:\\accesoDatos\\prueba_FTP", null);
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
