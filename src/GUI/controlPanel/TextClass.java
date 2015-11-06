/**
 *
 */
package GUI.controlPanel;

import java.util.Map;

import Cell.Simulation;
import GUI.GridControl.PossibleSpecificStates;
import Main.XMLReader;
import javafx.scene.Group;
import javafx.scene.text.Text;

/**
 *
 * @author Sally Al
 *
 */
public class TextClass {
	private static final int Xlocatio = 650;
	private static final int ylocatio = 300;
	private static final int Yshift = 20;
	private Text text = new Text();
	private Text keys = new Text();
	private StringBuilder stringBuilder = new StringBuilder();

	public TextClass(Group root, XMLReader myXMLReader) {

		text.setText(myXMLReader.getMySimulation());
		text.setX(Xlocatio);
		text.setY(ylocatio);

		String[] states = myXMLReader.getMyStates();
		PossibleSpecificStates possibleStates = new PossibleSpecificStates();
		Map<String, Simulation> map = possibleStates.createGOlStates();

		stringBuilder.append("Keys:" + "\n");
		for (String state : states) {
			stringBuilder.append(map.get(state).getMyColor() + " " + state + "\n");

		}
		keys.setText(stringBuilder.toString());
		keys.setX(Xlocatio);
		keys.setY(ylocatio + Yshift);
		root.getChildren().add(text);
		root.getChildren().add(keys);
	}

}
