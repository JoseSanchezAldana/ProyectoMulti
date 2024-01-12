/*
 * @proyect ProyectoMultidisciplinar_23/24
 * @author Grupo_4
 * @version 1.0
 */

package controlador;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import vista.VentanaFTP;

public class SeleccionTree implements TreeSelectionListener {

	VentanaFTP frame;
	
	public SeleccionTree(VentanaFTP frame) {
		this.frame=frame;
	}
	
	@Override
	public void valueChanged(TreeSelectionEvent e) {
		TreePath path = e.getNewLeadSelectionPath();
		if (path != null) {
			// Obtener la ruta como una cadena
			String ruta = obtenerRutaDesdeTreePath(path);
			System.out.println("Ruta seleccionada: " + ruta);
			frame.getRutaSeleccionada().setText(ruta);
		}
		
	}
	
	/**
	 * Obtiene la ruta seleccionada del JTree
	 * @param path
	 * @return
	 */
	private static String obtenerRutaDesdeTreePath(TreePath path) {
		StringBuilder ruta = new StringBuilder();
		for (Object node : path.getPath()) {
			DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) node;
			if (ruta.length() > 0) {
				ruta.append("/");
			}
			ruta.append(treeNode.getUserObject().toString());
		}
		return ruta.toString();
	}

}
