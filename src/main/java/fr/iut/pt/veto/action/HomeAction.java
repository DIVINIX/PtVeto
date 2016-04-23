/**
 * 
 */
package fr.iut.pt.veto.action;

import java.util.HashMap;

import org.apache.log4j.Logger;

import fr.iut.pt.veto.MainDisplay;
import fr.iut.pt.veto.action.admin.AddConstAction;
import fr.iut.pt.veto.form.HomePageController;
import fr.iut.pt.veto.model.dao.IndividuDao;
import fr.iut.pt.veto.model.entitie.individu.Salarie;
import fr.iut.pt.veto.model.entitie.rh.Role;
import fr.iut.pt.veto.util.JavaFxUtils;
import javafx.scene.layout.BorderPane;

/**
 * Action du debut. action particuliere car prend tout l'ecran.
 * 
 * @author sebastien
 */
public class HomeAction extends Action {
	
	private static final Logger LOG = Logger.getLogger(AddConstAction.class);

	public HomeAction(MainDisplay main) {
		super(main);
		this.identifier = ActionEnum.HOME;
	}

	@Override
	public void prepare() {
		LOG.debug("Entree dans la methode prepare de HomeAction");
		view = (BorderPane) JavaFxUtils.getView(this);
		parameters = new HashMap<>();
		
		if(IndividuDao.findIndividuByRole("Admin").isEmpty()) {
			Salarie salarie = new Salarie("admin", "admin");
			salarie.setIdentifiant("admin");
			salarie.setPassword("admin");
			salarie.setRole(new Role("Admin"));
			IndividuDao.saveOrUpdate(salarie);
			parameters.put("info", "Premiere connexion, Administrateur => admin/admin");
		}
		
		
		((HomePageController) controller).getInfo().setText((String) parameters.get("info"));
		LOG.debug("Sortie de la methode prepare de HomeAction");
	}

	@Override
	public void validate() {
		LOG.debug("Entree dans la methode validate de HomeAction");

		String user = ((HomePageController) controller).getUser().getText();
		String password = ((HomePageController) controller).getPass().getText();
		
		boolean validate = true;
		String message = null;
		
		if(user != null && password != null) {
			
			Salarie salarie = IndividuDao.findSalarieByIdentifiant(user);
			
			if (salarie != null) {
				if(password.equals(salarie.getPassword())) {
					main.setUtilisateur(salarie);
				} else {
					message = "Mot de passe incorrecte.";
					validate = false;
				}
			} else {
				message = "Identifiant incorrecte.";
				validate = false;
			}
		} else {
			message = "Veuillez remplir tout les champs.";
			validate = false;
		}
		
		if(validate) {
			LOG.debug("Sortie de la methode validate de HomeAction");
			next();
		} else {
			parameters.put("info", message);
			LOG.debug("Sortie de la methode validate de HomeAction");
			cancel();
		}
	}

	@Override
	public void next() {
		main.connection();
	}

	@Override
	public void cancel() {
		((HomePageController) controller).getInfo().setText((String) parameters.get("info"));
	}

}
