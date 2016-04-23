package fr.iut.pt.veto.form.treatment;

import fr.iut.pt.veto.template.FormController;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DetailTreatmentController extends FormController {

	@FXML
	private TextField name;
	@FXML
	private TextField owner;
	@FXML
	private TextField animal;
	@FXML
	private TextField malady;
	@FXML
	private TextArea desc;
	@FXML
	private TextField dateBegin;
	@FXML
	private TextField dateEnd;
	
	
	public void validate() {
		action.validate();
	}
	
	public TextField getName() {
		return name;
	}

	public TextField getOwner() {
		return owner;
	}
	
	public TextField getAnimal() {
		return animal;
	}
	
	public TextField getMalady() {
		return malady;
	}
	
	public TextArea getDesc() {
		return desc;
	}
	
	public TextField getDateBegin() {
		return dateBegin;
	}
	
	public TextField getDateEnd() {
		return dateEnd;
	}
}
