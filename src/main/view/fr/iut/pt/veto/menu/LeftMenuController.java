package fr.iut.pt.veto.menu;

import fr.iut.pt.veto.action.ActionEnum;
import fr.iut.pt.veto.template.GeneralController;
import javafx.fxml.FXML;
import javafx.scene.control.TitledPane;

/**
 * En gros, ce controlleur aura une méthode par action atteignable.
 * @author sebastien
 */
public class LeftMenuController extends GeneralController {
	
	@FXML
	private TitledPane administration;
	
	/**
	 * @return the administration
	 */
	public TitledPane getAdministration() {
		return administration;
	}

	public void deconnexion() {
		main.deconnexion();
	}

	/**
	 * ADMIN
	 */
	
	public void listUsers() {
		main.setAction(ActionEnum.LIST_USERS);
	}
	
	public void addUser() {
		main.setAction(ActionEnum.ADD_USER);
	}
	
	public void updateUser() {
		main.setAction(ActionEnum.UPDATE_USER);
	}
	
	public void addConst() {
		main.setAction(ActionEnum.ADD_CONST);
	}
	
	/**
	 * CLIENT 
	 */
	
	public void listClients() {
		main.setAction(ActionEnum.LIST_CLIENTS);
	}
	
	public void addClient() {
		main.setAction(ActionEnum.ADD_CLIENT);
	}
	
	public void listMeeting() {
		main.setAction(ActionEnum.LIST_MEETINGS);
	}
	
	public void addMeeting() {
		main.setAction(ActionEnum.ADD_MEETING);
	}
	
	/**
	 * ANIMAL
	 */
	
	public void listAnimal() {
		main.setAction(ActionEnum.LIST_ANIMALS);
	}
	
	public void addAnimal() {
		main.setAction(ActionEnum.ADD_ANIMAL);
	}
	
	public void listRecall() {
		main.setAction(ActionEnum.LIST_RECALLS);
	}
	
	public void addRecall() {
		main.setAction(ActionEnum.ADD_RECALL);
	}
	
	/**
	 * STOCK
	 */
	
	public void listStock() {
		main.setAction(ActionEnum.LIST_STOCK);
	}
	
	public void addProduct() {
		main.setAction(ActionEnum.ADD_PRODUCT);
	}
	
	public void updatePrice() {
		main.setAction(ActionEnum.UPDATE_PRODUCT);
	}
	
	/**
	 *  TREATMENT
	 */
	
	public void listTreatments() {
		main.setAction(ActionEnum.LIST_TREATMENTS);
	}
	
	public void addTreatment() {
		main.setAction(ActionEnum.ADD_TREATMENT);
	}
	
	public void addPrescription() {
		main.setAction(ActionEnum.ADD_PRESCRIPTION);
	}
	
	/**
	 *  RH
	 */
	public void listEmployees() {
		main.setAction(ActionEnum.LIST_EMPLOYEES);
	}
		
	public void listLeaves() {
		main.setAction(ActionEnum.LIST_LEAVES);
	}
	
	public void addLeave() {
		main.setAction(ActionEnum.ADD_LEAVE);
	}
	
	/**
	 *  Ventes
	 */
	public void saleProduct() {
		main.setAction(ActionEnum.SALE_PRODUCT);
	}
	
	public void listBills() {
		main.setAction(ActionEnum.LIST_BILLS);
	}
	

	
	
}
