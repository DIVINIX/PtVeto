package fr.iut.pt.veto.action.animal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import fr.iut.pt.veto.MainDisplay;
import fr.iut.pt.veto.action.Action;
import fr.iut.pt.veto.action.ActionEnum;
import fr.iut.pt.veto.form.animal.*;
import fr.iut.pt.veto.model.dao.AnimalDao;
import fr.iut.pt.veto.model.dao.IndividuDao;
import fr.iut.pt.veto.model.entitie.animal.Animal;
import fr.iut.pt.veto.model.entitie.individu.Client;
import fr.iut.pt.veto.util.JavaFxUtils;
import javafx.scene.layout.BorderPane;

/**
 * @author Gauthier
 *
 */

public class ListAnimalsAction extends Action {

	private static final Logger LOG = Logger.getLogger(ListAnimalsAction.class);
	
	public ListAnimalsAction(MainDisplay main) {
		super(main);
		this.identifier = ActionEnum.LIST_ANIMALS;

	}

	@Override
	public void prepare() {
		view = (BorderPane) JavaFxUtils.getView(this);
		
		List<Animal> animals = new ArrayList<>();
		List<Client> clients = new ArrayList<>();

		if (parameters != null && parameters.get("idClient") != null) {
			animals.addAll(AnimalDao.findAnimalByClient((Client)parameters.get("idClient")));
			clients.addAll(IndividuDao.findAllClient());
		} else {
			parameters = new HashMap<>();
			animals.addAll(AnimalDao.findAllAnimal());
			clients.addAll(IndividuDao.findAllClient());
		}

		((ListAnimalsController) controller).prepare(animals,clients);

	}

	
	@Override
	public void validate() {
		if("Select".equals(parameters.get("ACTION"))) 
		{
			if (parameters.get("select") instanceof Client)
			{
				parameters.put("idClient", (Client) parameters.get("select"));
				parameters.put("next", ActionEnum.LIST_ANIMALS);
				next();
			}
		} 
		else if ("Rappel".equals(parameters.get("ACTION"))) 
		{
			parameters.put("next", ActionEnum.LIST_RECALLS);
			next();
		} 
		else if ("Traitement".equals(parameters.get("ACTION"))) 
		{
			parameters.put("next", ActionEnum.LIST_TREATMENTS);
			next();
		}
		else if ("Modifier".equals(parameters.get("ACTION"))) 
		{
			LOG.debug("Action : Modifier");
			//
			parameters.put("next", ActionEnum.UPDATE_ANIMAL);
			next();
		} 
		else if ("Supprimer".equals(parameters.get("ACTION"))) 
		{
			LOG.debug("Action : Supprimer");
			//
			delete((int) parameters.get("idAnimal"));
			parameters.put("next", ActionEnum.LIST_ANIMALS);
			next();
		} 
	}

	private void delete(int id) {
		Animal animal = AnimalDao.findAnimalById(id);
		AnimalDao.delete(animal);
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
