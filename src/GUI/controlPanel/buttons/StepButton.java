package GUI.controlPanel.buttons;

/** Author Sally AL **/
public class StepButton extends ButtonsClass {
	private static final String description = "Step";

	public StepButton() {
		getmyButton().setText(description);
	}

	@Override
	public void ButtonClicked() {
		stopTimeLine();
		updateGui(1);

	}

}
