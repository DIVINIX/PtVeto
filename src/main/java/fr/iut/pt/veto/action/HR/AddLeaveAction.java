package fr.iut.pt.veto.action.HR;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import fr.iut.pt.veto.MainDisplay;
import fr.iut.pt.veto.action.Action;
import fr.iut.pt.veto.action.ActionEnum;
import fr.iut.pt.veto.form.HR.AddLeaveController;
import fr.iut.pt.veto.form.client.AddClientController;
import fr.iut.pt.veto.form.client.AddMeetingController;
import fr.iut.pt.veto.model.dao.IndividuDao;
import fr.iut.pt.veto.model.dao.RhDao;
import fr.iut.pt.veto.model.entitie.individu.Salarie;
import fr.iut.pt.veto.model.entitie.rh.Absence;
import fr.iut.pt.veto.model.entitie.rh.Contrat;
import fr.iut.pt.veto.model.entitie.rh.TypeAbsence;
import fr.iut.pt.veto.util.JavaFxUtils;
import javafx.scene.layout.BorderPane;

public class AddLeaveAction extends Action {

	private static final Logger LOG = Logger.getLogger(AddLeaveAction.class);
	
	public AddLeaveAction(MainDisplay main) {
		super(main);
		this.identifier = ActionEnum.ADD_LEAVE;
	}

	@Override
	public void prepare() {
		LOG.debug("Entree dans la methode prepare de AddLeaveAction");
		
		view = (BorderPane) JavaFxUtils.getView(this);
		parameters = new HashMap<>();
		List<Salarie> salaries = IndividuDao.findAllSalarie();
		((AddLeaveController) controller).getEmployee().getItems().setAll(salaries);
		List<TypeAbsence> types = RhDao.findAllTypeAbsence();
		((AddLeaveController) controller).getType().getItems().setAll(types);

		LOG.debug("Sortie de la methode prepare de AddLeaveAction");
	}

	@Override
	public void validate() {
		LOG.debug("Entree dans la methode validate de AddLeaveAction");
		
		if ("Validate".equals(parameters.get("ACTION"))) {
			LOG.debug("Action : Validate");
			
			Salarie salarie = ((AddLeaveController) controller).getEmployee().getSelectionModel().getSelectedItem();
			TypeAbsence type = ((AddLeaveController) controller).getType().getSelectionModel().getSelectedItem();
			LocalDate dateDebut = ((AddLeaveController) controller).getDateBegin().getValue();
			LocalDate dateFin = ((AddLeaveController) controller).getDateEnd().getValue();
			
			if (salarie != null && type != null && dateDebut != null ) {
				if (salarie.getContrat() != null) {
					Absence absence = new Absence();
					absence.setContrat(salarie.getContrat());
					absence.setType(type);
					absence.setDateDebut(Date.from(dateDebut.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
					if(dateFin != null)
						absence.setDateFin(Date.from(dateFin.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
					
					RhDao.saveOrUpdate(absence);
					parameters.put("info", "Congé ajoute avec succes.");
					LOG.debug("Sortie avec succes de la methode validate de AddClientAction");

					this.next();
				} else {
					LOG.error("Sortie avec erreur de la methode validate de AddLeaveAction : "+(String) parameters.get("info"));
					parameters.put("info","Il faut d'abord ajouter un contrat à ce salarié.");
					this.cancel();
				}				
			}
			
			else {
				LOG.error("Sortie avec erreur de la methode validate de AddLeaveAction : "+(String) parameters.get("info"));
				parameters.put("info","Un ou plusieurs champs ne sont pas renseignés.");
				this.cancel();
			}
		}
				
		LOG.debug("Sortie de la methode validate de AddLeaveAction");
	}

	@Override
	public void next() {
		((AddLeaveController) controller).getInfo().setText((String) parameters.get("info"));
		((AddLeaveController) controller).clear();
		
	}

	@Override
	public void cancel() {
		if (parameters.get("info") != null) {
			((AddLeaveController) controller).getInfo().setText((String) parameters.get("info"));
		} else {
			((AddLeaveController) controller).getInfo().setText("Action Annule");
			
		}
		((AddLeaveController) controller).clear();
	}

}
