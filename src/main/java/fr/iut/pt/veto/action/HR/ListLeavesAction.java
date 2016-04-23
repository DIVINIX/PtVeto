package fr.iut.pt.veto.action.HR;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import fr.iut.pt.veto.MainDisplay;
import fr.iut.pt.veto.action.Action;
import fr.iut.pt.veto.action.ActionEnum;
import fr.iut.pt.veto.form.HR.AddLeaveController;
import fr.iut.pt.veto.form.HR.ListLeavesController;
import fr.iut.pt.veto.form.client.ListMeetingsController;
import fr.iut.pt.veto.model.dao.ConsultationDao;
import fr.iut.pt.veto.model.dao.IndividuDao;
import fr.iut.pt.veto.model.dao.RhDao;
import fr.iut.pt.veto.model.entitie.Consultation;
import fr.iut.pt.veto.model.entitie.individu.Client;
import fr.iut.pt.veto.model.entitie.individu.Salarie;
import fr.iut.pt.veto.model.entitie.rh.Absence;
import fr.iut.pt.veto.model.entitie.rh.Contrat;
import fr.iut.pt.veto.util.JavaFxUtils;
import javafx.scene.layout.BorderPane;

public class ListLeavesAction extends Action {

	private static final Logger LOG = Logger.getLogger(ListLeavesAction.class);
	
	public ListLeavesAction(MainDisplay main) {
		super(main);
		this.identifier = ActionEnum.LIST_LEAVES;
	}

	@Override
	public void prepare() {
		LOG.debug("Entree dans la methode prepare de ListLeavesAction");
		
		view = (BorderPane) JavaFxUtils.getView(this);
		
		List<Absence> absences = new ArrayList<>();
		
		if (parameters != null && parameters.get("idSalarieToConge") != null) {
			Salarie salarie = (Salarie) IndividuDao.findIndividuById((int) parameters.get("idSalarieToConge"));
			absences.addAll(RhDao.findAbsenceByContrat(salarie.getContrat()));
			((ListLeavesController) controller).getEmployee().getSelectionModel().select(salarie);
			parameters.remove("idSalarieToConge");	
		} else if (parameters != null && parameters.get("selectSalarie") != null) {
			Salarie salarie = (Salarie) parameters.get("selectSalarie");
			if (salarie.getContrat() != null) {
			absences.addAll(RhDao.findAbsenceByContrat(salarie.getContrat()));
			} else {
				parameters.put("info","Il faut d'abord ajouter un contrat à ce salarié.");
				LOG.error("Sortie avec erreur de la methode validate de ListLeavesAction : "+(String) parameters.get("info"));
				this.cancel();
			}
			((ListLeavesController) controller).getEmployee().getSelectionModel().select(salarie);
			parameters.clear();
		} else {
			parameters = new HashMap<>();
			absences.addAll(RhDao.findAllAbsence());
		}
		
		List<Salarie> salaries = IndividuDao.findAllSalarie();
		((ListLeavesController) controller).prepare(absences,salaries);
		LOG.debug("Sortie de la methode prepare de ListLeavesAction");
	}

	@Override
	public void validate() {
		LOG.debug("Entree dans la methode validate de ListLeavesAction");
		if ("Select".equals(parameters.get("ACTION"))) {
			LOG.debug("Action : Select");
			
			parameters.put("selectSalarie",(Salarie) parameters.get("select"));
			parameters.put("next", ActionEnum.LIST_LEAVES);
			next();

		}else if ("Supprimer".equals(parameters.get("ACTION"))) {
			LOG.debug("Action : Supprimer");
			delete((int) parameters.get("id"));
			parameters.put("next", ActionEnum.LIST_LEAVES);
		}
		next();
		LOG.debug("Sortie de la methode validate de ListLeavesAction");
	}

	@Override
	public void next() {
		main.setAction((ActionEnum) parameters.get("next"));
	}

	@Override
	public void cancel() {
		if (parameters.get("info") != null)
			((ListLeavesController) controller).getInfo().setText((String) parameters.get("info"));		
	}
	
	public void delete(int id) {
		Absence absence = RhDao.findAbsenceById(id);
		RhDao.delete(absence);
	}

}
