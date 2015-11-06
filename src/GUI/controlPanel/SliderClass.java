package GUI.controlPanel;

import GUI.Grid;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

/** Author Sally AL **/
public abstract class SliderClass {

	public SliderClass() {
		slider = new Slider();

	}

	private double newValue;
	private Label sliderCaption;
	private Label sliderValueDisplay;
	private Label[] labelsArray = new Label[2];
	private Slider slider;

	public Slider getSlider() {
		return slider;
	}

	public abstract Label getDescription();

	public void createSlider(Grid grid, double initialValue) {

		sliderCaption = getDescription();
		sliderValueDisplay = new Label(Integer.toString((int) initialValue));
		setSliderAttrivutes(initialValue);
		sliderValueDisplay.setText(Integer.toString((int) initialValue));
		setLabelsArray(sliderCaption, sliderValueDisplay);
		slider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				newValue = (new_val.intValue());
				setSliderAttrivutes(newValue);
				sliderValueDisplay.setText(Integer.toString((int) newValue));

			}
		});

	}

	public void setSliderAttrivutes(double value) {
		newValue = value;
	}

	public double getSliderValue() {
		return newValue;
	}

	public void setLabelsArray(Label name, Label value) {

		labelsArray[0] = name;
		labelsArray[1] = value;

	}

	public Label[] getLabelsArray() {
		return labelsArray;
	}

}
