
package fr.iut.pt.veto.action.treatment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import fr.iut.pt.veto.MainDisplay;
import fr.iut.pt.veto.action.Action;
import fr.iut.pt.veto.action.ActionEnum;
import fr.iut.pt.veto.form.treatment.ListTreatmentsController;
import fr.iut.pt.veto.model.dao.AnimalDao;
import fr.iut.pt.veto.model.dao.IndividuDao;
import fr.iut.pt.veto.model.dao.TraitementDao;
import fr.iut.pt.veto.model.entitie.animal.Animal;
import fr.iut.pt.veto.model.entitie.individu.Client;
import fr.iut.pt.veto.model.entitie.traitement.Traitement;
import fr.iut.pt.veto.util.JavaFxUtils;
import javafx.scene.layout.BorderPane;

public class ListTreatmentsAction extends Action {

	private static final Logger LOG = Logger.getLogger(ListTreatmentsAction.class);
	
	public ListTreatmentsAction(MainDisplay main) {
		super(main);
		this.identifier = ActionEnum.LIST_TREATMENTS;
	}

	@Override
	public void prepare() {
		LOG.debug("Entree dans la methode validate de ListTreatmentsAction");
		
		view = (BorderPane) JavaFxUtils.getView(this);
		
		if (parameters != null && parameters.get("idAnimal") != null) {
			Animal animal = AnimalDao.findAnimalById((int) parameters.get("idAnimal"));
			
			((ListTreatmentsController) controller).getOwner().getSelectionModel().select(animal.getClient());
			((ListTreatmentsController) controller).getAnimal().getSelectionModel().select(animal);
			//
			((ListTreatmentsController) controller).getOwner().getItems().setAll(IndividuDao.findAllClient());
			((ListTreatmentsController) controller).getAnimal().getItems().setAll(AnimalDao.findAnimalByClient(animal.getClient()));
			((ListTreatmentsController) controller).prepare(TraitementDao.findTraitementByAnimal(animal));
		} 
		else 
		{
			parameters = new HashMap<>();
			List<Client> clients = IndividuDao.findAllClient();
			((ListTreatmentsController) controller).getOwner().getItems().setAll(clients);
			
			List<Traitement> traitements = new ArrayList<>();
			((ListTreatmentsController) controller).prepare(traitements);
		}
		
		LOG.debug("Entree dans la methode validate de ListTreatmentsAction");
	}

	@Override
	public void validate() {
		LOG.debug("Entree dans la methode validate de ListTreatmentsAction");

		if ("SelectOwner".equals(parameters.get("ACTION"))) {
			LOG.debug("Action : SelectOwner");
			
			List<Animal> animals = AnimalDao.findAnimalByClient((Client) parameters.get("owner"));
			((ListTreatmentsController) controller).getAnimal().getItems().setAll(animals);
			
		} else if ("SelectAnimal".equals(parameters.get("ACTION"))) {
			LOG.debug("Action : SelectAnimal");
			
			List<Traitement> traitements = TraitementDao.findTraitementByAnimal((Animal) parameters.get("animal"));
			((ListTreatmentsController) controller).actionAnimal(traitements);
		} else if ("Modifier".equals(parameters.get("ACTION"))) {
			LOG.debug("Action : Modifier");
			//
			parameters.put("next", ActionEnum.UPDATE_TREATMENT);
			next();
		} else if ("Details".equals(parameters.get("ACTION"))) {
			LOG.debug("Action : Détails");
			//
			parameters.put("next", ActionEnum.DETAIL_TREATMENT);
			next();
		}
		
		LOG.debug("Sortie de la methode validate de ListTreatmentsAction");
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
