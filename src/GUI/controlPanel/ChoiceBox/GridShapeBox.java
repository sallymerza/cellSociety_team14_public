
package GUI.controlPanel.ChoiceBox;

import GUI.GUIVariables;

/**
 *
 * @author Sally Al
 *
 */
public class GridShapeBox extends ChoiceBoxClass {

	public GridShapeBox(GUIVariables GuiVariables) {
		super(GuiVariables);

	}

	@Override
	public void setFirstItem() {
		getBox().getSelectionModel().select(getMyGuiVariables().getCellShape());

	}

	@Override
	protected void boxAction(String newValue) {

		getMyGuiVariables().setCellShape(newValue);
		getMyGuiVariables().getMyGUI().changeshape();
	}

	@Override
	protected String[] setOptions() {
		return getPossibleStates().getPossibleShapes();

	}

}
