package fr.iut.pt.veto.action.animal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import fr.iut.pt.veto.MainDisplay;
import fr.iut.pt.veto.action.Action;
import fr.iut.pt.veto.action.ActionEnum;
import fr.iut.pt.veto.form.animal.ListRecallsController;
import fr.iut.pt.veto.model.dao.AnimalDao;
import fr.iut.pt.veto.model.dao.RappelDao;
import fr.iut.pt.veto.model.entitie.traitement.Rappel;
import fr.iut.pt.veto.util.JavaFxUtils;
import javafx.scene.layout.BorderPane;

/**
 * @author Gauthier
 *
 */

public class ListRecallsAction extends Action {

	private static final Logger LOG = Logger.getLogger(ListRecallsAction.class);
	
	public ListRecallsAction(MainDisplay main) {
		super(main);
		this.identifier = ActionEnum.LIST_RECALLS;
	}

	@Override
	public void prepare() {
		LOG.debug("Entree dans la methode prepare de ListRecallsAction");
		
		view = (BorderPane) JavaFxUtils.getView(this);
		
		List<Rappel> rappels = new ArrayList<>();
		
		if (parameters != null && parameters.get("idAnimal") != null) {
			rappels.addAll(RappelDao.findRappelByAnimal(AnimalDao.findAnimalById((int)parameters.get("idAnimal"))));
		} else {
			parameters = new HashMap<>();
			rappels.addAll(RappelDao.findAllRappel());
		}
		
		((ListRecallsController) controller).prepare(rappels);
		
		LOG.debug("Sortie de la methode prepare de ListRecallsAction");
	}

	@Override
	public void validate() {
		LOG.debug("Entree dans la methode validate de ListRecallsAction");
		
		if ("Modifier".equals(parameters.get("ACTION"))) 
		{
			LOG.debug("Action : Modifier");
			//
			parameters.put("next", ActionEnum.UPDATE_RECALL);
		} 
		else if ("Supprimer".equals(parameters.get("ACTION"))) 
		{
			LOG.debug("Action : Supprimer");
			//
			delete((int) parameters.get("idRappel"));
			parameters.put("next", ActionEnum.LIST_RECALLS);
		}
		next();
		
		LOG.debug("Sortie de la methode validate de ListRecallsAction");
	}

	private void delete(int id) {
		Rappel rappel = RappelDao.findRappelById(id);
		RappelDao.delete(rappel);
	}

	@Override
	public void next() {
		main.setAction((ActionEnum) parameters.get("next"));
		
	}

	@Override
	public void cancel() {
		// TODO Auto-generated method stub
		
	}

}
