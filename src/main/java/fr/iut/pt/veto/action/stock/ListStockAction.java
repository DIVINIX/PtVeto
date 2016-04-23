package fr.iut.pt.veto.action.stock;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import fr.iut.pt.veto.MainDisplay;
import fr.iut.pt.veto.action.Action;
import fr.iut.pt.veto.action.ActionEnum;
import fr.iut.pt.veto.form.stock.ListStockController;
import fr.iut.pt.veto.model.dao.InventaireDao;
import fr.iut.pt.veto.model.entitie.inventaire.Produit;
import fr.iut.pt.veto.util.JavaFxUtils;
import javafx.scene.layout.BorderPane;

public class ListStockAction extends Action {

	private static final Logger LOG = Logger.getLogger(ListStockAction.class);

	public ListStockAction(MainDisplay main) {
		super(main);
		this.identifier = ActionEnum.LIST_STOCK;
	}

	@Override
	public void prepare() {
		LOG.debug("Entree dans la methode prepare de ListStockAction");
		view = (BorderPane) JavaFxUtils.getView(this);
		parameters = new HashMap<>();

		List<Produit> produits = InventaireDao.findAllProduit();
		((ListStockController) controller).prepare(produits);
		LOG.debug("Sortie de la methode prepare de ListStockAction");
	}

	@Override
	public void validate() {
		LOG.debug("Entree dans la methode validate de ListStockAction");
		if ("Modifier".equals(parameters.get("ACTION"))) {
			LOG.debug("Action : Modifier");
			parameters.put("next", ActionEnum.UPDATE_PRODUCT);
		} else if ("Supprimer".equals(parameters.get("ACTION"))) {
			LOG.debug("Action : Supprimer");
			delete((int) parameters.get("id"));
			parameters.put("next", ActionEnum.LIST_STOCK);
		}
		
		LOG.debug("Sortie de la methode validate de ListStockAction");
		next();
	}

	@Override
	public void next() {
		main.setAction((ActionEnum) parameters.get("next"));
	}

	@Override
	public void cancel() {
	}
	
	private void delete(int id) {
		Produit produit = InventaireDao.findProduitById(id);
		
		produit.setStock(0);
		
		InventaireDao.saveOrUpdate(produit);
	}

}
