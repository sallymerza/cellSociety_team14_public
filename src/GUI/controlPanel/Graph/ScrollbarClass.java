
package GUI.controlPanel.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Cell.Simulation;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/**
 * the code in this class is based on the chart code found here:
 * http://download.oracle.com/otndocs/products/javafx/2/samples/Ensemble/#
 * SAMPLES/Charts/Line/Advanced Stock Line Chart Sally Al
 **/

public class ScrollbarClass {

	private List<String> myStates;
	private List<XYChart.Series<Number, Number>> seriesList = new ArrayList<XYChart.Series<Number, Number>>();

	private Map<String, Integer> instances = new HashMap<String, Integer>();

	private final NumberAxis xAxis = new NumberAxis(0, 24, 3);

	private final NumberAxis yAxis = new NumberAxis(0, 50, 5);
	private final LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);

	private double hours = 0;
	private double minutes = 0;
	private double timeInHours = 0;
	private double prevY = 10;
	private double y = 10;
	private List<Integer> list = new ArrayList<Integer>();

	public void create(Group root, String[] states) {
		myStates = Arrays.asList(states);
		root.getChildren().add(createChart());
		list.add(root.getChildren().indexOf(lineChart));
		for (String state : myStates) {
			instances.put(state, 0);
		}

		for (int i = 0; i < myStates.size(); i++) {
			XYChart.Series<Number, Number> chart = new XYChart.Series<Number, Number>();
			seriesList.add(chart);
			seriesList.get(i).setName(myStates.get(i));
			seriesList.get(i).getData().add(new XYChart.Data<Number, Number>(timeInHours, prevY));
			lineChart.getData().add(seriesList.get(i));
		}

	}

	public LineChart<Number, Number> createChart() {

		lineChart.setLayoutX(30);
		lineChart.setLayoutY(530);
		lineChart.setPrefWidth(650);
		lineChart.setPrefHeight(200);
		lineChart.setLegendSide(Side.RIGHT);
		// setup chart
		lineChart.setCreateSymbols(true);

		lineChart.setAnimated(true);
		lineChart.setLegendVisible(true);
		xAxis.setLabel("Time");
		xAxis.setForceZeroInRange(false);
		yAxis.setLabel("count");

		// add starting data

		return lineChart;

	}

	public void plotTime(Simulation[][] cellArray) {
		instances.clear();
		for (int i = 0; i < cellArray.length; i++) {
			for (int j = 0; j < cellArray[0].length; j++) {
				if (instances.containsKey(cellArray[i][j].getMyState())) {
					int value = instances.get(cellArray[i][j].getMyState());
					instances.put(cellArray[i][j].getMyState(), value + 1);
				} else {
					instances.put(cellArray[i][j].getMyState(), 1);
				}
			}
		}

		if ((timeInHours % 1) == 0) {

			prevY = y;
			if (timeInHours > 24) {
				xAxis.setLowerBound(xAxis.getLowerBound() + 1);
				xAxis.setUpperBound(xAxis.getUpperBound() + 1);
			}
		}

		for (int i = 0; i < myStates.size(); i++) {
			if ((instances.get(myStates.get(i))) == null) {
				seriesList.get(i).getData().add(new XYChart.Data<Number, Number>(timeInHours, 0));
			} else {
				seriesList.get(i).getData()
						.add(new XYChart.Data<Number, Number>(timeInHours, instances.get(myStates.get(i))));

			}
		}
		// after 25hours delete old data
		if (timeInHours > 25)
			for (int i = 0; i < seriesList.size(); i++) {
				seriesList.get(i).getData().remove(0);
			}
	}

	public void nextTime() {
		if (minutes == 59) {
			hours++;
			minutes = 0;
		} else {
			minutes++;
		}
		timeInHours = hours + ((1d / 60d) * minutes);
	}

	public void resetgraph(Group root) {

		root.getChildren().remove(list.get(0));

	}
}
