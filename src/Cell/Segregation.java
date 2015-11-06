package Cell;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;

import GUI.GUIVariables;
import GUI.GridControl.PossibleSpecificStates;
import GUI.GridControl.Edges.EdgeTypes;
import GUI.GridControl.Shapes.GridShapeManager;

public class Segregation extends Simulation {
	private static double percent;
	private static List<int[]> emptyCells = new ArrayList<int[]>();
	private static Random rand = new Random();
	private GridShapeManager cellShape;
	private static Map<String, GridShapeManager> map;
	private static PossibleSpecificStates possibleStates = new PossibleSpecificStates();
	private static Map<String, EdgeTypes> edges;
	private EdgeTypes edgeOption;

	public Segregation() {
		getMyPossibleStates().put("agentO", "RED");
		getMyPossibleStates().put("agentX", "BLUE");
		getMyPossibleStates().put("empty", "BLACK");
		map = possibleStates.createManager();
		edges = possibleStates.createEdges();

	}

	@Override
	public void checkMyNeighbors(int row, int col, GUIVariables guiVariable, Simulation[][] cellArray) {
		if (guiVariable.getMyParameters().get("t").length() == 0)
			percent = 0.4;
		else
			percent = Double.parseDouble(guiVariable.getMyParameters().get("t"));

		List<Integer[]> possibleNeighbors = new ArrayList<Integer[]>();
		cellShape = map.get(guiVariable.getCellShape());
		possibleNeighbors = cellShape.getPossibleNeighbors(guiVariable.getDirection());
		edgeOption = edges.get(guiVariable.getEdgeType());

		if (this.getMyLocation()[0] == 0 && this.getMyLocation()[1] == 0) {
			emptyCells.clear();
		}

		this.trackEmptyCells();
		if (!this.getMyState().equals("empty")) {
			int myAlikeNeighbors = 0;
			int myTotalNeighbors = 0;

			for (Integer[] loc : possibleNeighbors) {
				if (row + loc[0] >= 0 && col + loc[1] >= 0 && col + loc[1] < cellArray.length
						&& row + loc[0] < cellArray[0].length) {
					if (cellArray[row + loc[0]][col + loc[1]].getMyState().equals(this.getMyState()))
						myAlikeNeighbors++;
					myTotalNeighbors++;
				}
			}

			this.setMyNeightbors((double) myAlikeNeighbors / myTotalNeighbors);
		}
	}

	private void trackEmptyCells() {
		int[] location = { this.getMyLocation()[0], this.getMyLocation()[1] };
		if (this.getMyState().equals("empty") && !emptyCells.contains(location)) {
			emptyCells.add(location);
		}
	}

	@Override
	public void update(Simulation[][] cellArray) {

		int[] newLocation = emptyCells.get(rand.nextInt(emptyCells.size()));
		emptyCells.remove(newLocation);
		cellArray[newLocation[0]][newLocation[1]].setMyNextState(this.getMyState());
		cellArray[newLocation[0]][newLocation[1]].setMyNextColor(this.getMyColor());

		cellArray[this.getMyLocation()[0]][this.getMyLocation()[1]].setMyNextState("empty");
		cellArray[this.getMyLocation()[0]][this.getMyLocation()[1]].setMyNextColor(getMyPossibleStates().get("empty"));
		emptyCells.add(new int[] { this.getMyLocation()[0], this.getMyLocation()[1] });
	}

	@Override
	public void needUpdate() {
		if (!this.getMyState().equals("empty") && this.isNeedsUpdate() == false)
			this.setNeedsUpdate(this.getMyNeightbors() < percent);
	}

	public double getPercent() {
		return percent;
	}

	public void setPercent(double percent) {
		this.percent = percent;
	}

}