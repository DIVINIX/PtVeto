/**
 * 
 */
package fr.iut.pt.veto.action;

import java.util.Map;

import fr.iut.pt.veto.MainDisplay;
import fr.iut.pt.veto.template.FormController;
import javafx.scene.layout.BorderPane;

/**
 * Template pour les actions.
 * Definition d'une action : c'est ce qui modelise un formulaire, une fonctionnalite (ecran de droite) de l'application.
 * @author sebastien
 */
public abstract class Action {
	
	/**
	 * L'identifiant de l'action, utile pour la récuperation de la vue
	 */
	protected ActionEnum identifier;

	/**
	 * La vue, le .fxml charge
	 */
	protected BorderPane view;
	
	/**
	 * Le controller directe (listener) de la vue
	 */
	protected FormController controller;
	
	/**
	 * Le lien vers l'application/dispatcher
	 */
	protected MainDisplay main;
	
	/**
	 * La map avec les paramètres accessibles depuis les controllers
	 */
	protected Map<String,Object> parameters;
	
	/**
	 * @return the parameters
	 */
	public Map<String, Object> getParameters() {
		return parameters;
	}

	/**
	 * @param parameters the parameters to set
	 */
	public void setParameters(Map<String, Object> parameters) {
		this.parameters = parameters;
	}

	/**
	 * Cette methode doit etre utiliser au debut d'une action, elle va charger la vue,
	 * puis doit effectuer les possibles traiement necessaire a l'affichage du formulaire.
	 */
	public abstract void prepare();
	
	/**
	 * Cette methode doit servir a valider les informations saisie dans le formulaire,
	 * et donc contenir les possibles modifications de la bdd grace aux DAO.
	 */
	public abstract void validate();
	
	/**
	 * Ici, l'action est terminer, il faut maintenant decider de ce qui se passe apres.
	 */
	public abstract void next();

	/**
	 * Cette méthode doit servir a arreter l'action en cour
	 */
	public abstract void cancel();
	
	/**
	 * L'action doit savoir 
	 * @param main
	 */
	public Action(MainDisplay main) {
		super();
		this.main = main;
	}

	/**
	 * @return the view
	 */
	public BorderPane getView() {
		return view;
	}

	/**
	 * @param view the view to set
	 */
	public void setView(BorderPane view) {
		this.view = view;
	}

	/**
	 * @return the controller
	 */
	public FormController getController() {
		return controller;
	}

	/**
	 * @param controller the controller to set
	 */
	public void setController(FormController controller) {
		this.controller = controller;
	}

	/**
	 * @return the main
	 */
	public MainDisplay getMain() {
		return main;
	}

	/**
	 * @param main the main to set
	 */
	public void setMain(MainDisplay main) {
		this.main = main;
	}

	/**
	 * @return the identifier
	 */
	public ActionEnum getIdentifier() {
		return identifier;
	}
	
}
