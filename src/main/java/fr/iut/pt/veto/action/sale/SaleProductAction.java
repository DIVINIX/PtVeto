package fr.iut.pt.veto.action.sale;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import fr.iut.pt.veto.MainDisplay;
import fr.iut.pt.veto.action.Action;
import fr.iut.pt.veto.action.ActionEnum;
import fr.iut.pt.veto.form.sale.SaleProductController;
import fr.iut.pt.veto.model.dao.IndividuDao;
import fr.iut.pt.veto.model.dao.InventaireDao;
import fr.iut.pt.veto.model.entitie.individu.Client;
import fr.iut.pt.veto.model.entitie.inventaire.Produit;
import fr.iut.pt.veto.model.entitie.inventaire.Vente;
import fr.iut.pt.veto.util.JavaFxUtils;
import javafx.scene.layout.BorderPane;

public class SaleProductAction extends Action {

	private static final Logger LOG = Logger.getLogger(SaleProductAction.class);

	public SaleProductAction(MainDisplay main) {
		super(main);
		this.identifier = ActionEnum.SALE_PRODUCT;
	}

	@Override
	public void prepare() {
		LOG.debug("Entree dans la methode prepare de SaleProductAction");
		view = (BorderPane) JavaFxUtils.getView(this);
		parameters = new HashMap<>();

		List<Produit> produits = InventaireDao.findAllProduit();
		List<Client> clients = IndividuDao.findAllClient();
		((SaleProductController) controller).prepare(produits, clients);

		LOG.debug("Sortie de la methode prepare de SaleProductAction");
	}

	@Override
	public void validate() {
		LOG.debug("Entree dans la methode validate de SaleProductAction");

		Client client = ((SaleProductController) controller).getClients().getSelectionModel().getSelectedItem();
		Produit produit = ((SaleProductController) controller).getProduits().getSelectionModel().getSelectedItem();
		String quantiteBrut = ((SaleProductController) controller).getQuantite().getText();

		boolean validate = true;
		String message = null;

		int quantite = 0;

		if (produit != null && client != null) {
			try {
				quantite = Integer.parseInt(quantiteBrut);
				if (produit.getStock() < quantite) {
					validate = false;
					message = "Il n'y a pas assez de produit en stock (" + produit.getStock() + ").";
				}
			} catch (NumberFormatException ex) {
				message = "La quantite n'est pas correcte.";
				validate = false;
			}
		} else {
			validate = false;
			message = "Il manque des champs.";
		}

		if (validate) {
			Vente vente = new Vente(produit, client, main.getUtilisateur(), new Date(), quantite,
					produit.getPrix_reel() * quantite);
			InventaireDao.saveOrUpdate(vente);

			produit.setStock(produit.getStock() - quantite);
			InventaireDao.saveOrUpdate(produit);

			message = "La vente a ete faite.";
		}

		parameters.put("info", message);
		LOG.debug("Sortie de la methode validate de SaleProductAction");
		next();
	}

	@Override
	public void next() {
		((SaleProductController) controller).getInfo().setText((String) parameters.get("info"));
	}

	@Override
	public void cancel() {
		main.setAction(identifier);
	}

}
