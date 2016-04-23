package fr.iut.pt.veto.action.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import fr.iut.pt.veto.MainDisplay;
import fr.iut.pt.veto.action.Action;
import fr.iut.pt.veto.action.ActionEnum;
import fr.iut.pt.veto.form.client.ListClientsController;
import fr.iut.pt.veto.form.client.ListMeetingsController;
import fr.iut.pt.veto.form.stock.UpdateProductController;
import fr.iut.pt.veto.model.dao.ConsultationDao;
import fr.iut.pt.veto.model.dao.IndividuDao;
import fr.iut.pt.veto.model.dao.InventaireDao;
import fr.iut.pt.veto.model.entitie.Consultation;
import fr.iut.pt.veto.model.entitie.individu.Client;
import fr.iut.pt.veto.model.entitie.individu.Individu;
import fr.iut.pt.veto.model.entitie.inventaire.Produit;
import fr.iut.pt.veto.util.JavaFxUtils;
import javafx.scene.layout.BorderPane;

/**
 * 
 * @author Yann
 *
 */

public class ListMeetingsAction extends Action {

	private static final Logger LOG = Logger.getLogger(ListMeetingsAction.class);

	public ListMeetingsAction(MainDisplay main) {
		super(main);
		this.identifier = ActionEnum.LIST_MEETINGS;
	}

	@Override
	public void prepare() {
		LOG.debug("Entree dans la methode prepare de ListMeetingsAction");
		view = (BorderPane) JavaFxUtils.getView(this);

		List<Consultation> consultations = new ArrayList<>();

		if (parameters != null && parameters.get("selectClientListRendezVous") != null) {
			consultations.addAll(
					ConsultationDao.findConsultationByClient((Client) parameters.get("selectClientListRendezVous")));
			parameters.remove("selectClientListRendezVous");
		} else if (parameters != null && parameters.get("selectClient") != null) {
			consultations.addAll(ConsultationDao.findConsultationByClient((Client) parameters.get("selectClient")));
			parameters.clear();
		} else {
			parameters = new HashMap<>();
			consultations.addAll(ConsultationDao.findAllConsultation());
		}

		List<Client> clients = IndividuDao.findAllClient();
		((ListMeetingsController) controller).prepare(consultations, clients);
		LOG.debug("Sortie de la methode prepare de ListMeetingsAction");

	}

	@Override
	public void validate() {
		LOG.debug("Entree dans la methode validate de ListMeetingsAction");
		if ("Select".equals(parameters.get("ACTION"))) {
			LOG.debug("Action : Select");

			parameters.put("selectClient", (Client) parameters.get("select"));
			parameters.put("next", ActionEnum.LIST_MEETINGS);
			next();

		} else if ("Details".equals(parameters.get("ACTION"))) {
			LOG.debug("Action : Details");
			parameters.put("next", ActionEnum.DETAIL_MEETING);
			LOG.debug("Sortie de la methode validate de ListMeetingsAction");
			next();
		} else if ("Modifier".equals(parameters.get("ACTION"))) {
			LOG.debug("Action : Modifier");
			parameters.put("next", ActionEnum.UPDATE_MEETING);
			LOG.debug("Sortie de la methode validate de ListMeetingsAction");
			next();
		} else if ("Supprimer".equals(parameters.get("ACTION"))) {
			LOG.debug("Action : Supprimer");
			delete((int) parameters.get("id"));
			parameters.put("next", ActionEnum.LIST_MEETINGS);
			LOG.debug("Sortie de la methode validate de ListMeetingsAction");
			next();
		} else if ("AjouterConsultation".equals(parameters.get("ACTION"))) {
			parameters.put("next", ActionEnum.ADD_MEETING);
			LOG.debug("Sortie de la methode validate de ListMeetingsAction");
			next();
		}

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
		Consultation consultation = ConsultationDao.findConsultationById(id);
		ConsultationDao.delete(consultation);
	}

}
