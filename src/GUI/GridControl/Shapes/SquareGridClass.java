package GUI.GridControl.Shapes;

import GUI.Grid;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class SquareGridClass extends ShapeClass {

	private Rectangle myNode;
	private Integer dimx;
	private Integer dimy;

	public SquareGridClass() {
		myNode = new Rectangle();

	}

	@Override
	public void calcCellDim(Grid grid) {
		dimx = (grid.getGridWidth() / grid.getGridCols());
		dimy = (grid.getGridHeight() / grid.getGridRows());

	}

	@Override
	public Rectangle getMyNode() {
		return myNode;
	}

	public void setRect(Rectangle rect) {
		this.myNode = rect;
	}

	@Override
	public Shape setNodeAttribute(Shape cell, int i, int j, String color) {
		Rectangle tempRectangle = (Rectangle) cell;
		tempRectangle.setX(j * dimx + 100);
		tempRectangle.setY(i * dimy + 30);
		tempRectangle.setWidth(dimx);
		tempRectangle.setHeight(dimy);
		tempRectangle.setFill(Color.valueOf(color));
		return tempRectangle;
	}

}
