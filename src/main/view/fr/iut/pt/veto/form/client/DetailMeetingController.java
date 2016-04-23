package fr.iut.pt.veto.form.client;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import fr.iut.pt.veto.model.entitie.Consultation;
import fr.iut.pt.veto.template.FormController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;

public class DetailMeetingController extends FormController {
	
	@FXML
	private TextField animal;
	
	@FXML
	private TextField date;
	
	@FXML
	private TextArea desc;
	
	public void prepare(Consultation consultation) {
		animal.setText(consultation.getAnimal().getNom());
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		date.setText(df.format(consultation.getDateDebut()));
		desc.setText(consultation.getDescription());
		
		animal.setEditable(false);
		date.setEditable(false);
		desc.setEditable(false);
	}
	
	public void update() {
		action.getParameters().put("ACTION", "Modifier");
		action.validate();
	}
	
	public void cancel() {
		action.getParameters().put("ACTION", "Retour");
		action.validate();
	}

}
