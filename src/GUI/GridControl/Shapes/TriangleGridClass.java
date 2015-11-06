/**
 *
 */
package GUI.GridControl.Shapes;

import GUI.Grid;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

/**
 *
 * @author Sally Al
 *
 */
public class TriangleGridClass extends ShapeClass {
	private Polygon myNode;
	private double base;
	private double height;
	private static final int TWO=2,ONE_EIGHTTY=180;

	public TriangleGridClass() {
		myNode = new Polygon();
	}

	@Override
	public void calcCellDim(Grid grid) {
		numOfUpTrigs = Math.round((grid.getGridCols() + 1) / TWO); // 0.5->1
		numOfDownTrigs = (int) Math.floor(grid.getGridCols() / TWO); // 0.5->0
		if (grid.getGridCols() % TWO == 0) {
			base = (grid.getGridWidth() / (numOfUpTrigs + 0.5));
		} else {
			base = grid.getGridWidth() / numOfUpTrigs;
		}
		height = grid.getGridHeight() / grid.getGridRows();
	}

	@Override
	public Shape setNodeAttribute(Shape cell, int row, int col, String color) {
		int colOfEvenRow = (int) Math.floor(col / TWO);
		myNode = new Polygon(new double[] { ((base / TWO) + (base * colOfEvenRow)), (row * height), (colOfEvenRow * base),
				(height * (row + 1)), ((colOfEvenRow + 1) * base), (height * (row + 1)), });
		if (row % TWO == 0) {
			createUpTrig(row, col);
		} else {
			createDownTrig(row, col);
		}

		myNode.setFill(Color.valueOf(color));
		myNode.setLayoutX(myNode.getLayoutX() + 100);
		myNode.setLayoutY(myNode.getLayoutY() + 30);
		increaseCounter();
		return myNode;
	}

	private void createDownTrig(int row, int col) {
		if ((col % TWO) == 0) {
			myNode.setRotate(ONE_EIGHTTY);
		} else {
			myNode.setLayoutX(base / TWO);
		}
	}

	private void createUpTrig(int row, int col) {
		if ((col % TWO) == 1) {
			myNode.setRotate(ONE_EIGHTTY);
			myNode.setLayoutX(base / TWO);
		}
	}

	@Override
	public Polygon getMyNode() {
		return myNode;
	}

	public void setRect(Polygon rect) {
		this.myNode = rect;
	}
}
