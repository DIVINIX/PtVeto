/**
 * 
 */
package fr.iut.pt.veto.util;

import java.io.IOException;

import fr.iut.pt.veto.MainDisplayController;
import fr.iut.pt.veto.action.Action;
import fr.iut.pt.veto.template.FormController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

/**
 * Classe utilitaire afin de refactorer des procedure de JavaFx
 * 
 * @author sebastien
 */
public abstract class JavaFxUtils {

	/**
	 * Cette méthode sert a la fois a recuperer la vue d"une action, mais aussi
	 * a initialiser le controleur directe (listener) de la vue.
	 * 
	 * A n'utiliser que pour la recuperation de vue liee a une Action
	 * 
	 * @param main
	 * @return node
	 */
	public static Node getView(final Action main) {
		Node node = null;

		String path = "/fr/iut/pt/veto/form/" + main.getIdentifier().toString() + ".fxml";

		final FXMLLoader loader = getLoader(path);

		try {
			node = loader.load();
			FormController controller = loader.getController();
			if (controller != null)
				controller.setAction(main);
			main.setController(controller);

		} catch (IOException e) {
			System.err.println("Erreur lors du chargement fxml de : " + path);
			e.printStackTrace();
		}

		return node;
	}

	/**
	 * Refacto de la recuperation du loader
	 * 
	 * @param path
	 * @return loader
	 */
	public static FXMLLoader getLoader(String path) {
		final FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainDisplayController.class.getResource(path));
		return loader;
	}

}
