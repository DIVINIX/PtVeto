/**
 * 
 */
package fr.iut.pt.veto.util;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;

/**
 * @author sebboursier
 *
 */
public class CellLabel<T> extends ListCell<T> {

	private Label cellLabel;

	private T value;

	@Override
	protected void updateItem(T t, boolean empty) {
		super.updateItem(t, empty);
		if (!empty) {
			value = t;
			cellLabel = new Label(value.toString());
			setGraphic(cellLabel);
		}
	}

}