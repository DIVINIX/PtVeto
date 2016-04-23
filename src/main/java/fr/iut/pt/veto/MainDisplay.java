package fr.iut.pt.veto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import fr.iut.pt.veto.action.*;
import fr.iut.pt.veto.action.HR.*;
import fr.iut.pt.veto.action.admin.*;
import fr.iut.pt.veto.action.animal.*;
import fr.iut.pt.veto.action.client.*;
import fr.iut.pt.veto.action.sale.*;
import fr.iut.pt.veto.action.stock.*;
import fr.iut.pt.veto.action.treatment.*;
import fr.iut.pt.veto.menu.LeftMenuController;
import fr.iut.pt.veto.model.dao.AnimalDao;
import fr.iut.pt.veto.model.dao.ConsultationDao;
import fr.iut.pt.veto.model.dao.EspeceDao;
import fr.iut.pt.veto.model.dao.GeoDao;
import fr.iut.pt.veto.model.dao.IndividuDao;
import fr.iut.pt.veto.model.dao.InventaireDao;
import fr.iut.pt.veto.model.dao.RappelDao;
import fr.iut.pt.veto.model.dao.RhDao;
import fr.iut.pt.veto.model.dao.TraitementDao;
import fr.iut.pt.veto.model.entitie.Consultation;
import fr.iut.pt.veto.model.entitie.animal.Animal;
import fr.iut.pt.veto.model.entitie.animal.Espece;
import fr.iut.pt.veto.model.entitie.animal.Race;
import fr.iut.pt.veto.model.entitie.geo.Pays;
import fr.iut.pt.veto.model.entitie.geo.Ville;
import fr.iut.pt.veto.model.entitie.individu.Client;
import fr.iut.pt.veto.model.entitie.individu.Salarie;
import fr.iut.pt.veto.model.entitie.inventaire.Produit;
import fr.iut.pt.veto.model.entitie.inventaire.Vente;
import fr.iut.pt.veto.model.entitie.rh.Role;
import fr.iut.pt.veto.model.entitie.rh.TypeAbsence;
import fr.iut.pt.veto.model.entitie.traitement.Maladie;
import fr.iut.pt.veto.model.entitie.traitement.Rappel;
import fr.iut.pt.veto.model.entitie.traitement.Traitement;
import fr.iut.pt.veto.util.JavaFxUtils;
import fr.iut.pt.veto.util.JpaUtils;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Classe "Main" de l'application. En Jfx, le main doit forcement se trouver
 * dans la classe qui etend de Application.
 * 
 * @author sebastien
 */
public class MainDisplay extends Application {

	private static final Logger LOG = Logger.getLogger(MainDisplay.class);

	/**
	 * Ecran de l'application
	 */
	private Stage primaryStage;

	/**
	 * L'action en cours (formulaire de droite)
	 */
	private Action currentAction;

	/**
	 * Le controller general, qui gere ce qui se passe a gauche (menu) et a
	 * droite (formulaires)
	 */
	private MainDisplayController mainDisplayController;

	/**
	 * Le controller du menu deroulant de gauche, il est ici car particulier, ce
	 * n'est pas une action.
	 */
	private LeftMenuController leftMenuController;
	
	/**
	 * L'utilisateur connecte a l'application.
	 */
	private Salarie utilisateur;

	private boolean bouchon = false;

	/**
	 * Le point d'entree de l'application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		LOG.debug("Debut de l'application");
		launch(args);
	}

	/**
	 * Le main nous renvoie au final ici. C'est donc ici que notre code commence
	 * vraiment. Lancement de la fenetre avec la HomePage.
	 */
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Veto");
		this.primaryStage.getIcons().add(new Image("/fr/iut/pt/veto/asset/Logo_noir.png"));

