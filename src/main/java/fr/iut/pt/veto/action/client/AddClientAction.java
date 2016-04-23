package fr.iut.pt.veto.action.client;


import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import fr.iut.pt.veto.MainDisplay;
import fr.iut.pt.veto.action.Action;
import fr.iut.pt.veto.action.ActionEnum;
import fr.iut.pt.veto.model.entitie.geo.Pays;
import fr.iut.pt.veto.model.entitie.geo.Ville;
import fr.iut.pt.veto.model.entitie.individu.Client;
import fr.iut.pt.veto.model.entitie.individu.Salarie;
import fr.iut.pt.veto.model.dao.GeoDao;
import fr.iut.pt.veto.model.dao.IndividuDao;
import fr.iut.pt.veto.form.admin.AddUserController;
import fr.iut.pt.veto.form.client.AddClientController;
import fr.iut.pt.veto.util.JavaFxUtils;
import javafx.scene.layout.BorderPane;

/**
 * 
 * @author Yann
 */
public class AddClientAction extends Action {
	private static final Logger LOG = Logger.getLogger(AddClientAction.class);

	public AddClientAction(MainDisplay main) {
		super(main);
		this.identifier = ActionEnum.ADD_CLIENT;
	}

	@Override
	public void prepare() {
		LOG.debug("Entree dans la methode prepare de AddClientAction");
		view = (BorderPane) JavaFxUtils.getView(this);
		parameters = new HashMap<>();
		
		List<Pays> pays = GeoDao.findAllPays();
		
		((AddClientController) controller).prepare(pays);
		
		LOG.debug("Sortie de la methode prepare de AddClientAction");

	}

	@Override
	public void validate() {
		LOG.debug("Entree dans la methode validate de AddClientAction");
		
		if ("Select".equals(parameters.get("ACTION"))) {
			LOG.debug("Action : Select");
			Pays pays = (Pays) parameters.get("pays");
			List<Ville> villes = GeoDao.findAllVilleByPays(pays);
			((AddClientController) controller).fillVille(villes);
		}else if ("Validate".equals(parameters.get("ACTION"))) {
			LOG.debug("Action : Validate");

			String lastName = ((AddClientController) controller).getLastName().getText();
			String firstName = ((AddClientController) controller).getFirstName().getText();
			Ville ville = ((AddClientController) controller).getVille().getSelectionModel().getSelectedItem();
			String telephone = ((AddClientController) controller).getTelephone().getText();
			String adress = ((AddClientController) controller).getAdress().getText();
			String mail = ((AddClientController) controller).getMail().getText();
		
			if (lastName.equals("") || firstName.equals(""))
			{
				LOG.error("Sortie avec erreur de la methode validate de AddClientAction : "+(String) parameters.get("info"));
				parameters.put("info","Un ou plusieurs champs ne sont pas renseignés.");
				this.cancel();
			}
			
			else {
				Client client = new Client(lastName,firstName);
				client.setTelephone(telephone);
				client.setVille(ville);		
				client.setAdresse(adress);		
				client.setMail(mail);
				
				IndividuDao.saveOrUpdate(client);
				parameters.put("info", "Client "+client.getNom()+" ajoute avec succes.");
				LOG.debug("Sortie avec succes de la methode validate de AddClientAction");
				this.next();
			}
		}
	}

	@Override
	public void next() {
		((AddClientController) controller).getInfo().setText((String) parameters.get("info"));
		((AddClientController) controller).clear();

	}

	@Override
	public void cancel() {
		if (parameters.get("info") != null) {
			((AddClientController) controller).getInfo().setText((String) parameters.get("info"));
		} else {
			((AddClientController) controller).getInfo().setText("Action Annule");
		}
		((AddClientController) controller).clear();
		
	}

}
