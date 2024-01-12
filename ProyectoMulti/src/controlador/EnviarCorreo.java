/*
 * @proyect ProyectoMultidisciplinar_23/24
 * @author Grupo_4
 * @version 1.0
 */

package controlador;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import modelo.Modelo;
import vista.VentanaEmail;
import vista.VentanaRedactarMail;

public  class EnviarCorreo  implements ActionListener{
	
	private VentanaRedactarMail vtnRedactarMail;
	private Modelo model;
	private VentanaEmail vtnEmail;
	

	public EnviarCorreo(VentanaRedactarMail vtnRedactarMail, VentanaEmail vtnEmail, Modelo modelo) {
		this.model = modelo;
		this.vtnEmail = vtnEmail;
		this.vtnRedactarMail =vtnRedactarMail;
	}
	/**
	 *  A partir de los datos que se envían como parámetro, 
	 * @param SMTP_USERNAME
	 * @param SMTP_PASSWORD
	 * @param FROM
	 * @param FROMNAME
	 * @param TO
	 * @param CC
	 * @param BCC
	 * @param SUBJECT
	 * @param BODY
	 * @throws Exception
	 */

	public void EnviarEmail(String SMTP_USERNAME, String SMTP_PASSWORD, String FROM, String FROMNAME, String TO,
			String CC, String BCC, String SUBJECT, String BODY)
			throws Exception {

		
		Properties props = System.getProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.port", this.model.getPORT());
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		Session session = Session.getDefaultInstance(props);
		session = Session.getDefaultInstance(props);
		MimeMessage msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(FROM, FROMNAME));

		String[] arTO = TO.split(";");
		for (int x = 0; x < arTO.length; x++) {
			System.out.println("Añadiendo destino: " + arTO[x]);
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(arTO[x]));
		}

		if (!CC.isEmpty()) {
			String[] arCC = CC.split(";");
			for (int x = 0; x < arCC.length; x++) {
				System.out.println("Añadiendo CC: " + arCC[x]);
				msg.addRecipient(Message.RecipientType.CC, new InternetAddress(arCC[x]));
			}
		}

		if (!BCC.isEmpty()) {
			String[] arBCC = BCC.split(";");
			for (int x = 0; x < arBCC.length; x++) {
				System.out.println("Añadiendo CCO: " + arBCC[x]);
				msg.addRecipient(Message.RecipientType.BCC, new InternetAddress(arBCC[x]));
			}
		}

		// MIMEMULTIPART PARA LOS ADJUNTOS

		msg.setSubject(SUBJECT);
		System.out.println("Asunto: " + SUBJECT);
		msg.setContent(BODY, "text/html");
		System.out.println("Contenido: " + BODY);
		msg.setHeader("X-SES-CONFIGURATION-SET", this.model.getCONFIGSET());
		Transport transport = session.getTransport();

		try {
			
			//this.vtnRedactarMail.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			this.vtnRedactarMail.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			this.vtnRedactarMail.getBtnEnviar().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			System.out.println("Enviando mensaje...");
			transport.connect(this.model.getHOST(), SMTP_USERNAME, SMTP_PASSWORD);
			transport.sendMessage(msg, msg.getAllRecipients());
			System.out.println("Mensaje enviado");
			JOptionPane.showMessageDialog(null, "Mensaje enviado correctamente", "ENVIADO", JOptionPane.INFORMATION_MESSAGE);
			vtnRedactarMail.frame.dispose();
			vtnEmail.frame.setVisible(true);
			
		} catch (Exception ex) {
			
			System.out.println("El mensaje no se ha podido enviar.");
			JOptionPane.showMessageDialog(null, "Error al enviar el mensaje", "ERROR", JOptionPane.INFORMATION_MESSAGE);
			
			

		} finally {
			this.vtnRedactarMail.frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			this.vtnRedactarMail.getBtnEnviar().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			transport.close();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			//Comprobaciones
			if(vtnRedactarMail.getTxtPara().getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Rellene la dirección de correo de destino", "FALTA DESTINO", JOptionPane.INFORMATION_MESSAGE);
			}else {
				this.EnviarEmail(
						this.model.getUsuario(),
						LoginEvent.decrypt(model.getPasword(), this.model.getKey()),					
						this.model.getUsuario(),						
						this.model.getNombre(),
						vtnRedactarMail.getTxtPara().getText(),
						vtnRedactarMail.getTxtCC().getText(),
						vtnRedactarMail.getTxtCCO().getText(),
						vtnRedactarMail.getTxtAsunto().getText(),
						vtnRedactarMail.getTxtMensaje().getText()
				);
			}
			
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
}
