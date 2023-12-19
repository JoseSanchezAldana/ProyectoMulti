package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;

public class descargarArchivoFTP implements ActionListener {
	
private FTPClient cliente; 
	
	public descargarArchivoFTP(FTPClient cliente) {
		this.cliente = cliente;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
				
		try {
			String directorioDondeGuardar = OperacionesFTP.seleccionarDirectorioConJFileChooser(); 
			OperacionesFTP.descargarFichero(cliente, null, directorioDondeGuardar, null);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