		this.primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent t) {
				LOG.debug("Demande de fermeture de l'application.");
				JpaUtils.closeEntityManagerFactory();
				System.exit(0);
			}
		});

		setAction(ActionEnum.HOME);
	}

	/**
	 * la fenetre generale prend MainDisplay pour Scene
	 */
	private void setSceneMainDisplay() {
		BorderPane mainView = null;
		BorderPane menuView = null;
		FXMLLoader loadMain = JavaFxUtils.getLoader("/fr/iut/pt/veto/MainDisplay.fxml");
		FXMLLoader loadMenu = JavaFxUtils.getLoader("/fr/iut/pt/veto/menu/LeftMenu.fxml");

		try {
			mainView = loadMain.load();
			mainDisplayController = loadMain.getController();
			mainDisplayController.setMain(this);

			menuView = loadMenu.load();
			leftMenuController = loadMenu.getController();
			leftMenuController.setMain(this);

			// Seul Admin peut voir Administration
			if (!"Admin".equals(utilisateur.getRole().getIntitule())) {
				leftMenuController.getAdministration().setVisible(false);
			}

		} catch (IOException e) {
			System.err.println("Erreur lors du chargement de MainDisplay.fxml et LeftMenu.fxml");
			e.printStackTrace();
		}

		((BorderPane) mainView.getLeft()).setCenter(menuView);

		primaryStage.setScene(new Scene(mainView));
		primaryStage.show();
	}

	/**
	 * Validation des donnees de connexion puis passage a MainDisplay.
	 */
	public void connection() {
		LOG.debug("Connexion");
		if (!bouchon)
			bouchon();
		this.setSceneMainDisplay();
	}

	/**
	 * Suppression utilisateur et passage a HomeAction.
	 */
	public void deconnexion() {
		LOG.debug("Deconnexion");
		utilisateur = null;
		setAction(ActionEnum.HOME);
	}

	/**
	 * Une methode qui est une sorte de melange entre les paterns Factory et
	 * Dispatcher. C'est ici que l'on change d'action.
	 * 
	 * @param actionType
	 */
	public void setAction(ActionEnum actionType) {
		switch (actionType) {
		case HOME: // la fenetre generale prend HomePage pour Scene
			currentAction = new HomeAction(this);
			currentAction.prepare();
			primaryStage.setScene(new Scene(currentAction.getView()));
			primaryStage.show();
			break;

		/**
		 * ADMIN
		 */

		case ADD_USER:
			currentAction = new AddUserAction(this);
			currentAction.prepare();
			mainDisplayController.setForm(currentAction.getView());
			break;
		case LIST_USERS:
			currentAction = new ListUsersAction(this);
			currentAction.prepare();
			mainDisplayController.setForm(currentAction.getView());
			break;
		case UPDATE_USER:
			UpdateUserAction updateUserAction = new UpdateUserAction(this);
			updateUserAction.setParameters(currentAction.getParameters());
			currentAction = updateUserAction;
			currentAction.prepare();
			mainDisplayController.setForm(currentAction.getView());
			break;
		case ADD_CONST:
			currentAction = new AddConstAction(this);
			currentAction.prepare();
			mainDisplayController.setForm(currentAction.getView());
			break;

		/**
		 * CLIENT
		 */

		case LIST_CLIENTS: // liste des clients
			currentAction = new ListClientsAction(this);
			currentAction.prepare();
			mainDisplayController.setForm(currentAction.getView());
			break;
		case ADD_CLIENT: // Ajout d'un client
			currentAction = new AddClientAction(this);
			currentAction.prepare();
			mainDisplayController.setForm(currentAction.getView());
			break;
		case LIST_MEETINGS: // liste des rendez-vous
			Action actionListMeeting = new ListMeetingsAction(this);
			;
			actionListMeeting.setParameters(currentAction.getParameters());
			currentAction = actionListMeeting;
			currentAction.prepare();
			mainDisplayController.setForm(currentAction.getView());
			break;
		case ADD_MEETING:
			currentAction = new AddMeetingAction(this);
			currentAction.prepare();
			mainDisplayController.setForm(currentAction.getView());
			break;
		case DETAIL_CLIENT:
			Action actionDetailClient = new DetailClientAction(this);
			actionDetailClient.setParameters(currentAction.getParameters());
			currentAction = actionDetailClient;
			currentAction.prepare();
			mainDisplayController.setForm(currentAction.getView());
			break;
		case DETAIL_MEETING:
			Action detailMeetingAction = new DetailMeetingAction(this);
			detailMeetingAction.setParameters(currentAction.getParameters());
			currentAction = detailMeetingAction;
			currentAction.prepare();
			mainDisplayController.setForm(currentAction.getView());
			break;
		case UPDATE_CLIENT:
			Action actionUpdateClient = new UpdateClientAction(this);
			actionUpdateClient.setParameters(currentAction.getParameters());
			currentAction = actionUpdateClient;
			currentAction.prepare();
			mainDisplayController.setForm(currentAction.getView());
			break;
		case UPDATE_MEETING:
			Action actionUpdateMeeting = new UpdateMeetingAction(this);
			actionUpdateMeeting.setParameters(currentAction.getParameters());
			currentAction = actionUpdateMeeting;
			currentAction.prepare();
			mainDisplayController.setForm(currentAction.getView());
			break;

		/**
		 * ANIMAL
		 */

		case LIST_ANIMALS:
			ListAnimalsAction listAnimalsAction = new ListAnimalsAction(this);
			listAnimalsAction.setParameters(currentAction.getParameters());
			currentAction = listAnimalsAction;
			currentAction.prepare();
			mainDisplayController.setForm(currentAction.getView());
			break;
		case ADD_ANIMAL:
			currentAction = new AddAnimalAction(this);
			currentAction.prepare();
			mainDisplayController.setForm(currentAction.getView());
			break;
		case UPDATE_ANIMAL:
			UpdateAnimalAction updateAnimalAction = new UpdateAnimalAction(this);
			updateAnimalAction.setParameters(currentAction.getParameters());
			currentAction = updateAnimalAction;
			currentAction.prepare();
			mainDisplayController.setForm(currentAction.getView());
			break;
		case LIST_RECALLS:
			ListRecallsAction listRecallsAction = new ListRecallsAction(this);
			listRecallsAction.setParameters(currentAction.getParameters());
			currentAction = listRecallsAction;
			currentAction.prepare();
			mainDisplayController.setForm(currentAction.getView());
			break;
		case ADD_RECALL:
			currentAction = new AddRecallAction(this);
			currentAction.prepare();
			mainDisplayController.setForm(currentAction.getView());
			break;
		case UPDATE_RECALL:
			UpdateRecallAction updateRecallAction = new UpdateRecallAction(this);
			updateRecallAction.setParameters(currentAction.getParameters());
			currentAction = updateRecallAction;
			currentAction.prepare();
			mainDisplayController.setForm(currentAction.getView());
			break;

		/**
		 * STOCK
		 */

		case LIST_STOCK:
			currentAction = new ListStockAction(this);
			currentAction.prepare();
			mainDisplayController.setForm(currentAction.getView());
			break;
		case ADD_PRODUCT:
			currentAction = new AddProductAction(this);
			currentAction.prepare();
			mainDisplayController.setForm(currentAction.getView());
			break;
		case UPDATE_PRODUCT:
			UpdateProductAction updateProductAction = new UpdateProductAction(this);
			updateProductAction.setParameters(currentAction.getParameters());
			currentAction = updateProductAction;
			currentAction.prepare();
			mainDisplayController.setForm(currentAction.getView());
			break;

		/**
		 * TREATMENT
		 */

		case LIST_TREATMENTS:
			ListTreatmentsAction listTraitementAction = new ListTreatmentsAction(this);
			listTraitementAction.setParameters(currentAction.getParameters());
			currentAction = listTraitementAction;
			currentAction.prepare();
			mainDisplayController.setForm(currentAction.getView());
			break;
		case ADD_TREATMENT:
			currentAction = new AddTreatmentAction(this);
			currentAction.prepare();
			mainDisplayController.setForm(currentAction.getView());
			break;
		case UPDATE_TREATMENT:
			UpdateTreatmentAction updateTraitementAction = new UpdateTreatmentAction(this);
			updateTraitementAction.setParameters(currentAction.getParameters());
			currentAction = updateTraitementAction;
			currentAction.prepare();
			mainDisplayController.setForm(currentAction.getView());
			break;
		case DETAIL_TREATMENT:
			DetailTreatmentAction detailTraitementAction = new DetailTreatmentAction(this);
			detailTraitementAction.setParameters(currentAction.getParameters());
			currentAction = detailTraitementAction;
			currentAction.prepare();
			mainDisplayController.setForm(currentAction.getView());
			break;
		case ADD_PRESCRIPTION:
			currentAction = new AddPrescriptionAction(this);
			currentAction.prepare();
			mainDisplayController.setForm(currentAction.getView());
			break;

		/**
		 * HR
		 */

		case LIST_EMPLOYEES:
			currentAction = new ListEmployeesAction(this);
			currentAction.prepare();
			mainDisplayController.setForm(currentAction.getView());
			break;
		case DETAIL_EMPLOYEE:
			DetailEmployeeAction detailEmployeeAction = new DetailEmployeeAction(this);
			detailEmployeeAction.setParameters(currentAction.getParameters());
			currentAction = detailEmployeeAction;
			currentAction.prepare();
			mainDisplayController.setForm(currentAction.getView());
			break;
		case LIST_LEAVES:
			ListLeavesAction listLeavesAction = new ListLeavesAction(this);
			listLeavesAction.setParameters(currentAction.getParameters());
			currentAction = listLeavesAction;
			currentAction.prepare();
			mainDisplayController.setForm(currentAction.getView());
			break;
		case ADD_LEAVE:
			currentAction = new AddLeaveAction(this);
			currentAction.prepare();
			mainDisplayController.setForm(currentAction.getView());
			break;
		case UPDATE_CONTRACT:
			UpdateContractAction updateContractAction = new UpdateContractAction(this);
			updateContractAction.setParameters(currentAction.getParameters());
			currentAction = updateContractAction;
			currentAction.prepare();
			mainDisplayController.setForm(currentAction.getView());
			break;
		/**
		 * SALE
		 */

		case LIST_BILLS:
			currentAction = new ListBillsAction(this);
			currentAction.prepare();
			mainDisplayController.setForm(currentAction.getView());
			break;
		case SALE_PRODUCT:
			currentAction = new SaleProductAction(this);
			currentAction.prepare();
			mainDisplayController.setForm(currentAction.getView());
			break;

		default: // par defaut, l'action en cours est ecrase.
			currentAction = null;
			break;
		}
	}

	/**
	 * @return the utilisateur
	 */
	public Salarie getUtilisateur() {
		return utilisateur;
	}

	/**
	 * @param utilisateur
	 *            the utilisateur to set
	 */
	public void setUtilisateur(Salarie utilisateur) {
		this.utilisateur = utilisateur;
	}

	@Deprecated
	private void bouchon() {
		Role role_rh = new Role("Rh");
		Role role_veterinaire = new Role("veterinaire");
		RhDao.saveOrUpdate(role_rh);
		RhDao.saveOrUpdate(role_veterinaire);

		Pays pays_france = new Pays("France");
		Pays pays_espagne = new Pays("Espagne");
		GeoDao.saveOrUpdate(pays_france);
		GeoDao.saveOrUpdate(pays_espagne);

		Ville ville_canejan = new Ville(GeoDao.findPaysByName("France"), "Canejan", "33610");
		Ville ville_talence = new Ville(GeoDao.findPaysByName("France"), "Talence", "33400");
		GeoDao.saveOrUpdate(ville_canejan);
		GeoDao.saveOrUpdate(ville_talence);

		TypeAbsence absence_cp = new TypeAbsence("Congees payees");
		TypeAbsence absence_rtt = new TypeAbsence("RTT");
		RhDao.saveOrUpdate(absence_cp);
		RhDao.saveOrUpdate(absence_rtt);

		Maladie maladie_rage = new Maladie("La rage");
		Maladie maladie_tula = new Maladie("La tularemie ");
		int id_m1 = TraitementDao.saveOrUpdate(maladie_rage);
		TraitementDao.saveOrUpdate(maladie_tula);

		Client c1 = new Client("Jean", "Peut-Plus");
		c1.setTelephone("0505050505");
		c1.setVille(GeoDao.findVilleByName("Talence"));
		c1.setMail("jean.peutplus@gmail.com");
		c1.setAdresse("42 rue du TurFu");
		c1.setDateDebut(new Date());
		int id_c1 = IndividuDao.saveOrUpdate(c1);
		
		Espece e1 = new Espece("Chat");
		Espece e2 = new Espece("Chien");
		int id_e1 = EspeceDao.saveOrUpdate(e1);
		int id_e2 = EspeceDao.saveOrUpdate(e2);
		
		Race race1 = new Race("Persan", EspeceDao.findEspeceById(id_e1));
		Race race2 = new Race("Labrador", EspeceDao.findEspeceById(id_e2));
		int id_race1 = EspeceDao.saveOrUpdate(race1);
		int id_race2 = EspeceDao.saveOrUpdate(race2);

		Animal a1 = new Animal((Client) IndividuDao.findIndividuById(id_c1));
		a1.setNom("Marcel");
		a1.setRace(EspeceDao.findRaceById(id_race1));
		int id_a1 = AnimalDao.saveOrUpdate(a1);

		Consultation cons1 = new Consultation();
		cons1.setAnimal(AnimalDao.findAnimalById(id_a1));
		cons1.setDateDebut(new Date());
		cons1.setDescription("Ceci est une consultation test.");
		ConsultationDao.saveOrUpdate(cons1);

		Rappel r1 = new Rappel();
		r1.setAnimal(AnimalDao.findAnimalById(id_a1));
		r1.setRaison("Rappel for no reason.");
		r1.setDate(new Date());
		RappelDao.saveOrUpdate(r1);

		Produit p1 = new Produit("produit 1");
		p1.setPrix_reel(2.92);
		p1.setStock(20);
		Produit p2 = new Produit("produit 2");
		p2.setPrix_reel(56.99);
		p2.setStock(20);
		int id_p1 = InventaireDao.saveOrUpdate(p1);
		int id_p2 = InventaireDao.saveOrUpdate(p2);

		Traitement t1 = new Traitement();
		t1.setAnimal(AnimalDao.findAnimalById(id_a1));
		t1.setDateDebut(new Date());
		t1.setDateFin(new Date());
		t1.setDescription("Il a toujours la rage, on reesaye.");
		t1.setNom("Traitement pour la rage.");
		t1.setMaladie(TraitementDao.findMaladieById(id_m1));
		List<Produit> t1_pr = new ArrayList<>();
		t1_pr.add(InventaireDao.findProduitById(id_p1));
		t1_pr.add(InventaireDao.findProduitById(id_p2));
		t1.setSoins(t1_pr);

		Vente v1 = new Vente();
		v1.setClient((Client) IndividuDao.findIndividuById(id_c1));
		v1.setDate(new Date());
		v1.setProduit(InventaireDao.findProduitById(id_p1));
		v1.setQuantite(3);
		v1.setSalarie(utilisateur);
		v1.setPrix(3 * 2.92);
		InventaireDao.saveOrUpdate(v1);

		bouchon = true;
	}

}
