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

public class LeerBandejaMail implements ActionListener, Runnable{
	private Modelo model;
	private VentanaEmail vtnEmail;
	

	public LeerBandejaMail(VentanaEmail vtnEmail, Modelo modelo) {
		this.model = modelo;
		this.vtnEmail = vtnEmail;;
	}

	
	public static String[][] LeerBandeja(String user, String pass) {
		// Correo y contraseña del usuario
        final String username = user;
        final String password = pass;
        String[][] strinaux = null;

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
            Folder emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);

            // Obtención de todos los mensajes de la bandeja de entrada
            Message[] mensajes = emailFolder.getMessages();
            
            strinaux = new String[mensajes.length][3];    		
    		

            for (int i = 0; i < mensajes.length; i++) {		
    			
    			strinaux[i][0] = mensajes[mensajes.length-1-i].getFrom()[0].toString();   			
    			strinaux[i][1] = mensajes[mensajes.length-1-i].getSubject();
    			
    			StringBuffer content = new StringBuffer();
                Message message = mensajes[mensajes.length-1-i];
                getMessageContent(message, content);
                strinaux[i][2] = content.toString();   
    			//System.out.println(mensajes[i].getContentType());
    			//strinaux[i][2] = (String) mensajes[i].getContent();
                
    		}

            // Cierre de la carpeta de bandeja de entrada y del almacén de correo
            emailFolder.close(false);
            store.close();

        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
		return strinaux;
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
	
	public static List<DatosPanelBandeja> ObtenerTitulosMensajes(String user, String pass, String bandeja) {
		//"INBOX"
        //"[Gmail]/Enviados"
        //"[Gmail]/Borradores"
        //"[Gmail]/Spam"
		
		
		// Correo y contraseña del usuario
        final String username = user;
        final String password = pass;
        List<DatosPanelBandeja> datos = new ArrayList<>();;

        // Configuración de propiedades para acceder al servidor de Gmail
        
        Properties props = new Properties();
        props.put("mail.store.protocol", "pop3");
        props.put("mail.pop3.host", "pop.gmail.com");
        props.put("mail.pop3.port", "995");
        props.put("mail.pop3.ssl.enable", "true");
        
//        Properties props = new Properties();
//        props.put("mail.store.protocol", "imaps");
//        props.put("mail.imaps.host", "imap.gmail.com");
//        props.put("mail.imaps.port", "993");
//        props.put("mail.imaps.ssl.enable", "true");

        try {
            // Autenticación y conexión al servidor de Gmail
            Session emailSession = Session.getDefaultInstance(props);
            Store store = emailSession.getStore("pop3");

            store.connect(username, password);

            // Apertura de la carpeta de bandeja de entrada
            Folder emailFolder = store.getFolder(bandeja);
            
            emailFolder.open(Folder.READ_ONLY);

            // Obtención de todos los mensajes de la bandeja de entrada
            Message[] messages = emailFolder.getMessages();

            
            // Impresión de la información de cada mensaje
            for (int i = messages.length; i > 0; i--) {
            	
                Message message = messages[i-1];
                
                datos.add(new DatosPanelBandeja(message.getFrom()[0].toString(), message.getSubject()));
            }
        	
            // Cierre de la carpeta de bandeja de entrada y del almacén de correo
            emailFolder.close(false);
            store.close();
            

        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
		return datos;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void run() {
		while(true) {
			vtnEmail.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			//LeerBandeja(this.model.getUsuario(), LoginEvent.decrypt(this.model.getPasword(), this.model.getKey()));
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
