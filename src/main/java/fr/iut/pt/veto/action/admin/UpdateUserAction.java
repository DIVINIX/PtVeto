package fr.iut.pt.veto.action.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import fr.iut.pt.veto.MainDisplay;
import fr.iut.pt.veto.action.Action;
import fr.iut.pt.veto.action.ActionEnum;
import fr.iut.pt.veto.form.admin.AddUserController;
import fr.iut.pt.veto.form.admin.UpdateUserController;
import fr.iut.pt.veto.model.dao.GeoDao;
import fr.iut.pt.veto.model.dao.IndividuDao;
import fr.iut.pt.veto.model.dao.RhDao;
import fr.iut.pt.veto.model.entitie.geo.Pays;
import fr.iut.pt.veto.model.entitie.geo.Ville;
import fr.iut.pt.veto.model.entitie.individu.Salarie;
import fr.iut.pt.veto.model.entitie.rh.Role;
import fr.iut.pt.veto.util.JavaFxUtils;
import javafx.scene.layout.BorderPane;

public class UpdateUserAction extends Action {

	private static final Logger LOG = Logger.getLogger(UpdateUserAction.class);

	public UpdateUserAction(MainDisplay main) {
		super(main);
		this.identifier = ActionEnum.UPDATE_USER;
	}

	@Override
	public void prepare() {
		LOG.debug("Entree dans la methode prepare de UpdateUserAction");
		view = (BorderPane) JavaFxUtils.getView(this);

		List<Salarie> salaries = new ArrayList<>();

		if (parameters != null && parameters.get("id") != null) {
			salaries.add((Salarie) IndividuDao.findIndividuById((int) parameters.get("id")));
		} else {
			parameters = new HashMap<>();
			salaries.addAll(IndividuDao.findAllSalarie());
		}

		((UpdateUserController) controller).prepare(salaries);

		LOG.debug("Sortie de la methode prepare de UpdateUserAction");
	}

	@Override
	public void validate() {
		LOG.debug("Entree dans la methode validate de UpdateUserAction");

		if ("SelectUser".equals(parameters.get("ACTION"))) {
			LOG.debug("Action : SelectUser");

			Salarie salarie = (Salarie) parameters.get("select");

			List<Pays> pays = GeoDao.findAllPays();
			List<Ville> ville = new ArrayList<>();
			if(salarie.getVille() != null) {
				ville = GeoDao.findAllVilleByPays(salarie.getVille().getPays());
			}
			List<Role> role = RhDao.findAllRole();

			((UpdateUserController) controller).fill(salarie, pays, ville, role);

		} else if ("SelectPays".equals(parameters.get("ACTION"))) {
			LOG.debug("Action : SelectPays");
			Pays pays = (Pays) parameters.get("select");
			List<Ville> villes = GeoDao.findAllVilleByPays(pays);
			((UpdateUserController) controller).fillVille(villes);
		} else if ("Validate".equals(parameters.get("ACTION"))) {
			LOG.debug("Action : Validate");

			if (parameters.get("select") != null
					&& !((UpdateUserController) controller).getPrenom().getText().isEmpty() 
					&& !((UpdateUserController) controller).getNom().getText().isEmpty()
					&& !(((UpdateUserController) controller).getIdentifiant().getText()).isEmpty()
					&& !((UpdateUserController) controller).getPassword().getText().isEmpty()) {
				Salarie salarie = (Salarie) parameters.get("select");
				salarie.setPrenom(((UpdateUserController) controller).getPrenom().getText());
				salarie.setNom(((UpdateUserController) controller).getNom().getText());
				salarie.setTelephone(((UpdateUserController) controller).getTelephone().getText());
				salarie.setVille(((UpdateUserController) controller).getVille().getSelectionModel().getSelectedItem());
				salarie.setAdresse(((UpdateUserController) controller).getAdresse().getText());
				salarie.setMail(((UpdateUserController) controller).getMail().getText());
				salarie.setRole(((UpdateUserController) controller).getRole().getSelectionModel().getSelectedItem());
				salarie.setIdentifiant((((UpdateUserController) controller).getIdentifiant().getText()));
				salarie.setPassword(((UpdateUserController) controller).getPassword().getText());

				IndividuDao.saveOrUpdate(salarie);
				
				LOG.debug("Sortie de la methode validate de UpdateUserAction");
				next();
			} else {
				parameters.put("info", "Il manque des champs");
				LOG.debug("Sortie de la methode validate de UpdateUserAction");
				cancel();
			}
		}
	}

	@Override
	public void next() {
		main.setAction(ActionEnum.LIST_USERS);
	}

	@Override
	public void cancel() {
		((UpdateUserController) controller).getInfo().setText((String) parameters.get("info"));
	}

}
