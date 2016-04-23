/**
 * 
 */
package fr.iut.pt.veto.form.admin;

import java.util.List;

import fr.iut.pt.veto.template.FormController;
import fr.iut.pt.veto.util.CellLabel;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.Callback;

/**
 * @author sebastien
 *
 */
public class AddConstController extends FormController {

	@FXML
	private ComboBox<String> type;
	
	@FXML
	private ComboBox<Object> foreign;
	
	@FXML
	private TextArea text;
	
	@FXML
	private TextField option;
	
	@FXML
	private Label info;
	
	public void prepare(List<String> type) {
		this.type.getItems().addAll(type);
		this.type.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
			
			@Override
			public ListCell<String> call(ListView<String> param) {
				return new CellLabel<String>();
			}
		});
	}
	
	public void select() {
		action.getParameters().put("ACTION", "Select");
		action.getParameters().put("type", type.getSelectionModel().getSelectedItem());
		action.validate();
	}
	
	public void validate() {
		action.getParameters().put("ACTION", "Validate");
		action.validate();
	}
	
	public void cancel() {
		action.cancel();
	}
	
	public void fill(List list) {
		this.foreign.getItems().clear();
		this.foreign.getItems().addAll(list);
		this.foreign.setCellFactory(new Callback<ListView<Object>, ListCell<Object>>() {
			
			@Override
			public ListCell<Object> call(ListView<Object> param) {
				return new CellLabel<Object>();
			}
		});
	}

	/**
	 * @return the type
	 */
	public ComboBox<String> getType() {
		return type;
	}

	/**
	 * @return the text
	 */
	public TextArea getText() {
		return text;
	}

	/**
	 * @return the info
	 */
	public Label getInfo() {
		return info;
	}

	/**
	 * @return the foreign
	 */
	public ComboBox<Object> getForeign() {
		return foreign;
	}

	/**
	 * @return the option
	 */
	public TextField getOption() {
		return option;
	}
	
}
