package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import modelo.Modelo;
import vista.VentanaRedactarMail;

public  class EnviarCorreo  implements ActionListener{
	
	private VentanaRedactarMail vtnMail;
	private Modelo model;
	
	public EnviarCorreo(VentanaRedactarMail vtnMail, Modelo model){
		this.vtnMail = vtnMail;
		this.model = model;
	}

	public void EnviarEmail(String SMTP_USERNAME, String SMTP_PASSWORD, String FROM, String FROMNAME, String TO,
			String CC, String BCC, String CONFIGSET, String HOST, int PORT, String SUBJECT, String BODY)
			throws Exception {

		Properties props = System.getProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.port", PORT);
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		Session session = Session.getDefaultInstance(props);
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
		msg.setContent(BODY, "text/html");
		msg.setHeader("X-SES-CONFIGURATION-SET", CONFIGSET);
		Transport transport = session.getTransport();

		try {
			System.out.println("Enviando mensaje...");
			transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);
			transport.sendMessage(msg, msg.getAllRecipients());
			System.out.println("Mensaje enviado");
		} catch (Exception ex) {
			System.out.println("El mensaje no se ha podido enviar.");

		} finally {
			transport.close();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			
			String CC = vtnMail.getTxtCC().getText();
			
			this.EnviarEmail(
					this.model.getUsuario(),
					"Multi123",
					this.model.getUsuario(),
					this.model.getUsuario(), //AQUI VA EL NOMBRE DEL USUARIO - NO EL CORREO -
					vtnMail.getTxtPara().getText(),
					CC,
					vtnMail.getTxtCCO().getText(),
					"ConfigSet",
					"smtp.gmail.com",
					587,
					vtnMail.getTxtAsunto().getText(),
					vtnMail.getTxtMensaje().getText()
			);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
}
