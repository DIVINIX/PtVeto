package fr.iut.pt.veto.form.client;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import fr.iut.pt.veto.model.entitie.Consultation;
import fr.iut.pt.veto.template.FormController;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class UpdateMeetingController extends FormController {
	
	@FXML
	private TextField animal;
	
	@FXML
	private DatePicker date;
	
	@FXML
	private TextArea desc;
	
	public void prepare(Consultation consultation) {
		animal.setText(consultation.getAnimal().getNom());
		Date dateMeeting = consultation.getDateDebut();
		Instant instant = Instant.ofEpochMilli(dateMeeting.getTime());
		LocalDate res = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
		date.setValue(res);
		desc.setText(consultation.getDescription());
		animal.setEditable(false);
	}
	
	public void validate() {
		action.getParameters().put("ACTION", "Validate");
		action.validate();
	}
	
	public void cancel() {
		action.cancel();
	}

	/**
	 * @return the animal
	 */
	public TextField getAnimal() {
		return animal;
	}

	/**
	 * @return the date
	 */
	public DatePicker getDate() {
		return date;
	}

	/**
	 * @return the desc
	 */
	public TextArea getDesc() {
		return desc;
	}
	
	

}
