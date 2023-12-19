package controlador;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

public class SeleccionTree implements TreeSelectionListener {

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		TreePath path = e.getNewLeadSelectionPath();
		if (path != null) {
			// Obtener la ruta como una cadena
			String ruta = obtenerRutaDesdeTreePath(path);
			System.out.println("Ruta seleccionada: " + ruta);
		}
		
	}
	
	
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
