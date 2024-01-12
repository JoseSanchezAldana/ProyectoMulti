package vista;

import java.awt.Font;
import java.awt.Toolkit;

import javax.imageio.ImageIO;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.html.HTMLEditorKit;

import controlador.LeerBandejaMail;
import controlador.LoginEvent;
import controlador.RefrescarBandejaMail;
import controlador.moverPantalla;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import modelo.Modelo;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JRadioButton;

public class VentanaEmail {

	public JFrame frame;
	private JButton btnSalir;
	private JButton btnRedactar;
	private JButton btnRefrescar;
	
	private ButtonGroup rgBandejas;
	private JRadioButton rdRecibidos;	
	private JRadioButton rdEnviados;
	private JRadioButton rdBorradores;
	private JRadioButton rdSpam;
	
	public DefaultTableModel modeloTabla;
	
	private RefrescarBandejaMail thRefresco;
	
	private Message[] messages;

	private Modelo model;

	/**
	 * Crea la ventana.
	 */
	public VentanaEmail(Modelo model) {
		
		this.model = model;
		initialize();
		
	}

	/**
	 * Inicializa los componentes de la ventana.
	 */
	private void initialize() {
		
		//runRefresco = new RefrescarBandejaMail(this);
		thRefresco = new RefrescarBandejaMail(this);
			
		
		frame = new JFrame("Sesión iniciada con: "+ model.getUsuario());
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaFTP.class.getResource("/img/logoProyecto.png")));
		frame.getContentPane().setBackground(new Color(0, 64, 128));
		frame.setBounds(100, 100, 700, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		JPanel panel_1 = new JPanel(null);
		frame.setContentPane(panel_1);
		moverPantalla.centrar(frame);
        
        btnSalir = new JButton("Salir");
		btnSalir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSalir.setHorizontalAlignment(SwingConstants.LEFT);
		btnSalir.setIcon(new ImageIcon(VentanaFTP.class.getResource("/img/salida32.png")));
		btnSalir.setFocusPainted(false);
		btnSalir.setContentAreaFilled(false);
		btnSalir.setForeground(new Color(255, 255, 255));
		btnSalir.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSalir.setBorderPainted(false);
		btnSalir.setBackground(new Color(0, 64, 128));
		btnSalir.setBounds(60, 357, 105, 30);
		btnSalir.setOpaque(false);
		frame.getContentPane().add(btnSalir);
		
		btnRedactar = new JButton("Redactar");
		btnRedactar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRedactar.setIcon(new ImageIcon(VentanaEmail.class.getResource("/img/lapiz.png")));
		btnRedactar.setOpaque(false);
		btnRedactar.setHorizontalAlignment(SwingConstants.LEFT);
		btnRedactar.setForeground(Color.WHITE);
		btnRedactar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnRedactar.setFocusPainted(false);
		btnRedactar.setContentAreaFilled(false);
		btnRedactar.setBorderPainted(false);
		btnRedactar.setBackground(new Color(0, 64, 128));
		btnRedactar.setBounds(44, 86, 150, 30);
		panel_1.add(btnRedactar);
		
		btnRefrescar = new JButton("Refrescar");
		btnRefrescar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRefrescar.setIcon(new ImageIcon(VentanaEmail.class.getResource("/img/refrescar.png")));
		btnRefrescar.setOpaque(false);
		btnRefrescar.setHorizontalAlignment(SwingConstants.LEFT);
		btnRefrescar.setForeground(Color.WHITE);
		btnRefrescar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnRefrescar.setFocusPainted(false);
		btnRefrescar.setContentAreaFilled(false);
		btnRefrescar.setBorderPainted(false);
		btnRefrescar.setBackground(new Color(0, 64, 128));
		btnRefrescar.setBounds(512, 45, 200, 30);
		panel_1.add(btnRefrescar);
		
		
		modeloTabla = new DefaultTableModel();
		JTable table = new JTable(modeloTabla);
		table.setModel(modeloTabla);
		
		table.setRowSelectionAllowed(false);
		table.setCellSelectionEnabled(true);
		crearMenuContextual(table);
				
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(281, 86, 354, 320);		
		
		String[] encabezados = {"De: ", "Asunto: "};
		modeloTabla.setColumnIdentifiers(encabezados);

		panel_1.add(scrollPane);		
		
		
		rgBandejas = new ButtonGroup();
		
		rdRecibidos = new JRadioButton("Recibidos");
		rdRecibidos.setForeground(new Color(255, 255, 255));
		rdRecibidos.setFont(new Font("Tahoma", Font.BOLD, 13));
		rdRecibidos.setBounds(94, 146, 167, 21);
		rdRecibidos.setOpaque(false);
		rdRecibidos.setSelected(true);		
		panel_1.add(rdRecibidos);
		
		rdEnviados = new JRadioButton("Enviados");
		rdEnviados.setOpaque(false);
		rdEnviados.setForeground(Color.WHITE);
		rdEnviados.setFont(new Font("Tahoma", Font.BOLD, 13));
		rdEnviados.setBounds(94, 201, 167, 21);
		panel_1.add(rdEnviados);
		
		rdBorradores = new JRadioButton("Borradores");
		rdBorradores.setOpaque(false);
		rdBorradores.setForeground(Color.WHITE);
		rdBorradores.setFont(new Font("Tahoma", Font.BOLD, 13));
		rdBorradores.setBounds(94, 251, 167, 21);
		panel_1.add(rdBorradores);
		
		rdSpam = new JRadioButton("Spam");
		rdSpam.setOpaque(false);
		rdSpam.setForeground(Color.WHITE);
		rdSpam.setFont(new Font("Tahoma", Font.BOLD, 13));
		rdSpam.setBounds(94, 304, 167, 21);
		panel_1.add(rdSpam);
		
		
		rgBandejas.add(rdRecibidos);
		rgBandejas.add(rdEnviados);
		rgBandejas.add(rdBorradores);
		rgBandejas.add(rdSpam);
		
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(VentanaEmail.class.getResource("/img/bandeja-de-entrada.png")));
		lblNewLabel.setBounds(60, 137, 45, 30);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(VentanaEmail.class.getResource("/img/enviados.png")));
		lblNewLabel_1.setBounds(60, 185, 45, 48);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon(VentanaEmail.class.getResource("/img/borrador.png")));
		lblNewLabel_2.setBounds(60, 241, 45, 43);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(VentanaEmail.class.getResource("/img/alerta-de-spam.png")));
		lblNewLabel_3.setBounds(60, 292, 45, 43);
		panel_1.add(lblNewLabel_3);
		
		
		JLabel backgroundLabel = new JLabel(new ImageIcon(VentanaFTP.class.getResource("/img/fondo.jpg")));
		backgroundLabel.setBounds(-14, -17, 700, 500);
		frame.getContentPane().add(backgroundLabel);
		JPanel panel = new JPanel();
		panel.setBounds(44, 28, 10, 10);
		panel_1.add(panel);	
		

		RunRefrescar();
	}

	
	//MENU CONTEXTUAL
			private void crearMenuContextual(JTable table) {
			    JPopupMenu menu = new JPopupMenu();
			    
			    JMenuItem item = new JMenuItem("Abrir");
			    item.addActionListener(e -> {
			        System.out.println(table.getSelectedRow()) ;
			        if(table.getSelectedRow() != -1) {   			
		    			try {
							abrirMensaje(messages[messages.length-1 - table.getSelectedRow()]);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (MessagingException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
		    			
		    		}
			    });

			    menu.add(item);

			    table.addMouseListener(new MouseAdapter() {
			        public void mousePressed(MouseEvent e) {
			            if (e.getClickCount() == 2) {//DOBLE CLICK
			            	System.out.println(table.getSelectedRow());
			            	try {
			            		if(table.getSelectedRow() != -1) {
			            			abrirMensaje(messages[messages.length-1 - table.getSelectedRow()]);			            			
			            		}
								
							} catch (IOException | MessagingException e1) {
								e1.printStackTrace();
							}
			            }
			        }

			        public void mouseReleased(MouseEvent e) {
			            if (e.isPopupTrigger()) {//BOTON DERECHO
			                menu.show(e.getComponent(), e.getX(), e.getY());
			            }
			        }
			    });
			}
	
	public void RunRefrescar() {
		thRefresco.start();
	}
	
	public void DespertarRefrescar() {
		thRefresco.interrupt();
	}
	
	public void PararRefresco() {
		thRefresco.pararHilo();		
	}
	
	
	public String obtenerBandeja() {
		
		String bandeja = "";
		
		if(this.getRdRecibidos().isSelected()) {
			bandeja = "INBOX";
		}else if(this.getRdEnviados().isSelected()) {
			bandeja = "[Gmail]/Enviados";
		}else if(this.getRdBorradores().isSelected()) {
			bandeja = "[Gmail]/Borradores";
		}else if(this.getRdSpam().isSelected()) {
			bandeja = "[Gmail]/Spam";
		}	
		
		return bandeja;
		
	}
	
	
	public void RefrescarCorreos() {

		frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		getBtnRefrescar().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		getBtnRefrescar().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		this.messages = LeerBandejaMail.LeerBandeja(this.model.getUsuario(), LoginEvent.decrypt(this.model.getPasword(), this.model.getKey()), obtenerBandeja());
		frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		getBtnRefrescar().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}
	
	public void RefrescarTabla() {
		frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		getBtnRefrescar().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		
		//Limpìar tabla		
		int totalFilas= this.modeloTabla.getRowCount();
		for(int x = 0; x < totalFilas; x++) {
			this.modeloTabla.removeRow(0);
		}
		
		//Llenar tabla
		for (int x = this.getMessages().length-1; x >= 0; x--) {	
			String subject = "";
			try {
				if(messages[x].getSubject() != null) {
					subject = messages[x].getSubject().toString();
				}
				System.out.println("Cargando mensaje: "+ (x +1));
				this.modeloTabla.addRow(new Object[]{ messages[x].getFrom()[0].toString() ,	subject });
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
		frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		getBtnRefrescar().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}
			

	public Message[] getMessages() {
		return messages;
	}

	public void setMessages(Message[] messages) {
		this.messages = messages;
	}

	public JButton getBtnRefrescar() {
		return btnRefrescar;
	}

	public void setBtnRefrescar(JButton btnRefrescar) {
		this.btnRefrescar = btnRefrescar;
	}
	
	public JButton getBtnSalir() {
		return btnSalir;
	}

	public void setBtnSalir(JButton btnSalir) {
		this.btnSalir = btnSalir;
	}

	public JButton getBtnRedactar() {
		return btnRedactar;
	}

	public void setBtnRedactar(JButton btnRedactar) {
		this.btnRedactar = btnRedactar;
	}
	
	public ButtonGroup getRgBandejas() {
		return rgBandejas;
	}

	public JRadioButton getRdRecibidos() {
		return rdRecibidos;
	}

	public JRadioButton getRdEnviados() {
		return rdEnviados;
	}

	public JRadioButton getRdBorradores() {
		return rdBorradores;
	}

	public JRadioButton getRdSpam() {
		return rdSpam;
	}

	
	
	private static void abrirMensaje(Message msg) throws IOException, MessagingException {
		String html = "";
		// Crea un JFrame para visualizar el código HTML
        JFrame frame = new JFrame("Asunto: "+msg.getSubject() +" - De: "+ msg.getFrom()[0]);
        //JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 900);

        // Crea un JEditorPane para visualizar el código HTML y configura el editor kit
        JEditorPane editorPane = new JEditorPane();
        editorPane.setEditorKit(new HTMLEditorKit());
        editorPane.setContentType("text/html");
        StringBuffer sBuffer = new StringBuffer();
        getMessageContent(msg, sBuffer);
        html = sBuffer.toString();
        if(html.indexOf("<html") != -1) {
        	html = html.substring(html.indexOf("<html"));
        }
        
        editorPane.setText(html);
        editorPane.setEditable(false);

        // Añade el JEditorPane al JFrame
        frame.getContentPane().add(new JScrollPane(editorPane), BorderLayout.CENTER);

        // Muestra el JFrame
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
	}
	
	/**
     * Metodo recursivo.
     * Si la parte que se pasa es compuesta, se extrae cada una de las subpartes y
     * el metodo se llama a si mismo con cada una de ellas.
     * Si la parte es un text, se escribe en pantalla.
     * Si la parte es una image, se guarda en un fichero y se visualiza en un JFrame.
     * En cualquier otro caso, simplemente se escribe el tipo recibido, pero se
     * ignora el mensaje.
     *
     * @param unaParte Parte del mensaje a analizar.
     */
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
        	visualizaImagenEnJFrame(part);
        	
        }/*else if(part.isMimeType("aplication/*") || part.isMimeType("audio/*") || part.isMimeType("video/*")) {
        	salvaImagenEnFichero(part);
        }*/
    }

    /**
     * Presupone que unaParte es una foto adjunta a un correo.
     * Recoge la imagen y la visualiza en un JFrame
     *
     * @param unaParte Parte de un correo correspondiente a una imagen.
     *
     * @throws IOException 
     * @throws MessagingException 
     */
    private static void visualizaImagenEnJFrame(Part unaParte)
        throws IOException, MessagingException
    {
        JFrame v = new JFrame("Imagen adjunta");
        ImageIcon icono = new ImageIcon(
                ImageIO.read(unaParte.getInputStream()));
        JLabel l = new JLabel(icono);
        v.getContentPane().add(l);
        v.pack();
        v.setVisible(true);
    }

}
