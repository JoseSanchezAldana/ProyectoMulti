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
	private Runnable runRefresco;
	private Thread thRefresco;
	
	private String[][] listaMensajes;


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
		
		runRefresco = new LeerBandejaMail(this, this.model);
		thRefresco = new Thread(runRefresco);
		
		this.listaMensajes = LeerBandejaMail.LeerBandeja(this.model.getUsuario(), LoginEvent.decrypt(this.model.getPasword(), this.model.getKey()));
		
		
		
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
		
		
		
		DefaultTableModel modeloTabla = new DefaultTableModel();
		JTable table = new JTable(modeloTabla);
		table.setModel(modeloTabla);
		table.setAutoCreateRowSorter(true);
		table.setRowSelectionAllowed(false);
		//table.setEnabled(false);
		crearMenuContextual(table);
		
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(281, 86, 354, 320);
		
		
		String[] encabezados = {"De: ", "Asunto: "};

		modeloTabla.setColumnIdentifiers(encabezados);
				
//		List<DatosPanelBandeja> datos = LeerBandejaMail.ObtenerTitulosMensajes(this.model.getUsuario(), LoginEvent.decrypt(this.model.getPasword(), this.model.getKey()), "INBOX");
//		
//		for (DatosPanelBandeja dato : datos) {
//			modeloTabla.addRow(new Object[]{dato.getParade(), dato.getAsunto()});
//		}
		
		for (int x = 0; x < listaMensajes.length; x++) {
			modeloTabla.addRow(new Object[]{listaMensajes[x][0], listaMensajes[x][1]});;
		}
				
		panel_1.add(scrollPane);		
			
		
		
		JLabel backgroundLabel = new JLabel(new ImageIcon(VentanaFTP.class.getResource("/img/fondo.jpg")));
		backgroundLabel.setBounds(-14, -17, 700, 500);
		frame.getContentPane().add(backgroundLabel);
		JPanel panel = new JPanel();
		panel.setBounds(44, 28, 10, 10);
		panel_1.add(panel);	
		
		
	}
	
	//LLAMADA
	//LeerBandejaMail.LeerBandeja(this.model.getUsuario(), LoginEvent.decrypt(this.model.getPasword(), this.model.getKey()));
	
	
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
	            			abrirMensaje(listaMensajes[(table.getSelectedRow())]);
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
	
	
	private void PrimerRefrescar() {

		thRefresco.run();
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
	
	
	private static void abrirMensaje(String[] msg) throws IOException, MessagingException {
		String html = "";
		//String html = msg.getContent().toString();
		//String html = "<html><body><h1>Tu correo electrónico</h1><p>Tu contraseña</p></body></html>";
		if(msg[2].indexOf('<') != -1) {
			html = msg[2].substring(msg[2].indexOf('<'));
		}else {
			html = msg[2];
		}
		
		System.out.println(html);
		// Crea un JFrame para visualizar el código HTML
        JFrame frame = new JFrame("Asunto: "+msg[1] +" - De: "+ msg[0]);
        //JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // Crea un JEditorPane para visualizar el código HTML y configura el editor kit
        JEditorPane editorPane = new JEditorPane();
        editorPane.setEditorKit(new HTMLEditorKit());
        editorPane.setContentType("text/html");
        editorPane.setText(html);

        // Añade el JEditorPane al JFrame
        frame.getContentPane().add(new JScrollPane(editorPane), BorderLayout.CENTER);

        // Muestra el JFrame
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
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
        	JFrame v = new JFrame("Adjunto");
            ImageIcon icono = new ImageIcon(
                    ImageIO.read(part.getInputStream()));
            JLabel l = new JLabel(icono);
            v.getContentPane().add(l);
            v.pack();
            v.setVisible(true);
        }
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
    private static void analizaParteDeMensaje(Part unaParte)
    {
        try
        {
          // Si es multipart, se analiza cada una de sus partes recursivamente.
            if (unaParte.isMimeType("multipart/*"))
            {
                Multipart multi;
                multi = (Multipart) unaParte.getContent();

                for (int j = 0; j < multi.getCount(); j++)
                {
                    analizaParteDeMensaje(multi.getBodyPart(j));
                }
            }
            else
            {
              // Si es texto, se escribe el texto.
                if (unaParte.isMimeType("text/*"))
                {
                    System.out.println("Texto " + unaParte.getContentType());
                    System.out.println(unaParte.getContent());
                    System.out.println("---------------------------------");
                }
                else
                {
                  // Si es imagen, se guarda en fichero y se visualiza en JFrame
                    if (unaParte.isMimeType("image/*"))
                    {
                        System.out.println(
                            "Imagen " + unaParte.getContentType());
                        System.out.println("Fichero=" + unaParte.getFileName());
                        System.out.println("---------------------------------");

                        salvaImagenEnFichero(unaParte);
                        visualizaImagenEnJFrame(unaParte);
                    }
                    else
                    {
                      // Si no es ninguna de las anteriores, se escribe en pantalla
                      // el tipo.
                        System.out.println(
                            "Recibido " + unaParte.getContentType());
                        System.out.println("---------------------------------");
                    }
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
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
        JFrame v = new JFrame();
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
