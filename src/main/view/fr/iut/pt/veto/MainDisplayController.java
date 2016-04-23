/**
 * 
 */
package fr.iut.pt.veto;

import fr.iut.pt.veto.template.GeneralController;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * 
 * @author sebboursier
 */
public class MainDisplayController extends GeneralController {

	/**
	 * Conteneur de droite. Il va bien sur contenir le menu. 
	 */
	@FXML
	private BorderPane menuContainer;
	
	/**
	 * Conteneur de gauche. il va contenir les formulaires, un par un.
	 */
	@FXML
	private BorderPane formContainer;

	/**
	 * Quand on change d'action, il faut bien entendu changer de formulaire a afficher.
	 * @param p
	 */
	public void setForm(Pane p) {
		formContainer.setCenter(p);
	}
}
