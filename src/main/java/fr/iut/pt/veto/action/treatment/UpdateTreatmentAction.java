package fr.iut.pt.veto.action.treatment;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import fr.iut.pt.veto.MainDisplay;
import fr.iut.pt.veto.action.Action;
import fr.iut.pt.veto.action.ActionEnum;
import fr.iut.pt.veto.form.treatment.AddTreatmentController;
import fr.iut.pt.veto.form.treatment.UpdateTreatmentController;
import fr.iut.pt.veto.model.dao.AnimalDao;
import fr.iut.pt.veto.model.dao.IndividuDao;
import fr.iut.pt.veto.model.dao.TraitementDao;
import fr.iut.pt.veto.model.entitie.animal.Animal;
import fr.iut.pt.veto.model.entitie.individu.Client;
import fr.iut.pt.veto.model.entitie.traitement.Maladie;
import fr.iut.pt.veto.model.entitie.traitement.Traitement;
import fr.iut.pt.veto.util.JavaFxUtils;
import javafx.scene.layout.BorderPane;

public class UpdateTreatmentAction extends Action {

	private static final Logger LOG = Logger.getLogger(UpdateTreatmentAction.class);
	
	public UpdateTreatmentAction(MainDisplay main) {
		super(main);
		this.identifier = ActionEnum.UPDATE_TREATMENT;
	}

	@Override
	public void prepare() {
		LOG.debug("Entree dans la methode prepare de UpdateTreatmentAction");
		
		view = (BorderPane) JavaFxUtils.getView(this);
		
		if (parameters != null && parameters.get("idTraitement") != null) {
		
			Traitement traitement = TraitementDao.findTraitementById((int) parameters.get("idTraitement"));
			
			((UpdateTreatmentController) controller).getName().setText(traitement.getNom());
			((UpdateTreatmentController) controller).getOwner().getSelectionModel().select(traitement.getAnimal().getClient());
			((UpdateTreatmentController) controller).getAnimal().getSelectionModel().select(traitement.getAnimal());
			((UpdateTreatmentController) controller).getMalady().getSelectionModel().select(traitement.getMaladie());
			((UpdateTreatmentController) controller).getDesc().setText(traitement.getDescription());
			
			((UpdateTreatmentController) controller).getDateBegin().setValue(Instant.ofEpochMilli(traitement.getDateDebut().getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
			((UpdateTreatmentController) controller).getDateEnd().setValue(Instant.ofEpochMilli(traitement.getDateFin().getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
			
			List<Client> clients = IndividuDao.findAllClient();
			List<Maladie> maladies = TraitementDao.findAllMaladie();
			((UpdateTreatmentController) controller).prepare(clients,maladies);
		} else {
			next();
		}
		
		
		LOG.debug("Sortie de la methode prepare de UpdateTreatmentAction");
	}

	@Override
	public void validate() {
		LOG.debug("Entree dans la methode validate de UpdateTreatmentAction");

		if ("SelectOwner".equals(parameters.get("ACTION"))) {
			LOG.debug("Action : SelectOwner");
			
			((UpdateTreatmentController) controller).getAnimal().getItems().clear();
			List<Animal> animals = AnimalDao.findAnimalByClient((Client) parameters.get("owner"));
			((UpdateTreatmentController) controller).getAnimal().getItems().setAll(animals);
			
			
		} else if ("Validate".equals(parameters.get("ACTION"))) {
			LOG.debug("Action : Validate");
			
			String name = ((UpdateTreatmentController) controller).getName().getText();
			Animal animal = ((UpdateTreatmentController) controller).getAnimal().getSelectionModel().getSelectedItem();
			Maladie maladie = ((UpdateTreatmentController) controller).getMalady().getSelectionModel().getSelectedItem();
			String desc = ((UpdateTreatmentController) controller).getDesc().getText();
			LocalDate ldBegin = ((UpdateTreatmentController) controller).getDateBegin().getValue();
			LocalDate ldEnd = ((UpdateTreatmentController) controller).getDateEnd().getValue();
			if (animal != null && ldBegin != null)
			{
				Traitement traitement = TraitementDao.findTraitementById((int) parameters.get("idTraitement"));
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
				next();
			}
			else
			{
				((AddTreatmentController) controller).getWarning().setText("Un ou plusieurs champs ne sont pas renseignés.");
			}
		}
		
		LOG.debug("Sortie de la methode validate de UpdateTreatmentAction");
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
