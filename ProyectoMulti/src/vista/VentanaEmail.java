package vista;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.imageio.ImageIO;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
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
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

import modelo.Modelo;
import modelo.DatosPanelBandeja;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class VentanaEmail {

	public JFrame frame;
	private JButton btnSalir;
	private JButton btnRedactar;
	private JButton btnRefrescar;
	public DefaultTableModel modeloTabla;
	
	//private RefrescarBandejaMail runRefresco;
	private RefrescarBandejaMail thRefresco;
	
	
	private String[][] listaMensajes;
	private Message[] messages;

	private Modelo model;

	/**
	 * Create the application.
	 */
	public VentanaEmail(Modelo model) {
		
		this.model = model;
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
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
		ImageIcon backgroundImage = new ImageIcon(VentanaFTP.class.getResource("/img/logoProyecto.png"));
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
		btnSalir.setBounds(44, 141, 105, 30);
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
		
		//table.setAutoCreateRowSorter(true);
		table.setRowSelectionAllowed(false);
		//table.setEnabled(false);
		table.setCellSelectionEnabled(true);
		crearMenuContextual(table);
				
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(281, 86, 354, 320);		
		
		String[] encabezados = {"De: ", "Asunto: "};
		modeloTabla.setColumnIdentifiers(encabezados);

		panel_1.add(scrollPane);		
				
		
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
	    });

	    menu.add(item);

	    table.addMouseListener(new MouseAdapter() {
	        public void mousePressed(MouseEvent e) {
//	            if (e.isPopupTrigger()) {//BOTON DERECHO
//	                menu.show(e.getComponent(), e.getX(), e.getY());
//	            }
	            if (e.getClickCount() == 2) {//DOBLE CLICK
	                //menu.show(e.getComponent(), e.getX(), e.getY());
	            	System.out.println(table.getSelectedRow());
	            	try {
	            		if(table.getSelectedRow() != -1) {
	            			
	            			
	            			//abrirMensaje(listaMensajes[(table.getSelectedRow())]);
	            			abrirMensaje(messages[messages.length-1 - table.getSelectedRow()]);
	            			
	            		}
						
					} catch (IOException | MessagingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	            }
	        }

	        public void mouseReleased(MouseEvent e) {
	            if (e.isPopupTrigger()) {//BOTON DERECHO
	                menu.show(e.getComponent(), e.getX(), e.getY());
	            }
//	            if (e.getClickCount() == 2) {//DOBLE CLICK
//	                //menu.show(e.getComponent(), e.getX(), e.getY());
//	            	System.out.println(table.getSelectedRow()) ;
//	            }
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
	
	public void RefrescarCorreos() {
		frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		getBtnRefrescar().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		getBtnRefrescar().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		this.messages = LeerBandejaMail.LeerBandeja(this.model.getUsuario(), LoginEvent.decrypt(this.model.getPasword(), this.model.getKey()), "INBOX");
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
				// TODO Auto-generated catch block
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
        	
        }else if(part.isMimeType("aplication/*") || part.isMimeType("audio/*") || part.isMimeType("video/*")) {
        	salvaImagenEnFichero(part);
        }
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

    /**
     * Supone que unaParte corresponde a una imagen de un fichero y que
     * getFileName() esta relleno.
     * Salva la imagen en d:\getFileName().
     *
     * @param unaParte Parte de un correo correspondiente a una imagen.
     *
     * @throws FileNotFoundException 
     * @throws MessagingException 
     * @throws IOException 
     */
    private static void salvaImagenEnFichero(Part unaParte)
        throws FileNotFoundException, MessagingException, IOException
    {
        FileOutputStream fichero = new FileOutputStream(
                "d:/" + unaParte.getFileName());
        InputStream imagen = unaParte.getInputStream();
        byte[] bytes = new byte[1000];
        int leidos = 0;

        while ((leidos = imagen.read(bytes)) > 0)
        {
            fichero.write(bytes, 0, leidos);
        }
    }
	
	
	
	
	
}
