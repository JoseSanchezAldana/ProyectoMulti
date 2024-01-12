package controlador;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMultipart;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import modelo.DatosPanelBandeja;
import modelo.Modelo;
import vista.VentanaEmail;

public class LeerBandejaMail{
	private Modelo model;
	private VentanaEmail vtnEmail;
	

	public LeerBandejaMail(VentanaEmail vtnEmail, Modelo modelo) {
		this.model = modelo;
		this.vtnEmail = vtnEmail;
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
            //"INBOX"
            //"[Gmail]/Enviados"
            //"[Gmail]/Borradores"
            //"[Gmail]/Spam"
            Folder emailFolder = store.getFolder(bandeja);
            emailFolder.open(Folder.READ_ONLY);

            // Obtención de todos los mensajes de la bandeja de entrada
            //Message[] mensajes = emailFolder.getMessages();
            mensajes = emailFolder.getMessages();

            // Cierre de la carpeta de bandeja de entrada y del almacén de correo
            //emailFolder.close(false);
            //store.close();

        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
		return mensajes;
	}

	private static void getMessageContent(Part part, StringBuffer content) throws MessagingException, IOException {
        if (part.isMimeType("text/*")) {
            content.append(part.getContent());
            
        } else if (part.isMimeType("multipart/*")) {
            Multipart multipart = (Multipart) part.getContent();
            int partCount = multipart.getCount();
            for (int i = 0; i < partCount; i++) {
                BodyPart bodyPart = multipart.getBodyPart(i);
                getMessageContent(bodyPart, content);
            }
        }else if(part.isMimeType("image/*")) {
//        	JFrame v = new JFrame("Adjunto");
//            ImageIcon icono = new ImageIcon(
//                    ImageIO.read(part.getInputStream()));
//            JLabel l = new JLabel(icono);
//            v.getContentPane().add(l);
//            v.pack();
//            v.setVisible(true);
        }
    }

}
