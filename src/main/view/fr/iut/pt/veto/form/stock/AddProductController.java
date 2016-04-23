package fr.iut.pt.veto.form.stock;

import fr.iut.pt.veto.template.FormController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddProductController extends FormController {

	@FXML
	private TextField reference;
	
	@FXML
	private TextField quantity;
	
	@FXML
	private TextField price;
	
	@FXML
	private Label info;
	
	public void validate() {
		action.validate();
	}
	
	public void cancel() {
		action.cancel();
	}
	
	public void clear() {
		reference.clear();
		quantity.clear();
		price.clear();
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
