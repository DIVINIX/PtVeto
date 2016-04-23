package fr.iut.pt.veto.form.treatment;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import fr.iut.pt.veto.model.entitie.animal.Animal;
import fr.iut.pt.veto.model.entitie.individu.Client;
import fr.iut.pt.veto.model.entitie.traitement.Maladie;
import fr.iut.pt.veto.model.entitie.traitement.Traitement;
import fr.iut.pt.veto.template.FormController;
import fr.iut.pt.veto.util.CellBouton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;



public class ListTreatmentsController extends FormController implements Initializable {

	@FXML
	private ComboBox<Client> owner;
	
	@FXML
	private ComboBox<Animal> animal;
	
	@FXML
	private TableView<Traitement> tabTreatment;
	
	@FXML
	private TableColumn<Traitement,String> nameColumn;
	
	@FXML
	private TableColumn<Traitement,Maladie> maladyColumn;
	
	@FXML
	private TableColumn<Traitement,Date> dateBeginColumn;
	
	@FXML
	private TableColumn<Traitement,Date> dateEndColumn;
	
	@FXML
	private TableColumn<Traitement,Integer> updateColumn;
	
	@FXML
	private TableColumn<Traitement,Integer> detailsColumn;
	
	
	public void selectOwner() {
		animal.getSelectionModel().clearSelection();
		//
		action.getParameters().put("ACTION", "SelectOwner");
		action.getParameters().put("owner", owner.getSelectionModel().getSelectedItem());
		action.validate();
	}
	
	public void selectAnimal() {
		tabTreatment.getItems().clear();
		//
		action.getParameters().put("ACTION", "SelectAnimal");
		action.getParameters().put("animal", animal.getSelectionModel().getSelectedItem());
		action.validate();
	}
	
	public void actionAnimal(List<Traitement> traitements) {
		tabTreatment.getItems().clear();
		tabTreatment.getItems().addAll(traitements);
	}
	
	public void prepare(List<Traitement> traitements) {
		tabTreatment.getItems().setAll(traitements);
	}
	
	public void validate() {
		action.validate();
	}
	
	public void cancel() {
		action.cancel();
	}
	
	public ComboBox<Client> getOwner() {
		return owner;
	}
	
	public ComboBox<Animal> getAnimal() {
		return animal;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		nameColumn.setCellValueFactory(new PropertyValueFactory<Traitement, String>("nom"));
		maladyColumn.setCellValueFactory(new PropertyValueFactory<Traitement, Maladie>("maladie"));
		dateBeginColumn.setCellValueFactory(new PropertyValueFactory<Traitement, Date>("dateDebut"));
		dateEndColumn.setCellValueFactory(new PropertyValueFactory<Traitement, Date>("dateFin"));
		
		updateColumn.setCellValueFactory(new PropertyValueFactory<Traitement, Integer>("id"));
		updateColumn.setCellFactory(new Callback<TableColumn<Traitement, Integer>, TableCell<Traitement, Integer>>() {
			@Override
			public TableCell<Traitement, Integer> call(final TableColumn<Traitement, Integer> p) {
				final CellBouton<Traitement> bouton = new CellBouton<Traitement>("Modifier");
				bouton.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						action.getParameters().put("ACTION", "Modifier");
						action.getParameters().put("idTraitement", bouton.getValue());
						action.validate();
					}
				});
				return bouton;
			}
		});
		
		detailsColumn.setCellValueFactory(new PropertyValueFactory<Traitement, Integer>("id"));
		detailsColumn.setCellFactory(new Callback<TableColumn<Traitement, Integer>, TableCell<Traitement, Integer>>() {
			@Override
			public TableCell<Traitement, Integer> call(final TableColumn<Traitement, Integer> p) {
				final CellBouton<Traitement> bouton = new CellBouton<Traitement>("Details");
				bouton.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						action.getParameters().put("ACTION", "Details");
						action.getParameters().put("idTraitement", bouton.getValue());
						action.validate();
					}
				});
				return bouton;
				
			}
		});
		
	}
}
