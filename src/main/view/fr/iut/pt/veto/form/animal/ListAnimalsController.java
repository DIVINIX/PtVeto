package fr.iut.pt.veto.form.animal;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import fr.iut.pt.veto.model.entitie.animal.*;
import fr.iut.pt.veto.model.entitie.individu.Client;
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

/**
 * @author Gauthier
 *
 */

public class ListAnimalsController extends FormController implements Initializable {

	@FXML
	private ComboBox<Client> owner;
	
    @FXML
    private TableView<Animal> tabAnimals;
    @FXML
    private TableColumn<Animal, String> nameColumn;
    @FXML
    private TableColumn<Animal, Client> ownerColumn;
    @FXML
    private TableColumn<Animal, Race> breedColumn;
    //
    @FXML
    private TableColumn<Animal, Integer> updateColumn;
    @FXML
    private TableColumn<Animal, Integer> recallColumn;
    @FXML
    private TableColumn<Animal, Integer> treatmentColumn;
    
	
	public void actionOwner(List<Animal> animals) {
		this.tabAnimals.getItems().setAll(animals);
	}
	
    
	public void prepare(List<Animal> animals,List<Client> clients) {
		//Client
		this.owner.getItems().setAll(clients);
		
		//Animal
		this.tabAnimals.getItems().setAll(animals);
	}
	
	public void selectOwner(){
		action.getParameters().put("ACTION", "Select");
		action.getParameters().put("select", owner.getSelectionModel().getSelectedItem());
		action.validate();
	}
	
	public ComboBox<Client> getOwner() {
		return owner;
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		nameColumn.setCellValueFactory(new PropertyValueFactory<Animal, String>("nom"));
		ownerColumn.setCellValueFactory(new PropertyValueFactory<Animal, Client>("client"));
		breedColumn.setCellValueFactory(new PropertyValueFactory<Animal, Race>("race"));
		
		recallColumn.setCellValueFactory(new PropertyValueFactory<Animal, Integer>("id"));
		recallColumn.setCellFactory(new Callback<TableColumn<Animal, Integer>, TableCell<Animal, Integer>>() {
			@Override
			public TableCell<Animal, Integer> call(final TableColumn<Animal, Integer> p) {
				final CellBouton<Animal> bouton = new CellBouton<Animal>("Rappel");
				bouton.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						action.getParameters().put("ACTION", "Rappel");
						action.getParameters().put("idAnimal", bouton.getValue());
						action.validate();
					}
				});
				return bouton;
			}
		});
		
		treatmentColumn.setCellValueFactory(new PropertyValueFactory<Animal, Integer>("id"));
		treatmentColumn.setCellFactory(new Callback<TableColumn<Animal, Integer>, TableCell<Animal, Integer>>() {
			@Override
			public TableCell<Animal, Integer> call(final TableColumn<Animal, Integer> p) {
				final CellBouton<Animal> bouton = new CellBouton<Animal>("Traitement");
				bouton.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						action.getParameters().put("ACTION", "Traitement");
						action.getParameters().put("idAnimal", bouton.getValue());
						action.validate();
					}
				});
				return bouton;
				
			}
		});
		
		updateColumn.setCellValueFactory(new PropertyValueFactory<Animal, Integer>("id"));
		updateColumn.setCellFactory(new Callback<TableColumn<Animal, Integer>, TableCell<Animal, Integer>>() {
			@Override
			public TableCell<Animal, Integer> call(final TableColumn<Animal, Integer> p) {
				final CellBouton<Animal> bouton = new CellBouton<Animal>("Modifier");
				bouton.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						action.getParameters().put("ACTION", "Modifier");
						action.getParameters().put("idAnimal", bouton.getValue());
						action.validate();
					}
				});
				return bouton;
			}
		});
	}
}
