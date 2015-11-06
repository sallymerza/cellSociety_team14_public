package Cell;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import GUI.ConfigurationException;
import GUI.GUIVariables;

public abstract class Simulation {
	private String myState;
	private String myColor;
	private String myNextState;
	private String myNextColor;
	private boolean needsUpdate = false;
	private double myNeightbors=0;
	private int[] myLocation;
	private Map<String, String> myPossibleStates = new HashMap<String, String>();
	private List<Integer[]> myNeighbors = new ArrayList<Integer[]>();



	public Map<String, String> getMyPossibleStates() {
		return myPossibleStates;
	}

	public void setMyPossibleStates(Map<String, String> myPossibleStates) {
		this.myPossibleStates = myPossibleStates;
	}

	public List<Integer[]> getMyNeighbors() {
		return myNeighbors;
	}

	public void setMyNeighbors(List<Integer[]> myNeighbors) {
		this.myNeighbors = myNeighbors;
	}

	public int[] getMyLocation() {
		return myLocation;
	}

	public void setMyLocation(int[] myLocation) {
		this.myLocation = myLocation;
	}

	public String getMyState() {
		return myState;
	}

	public void setMyState(String myState) throws ConfigurationException {
		if (!myPossibleStates.containsKey(myState)) {
			throw new ConfigurationException("Invalid State");
		}
		this.myState = myState;
		this.myColor = myPossibleStates.get(myState);
	}

	public String getMyNextState() {
		return myNextState;
	}

	public void setMyNextState(String myNextState) {
		this.myNextState = myNextState;
	}

	public abstract void checkMyNeighbors(int row, int col, GUIVariables guiVariable,  Simulation[][] cellArray);

	public boolean isNeedsUpdate() {
		return needsUpdate;
	}

	public void setNeedsUpdate(boolean needsUpdate) {
		this.needsUpdate = needsUpdate;
	}

	public abstract void update(Simulation[][] cellArray) throws ConfigurationException;

	public abstract void needUpdate();

	public String getMyColor() {
		return myColor;
	}

	public void setMyColor(String myColor) {
		this.myColor = myColor;
	}

	public String getMyNextColor() {
		return myNextColor;
	}

	public void setMyNextColor(String myNextColor) {
		this.myNextColor = myNextColor;
	}

	/**
	 * @return the myNeightbors
	 */
	public double getMyNeightbors() {
		return myNeightbors;
	}

	/**
	 * @param d the myNeightbors to set
	 */
	public void setMyNeightbors(double d) {
		this.myNeightbors = d;
	}

}
