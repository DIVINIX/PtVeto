package fr.iut.pt.veto.action.HR;

import org.apache.log4j.Logger;

import fr.iut.pt.veto.MainDisplay;
import fr.iut.pt.veto.action.Action;
import fr.iut.pt.veto.action.ActionEnum;
import fr.iut.pt.veto.form.HR.DetailEmployeeController;
import fr.iut.pt.veto.model.dao.IndividuDao;
import fr.iut.pt.veto.model.entitie.individu.Salarie;
import fr.iut.pt.veto.util.JavaFxUtils;
import javafx.scene.layout.BorderPane;

public class DetailEmployeeAction extends Action {

	private static final Logger LOG = Logger.getLogger(DetailEmployeeAction.class);
	
	public DetailEmployeeAction(MainDisplay main) {
		super(main);
		this.identifier = ActionEnum.DETAIL_EMPLOYEE;
	}

	@Override
	public void prepare() {
		LOG.debug("Entree dans la methode prepare de DetailEmployeeAction");
		view = (BorderPane) JavaFxUtils.getView(this);
		
		if (parameters != null && parameters.get("idSalarie") != null) {
			Salarie salarie = (Salarie) IndividuDao.findIndividuById((int) parameters.get("idSalarie"));
			
			((DetailEmployeeController) controller).getFirstname().setText(salarie.getPrenom());
			((DetailEmployeeController) controller).getLastname().setText(salarie.getNom());
			((DetailEmployeeController) controller).getPhone().setText(salarie.getTelephone());
			((DetailEmployeeController) controller).getEmail().setText(salarie.getMail());
			((DetailEmployeeController) controller).getAdress().setText(salarie.getAdresse());
			if (salarie.getVille() != null)
				((DetailEmployeeController) controller).getCity().setText(salarie.getVille().getNom());
			if (salarie.getRole() != null)
				((DetailEmployeeController) controller).getRole().setText(salarie.getRole().toString());
			((DetailEmployeeController) controller).getLogin().setText(salarie.getIdentifiant());
		}
		else
		{
			next();
		}

		LOG.debug("Entree dans la methode prepare de DetailEmployeeAction");
	}

	@Override
	public void validate() {
		LOG.debug("Entree dans la methode validate de DetailEmployeeAction");
		
		this.cancel();
		
		LOG.debug("Entree dans la methode validate de DetailEmployeeAction");
	}

	@Override
	public void next() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cancel() {
		main.setAction(ActionEnum.LIST_EMPLOYEES);
		
	}

}
