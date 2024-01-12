package conexion;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import modelo.Modelo;

//Conexion
/**
 * Clase que contiene los métodos referidos a la conexión con la Base de datos
 */
public class Conexion {
	
	private java.sql.Statement s;
	private ResultSet rs;
	private PreparedStatement ps;
	private Connection conexion;
	private Modelo modelo;
	
	/**
	 * Iniciar conexión con la base de datos
	 * @param modelo  Enviar datos que contiene el modelo
	 * @throws ClassNotFoundException
	 */
	public Conexion(Modelo modelo) throws ClassNotFoundException {
		
		Class.forName("com.mysql.jdbc.Driver");

		try {
			conexion = DriverManager.getConnection(modelo.getDireccionBd(), modelo.getUsuarioBd(), modelo.getPasswordBd());
			s = conexion.createStatement(); 
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Fallo al conectar con el servidor.", "FALLO CONECTIVIDAD", JOptionPane.INFORMATION_MESSAGE);
		}
		
	}

	/**
	 * Registrar las operaciones realizadas en el FTP
	 * @param localDate   Fecha de operación
	 * @param idOperacion Tipo de operación realizada
	 * @param idUsuario	  Usuario que está realizando la tarea
	 */
	public void insertarDatos(LocalDate localDate, int idOperacion, int idUsuario) {
        try {
        	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String fechaActualString = localDate.format(formatter);
            String consulta = "INSERT INTO log (fecha, idOperacion, idUsuario) VALUES (?, ?, ?)";
            ps = conexion.prepareStatement(consulta);
            ps.setString(1, fechaActualString);
            ps.setInt(2, idOperacion);
            ps.setInt(3, idUsuario);
            
            // Ejecutar la inserción
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	

	public java.sql.Statement getS() {
		return s;
	}



	public void setS(java.sql.Statement s) {
		this.s = s;
	}



	public ResultSet getRs() {
		return rs;
	}



	public void setRs(ResultSet rs) {
		this.rs = rs;
	}



	public Connection getConexion() {
		return conexion;
	}



	public void setConexion(Connection conexion) {
		this.conexion = conexion;
	}



	public ResultSet query(String text) {
		try {
			rs = s.executeQuery(text);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

}
