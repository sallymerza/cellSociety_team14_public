/**
 *
 */
package GUI.controlPanel.checkBox;

import java.util.HashMap;
import java.util.Map;

import GUI.GUIVariables;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;

/**
 *
 * @author Sally Al
 *
 */
public class CheckBoxClass {

	CheckBox imageCheckBox = new CheckBox();
	GUIVariables myGuiVariables;
	Map<Boolean, String> options = new HashMap<Boolean, String>();

	public CheckBoxClass(GUIVariables guiVariables) {
		myGuiVariables = guiVariables;
		createOptions();
		setBoxValues(guiVariables.getImageSwitch());
		boxAction();
		myGuiVariables.getRoot().getChildren().add(imageCheckBox);

	}

	private void createOptions() {

		options.put(false, "off");
		options.put(true, "on");

	}

	private void setBoxValues(Boolean value) {
		myGuiVariables.setImageSwitch(value);
		imageCheckBox.setText("show images: " + options.get(value));
		imageCheckBox.setSelected(value);

	}

	private void boxAction() {
		imageCheckBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue ov, Boolean old_val, Boolean new_val) {

				setBoxValues(new_val);

			}
		});
	}

	public void resetImageCheckBox() {

		setBoxValues(myGuiVariables.getXmlReader().getMyImageSwitch());
	}

	public CheckBox getImageXhexkBox() {
		return imageCheckBox;
	}

}
