package fr.iut.pt.veto.action.admin;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import fr.iut.pt.veto.MainDisplay;
import fr.iut.pt.veto.action.Action;
import fr.iut.pt.veto.action.ActionEnum;
import fr.iut.pt.veto.action.stock.AddProductAction;
import fr.iut.pt.veto.form.admin.ListUsersController;
import fr.iut.pt.veto.model.dao.IndividuDao;
import fr.iut.pt.veto.model.entitie.individu.Salarie;
import fr.iut.pt.veto.util.JavaFxUtils;
import javafx.scene.layout.BorderPane;

public class ListUsersAction extends Action{
	
	private static final Logger LOG = Logger.getLogger(AddProductAction.class);

	public ListUsersAction(MainDisplay main) {
		super(main);
		this.identifier = ActionEnum.LIST_USERS;
	}

	@Override
	public void prepare() {
		LOG.debug("Entree dans la methode prepare de ListUsersAction");
		view = (BorderPane) JavaFxUtils.getView(this);
		parameters = new HashMap<>();

		List<Salarie> salaries = IndividuDao.findAllSalarie();
		((ListUsersController) controller).prepare(salaries);
		LOG.debug("Sortie de la methode prepare de ListUsersAction");
	}

	@Override
	public void validate() {
		LOG.debug("Entree dans la methode validate de ListUsersAction");
		if ("Modifier".equals(parameters.get("ACTION"))) {
			LOG.debug("Action : Modifier");
			parameters.put("next", ActionEnum.UPDATE_USER);
		} else if ("Supprimer".equals(parameters.get("ACTION"))) {
			LOG.debug("Action : Supprimer");
			delete((int) parameters.get("id"));
			parameters.put("next", ActionEnum.LIST_USERS);
		}
		
		LOG.debug("Sortie de la methode validate de ListUsersAction");
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
		Salarie salarie = (Salarie) IndividuDao.findIndividuById(id);
		
		IndividuDao.delete(salarie);
	}
	
}
