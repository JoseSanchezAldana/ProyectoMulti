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
import org.apache.commons.net.ftp.FTPFile;

public class OperacionesFTP {

	public OperacionesFTP() {

	}

	public static String seleccionarDirectorioConJFileChooser() {
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

		if (seleccion == JFileChooser.APPROVE_OPTION) {
			dir = seleccionadorDirectorio.getSelectedFile().getAbsolutePath();
		} else {
			dir = "..\\Downloads";
		}
		return dir;
	}

	public static void descargarFichero(FTPClient cliente, String directorioFTP, String directorioDondeGuardar)
            throws IOException {
        // Change the working directory on the FTP server
        cliente.changeWorkingDirectory(directorioFTP);

        // Extract the file name from the directory path
        String nombreArchivo = new File(directorioFTP).getName();

        // Build the full path of the local file
        String localFilePath = directorioDondeGuardar + File.separator + nombreArchivo;

        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(localFilePath))) {
            // Download the specified file from the FTP server and write it to the local file
            if (!cliente.retrieveFile(nombreArchivo, out)) {
                JOptionPane.showMessageDialog(null, "Se ha descargado correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "No se ha descargado correctamente");
            }
        } catch (IOException e) {
            // Handle the exception appropriately
            e.printStackTrace();
        }
    }


	public static void crearCarpeta(FTPClient cliente, String directorioFTP, String nombreCarpeta) throws IOException {
	    cliente.changeWorkingDirectory(directorioFTP);

	    // Attempt to create the directory on the FTP server using only the folder name
	    if (cliente.makeDirectory(nombreCarpeta)) {
	        JOptionPane.showMessageDialog(null, "Se ha creado la carpeta ".concat(nombreCarpeta));
	    } else {
	        JOptionPane.showMessageDialog(null, "NO se ha creado la carpeta ".concat(nombreCarpeta));
	    }
	}


	public static boolean subirFichero(FTPClient cliente, String directorio) throws IOException {
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
			if (cliente.storeFile(fichero.getName(), fis)) {
				cadenaSalida = fichero.getName().concat(" >>> Subido Correctamente al server");
				JOptionPane.showMessageDialog(null, cadenaSalida);

				ok = true;
			} else {
				cadenaSalida = fichero.getName().concat(" >>> NO Subido Correctamente al server");
				JOptionPane.showMessageDialog(null, cadenaSalida);
			}
		}
		return ok;
	}

}
