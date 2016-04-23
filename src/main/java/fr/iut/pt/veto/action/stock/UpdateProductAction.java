package fr.iut.pt.veto.action.stock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import fr.iut.pt.veto.MainDisplay;
import fr.iut.pt.veto.action.Action;
import fr.iut.pt.veto.action.ActionEnum;
import fr.iut.pt.veto.form.stock.UpdateProductController;
import fr.iut.pt.veto.model.dao.InventaireDao;
import fr.iut.pt.veto.model.entitie.inventaire.Produit;
import fr.iut.pt.veto.util.JavaFxUtils;
import javafx.scene.layout.BorderPane;

public class UpdateProductAction extends Action {

	private static final Logger LOG = Logger.getLogger(UpdateProductAction.class);

	public UpdateProductAction(MainDisplay main) {
		super(main);
		this.identifier = ActionEnum.UPDATE_PRODUCT;
	}

	@Override
	public void prepare() {
		LOG.debug("Entree dans la methode prepare de UpdatePriceAction");
		view = (BorderPane) JavaFxUtils.getView(this);

		List<Produit> produits = new ArrayList<>();

		if (parameters != null && parameters.get("id") != null) {
			produits.add(InventaireDao.findProduitById((int) parameters.get("id")));
		} else {
			parameters = new HashMap<>();
			produits.addAll(InventaireDao.findAllProduit());
		}

		((UpdateProductController) controller).prepare(produits);

		LOG.debug("Sortie de la methode prepare de UpdatePriceAction");
	}

	@Override
	public void validate() {
		LOG.debug("Entree dans la methode validate de UpdatePriceAction");

		if ("Select".equals(parameters.get("ACTION"))) {
			LOG.debug("Action : Select");

			Produit produit = (Produit) parameters.get("select");

			((UpdateProductController) controller).fill(produit);

		} else if ("Validate".equals(parameters.get("ACTION"))) {
			LOG.debug("Action : Validate");

			if (parameters.get("select") != null) {
				Produit produit = (Produit) parameters.get("select");
				boolean validate = true;

				if (!((UpdateProductController) controller).getReference().getText().isEmpty()) {

					try {
						produit.setNom(((UpdateProductController) controller).getReference().getText());
						produit.setStock(
								Integer.parseInt((((UpdateProductController) controller).getQuantity().getText())));
						produit.setPrix_reel(
								Double.parseDouble(((UpdateProductController) controller).getPrice().getText()));
					} catch (NumberFormatException ex) {
						validate = false;
						parameters.put("info", "Les donnees sont incorrecte.");
					}
				} else {
					validate = false;
					parameters.put("info", "La reference est vide.");
				}

				if (validate) {
					InventaireDao.saveOrUpdate(produit);
					next();
				} else {
					LOG.error("Sortie avec erreur de la methode validate de UpdatePriceAction : "
							+ (String) parameters.get("info"));
					this.cancel();
				}
			}
		}

		LOG.debug("Sortie de la methode validate de UpdatePriceAction");

	}

	@Override
	public void next() {
		main.setAction(ActionEnum.LIST_STOCK);
	}

	@Override
	public void cancel() {
		if (parameters.get("info") != null) {
			((UpdateProductController) controller).getInfo().setText((String) parameters.get("info"));
		} else {
			((UpdateProductController) controller).getInfo().setText("Action Annule");
		}
		Produit produit = (Produit) parameters.get("select");
		((UpdateProductController) controller).fill(produit);
	}

}
