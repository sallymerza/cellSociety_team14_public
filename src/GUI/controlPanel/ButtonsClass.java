package GUI.controlPanel;

import java.util.List;

import GUI.GUI;
import GUI.Grid;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;

/** Author Sally AL **/
public abstract class ButtonsClass {
	private Button button;

	public void createButton(String description, GUI gui, Grid grid, List<Slider> s, List<Object> list) {
		button = new Button(description);

		button.setOnAction(e -> ButtonClicked(gui, grid, s, list));
	}

	public Button getButton() {
		return button;
	}

	public abstract void ButtonClicked(GUI gui, Grid grid, List<Slider> s, List<Object> list);
}
