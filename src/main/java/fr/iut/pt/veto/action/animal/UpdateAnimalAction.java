package fr.iut.pt.veto.action.animal;

import java.util.List;

import org.apache.log4j.Logger;

import fr.iut.pt.veto.MainDisplay;
import fr.iut.pt.veto.action.Action;
import fr.iut.pt.veto.action.ActionEnum;
import fr.iut.pt.veto.form.animal.UpdateAnimalController;
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

public class UpdateAnimalAction extends Action {

	private static final Logger LOG = Logger.getLogger(UpdateAnimalAction.class);
	
	public UpdateAnimalAction(MainDisplay main) {
		super(main);
		this.identifier = ActionEnum.UPDATE_ANIMAL;
	}

	@Override
	public void prepare() {
		LOG.debug("Entree dans la methode prepare de UpdateAnimalAction");
		
		if (parameters != null && parameters.get("idAnimal") != null) {
			view = (BorderPane) JavaFxUtils.getView(this);
			
			Animal animal = AnimalDao.findAnimalById((int) parameters.get("idAnimal"));
			
			((UpdateAnimalController) controller).getName().setText(animal.getNom());
			((UpdateAnimalController) controller).getOwner().getSelectionModel().select(animal.getClient());
			((UpdateAnimalController) controller).getNature().getSelectionModel().select(animal.getRace().getEspece());
			((UpdateAnimalController) controller).getBreed().getSelectionModel().select(animal.getRace());
			
			List<Client> clients = IndividuDao.findAllClient();
			List<Espece> especes = EspeceDao.findAllEspece();

			((UpdateAnimalController) controller).prepare(clients,especes);
		} else {
			next();
		}
		
		LOG.debug("Sortie de la methode prepare de UpdateAnimalAction");
	}

	@Override
	public void validate() {
		LOG.debug("Entree dans la methode validate de UpdateAnimalAction");
		
		if ("Select".equals(parameters.get("ACTION"))) {
			LOG.debug("Action : Select");
		
			List<Race> races = EspeceDao.findRaceByEspece((Espece) parameters.get("nature"));
			((UpdateAnimalController) controller).getBreed().getItems().setAll(races);
			
		} else if ("Validate".equals(parameters.get("ACTION"))) {
			LOG.debug("Action : Validate");
			
			String name = ((UpdateAnimalController) controller).getName().getText();
			Client client = ((UpdateAnimalController) controller).getOwner().getSelectionModel().getSelectedItem();
			Race race = ((UpdateAnimalController) controller).getBreed().getSelectionModel().getSelectedItem();
			
			if (client != null)
			{
				Animal animal = AnimalDao.findAnimalById((int) parameters.get("idAnimal"));
				animal.setNom(name);
				animal.setClient(client);
				animal.setRace(race);
				
				AnimalDao.saveOrUpdate(animal);
				next();
			} else {
				((UpdateAnimalController) controller).getWarning().setText("Un ou plusieurs champs ne sont pas renseignés.");
			}
		}
		LOG.debug("Sortie de la methode validate de UpdateAnimalAction");
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
