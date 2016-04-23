package fr.iut.pt.veto.action.client;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import fr.iut.pt.veto.MainDisplay;
import fr.iut.pt.veto.action.Action;
import fr.iut.pt.veto.action.ActionEnum;
import fr.iut.pt.veto.action.stock.UpdateProductAction;
import fr.iut.pt.veto.form.client.AddClientController;
import fr.iut.pt.veto.form.client.AddMeetingController;
import fr.iut.pt.veto.form.stock.AddProductController;
import fr.iut.pt.veto.form.stock.UpdateProductController;
import fr.iut.pt.veto.form.treatment.AddTreatmentController;
import fr.iut.pt.veto.model.dao.AnimalDao;
import fr.iut.pt.veto.model.dao.ConsultationDao;
import fr.iut.pt.veto.model.dao.IndividuDao;
import fr.iut.pt.veto.model.dao.InventaireDao;
import fr.iut.pt.veto.model.dao.TraitementDao;
import fr.iut.pt.veto.model.entitie.Consultation;
import fr.iut.pt.veto.model.entitie.animal.Animal;
import fr.iut.pt.veto.model.entitie.individu.Client;
import fr.iut.pt.veto.model.entitie.individu.Individu;
import fr.iut.pt.veto.model.entitie.inventaire.Produit;
import fr.iut.pt.veto.model.entitie.traitement.Maladie;
import fr.iut.pt.veto.model.entitie.traitement.Traitement;
import fr.iut.pt.veto.util.JavaFxUtils;
import javafx.scene.layout.BorderPane;

/**
 * 
 * @author Yann
 *
 */

public class AddMeetingAction extends Action {
	
	private static final Logger LOG = Logger.getLogger(AddMeetingAction.class);

	public AddMeetingAction(MainDisplay main) {
		super(main);
		this.identifier = ActionEnum.ADD_MEETING;
	}

	@Override
	public void prepare() {
		LOG.debug("Entree dans la methode prepare de AddMeetingAction");
		view = (BorderPane) JavaFxUtils.getView(this);
		parameters = new HashMap<>();
		List<Client> clients = IndividuDao.findAllClient();
		((AddMeetingController) controller).prepare(clients);

		LOG.debug("Sortie de la methode prepare de AddMeetingAction");
	}

	@Override
	public void validate() {
		LOG.debug("Entree dans la methode validate de AddMeetingAction");
		
		if ("Select".equals(parameters.get("ACTION"))) {
			LOG.debug("Action : Select");

			List<Animal> animaux = AnimalDao.findAnimalByClient((Client) parameters.get("select"));

			((AddMeetingController) controller).fill(animaux);
			
		} else if ("Validate".equals(parameters.get("ACTION"))) {
			LOG.debug("Action : Validate");
			
			Animal animal = ((AddMeetingController) controller).getAnimal().getSelectionModel().getSelectedItem();
			String desc = ((AddMeetingController) controller).getDesc().getText();
			LocalDate dateMeeting = ((AddMeetingController) controller).getDateMeeting().getValue();
			if (animal != null && dateMeeting != null)
			{
				boolean validate = true;
				Consultation consultation = new Consultation();

				try {
					consultation.setAnimal(animal);
				} catch (NumberFormatException ex) {
					validate = false;
					parameters.put("info", "L'animal n'est pas correct.");
				}
				try {
					consultation.setDateDebut(Date.from(dateMeeting.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
				} catch (NumberFormatException ex) {
					validate = false;
					parameters.put("info", "La date n'est pas correcte.");
				}
				try {
					consultation.setDescription(desc);
				} catch (NumberFormatException ex) {
					validate = false;
					parameters.put("info", "La description n'est pas correcte.");
				}

				if (validate) {
					ConsultationDao.saveOrUpdate(consultation);
					parameters.put("info", "Consultation ajoute avec succes.");
					LOG.debug("Sortie avec succes de la methode validate de AddMeetingAction");
					this.next();
				} else {
					LOG.error("Sortie avec erreur de la methode validate de AddMeetingAction : "+(String) parameters.get("info"));
					this.cancel();
				}
			}
			else
			{
				parameters.put("info", "Un ou plusieurs champs ne sont pas renseignés.");
				this.cancel();
			}
		}		
	}

	@Override
	public void next() {
		((AddMeetingController) controller).getInfo().setText((String) parameters.get("info"));
		((AddMeetingController) controller).clear();
		
	}

	@Override
	public void cancel() {
		if (parameters.get("info") != null) {
			((AddMeetingController) controller).getInfo().setText((String) parameters.get("info"));
		} else {
			((AddMeetingController) controller).getInfo().setText("Action Annule");
		}
		((AddMeetingController) controller).clear();
	}

}
