/**
 * 
 */
package fr.iut.pt.veto.form;

import fr.iut.pt.veto.template.FormController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * @author sebastien
 *
 */
public class HomePageController extends FormController {
	
	@FXML
	private TextField user;
	
	@FXML
	private PasswordField pass;
	
	@FXML
	private Label info;
	
	public void validate() {
		action.validate();
	}

	/**
	 * @return the user
	 */
	public TextField getUser() {
		return user;
	}

	/**
	 * @return the pass
	 */
	public PasswordField getPass() {
		return pass;
	}

	/**
	 * @return the info
	 */
	public Label getInfo() {
		return info;
	}

}
