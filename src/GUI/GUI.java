package GUI;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Cell.Simulation;
import GUI.GridControl.GridInitialization;
import GUI.GridControl.SpecificIntialStates;
import GUI.controlPanel.ControlPanel;
import GUI.controlPanel.Graph.ScrollbarClass;
import Main.XMLReader;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.util.Duration;

public class GUI {

	ScrollbarClass graph;

	private Simulation[][] cellArray;
	private Timeline animation = new Timeline();
	public static final int FRAMES_PER_SECOND = 60;
	private static final int MILLISECOND_DELAY = 10000 / FRAMES_PER_SECOND;
	private static final Map<String, GridInitialization> gridControlMap;

	static {
		gridControlMap = new HashMap<String, GridInitialization>();
		gridControlMap.put("specific", new SpecificIntialStates());
	}

	private Group root = new Group();
	private XMLReader xmlReader = new XMLReader();
	private Grid grid;
	private Map<String, String> myParameters;
	public GUIVariables guiVariable;
	private ControlPanel controlPanel;

	public Scene init(String fileName, int width, int height, List<Object> stageList) {
		try {
			xmlReader.parseXML(fileName);
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		setVariables(fileName);
		grid = new Grid(guiVariable);
		Scene myScene = new Scene(root, width, height);
		cellArray = new Simulation[xmlReader.getMyRows()][xmlReader.getMyColumns()];
		createGrid();
		myParameters = xmlReader.getMyParameters();
		controlPanel = new ControlPanel(guiVariable, grid, stageList);
		controlPanel.createControlPanel();
		createKeyFrame(1);
		graph = new ScrollbarClass();
		graph.create(root, xmlReader.getMyStates());
		return myScene;
	}

	private void setVariables(String fileName) {
		guiVariable = new GUIVariables(xmlReader);
		guiVariable.setFileName(fileName);
		guiVariable.setRoot(root);
		guiVariable.setMyGUI(this);
	}

	public void step() {
		callCalcNeighbors();
		updateCells(cellArray);
		grid.displayGrid2(cellArray);
		resetCells();
		graph.nextTime();
		graph.plotTime(cellArray);

	}

	private void createGrid() {
		GridInitialization gi = new SpecificIntialStates();
		cellArray = new Simulation[grid.getGridInitialRows()][grid.getGridInitialCols()];
		cellArray = gi.createSimulationArray(xmlReader);
		grid.createGridShape(cellArray, root);

	}

	private void callCalcNeighbors() {
		for (int row = 0; row < grid.getGridRows(); row++) {
			for (int col = 0; col < grid.getGridCols(); col++) {
				cellArray[row][col].checkMyNeighbors(row, col, guiVariable, cellArray);
			}
		}
	}

	private void updateCells(Simulation[][] cellArray) {

		for (int row = 0; row < grid.getGridRows(); row++) {
			for (int col = 0; col < grid.getGridCols(); col++) {

				cellArray[row][col].needUpdate();

				if (cellArray[row][col].isNeedsUpdate()) {
					try {
						cellArray[row][col].update(cellArray);
					} catch (ConfigurationException e) {
						e.printStackTrace();
					}
				}
			}
		}

	}

	private void resetCells() {
		for (int row = 0; row < grid.getGridRows(); row++) {
			for (int col = 0; col < grid.getGridCols(); col++) {
				if (cellArray[row][col].getMyNextColor() != null && cellArray[row][col].getMyNextState() != null) {
					cellArray[row][col].setMyColor(cellArray[row][col].getMyNextColor());
					try {
						cellArray[row][col].setMyState(cellArray[row][col].getMyNextState());
					} catch (ConfigurationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					cellArray[row][col].setMyNextColor(null);
					cellArray[row][col].setMyNextState(null);
					cellArray[row][col].setNeedsUpdate(false);

				}
			}
		}
	}

	public void createKeyFrame(int delay) {
		KeyFrame frame = new KeyFrame(Duration.millis(delay * MILLISECOND_DELAY), e -> step());
		animation.getKeyFrames().add(frame);
	}

	public Timeline getTimeLine() {
		return animation;
	}

	public void setTimeLine(Timeline t) {
		animation = t;
	}

	public void resetArray() {
		GridInitialization gi = new SpecificIntialStates();
		cellArray = gi.createSimulationArray(xmlReader);
		grid.createGridShape(cellArray, root);
		graph = new ScrollbarClass();
		graph.create(root, xmlReader.getMyStates());
		controlPanel.resetPanelI();

	}

	public void changeshape() {
		grid.createGridShape(cellArray, root);
	}

}
