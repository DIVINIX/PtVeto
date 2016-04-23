package fr.iut.pt.veto.form.HR;

import fr.iut.pt.veto.template.FormController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class DetailEmployeeController extends FormController {

	@FXML
	private TextField lastname;
	@FXML
	private TextField firstname;
	@FXML
	private TextField phone;
	@FXML
	private TextField adress;
	@FXML
	private TextField city;
	@FXML
	private TextField email;
	@FXML
	private TextField role;
	@FXML
	private TextField login;
	
	
	public void validate() {
		action.validate();
	}


	/**
	 * @return the lastname
	 */
	public TextField getLastname() {
		return lastname;
	}


	/**
	 * @return the firstname
	 */
	public TextField getFirstname() {
		return firstname;
	}


	/**
	 * @return the phone
	 */
	public TextField getPhone() {
		return phone;
	}


	/**
	 * @return the adress
	 */
	public TextField getAdress() {
		return adress;
	}


	/**
	 * @return the city
	 */
	public TextField getCity() {
		return city;
	}


	/**
	 * @return the email
	 */
	public TextField getEmail() {
		return email;
	}


	/**
	 * @return the role
	 */
	public TextField getRole() {
		return role;
	}


	/**
	 * @return the login
	 */
	public TextField getLogin() {
		return login;
	}	
	

	
}
