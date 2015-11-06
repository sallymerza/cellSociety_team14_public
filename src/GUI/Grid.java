package GUI;

import java.util.HashMap;
import java.util.Map;

import Cell.Simulation;
import GUI.GridControl.PossibleSpecificStates;
import GUI.GridControl.Shapes.ShapeClass;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/** Author Sally AL **/
public class Grid {
	private Map<Integer[], ShapeClass> cells = new HashMap<Integer[], ShapeClass>();

	private static final int gridWidth = 500;
	private static final int gridHeight = 500;
	private static final int gridX = 100;
	private static final int gridY = 50;
	private int myInitialRows = 0;
	private int myInitialColumns = 0;
	private int gridCols = 0;
	private int gridRows = 0;
	private GUIVariables myGuiVariable;
	private static PossibleSpecificStates possibleStates = new PossibleSpecificStates();
	private static Map<String, Image> images = new HashMap<String, Image>();

	public Grid(GUIVariables guiVariable) {
		myGuiVariable = guiVariable;
		myInitialRows = guiVariable.getGridRows();
		myInitialColumns = guiVariable.getGridColumns();
		gridRows = myInitialRows;
		gridCols = myInitialColumns;
		images = possibleStates.createImageMap();

	}

	public void createGridShape(Simulation[][] simulationArray, Group root) {
		createGridBG(root);
		cells.clear();
		for (int i = 0; i < simulationArray.length; i++) {
			for (int j = 0; j < simulationArray[0].length; j++) {

				Map<String, ShapeClass> gridShapes = possibleStates.createShapeInstances();
				Integer[] location = { i, j };
				cells.put(location, gridShapes.get(myGuiVariable.getCellShape()));

			}
		}
		displayGrid(root, simulationArray);

	}

	private void createGridBG(Group root) {
		Rectangle border = new Rectangle(100, 30, 500, 500);
		border.toBack();
		border.setFill(Color.BEIGE);
		border.setStroke(Color.YELLOW);
		border.setStrokeWidth(2);
		root.getChildren().add(border);
	}

	public void displayGrid(Group root, Simulation[][] simulationArray) {

		for (Integer[] i : cells.keySet()) {

			ShapeClass shapeClass = cells.get(i);
			shapeClass.calcCellDim(this);
			Shape cellShape = shapeClass.getMyNode();// gets a rectangle
			cellShape = shapeClass.setNodeAttribute(cellShape, i[0], i[1], simulationArray[i[0]][i[1]].getMyColor());
			if (myGuiVariable.getImageSwitch()) {
				showImage(simulationArray[i[0]][i[1]].getMyState(), i);
			}
			cellShape.setStroke(Color.YELLOW);
			cellShape.setStrokeWidth(2);
			root.getChildren().add(cellShape);
		}

	}

	public void displayGrid2(Simulation[][] simulationArray) {

		for (Integer[] i : cells.keySet()) {
			cells.get(i).getMyNode().setFill(Color.valueOf(simulationArray[i[0]][i[1]].getMyColor()));
			if (myGuiVariable.getImageSwitch()) {
				showImage(simulationArray[i[0]][i[1]].getMyState(), i);
			}
		}
	}

	private void showImage(String state, Integer[] i) {
		cells.get(i).getMyNode().setFill(new ImagePattern((images.get(state)), 0, 0, 1, 1, true));
	}

	public int getGridWidth() {
		return gridWidth;
	}

	public int getGridHeight() {
		return gridHeight;
	}

	public void setGridRows(int r) {
		gridRows = r;
	}

	public void setGridCols(int c) {
		gridCols = c;
	}

	public int getGridRows() {
		return gridRows;
	}

	public int getGridCols() {
		return gridCols;
	}

	public int getGridX() {
		return gridX;
	}

	public int getGridY() {
		return gridY;
	}

	public int getGridInitialRows() {
		return myInitialRows;
	}

	public int getGridInitialCols() {
		return myInitialColumns;
	}

}
