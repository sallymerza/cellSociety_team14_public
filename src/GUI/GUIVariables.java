/**
 *
 */
package GUI;

import java.util.HashMap;
import java.util.Map;

import Main.XMLReader;
import javafx.scene.Group;

/**
 *
 * @author Sally Al
 *
 */
public class GUIVariables {
	private String simulationName;
	private String cellShape;
	private int gridRows;
	private int gridColumns;
	private String neighborsToCheck;
	private Group root;
	private XMLReader myxmlReader;
	private GUI myGUI;
	private String fileName;
	private String edgeType;
	private String direction;

	private Boolean imageSwitch;
	Map<String, String> resetMap = new HashMap<String, String>();

	private Map<String, String> myParameters;

	public GUIVariables(XMLReader xmlReader) {
		myxmlReader = xmlReader;
		setCellShape(xmlReader.getMyShape());
		setGridColumns(xmlReader.getMyColumns());
		setGridRows(xmlReader.getMyRows());
		setNeighborsToCheck(xmlReader.getMyNeighbors());
		setSimulationName(xmlReader.getMySimulation());
		setEdgeType(xmlReader.getEdgeType());
		setDirection(xmlReader.getDirection());
		setImageSwitch(xmlReader.getMyImageSwitch());
		setMyParameters(xmlReader.getMyParameters());

	}

	public void recetGuiVars() {
		cellShape = myxmlReader.getMyShape();
		edgeType = myxmlReader.getEdgeType();
		direction = myxmlReader.getDirection();
		imageSwitch = myxmlReader.getMyImageSwitch();
	}

	public String getSimulationName() {
		return simulationName;
	}

	public void setSimulationName(String simulationName) {
		this.simulationName = simulationName;
	}

	public String getCellShape() {
		return cellShape;
	}

	public void setCellShape(String cellShape) {
		this.cellShape = cellShape;
	}

	public int getGridRows() {
		return gridRows;
	}

	public void setGridRows(int gridRows) {
		this.gridRows = gridRows;
	}

	public int getGridColumns() {
		return gridColumns;
	}

	public void setGridColumns(int gridColumns) {
		this.gridColumns = gridColumns;
	}

	public String getNeighborsToCheck() {
		return neighborsToCheck;
	}

	public void setNeighborsToCheck(String neighborsToCheck) {
		this.neighborsToCheck = neighborsToCheck;
	}

	public Group getRoot() {
		return root;
	}

	public void setRoot(Group root) {
		this.root = root;
	}

	public XMLReader getXmlReader() {
		return myxmlReader;
	}

	public void setXmlReader(XMLReader xmlReader) {
		this.myxmlReader = xmlReader;
	}

	public GUI getMyGUI() {
		return myGUI;
	}

	public void setMyGUI(GUI myGUI) {
		this.myGUI = myGUI;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getEdgeType() {
		return edgeType;
	}

	public void setEdgeType(String edgeType) {
		this.edgeType = edgeType;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public Map<String, String> getMyParameters() {
		return myParameters;
	}

	public void setMyParameters(Map<String, String> myParameters) {
		this.myParameters = myParameters;

	}

	public Boolean getImageSwitch() {
		return imageSwitch;
	}

	public void setImageSwitch(Boolean imageSwitch) {
		this.imageSwitch = imageSwitch;
	}

}
