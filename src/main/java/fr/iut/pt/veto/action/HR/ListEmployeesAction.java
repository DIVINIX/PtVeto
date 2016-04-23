package fr.iut.pt.veto.action.HR;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import fr.iut.pt.veto.MainDisplay;
import fr.iut.pt.veto.action.Action;
import fr.iut.pt.veto.action.ActionEnum;
import fr.iut.pt.veto.form.HR.ListEmployeesController;
import fr.iut.pt.veto.form.HR.ListLeavesController;
import fr.iut.pt.veto.model.dao.IndividuDao;
import fr.iut.pt.veto.model.entitie.individu.Salarie;
import fr.iut.pt.veto.util.JavaFxUtils;
import javafx.scene.layout.BorderPane;

public class ListEmployeesAction extends Action {

	private static final Logger LOG = Logger.getLogger(ListEmployeesAction.class);
	
	public ListEmployeesAction(MainDisplay main) {
		super(main);
		this.identifier = ActionEnum.LIST_EMPLOYEES;
	}

	@Override
	public void prepare() {
		LOG.debug("Entree dans la methode prepare de ListEmployeesAction");
		
		view = (BorderPane) JavaFxUtils.getView(this);
		
		parameters = new HashMap<>();
		
		List<Salarie> salaries = IndividuDao.findAllSalarie();
		((ListEmployeesController) controller).prepare(salaries);
		
		LOG.debug("Sortie de la methode prepare de ListEmployeesAction");
	}

	@Override
	public void validate() {
		LOG.debug("Entree dans la methode validate de ListEmployeesAction");
		
		if("Details".equals(parameters.get("ACTION"))) 
		{
			parameters.put("next", ActionEnum.DETAIL_EMPLOYEE);
		} 
		else if ("Conge".equals(parameters.get("ACTION"))) 
		{
			Salarie salarie = (Salarie) IndividuDao.findIndividuById((int) parameters.get("idSalarieToConge"));
			if (salarie.getContrat()!= null) {
				parameters.put("next", ActionEnum.LIST_LEAVES);
			} else {
				parameters.put("info","Il faut d'abord ajouter un contrat à ce salarié.");
				LOG.error("Sortie avec erreur de la methode validate de ListEmployeesAction : "+(String) parameters.get("info"));
				this.cancel();
			}			
		}
		else if ("Contrat".equals(parameters.get("ACTION"))) 
		{
			parameters.put("next", ActionEnum.UPDATE_CONTRACT);
		}
		next();
		
		LOG.debug("Sortie de la methode validate de ListEmployeesAction");
	}

	@Override
	public void next() {
		if (parameters.get("next") != null)
			main.setAction((ActionEnum) parameters.get("next"));
		
	}

	@Override
	public void cancel() {
		if (parameters.get("info") != null)
			((ListEmployeesController) controller).getInfo().setText((String) parameters.get("info"));	
		
	}

}
