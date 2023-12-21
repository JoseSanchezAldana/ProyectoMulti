package controlador;

import java.awt.Component;
import java.awt.HeadlessException;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import conexion.ConexionFTP;
import modelo.Modelo;
import vista.VentanaFTP;

public class OperacionesFTP {

	VentanaFTP vtnFtp;
	FTPClient cliente;
	Modelo modelo;

	public OperacionesFTP(VentanaFTP vtnFtp) {
		this.vtnFtp = vtnFtp;
	}	

	public String seleccionarDirectorioConJFileChooser() {
		String dir="..\\Downloads";
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
		} 
		return dir;
	}

	public void descargarFichero(FTPClient cliente, String directorioFTP) throws IOException {
		if (!isCarpeta(directorioFTP, cliente)) {
			String directorioDondeGuardar = seleccionarDirectorioConJFileChooser();
			cliente.changeWorkingDirectory(directorioFTP);
			cliente.enterLocalPassiveMode();

			String nombreArchivo = new File(directorioFTP).getName();

			String localFilePath = directorioDondeGuardar + File.separator + nombreArchivo;
			cliente.setFileType(FTP.BINARY_FILE_TYPE);

			try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(localFilePath))) {

				if (cliente.retrieveFile(directorioFTP, out)) {
					JOptionPane.showMessageDialog(null, "Se ha descargado correctamente");
					
				} else {
					JOptionPane.showMessageDialog(null, "No se ha descargado correctamente");
				}
				out.close();
			} catch (IOException e) {
				
			}
			
		} else {
			JOptionPane.showMessageDialog(null, "No se pueden descargar carpetas");
		}
	
		recargarVentana();
	}

	public void crearCarpeta(FTPClient cliente, String directorioFTP) throws IOException {
		if (isCarpeta(directorioFTP, cliente) && !directorioFTP.isEmpty()) {
			String nombreCarpeta = JOptionPane.showInputDialog("Introduzca el nombre de la carpeta");
			cliente.changeWorkingDirectory(directorioFTP);
			if (cliente.makeDirectory(nombreCarpeta)) {
				JOptionPane.showMessageDialog(null, "Se ha creado la carpeta ".concat(nombreCarpeta));
			} else {
				JOptionPane.showMessageDialog(null, "NO se ha creado la carpeta ".concat(nombreCarpeta));
			}
		} else {
			JOptionPane.showMessageDialog(null, "Solo puedes crear carpetas dentro de directorios");
		}
		recargarVentana();
	}

	public void subirFichero(FTPClient cliente, String directorio) throws IOException {
		if (isCarpeta(directorio, cliente) && !directorio.isEmpty()) {
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
		} else {
			JOptionPane.showMessageDialog(null, "Debe de ser una carpeta");
		}
		recargarVentana();
	}

	public void borrarArchivoFTP(String ruta, FTPClient cliente) throws HeadlessException {

		// String nombreArchivo = extraerNombreArchivo(ruta, cliente);
		String nombreArchivo = obtenerNombreArchivo(ruta);
		if (nombreArchivo.equals("")) {
			JOptionPane.showMessageDialog(null, ">>>>>>No ha seleccionado ninguna ruta");
		} else {
			int seleccion = JOptionPane.showConfirmDialog(null, "¿Desea borrar el archivo " + nombreArchivo + "?");
			if (seleccion == JOptionPane.OK_OPTION) {
				try {

					if (isCarpeta(ruta, cliente)) {
						FTPFile[] archivosEnDirectorio = cliente.listFiles(ruta);
						if (archivosEnDirectorio.length == 0) {
							if (cliente.removeDirectory(ruta)) {
								JOptionPane.showMessageDialog(null, nombreArchivo + " =>Eliminado correctamente...");
							} else {
								JOptionPane.showMessageDialog(null,
										nombreArchivo + " =>No se ha podido borrar por algun error");
							}
						} else {
							JOptionPane.showMessageDialog(null,
									nombreArchivo + " =>Debe estar vacio para poder borrarse");
						}
					} else {
						if (cliente.deleteFile(ruta)) {
							JOptionPane.showMessageDialog(null, nombreArchivo + " =>Eliminado correctamente...");
						}
					}

				} catch (IOException e) {
				}
			}
		}
		recargarVentana();
	}

	public boolean isCarpeta(String ruta, FTPClient cliente) {
		boolean isCarpeta = false;
		try {
			FTPFile archivoFTP = cliente.mlistFile(ruta);
			if (archivoFTP.isDirectory()) {
				isCarpeta = true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return isCarpeta;
	}

	private String obtenerNombreArchivo(String rutaFTP) {

		String[] segmentosRuta = rutaFTP.split("/");
		return segmentosRuta[segmentosRuta.length - 1];
	}
	
	public void recargarVentana() {
		vtnFtp.actualizarArbol();
		vtnFtp.getRutaSeleccionada().setText("");
	}

}
