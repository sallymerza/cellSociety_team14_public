package GUI.GridControl.Shapes;

import GUI.Grid;
import javafx.scene.shape.Shape;

public abstract class ShapeClass {
	private Shape myNode;
	private static int counter = 0;
	protected static int x = 0;
	protected static int colOfOddRow = 0;
	protected static int rowOfOddCol = 0;
	protected static int rowOfEvenCol = 0;
	protected static double numOfUpTrigs;
	protected static double numOfDownTrigs;

	protected void increaseCounter() {
		counter++;
	}

	protected int getCounter() {
		return counter;
	}

	public abstract void calcCellDim(Grid grid);

	public Shape getMyNode() {
		return myNode;
	}

	public void setMyNode(Shape myNode) {
		this.myNode = myNode;
	}

	public abstract Shape setNodeAttribute(Shape cell, int i, int j, String color);

	/*
	 * public abstract double getX();
	 *
	 * public abstract double getY();
	 */

}
