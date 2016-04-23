package fr.iut.pt.veto.form.HR;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import fr.iut.pt.veto.model.entitie.Consultation;
import fr.iut.pt.veto.model.entitie.individu.Client;
import fr.iut.pt.veto.model.entitie.individu.Salarie;
import fr.iut.pt.veto.model.entitie.rh.*;
import fr.iut.pt.veto.template.FormController;
import fr.iut.pt.veto.util.CellBouton;
import fr.iut.pt.veto.util.CellLabel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class ListLeavesController extends FormController {

	@FXML
	private ComboBox<Salarie> employee;
	
	@FXML
	private TableView<Absence> tabLeave;

	@FXML
	private TableColumn<Absence, TypeAbsence> typeColumn;
	
	@FXML
	private TableColumn<Absence, Date> dateBeginColumn;
	
	@FXML
	private TableColumn<Absence, Date> dateEndColumn;
	
	@FXML
	private TableColumn<Absence, Integer> supprColonne;
	
	@FXML
	private Label info;
	
	public void prepare(List<Absence> absences,List<Salarie> salarie) {
		this.employee.getItems().setAll(salarie);
		this.employee.setCellFactory(new Callback<ListView<Salarie>, ListCell<Salarie>>() {
			
			@Override
			public ListCell<Salarie> call(ListView<Salarie> param) {
				return new CellLabel<Salarie>();
			}
		});
		
		this.tabLeave.getItems().setAll(absences);
		typeColumn.setCellValueFactory(new PropertyValueFactory<Absence, TypeAbsence>("type"));
		dateBeginColumn.setCellValueFactory(new PropertyValueFactory<Absence, Date>("dateDebut"));
		dateEndColumn.setCellValueFactory(new PropertyValueFactory<Absence, Date>("dateFin"));
		
		supprColonne.setCellValueFactory(new PropertyValueFactory<Absence, Integer>("id"));
		supprColonne.setCellFactory(new Callback<TableColumn<Absence, Integer>, TableCell<Absence, Integer>>() {
			@Override
			public TableCell<Absence, Integer> call(final TableColumn<Absence, Integer> p) {
				final CellBouton<Absence> bouton = new CellBouton<Absence>("Retirer");
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
		action.getParameters().put("select", employee.getSelectionModel().getSelectedItem());
		action.validate();
	}

	/**
	 * @return the employee
	 */
	public ComboBox<Salarie> getEmployee() {
		return employee;
	}

	/**
	 * @return the tabLeave
	 */
	public TableView<Absence> getTabLeave() {
		return tabLeave;
	}

	/**
	 * @return the typeColumn
	 */
	public TableColumn<Absence, TypeAbsence> getTypeColumn() {
		return typeColumn;
	}

	/**
	 * @return the dateBeginColumn
	 */
	public TableColumn<Absence, Date> getDateBeginColumn() {
		return dateBeginColumn;
	}

	/**
	 * @return the dateEndColumn
	 */
	public TableColumn<Absence, Date> getDateEndColumn() {
		return dateEndColumn;
	}

	/**
	 * @return the supprColonne
	 */
	public TableColumn<Absence, Integer> getSupprColonne() {
		return supprColonne;
	}

	/**
	 * @return the info
	 */
	public Label getInfo() {
		return info;
	}
	
	
}
