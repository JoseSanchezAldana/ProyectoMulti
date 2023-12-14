package conexion;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

//Conexion
public class Conexion {
	
	private java.sql.Statement s;
	private ResultSet rs;
	Connection conexion;
	
	public Conexion() throws ClassNotFoundException {
		
		Class.forName("com.mysql.jdbc.Driver");

		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/musica", "root", "");
			s = conexion.createStatement(); 
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
