package modelo;

public class Modelo {
	
	//Datos conexion base de datos
	
	private String direccionBd = "jdbc:mysql://localhost/localproyecto";
	private String usuarioBd = "root";
	private String passwordBd = "";
	
	//Datos conexion FTP
	private String servFTP ="127.0.0.1";
	private String usuario = null;
	private String pasword = null;
	private final String directorioInicial = "/";
	private String rutaSelec = null;
	
	
	public String getDireccionBd() {
		return direccionBd;
	}
	public void setDireccionBd(String direccionBd) {
		this.direccionBd = direccionBd;
	}
	public String getUsuarioBd() {
		return usuarioBd;
	}
	public void setUsuarioBd(String usuarioBd) {
		this.usuarioBd = usuarioBd;
	}
	public String getPasswordBd() {
		return passwordBd;
	}
	public void setPasswordBd(String passwordBd) {
		this.passwordBd = passwordBd;
	}
	public String getServFTP() {
		return servFTP;
	}
	public void setServFTP(String servFTP) {
		this.servFTP = servFTP;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPasword() {
		return pasword;
	}
	public void setPasword(String pasword) {
		this.pasword = pasword;
	}
	public String getRutaSelec() {
		return rutaSelec;
	}
	public void setRutaSelec(String rutaSelec) {
		this.rutaSelec = rutaSelec;
	}
	public String getDirectorioInicial() {
		return directorioInicial;
	}
	
	
	

}
