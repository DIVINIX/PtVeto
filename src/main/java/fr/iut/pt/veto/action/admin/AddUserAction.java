package fr.iut.pt.veto.action.admin;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import fr.iut.pt.veto.MainDisplay;
import fr.iut.pt.veto.action.Action;
import fr.iut.pt.veto.action.ActionEnum;
import fr.iut.pt.veto.action.stock.AddProductAction;
import fr.iut.pt.veto.form.admin.AddUserController;
import fr.iut.pt.veto.model.dao.GeoDao;
import fr.iut.pt.veto.model.dao.IndividuDao;
import fr.iut.pt.veto.model.dao.RhDao;
import fr.iut.pt.veto.model.entitie.geo.Pays;
import fr.iut.pt.veto.model.entitie.geo.Ville;
import fr.iut.pt.veto.model.entitie.individu.Salarie;
import fr.iut.pt.veto.model.entitie.rh.Role;
import fr.iut.pt.veto.util.JavaFxUtils;
import javafx.scene.layout.BorderPane;

public class AddUserAction extends Action {

	private static final Logger LOG = Logger.getLogger(AddProductAction.class);

	public AddUserAction(MainDisplay main) {
		super(main);
		this.identifier = ActionEnum.ADD_USER;
	}

	@Override
	public void prepare() {
		LOG.debug("Entree dans la methode prepare de AddUserAction");
		view = (BorderPane) JavaFxUtils.getView(this);
		parameters = new HashMap<>();

		List<Pays> pays = GeoDao.findAllPays();
		List<Role> roles = RhDao.findAllRole();

		((AddUserController) controller).prepare(pays, roles);

		LOG.debug("Sortie de la methode prepare de AddUserAction");
	}

	@Override
	public void validate() {
		LOG.debug("Entree dans la methode validate de AddConstAction");

		if ("Select".equals(parameters.get("ACTION"))) {
			LOG.debug("Action : Select");
			Pays pays = (Pays) parameters.get("pays");
			List<Ville> villes = GeoDao.findAllVilleByPays(pays);
			((AddUserController) controller).fillVille(villes);
		} else if ("Validate".equals(parameters.get("ACTION"))) {
			LOG.debug("Action : Validate");
			
			if (IndividuDao.findSalarieByIdentifiant((((AddUserController) controller).getIdentifiant().getText())) != null) {
				parameters.put("info", "Salarie déjà existant.");
				LOG.debug("Sortie de la methode validate de AddUserAction");
				cancel();
			} else if(!((AddUserController) controller).getPrenom().getText().isEmpty() 
					&& !((AddUserController) controller).getNom().getText().isEmpty()
					&& !(((AddUserController) controller).getIdentifiant().getText()).isEmpty()
					&& !((AddUserController) controller).getPassword().getText().isEmpty()){
				Salarie salarie = new Salarie();
				salarie.setPrenom(((AddUserController) controller).getPrenom().getText());
				salarie.setNom(((AddUserController) controller).getNom().getText());
				salarie.setTelephone(((AddUserController) controller).getTelephone().getText());
				salarie.setVille(((AddUserController) controller).getVille().getSelectionModel().getSelectedItem());
				salarie.setAdresse(((AddUserController) controller).getAdresse().getText());
				salarie.setMail(((AddUserController) controller).getMail().getText());
				salarie.setRole(((AddUserController) controller).getRole().getSelectionModel().getSelectedItem());
				salarie.setIdentifiant((((AddUserController) controller).getIdentifiant().getText()));
				salarie.setPassword(((AddUserController) controller).getPassword().getText());

				IndividuDao.saveOrUpdate(salarie);
				
				LOG.debug("Sortie de la methode validate de AddUserAction");
				next();
			} else {
				parameters.put("info", "Il manque des champs");
				LOG.debug("Sortie de la methode validate de AddUserAction");
				cancel();
			}
		}
	}

	@Override
	public void next() {
		parameters.clear();
		main.setAction(ActionEnum.LIST_USERS);
	}

	@Override
	public void cancel() {
		((AddUserController) controller).getInfo().setText((String) parameters.get("info"));
	}

}
