package fr.iut.pt.veto.action.sale;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import fr.iut.pt.veto.MainDisplay;
import fr.iut.pt.veto.action.Action;
import fr.iut.pt.veto.action.ActionEnum;
import fr.iut.pt.veto.form.sale.ListBillsController;
import fr.iut.pt.veto.model.dao.InventaireDao;
import fr.iut.pt.veto.model.entitie.inventaire.Vente;
import fr.iut.pt.veto.util.JavaFxUtils;
import javafx.scene.layout.BorderPane;

public class ListBillsAction extends Action  {

	private static final Logger LOG = Logger.getLogger(ListBillsAction.class);
	
	public ListBillsAction(MainDisplay main) {
		super(main);
		this.identifier = ActionEnum.LIST_BILLS;
	}

	@Override
	public void prepare() {
		LOG.debug("Entree dans la methode prepare de ListBillsAction");
		
		view = (BorderPane) JavaFxUtils.getView(this);
		
		parameters = new HashMap<>();
		
		List<Vente> ventes = InventaireDao.findAllVente();
		((ListBillsController) controller).prepare(ventes);
		
		LOG.debug("Sortie de la methode prepare de ListBillsAction");
	}

	@Override
	public void validate() {
	}

	@Override
	public void next() {
	}

	@Override
	public void cancel() {
	}

}
