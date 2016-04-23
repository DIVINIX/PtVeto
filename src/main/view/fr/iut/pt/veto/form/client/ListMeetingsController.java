package fr.iut.pt.veto.form.client;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import fr.iut.pt.veto.template.FormController;
import fr.iut.pt.veto.util.CellBouton;
import fr.iut.pt.veto.util.CellLabel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import fr.iut.pt.veto.model.entitie.Consultation;
import fr.iut.pt.veto.model.entitie.animal.Animal;
import fr.iut.pt.veto.model.entitie.individu.Client;
import fr.iut.pt.veto.model.entitie.individu.Individu;
import fr.iut.pt.veto.model.entitie.inventaire.Produit;

/**
 * 
 * @author Yann
 *
 */

public class ListMeetingsController extends FormController {

	@FXML
	private ComboBox<Client> clients;
	
	@FXML
	private TableView<Consultation> meetings;
	
	@FXML
	private TableColumn<Consultation,String> animal;
	
	@FXML
	private TableColumn<Consultation,String> date;
	
	@FXML
	private TableColumn<Consultation, Integer> detailColonne;
	
	@FXML
	private TableColumn<Consultation, Integer> modColonne;

	@FXML
	private TableColumn<Consultation, Integer> supprColonne;
	
	public void prepare(List<Consultation> consultations,List<Client> clients) {
		this.clients.getItems().setAll(clients);
		this.clients.setCellFactory(new Callback<ListView<Client>, ListCell<Client>>() {
			
			@Override
			public ListCell<Client> call(ListView<Client> param) {
				return new CellLabel<Client>();
			}
		});
		
		this.meetings.getItems().addAll(consultations);

		animal.setCellValueFactory(new PropertyValueFactory<Consultation, String>("animal"));
		date.setCellValueFactory(new PropertyValueFactory<Consultation, String>("dateDebut"));
		
		detailColonne.setCellValueFactory(new PropertyValueFactory<Consultation, Integer>("id"));
		detailColonne.setCellFactory(new Callback<TableColumn<Consultation, Integer>, TableCell<Consultation, Integer>>() {
			@Override
			public TableCell<Consultation, Integer> call(final TableColumn<Consultation, Integer> p) {
				final CellBouton<Consultation> bouton = new CellBouton<Consultation>("Détails");
				bouton.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						action.getParameters().put("ACTION", "Details");
						action.getParameters().put("id", bouton.getValue());
						action.validate();
					}
				});
				return bouton;
			}
		});

		modColonne.setCellValueFactory(new PropertyValueFactory<Consultation, Integer>("id"));
		modColonne.setCellFactory(new Callback<TableColumn<Consultation, Integer>, TableCell<Consultation, Integer>>() {
			@Override
			public TableCell<Consultation, Integer> call(final TableColumn<Consultation, Integer> p) {
				final CellBouton<Consultation> bouton = new CellBouton<Consultation>("Modifier");
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

		supprColonne.setCellValueFactory(new PropertyValueFactory<Consultation, Integer>("id"));
		supprColonne.setCellFactory(new Callback<TableColumn<Consultation, Integer>, TableCell<Consultation, Integer>>() {
			@Override
			public TableCell<Consultation, Integer> call(final TableColumn<Consultation, Integer> p) {
				final CellBouton<Consultation> bouton = new CellBouton<Consultation>("Retirer");
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
	
	public void select() {
		action.getParameters().put("ACTION", "Select");
		action.getParameters().put("select", clients.getSelectionModel().getSelectedItem());
		action.validate();
	}
	
	public void addConsultation() {
		action.getParameters().put("ACTION", "AjouterConsultation");
		action.validate();
	}

	/**
	 * @return the clients
	 */
	public ComboBox<Client> getClients() {
		return clients;
	}

	/**
	 * @return the meetings
	 */
	public TableView<Consultation> getMeetings() {
		return meetings;
	}

	/**
	 * @return the animal
	 */
	public TableColumn<Consultation, String> getAnimal() {
		return animal;
	}

	/**
	 * @return the date
	 */
	public TableColumn<Consultation, String> getDate() {
		return date;
	}

	/**
	 * @return the detailColonne
	 */
	public TableColumn<Consultation, Integer> getDetailColonne() {
		return detailColonne;
	}

	/**
	 * @return the modColonne
	 */
	public TableColumn<Consultation, Integer> getModColonne() {
		return modColonne;
	}

	/**
	 * @return the supprColonne
	 */
	public TableColumn<Consultation, Integer> getSupprColonne() {
		return supprColonne;
	}
	
	
}
