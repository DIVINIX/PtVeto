package fr.iut.pt.veto.form.stock;

import java.util.List;

import fr.iut.pt.veto.model.entitie.inventaire.Produit;
import fr.iut.pt.veto.template.FormController;
import fr.iut.pt.veto.util.CellBouton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.PixelFormat.Type;
import javafx.util.Callback;

public class ListStockController extends FormController {

	@FXML
	private TableView<Produit> produits;

	@FXML
	private TableColumn<Produit, String> refColonne;

	@FXML
	private TableColumn<Produit, Integer> stockColonne;

	@FXML
	private TableColumn<Produit, Double> prixColonne;

	@FXML
	private TableColumn<Produit, Integer> modColonne;

	@FXML
	private TableColumn<Produit, Integer> supprColonne;

	public void prepare(List<Produit> produits) {
		this.produits.getItems().addAll(produits);

		refColonne.setCellValueFactory(new PropertyValueFactory<Produit, String>("nom"));
		stockColonne.setCellValueFactory(new PropertyValueFactory<Produit, Integer>("stock"));
		prixColonne.setCellValueFactory(new PropertyValueFactory<Produit, Double>("prix_reel"));

		modColonne.setCellValueFactory(new PropertyValueFactory<Produit, Integer>("id"));
		modColonne.setCellFactory(new Callback<TableColumn<Produit, Integer>, TableCell<Produit, Integer>>() {
			@Override
			public TableCell<Produit, Integer> call(final TableColumn<Produit, Integer> p) {
				final CellBouton<Produit> bouton = new CellBouton<Produit>("Modifier");
				bouton.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						action.getParameters().put("ACTION", "Modifier");
						action.getParameters().put("id", bouton.getValue());
						action.validate();
					}
				});
				return bouton;
			}
		});

		supprColonne.setCellValueFactory(new PropertyValueFactory<Produit, Integer>("id"));
		supprColonne.setCellFactory(new Callback<TableColumn<Produit, Integer>, TableCell<Produit, Integer>>() {
			@Override
			public TableCell<Produit, Integer> call(final TableColumn<Produit, Integer> p) {
				final CellBouton<Produit> bouton = new CellBouton<Produit>("Retirer");
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

}
