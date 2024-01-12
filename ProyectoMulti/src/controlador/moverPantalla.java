package controlador;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;

public abstract class moverPantalla {

	public static void centrar(Frame frame) {

		//Centrar pantalla
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frame.getSize();
        int x = (screenSize.width - frameSize.width) / 2;
        int y = (screenSize.height - frameSize.height) / 2;
        frame.setLocation(x, y);

	}
}
