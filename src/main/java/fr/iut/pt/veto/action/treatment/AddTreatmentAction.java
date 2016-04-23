package fr.iut.pt.veto.action.treatment;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import fr.iut.pt.veto.MainDisplay;
import fr.iut.pt.veto.action.Action;
import fr.iut.pt.veto.action.ActionEnum;
import fr.iut.pt.veto.form.treatment.AddTreatmentController;
import fr.iut.pt.veto.model.dao.AnimalDao;
import fr.iut.pt.veto.model.dao.IndividuDao;
import fr.iut.pt.veto.model.dao.TraitementDao;
import fr.iut.pt.veto.model.entitie.animal.Animal;
import fr.iut.pt.veto.model.entitie.individu.Client;
import fr.iut.pt.veto.model.entitie.traitement.Maladie;
import fr.iut.pt.veto.model.entitie.traitement.Traitement;
import fr.iut.pt.veto.util.JavaFxUtils;
import javafx.scene.layout.BorderPane;

public class AddTreatmentAction extends Action {

	private static final Logger LOG = Logger.getLogger(AddTreatmentAction.class);
	
	public AddTreatmentAction(MainDisplay main) {
		super(main);
		this.identifier = ActionEnum.ADD_TREATMENT;
	}

	@Override
	public void prepare() {
		LOG.debug("Entree dans la methode prepare de AddTreatmentAction");
		
		view = (BorderPane) JavaFxUtils.getView(this);
		
		parameters = new HashMap<>();
		
		List<Client> clients = IndividuDao.findAllClient();
		List<Maladie> maladies = TraitementDao.findAllMaladie();
		((AddTreatmentController) controller).prepare(clients,maladies);
		
		LOG.debug("Sortie de la methode prepare de AddTreatmentAction");
	}

	@Override
	public void validate() {
		LOG.debug("Entree dans la methode validate de AddTreatmentAction");

		if ("SelectOwner".equals(parameters.get("ACTION"))) {
			LOG.debug("Action : SelectOwner");
			
			((AddTreatmentController) controller).getAnimal().getItems().clear();
			List<Animal> animals = AnimalDao.findAnimalByClient((Client) parameters.get("owner"));
			((AddTreatmentController) controller).getAnimal().getItems().setAll(animals);
			
			
		} else if ("Validate".equals(parameters.get("ACTION"))) {
			LOG.debug("Action : Validate");
			
			String name = ((AddTreatmentController) controller).getName().getText();
			Animal animal = ((AddTreatmentController) controller).getAnimal().getSelectionModel().getSelectedItem();
			Maladie maladie = ((AddTreatmentController) controller).getMalady().getSelectionModel().getSelectedItem();
			String desc = ((AddTreatmentController) controller).getDesc().getText();
			LocalDate ldBegin = ((AddTreatmentController) controller).getDateBegin().getValue();
			LocalDate ldEnd = ((AddTreatmentController) controller).getDateEnd().getValue();
			if (animal != null && ldBegin != null)
			{
				Traitement traitement = new Traitement();
				traitement.setNom(name);
				traitement.setAnimal(animal);
				traitement.setMaladie(maladie);
				traitement.setDescription(desc);
				traitement.setDateDebut(Date.from(ldBegin.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
				if (ldEnd != null && ldBegin.isBefore(ldEnd))
				{
					traitement.setDateFin(Date.from(ldEnd.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
				}
				TraitementDao.saveOrUpdate(traitement);
				parameters.put("idAnimal", animal.getId());
				next();
			}
			else
			{
				((AddTreatmentController) controller).getWarning().setText("Un ou plusieurs champs ne sont pas renseignés.");
			}
		}
		
		LOG.debug("Sortie de la methode validate de AddTreatmentAction");
	}

	@Override
	public void next() {
		main.setAction(ActionEnum.LIST_TREATMENTS);
		
	}

	@Override
	public void cancel() {
		main.setAction(ActionEnum.LIST_TREATMENTS);
		
	}

}
