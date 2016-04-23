package fr.iut.pt.veto.form.client;

import java.net.URL;
import java.util.ResourceBundle;

import fr.iut.pt.veto.template.FormController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import fr.iut.pt.veto.action.client.*;
import fr.iut.pt.veto.model.entitie.individu.Individu;

public class DetailClientController extends FormController {

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
		
		lastName.setEditable(false);
		firstName.setEditable(false);
		telephone.setEditable(false);
		adress.setEditable(false);
		mail.setEditable(false);
	}
	
	public void update() {
		action.getParameters().put("ACTION", "Modifier");
		action.validate();
	}
	
	public void cancel() {
		action.getParameters().put("ACTION", "Retour");
		action.validate();
	}
	
	public void showMeetings() {
		action.getParameters().put("ACTION", "ListRendezVous");
		action.validate();
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
