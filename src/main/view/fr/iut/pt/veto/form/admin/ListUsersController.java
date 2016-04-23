package fr.iut.pt.veto.form.admin;

import java.util.List;

import fr.iut.pt.veto.model.entitie.geo.Pays;
import fr.iut.pt.veto.model.entitie.geo.Ville;
import fr.iut.pt.veto.model.entitie.individu.Salarie;
import fr.iut.pt.veto.model.entitie.inventaire.Produit;
import fr.iut.pt.veto.model.entitie.rh.Role;
import fr.iut.pt.veto.template.FormController;
import fr.iut.pt.veto.util.CellBouton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class ListUsersController extends FormController {

	@FXML
	private TableView<Salarie> salaries;

	@FXML
	private TableColumn<Salarie, String> nom;

	@FXML
	private TableColumn<Salarie, String> prenom;

	@FXML
	private TableColumn<Salarie, String> telephone;

	@FXML
	private TableColumn<Salarie, Ville> ville;

	@FXML
	private TableColumn<Salarie, String> adresse;
	
	@FXML
	private TableColumn<Salarie, String> mail;
	
	@FXML
	private TableColumn<Salarie, Role> role;
	
	@FXML
	private TableColumn<Salarie, String> identifiant;
	
	@FXML
	private TableColumn<Produit, Integer> modColonne;

	@FXML
	private TableColumn<Produit, Integer> supprColonne;

	public void prepare(List<Salarie> salaries) {
		this.salaries.getItems().addAll(salaries);

		nom.setCellValueFactory(new PropertyValueFactory<Salarie, String>("nom"));
		prenom.setCellValueFactory(new PropertyValueFactory<Salarie, String>("prenom"));
		telephone.setCellValueFactory(new PropertyValueFactory<Salarie, String>("telephone"));
		
		ville.setCellValueFactory(new PropertyValueFactory<Salarie, Ville>("ville"));

		adresse.setCellValueFactory(new PropertyValueFactory<Salarie, String>("adresse"));
		mail.setCellValueFactory(new PropertyValueFactory<Salarie, String>("mail"));
		
		role.setCellValueFactory(new PropertyValueFactory<Salarie, Role>("role"));
		
		identifiant.setCellValueFactory(new PropertyValueFactory<Salarie, String>("identifiant"));
		
		modColonne.setCellValueFactory(new PropertyValueFactory<Produit, Integer>("id"));
		modColonne.setCellFactory(new Callback<TableColumn<Produit, Integer>, TableCell<Produit, Integer>>() {
			@Override
			public TableCell<Produit, Integer> call(final TableColumn<Produit, Integer> p) {
				final CellBouton<Produit> bouton = new CellBouton<Produit>("Modifier");
				bouton.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						action.getParameters().put("ACTION", "Modifier");
						action.getParameters().put("id", bouton.getValue());
						action.validate();
					}
				});
				return bouton;
			}
		});

		supprColonne.setCellValueFactory(new PropertyValueFactory<Produit, Integer>("id"));
		supprColonne.setCellFactory(new Callback<TableColumn<Produit, Integer>, TableCell<Produit, Integer>>() {
			@Override
			public TableCell<Produit, Integer> call(final TableColumn<Produit, Integer> p) {
				final CellBouton<Produit> bouton = new CellBouton<Produit>("Retirer");
				bouton.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						action.getParameters().put("ACTION", "Supprimer");
						action.getParameters().put("id", bouton.getValue());
						action.validate();
					}
				});
				return bouton;
			}
		});

	}

}
