/**
 * 
 */
package fr.iut.pt.veto.template;

import fr.iut.pt.veto.MainDisplay;

/**
 * Template pour les controleurs directe d'ecrans particulier (Le menu de gauche ainsi que le conteneur Main par exemple).
 * @author sebastien
 */
public abstract class GeneralController {
	
	/**
	 * Un ecran particulier n'a pas d'action, on lui assigne donc l'application elle-meme.
	 */
	protected MainDisplay main;
	
	public void setMain(MainDisplay main) {
		this.main = main;
	}
}
