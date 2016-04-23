package fr.iut.pt.veto.form.client;

import java.util.List;

import fr.iut.pt.veto.model.entitie.animal.Animal;
import fr.iut.pt.veto.model.entitie.individu.Client;
import fr.iut.pt.veto.model.entitie.individu.Individu;
import fr.iut.pt.veto.model.entitie.inventaire.Produit;
import fr.iut.pt.veto.model.entitie.traitement.Maladie;
import fr.iut.pt.veto.template.FormController;
import fr.iut.pt.veto.util.CellLabel;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.Callback;

/**
 * 
 * @author Yann
 *
 */

public class AddMeetingController extends FormController {
	
	@FXML
    private ComboBox<Client> owner;

	@FXML
	private ComboBox<Animal> animal;

	@FXML 
	private TextArea desc;
	
	@FXML 
	private DatePicker dateMeeting;
	
	@FXML
	private Label info;
	
	public void prepare(List<Client> clients){
		owner.getItems().addAll(clients);
		owner.setCellFactory(new Callback<ListView<Client>, ListCell<Client>>() {
			
			@Override
			public ListCell<Client> call(ListView<Client> param) {
				return new CellLabel<Client>();
			}
		});
	}
    
	public void select() {
		action.getParameters().put("ACTION", "Select");
		action.getParameters().put("select", owner.getSelectionModel().getSelectedItem());
		action.validate();
	}
	
	public void fill(List<Animal> animaux) {
		animal.getItems().clear();
		animal.getItems().addAll(animaux);
		animal.setCellFactory(new Callback<ListView<Animal>, ListCell<Animal>>() {
			
			@Override
			public ListCell<Animal> call(ListView<Animal> param) {
				return new CellLabel<Animal>();
			}
		});
	}
	
	public void validate() {
		action.getParameters().put("ACTION", "Validate");
		action.validate();
	}
	
	public void cancel() {
		action.cancel();
	}
	
	public void clear() {
		animal.getSelectionModel().clearSelection();
		dateMeeting.getEditor().clear();
		desc.clear();
	}

	/**
	 * @return the owner
	 */
	public ComboBox<Client> getOwner() {
		return owner;
	}

	/**
	 * @return the animal
	 */
	public ComboBox<Animal> getAnimal() {
		return animal;
	}

	/**
	 * @return the desc
	 */
	public TextArea getDesc() {
		return desc;
	}

	/**
	 * @return the dateMeeting
	 */
	public DatePicker getDateMeeting() {
		return dateMeeting;
	}

	/**
	 * @return the info
	 */
	public Label getInfo() {
		return info;
	}

	
}
