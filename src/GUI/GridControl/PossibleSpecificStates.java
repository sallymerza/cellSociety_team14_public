/**
 *
 */
package GUI.GridControl;

import java.util.HashMap;
import java.util.Map;

import Cell.Fire;
import Cell.GameOfLife;
import Cell.Segregation;
import Cell.Simulation;
import Cell.Wator;
import GUI.GridControl.Edges.EdgeTypes;
import GUI.GridControl.Edges.Finite;
import GUI.GridControl.Edges.Toroidal;
import GUI.GridControl.Shapes.GridShapeManager;
import GUI.GridControl.Shapes.ShapeClass;
import GUI.GridControl.Shapes.SquareGridClass;
import GUI.GridControl.Shapes.SquareGridManager;
import GUI.GridControl.Shapes.TriangleGridClass;
import GUI.GridControl.Shapes.TriangleGridManager;
import javafx.scene.image.Image;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 *
 * @author Sally Al
 *
 */
public class PossibleSpecificStates {

	private Map<String, Simulation> map = new HashMap<String, Simulation>();
	private Map<String, ShapeClass> gridShapes = new HashMap<String, ShapeClass>();
	private Map<String, Shape> cpecificShape = new HashMap<String, Shape>();
	private Map<String, GridShapeManager> managerMap = new HashMap<String, GridShapeManager>();
	private Map<String, EdgeTypes> edges = new HashMap<String, EdgeTypes>();
	private Map<String, Image> imageMap = new HashMap<String, Image>();

	private static final String[] possiblehapes = { "Square", "Triangle" };
	private static final String[] possibleEdges = { "Finite", "Toroidal" };
	private static final String[] possibleDirections = { "all", "cardinal", "diagonal" };

	public String[] getPossibleShapes() {
		return possiblehapes;
	}

	public String[] getPossibleDirections() {
		return possibleDirections;
	}

	public String[] getPossibleEdges() {
		return possibleEdges;
	}

	public Map<String, Image> createImageMap() {
		imageMap.put("alive", new Image(getClass().getClassLoader().getResourceAsStream("alive.gif")));
		imageMap.put("dead", new Image(getClass().getClassLoader().getResourceAsStream("dead.png")));
		imageMap.put("tree", new Image(getClass().getClassLoader().getResourceAsStream("tree.jpg")));
		imageMap.put("burning", new Image(getClass().getClassLoader().getResourceAsStream("burning.jpg")));
		imageMap.put("burnt", new Image(getClass().getClassLoader().getResourceAsStream("burnt.jpg")));
		imageMap.put("agentO", new Image(getClass().getClassLoader().getResourceAsStream("tree.jpg")));
		imageMap.put("agentX", new Image(getClass().getClassLoader().getResourceAsStream("burning.jpg")));
		imageMap.put("empty", new Image(getClass().getClassLoader().getResourceAsStream("burnt.jpg")));
		imageMap.put("fish", new Image(getClass().getClassLoader().getResourceAsStream("burning.jpg")));
		imageMap.put("shark", new Image(getClass().getClassLoader().getResourceAsStream("burnt.jpg")));
		imageMap.put("kelp", new Image(getClass().getClassLoader().getResourceAsStream("water.jpg")));

		return imageMap;
	}

	public Image getImage(String state) {
		return imageMap.get(state);
	}

	public Map<String, Simulation> createGOlStates() {
		map.put("GameOfLife", new GameOfLife());
		map.put("Fire", new Fire());
		map.put("Segregation", new Segregation());
		map.put("Wator", new Wator());
		return map;
	}

	public Simulation getClass(String state) {
		return map.get(state);
	}

	public Map<String, ShapeClass> createShapeInstances() {
		gridShapes.put(possiblehapes[0], new SquareGridClass());
		gridShapes.put(possiblehapes[1], new TriangleGridClass());
		return gridShapes;
	}

	public ShapeClass getShapesMap(String shape) {
		return gridShapes.get(shape);
	}

	public Map<String, Shape> createSpecificShape() {
		cpecificShape.put(possiblehapes[0], new Rectangle());
		cpecificShape.put(possiblehapes[1], new Polygon());
		return cpecificShape;
	}

	public Shape getCpecifiMap(String shape) {
		return cpecificShape.get(shape);
	}

	public Map<String, GridShapeManager> createManager() {
		managerMap.put(possiblehapes[0], new SquareGridManager());
		managerMap.put(possiblehapes[1], new TriangleGridManager());
		return managerMap;
	}

	public Map<String, EdgeTypes> createEdges() {
		edges.put(possibleEdges[0], new Finite());
		edges.put(possibleEdges[1], new Toroidal());
		return edges;
	}

	public EdgeTypes getEdges(String edge) {
		return edges.get(edge);
	}

	public GridShapeManager getManager(String cellShape) {
		return managerMap.get(cellShape);
	}

}
