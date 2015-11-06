package GUI.controlPanel;

import java.util.ArrayList;
import java.util.List;

import GUI.GUIVariables;
import GUI.Grid;
import GUI.controlPanel.ChoiceBox.ChoiceBoxClass;
import GUI.controlPanel.ChoiceBox.DirectionBox;
import GUI.controlPanel.ChoiceBox.EdgeTypeBox;
import GUI.controlPanel.ChoiceBox.GridShapeBox;
import GUI.controlPanel.buttons.ButtonsClass;
import GUI.controlPanel.buttons.Pause;
import GUI.controlPanel.buttons.Play;
import GUI.controlPanel.buttons.Reset;
import GUI.controlPanel.buttons.StepButton;
import GUI.controlPanel.buttons.Upload;
import GUI.controlPanel.checkBox.CheckBoxClass;
import GUI.controlPanel.sliders.KeyFrameSlider;
import javafx.scene.Group;
import javafx.scene.control.Slider;
import javafx.scene.layout.FlowPane;

/** Author Sally AL **/

public class ControlPanel {
	private static final double SHIFT = 50;
	private static final double TEN = 10;
	private static final int FIVE = 5;
	private static final double TWO_HUND = 200;
	private static final double NINTY = 90;
	private static final double FOUR = 4;
	private static final double TWO = 2;
	private Group myRoot;
	private Grid myGrid;
	private List<Object> stageList;
	private GUIVariables myGuiVariables;
	private ChoiceBoxClass[] myChoiceBox;
	private CheckBoxClass imageCheckBox;

	public ControlPanel(GUIVariables guiVariables, Grid grid, List<Object> myStageList) {
		myGuiVariables = guiVariables;
		myGrid = grid;
		stageList = myStageList;
		myRoot = myGuiVariables.getRoot();
	}

	private List<Slider> sliderList = new ArrayList<Slider>();
	private FlowPane buttonsflowpane = new FlowPane(FIVE - TWO, FOUR);
	private FlowPane slidersflowpane = new FlowPane(TWO, FOUR);
	private FlowPane shapeflowpane = new FlowPane(TEN - FOUR, FOUR * TEN);

	public void createControlPanel() {
		setFlowPanes();
		createButtons();
		createSliders();
		createChoiceBox();
		createImageCheckBox();
		// TextClass textClass = new TextClass(myRoot,myXMLReader);
	}

	private void createImageCheckBox() {
		imageCheckBox = new CheckBoxClass(myGuiVariables);
		imageCheckBox.getImageXhexkBox().setLayoutX(slidersflowpane.getLayoutX());
		imageCheckBox.getImageXhexkBox().setLayoutY(slidersflowpane.getLayoutY() + SHIFT);

	}

	private void createChoiceBox() {
		ChoiceBoxClass[] choiceBox = { new GridShapeBox(myGuiVariables), new EdgeTypeBox(myGuiVariables),
				new DirectionBox(myGuiVariables) };
		myChoiceBox = choiceBox;
		for (int i = 0; i < choiceBox.length; i++) {
			shapeflowpane.getChildren().add(choiceBox[i].getBox());
		}
		myRoot.getChildren().add(shapeflowpane);
	}

	private void createButtons() {
		ButtonsClass[] buttonSubClasses = { new Play(), new Pause(), new StepButton(), new Reset(), new Upload() };
		for (int i = 0; i < buttonSubClasses.length; i++) {
			buttonSubClasses[i].createButton(myGuiVariables, myGrid, sliderList, stageList);
			buttonsflowpane.getChildren().add(buttonSubClasses[i].getButton());
		}
		myRoot.getChildren().add(buttonsflowpane);
	}

	private void createSliders() {
		SliderClass[] sliders = { new KeyFrameSlider() };
		double[] sliderIntials = { 1.0 };

		for (int i = 0; i < sliders.length; i++) {
			sliders[i].createSlider(myGrid, sliderIntials[i]);
			slidersflowpane.getChildren().add(sliders[i].getSlider());
			sliderList.add(sliders[i].getSlider());
			slidersflowpane.getChildren().addAll(sliders[i].getLabelsArray());
		}
		myRoot.getChildren().add(slidersflowpane);
	}

	private void setFlowPanes() {
		shapeflowpane.setPrefWidth(TEN);
		shapeflowpane.setLayoutX(myGrid.getGridX() + myGrid.getGridWidth() + FIVE);
		shapeflowpane.setLayoutY(TWO_HUND / TWO);
		buttonsflowpane.setLayoutY(shapeflowpane.getLayoutY() + TWO_HUND);
		buttonsflowpane.setLayoutX(shapeflowpane.getLayoutX());
		buttonsflowpane.setPrefWidth(TWO_HUND);
		slidersflowpane.setLayoutY(buttonsflowpane.getLayoutY() + NINTY);
		slidersflowpane.setLayoutX(buttonsflowpane.getLayoutX());

	}

	public void resetPanelI() {
		for (int i = 0; i < myChoiceBox.length; i++) {
			myChoiceBox[i].setFirstItem();
		}
		imageCheckBox.resetImageCheckBox();

	}
}
