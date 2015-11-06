package GUI.controlPanel.sliders;

import GUI.controlPanel.SliderClass;
import javafx.scene.control.Label;

/** Author Sally AL **/
public class KeyFrameSlider extends SliderClass {
	private static final double FIVE = 5.0, ONE = 1.0, ZERO = 0.0;

	private static final Label description = new Label("Rate x");

	public KeyFrameSlider() {
		getSlider().setMin(ZERO);
		getSlider().setMax(FIVE);
		getSlider().setValue(ONE);
	}

	@Override
	public Label getDescription() {
		return description;

	}
}
