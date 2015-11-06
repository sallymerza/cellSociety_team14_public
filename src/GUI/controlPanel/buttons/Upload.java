package GUI.controlPanel.buttons;

import Main.FirstScene;

/** Author Sally AL **/
public class Upload extends ButtonsClass {
	private static final String description = "Upload";

	public Upload() {
		getmyButton().setText(description);
	}

	@Override
	public void ButtonClicked() {
		FirstScene firstScene = new FirstScene();
		firstScene.ButtonClicked(getStageList());

	}

}
