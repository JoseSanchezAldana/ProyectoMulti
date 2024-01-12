package controlador;

import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;

public class LeerBandejaMail{

	public LeerBandejaMail() {

	}

	public static Message[] LeerBandeja(String user, String pass, String bandeja) {
		// Correo y contraseña del usuario
        final String username = user;
        final String password = pass;
        Message[] mensajes = null;

        // Configuración de propiedades para acceder al servidor de Gmail
        
        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.store.protocol", "imaps");
        props.put("mail.imaps.host", "imap.gmail.com");
        props.put("mail.imaps.port", "993");
        props.put("mail.imaps.ssl.enable", "true");

        try {
            // Autenticación y conexión al servidor de Gmail
            Session emailSession = Session.getDefaultInstance(props);
            Store store = emailSession.getStore("imaps");
            store.connect("imap.gmail.com",username, password);

            // Apertura de la carpeta de bandeja de entrada
            Folder emailFolder = store.getFolder(bandeja);
            emailFolder.open(Folder.READ_ONLY);

            // Obtención de todos los mensajes de la bandeja de entrada
            mensajes = emailFolder.getMessages();

        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
		return mensajes;
	}

}
