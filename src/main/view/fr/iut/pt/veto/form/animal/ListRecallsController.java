package fr.iut.pt.veto.form.animal;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import fr.iut.pt.veto.model.entitie.animal.Animal;
import fr.iut.pt.veto.model.entitie.traitement.Rappel;
import fr.iut.pt.veto.template.FormController;
import fr.iut.pt.veto.util.CellBouton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

/**
 * @author Gauthier
 *
 */

public class ListRecallsController extends FormController implements Initializable {

    @FXML
    private TableView<Rappel> tabRecalls;
    @FXML
    private TableColumn<Rappel, String> nameColumn;
    @FXML
    private TableColumn<Rappel, Date> dateColumn;
    @FXML
    private TableColumn<Rappel, Animal> animalColumn;
    @FXML
    private TableColumn<Rappel, Integer> updateColumn;
    @FXML
    private TableColumn<Rappel, Integer> deleteColumn;
	
	public void prepare(List<Rappel> rappels) {
		tabRecalls.getItems().setAll(rappels);
	}
    
	public void validate() {
		action.validate();
	}
	
	public void cancel() {
		action.cancel();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		nameColumn.setCellValueFactory(new PropertyValueFactory<Rappel, String>("raison"));
		dateColumn.setCellValueFactory(new PropertyValueFactory<Rappel, Date>("date"));
		animalColumn.setCellValueFactory(new PropertyValueFactory<Rappel, Animal>("animal"));

		updateColumn.setCellValueFactory(new PropertyValueFactory<Rappel, Integer>("id"));
		updateColumn.setCellFactory(new Callback<TableColumn<Rappel, Integer>, TableCell<Rappel, Integer>>() {
			@Override
			public TableCell<Rappel, Integer> call(final TableColumn<Rappel, Integer> p) {
				final CellBouton<Rappel> bouton = new CellBouton<Rappel>("Modifier");
				bouton.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						action.getParameters().put("ACTION", "Modifier");
						action.getParameters().put("idRappel", bouton.getValue());
						action.validate();
					}
				});
				return bouton;
			}
		});

		deleteColumn.setCellValueFactory(new PropertyValueFactory<Rappel, Integer>("id"));
		deleteColumn.setCellFactory(new Callback<TableColumn<Rappel, Integer>, TableCell<Rappel, Integer>>() {
			@Override
			public TableCell<Rappel, Integer> call(final TableColumn<Rappel, Integer> p) {
				final CellBouton<Rappel> bouton = new CellBouton<Rappel>("Supprimer");
				bouton.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						action.getParameters().put("ACTION", "Supprimer");
						action.getParameters().put("idRappel", bouton.getValue());
						action.validate();
					}
				});
				return bouton;
			}
		});
		
	}
	
}
