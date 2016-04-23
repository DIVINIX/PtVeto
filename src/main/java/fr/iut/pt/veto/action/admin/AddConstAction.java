/**
 * 
 */
package fr.iut.pt.veto.action.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import fr.iut.pt.veto.MainDisplay;
import fr.iut.pt.veto.action.Action;
import fr.iut.pt.veto.action.ActionEnum;
import fr.iut.pt.veto.form.admin.AddConstController;
import fr.iut.pt.veto.model.dao.EspeceDao;
import fr.iut.pt.veto.model.dao.GeoDao;
import fr.iut.pt.veto.model.dao.TraitementDao;
import fr.iut.pt.veto.model.entitie.animal.Espece;
import fr.iut.pt.veto.model.entitie.animal.Race;
import fr.iut.pt.veto.model.entitie.geo.Pays;
import fr.iut.pt.veto.model.entitie.geo.Ville;
import fr.iut.pt.veto.model.entitie.traitement.Maladie;
import fr.iut.pt.veto.util.JavaFxUtils;
import javafx.scene.layout.BorderPane;

/**
 * @author sebastien
 *
 */
public class AddConstAction extends Action {

	private static final Logger LOG = Logger.getLogger(AddConstAction.class);

	public AddConstAction(MainDisplay main) {
		super(main);
		this.identifier = ActionEnum.ADD_CONST;
	}

	@Override
	public void prepare() {
		LOG.debug("Entree dans la methode prepare de AddConstAction");
		view = (BorderPane) JavaFxUtils.getView(this);
		parameters = new HashMap<>();

		List<String> type = new ArrayList<String>();
		type.add("Pays");
		type.add("Ville");
		type.add("Maladie");
		type.add("TypeAbsence");
		type.add("Role");
		type.add("Espece");
		type.add("Race");
		((AddConstController) controller).prepare(type);
		
		((AddConstController) controller).getOption().setVisible(false);
		((AddConstController) controller).getForeign().setVisible(false);
		
		LOG.debug("Sortie de la methode prepare de AddConstAction");
	}

	@Override
	public void validate() {
		LOG.debug("Entree dans la methode validate de AddConstAction");

		if ("Select".equals(parameters.get("ACTION"))) {
			LOG.debug("Action : Select");
			switch ((String) parameters.get("type")) {
			case "Ville":
				List<Pays> pays = GeoDao.findAllPays();
				((AddConstController) controller).getForeign().setVisible(true);
				((AddConstController) controller).fill(pays);
				((AddConstController) controller).getOption().setVisible(true);
				((AddConstController) controller).getOption().setPromptText("Code Postal");
				break;
			case "Race":
				List<Espece> especes = EspeceDao.findAllEspece();
				((AddConstController) controller).getForeign().setVisible(true);
				((AddConstController) controller).fill(especes);
				break;
			default:
				((AddConstController) controller).getOption().setVisible(false);
				((AddConstController) controller).getForeign().setVisible(false);
				break;
			}

		} else if ("Validate".equals(parameters.get("ACTION"))) {
			LOG.debug("Action : Validate");
			String type = ((AddConstController) controller).getType().getSelectionModel().getSelectedItem();
			String foreign = null;
			if (((AddConstController) controller).getForeign().getSelectionModel().getSelectedItem() != null) {
				foreign = ((AddConstController) controller).getForeign().getSelectionModel().getSelectedItem()
						.toString();
			}
			String option = ((AddConstController) controller).getOption().getText();
			String text = ((AddConstController) controller).getText().getText();

			String message = null;

			switch (type) {
			case "Pays":
				if (GeoDao.findPaysByName(text) != null) {
					message = "Pays existant!";
				} else {
					Pays pays = new Pays(text);
					GeoDao.saveOrUpdate(pays);
					message = "Pays " + text + " a ete ajoute.";
				}
				break;
			case "Ville":
				if (GeoDao.findVilleByName(text) != null) {
					message = "Ville existante!";
				} else {
					Ville ville = new Ville(GeoDao.findPaysByName(foreign), text, option);
					GeoDao.saveOrUpdate(ville);
					message = "Ville " + text + " a ete ajoute.";
				}
				break;
			case "Race":
				if (EspeceDao.findRaceByNom(text) != null) {
					message = "Race existante!";
				} else {
					Race race = new Race(text);
					race.setEspece(EspeceDao.findEspeceByNom(foreign));
					EspeceDao.saveOrUpdate(race);
					message = "Race " + text + " a ete ajoute.";
				}
				break;
			case "Espece":
				if (EspeceDao.findEspeceByNom(text) != null) {
					message = "Espece existante!";
				} else {
					Espece espece = new Espece(text);
					EspeceDao.saveOrUpdate(espece);
					message = "Espece " + text + " a ete ajoute.";
				}
				break;
			case "Role":
				if (TraitementDao.findMaladieByNom(text) != null) {
					message = "Maladie existante!";
				} else {
					Maladie maladie = new Maladie(text);
					TraitementDao.saveOrUpdate(maladie);
					message = "Maladie " + text + " a ete ajoute.";
				}
				break;
			case "Maladie":
				if (TraitementDao.findMaladieByNom(text) != null) {
					message = "Maladie existante!";
				} else {
					Maladie maladie = new Maladie(text);
					TraitementDao.saveOrUpdate(maladie);
					message = "Maladie " + text + " a ete ajoute.";
				}
				break;
			default:
				break;
			}
			LOG.info(message);
			parameters.put("info", message);
			LOG.debug("Sortie de la methode validate de AddConstAction");
			next();
		} else {
			LOG.debug("Sortie de la methode validate de AddConstAction");
		}
	}

	@Override
	public void next() {
		((AddConstController) controller).getInfo().setText((String) parameters.get("info"));
		((AddConstController) controller).getText().setText(null);
	}

	@Override
	public void cancel() {
		main.setAction(identifier);
	}

}
