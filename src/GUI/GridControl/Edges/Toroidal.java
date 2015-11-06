/**
 *
 */
package GUI.GridControl.Edges;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sally Al
 *
 */
public class Toroidal extends EdgeTypes {
	@Override
	public List<Object> checkEdges(int neighborPosX, int neighborPosY, int gridRows, int girdCols) {
		List<Object> list = new ArrayList<Object>();

		if (neighborPosY < 0) {
			neighborPosY = girdCols - 1;
		}
		if (neighborPosY >= girdCols) {
			neighborPosY = 0;
		}
		if (neighborPosX < 0) {
			neighborPosX = gridRows - 1;
		}
		if (neighborPosX >= gridRows) {
			neighborPosX = 0;
		}
		list.add(true);
		list.add(neighborPosX);
		list.add(neighborPosY);
		return list;
	}

}
