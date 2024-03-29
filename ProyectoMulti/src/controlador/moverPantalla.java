/*
 * @proyect ProyectoMultidisciplinar_23/24
 * @author Grupo_4
 * @version 1.0
 */

package controlador;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;

public abstract class moverPantalla {

	/**
	 * Coloca una ventana en el centro de la pantalla del usuario
	 * @param frame Ventana que se quiere centrar
	 */
	public static void centrar(Frame frame) {

		//Centrar pantalla
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frame.getSize();
        int x = (screenSize.width - frameSize.width) / 2;
        int y = (screenSize.height - frameSize.height) / 2;
        frame.setLocation(x, y);

	}
}
