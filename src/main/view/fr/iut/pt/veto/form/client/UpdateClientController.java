package fr.iut.pt.veto.form.client;

import fr.iut.pt.veto.template.FormController;
import fr.iut.pt.veto.model.entitie.individu.Individu;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class UpdateClientController extends FormController {
	
	@FXML
	private TextField lastName;
	
	@FXML
	private TextField firstName;
	
	@FXML
	private TextField telephone;
	
	@FXML
	private TextField adress;
	
	@FXML
	private TextField mail;

	public void prepare(Individu client){
		lastName.setText(client.getNom());
		firstName.setText(client.getPrenom());
		telephone.setText(client.getTelephone());;
		adress.setText(client.getAdresse());
		mail.setText(client.getMail());
	}
	
	public void validate() {
		action.getParameters().put("ACTION", "Validate");
		action.validate();
	}
	
	public void cancel() {
		action.cancel();
	}

	/**
	 * @return the lastName
	 */
	public TextField getLastName() {
		return lastName;
	}

	/**
	 * @return the firstName
	 */
	public TextField getFirstName() {
		return firstName;
	}
	
	/**
	 * @return the telephone
	 */
	public TextField getTelephone() {
		return telephone;
	}

	/**
	 * @return the adress
	 */
	public TextField getAdress() {
		return adress;
	}

	/**
	 * @return the mail
	 */
	public TextField getMail() {
		return mail;
	}

}
