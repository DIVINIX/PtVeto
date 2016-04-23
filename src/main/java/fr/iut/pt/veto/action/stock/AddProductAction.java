package fr.iut.pt.veto.action.stock;

import java.util.HashMap;

import org.apache.log4j.Logger;

import fr.iut.pt.veto.MainDisplay;
import fr.iut.pt.veto.action.Action;
import fr.iut.pt.veto.action.ActionEnum;
import fr.iut.pt.veto.form.stock.AddProductController;
import fr.iut.pt.veto.model.dao.InventaireDao;
import fr.iut.pt.veto.model.entitie.inventaire.Produit;
import fr.iut.pt.veto.util.JavaFxUtils;
import javafx.scene.layout.BorderPane;

public class AddProductAction extends Action {

	private static final Logger LOG = Logger.getLogger(AddProductAction.class);

	public AddProductAction(MainDisplay main) {
		super(main);
		this.identifier = ActionEnum.ADD_PRODUCT;
	}

	@Override
	public void prepare() {
		LOG.debug("Entree dans la methode prepare de AddProductAction");
		view = (BorderPane) JavaFxUtils.getView(this);
		parameters = new HashMap<>();
		LOG.debug("Sortie de la methode prepare de AddProductAction");
	}

	@Override
	public void validate() {
		LOG.debug("Entree dans la methode validate de AddProductAction");

		String reference = ((AddProductController) controller).getReference().getText();
		String quantity = ((AddProductController) controller).getQuantity().getText();
		String price = ((AddProductController) controller).getPrice().getText();

		Produit produit = null;
		boolean validate = true;

		if (!reference.isEmpty()) {
			if (InventaireDao.findProduitByNom(reference) == null) {
				produit = new Produit(reference);
				try {
					produit.setPrix_reel(Double.parseDouble(price));
				} catch (NumberFormatException ex) {
					validate = false;
					parameters.put("info", "Le prix n'est pas correcte.");
				}
				try {
					produit.setStock(Integer.parseInt(quantity));
				} catch (NumberFormatException ex) {
					validate = false;
					parameters.put("info", "La quantite n'est pas correcte.");
				}
			} else {
				validate = false;
				parameters.put("info", "Le Produit existe deja.");
			}
		} else {
			validate = false;
			parameters.put("info", "La reference est vide.");
		}

		if (validate) {
			InventaireDao.saveOrUpdate(produit);
			parameters.put("info", "Produit " + produit.getNom() + " ajoute avec succes.");
			LOG.debug("Sortie avec succes de la methode validate de AddProductAction");
			this.next();
		} else {
			LOG.error("Sortie avec erreur de la methode validate de AddProductAction : "
					+ (String) parameters.get("info"));
			this.cancel();
		}
	}

	@Override
	public void next() {
		((AddProductController) controller).getInfo().setText((String) parameters.get("info"));
		((AddProductController) controller).clear();
	}

	@Override
	public void cancel() {
		if (parameters.get("info") != null) {
			((AddProductController) controller).getInfo().setText((String) parameters.get("info"));
		} else {
			((AddProductController) controller).getInfo().setText("Action Annule");
		}
		((AddProductController) controller).clear();
	}

}
