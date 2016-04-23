package fr.iut.pt.veto.action.client;

import org.apache.log4j.Logger;

import fr.iut.pt.veto.MainDisplay;
import fr.iut.pt.veto.action.Action;
import fr.iut.pt.veto.action.ActionEnum;
import fr.iut.pt.veto.form.client.DetailMeetingController;
import fr.iut.pt.veto.model.dao.ConsultationDao;
import fr.iut.pt.veto.model.dao.IndividuDao;
import fr.iut.pt.veto.util.JavaFxUtils;
import javafx.scene.layout.BorderPane;

public class DetailMeetingAction extends Action {
	
	private static final Logger LOG = Logger.getLogger(DetailMeetingAction.class);

	public DetailMeetingAction(MainDisplay main) {
		super(main);
		this.identifier = ActionEnum.DETAIL_MEETING;
	}

	@Override
	public void prepare() {
		LOG.debug("Entree dans la methode prepare de DetailMeetingAction");
		view = (BorderPane) JavaFxUtils.getView(this);

		if (parameters != null && parameters.get("id") != null) {
			((DetailMeetingController) controller).prepare(ConsultationDao.findConsultationById((int) parameters.get("id")));
			parameters.put("ACTION", "Select");
			parameters.put("select", ConsultationDao.findConsultationById((int) parameters.get("id")));
			
		} else {
			LOG.error("Sortie avec erreur de la methode prepare de DetailMeetingAction : ");
		}

		LOG.debug("Sortie de la methode prepare de DetailMeetingAction");
		
	}

	@Override
	public void validate() {
		LOG.debug("Entree dans la methode validate de DetailMeetingAction");
		if ("Modifier".equals(parameters.get("ACTION"))) {
			LOG.debug("Action : Modifier");
			parameters.put("next", ActionEnum.UPDATE_MEETING);
		}else if ("Retour".equals(parameters.get("ACTION"))) {
			LOG.debug("Action : Retour");
			parameters.put("next", ActionEnum.LIST_MEETINGS);
		}
		
		LOG.debug("Sortie de la methode validate de DetailMeetingAction");
		next();
		
	}

	@Override
	public void next() {
		main.setAction((ActionEnum) parameters.get("next"));
		
	}

	@Override
	public void cancel() {
		main.setAction(ActionEnum.LIST_MEETINGS);
		
	}

}
