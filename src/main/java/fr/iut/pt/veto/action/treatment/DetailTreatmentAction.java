package fr.iut.pt.veto.action.treatment;

import org.apache.log4j.Logger;

import fr.iut.pt.veto.MainDisplay;
import fr.iut.pt.veto.action.Action;
import fr.iut.pt.veto.action.ActionEnum;
import fr.iut.pt.veto.form.treatment.DetailTreatmentController;
import fr.iut.pt.veto.model.dao.TraitementDao;
import fr.iut.pt.veto.model.entitie.traitement.Traitement;
import fr.iut.pt.veto.util.JavaFxUtils;
import javafx.scene.layout.BorderPane;

public class DetailTreatmentAction extends Action {

	private static final Logger LOG = Logger.getLogger(DetailTreatmentAction.class);
	
	public DetailTreatmentAction(MainDisplay main) {
		super(main);
		this.identifier = ActionEnum.DETAIL_TREATMENT;
	}

	@Override
	public void prepare() {
		LOG.debug("Entree dans la methode prepare de DetailTreatmentAction");
		
		view = (BorderPane) JavaFxUtils.getView(this);
		
		if (parameters != null && parameters.get("idTraitement") != null) {
		
			Traitement traitement = TraitementDao.findTraitementById((int) parameters.get("idTraitement"));
			
			((DetailTreatmentController) controller).getName().setText(traitement.getNom());
			((DetailTreatmentController) controller).getOwner().setText(traitement.getAnimal().getClient().toString());
			((DetailTreatmentController) controller).getAnimal().setText(traitement.getAnimal().toString());
			((DetailTreatmentController) controller).getMalady().setText(traitement.getMaladie().toString());
			((DetailTreatmentController) controller).getDesc().setText(traitement.getDescription());
			
			((DetailTreatmentController) controller).getDateBegin().setText(traitement.getDateDebut().toString());
			((DetailTreatmentController) controller).getDateEnd().setText(traitement.getDateFin().toString());
			
		} else {
			next();
		}
		
		
		LOG.debug("Sortie de la methode prepare de DetailTreatmentAction");
	}

	@Override
	public void validate() {
		LOG.debug("Entree dans la methode validate de DetailTreatmentAction");
		
		next();
		
		LOG.debug("Sortie de la methode validate de DetailTreatmentAction");
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
