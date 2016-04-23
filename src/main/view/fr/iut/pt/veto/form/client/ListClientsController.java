package fr.iut.pt.veto.form.client;


import fr.iut.pt.veto.template.FormController;
import fr.iut.pt.veto.util.CellBouton;

import java.util.List;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import fr.iut.pt.veto.model.entitie.geo.Ville;
import fr.iut.pt.veto.model.entitie.individu.*;

/**
 * @author Yann
 *
 */

public class ListClientsController extends FormController {

	@FXML
	private TableView<Client> clients;
	
	@FXML
	private TableColumn<Client,String> lastName;
	
	@FXML
	private TableColumn<Client,String> firstName;
	
	@FXML
	private TableColumn<Client,Ville> ville;
	
	@FXML
	private TableColumn<Client,String> telephone;
	
	@FXML
	private TableColumn<Client,String> mail;
	
	@FXML
	private TableColumn<Client, Integer> detailColonne;
	
	@FXML
	private TableColumn<Client, Integer> modColonne;

	@FXML
	private TableColumn<Client, Integer> supprColonne;
	
	public void prepare(List<Client> clients) {
		this.clients.getItems().addAll(clients);

		lastName.setCellValueFactory(new PropertyValueFactory<Client, String>("nom"));
		firstName.setCellValueFactory(new PropertyValueFactory<Client, String>("prenom"));
		ville.setCellValueFactory(new PropertyValueFactory<Client, Ville>("ville"));
		telephone.setCellValueFactory(new PropertyValueFactory<Client, String>("telephone"));
		mail.setCellValueFactory(new PropertyValueFactory<Client, String>("mail"));
		
		detailColonne.setCellValueFactory(new PropertyValueFactory<Client, Integer>("id"));
		detailColonne.setCellFactory(new Callback<TableColumn<Client, Integer>, TableCell<Client, Integer>>() {
			@Override
			public TableCell<Client, Integer> call(final TableColumn<Client, Integer> p) {
				final CellBouton<Client> bouton = new CellBouton<Client>("Détails");
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

		modColonne.setCellValueFactory(new PropertyValueFactory<Client, Integer>("id"));
		modColonne.setCellFactory(new Callback<TableColumn<Client, Integer>, TableCell<Client, Integer>>() {
			@Override
			public TableCell<Client, Integer> call(final TableColumn<Client, Integer> p) {
				final CellBouton<Client> bouton = new CellBouton<Client>("Modifier");
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

		supprColonne.setCellValueFactory(new PropertyValueFactory<Client, Integer>("id"));
		supprColonne.setCellFactory(new Callback<TableColumn<Client, Integer>, TableCell<Client, Integer>>() {
			@Override
			public TableCell<Client, Integer> call(final TableColumn<Client, Integer> p) {
				final CellBouton<Client> bouton = new CellBouton<Client>("Retirer");
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
	
	public void showMeetings() {
		action.getParameters().put("ACTION", "ListRendezVous");
		action.validate();
	}

	public void addClient() {
		action.getParameters().put("ACTION", "AjouterClient");
		action.validate();
	}

}
