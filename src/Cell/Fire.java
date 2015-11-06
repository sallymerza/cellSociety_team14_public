package Cell;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import GUI.ConfigurationException;
import GUI.GUIVariables;
import GUI.GridControl.PossibleSpecificStates;
import GUI.GridControl.Edges.EdgeTypes;
import GUI.GridControl.Shapes.GridShapeManager;

public class Fire extends Simulation {
	private static double probCatch;
	private static PossibleSpecificStates possibleStates = new PossibleSpecificStates();
	private static Map<String, GridShapeManager> map;
	private static Map<String, EdgeTypes> edges;
	private GridShapeManager cellShape;
	private EdgeTypes edgeOption;

	public Fire() {
		getMyPossibleStates().put("burning", "RED");
		getMyPossibleStates().put("tree", "GREEN");
		getMyPossibleStates().put("burnt", "BLACK");
		map = possibleStates.createManager();
		edges = possibleStates.createEdges();
	}

	@Override
	public void checkMyNeighbors(int myX, int myY, GUIVariables guiVariables, Simulation[][] cellArray) {
		if (guiVariables.getMyParameters().get("probCatch").length() == 0)
			probCatch = 0.5;
		else
			probCatch = Double.parseDouble(guiVariables.getMyParameters().get("probCatch"));

		List<Integer[]> possibleNeighbors = new ArrayList<Integer[]>();
		cellShape = map.get(guiVariables.getCellShape());
		possibleNeighbors = cellShape.getPossibleNeighbors(guiVariables.getDirection());
		edgeOption = edges.get(guiVariables.getEdgeType());

		int myBurningNeighbors = 0;
		for (Integer[] loc : possibleNeighbors) {
			if (myX + loc[0] >= 0 && myY + loc[1] >= 0 && myY + loc[1] < cellArray.length
					&& myX + loc[0] < cellArray[0].length) {
				if (cellArray[myX + loc[0]][myY + loc[1]].getMyState().equals("burning")) {
					myBurningNeighbors++;
				}
			}
		}

		this.setMyNeightbors(myBurningNeighbors);

	}

	@Override
	public void needUpdate() {
		if (this.getMyState().equals("burnt"))
			this.setNeedsUpdate(false);
		else
			this.setNeedsUpdate(true);
	}

	@Override
	public void update(Simulation[][] cellArray) throws ConfigurationException {
		if (this.getMyState().equals("tree")) {
			if (this.getMyNeightbors() > 0) {
				if (new java.util.Random().nextInt((int) (1 / probCatch)) == 0) {
					this.setMyState("burning");
				} else
					this.setMyState("tree");
			}
		} else if (this.getMyState().equals("burning"))
			this.setMyState("burnt");
		this.setMyColor(getMyPossibleStates().get(this.getMyState()));
	}

	public double getProbCatch() {
		return probCatch;
	}

	public void setProbCatch(double probCatch) {
		this.probCatch = probCatch;
	}
}