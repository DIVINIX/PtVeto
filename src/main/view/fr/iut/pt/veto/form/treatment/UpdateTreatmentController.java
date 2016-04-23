package fr.iut.pt.veto.form.treatment;

import java.util.List;

import fr.iut.pt.veto.model.entitie.animal.Animal;
import fr.iut.pt.veto.model.entitie.individu.Client;
import fr.iut.pt.veto.model.entitie.traitement.Maladie;
import fr.iut.pt.veto.template.FormController;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class UpdateTreatmentController extends FormController {

	@FXML
	private TextField name;
	
	@FXML
	private ComboBox<Client> owner;
	
	@FXML
	private ComboBox<Animal> animal;
	
	@FXML
	private ComboBox<Maladie> malady;
	
	@FXML 
	private TextArea desc;
	
	@FXML 
	private DatePicker dateBegin;
	
	@FXML 
	private DatePicker dateEnd;
	
	@FXML
	private Label warning;
	
	
	
	public void selectOwner() {
		action.getParameters().put("ACTION", "SelectOwner");
		action.getParameters().put("owner", owner.getSelectionModel().getSelectedItem());
		action.validate();
	}
	
	public void prepare(List<Client> clients, List<Maladie> maladies) {
		owner.getItems().setAll(clients);
		malady.getItems().setAll(maladies);
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
	
	public ComboBox<Animal> getAnimal() {
		return animal;
	}
	
	public ComboBox<Maladie> getMalady() {
		return malady;
	}
	
	public TextArea getDesc() {
		return desc;
	}
	
	public DatePicker getDateBegin() {
		return dateBegin;
	}
	
	public DatePicker getDateEnd() {
		return dateEnd;
	}
	
	public Label getWarning() {
		return warning;
	}
	
}
