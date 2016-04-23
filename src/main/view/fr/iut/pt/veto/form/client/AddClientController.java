package fr.iut.pt.veto.form.client;

import java.util.List;

import fr.iut.pt.veto.model.entitie.geo.Pays;
import fr.iut.pt.veto.model.entitie.geo.Ville;
import fr.iut.pt.veto.model.entitie.rh.Role;
import fr.iut.pt.veto.template.FormController;
import fr.iut.pt.veto.util.CellLabel;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Callback;

/**
 * 
 * @author Yann
 *
 */

public class AddClientController extends FormController {

	@FXML
	private TextField lastName;
	
	@FXML
	private TextField firstName;
	
	@FXML
	private TextField telephone;
	
	@FXML
	private ComboBox<Pays> pays;
	
	@FXML
	private ComboBox<Ville> ville;
	
	@FXML
	private TextField adress;
	
	@FXML
	private TextField mail;
	
	@FXML
	private Label info;
	
	public void prepare(List<Pays> pays) {
		this.pays.getItems().addAll(pays);
		this.pays.setCellFactory(new Callback<ListView<Pays>, ListCell<Pays>>() {
			
			@Override
			public ListCell<Pays> call(ListView<Pays> param) {
				return new CellLabel<Pays>();
			}
		});
		
		this.ville.setVisible(false);
	}
	
	public void validate() {
		action.getParameters().put("ACTION", "Validate");
		action.validate();
	}
	
	public void cancel() {
		action.cancel();
	}
	
	public void clear() {
		lastName.clear();
		firstName.clear();
		telephone.clear();
		adress.clear();
		mail.clear();
	}
	
	public void select() {
		action.getParameters().put("ACTION", "Select");
		action.getParameters().put("pays", pays.getSelectionModel().getSelectedItem());
		action.validate();
	}
	
	public void fillVille(List<Ville> villes) {
		this.ville.setVisible(true);
		this.ville.getItems().clear();
		this.ville.getItems().addAll(villes);
		this.ville.setCellFactory(new Callback<ListView<Ville>, ListCell<Ville>>() {
			
			@Override
			public ListCell<Ville> call(ListView<Ville> param) {
				return new CellLabel<Ville>();
			}
		});
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

	/**
	 * @return the telephone
	 */
	public TextField getTelephone() {
		return telephone;
	}

	/**
	 * @return the info
	 */
	public Label getInfo() {
		return info;
	}

	/**
	 * @return the pays
	 */
	public ComboBox<Pays> getPays() {
		return pays;
	}

	/**
	 * @return the ville
	 */
	public ComboBox<Ville> getVille() {
		return ville;
	}
	
	
}
