package fr.iut.pt.veto.action.client;

import org.apache.log4j.Logger;

import fr.iut.pt.veto.MainDisplay;
import fr.iut.pt.veto.action.Action;
import fr.iut.pt.veto.action.ActionEnum;
import fr.iut.pt.veto.form.client.DetailClientController;
import fr.iut.pt.veto.form.client.UpdateClientController;
import fr.iut.pt.veto.model.dao.IndividuDao;
import fr.iut.pt.veto.model.entitie.individu.Client;
import fr.iut.pt.veto.util.JavaFxUtils;
import javafx.scene.layout.BorderPane;

public class DetailClientAction extends Action {
	private static final Logger LOG = Logger.getLogger(DetailClientAction.class);

	private Client clientToShowDetails;
	
	public DetailClientAction(MainDisplay main) {
		super(main);
		this.identifier = ActionEnum.DETAIL_CLIENT;
	}

	@Override
	public void prepare() {
		LOG.debug("Entree dans la methode prepare de DetailClientAction");
		view = (BorderPane) JavaFxUtils.getView(this);

		if (parameters != null && parameters.get("id") != null) {
			((DetailClientController) controller).prepare(IndividuDao.findIndividuById((int) parameters.get("id")));
			parameters.put("ACTION", "Select");
			parameters.put("select", IndividuDao.findIndividuById((int) parameters.get("id")));
			
		} else {
			LOG.error("Sortie avec erreur de la methode prepare de DetailClientAction : ");
		}

		LOG.debug("Sortie de la methode prepare de DetailClientAction");
	}

	@Override
	public void validate() {
		LOG.debug("Entree dans la methode validate de DetailClientAction");
		if ("Modifier".equals(parameters.get("ACTION"))) {
			LOG.debug("Action : Modifier");
			parameters.put("next", ActionEnum.UPDATE_CLIENT);
		}else if ("Retour".equals(parameters.get("ACTION"))) {
			LOG.debug("Action : Retour");
			parameters.put("next", ActionEnum.LIST_CLIENTS);
		}else if ("ListRendezVous".equals(parameters.get("ACTION"))) {
			LOG.debug("Action : ListRendezVous");
			parameters.put("ACTION", "SelectClientListRendezVous");
			parameters.put("selectClientListRendezVous", IndividuDao.findIndividuById((int) parameters.get("id")));
			parameters.put("next", ActionEnum.LIST_MEETINGS);
		}
		
		LOG.debug("Sortie de la methode validate de DetailClientAction");
		next();
		
	}

	@Override
	public void next() {
		main.setAction((ActionEnum) parameters.get("next"));
		
	}

	@Override
	public void cancel() {
		main.setAction(ActionEnum.LIST_CLIENTS);
		
	}
}
