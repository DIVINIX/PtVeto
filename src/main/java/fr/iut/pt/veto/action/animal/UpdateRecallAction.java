package fr.iut.pt.veto.action.animal;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import fr.iut.pt.veto.MainDisplay;
import fr.iut.pt.veto.action.Action;
import fr.iut.pt.veto.action.ActionEnum;
import fr.iut.pt.veto.form.animal.UpdateRecallController;
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

public class UpdateRecallAction extends Action {

	private static final Logger LOG = Logger.getLogger(UpdateRecallAction.class);
	
	public UpdateRecallAction(MainDisplay main) {
		super(main);
		this.identifier = ActionEnum.UPDATE_RECALL;
	}

	@Override
	public void prepare() {
		LOG.debug("Entree dans la methode prepare de UpdateRecallAction");
		
		if (parameters != null && parameters.get("idRappel") != null) {
			view = (BorderPane) JavaFxUtils.getView(this);
			
			Rappel rappel = RappelDao.findRappelById((int) parameters.get("idRappel"));
			((UpdateRecallController) controller).getRaison().setText(rappel.getRaison());
			((UpdateRecallController) controller).getClient().getSelectionModel().select(rappel.getAnimal().getClient());
			((UpdateRecallController) controller).getAnimal().getSelectionModel().select(rappel.getAnimal());
			((UpdateRecallController) controller).getDate().setValue(Instant.ofEpochMilli(rappel.getDate().getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
			
			List<Client> clients = IndividuDao.findAllClient();
			((UpdateRecallController) controller).prepare(clients);
		} else {
			next();
		}
		
		LOG.debug("Sortie de la methode prepare de UpdateRecallAction");
	}

	@Override
	public void validate() {
		LOG.debug("Entree dans la methode validate de UpdateRecallAction");
		
		if ("SelectOwner".equals(parameters.get("ACTION"))) {
			LOG.debug("Action : Select");
			
			List<Animal> animals = AnimalDao.findAnimalByClient((Client) parameters.get("owner"));
			((UpdateRecallController) controller).getAnimal().getItems().setAll(animals);
		} 
		else if ("Validate".equals(parameters.get("ACTION"))) 
		{
			String raison = (((UpdateRecallController) controller).getRaison().getText());
			Animal animal = (((UpdateRecallController) controller).getAnimal().getSelectionModel().getSelectedItem());
			LocalDate ld = ((UpdateRecallController) controller).getDate().getValue();
			
			if (animal != null && ld != null)
			{
				Rappel rappel = RappelDao.findRappelById((int) parameters.get("idRappel"));
				rappel.setRaison(raison);
				rappel.setAnimal(animal);
				rappel.setDate(Date.from(ld.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
				RappelDao.saveOrUpdate(rappel);
				next();
			} 
			else
			{
				((UpdateRecallController) controller).getWarning().setText("Un ou plusieurs champs ne sont pas renseignés.");
			}
		}
		LOG.debug("Sortie de la methode validate de UpdateRecallAction");
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
