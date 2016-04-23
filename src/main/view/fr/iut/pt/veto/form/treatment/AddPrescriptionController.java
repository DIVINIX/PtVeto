package fr.iut.pt.veto.form.treatment;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import fr.iut.pt.veto.model.entitie.animal.Animal;
import fr.iut.pt.veto.model.entitie.individu.Client;
import fr.iut.pt.veto.model.entitie.inventaire.Produit;
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

public class AddPrescriptionController extends FormController implements Initializable {

	@FXML
	private ComboBox<Client> owner;
	
	@FXML
	private ComboBox<Animal> animal;

	@FXML
	private ComboBox<Traitement> treatment;
	
	@FXML
	private ComboBox<Produit> product;
	
	@FXML
	private TableView<Produit> tabProduct;
	
	@FXML
	private TableColumn<Produit,String> nameColumn;
	
	@FXML
	private TableColumn<Produit,Integer> deleteColumn;
	
	public void selectOwner() {
		action.getParameters().put("ACTION", "SelectOwner");
		action.validate();
	}
	
	public void selectAnimal() {
		action.getParameters().put("ACTION", "SelectAnimal");
		action.validate();
	}
	
	public void selectTreatment() {
		action.getParameters().put("ACTION", "SelectTreatment");
		action.getParameters().put("idTraitement", treatment.getSelectionModel().getSelectedItem().getId());
		action.validate();
	}
	
	public void showProducts(List<Produit> produits) {
		tabProduct.getItems().setAll(produits);
	}
	
	public void addProduct() {
		action.getParameters().put("ACTION", "AddProduct");
		action.getParameters().put("idProduit", product.getSelectionModel().getSelectedItem().getId());
		action.validate();
	}
	
	public void prepare(List<Produit> produits) {
		product.getItems().setAll(produits);
	}
	
	public void validate() {
		action.getParameters().put("ACTION", "Validate");
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
	
	public ComboBox<Traitement> getTreatment() {
		return treatment;
	}
	
	public ComboBox<Produit> getProduct() {
		return product;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		nameColumn.setCellValueFactory(new PropertyValueFactory<Produit, String>("nom"));
		
		deleteColumn.setCellValueFactory(new PropertyValueFactory<Produit, Integer>("id"));
		deleteColumn.setCellFactory(new Callback<TableColumn<Produit, Integer>, TableCell<Produit, Integer>>() {
			@Override
			public TableCell<Produit, Integer> call(final TableColumn<Produit, Integer> p) {
				final CellBouton<Produit> bouton = new CellBouton<Produit>("Supprimer");
				bouton.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						action.getParameters().put("ACTION", "Supprimer");
						action.getParameters().put("idTraitement", treatment.getSelectionModel().getSelectedItem().getId());
						action.getParameters().put("idProduit", bouton.getValue());
						action.validate();
					}
				});
				return bouton;
			}
		});
		
	}
	
}
