package conexion;

import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;

import modelo.Modelo;

public class ConexionFTP {
//	conexion
	private FTPClient cliente = new FTPClient();
	private Modelo modelo;
	private boolean login;
	
	public ConexionFTP(Modelo modelo) {
		this.modelo = modelo;
		String servFTP = modelo.getServFTP();
		try {
			cliente.connect(servFTP);
			login = cliente.login(modelo.getUsuario(), modelo.getPasword());
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public  FTPClient getCliente() {
		return cliente;
	}

	public void setCliente(FTPClient cliente) {
		this.cliente = cliente;
	}

	public boolean isLogin() {
		return login;
	}

	public void setLogin(boolean login) {
		this.login = login;
	}
	
}
