/**
 * 
 */
package fr.iut.pt.veto.util;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import fr.iut.pt.veto.action.Action;
import fr.iut.pt.veto.action.HomeAction;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * @author sebastien
 *
 */
public class TestJavaFxUtils {

	@Test
	public void testGetLoader() throws IOException {
		FXMLLoader loader = JavaFxUtils.getLoader("/fr/iut/pt/veto/util/test.fxml");

		Node node = loader.load();

		Assert.assertNotNull(node);
		Assert.assertTrue(node instanceof AnchorPane);
	}
	
	@Test
	public void testGetView() {
		Action action = new HomeAction(null);
		
		BorderPane view = (BorderPane) JavaFxUtils.getView(action);

		Assert.assertNotNull(view);
	}
}
