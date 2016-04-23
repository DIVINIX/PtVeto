package fr.iut.pt.veto.action.treatment;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import fr.iut.pt.veto.MainDisplay;
import fr.iut.pt.veto.action.Action;
import fr.iut.pt.veto.action.ActionEnum;
import fr.iut.pt.veto.form.treatment.AddPrescriptionController;
import fr.iut.pt.veto.model.dao.AnimalDao;
import fr.iut.pt.veto.model.dao.IndividuDao;
import fr.iut.pt.veto.model.dao.InventaireDao;
import fr.iut.pt.veto.model.dao.TraitementDao;
import fr.iut.pt.veto.model.entitie.animal.Animal;
import fr.iut.pt.veto.model.entitie.inventaire.Produit;
import fr.iut.pt.veto.model.entitie.traitement.Traitement;
import fr.iut.pt.veto.util.JavaFxUtils;
import javafx.scene.layout.BorderPane;

public class AddPrescriptionAction extends Action {

	private static final Logger LOG = Logger.getLogger(AddPrescriptionAction.class);

	public AddPrescriptionAction(MainDisplay main) {
		super(main);
		this.identifier = ActionEnum.ADD_PRESCRIPTION;
	}

	@Override
	public void prepare() {
		LOG.debug("Entree dans la methode prepare de AddPrescriptionAction");

		view = (BorderPane) JavaFxUtils.getView(this);

		if (parameters != null && parameters.get("idTraitement") != null) {
			Traitement traitement = TraitementDao.findTraitementById((int) parameters.get("idTraitement"));
			((AddPrescriptionController) controller).getOwner().getSelectionModel()
					.select(traitement.getAnimal().getClient());
			((AddPrescriptionController) controller).getAnimal().getSelectionModel().select(traitement.getAnimal());
			((AddPrescriptionController) controller).getTreatment().getSelectionModel().select(traitement);
		} else {
			parameters = new HashMap<>();

			((AddPrescriptionController) controller).getOwner().getItems().setAll(IndividuDao.findAllClient());
			((AddPrescriptionController) controller).prepare(InventaireDao.findAllProduit());
		}

		LOG.debug("Sortie de la methode prepare de AddPrescriptionAction");
	}

	@Override
	public void validate() {
		LOG.debug("Entree dans la methode validate de AddPrescriptionAction");

		if ("SelectOwner".equals(parameters.get("ACTION"))) {
			LOG.debug("Action : SelectOwner");

			List<Animal> animals = AnimalDao.findAnimalByClient(
					((AddPrescriptionController) controller).getOwner().getSelectionModel().getSelectedItem());
			((AddPrescriptionController) controller).getAnimal().getItems().setAll(animals);
		} else if ("SelectAnimal".equals(parameters.get("ACTION"))) {
			LOG.debug("Action : SelectAnimal");

			List<Traitement> traitements = TraitementDao.findTraitementByAnimal(
					((AddPrescriptionController) controller).getAnimal().getSelectionModel().getSelectedItem());
			((AddPrescriptionController) controller).getTreatment().getItems().setAll(traitements);
		} else if ("SelectTreatment".equals(parameters.get("ACTION"))) {
			LOG.debug("Action : SelectTreatment");

			Traitement traitement = TraitementDao.findTraitementById((int) parameters.get("idTraitement"));
			((AddPrescriptionController) controller).showProducts(traitement.getSoins());
		} else if ("AddProduct".equals(parameters.get("ACTION"))) {
			LOG.debug("Action : addProduct");
			boolean validate = true;
			Produit add = InventaireDao.findProduitById((int) parameters.get("idProduit"));
			Traitement traitement = TraitementDao.findTraitementById((int) parameters.get("idTraitement"));
			for (Produit produit : traitement.getSoins()) {
				if (produit.getId() == add.getId()) {
					validate = false;
					break;
				}
			}
			if (validate) {
				traitement.getSoins().add(add);
				TraitementDao.saveOrUpdate(traitement);
				((AddPrescriptionController) controller).showProducts(traitement.getSoins());
			}
		} else if ("Supprimer".equals(parameters.get("ACTION"))) {
			LOG.debug("Action : Supprimer");
			Produit rem = null;
			Traitement traitement = TraitementDao.findTraitementById((int) parameters.get("idTraitement"));
			for (Produit produit : traitement.getSoins()) {
				if (produit != null && produit.getId() == (int) parameters.get("idProduit")) {
					rem = produit;
					break;
				}
			}
			traitement.getSoins().remove(rem);
			TraitementDao.saveOrUpdate(traitement);
			((AddPrescriptionController) controller).showProducts(traitement.getSoins());
		} else if ("Validate".equals(parameters.get("ACTION"))) {
			LOG.debug("Action : Validate");
			next();
		}

		LOG.debug("Sortie de la methode validate de AddPrescriptionAction");
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
