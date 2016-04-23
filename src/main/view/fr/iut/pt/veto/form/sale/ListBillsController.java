package fr.iut.pt.veto.form.sale;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import fr.iut.pt.veto.template.FormController;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.Initializable;
import fr.iut.pt.veto.model.entitie.individu.*;
import fr.iut.pt.veto.model.entitie.inventaire.*;

public class ListBillsController extends FormController implements Initializable {

	@FXML
	private TableView<Vente> tabBills;
	
	@FXML
	private TableColumn<Vente,Produit> produitColumn;
	
	@FXML
	private TableColumn<Vente,Client> clientColumn;
	
	@FXML
	private TableColumn<Vente,Salarie> salarieColumn;
	
	@FXML
	private TableColumn<Vente,Date> dateColumn;
	
	@FXML
	private TableColumn<Vente,Integer> quantiteColumn;
	
	@FXML
	private TableColumn<Vente,Double> prixColumn;
	
	
	public void prepare(List<Vente> ventes) {
		tabBills.getItems().setAll(ventes);
	}
	
	public void validate() {
		action.validate();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		produitColumn.setCellValueFactory(new PropertyValueFactory<Vente, Produit>("produit"));
		clientColumn.setCellValueFactory(new PropertyValueFactory<Vente, Client>("client"));
		salarieColumn.setCellValueFactory(new PropertyValueFactory<Vente, Salarie>("salarie"));
		dateColumn.setCellValueFactory(new PropertyValueFactory<Vente, Date>("date"));
		quantiteColumn.setCellValueFactory(new PropertyValueFactory<Vente, Integer>("quantite"));
		prixColumn.setCellValueFactory(new PropertyValueFactory<Vente, Double>("prix"));

	}
}
