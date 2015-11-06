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
public class EdgeTypeBox extends ChoiceBoxClass {

	public EdgeTypeBox(GUIVariables GuiVariables) {
		super(GuiVariables);

	}

	@Override
	public void setFirstItem() {
		getBox().getSelectionModel().select(getMyGuiVariables().getEdgeType());
	}

	@Override
	protected void boxAction(String newValue) {

		getMyGuiVariables().setEdgeType(newValue);
		getMyGuiVariables().getMyGUI().changeshape();
	}

	@Override
	protected String[] setOptions() {
		return getPossibleStates().getPossibleEdges();

	}

}
