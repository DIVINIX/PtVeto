package fr.iut.pt.veto.form.admin;

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

public class AddUserController extends FormController {
	
	@FXML
	private TextField prenom;
	
	@FXML
	private TextField nom;
	
	@FXML
	private TextField telephone;
	
	@FXML
	private ComboBox<Pays> pays;
	
	@FXML
	private ComboBox<Ville> ville;
	
	@FXML
	private TextField adresse;
	
	@FXML
	private TextField mail;
	
	@FXML
	private ComboBox<Role> role;
	
	@FXML
	private TextField identifiant;
	
	@FXML
	private TextField password;
	
	@FXML
	private Label info;
	
	public void prepare(List<Pays> pays, List<Role> role) {
		this.pays.getItems().addAll(pays);
		this.pays.setCellFactory(new Callback<ListView<Pays>, ListCell<Pays>>() {
			
			@Override
			public ListCell<Pays> call(ListView<Pays> param) {
				return new CellLabel<Pays>();
			}
		});
		
		this.role.getItems().addAll(role);
		this.role.setCellFactory(new Callback<ListView<Role>, ListCell<Role>>() {
			
			@Override
			public ListCell<Role> call(ListView<Role> param) {
				return new CellLabel<Role>();
			}
		});
		this.ville.setVisible(false);
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
	
	public void validate() {
		action.getParameters().put("ACTION", "Validate");
		action.validate();
	}
	
	public void cancel() {
		action.cancel();
	}

	/**
	 * @return the prenom
	 */
	public TextField getPrenom() {
		return prenom;
	}

	/**
	 * @return the nom
	 */
	public TextField getNom() {
		return nom;
	}

	/**
	 * @return the telephone
	 */
	public TextField getTelephone() {
		return telephone;
	}

	/**
	 * @return the ville
	 */
	public ComboBox<Ville> getVille() {
		return ville;
	}

	/**
	 * @return the adresse
	 */
	public TextField getAdresse() {
		return adresse;
	}

	/**
	 * @return the mail
	 */
	public TextField getMail() {
		return mail;
	}

	/**
	 * @return the role
	 */
	public ComboBox<Role> getRole() {
		return role;
	}

	/**
	 * @return the identifiant
	 */
	public TextField getIdentifiant() {
		return identifiant;
	}

	/**
	 * @return the password
	 */
	public TextField getPassword() {
		return password;
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

}
