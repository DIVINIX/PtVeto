package fr.iut.pt.veto.form.animal;

import java.util.List;

import fr.iut.pt.veto.model.entitie.animal.Animal;
import fr.iut.pt.veto.model.entitie.individu.Client;
import fr.iut.pt.veto.template.FormController;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * @author Gauthier
 *
 */

public class UpdateRecallController extends FormController {

	@FXML
	private TextField name;
	@FXML
	private ComboBox<Client> owner;
	@FXML
	private ComboBox<Animal> animal;
	@FXML
	private DatePicker date;
	@FXML
	private Label warning;

	public void prepare(List<Client> clients) {
		owner.getItems().setAll(clients);
	}
	
	public void selectClient() {
		animal.getItems().clear();
		action.getParameters().put("ACTION", "SelectOwner");
		action.getParameters().put("owner", owner.getSelectionModel().getSelectedItem());
		action.validate();
	}
	
	public void validate() {
		action.getParameters().put("ACTION", "Validate");
		action.validate();
	}
	
	public void cancel() {
		action.cancel();
	}
	
	public TextField getRaison() {
		return name;
	}
	
	public ComboBox<Client> getClient() {
		return owner;
	}
	
	public ComboBox<Animal> getAnimal() {
		return animal;
	}
	
	public DatePicker getDate() {
		return date;
	}
	
	public Label getWarning() {
		return warning;
	}
	
}
