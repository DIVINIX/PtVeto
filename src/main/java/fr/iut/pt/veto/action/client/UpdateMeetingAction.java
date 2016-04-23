package fr.iut.pt.veto.action.client;

import java.time.ZoneId;
import java.util.Date;

import org.apache.log4j.Logger;

import fr.iut.pt.veto.MainDisplay;
import fr.iut.pt.veto.action.Action;
import fr.iut.pt.veto.action.ActionEnum;
import fr.iut.pt.veto.form.client.AddMeetingController;
import fr.iut.pt.veto.form.client.DetailMeetingController;
import fr.iut.pt.veto.form.client.UpdateClientController;
import fr.iut.pt.veto.form.client.UpdateMeetingController;
import fr.iut.pt.veto.model.dao.ConsultationDao;
import fr.iut.pt.veto.model.dao.IndividuDao;
import fr.iut.pt.veto.model.entitie.Consultation;
import fr.iut.pt.veto.model.entitie.individu.Individu;
import fr.iut.pt.veto.util.JavaFxUtils;
import javafx.scene.layout.BorderPane;

public class UpdateMeetingAction extends Action {
	
	private static final Logger LOG = Logger.getLogger(UpdateMeetingAction.class);

	public UpdateMeetingAction(MainDisplay main) {
		super(main);
		this.identifier = ActionEnum.UPDATE_MEETING;
	}

	@Override
	public void prepare() {
		LOG.debug("Entree dans la methode prepare de UpdateMeetingAction");
		view = (BorderPane) JavaFxUtils.getView(this);

		if (parameters != null && parameters.get("id") != null) {
			((UpdateMeetingController) controller).prepare(ConsultationDao.findConsultationById((int) parameters.get("id")));
			parameters.put("ACTION", "Select");
			parameters.put("UpdateMeetingAction", ConsultationDao.findConsultationById((int) parameters.get("id")));
			
		} else {
			LOG.error("Sortie avec erreur de la methode prepare de UpdateMeetingAction : ");
		}

		LOG.debug("Sortie de la methode prepare de UpdateMeetingAction");
		
	}

	@Override
	public void validate() {
		LOG.debug("Entree dans la methode validate de UpdateMeetingAction");

		if ("Validate".equals(parameters.get("ACTION"))) {
			LOG.debug("Action : Validate");

				Consultation consultation = (Consultation) parameters.get("UpdateMeetingAction");
				boolean validate = true;
				try {
					consultation.setDateDebut(Date.from(((UpdateMeetingController) controller).getDate().getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
					consultation.setDescription(((UpdateMeetingController)controller).getDesc().getText());

				} catch (NumberFormatException ex) {
					validate = false;
				}

				if (validate) {
					ConsultationDao.saveOrUpdate(consultation);
					next();
				} else {
					LOG.error("Sortie avec erreur de la methode validate de UpdateMeetingAction : ");
					this.cancel();
				}
		}

		LOG.debug("Sortie de la methode validate de UpdateMeetingAction");
		
	}

	@Override
	public void next() {
		main.setAction(ActionEnum.LIST_MEETINGS);
		
	}

	@Override
	public void cancel() {
		main.setAction(ActionEnum.LIST_MEETINGS);
		
	}

}
