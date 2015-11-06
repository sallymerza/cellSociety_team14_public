package Cell;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import GUI.ConfigurationException;
import GUI.GUIVariables;
import GUI.GridControl.PossibleSpecificStates;
//import Main.ConfigurationException;
import GUI.GridControl.Shapes.GridShapeManager;

public class Wator extends Simulation {

	private int fishBreedTime;
	private int sharkBreedTime;
	private int adjacentKelpCount;
	private int kelpCount;
	private int fishCount;
	private int sharkCount;
	private boolean fishHasMoved = false;
	private boolean sharkHasMoved =false;
	private List<Kelp> kelpLocation = new ArrayList<Kelp>();
	private GridShapeManager cellShape;
	private static Map<String, GridShapeManager> map;
	private static PossibleSpecificStates possibleStates = new PossibleSpecificStates();


	public Wator() {
		getMyPossibleStates().put("fish", "BROWN");
		getMyPossibleStates().put("shark", "GRAY");
		getMyPossibleStates().put("kelp", "LIGHTGREEN");
		map = possibleStates.createManager();

	}

	public void countStates(int row, int col,GUIVariables guiVariables, Simulation[][] cellArray){
		for(row = 0; row<guiVariables.getGridRows(); row++){
			for(col = 0; col<guiVariables.getGridColumns(); col++){

				if (cellArray[row][col].getMyState().equals("kelp")) {
					setKelpCount(getKelpCount() + 1);
				}else if(cellArray[row][col].getMyState().equals("fish")){
					setFishCount(getFishCount() + 1);
				}else if(cellArray[row][col].getMyState().equals("shark")){
					setSharkCount(getSharkCount() + 1);
				}
			}
		}

	}


	@Override
	public void checkMyNeighbors(int row, int col, GUIVariables guiVariables, Simulation[][] cellArray) {
		countStates(row, col, guiVariables, cellArray);
		List<Integer[]> possibleNeighbors= new ArrayList<Integer[]>();
		cellShape=map.get(guiVariables.getCellShape());

		possibleNeighbors =cellShape.getPossibleNeighbors(guiVariables.getDirection());

		for (Integer[] loc : possibleNeighbors) {
			if (row + loc[0] >= 0 && col + loc[1] >= 0 && col + loc[1] < cellArray.length && 
					row + loc[0] < cellArray[0].length) {
				if (cellArray[row+ loc[0]][col + loc[1]].getMyState().equals("kelp")) {

					kelpLocation.add(new Kelp(row+loc[0], col+loc[1]));
					setAdjacentKelpCount(getAdjacentKelpCount()+1);
				}
			}
		}
		this.setMyNeightbors(adjacentKelpCount);
	}

	@Override
	public void update(Simulation[][] cellArray) throws ConfigurationException {

		setKelpCount(0);
		setFishCount(0);
		setSharkCount(0);

		setFishBreedTime(getFishBreedTime() + 1);
		setSharkBreedTime(getSharkBreedTime() + 1);

		if(this.getMyState().equals("fish") && !kelpLocation.isEmpty() && !fishHasMoved){
			fishMove(cellArray);
		}

		if(this.getMyState().equals("shark") && !sharkHasMoved){
			sharkMove(cellArray);
		}

		if(this.getMyNeighbors().contains("kelp") && this.getMyState().equals("fish") && !kelpLocation.isEmpty() && (getFishBreedTime() > 4) && (adjacentKelpCount > 0)){
			fishReproduce(cellArray);
		}

		if(this.getMyState().equals("shark") && !kelpLocation.isEmpty() && (getSharkBreedTime() > 8)){
			sharkReproduce(cellArray);
		}

		setAdjacentKelpCount(0);
	}

	private void fishMove(Simulation[][] cellArray) throws ConfigurationException{

		this.setMyState("kelp");
		this.setMyColor(getMyPossibleStates().get(this.getMyState()));	

		this.setMyNextState("fish");
		this.setMyNextColor(getMyPossibleStates().get(this.getMyNextState()));
		fishHasMoved = true;

	}

	private void sharkMove(Simulation[][] cellArray) throws ConfigurationException{

		this.setMyState("kelp");
		this.setMyColor(getMyPossibleStates().get(this.getMyState()));	

		this.setMyNextState("shark");
		this.setMyNextColor(getMyPossibleStates().get(this.getMyNextState()));
		sharkHasMoved = true;

	}



	private void fishReproduce(Simulation[][] cellArray) throws ConfigurationException{
		int row = 0;
		int col = 0;
		if(kelpLocation.size() > 0){
			Kelp kelp = kelpLocation.get(0);	
			col = kelp.getKelpRow();
			row = kelp.getKelpColumn();
			kelpLocation.remove(0);
		}
		cellArray[row][col].setMyState("fish");
		cellArray[row][col].setMyColor(getMyPossibleStates().get("fish"));
		fishHasMoved = false;
		setFishBreedTime(0);

	}

	private void sharkReproduce(Simulation[][] cellArray){
		int row = 0;
		int col = 0;
		if(kelpLocation.size() > 0){
			Kelp kelp = kelpLocation.get(0);	
			col = kelp.getKelpRow();
			row = kelp.getKelpColumn();
			kelpLocation.remove(0);
		}
		try {
			cellArray[row][col].setMyState("shark");
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cellArray[row][col].setMyColor(getMyPossibleStates().get("shark"));
		sharkHasMoved = false;
		setSharkBreedTime(0);
	}

	@Override
	public void needUpdate() {
		this.setNeedsUpdate(true);

	}

	public int getKelpCount() {
		return kelpCount;
	}

	public void setKelpCount(int kelpCount) {
		this.kelpCount = kelpCount;
	}

	public int getFishCount() {
		return fishCount;
	}

	public void setFishCount(int fishCount) {
		this.fishCount = fishCount;
	}

	public int getSharkCount() {
		return sharkCount;
	}

	public void setSharkCount(int sharkCount) {
		this.sharkCount = sharkCount;
	}

	public int getFishBreedTime() {
		return fishBreedTime;
	}

	public void setFishBreedTime(int fishBreedTime) {
		this.fishBreedTime = fishBreedTime;
	}

	public int getSharkBreedTime() {
		return sharkBreedTime;
	}

	public void setSharkBreedTime(int sharkBreedTime) {
		this.sharkBreedTime = sharkBreedTime;
	}

	public int getAdjacentKelpCount() {
		return adjacentKelpCount;
	}

	public void setAdjacentKelpCount(int adjacentKelpCount) {
		this.adjacentKelpCount = adjacentKelpCount;
	}
}