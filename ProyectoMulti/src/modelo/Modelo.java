package modelo;

public class Modelo {
	
	//Datos conexion base de datos
	private String direccionBd = "jdbc:mysql://localhost/localproyecto";
	private String usuarioBd = "root";
	private String passwordBd = "";
	
	//Datos conexion mail
	private String CONFIGSET = "ConfigSet";
	private String HOST = "smtp.gmail.com";
	private int PORT = 587;
	
	//Key de encriptacion
	private String key = "afghklkhghkln";
	
	//Datos conexion FTP
	private String servFTP ="127.0.0.1";
	private String usuarioFTP = null;
	private String passwordFTP = null;
	private final String directorioInicial = "/";

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
		return usuarioFTP;
	}
	public void setUsuario(String usuario) {
		this.usuarioFTP = usuario;
	}
	public String getPasword() {
		return passwordFTP;
	}
	public void setPasword(String pasword) {
		this.passwordFTP = pasword;
	}
	public String getDirectorioInicial() {
		return directorioInicial;
	}
	public String getCONFIGSET() {
		return CONFIGSET;
	}
	public String getHOST() {
		return HOST;
	}
	public int getPORT() {
		return PORT;
	}
	public String getKey() {
		return key;
	}
	
	
	

}
