package fr.iut.pt.veto.form.sale;

import java.util.List;

import fr.iut.pt.veto.model.entitie.individu.Client;
import fr.iut.pt.veto.model.entitie.inventaire.Produit;
import fr.iut.pt.veto.template.FormController;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SaleProductController extends FormController {
	
	@FXML
    private ComboBox<Client> clients;

    @FXML
    private ComboBox<Produit> produits;
    
    @FXML
    private TextField quantite;
    
    @FXML
    private Label info;
    
    public void prepare(List<Produit> produit, List<Client> clients) {
    	this.produits.getItems().addAll(produit);
    	this.clients.getItems().addAll(clients);
    }
    
    public void validate() {
    	action.validate();
    }
    
    public void cancel() {
    	action.validate();
    }

	/**
	 * @return the clients
	 */
	public ComboBox<Client> getClients() {
		return clients;
	}

	/**
	 * @return the produits
	 */
	public ComboBox<Produit> getProduits() {
		return produits;
	}

	/**
	 * @return the quantite
	 */
	public TextField getQuantite() {
		return quantite;
	}

	/**
	 * @return the info
	 */
	public Label getInfo() {
		return info;
	}
}
