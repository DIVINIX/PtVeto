/**
 * 
 */
package fr.iut.pt.veto.util;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;

/**
 * @author sebastien
 *
 */
public class CellBouton<T> extends TableCell<T,Integer> {

	private Button cellButton;
	
	private Integer value;

	public CellBouton(String nom) {
		cellButton = new Button(nom);
	}
	
    @Override
    protected void updateItem(Integer t, boolean empty) {
        super.updateItem(t, empty);
        value = t;
        if(!empty){
            setGraphic(cellButton);
        }
    }
    
    public void setOnAction(EventHandler<ActionEvent> eventHandler) {
    	cellButton.setOnAction(eventHandler);
    }

	/**
	 * @return the id
	 */
	public Integer getValue() {
		return this.value;
	}
    
}
