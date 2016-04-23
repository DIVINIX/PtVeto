package fr.iut.pt.veto.action.HR;

import java.time.ZoneId;
import java.util.Date;

import org.apache.log4j.Logger;

import fr.iut.pt.veto.MainDisplay;
import fr.iut.pt.veto.action.Action;
import fr.iut.pt.veto.action.ActionEnum;
import fr.iut.pt.veto.form.HR.AddLeaveController;
import fr.iut.pt.veto.form.HR.DetailEmployeeController;
import fr.iut.pt.veto.form.HR.UpdateContractController;
import fr.iut.pt.veto.form.client.UpdateMeetingController;
import fr.iut.pt.veto.model.dao.ConsultationDao;
import fr.iut.pt.veto.model.dao.IndividuDao;
import fr.iut.pt.veto.model.dao.RhDao;
import fr.iut.pt.veto.model.entitie.Consultation;
import fr.iut.pt.veto.model.entitie.individu.Salarie;
import fr.iut.pt.veto.model.entitie.rh.Contrat;
import fr.iut.pt.veto.util.JavaFxUtils;
import javafx.scene.layout.BorderPane;

public class UpdateContractAction extends Action {
	
	private static final Logger LOG = Logger.getLogger(DetailEmployeeAction.class);

	public UpdateContractAction(MainDisplay main) {
		super(main);
		this.identifier = ActionEnum.UPDATE_CONTRACT;
	}

	@Override
	public void prepare() {
		LOG.debug("Entree dans la methode prepare de UpdateContractAction");
		view = (BorderPane) JavaFxUtils.getView(this);
		
		if (parameters != null && parameters.get("idSalarieToContrat") != null) {
			Salarie salarie = (Salarie) IndividuDao.findIndividuById((int) parameters.get("idSalarieToContrat"));
			
			((UpdateContractController) controller).prepare(salarie);
			parameters.put("ACTION", "Select");
			parameters.put("UpdateContractAction", salarie);
		}
		else
		{
			LOG.error("Sortie avec erreur de la methode prepare de UpdateContractAction : ");
		}

		LOG.debug("Entree dans la methode prepare de UpdateContractAction");
		
	}

	@Override
	public void validate() {
		LOG.debug("Entree dans la methode validate de UpdateContractAction");

		if ("Validate".equals(parameters.get("ACTION"))) {
			LOG.debug("Action : Validate");

				Salarie salarie = (Salarie) parameters.get("UpdateContractAction");
				Contrat contrat = null;
					if (salarie.getContrat() == null) {
						contrat = new Contrat();
					}else
						contrat = salarie.getContrat();
					
					if (((UpdateContractController) controller).getSalary().getText() != null && ((UpdateContractController) controller).getDateBegin().getValue() != null  ) {						
					contrat.setSalaire(((UpdateContractController) controller).getSalary().getText());
					contrat.setDateDebut(Date.from(((UpdateContractController) controller).getDateBegin().getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
					if (((UpdateContractController) controller).getDateEnd().getValue() != null)
						contrat.setDateFin(Date.from(((UpdateContractController) controller).getDateEnd().getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
					salarie.setContrat(contrat);
					
					parameters.put("info", "Congé ajoute avec succes.");
					IndividuDao.saveOrUpdate(salarie);
					next();
					}else {
						LOG.error("Sortie avec erreur de la methode validate de UpdateContractAction : ");
						parameters.put("info","Un ou plusieurs champs ne sont pas renseignés.");
						this.cancel();
					}
		}

		LOG.debug("Sortie de la methode validate de UpdateContractAction");
		
	}

	@Override
	public void next() {
		((UpdateContractController) controller).getInfo().setText((String) parameters.get("info"));
		
	}

	@Override
	public void cancel() {
		if (parameters.get("info") != null) {
			((UpdateContractController) controller).getInfo().setText((String) parameters.get("info"));
		} else {
			((UpdateContractController) controller).getInfo().setText("Action Annule");
			
		}
	}
}
