package fr.iut.pt.veto.form.animal;

import java.util.List;

import fr.iut.pt.veto.model.entitie.animal.Espece;
import fr.iut.pt.veto.model.entitie.animal.Race;
import fr.iut.pt.veto.model.entitie.individu.Client;
import fr.iut.pt.veto.template.FormController;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * @author Gauthier
 *
 */

public class AddAnimalController extends FormController {
	
	@FXML
	private TextField name;
		
	@FXML
	private ComboBox<Client> owner;
	
	@FXML
	private ComboBox<Espece> nature;
	
	@FXML
	private ComboBox<Race> breed;
	
	@FXML
	private Label warning;
	
	
	public void prepare(List<Client> clients,List<Espece> especes) {
		owner.getItems().addAll(clients);
		nature.getItems().addAll(especes);
	}
	
	public void selectNature() {
		breed.getSelectionModel().clearSelection();
		//
		action.getParameters().put("ACTION", "Select");
		action.getParameters().put("nature", nature.getSelectionModel().getSelectedItem());
		action.validate();
	}
	
	public void validate() {
		action.getParameters().put("ACTION", "Validate");
		action.validate();
	}
	
	public void cancel() {
		action.cancel();
	}

	
	public TextField getName() {
		return name;
	}
	
	public ComboBox<Client> getOwner() {
		return owner;
	}
	
	public ComboBox<Espece> getNature() {
		return nature;
	}
	
	public ComboBox<Race> getBreed() {
		return breed;
	}
	
	public Label getWarning() {
		return warning;
	}
	
}
