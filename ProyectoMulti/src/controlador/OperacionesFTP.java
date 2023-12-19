package controlador;

import java.awt.Component;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

public class OperacionesFTP {
	
	 public static String seleccionarDirectorioConJFileChooser(){
	        String dir;
	        JFileChooser seleccionadorDirectorio = new JFileChooser();
	     

	        seleccionadorDirectorio.setDialogTitle("Guardar en...");
	        seleccionadorDirectorio.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

	        int seleccion = seleccionadorDirectorio.showSaveDialog(new Component() {
	            @Override
	            public String getName() {
	                return super.getName();
	            }
	        });

	        if (seleccion == JFileChooser.APPROVE_OPTION){
	            dir = seleccionadorDirectorio.getSelectedFile().getAbsolutePath();
	        }
	        else {
	            dir = "..\\Downloads";
	        }
	        return dir;
	    }


	    public static void descargarFichero(FTPClient cliente, String directorioFTP, String directorioDondeGuardar, String nombreArchivo) throws IOException {
	        cliente.changeWorkingDirectory(directorioFTP);

	        BufferedOutputStream out = new BufferedOutputStream( new FileOutputStream(directorioDondeGuardar) );

	        if (cliente.retrieveFile(nombreArchivo,out)){
	           JOptionPane.showMessageDialog(null,"Se ha descargado correctamente");
	    }
	        else {
	            JOptionPane.showMessageDialog(null,"No se ha descargado correctamente");
	        }
	    }

	    public void crearCarpeta(){
	    	
	    	

	    }
	

	    public static boolean subirFichero(FTPClient cliente, String directorio)
	            throws IOException {
	        File fichero;
	        FileInputStream fis = null;
	        boolean ok = false;

	        JFileChooser fileChooser = new JFileChooser();
	        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
	        fileChooser.setDialogTitle("Seleccione el archivo para subir");
	        int seleccion = fileChooser.showSaveDialog(new Component() {
	         
	            public String getName() {
	                return super.getName();
	            }
	        });
	        if (seleccion == JFileChooser.APPROVE_OPTION) {
	            fichero = fileChooser.getSelectedFile();
	            System.out.println(fichero.getAbsolutePath());
	            String ficheroASubir = fichero.getAbsolutePath();
	            fis = new FileInputStream(ficheroASubir);

	            cliente.enterLocalPassiveMode();

	            cliente.setFileType(FTP.BINARY_FILE_TYPE);

	            String cadenaSalida;
	            cliente.changeWorkingDirectory(directorio);
	            System.out.println(fichero.getName());
	            if(cliente.storeFile(fichero.getName(),fis)){
	                cadenaSalida = fichero.getName().concat(" >>> Subido Correctamente al server");
	                JOptionPane.showMessageDialog(null,cadenaSalida);

	                ok = true;
	            }
	            else {
	                cadenaSalida = fichero.getName().concat(" >>> NO Subido Correctamente al server");
	                JOptionPane.showMessageDialog(null,cadenaSalida);
	            }
	        }
	        return ok;
	    }

}
