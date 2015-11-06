/**
 *
 */
package GUI.controlPanel.ChoiceBox;

import GUI.GUIVariables;
import GUI.GridControl.PossibleSpecificStates;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ChoiceBox;

/**
 *
 * @author Sally Al
 *
 */
public abstract class ChoiceBoxClass {

	private GUIVariables myGuiVariables;

	public ChoiceBoxClass(GUIVariables GuiVariables) {
		myGuiVariables = GuiVariables;

		createOptions();
	}

	private ChoiceBox<String> box = new ChoiceBox<String>();
	private PossibleSpecificStates possibleStates = new PossibleSpecificStates();
	private String[] optionsArray;

	protected void createOptions() {
		optionsArray = setOptions();
		for (String op : optionsArray) {
			getBox().getItems().add(op);
		}
		setFirstItem();
		getBox().valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				boxAction(newValue);

			}
		});

	}

	protected abstract void boxAction(String newValue);

	public abstract void setFirstItem();

	public ChoiceBox<String> getBox() {
		return box;
	}

	protected abstract String[] setOptions();

	protected GUIVariables getMyGuiVariables() {
		return myGuiVariables;
	}

	public PossibleSpecificStates getPossibleStates() {
		return possibleStates;
	}

}
