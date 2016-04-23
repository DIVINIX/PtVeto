package fr.iut.pt.veto.form.HR;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import fr.iut.pt.veto.model.entitie.individu.Salarie;
import fr.iut.pt.veto.model.entitie.rh.Contrat;
import fr.iut.pt.veto.template.FormController;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class UpdateContractController extends FormController {

	@FXML
	private TextField salary;
	
	@FXML
	private DatePicker dateBegin;
	
	@FXML
	private DatePicker dateEnd;
	
	@FXML
	private Label info;
	
	public void prepare(Salarie salarie) {
		if (salarie.getContrat() != null) {
			salary.setText(salarie.getContrat().getSalaire());
			Date dateDebut = salarie.getContrat().getDateDebut();
			Instant instantDateDebut = Instant.ofEpochMilli(dateDebut.getTime());
			LocalDate resDateDebut = LocalDateTime.ofInstant(instantDateDebut, ZoneId.systemDefault()).toLocalDate();
			dateBegin.setValue(resDateDebut);
		
			if (salarie.getContrat().getDateFin() != null) {
				Date dateFin = salarie.getContrat().getDateFin();
				Instant instantFin = Instant.ofEpochMilli(dateFin.getTime());
				LocalDate resFin = LocalDateTime.ofInstant(instantFin, ZoneId.systemDefault()).toLocalDate();
				dateEnd.setValue(resFin);
			}			 
		}	
	}
	
	public void validate() {
		action.getParameters().put("ACTION", "Validate");
		action.validate();
	}
	
	public void cancel() {
		action.cancel();
	}

	/**
	 * @return the salary
	 */
	public TextField getSalary() {
		return salary;
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
