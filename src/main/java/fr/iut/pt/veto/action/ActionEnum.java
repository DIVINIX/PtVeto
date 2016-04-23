/**
 * 
 */
package fr.iut.pt.veto.action;

/**
 * Cette enumeration doit lister toutes les actions (formulaires de droite)
 * disponibles. Cela sert principalement a ce que les controller/listener
 * puissent appeler des actions sans en savoir trop.
 * 
 * @author sebastien
 * @update Gauthier, Yann
 */
public enum ActionEnum {

	HOME("HomePage"),
	/**
	 * ADMIN
	 */
	LIST_USERS("admin/ListUsers"), ADD_USER("admin/AddUser"), UPDATE_USER("admin/UpdateUser"), ADD_CONST("admin/AddConst"), ADD_BREED("admin/AddBreed"), ADD_NATURE("admin/AddNature"),
	/**
	 * CLIENT
	 */
	LIST_CLIENTS("client/ListClients"), ADD_CLIENT("client/AddClient"), LIST_MEETINGS("client/ListMeetings"), ADD_MEETING("client/AddMeeting"), DETAIL_CLIENT("client/DetailClient"), DETAIL_MEETING("client/DetailMeeting"), UPDATE_CLIENT("client/UpdateClient"),UPDATE_MEETING("client/UpdateMeeting"), 
	/**
	 * ANIMAL
	 */
	LIST_ANIMALS("animal/ListAnimals"), ADD_ANIMAL("animal/AddAnimal"), UPDATE_ANIMAL("animal/UpdateAnimal"), LIST_RECALLS("animal/ListRecalls"), ADD_RECALL("animal/AddRecall"), UPDATE_RECALL("animal/UpdateRecall"), 
	/**
	 * TREATMENT
	 */
	LIST_TREATMENTS("treatment/ListTreatments"), ADD_TREATMENT("treatment/AddTreatment"), UPDATE_TREATMENT("treatment/UpdateTreatment"), DETAIL_TREATMENT("treatment/DetailTreatment"), ADD_PRESCRIPTION("treatment/AddPrescription"), 
	/**
	 * STOCK
	 */
	LIST_STOCK("stock/ListStock"), ADD_PRODUCT("stock/AddProduct"), UPDATE_PRODUCT("stock/UpdateProduct"), 
	/**
	 * RH
	 */
	LIST_EMPLOYEES("HR/ListEmployees"), DETAIL_EMPLOYEE("HR/DetailEmployee"), LIST_LEAVES("HR/ListLeaves"), ADD_LEAVE("HR/AddLeave"), UPDATE_CONTRACT("HR/UpdateContract"), 
	/**
	 * SALE
	 */
	LIST_BILLS("sale/ListBills"), SALE_PRODUCT("sale/SaleProduct");

	/**
	 * Sert essentiellement pour recuperer le chemin des vues. C'est en gros la
	 * regle de nomage pour les fichiers relatifs a l'action. vue => name.fxml
	 * controller/listener => nameController.java action => nameAction.java
	 */
	private String name = "";

	ActionEnum(String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}
}
