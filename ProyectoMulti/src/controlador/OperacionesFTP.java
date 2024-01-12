/*
 * @proyect ProyectoMultidisciplinar_23/24
 * @author Grupo_4
 * @version 1.0
 */

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
/**
 * Clase que contendrá cada uno de los métodos que realizan las diferentes acciones 
 * que el FTP permita.  
 */
public class OperacionesFTP {

	VentanaFTP vtnFtp;
	FTPClient cliente;
	Modelo modelo;
	boolean ok;

	/**
	 * 
	 * @param vtnFtp Se envía la vista para poder aplicarle las funciones
	 */
	public OperacionesFTP(VentanaFTP vtnFtp) {
		this.vtnFtp = vtnFtp;
	}
	
	/**
	 * Este método mostrará un JFileChooser al usuario que estará modificado únicamente para 
	 * que solo se puedan seleccionar directorios y no ficheros. 
	 * @return Una cadena de texto con la ruta del directorio que se haya seleccionado. 
	 */

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
	/**
	 * Método que descargará un fichero (solo ficheros, no carpetas) en un directorio específico del equipo del usuario.  
	 * @param cliente Cliente que realizará la operación
	 * @param directorioFTP Directorio del servidor FTP dónde se encuentra el fichero que el usuario desea descargar. 
	 * @return True si se descargó el fichero satisfactoriamente, False en caso contrario
	 * @throws IOException En caso de error de conexión 
	 */

	public boolean descargarFichero(FTPClient cliente, String directorioFTP) throws IOException {
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
					ok = true;
					
				} else {
					JOptionPane.showMessageDialog(null, "No se ha descargado correctamente");
					ok = false;
				}
				out.close();
			} catch (IOException e) {
				
			}
			
		} else {
			JOptionPane.showMessageDialog(null, "No se pueden descargar carpetas");
			ok = false;
		}
	
		recargarVentana();
		return ok;
	}
	
	/**
	 * Método que permite crear una nueva carpeta en el servidor FTP 
	 * @param cliente Cliente que realizará la operación
	 * @param directorioFTP Directorio del servidor FTP dónde se quiere crear la carpeta. 
	 * @return True si se creó el directorio satisfactoriamente, False en caso contrario. 
	 * @throws IOException En caso de error de conexión 
	 */

	public boolean crearCarpeta(FTPClient cliente, String directorioFTP) throws IOException {
		if (isCarpeta(directorioFTP, cliente) && !directorioFTP.isEmpty()) {
			String nombreCarpeta = JOptionPane.showInputDialog("Introduzca el nombre de la carpeta");
			cliente.changeWorkingDirectory(directorioFTP);
			if (cliente.makeDirectory(nombreCarpeta)) {
				JOptionPane.showMessageDialog(null, "Se ha creado la carpeta ".concat(nombreCarpeta));
				ok = true;
			} else {
				JOptionPane.showMessageDialog(null, "NO se ha creado la carpeta ".concat(nombreCarpeta));
				ok = false;
			}
		} else {
			JOptionPane.showMessageDialog(null, "Solo puedes crear carpetas dentro de directorios");
			ok = false;
		}
		recargarVentana();
		return ok;
	}
	/**
	 * Método que permite guardar un fichero dentro de una carpeta del servidor FTP 
	 * @param cliente Cliente que realizará la operación
	 * @param directorioFTP Directorio del servidor FTP dónde se quiere subir el archivo. 
	 * @return True si se subió el fichero satisfactoriamente, False en caso contrario. 
	 * @throws IOException En caso de error de conexión 
	 */


	public boolean subirFichero(FTPClient cliente, String directorio) throws IOException {
		boolean ok = false;
		if (isCarpeta(directorio, cliente) && !directorio.isEmpty()) {
			File fichero;
			FileInputStream fis = null;

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
					ok = false;
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "Debe de ser una carpeta");
			ok = false;
		}
		recargarVentana();
		return ok;
		
	}
	/**
	 * Método que elimina un fichero del servidor FTP 
	 * @param cliente Cliente que realizará la operación
	 * @param directorioFTP Directorio del servidor FTP dónde se encuentra el archivo que se desea eliminar. 
	 * @return True si se borró el fichero satisfactoriamente, False en caso contrario. 
	 */

	public boolean borrarArchivoFTP(String ruta, FTPClient cliente) throws HeadlessException {
		String nombreArchivo = obtenerNombreArchivo(ruta);
		if (nombreArchivo.equals("")) {
			JOptionPane.showMessageDialog(null, ">>>>>>No ha seleccionado ninguna ruta");
			ok = false;
		} else {
			int seleccion = JOptionPane.showConfirmDialog(null, "¿Desea borrar el archivo " + nombreArchivo + "?");
			if (seleccion == JOptionPane.OK_OPTION) {
				try {

					if (isCarpeta(ruta, cliente)) {
						FTPFile[] archivosEnDirectorio = cliente.listFiles(ruta);
						if (archivosEnDirectorio.length == 0) {
							if (cliente.removeDirectory(ruta)) {
								JOptionPane.showMessageDialog(null, nombreArchivo + " =>Eliminado correctamente...");
								ok = true;
							} else {
								JOptionPane.showMessageDialog(null,
										nombreArchivo + " =>No se ha podido borrar por algun error");
								ok = false;
							}
						} else {
							JOptionPane.showMessageDialog(null, nombreArchivo + " =>Debe estar vacio para poder borrarse");
							ok = false;
						}
					} else {
						if (cliente.deleteFile(ruta)) {
							JOptionPane.showMessageDialog(null, nombreArchivo + " =>Eliminado correctamente...");
							ok = true;
						}
					}

				} catch (IOException e) {
				}
			}
		}
		recargarVentana();
		return ok;
	}
	
	/**
	 * Método que permite comprobar si una ruta del FTP pertenece a una carpeta o a un directorio. 
	 * @param ruta  Ruta del servidor que se desea comprobar
	 * @param cliente Cliente que realiza la operación
	 * @return True si es directorio, False si es fichero. 
	 */

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
	
	/**
	 * Obtención del nombre de un archvo (o directorio) concreto. 
	 * @param rutaFTP Ruta del archivo del que se quiere obtener el nombre
	 * @return Una cadena de texto con el último segmento de la ruta enviada, la que corresponde al nombre
	 */

	private String obtenerNombreArchivo(String rutaFTP) {

		String[] segmentosRuta = rutaFTP.split("/");
		return segmentosRuta[segmentosRuta.length - 1];
	}
	
	/**
	 * Actualizza la ventana del FTP, refrescando el JTree que muestra los archivos y directorios del FTP y vaciando el texto que muestra la ruta selecionada. 
	 */
	
	public void recargarVentana() {
		vtnFtp.actualizarArbol();
		vtnFtp.getRutaSeleccionada().setText("");
	}

}
