package fr.iut.pt.veto.form.stock;

import java.util.List;

import fr.iut.pt.veto.model.entitie.inventaire.Produit;
import fr.iut.pt.veto.template.FormController;
import fr.iut.pt.veto.util.CellLabel;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Callback;

public class UpdateProductController extends FormController {
	

	@FXML
	private ComboBox<Produit> product;
	
	@FXML
	private TextField reference;
	
	@FXML
	private TextField quantity;
	
	@FXML
	private TextField price;
	
	@FXML
	private Label info;
	
	public void prepare(List<Produit> produits) {
		product.getItems().addAll(produits);
		product.setCellFactory(new Callback<ListView<Produit>, ListCell<Produit>>() {
			
			@Override
			public ListCell<Produit> call(ListView<Produit> param) {
				return new CellLabel<Produit>();
			}
		});
	}
	
	public void select() {
		action.getParameters().put("ACTION", "Select");
		action.getParameters().put("select", product.getSelectionModel().getSelectedItem());
		action.validate();
	}
	
	public void fill(Produit produit) {
		reference.setText(produit.getNom());
		quantity.setText(String.valueOf(produit.getStock()));
		price.setText(String.valueOf(produit.getPrix_reel()));
	}
	
	public void validate() {
		action.getParameters().put("ACTION", "Validate");
		action.validate();
	}
	
	public void cancel() {
		action.cancel();
	}
	
	/**
	 * @return the product
	 */
	public ComboBox<Produit> getProduct() {
		return product;
	}

	/**
	 * @return the reference
	 */
	public TextField getReference() {
		return reference;
	}

	/**
	 * @return the quantity
	 */
	public TextField getQuantity() {
		return quantity;
	}

	/**
	 * @return the price
	 */
	public TextField getPrice() {
		return price;
	}

	/**
	 * @return the info
	 */
	public Label getInfo() {
		return info;
	}

}
