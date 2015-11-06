package GUI.GridControl.Shapes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Sally Al
 *
 */
public abstract class GridShapeManager {
	private static Map<String, List<Integer[]>> myPossibleNeighbors = new HashMap<String, List<Integer[]>>();

	public GridShapeManager() {
		createPossibleNeighbors();
	}

	public List<Integer[]> getPossibleNeighbors(String directions) {
		return myPossibleNeighbors.get(directions);
	}

	private void createPossibleNeighbors() {
		ArrayList<Integer[]> diagonal = new ArrayList<Integer[]>();
		diagonal.add(new Integer[] { 1, 1 });
		diagonal.add(new Integer[] { 1, -1 });
		diagonal.add(new Integer[] { -1, 1 });
		diagonal.add(new Integer[] { -1, -1 });
		ArrayList<Integer[]> cardinal = new ArrayList<Integer[]>();
		cardinal.add(new Integer[] { 1, 0 });
		cardinal.add(new Integer[] { 0, 1 });
		cardinal.add(new Integer[] { -1, 0 });
		cardinal.add(new Integer[] { 0, -1 });
		ArrayList<Integer[]> all = new ArrayList<Integer[]>();
		all.addAll(diagonal);
		all.addAll(cardinal);

		myPossibleNeighbors.put("diagonal", diagonal);
		myPossibleNeighbors.put("cardinal", cardinal);
		myPossibleNeighbors.put("all", all);
	}

}
