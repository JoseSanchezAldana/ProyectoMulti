package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import conexion.Conexion;
import vista.Login;
import vista.Menu;

public class LoginEvent implements ActionListener {

    private Login login;
    private Conexion conexion;

    public LoginEvent(Login login, Conexion conexion) {
        this.login = login;
        this.conexion = conexion;
    }

    private void realizarConsulta(ArrayList<String> credenciales) {
        conexion.query("SELECT Usuario, password FROM usuarios WHERE Usuario = '" + login.getTxtrUsuario().getText() + "';");
        try {
            if (conexion.getRs().next()) {
                credenciales.add(conexion.getRs().getString(1));
                credenciales.add(conexion.getRs().getString(2));
                System.out.println("USUARIObd: " + credenciales.get(0) + "\nCONTRASEÑAbd: " + credenciales.get(1));
            } else {
                System.out.println("No se encontraron resultados en la consulta.");
            }
        } catch (Exception e) {
            System.out.println("ERROR AL REALIZAR LA CONSULTA A BD: " + e.getMessage());
        } 
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Menu menu = new Menu();
        ArrayList<String> credenciales = new ArrayList<>();

        realizarConsulta(credenciales);

        try {
        	String encryptedText = encrypt(login.getBtnNewButton().getText(), "afghklkhghkln");
            System.out.println("Texto cifrado: " + encryptedText);

            if (credenciales.get(0).equals(login.getTxtrUsuario().getText()) && credenciales.get(1).equals(encryptedText)) {
            	login.frame.dispose();
                menu.frame.setVisible(true);
                System.out.println("Login correcto");
                MenuEvent mEvent = new MenuEvent(menu);
                menu.getBtnCorreoElectronico().addActionListener(mEvent);
                menu.getBtnFTP().addActionListener(mEvent);
                
            } else {
            	 JOptionPane.showMessageDialog(null, "USUARIO O CONTRASEÑA INCORRECTO", "ERROR", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex) {
        	 JOptionPane.showMessageDialog(null, "USUARIO O CONTRASEÑA INCORRECTO", "ERROR", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static String encrypt(String plaintext, String key) {
        StringBuilder ciphertext = new StringBuilder();

        for (int i = 0; i < plaintext.length(); i++) {
            char plainChar = plaintext.charAt(i);
            char keyChar = key.charAt(i % key.length());
            char encryptedChar = (char) (plainChar ^ keyChar);
            ciphertext.append(encryptedChar);
        }
        return Base64.getEncoder().encodeToString(ciphertext.toString().getBytes());
    }


    public static String decrypt(String ciphertext, String key) {
        byte[] decodedBytes = Base64.getDecoder().decode(ciphertext);
        String decodedText = new String(decodedBytes);

        return encrypt(decodedText, key);
    }
}
