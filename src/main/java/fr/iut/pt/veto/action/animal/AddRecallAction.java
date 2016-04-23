package fr.iut.pt.veto.action.animal;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import fr.iut.pt.veto.MainDisplay;
import fr.iut.pt.veto.action.Action;
import fr.iut.pt.veto.action.ActionEnum;
import fr.iut.pt.veto.form.animal.AddRecallController;
import fr.iut.pt.veto.model.dao.AnimalDao;
import fr.iut.pt.veto.model.dao.IndividuDao;
import fr.iut.pt.veto.model.dao.RappelDao;
import fr.iut.pt.veto.model.entitie.animal.Animal;
import fr.iut.pt.veto.model.entitie.individu.Client;
import fr.iut.pt.veto.model.entitie.traitement.Rappel;
import fr.iut.pt.veto.util.JavaFxUtils;
import javafx.scene.layout.BorderPane;

/**
 * @author Gauthier
 *
 */

public class AddRecallAction extends Action {

	private static final Logger LOG = Logger.getLogger(AddRecallAction.class);
	
	public AddRecallAction(MainDisplay main) {
		super(main);
		this.identifier = ActionEnum.ADD_RECALL;
	}

	@Override
	public void prepare() {
		LOG.debug("Entree dans la methode prepare de AddRecallAction");
		
		view = (BorderPane) JavaFxUtils.getView(this);
		
		parameters = new HashMap<>();
		
		List<Client> clients = IndividuDao.findAllClient();
		((AddRecallController) controller).prepare(clients);
		
		LOG.debug("Sortie de la methode prepare de AddRecallAction");
	}

	@Override
	public void validate() {
		LOG.debug("Entree dans la methode validate de AddRecallAction");
		
		if ("SelectOwner".equals(parameters.get("ACTION"))) {
			LOG.debug("Action : Select");
			
			if (parameters.get("owner") instanceof Client)
			{
				List<Animal> animals = AnimalDao.findAnimalByClient((Client) parameters.get("owner"));
				((AddRecallController) controller).getAnimal().getItems().setAll(animals);
			}

		} 
		else if ("Validate".equals(parameters.get("ACTION"))) 
		{
			LOG.debug("Action : Validate");
			String raison = (((AddRecallController) controller).getRaison().getText());
			Animal animal = (((AddRecallController) controller).getAnimal().getSelectionModel().getSelectedItem());
			LocalDate ld = ((AddRecallController) controller).getDate().getValue();
	
			if (animal != null && ld != null)
			{
				Rappel rappel = new Rappel();
				rappel.setRaison(raison);
				rappel.setAnimal(animal);
				rappel.setDate(Date.from(ld.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
				
				RappelDao.saveOrUpdate(rappel);
				next();
			} else {
				((AddRecallController) controller).getWarning().setText("Un ou plusieurs champs ne sont pas renseignés.");
			}
		}
		
		LOG.debug("Sortie de la methode validate de AddRecallAction");
	}

	@Override
	public void next() {
		main.setAction(ActionEnum.LIST_RECALLS);
	}

	@Override
	public void cancel() {
		main.setAction(ActionEnum.LIST_RECALLS);
	}

}
