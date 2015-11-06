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
public class Finite extends EdgeTypes {

	@Override
	public List<Object> checkEdges(int neighborPosX, int neighborPosY, int gridRows, int girdCols) {
		List<Object> list = new ArrayList<Object>();
		Boolean flag = false;
		if (neighborPosX >= 0 && neighborPosY >= 0 && neighborPosY < girdCols && neighborPosX < gridRows) {
			flag = true;
		}
		list.add(flag);
		list.add(neighborPosX);
		list.add(neighborPosY);
		return list;
	}

}
