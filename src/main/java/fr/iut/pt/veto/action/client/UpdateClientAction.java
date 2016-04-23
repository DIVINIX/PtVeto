package fr.iut.pt.veto.action.client;

import org.apache.log4j.Logger;

import fr.iut.pt.veto.MainDisplay;
import fr.iut.pt.veto.action.Action;
import fr.iut.pt.veto.action.ActionEnum;
import fr.iut.pt.veto.form.client.UpdateClientController;
import fr.iut.pt.veto.model.dao.IndividuDao;
import fr.iut.pt.veto.model.entitie.individu.Individu;
import fr.iut.pt.veto.util.JavaFxUtils;
import javafx.scene.layout.BorderPane;

public class UpdateClientAction extends Action {
	private static final Logger LOG = Logger.getLogger(UpdateClientAction.class);

	public UpdateClientAction(MainDisplay main) {
		super(main);
		this.identifier = ActionEnum.UPDATE_CLIENT;
	}

	@Override
	public void prepare() {
		LOG.debug("Entree dans la methode prepare de UpdateClientAction");
		view = (BorderPane) JavaFxUtils.getView(this);

		if (parameters != null && parameters.get("id") != null) {
			((UpdateClientController) controller).prepare(IndividuDao.findIndividuById((int) parameters.get("id")));
			parameters.put("ACTION", "Select");
			parameters.put("select", IndividuDao.findIndividuById((int) parameters.get("id")));
			
		} else {
			LOG.error("Sortie avec erreur de la methode prepare de UpdateClientAction : ");
		}

		LOG.debug("Sortie de la methode prepare de UpdateClientAction");
	}

	@Override
	public void validate() {
		LOG.debug("Entree dans la methode validate de UpdateClientAction");

		if ("Validate".equals(parameters.get("ACTION"))) {
			LOG.debug("Action : Validate");

				Individu client = (Individu) parameters.get("select");
				boolean validate = true;
				try {
					client.setNom(((UpdateClientController)controller).getLastName().getText());
					client.setPrenom(((UpdateClientController)controller).getFirstName().getText());
					client.setTelephone(((UpdateClientController)controller).getTelephone().getText());
					client.setAdresse(((UpdateClientController)controller).getAdress().getText());
					client.setMail(((UpdateClientController)controller).getMail().getText());
				} catch (NumberFormatException ex) {
					validate = false;
				}

				if (validate) {
					IndividuDao.saveOrUpdate(client);
					next();
				} else {
					LOG.error("Sortie avec erreur de la methode validate de UpdateClientAction : ");
					this.cancel();
				}
			
		}

		LOG.debug("Sortie de la methode validate de UpdateClientAction");
	}

	@Override
	public void next() {
		main.setAction(ActionEnum.LIST_CLIENTS);
		
	}

	@Override
	public void cancel() {
		main.setAction(ActionEnum.LIST_CLIENTS);
		
	}

}
