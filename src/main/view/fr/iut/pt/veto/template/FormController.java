/**
 * 
 */
package fr.iut.pt.veto.template;

import fr.iut.pt.veto.action.Action;

/**
 * Template pour les controleurs directe d'actions.
 * @author sebastien
 */
public abstract class FormController {

	/**
	 * Un controller directe de formulaire de droite doit avoir une action associe.
	 */
	protected Action action;
	
	public void setAction(Action action) {
		this.action = action;
	}
}
