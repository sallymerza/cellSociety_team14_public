package Cell;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import GUI.ConfigurationException;
import GUI.GUIVariables;
import GUI.GridControl.PossibleSpecificStates;
import GUI.GridControl.Edges.EdgeTypes;
import GUI.GridControl.Shapes.GridShapeManager;


public class GameOfLife extends Simulation {
	private static final Map<String, int[]> myUpdateRules = new HashMap<String, int[]>();
	private Map<String, String> myNextStateMap = new HashMap<String, String>();
	private static final String ALIVE = "alive";
	private static final String DEAD = "dead";
	private static final String AQUA = "AQUA";
	private static final String BLACK = "BLACK";
	private static PossibleSpecificStates possibleStates = new PossibleSpecificStates();
	private static Map<String, GridShapeManager> map;
	private static Map<String, EdgeTypes> edges;
	private GridShapeManager cellShape;
	private EdgeTypes edgeOption;

	public GameOfLife() {
		getMyPossibleStates().put(ALIVE, AQUA);
		getMyPossibleStates().put(DEAD, BLACK);
		myUpdateRules.put(ALIVE, new int[] { 2, 3 });
		myUpdateRules.put(DEAD, new int[] { 3 });
		myNextStateMap.put(ALIVE, DEAD);
		myNextStateMap.put(DEAD, ALIVE);
		map = possibleStates.createManager();
		edges= possibleStates.createEdges();

	}

	@Override
	public void checkMyNeighbors(int myX, int myY, GUIVariables guiVariables, Simulation[][] cellArray) {
		List<Integer[]> possibleNeighbors = new ArrayList<Integer[]>();
		cellShape=map.get(guiVariables.getCellShape());
		possibleNeighbors =cellShape.getPossibleNeighbors(guiVariables.getDirection());
		edgeOption=edges.get(guiVariables.getEdgeType());
		int myAliveNeighbors = 0;
		for (Integer[] loc : possibleNeighbors) {
			//System.out.println(loc[0]+" "+loc[1]);
			List<Object> list=edgeOption.checkEdges(myX + loc[0], myY + loc[1], guiVariables.getGridRows(), guiVariables.getGridColumns());


			if ((boolean) list.get(0)) {

				if (cellArray[(int) list.get(1)][(int) list.get(2)].getMyState().equals(ALIVE)) {


					myAliveNeighbors++;
				}
			}
		}
		this.setMyNeightbors(myAliveNeighbors);

	}

	@Override
	public void needUpdate() {

		boolean live = false;
		int i = 0;
		while (live == false && i < myUpdateRules.get(this.getMyState()).length) {
			live = (this.getMyNeightbors() == myUpdateRules.get(this.getMyState())[i]);
			i++;
		}

		this.setNeedsUpdate(live ^ this.getMyState().equals(ALIVE));
	}

	@Override
	public void update(Simulation[][] cellArray) throws ConfigurationException {
		String nextState = myNextStateMap.get(this.getMyState());
		this.setMyState(nextState);
		this.setMyColor(getMyPossibleStates().get(nextState));
		this.setNeedsUpdate(false);

	}
}
