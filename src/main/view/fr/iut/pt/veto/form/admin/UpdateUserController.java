package fr.iut.pt.veto.form.admin;

import java.util.List;

import fr.iut.pt.veto.model.entitie.geo.Pays;
import fr.iut.pt.veto.model.entitie.geo.Ville;
import fr.iut.pt.veto.model.entitie.individu.Salarie;
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

public class UpdateUserController extends FormController {

	@FXML
	private ComboBox<Salarie> salaries;

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

	private static boolean DO_SELECT = true;

	public void prepare(List<Salarie> salaries) {
		this.salaries.setVisible(true);
		this.salaries.getItems().clear();
		this.salaries.getItems().addAll(salaries);
		this.salaries.setCellFactory(new Callback<ListView<Salarie>, ListCell<Salarie>>() {

			@Override
			public ListCell<Salarie> call(ListView<Salarie> param) {
				return new CellLabel<Salarie>();
			}
		});

		if (salaries.size() == 1) {
			this.salaries.getSelectionModel().selectFirst();
		}
	}

	public void select() {
		action.getParameters().put("ACTION", "SelectUser");
		action.getParameters().put("select", salaries.getSelectionModel().getSelectedItem());
		action.validate();
	}

	public void selectPays() {
		if (DO_SELECT) {
			action.getParameters().put("ACTION", "SelectPays");
			action.getParameters().put("select", pays.getSelectionModel().getSelectedItem());
			action.validate();
		}
	}

	public void fill(Salarie salarie, List<Pays> pays, List<Ville> ville, List<Role> role) {
		prenom.setText(salarie.getPrenom());
		nom.setText(salarie.getNom());
		telephone.setText(salarie.getTelephone());
		adresse.setText(salarie.getAdresse());
		mail.setText(salarie.getMail());
		identifiant.setText(salarie.getIdentifiant());
		password.setText(salarie.getPassword());

		DO_SELECT = false;

		fillPays(pays);
		fillVille(ville);
		fillRole(role);

		if (salarie.getVille() != null) {
			for (int i = 0; i < this.ville.getItems().size(); i++) {
				this.ville.getSelectionModel().selectNext();
				if (salarie.getVille().getNom().equals(this.ville.getSelectionModel().getSelectedItem().getNom())) {
					break;
				}
			}

			for (int i = 0; i < this.pays.getItems().size(); i++) {
				this.pays.getSelectionModel().selectNext();
				if (salarie.getVille().getPays().getNom()
						.equals(this.pays.getSelectionModel().getSelectedItem().getNom())) {
					break;
				}
			}
		}
		if (salarie.getRole() != null) {
			for (int i = 0; i < this.role.getItems().size(); i++) {
				this.role.getSelectionModel().selectNext();
				if (salarie.getRole().getIntitule()
						.equals(this.role.getSelectionModel().getSelectedItem().getIntitule())) {
					break;
				}
			}
		}

		DO_SELECT = true;
	}

	public void fillPays(List<Pays> pays) {
		this.pays.setVisible(true);
		this.pays.getItems().clear();
		this.pays.getItems().addAll(pays);
		this.pays.setCellFactory(new Callback<ListView<Pays>, ListCell<Pays>>() {

			@Override
			public ListCell<Pays> call(ListView<Pays> param) {
				return new CellLabel<Pays>();
			}
		});
	}
	
	public void fillRole(List<Role> roles) {
		this.role.setVisible(true);
		this.role.getItems().clear();
		this.role.getItems().addAll(roles);
		this.role.setCellFactory(new Callback<ListView<Role>, ListCell<Role>>() {

			@Override
			public ListCell<Role> call(ListView<Role> param) {
				return new CellLabel<Role>();
			}
		});
	}

	public void fillVille(List<Ville> villes) {
		this.ville.setVisible(true);
		this.ville.getItems().clear();
		this.ville.setPromptText("Choisir une ville");
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
		action.getParameters().put("select", this.salaries.getSelectionModel().getSelectedItem());
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
