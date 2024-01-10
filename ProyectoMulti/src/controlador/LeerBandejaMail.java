package controlador;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;

import modelo.DatosPanelBandeja;
import modelo.Modelo;
import vista.VentanaEmail;

public class LeerBandejaMail implements ActionListener, Runnable{
	private Modelo model;
	private VentanaEmail vtnEmail;
	

	public LeerBandejaMail(VentanaEmail vtnEmail, Modelo modelo) {
		this.model = modelo;
		this.vtnEmail = vtnEmail;;
	}

	
	public static void LeerBandeja(String user, String pass) {
		// Correo y contraseña del usuario
        final String username = user;
        final String password = pass;

        // Configuración de propiedades para acceder al servidor de Gmail
        Properties props = new Properties();
        props.put("mail.store.protocol", "imaps");
        props.put("mail.imaps.host", "imap.gmail.com");
        props.put("mail.imaps.port", "993");
        props.put("mail.imaps.ssl.enable", "true");

        try {
            // Autenticación y conexión al servidor de Gmail
            Session emailSession = Session.getDefaultInstance(props);
            Store store = emailSession.getStore("imaps");
            store.connect(username, password);

            // Apertura de la carpeta de bandeja de entrada
            Folder emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);

            // Obtención de todos los mensajes de la bandeja de entrada
            Message[] messages = emailFolder.getMessages();

            // Impresión de la información de cada mensaje
            for (int i = 0; i < messages.length; i++) {
                Message message = messages[i];
                System.out.println("Message " + (i + 1));
                System.out.println("Subject: " + message.getSubject());
                System.out.println("From: " + message.getFrom()[0]);
                System.out.println("Sent Date: " + message.getSentDate());
                System.out.println("-----------------------------");
            }

            // Cierre de la carpeta de bandeja de entrada y del almacén de correo
            emailFolder.close(false);
            store.close();

        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public static List<DatosPanelBandeja> ObtenerTitulosMensajes(String user, String pass) {
		// Correo y contraseña del usuario
        final String username = user;
        final String password = pass;
        List<DatosPanelBandeja> datos = new ArrayList<>();;

        // Configuración de propiedades para acceder al servidor de Gmail
        Properties props = new Properties();
        props.put("mail.store.protocol", "imaps");
        props.put("mail.imaps.host", "imap.gmail.com");
        props.put("mail.imaps.port", "993");
        props.put("mail.imaps.ssl.enable", "true");

        try {
            // Autenticación y conexión al servidor de Gmail
            Session emailSession = Session.getDefaultInstance(props);
            Store store = emailSession.getStore("imaps");
            store.connect(username, password);

            // Apertura de la carpeta de bandeja de entrada
            //Folder emailFolder = store.getFolder("INBOX");
            //Folder emailFolder = store.getFolder("[Gmail]/Enviados");
            //Folder emailFolder = store.getFolder("[Gmail]/Borradores");
            Folder emailFolder = store.getFolder("[Gmail]/Spam");
            emailFolder.open(Folder.READ_ONLY);

            // Obtención de todos los mensajes de la bandeja de entrada
            Message[] messages = emailFolder.getMessages();

            
            // Impresión de la información de cada mensaje
            for (int i = messages.length; i > 0; i--) {
            	
                Message message = messages[i-1];
                
                datos.add(new DatosPanelBandeja(message.getFrom()[0].toString(), message.getSubject()));
                /*
                System.out.println("Message " + (i + 1));
                System.out.println("Subject: " + message.getSubject());
                System.out.println("From: " + message.getFrom()[0]);
                System.out.println("Sent Date: " + message.getSentDate());
                System.out.println("-----------------------------");
                */
            }
        	
            // Cierre de la carpeta de bandeja de entrada y del almacén de correo
            emailFolder.close(false);
            store.close();

        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
			return datos;
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void run() {
		while(true) {
			vtnEmail.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			LeerBandeja(this.model.getUsuario(), LoginEvent.decrypt(this.model.getPasword(), this.model.getKey()));
			vtnEmail.frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			try {
				Thread.sleep(30000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

	}
}
