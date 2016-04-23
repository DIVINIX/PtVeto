package fr.iut.pt.veto.action.animal;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import fr.iut.pt.veto.MainDisplay;
import fr.iut.pt.veto.action.Action;
import fr.iut.pt.veto.action.ActionEnum;
import fr.iut.pt.veto.form.animal.AddAnimalController;
import fr.iut.pt.veto.model.dao.AnimalDao;
import fr.iut.pt.veto.model.dao.EspeceDao;
import fr.iut.pt.veto.model.dao.IndividuDao;
import fr.iut.pt.veto.model.entitie.animal.Animal;
import fr.iut.pt.veto.model.entitie.animal.Espece;
import fr.iut.pt.veto.model.entitie.animal.Race;
import fr.iut.pt.veto.model.entitie.individu.Client;
import fr.iut.pt.veto.util.JavaFxUtils;
import javafx.scene.layout.BorderPane;

/**
 * @author Gauthier
 *
 */

public class AddAnimalAction extends Action{

	private static final Logger LOG = Logger.getLogger(AddAnimalAction.class);
	
	public AddAnimalAction(MainDisplay main) {
		super(main);
		this.identifier = ActionEnum.ADD_ANIMAL;
	}

	@Override
	public void prepare() {
		LOG.debug("Entree dans la methode prepare de AddAnimalAction");
		
		view = (BorderPane) JavaFxUtils.getView(this);
		parameters = new HashMap<>();
	
		List<Client> clients = IndividuDao.findAllClient();
		List<Espece> especes = EspeceDao.findAllEspece();
		((AddAnimalController) controller).prepare(clients,especes);

		LOG.debug("Sortie de la methode prepare de AddAnimalAction");
	}

	@Override
	public void validate() {
		LOG.debug("Entree dans la methode validate de AddAnimalAction");
		
		if ("Select".equals(parameters.get("ACTION"))) {
			LOG.debug("Action : Select");
			
			List<Race> races = EspeceDao.findRaceByEspece((Espece) parameters.get("nature"));
			((AddAnimalController) controller).getBreed().getItems().setAll(races);
			
		} else if ("Validate".equals(parameters.get("ACTION"))) {
			LOG.debug("Action : Validate");
			
			String name = ((AddAnimalController) controller).getName().getText();
			Client client = ((AddAnimalController) controller).getOwner().getSelectionModel().getSelectedItem();
			Race race = ((AddAnimalController) controller).getBreed().getSelectionModel().getSelectedItem();
			
			if (client != null) {
				Animal animal = new Animal();
				animal.setNom(name);
				animal.setClient(client);
				animal.setRace(race);
				
				AnimalDao.saveOrUpdate(animal);
				next();
			} else {
				((AddAnimalController) controller).getWarning().setText("Un ou plusieurs champs ne sont pas renseignés.");
			}
		}
		
		LOG.debug("Sortie de la methode validate de AddAnimalAction");
	}

	@Override
	public void next() {
		main.setAction(ActionEnum.LIST_ANIMALS);
		
	}

	@Override
	public void cancel() {
		main.setAction(ActionEnum.LIST_ANIMALS);
		
	}

}
