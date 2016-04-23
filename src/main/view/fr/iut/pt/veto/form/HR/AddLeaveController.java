package fr.iut.pt.veto.form.HR;

import fr.iut.pt.veto.model.entitie.individu.Salarie;
import fr.iut.pt.veto.model.entitie.rh.TypeAbsence;
import fr.iut.pt.veto.template.FormController;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

public class AddLeaveController extends FormController {

	@FXML
	private ComboBox<Salarie> employee;
	
	@FXML
	private ComboBox<TypeAbsence> type;
	
	@FXML
	private DatePicker dateBegin;
	
	@FXML
	private DatePicker dateEnd;
	
	@FXML
	private Label info;
	

	public void validate() {
		action.getParameters().put("ACTION", "Validate");
		action.validate();
	}
	
	public void cancel() {
		action.cancel();
	}
	
	public void clear() {
		employee.getSelectionModel().clearSelection();
		type.getSelectionModel().clearSelection();
		dateBegin.getEditor().clear();
		dateEnd.getEditor().clear();
	}

	

	/**
	 * @return the employee
	 */
	public ComboBox<Salarie> getEmployee() {
		return employee;
	}

	/**
	 * @return the type
	 */
	public ComboBox<TypeAbsence> getType() {
		return type;
	}

	/**
	 * @return the dateBegin
	 */
	public DatePicker getDateBegin() {
		return dateBegin;
	}

	/**
	 * @return the dateEnd
	 */
	public DatePicker getDateEnd() {
		return dateEnd;
	}

	/**
	 * @return the info
	 */
	public Label getInfo() {
		return info;
	}
	
	
}

