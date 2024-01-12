package controlador;

import java.awt.Cursor;

import javax.mail.Message;
import javax.mail.MessagingException;

import modelo.Modelo;
import vista.VentanaEmail;

public class RefrescarBandejaMail extends Thread{
	
	private VentanaEmail vtnEmail;
	private volatile boolean corriendo = true;
	
	public RefrescarBandejaMail(VentanaEmail vtnEmail) {
		this.vtnEmail = vtnEmail;
	}

	public void pararHilo() {
		this.corriendo = false;
		System.out.println("Hilo eliminado.");
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(corriendo) {
			//vtnEmail.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			
			vtnEmail.getBtnRefrescar().setEnabled(false);
			//LLENAR MENSAJES
			vtnEmail.RefrescarCorreos();

			//RELLENAR TABLA			
			vtnEmail.RefrescarTabla();			
			
			vtnEmail.getBtnRefrescar().setEnabled(true);
			//vtnEmail.frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			try {
				Thread.sleep(20000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("Hilo reiniciado");
			}
		}	
	}


	
}
