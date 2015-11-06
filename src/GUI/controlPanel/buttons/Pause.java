package GUI.controlPanel.buttons;

/** Author Sally AL **/
public class Pause extends ButtonsClass {
	private static final String description = "Pause";

	public Pause() {
		getmyButton().setText(description);
	}

	@Override
	public void ButtonClicked() {
		stopTimeLine();

	}

}
