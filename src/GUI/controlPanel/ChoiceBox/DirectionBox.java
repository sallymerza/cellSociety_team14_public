/**
 *
 */
package GUI.controlPanel.ChoiceBox;

import GUI.GUIVariables;

/**
 *
 * @author Sally Al
 *
 */
public class DirectionBox extends ChoiceBoxClass {

	public DirectionBox(GUIVariables GuiVariables) {
		super(GuiVariables);
	}

	@Override
	protected void boxAction(String newValue) {
		getMyGuiVariables().setDirection(newValue);
		getMyGuiVariables().getMyGUI().changeshape();

	}

	@Override
	public void setFirstItem() {
		getBox().getSelectionModel().select(getMyGuiVariables().getDirection());

	}

	@Override
	protected String[] setOptions() {
		return getPossibleStates().getPossibleDirections();
	}

}
