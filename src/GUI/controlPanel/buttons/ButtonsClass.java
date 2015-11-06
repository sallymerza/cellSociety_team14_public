package GUI.controlPanel.buttons;

import java.util.List;

import GUI.GUI;
import GUI.GUIVariables;
import GUI.Grid;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;

/** Author Sally AL **/
public abstract class ButtonsClass {
	private Button button;
	private Grid grid;
	private static List<Slider> sliders;
	private static GUIVariables myGuiVariables;
	private List<Object> myStageList;

	public ButtonsClass() {
		button = new Button();
	}

	protected Button getmyButton() {
		return button;
	}

	public void createButton(GUIVariables guiVariables, Grid grid, List<Slider> sliderList, List<Object> list) {

		setGrid(grid);
		sliders = sliderList;
		myGuiVariables = guiVariables;
		myStageList = list;
		button.setOnAction(e -> ButtonClicked());
	}

	protected List<Object> getStageList() {
		return myStageList;
	}

	public Button getButton() {
		return button;
	}

	protected GUIVariables getGuiVariables() {
		return myGuiVariables;
	}

	protected void updateGui(int cycle) {
		GUI gui = myGuiVariables.getMyGUI();
		gui.getTimeLine().setRate(updateSlider(0));
		gui.getTimeLine().getRate();
		gui.getTimeLine().setCycleCount(cycle);
		gui.getTimeLine().play();

	}

	protected void resetSliders() {
		sliders.get(0).setValue(1);

	}

	protected double updateSlider(int index) {
		return sliders.get(index).getValue();

	}

	protected void stopTimeLine() {
		myGuiVariables.getMyGUI().getTimeLine().stop();
	}

	public abstract void ButtonClicked();

	protected Grid getGrid() {
		return grid;
	}

	protected void setGrid(Grid grid) {
		this.grid = grid;
	}
}
