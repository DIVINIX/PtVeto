package fr.iut.pt.veto.form.HR;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import fr.iut.pt.veto.model.entitie.individu.Salarie;
import fr.iut.pt.veto.model.entitie.rh.Role;
import fr.iut.pt.veto.template.FormController;
import fr.iut.pt.veto.util.CellBouton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class ListEmployeesController extends FormController implements Initializable {

	@FXML
	private TableView<Salarie> tabEmployee;
	
	@FXML
	private TableColumn<Salarie,Role> roleColumn;
	
	@FXML
	private TableColumn<Salarie,String> lastNameColumn;
	
	@FXML
	private TableColumn<Salarie,String> firstNameColumn;
	
	@FXML
	private TableColumn<Salarie,Integer> detailColumn;
	
	@FXML
	private TableColumn<Salarie,Integer> leaveColumn;
	
	@FXML
	private TableColumn<Salarie,Integer> contractColumn;
	
	@FXML
	private Label info;
	
	public void prepare(List<Salarie> salaries) {
		tabEmployee.getItems().setAll(salaries);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		roleColumn.setCellValueFactory(new PropertyValueFactory<Salarie, Role>("role"));
		lastNameColumn.setCellValueFactory(new PropertyValueFactory<Salarie, String>("nom"));
		firstNameColumn.setCellValueFactory(new PropertyValueFactory<Salarie, String>("prenom"));

		detailColumn.setCellValueFactory(new PropertyValueFactory<Salarie, Integer>("id"));
		detailColumn.setCellFactory(new Callback<TableColumn<Salarie, Integer>, TableCell<Salarie, Integer>>() {
			@Override
			public TableCell<Salarie, Integer> call(final TableColumn<Salarie, Integer> p) {
				final CellBouton<Salarie> bouton = new CellBouton<Salarie>("Détails");
				bouton.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						action.getParameters().put("ACTION", "Details");
						action.getParameters().put("idSalarie", bouton.getValue());
						action.validate();
					}
				});
				return bouton;
			}
		});
		
		leaveColumn.setCellValueFactory(new PropertyValueFactory<Salarie, Integer>("id"));
		leaveColumn.setCellFactory(new Callback<TableColumn<Salarie, Integer>, TableCell<Salarie, Integer>>() {
			@Override
			public TableCell<Salarie, Integer> call(final TableColumn<Salarie, Integer> p) {
				final CellBouton<Salarie> bouton = new CellBouton<Salarie>("Congé");
				bouton.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						action.getParameters().put("ACTION", "Conge");
						action.getParameters().put("idSalarieToConge", bouton.getValue());
						action.validate();
					}
				});
				return bouton;
			}
		});

		contractColumn.setCellValueFactory(new PropertyValueFactory<Salarie, Integer>("id"));
		contractColumn.setCellFactory(new Callback<TableColumn<Salarie, Integer>, TableCell<Salarie, Integer>>() {
			@Override
			public TableCell<Salarie, Integer> call(final TableColumn<Salarie, Integer> p) {
				final CellBouton<Salarie> bouton = new CellBouton<Salarie>("Contrat");
				bouton.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						action.getParameters().put("ACTION", "Contrat");
						action.getParameters().put("idSalarieToContrat", bouton.getValue());
						action.validate();
					}
				});
				return bouton;
			}
		});
	}

	/**
	 * @return the tabEmployee
	 */
	public TableView<Salarie> getTabEmployee() {
		return tabEmployee;
	}

	/**
	 * @return the roleColumn
	 */
	public TableColumn<Salarie, Role> getRoleColumn() {
		return roleColumn;
	}

	/**
	 * @return the lastNameColumn
	 */
	public TableColumn<Salarie, String> getLastNameColumn() {
		return lastNameColumn;
	}

	/**
	 * @return the firstNameColumn
	 */
	public TableColumn<Salarie, String> getFirstNameColumn() {
		return firstNameColumn;
	}

	/**
	 * @return the detailColumn
	 */
	public TableColumn<Salarie, Integer> getDetailColumn() {
		return detailColumn;
	}

	/**
	 * @return the leaveColumn
	 */
	public TableColumn<Salarie, Integer> getLeaveColumn() {
		return leaveColumn;
	}

	/**
	 * @return the contractColumn
	 */
	public TableColumn<Salarie, Integer> getContractColumn() {
		return contractColumn;
	}

	/**
	 * @return the info
	 */
	public Label getInfo() {
		return info;
	}
	
}
