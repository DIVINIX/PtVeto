/**
 * 
 */
package fr.iut.pt.veto.action.client;

import java.util.HashMap;
import java.util.List;

import fr.iut.pt.veto.MainDisplay;
import fr.iut.pt.veto.action.Action;
import fr.iut.pt.veto.action.ActionEnum;
import fr.iut.pt.veto.form.client.ListClientsController;
import fr.iut.pt.veto.model.dao.IndividuDao;
import fr.iut.pt.veto.model.entitie.individu.Client;
import fr.iut.pt.veto.model.entitie.individu.Individu;
import fr.iut.pt.veto.util.JavaFxUtils;
import javafx.scene.layout.BorderPane;
import org.apache.log4j.Logger;

/**
 * @author Yann
 *
 */
public class ListClientsAction extends Action {
	
	private static final Logger LOG = Logger.getLogger(ListClientsAction.class);
	
	public ListClientsAction(MainDisplay main) {
		super(main);
		this.identifier = ActionEnum.LIST_CLIENTS;
	}

	@Override
	public void prepare() {		
		LOG.debug("Entree dans la methode prepare de ListClientsAction");
		view = (BorderPane) JavaFxUtils.getView(this);
		parameters = new HashMap<>();

		List<Client> clients = IndividuDao.findAllClient();
		((ListClientsController) controller).prepare(clients);
		LOG.debug("Sortie de la methode prepare de ListClientsAction");
	}

	@Override
	public void validate() {
		LOG.debug("Entree dans la methode validate de ListClientsAction");
		if ("Details".equals(parameters.get("ACTION"))) {
			LOG.debug("Action : Details");
			parameters.put("next", ActionEnum.DETAIL_CLIENT);
		}else if ("Modifier".equals(parameters.get("ACTION"))) {
			LOG.debug("Action : Modifier");
			parameters.put("next", ActionEnum.UPDATE_CLIENT);
		}else if ("Supprimer".equals(parameters.get("ACTION"))) {
			LOG.debug("Action : Supprimer");
			delete((int) parameters.get("id"));
			parameters.put("next", ActionEnum.LIST_CLIENTS);
		}else if("AjouterClient".equals(parameters.get("ACTION"))) {
			parameters.put("next", ActionEnum.ADD_CLIENT);
		}
		else if("ListRendezVous".equals(parameters.get("ACTION"))) {
			parameters.put("next", ActionEnum.LIST_MEETINGS);
		}
		
		LOG.debug("Sortie de la methode validate de ListClientsAction");
		next();

	}

	@Override
	public void next() {
		main.setAction((ActionEnum) parameters.get("next"));
	}

	@Override
	public void cancel() {
		// TODO Auto-generated method stub
		
	}
	
	private void delete(int id) {
		Individu client = IndividuDao.findIndividuById(id);
		IndividuDao.delete(client);
	}

}
