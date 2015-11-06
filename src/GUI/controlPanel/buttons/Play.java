package GUI.controlPanel.buttons;

/** Author Sally AL **/
public class Play extends ButtonsClass {
	private static final String description = "Play";

	public Play() {
		getmyButton().setText(description);
	}

	@Override
	public void ButtonClicked() {
		updateGui(-1);

	}

}
