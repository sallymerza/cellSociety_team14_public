package GUI.controlPanel.buttons;

/** Author Sally AL **/
public class Reset extends ButtonsClass {
	private static final String description = "Reset";

	public Reset() {
		getmyButton().setText(description);
	}

	@Override
	public void ButtonClicked() {
		stopTimeLine();
		resetSliders();
		getGuiVariables().recetGuiVars();
		getGuiVariables().getMyGUI().resetArray();

	}

}
